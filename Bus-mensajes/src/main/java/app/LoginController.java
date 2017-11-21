package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Controller;
import pojo.LoginUser;
import pojo.User;

@Controller
public class LoginController {
    
    @Autowired
    private SimpMessagingTemplate template;    
    
    ObjectMapper mapper = new ObjectMapper();
    
    // Este método se ejecuta al mandar un mensaje a /app/login
    @MessageMapping("/login")
    public void login(LoginUser user) throws Exception {
        // Obtener una sesión de token. 
        StompClient stompClient = new StompClient();
        StompSession session = stompClient.connect(8090);
        
        // Suscribirse a la respuesta exitosa de la app.
        session.subscribe("/topic/loginResponse", new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders sh) {return byte[].class;}

            @Override
            public void handleFrame(StompHeaders sh, Object o) {
                try {
                    User user = mapper.readValue(new String((byte[]) o), User.class);
                    template.convertAndSend("/topic/loginResponse", user);
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // Suscribirse a la respuesta fallida de la app.
        session.subscribe("/topic/loginResponse/error", new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders sh) {return byte[].class;}

            @Override
            public void handleFrame(StompHeaders sh, Object o){
                try {
                    //Retorna mensaje de error al sitio.
                    String error = mapper.readValue(new String((byte[]) o), String.class);
                    template.convertAndSend("/topic/loginResponse/error", error);
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        // Mandar mensaje a la app. 
        String m = mapper.writeValueAsString(user);
        session.send("/auth/login", m.getBytes());
    }
}
