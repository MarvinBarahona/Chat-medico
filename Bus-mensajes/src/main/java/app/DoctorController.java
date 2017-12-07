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
import pojo.jpa.Doctor;

@Controller
public class DoctorController {
    ObjectMapper mapper = new ObjectMapper();
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8110/";
    
    @Autowired
    private SimpMessagingTemplate template; 
    
    @MessageMapping("/getDoctor/{id}")
    @SendTo("/topic/getDoctorResponse/{id}")
    public Doctor getDoctor(Message message, @DestinationVariable String id){
        User user = message.getUser();
        System.out.println("Getting user");
        
        return restTemplate.getForObject(url + user.getSchema() + "/doctors/" + id , Doctor.class);      
    }
    
    @MessageMapping("/saveDoctor/{id}")
    public void saveDoctor(Message message) throws Exception{
        System.out.println("Saving user");
        final User user = message.getUser();
        final Doctor doctor = mapper.convertValue(message.getObject(), Doctor.class);
        
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
                    System.out.println("Saving doctor");
                    User savedUser = mapper.readValue(new String((byte[]) o, "UTF-8"), User.class);
                    
                    doctor.setId(savedUser.getId());
                    
                    Doctor returnDoctor = restTemplate.postForObject(url + user.getSchema() + "/doctors" , doctor, Doctor.class);
                     
                    
                    template.convertAndSend("/topic/saveDoctorResponse/"+user.getId(), returnDoctor);
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        User createUser = new User(doctor, user.getSchema());        
        
        // Mandar mensaje a la app. 
        String m = mapper.writeValueAsString(createUser);
        session.send("/auth/createUser/"+user.getId(), m.getBytes("UTF-8"));
    }
    
    @MessageMapping("/saveDoctors/{id}")
    public void saveDoctors(Message message) throws Exception{
        System.out.println("Saving users");
        final User user = message.getUser();
        final ArrayList<Doctor> doctors = mapper.convertValue(message.getObject(), new TypeReference<ArrayList<Doctor>>(){});
        
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
                    System.out.println("Saving doctors");
                    ArrayList<User> savedUsers = mapper.readValue(new String((byte[]) o, "UTF-8"), new TypeReference<ArrayList<User>>(){});
                    
                    for(int i=0; i<savedUsers.size(); i++){
                        doctors.get(i).setId(savedUsers.get(i).getId());
                    }
                    
                    Doctor[] returnDoctors = restTemplate.postForObject(url + user.getSchema() + "/doctors/masive" , doctors, Doctor[].class);
                     
                    template.convertAndSend("/topic/saveDoctorsResponse/"+user.getId(), returnDoctors);
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        ArrayList<User> createUsers = new ArrayList<>();
        doctors.forEach((doctor) -> {
            createUsers.add(new User(doctor, user.getSchema()));
        });
        
        // Mandar mensaje a la app. 
        String m = mapper.writeValueAsString(createUsers);
        session.send("/auth/createUsers/"+user.getId(), m.getBytes("UTF-8"));        
    }
}
