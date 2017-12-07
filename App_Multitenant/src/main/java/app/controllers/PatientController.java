package app.controllers;

import app.models.Patient;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import app.repository.PatientRepository;

@RestController
@RequestMapping("/{tenantid}/patients")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Patient> get() {
        System.out.println("Getting patients");
        return (Collection<Patient>) patientRepository.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Patient get(@PathVariable int id) {
        System.out.println("Getting patient");
        return patientRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Patient create(@RequestBody Patient patient) {
        System.out.println("Saving patient");
        return patientRepository.save(patient);
    }  

    @RequestMapping(value="/masive", method = RequestMethod.POST)
    public Collection<Patient> create(@RequestBody Collection<Patient> patients) {
        System.out.println("Saving patients");
        return (Collection<Patient>) patientRepository.save(patients);
    } 
}
