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
public class ChatController {
    @Autowired
    private SimpMessagingTemplate template;    
    
    ObjectMapper mapper = new ObjectMapper();
    
    private final HashMap<String, ArrayList<Chat>> offices;
    
    
    ChatController(){
        this.offices = new HashMap<>();
    }
    
    @MessageMapping("/chat/{id}")
    public void addChat(Message message, @DestinationVariable int id){
        User user = message.getUser();
        Chat chat = new Chat(id, user.getName(), new Date());
        
        ArrayList<Chat> officeChats;        
        if(!offices.containsKey(user.getSchema())){
            offices.put(user.getSchema(), new ArrayList<>());
        }
        
        officeChats = offices.get(user.getSchema());
        officeChats.add(chat);
        
        template.convertAndSend("/topic/chats/"+user.getSchema(), chat);
    }

    @MessageMapping("/chats/{id}")
    @SendTo("/topic/chatsInit/{id}")
    public ArrayList<Chat> getChats(Message message){
        User user = message.getUser();
        
        if(!offices.containsKey(user.getSchema())){
            offices.put(user.getSchema(), new ArrayList<>());
        }
        
        return offices.get(user.getSchema());
    }
}
