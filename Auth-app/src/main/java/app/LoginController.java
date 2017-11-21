package app;

import models.LoginUser;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
    
    @Autowired
    private SimpMessagingTemplate template; 

    @MessageMapping("/login")
    @SendTo("/topic/loginResponse")
    public User replicarMensaje(LoginUser loginUser) throws Exception {
        System.out.println("Reciving from bus");
        User user = new User(loginUser.getUsername(), "Pérez");
        
        if(!user.getNombre().contains("a")){
            user = null;
            template.convertAndSend("/topic/loginResponse/error", "Error en el email");
        }
        
        System.out.println("Sending to bus");
        return user;
    }
}