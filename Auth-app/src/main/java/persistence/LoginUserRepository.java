package persistence;

import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import models.LoginUser;
import models.Office;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginUserRepository extends CrudRepository<LoginUser, Long>{
    LoginUser findByUsername(String username);
    ArrayList<LoginUser> findByOffice(Office office);
}
