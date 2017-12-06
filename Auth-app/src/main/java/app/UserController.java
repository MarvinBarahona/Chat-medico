package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import models.LoginUser;
import models.Office;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import persistence.LoginUserRepository;
import persistence.OfficeRepository;

@Controller
public class UserController {
    @Autowired
    private SimpMessagingTemplate template; 
    
    ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private LoginUserRepository userRepository;
    
    @Autowired
    private OfficeRepository officeRepository;
    
    @MessageMapping("/getUsers/{id}")
    @SendTo("/topic/getUsersResponse/{id}")
    public ArrayList<User> getUsers(String schema){
        System.out.println("Getting users");
        Office office = officeRepository.findBySchema(schema);        
        ArrayList<LoginUser> loginUsers = userRepository.findByOffice(office);
        
        ArrayList<User> users = new ArrayList<>();        
        loginUsers.forEach( (LoginUser loginUser) -> {users.add(new User(loginUser));});
        
        return users;
    }
    
    @MessageMapping("/saveUser")
    public void saveUser(User user){
        System.out.println("Saving user");
        
        LoginUser loginUser = userRepository.findOne(user.getId());
        
        loginUser.setActive(user.isActive());
        if(!"".equals(user.getNewPassword()))loginUser.setPassword(BCrypt.hashpw(user.getNewPassword(), BCrypt.gensalt(10)));
        
        userRepository.save(loginUser);
    }
}
