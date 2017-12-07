package app.controllers;

import app.models.Consultation;
import app.models.Doctor;
import app.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import app.repository.ConsultationRepository;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/{tenantid}/consultations")
public class ConsultationController {
    @Autowired
    private ConsultationRepository consultationRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Consultation create(@RequestBody Consultation consultation) {
        System.out.println("Saving consultation");
        return consultationRepository.save(consultation);
    }
    
    @RequestMapping(value="/patients/{id}", method = RequestMethod.GET)
    public ArrayList<Consultation> getPatientHistory(@PathVariable int id) {
        System.out.println("Getting patient history");
        Patient patient = new Patient(id);
        return consultationRepository.findByPatient(patient);
    }
    
    @RequestMapping(value="/doctors/{id}", method = RequestMethod.GET)
    public ArrayList<Consultation> getDoctorHistory(@PathVariable int id) {
        System.out.println("Getting doctor history");
        Doctor doctor = new Doctor(id);
        return consultationRepository.findByDoctor(doctor);
    }
}
