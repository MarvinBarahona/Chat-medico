package app;

import pojo.jpa.Consultation;
import pojo.jpa.Diagnostic;
import pojo.jpa.Doctor;
import pojo.jpa.Patient;
import pojo.jpa.Prescription;
import pojo.jpa.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pojo.Message;

@Controller
public class ConsultationController {
    
    ObjectMapper mapper = new ObjectMapper();
    
    @MessageMapping("/getPatientHistory/{id}")
    @SendTo("/topic/getPatientHistoryResponse/{id}")
    public ArrayList<Consultation> getPatientHistory(Message message){
        System.out.println("Getting patient history");
        ArrayList<Consultation> consultations = new ArrayList<>();
        
        Doctor doctor = new Doctor(0, "Juan", "12345", "General", new Date(1123123123));
        Patient patient = new Patient(0, "Juana", "12346", new Date(1231545654));
        Diagnostic diagnostic = new Diagnostic(3, "Enfermo", "Se enfermó por comer cerdito", "Vomitos");
        Prescription prescription = new Prescription(5, "Acetaminofen", "200ml", "En el día");
        Reference reference = new Reference(1, "Oculista", "No ve bien");
        
        Consultation consultation = new Consultation(4, new Date(12323434), doctor, patient, diagnostic, prescription, reference);
        
        consultations.add(consultation);
        consultations.add(consultation);
        consultations.add(consultation);
        consultations.add(consultation);
        
        return consultations;
    }
    
    @MessageMapping("/getDoctorHistory/{id}")
    @SendTo("/topic/getDoctorHistoryResponse/{id}")
    public ArrayList<Consultation> getDoctorHistory(Message message){
        System.out.println("Getting doctor history");
        ArrayList<Consultation> consultations = new ArrayList<>();
        
        Doctor doctor = new Doctor(0, "Juan", "12345", "General", new Date(1123123123));
        Patient patient = new Patient(0, "Juana", "12346", new Date(1231545654));
        Diagnostic diagnostic = new Diagnostic(3, "Enfermo", "Se enfermó por comer cerdito", "Vomitos");
        Prescription prescription = new Prescription(5, "Acetaminofen", "200ml", "En el día");
        Reference reference = new Reference(1, "Oculista", "No ve bien");
        
        Consultation consultation = new Consultation(4, new Date(12323434), doctor, patient, diagnostic, prescription, reference);
        
        consultations.add(consultation);
        consultations.add(consultation);
        consultations.add(consultation);
        consultations.add(consultation);
        
        return consultations;
    }
    
    @MessageMapping("/saveConsultation")
    public void saveConsultatio(Message message){
        System.out.println("Save consultation");
        Consultation consultation = mapper.convertValue(message.getObject(), Consultation.class);
        System.out.print(consultation.getDate().toString() + consultation.getDiagnostic().getSymptom() +
                consultation.getDoctor().getId() + consultation.getPatient().getId() + 
                consultation.getPrescription().getDose() + consultation.getReference().getSpeciality());
    }
}
