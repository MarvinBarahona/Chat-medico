package app;

import models.LoginUser;
import models.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    @MessageMapping("/login")
    @SendTo("/topic/loginResponse")
    public User replicarMensaje(LoginUser loginUser) throws Exception {
        User user = null;
        if("Juan".equals(loginUser.getUsername()))
            user = new User("Juan", "Pérez");
        return user;
    }
}