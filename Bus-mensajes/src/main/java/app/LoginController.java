package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.Message;
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
    
    @MessageMapping("/login")
    public void login(LoginUser user) throws Exception {
        System.out.println("Reciving from angular");
        StompClient stompClient = new StompClient();
        StompSession session = stompClient.connect(8090);
        
        session.subscribe("/topic/loginResponse", new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders sh) {return byte[].class;}

            @Override
            public void handleFrame(StompHeaders sh, Object o) {
                try {
                    System.out.println("Reciving from app");
                    User user = mapper.readValue(new String((byte[]) o), User.class);
                    System.out.println("Sending to angular");
                    template.convertAndSend("/topic/loginResponse", user);
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        session.subscribe("/topic/loginResponse/error", new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders sh) {return byte[].class;}

            @Override
            public void handleFrame(StompHeaders sh, Object o) {
                System.out.println("Reciving from app");
                String error = new String((byte[]) o);
                System.out.println("Sending to angular");
                template.convertAndSend("/topic/loginResponse/error", error);
                session.disconnect();
            }
        });
        
        String m = mapper.writeValueAsString(user);
        System.out.println("Sending to app");
        session.send("/app/login", m.getBytes());
    }
}
