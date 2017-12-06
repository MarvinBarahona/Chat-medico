package app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pojo.Message;
import pojo.jpa.Patient;

@Controller
public class PatientController {
    
    ObjectMapper mapper = new ObjectMapper();
    
    @MessageMapping("/getPatient/{id}")
    @SendTo("/topic/getPatientResponse/{id}")
    public Patient getPatient(Message message){
        System.out.println("Getting patient");
        Patient patient = new Patient(3, "Juana", "12346", new Date(1234334));
        return patient;        
    }
    
    @MessageMapping("/savePatient/{id}")
    @SendTo("/topic/savePatientResponse/{id}")
    public Patient savePatient(Message message){
        System.out.println("Saving patient");
        Patient patient = mapper.convertValue(message.getObject(), Patient.class);
        return patient;
    }
    
    @MessageMapping("/savePatients/{id}")
    @SendTo("/topic/savePatientsResponse/{id}")
    public ArrayList<Patient> savePatients(Message message){
        System.out.println("Saving patients");
        ArrayList<Patient> patients = mapper.convertValue(message.getObject(), new TypeReference<ArrayList<Patient>>(){});
        return patients;
    }
}
