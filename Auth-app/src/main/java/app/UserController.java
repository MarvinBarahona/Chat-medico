package app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @MessageMapping("/createUser/{id}")
    @SendTo("/topic/createUserResponse/{id}")
    public User create(User user) {
        System.out.println("Saving user");        
        Office office = officeRepository.findBySchema(user.getSchema());
        
        LoginUser loginUser = new LoginUser(user, office);
        loginUser = userRepository.save(loginUser);
        return new User(loginUser);
    } 
    
    @MessageMapping("/createUsers/{id}")
    @SendTo("/topic/createUsersResponse/{id}")
    public ArrayList<User> create(Object u) {
        System.out.println("Saving users");            
        ArrayList<User> users = null;
        try {            
            users = mapper.readValue(new String((byte[]) u, "UTF-8"), new TypeReference<ArrayList<User>>(){});
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Office office = officeRepository.findBySchema(users.get(0).getSchema());
            
        Collection<LoginUser> loginUsers = new ArrayList<>();
        for(User _user : users){
            loginUsers.add(new LoginUser(_user, office));
        }

        loginUsers = (ArrayList<LoginUser>)userRepository.save(loginUsers);

        users.clear();

        for(LoginUser _loginUser : loginUsers){
            users.add(new User(_loginUser));
        }    
        
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
