package persistence;

import models.Receta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends CrudRepository<Receta, Integer>{
    
}
