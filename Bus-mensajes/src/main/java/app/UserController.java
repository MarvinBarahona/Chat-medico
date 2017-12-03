package app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
import pojo.Message;
import pojo.User;

@Controller
public class UserController {
    
    @Autowired
    private SimpMessagingTemplate template;    
    
    ObjectMapper mapper = new ObjectMapper();
    
    @MessageMapping("/getUsers/{id}")
    public void getUsers(Message message, @DestinationVariable String id) throws Exception {        
        User user = message.getUser();
        System.out.println("Getting users " + user.getSchema());
        
        // Obtener una sesión de token. 
        StompClient stompClient = new StompClient();
        StompSession session = stompClient.connect(8090);
        
        // Suscribirse a la respuesta exitosa de la app.
        session.subscribe("/topic/getUsersResponse/"+id, new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders sh) {return byte[].class;}

            @Override
            public void handleFrame(StompHeaders sh, Object o) {
                try {
                    System.out.println("Sending users " + user.getSchema());
                    ArrayList<User> users = mapper.readValue(new String((byte[]) o, "UTF-8"), new TypeReference<ArrayList<User>>(){});
                    template.convertAndSend("/topic/getUsersResponse/"+id, users);
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // Mandar mensaje a la app. 
        String m = mapper.writeValueAsString(user.getSchema());
        session.send("/auth/getUsers/"+id, m.getBytes());
    }
    
    @MessageMapping("/saveUser/{id}")
    public void saveUser(Message message, @DestinationVariable String id) throws Exception {
        System.out.println("Saving user");
        User user = mapper.convertValue(message.getObject(), User.class);
        
        // Obtener una sesión de token. 
        StompClient stompClient = new StompClient();
        StompSession session = stompClient.connect(8090);
        
        // Mandar mensaje a la app. 
        String m = mapper.writeValueAsString(user);
        session.send("/auth/saveUser/"+id, m.getBytes());
        
        session.disconnect();
    }
}
