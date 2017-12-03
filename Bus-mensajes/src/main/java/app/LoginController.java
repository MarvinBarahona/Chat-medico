package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
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
    @MessageMapping("/login/{id}")
    public void login(LoginUser user, @DestinationVariable String id) throws Exception {
        System.out.println("Login attemp " + id);
        // Obtener una sesión de token. 
        StompClient stompClient = new StompClient();
        StompSession session = stompClient.connect(8090);
        
        // Suscribirse a la respuesta exitosa de la app.
        session.subscribe("/topic/loginResponse/"+id, new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders sh) {return byte[].class;}

            @Override
            public void handleFrame(StompHeaders sh, Object o) {
                try {
                    System.out.println("Login success " + id);
                    User user = mapper.readValue(new String((byte[]) o, "UTF-8"), User.class);
                    template.convertAndSend("/topic/loginResponse/"+id, user);
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // Suscribirse a la respuesta fallida de la app.
        session.subscribe("/topic/loginResponse/error/"+id, new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders sh) {return byte[].class;}

            @Override
            public void handleFrame(StompHeaders sh, Object o){
                System.out.println("Login error " + id);
                try {
                    //Retorna mensaje de error al sitio.
                    String error = mapper.readValue(new String((byte[]) o,"UTF-8"), String.class);
                    template.convertAndSend("/topic/loginResponse/error/"+id, error);
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        // Mandar mensaje a la app. 
        String m = mapper.writeValueAsString(user);
        session.send("/auth/login/"+id, m.getBytes());
    }
}
