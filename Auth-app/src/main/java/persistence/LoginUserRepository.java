package persistence;

import org.springframework.data.repository.CrudRepository;
import models.LoginUser;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginUserRepository extends CrudRepository<LoginUser, Long>{
    LoginUser findByUsername(String username);
}
