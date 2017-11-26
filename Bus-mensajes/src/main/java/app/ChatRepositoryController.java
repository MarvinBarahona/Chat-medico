package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import pojo.Chat;
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
        System.out.println("Agregando chat");
        User user = message.getUser();
        Chat chat = new Chat(id, String.valueOf(message.getObject()), new Date());
        
        HashMap<String, Chat> officeChats;        
        if(!offices.containsKey(user.getSchema())){
            offices.put(user.getSchema(), new HashMap<>());
        }
        
        officeChats = offices.get(user.getSchema());
        officeChats.put(chat.getId(), chat);
        
        template.convertAndSend("/topic/addChat/"+user.getSchema(), chat);
        if(id.length() > 10) template.convertAndSend("/topic/addConversatory/"+user.getSchema(), chat);
    }
    
    @MessageMapping("/startChat/{id}")
    public void startChat(Message message, @DestinationVariable String id){
        System.out.println("Empezando chat");
        User user = message.getUser();
        
        HashMap<String, Chat> officeChats = offices.get(user.getSchema());
        Chat chat = officeChats.get(id);
              
        chat.setState("En curso");
        template.convertAndSend("/topic/addChat/"+user.getSchema(), chat);
    }
    
    @MessageMapping("/endChat/{id}")
    public void endChat(Message message, @DestinationVariable String id){
        System.out.println("Terminando chat");
        User user = message.getUser();
        
        HashMap<String, Chat> officeChats = offices.get(user.getSchema());
        Chat chat = officeChats.get(id);
        
        if(chat != null){
            chat.setState("Terminada");        
            template.convertAndSend("/topic/addChat/"+user.getSchema(), chat); 
        }       
    }
    
    @MessageMapping("/removeChat/{id}")
    public void removeChat(Message message){
        System.out.println("Eliminando chat");
        User user = message.getUser();
        HashMap<String, Chat> officeChats = offices.get(user.getSchema());
        officeChats.remove(String.valueOf(user.getId()));
        
        template.convertAndSend("/topic/removeChat/"+user.getSchema(), user.getId());
    }

    @MessageMapping("/getChats/{id}")
    @SendTo("/topic/getChatsResponse/{id}")
    public ArrayList<Chat> getChats(Message message){
        System.out.println("Agregando chat");
        User user = message.getUser();
        
        if(!offices.containsKey(user.getSchema())){
            offices.put(user.getSchema(), new HashMap<>());
        }
        
        return new ArrayList<>(offices.get(user.getSchema()).values());
    }   
}
