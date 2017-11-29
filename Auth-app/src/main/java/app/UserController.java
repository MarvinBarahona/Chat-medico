package app;

import java.util.ArrayList;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import persistence.LoginUserRepository;

@Controller
public class UserController {
    @Autowired
    private SimpMessagingTemplate template; 
    
    @Autowired
    private LoginUserRepository repository;
    
    @MessageMapping("/getUsers/{id}")
    @SendTo("/getUsersResponse/{id}")
    public ArrayList<User> getUsers(String schema){
        return null;
    }
}
