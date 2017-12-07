package app.repository;

import app.models.Consultation;
import app.models.Doctor;
import app.models.Patient;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends CrudRepository<Consultation, Integer>{
    //Buscar por Consultas por pacientes
    ArrayList<Consultation> findByPatient(Patient paciente);
    
    ArrayList<Consultation> findByDoctor(Doctor doctor);
    
}
