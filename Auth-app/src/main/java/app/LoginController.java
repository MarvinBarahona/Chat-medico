package app;

import models.LoginUser;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import persistence.LoginUserRepository;

@Controller
public class LoginController {
    
    @Autowired
    private SimpMessagingTemplate template; 
    
    @Autowired
    private LoginUserRepository repository;

    // Este método se ejecuta al mandar un mensaje a /auth/login
    @MessageMapping("/login/{id}")
    public void login(LoginUser loginUser, @DestinationVariable String id) throws Exception {
        LoginUser databaseUser = repository.findByUsername(loginUser.getUsername());
        
        if(databaseUser != null && databaseUser.isActive()){            
            if(BCrypt.checkpw(loginUser.getPassword(), databaseUser.getPassword())){
                User user = new User(databaseUser.getId(), databaseUser.getName(), databaseUser.getRole().getName(), databaseUser.getOffice().getName(), databaseUser.getOffice().getSchema());
                // Mandando respuesta exitosa al bus.
                template.convertAndSend("/topic/loginResponse/"+id, user);
            }
            else{
                // Mandando mensaje de error al bus.
                template.convertAndSend("/topic/loginResponse/error/"+id, "Contraseña equivocada");
            }
        }
        else{
            // Mandando mensaje de error al bus.
            template.convertAndSend("/topic/loginResponse/error/"+id, "Cuenta no encontrada");
        }
    }
}