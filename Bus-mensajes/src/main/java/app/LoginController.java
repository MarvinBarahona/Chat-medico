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

@Controller
public class LoginController {
    
    @Autowired
    private SimpMessagingTemplate template;    
    
    ObjectMapper mapper = new ObjectMapper();
    
    @MessageMapping("/login")
    public void login(LoginUser user) throws Exception {
        StompClient stompClient = new StompClient();
        StompSession session = stompClient.connect(8090);
        
        session.subscribe("/topic/loginResponse", new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders sh) {
                return byte[].class;
            }

            @Override
            public void handleFrame(StompHeaders sh, Object o) {
                try {
                    Message mensaje = mapper.readValue(new String((byte[]) o), Message.class);
                    template.convertAndSend("/topic/loginResponse", mensaje);
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        String m = mapper.writeValueAsString(user);
        session.send("/app/login", m.getBytes());
    }
}
