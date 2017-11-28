package app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
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
import pojo.Conference;
import pojo.Message;
import pojo.User;

@Controller
public class ConferenceController {
    @Autowired
    private SimpMessagingTemplate template;  
    
    ObjectMapper mapper = new ObjectMapper();
    
    @MessageMapping("/newConference/{id}")
    public void newConference(Message message, @DestinationVariable String id) throws Exception {
        System.out.println("Saving conference");
        User user = message.getUser();
        Conference conference = mapper.convertValue(message.getObject(), Conference.class);
        conference.setSchema(user.getSchema());
        
        StompClient stompClient = new StompClient();
        StompSession session = stompClient.connect(8100);
        
        // Suscribirse a la respuesta exitosa de la app.
        session.subscribe("/topic/newConferenceResponse/"+id, new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders sh) {return byte[].class;}

            @Override
            public void handleFrame(StompHeaders sh, Object o) {
                System.out.println("Sending conference");
                try {
                    Conference conference = mapper.readValue(new String((byte[]) o, "UTF-8"), Conference.class);
                    template.convertAndSend("/topic/addConference/"+conference.getSchema(), conference);
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // Mandar mensaje a la app. 
        String m = mapper.writeValueAsString(conference);
        session.send("/mongo/newConference/"+id, m.getBytes());
    }
    
    @MessageMapping("/getConferences/{id}")
    public void getConferences(Message message, @DestinationVariable String id) throws Exception {
        System.out.println("Getting conferences");
        User user = message.getUser();
        
        StompClient stompClient = new StompClient();
        StompSession session = stompClient.connect(8100);
        
        // Suscribirse a la respuesta exitosa de la app.
        session.subscribe("/topic/getConferencesResponse/"+id, new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders sh) {return byte[].class;}

            @Override
            public void handleFrame(StompHeaders sh, Object o) {
                try {
                    System.out.println("Sending conferences");
                    List<Conference> conferences = mapper.readValue(new String((byte[]) o, "UTF-8"), new TypeReference<List<Conference>>(){});
                    template.convertAndSend("/topic/getConferencesResponse/"+id, conferences.toArray());
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // Mandar mensaje a la app. 
        String m = mapper.writeValueAsString(user.getSchema());
        session.send("/mongo/getConferences/"+id, m.getBytes());
    }
    
    @MessageMapping("/removeConference/{id}")
    public void removeConference(Message message, @DestinationVariable String id) throws Exception {
        System.out.println("Deleting conference");
        Conference conference = mapper.convertValue(message.getObject(), Conference.class);
        
        StompClient stompClient = new StompClient();
        StompSession session = stompClient.connect(8100);
        
        // Suscribirse a la respuesta exitosa de la app.
        session.subscribe("/topic/deleteConferenceResponse/"+id, new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders sh) {return byte[].class;}

            @Override
            public void handleFrame(StompHeaders sh, Object o) {
                try {
                    Conference conference = mapper.readValue(new String((byte[]) o, "UTF-8"), Conference.class);
                    template.convertAndSend("/topic/removeConference/"+conference.getSchema(), conference);
                    session.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // Mandar mensaje a la app. 
        String m = mapper.writeValueAsString(conference);
        session.send("/mongo/deleteConference/"+id, m.getBytes());
    }
}
