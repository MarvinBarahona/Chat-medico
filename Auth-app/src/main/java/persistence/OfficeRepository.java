package persistence;

import org.springframework.data.repository.CrudRepository;
import models.Office;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Long>{
    
}
