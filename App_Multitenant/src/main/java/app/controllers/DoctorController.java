package app.controllers;

import app.models.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import app.repository.DoctorRepository;
import java.util.Collection;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{tenantid}/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Doctor> get() {
        System.out.println("Getting doctors");
        return (Collection<Doctor>) doctorRepository.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Doctor get(@PathVariable int id) {
        System.out.println("Getting doctor");
        return doctorRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Doctor create(@RequestBody Doctor doctor) {
        System.out.println("Saving doctor");
        return doctorRepository.save(doctor);
    }  

    @RequestMapping(value="/masive", method = RequestMethod.POST)
    public Collection<Doctor> create(@RequestBody Collection<Doctor> doctors) {
        System.out.println("Saving doctors");
        return (Collection<Doctor>) doctorRepository.save(doctors);
    } 
}
