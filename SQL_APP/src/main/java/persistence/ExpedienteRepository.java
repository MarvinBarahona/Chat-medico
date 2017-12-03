package persistence;

import models.Expediente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpedienteRepository extends CrudRepository<Expediente, Integer>{
    
}
