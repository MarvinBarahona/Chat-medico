package app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import pojo.Message;
import pojo.User;
import pojo.jpa.Patient;

@Controller
public class PatientController {
    ObjectMapper mapper = new ObjectMapper();
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8110/";
    
    @Autowired
    private SimpMessagingTemplate template; 
    
    @MessageMapping("/getPatient/{id}")
    @SendTo("/topic/getPatientResponse/{id}")
    public Patient getPatient(Message message, @DestinationVariable String id){
        User user = message.getUser();
        System.out.println("Getting user");
        
        return restTemplate.getForObject(url + user.getSchema() + "/patients/" + id , Patient.class);      
    }
    
    @MessageMapping("/savePatient/{id}")
    public void savePatient(Message message) throws Exception{
        System.out.println("Saving user");
        final User user = message.getUser();
        final Patient patient = mapper.convertValue(message.getObject(), Patient.class);
        
        // Obtener una sesión de token. 
        StompClient stompClient = new StompClient();
        StompSession session = stompClient.connect(8090);
        
        // Suscribirse a la respuesta exitosa de la app.
        session.subscribe("/topic/createUserResponse/"+user.getId(), new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders sh) {return byte[].class;}

            @Override
            public void handleFrame(StompHeaders sh, Object o) {
                try {
                    System.out.println("Saving patient");
                    User savedUser = mapper.readValue(new String((byte[]) o, "UTF-8"), User.class);
                    
                    patient.setId(savedUser.getId());
                    
                    Patient returnPatient = restTemplate.postForObject(url + user.getSchema() + "/patients" , patient, Patient.class);
                     
                    
                    template.convertAndSend("/topic/savePatientResponse/"+user.getId(), returnPatient);
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        User createUser = new User(patient, user.getSchema());        
        
        // Mandar mensaje a la app. 
        String m = mapper.writeValueAsString(createUser);
        session.send("/auth/createUser/"+user.getId(), m.getBytes("UTF-8"));
    }
    
    @MessageMapping("/savePatients/{id}")
    public void savePatients(Message message) throws Exception{
        System.out.println("Saving users");
        final User user = message.getUser();
        final ArrayList<Patient> patients = mapper.convertValue(message.getObject(), new TypeReference<ArrayList<Patient>>(){});
        
        // Obtener una sesión de token. 
        StompClient stompClient = new StompClient();
        StompSession session = stompClient.connect(8090);
        
        
        // Suscribirse a la respuesta exitosa de la app.
        session.subscribe("/topic/createUsersResponse/"+user.getId(), new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders sh) {return byte[].class;}

            @Override
            public void handleFrame(StompHeaders sh, Object o) {
                try {
                    System.out.println("Saving patients");
                    ArrayList<User> savedUsers = mapper.readValue(new String((byte[]) o, "UTF-8"), new TypeReference<ArrayList<User>>(){});
                    
                    for(int i=0; i<savedUsers.size(); i++){
                        patients.get(i).setId(savedUsers.get(i).getId());
                    }
                    
                    Patient[] returnPatients = restTemplate.postForObject(url + user.getSchema() + "/patients/masive" , patients, Patient[].class);
                     
                    template.convertAndSend("/topic/savePatientsResponse/"+user.getId(), returnPatients);
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        ArrayList<User> createUsers = new ArrayList<>();
        patients.forEach((patient) -> {
            createUsers.add(new User(patient, user.getSchema()));
        });
        
        // Mandar mensaje a la app. 
        String m = mapper.writeValueAsString(createUsers);
        session.send("/auth/createUsers/"+user.getId(), m.getBytes("UTF-8"));        
    }
}
