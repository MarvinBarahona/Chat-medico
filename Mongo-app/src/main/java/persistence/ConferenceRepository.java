package persistence;

import java.util.List;
import models.Conference;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConferenceRepository extends MongoRepository<Conference, String> {
    public List<Conference> findBySchema(String schema);
}
