package app.controllers;

import app.models.Consultation;
import app.models.Doctor;
import app.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import app.repository.ConsultationRepository;
import app.repository.DoctorRepository;
import app.repository.PatientRepository;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{tenantid}/consultations")
public class ConsultationController {
    @Autowired
    private ConsultationRepository consultationRepository;
    
    @Autowired 
    private DoctorRepository doctorRepository;
    
    @Autowired 
    private PatientRepository patientRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Consultation create(@RequestBody Consultation consultation) {
        System.out.println("Saving consultation");
        return consultationRepository.save(consultation);
    }
    
    @RequestMapping(value="/patients/{id}", method = RequestMethod.GET)
    public ArrayList<Consultation> getPatientHistory(@PathVariable int id) {
        System.out.println("Getting patient history");
        Patient patient =  patientRepository.findOne(id);
        return consultationRepository.findByPatient(patient);
    }
    
    @RequestMapping(value="/doctors/{id}", method = RequestMethod.GET)
    public ArrayList<Consultation> getDoctorHistory(@PathVariable int id) {
        System.out.println("Getting doctor history");
        Doctor doctor = doctorRepository.findOne(id);
        return consultationRepository.findByDoctor(doctor);
    }
}
