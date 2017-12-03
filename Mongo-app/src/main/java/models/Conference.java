package models;

import java.util.Date;
import org.springframework.data.annotation.Id;

public class Conference {
    @Id
    private String id;
    
    private Date date;
    private String topic;
    private String schema;

    public Conference() {
    }

    public Conference(Date date, String topic, String schema) {
        this.date = date;
        this.topic = topic;
        this.schema = schema;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }    
}
