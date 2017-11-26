package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pojo.ChatMessage;
import pojo.Message;
import pojo.User;

@Controller
public class ChatController {    
    ObjectMapper mapper = new ObjectMapper();
    
    @MessageMapping("/joinChat/{id}")
    @SendTo("/topic/chat/{id}")
    public ChatMessage joinChat(Message message){
        User user = message.getUser();
        String responseMessage = user.getName() + " se ha unido al chat";  
        
        return new ChatMessage("system", responseMessage);
    }
    
    @MessageMapping("/leaveChat/{id}")
    @SendTo("/topic/chat/{id}")
    public ChatMessage leaveChat(Message message){
        User user = message.getUser();        
        String responseMessage = user.getName() + " ha abandonado el chat";    
        
        return new ChatMessage("system", responseMessage);
    }
    
    @MessageMapping("/chat/{id}")
    @SendTo("/topic/chat/{id}")
    public ChatMessage sendMessage(Message message){
        User user = message.getUser();        
        return new ChatMessage(user.getName(), String.valueOf(message.getObject()));
    }    
}
