package app;

import pojo.jpa.Consultation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import pojo.Message;
import pojo.User;

@Controller
public class ConsultationController {
    String url = "http://localhost:8110/";
    ObjectMapper mapper = new ObjectMapper();
    RestTemplate restTemplate = new RestTemplate();
    
    @MessageMapping("/getPatientHistory/{id}")
    @SendTo("/topic/getPatientHistoryResponse/{id}")
    public Consultation[] getPatientHistory(Message message, @DestinationVariable String id){
        User user = message.getUser();
        System.out.println("Getting patient history");
        
        return restTemplate.getForObject(url + user.getSchema() + "/consultations/patients/" + id, Consultation[].class);
    }
    
    @MessageMapping("/getDoctorHistory/{id}")
    @SendTo("/topic/getDoctorHistoryResponse/{id}")
    public Consultation[] getDoctorHistory(Message message, @DestinationVariable String id){
        User user = message.getUser();
        System.out.println("Getting doctor history");
       
        return restTemplate.getForObject(url + user.getSchema() + "/consultations/doctors/" + id, Consultation[].class);
    }
    
    @MessageMapping("/saveConsultation")
    public void saveConsultation(Message message){
        System.out.println("Saving consultation");
        User user = message.getUser();
        Consultation consultation = mapper.convertValue(message.getObject(), Consultation.class);
        
        restTemplate.postForObject(url + user.getSchema() + "/consultations", consultation, Consultation.class);
    }
}
