package app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pojo.Message;
import pojo.jpa.Doctor;

@Controller
public class DoctorController {
    ObjectMapper mapper = new ObjectMapper();
    
    @MessageMapping("/getDoctor/{id}")
    @SendTo("/topic/getDoctorResponse/{id}")
    public Doctor getDoctor(Message message){
        System.out.println("Getting user");
        Doctor doctor = new Doctor(3, "Juana", "12346", "General", new Date(1234334));
        return doctor;        
    }
    
    @MessageMapping("/saveDoctor/{id}")
    @SendTo("/topic/saveDoctorResponse/{id}")
    public Doctor saveDoctor(Message message){
        System.out.println("Saving doctor");
        Doctor doctor = mapper.convertValue(message.getObject(), Doctor.class);
        return doctor;
    }
    
    @MessageMapping("/saveDoctors/{id}")
    @SendTo("/topic/saveDoctorsResponse/{id}")
    public ArrayList<Doctor> saveDoctors(Message message){
        System.out.println("Saving doctors");
        ArrayList<Doctor> doctors = mapper.convertValue(message.getObject(), new TypeReference<ArrayList<Doctor>>(){});
        return doctors;
    }
}
