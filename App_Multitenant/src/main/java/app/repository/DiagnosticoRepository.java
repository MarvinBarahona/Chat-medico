package app.repository;

import app.models.Diagnostico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticoRepository extends CrudRepository<Diagnostico, Integer>{
    Diagnostico findBySeveridad(Short grado);    
}
