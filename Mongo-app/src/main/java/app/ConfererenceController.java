package app;

import java.util.List;
import models.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import persistence.ConferenceRepository;

@Controller
public class ConfererenceController {
    
    @Autowired
    private ConferenceRepository repository;
    
    @MessageMapping("/getConferences/{id}")
    @SendTo("/topic/getConferencesResponse/{id}")
    public List<Conference> getConferences(String schema){
        System.out.println("getConferences");
        return repository.findBySchema(schema);
    }
    
    @MessageMapping("/newConference/{id}")
    @SendTo("/topic/newConferenceResponse/{id}")
    public Conference newConference(Conference conference){
        System.out.println("Save conference");
        repository.save(conference);        
        return conference;
    }
    
    @MessageMapping("/deleteConference/{id}")
    @SendTo("/topic/deleteConferenceResponse/{id}")
    public Conference deleteConference(Conference conference){
        System.out.println("Delete conference");
        repository.delete(conference);
        return conference;
    }
}
