package app;

import models.Office;
import models.Role;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import persistence.LoginUserRepository;
import persistence.OfficeRepository;

@Controller
public class OfficeController {
    
    @Autowired
    private LoginUserRepository userRepository;
    
    @Autowired
    private OfficeRepository officeRepository;
    
    @MessageMapping("/createOffice/{id}")
    @SendTo("/topic/createOfficeResponse/{id}")
    public Office login(Office office) throws Exception {
        office.getAdmin().setName(office.getAdmin().getUsername());
        office.getAdmin().setUsername("admin@" + office.getSchema());
        office.getAdmin().setActive(true);
        office.getAdmin().setRole(new Role(1, "admin"));
        office.getAdmin().setPassword(BCrypt.hashpw(office.getAdmin().getPassword(), BCrypt.gensalt(10)));
        
        office.getNurse().setName("Enfermeras de " + office.getName());
        office.getNurse().setUsername("enfermera@" + office.getSchema());
        office.getNurse().setRole(new Role(3, "enfermera"));
        office.getNurse().setActive(true);
        office.getNurse().setPassword(BCrypt.hashpw(office.getNurse().getPassword(), BCrypt.gensalt(10)));
        
        System.out.println("Creating office");
        officeRepository.save(office);
        
        System.out.println("Creating admin");
        office.getAdmin().setOffice(office);
        userRepository.save(office.getAdmin());
        
        System.out.println("Creating nurse");
        office.getNurse().setOffice(office);
        userRepository.save(office.getNurse());
        
        office.setNurse(null);
        office.setAdmin(null);
        
        return office;        
    }
}
