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
import pojo.general.Office;

@Controller
public class OfficeController {
    
    @Autowired
    private SimpMessagingTemplate template;    
    
    ObjectMapper mapper = new ObjectMapper();
    
    // Este método se ejecuta al mandar un mensaje a /app/login
    @MessageMapping("/createOffice/{id}")
    public void login(Office office, @DestinationVariable String id) throws Exception {
        System.out.println("Create office " + id);
        // Obtener una sesión de token. 
        StompClient stompClient = new StompClient();
        StompSession session = stompClient.connect(8090);
        
        // Suscribirse a la respuesta exitosa de la app.
        session.subscribe("/topic/createOfficeResponse/"+id, new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders sh) {return byte[].class;}

            @Override
            public void handleFrame(StompHeaders sh, Object o) {
                try {
                    System.out.println("Create office success " + id);
                    Office office = mapper.readValue(new String((byte[]) o, "UTF-8"), Office.class);
                    template.convertAndSend("/topic/createOfficeResponse/"+id, office);
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // Mandar mensaje a la app. 
        String m = mapper.writeValueAsString(office);
        session.send("/auth/createOffice/"+id, m.getBytes());
    }
}
