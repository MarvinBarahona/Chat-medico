package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Controller;
import pojo.Chat;
import pojo.Conference;
import pojo.Message;
import pojo.User;

@Controller
public class ChatRepositoryController {
    @Autowired
    private SimpMessagingTemplate template;    
    
    ObjectMapper mapper = new ObjectMapper();
    
    private final HashMap<String, HashMap<String, Chat>> offices;
    
    
    ChatRepositoryController(){
        this.offices = new HashMap<>();
    }
    
    @MessageMapping("/addChat/{id}")
    public void addChat(Message message, @DestinationVariable String id){
        System.out.println("Adding chat");
        User user = message.getUser();
        Chat chat = new Chat(id, String.valueOf(message.getObject()), new Date());
        
        HashMap<String, Chat> officeChats;        
        if(!offices.containsKey(user.getSchema())){
            offices.put(user.getSchema(), new HashMap<>());
        }
        
        officeChats = offices.get(user.getSchema());
        if(!officeChats.containsKey(id)){
            officeChats.put(chat.getId(), chat);
            template.convertAndSend("/topic/addChat/"+user.getSchema(), chat);
            if(id.length() > 10) template.convertAndSend("/topic/addStartedChat/"+user.getSchema(), chat);
        }           
    }
    
    @MessageMapping("/startChat/{id}")
    public void startChat(Message message, @DestinationVariable String id) throws Exception{
        System.out.println("Starting chat");
        User user = message.getUser();
        
        HashMap<String, Chat> officeChats = offices.get(user.getSchema());
        Chat chat = officeChats.get(id);
              
        chat.setState("En curso");
        template.convertAndSend("/topic/addChat/"+user.getSchema(), chat);
        if(id.length() > 10){
            template.convertAndSend("/topic/addStartedChat/"+user.getSchema(), chat);
            System.out.println("Deleting conference");
        
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
            String m = mapper.writeValueAsString(id);
            session.send("/mongo/deleteConference/"+id, m.getBytes());
        }       
    }
    
    @MessageMapping("/endChat/{id}")
    public void endChat(Message message, @DestinationVariable String id){
        System.out.println("Ending chat");
        User user = message.getUser();
        
        HashMap<String, Chat> officeChats = offices.get(user.getSchema());
        Chat chat = officeChats.get(id);
        
        if(chat != null){
            chat.setState("Terminada");        
            template.convertAndSend("/topic/addChat/"+user.getSchema(), chat); 
            if(id.length() > 10) template.convertAndSend("/topic/addStartedChat/"+user.getSchema(), chat);
        }       
    }
    
    @MessageMapping("/removeChat/{id}")
    public void removeChat(Message message, @DestinationVariable String id){
        System.out.println("Deleting chat");
        User user = message.getUser();
        HashMap<String, Chat> officeChats = offices.get(user.getSchema());
        Chat chat = officeChats.remove(id);
        
        template.convertAndSend("/topic/removeChat/"+user.getSchema(), chat);
        if(id.length() > 10) template.convertAndSend("/topic/removeStartedChat/"+user.getSchema(), chat);
    }

    @MessageMapping("/getChats/{id}")
    @SendTo("/topic/getChatsResponse/{id}")
    public ArrayList<Chat> getChats(Message message){
        System.out.println("Getting chats");
        User user = message.getUser();
        
        if(!offices.containsKey(user.getSchema())){
            offices.put(user.getSchema(), new HashMap<>());
        }
        
        return new ArrayList<>(offices.get(user.getSchema()).values());
    }   
    
    @MessageMapping("/getStartedChats/{id}")
    @SendTo("/topic/getStartedChatsResponse/{id}")
    public ArrayList<Chat> getStartedChats(Message message){
        System.out.println("Getting started chats");
        User user = message.getUser();
        
        if(!offices.containsKey(user.getSchema())){
            offices.put(user.getSchema(), new HashMap<>());
        }
        
        ArrayList<Chat> chats = new ArrayList<>(offices.get(user.getSchema()).values());
        chats.removeIf((Chat chat) -> chat.getId().length() < 10);
        
        return chats;
    }  
    
    @MessageMapping("/getChat/{id}")
    @SendTo("/topic/getChatResponse/{id}")
    public Chat getChat(Message message, @DestinationVariable String id){
        System.out.println("Getting chat");
        Chat chat = null; 
        User user = message.getUser();
        
        if(offices.containsKey(user.getSchema())){
            HashMap<String, Chat> officeChats = offices.get(user.getSchema());
            if(officeChats.containsKey(id)){
                chat = officeChats.get(id);
            }
        }
        
        return chat;
    }
    
    
}
