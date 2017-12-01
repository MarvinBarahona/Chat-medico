package app;

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
    
    @Autowired
    private LoginUserRepository userRepository;
    
    @Autowired
    private OfficeRepository officeRepository;
    
    @MessageMapping("/getUsers/{id}")
    @SendTo("/topic/getUsersResponse/{id}")
    public ArrayList<User> getUsers(String schema){
        Office office = officeRepository.findBySchema(schema);        
        ArrayList<LoginUser> loginUsers = userRepository.findByOffice(office);
        
        ArrayList<User> users = new ArrayList<>();        
        loginUsers.forEach( (LoginUser loginUser) -> {users.add(new User(loginUser));});
        
        return users;
    }
    
    @MessageMapping("/saveUser/{id}")
    public void saveUser(User user) throws Exception {
        System.out.println("Saving user");
        
        LoginUser loginUser = userRepository.findOne(user.getId());
        
        loginUser.setActive(user.isActive());
        if(user.getNewPassword() != null && !"".equals(user.getNewPassword()))loginUser.setPassword(BCrypt.hashpw(user.getNewPassword(), BCrypt.gensalt(10)));
        
        userRepository.save(loginUser);
    }
}
