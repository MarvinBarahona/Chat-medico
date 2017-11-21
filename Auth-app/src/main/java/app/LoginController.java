package app;

import models.LoginUser;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
    
    @Autowired
    private SimpMessagingTemplate template; 

    // Este método se ejecuta al mandar un mensaje a /auth/login
    @MessageMapping("/login")
    public void login(LoginUser loginUser) throws Exception {
        User user = new User(loginUser.getUsername(), "Pérez");
        
        if(!user.getNombre().contains("a")){
            // Mandando mensaje de error al bus.
            template.convertAndSend("/topic/loginResponse/error", "Error en el email");
        }
        
        else{
            // Mandando respuesta exitosa al bus.
            template.convertAndSend("/topic/loginResponse", user);
        }
    }
}