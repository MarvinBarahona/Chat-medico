package pojo.chat;

import java.util.Date;

public class Chat {
    private String id;
    private String state;
    private String name;
    private Date waitingSince;

    public Chat(String id, String name, Date waitingSince) {
        this.id = id;
        this.state = "Pendiente";
        this.name = name;
        this.waitingSince = waitingSince;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getWaitingSince() {
        return waitingSince;
    }

    public void setWaitingSince(Date waitingSince) {
        this.waitingSince = waitingSince;
    }
    
    public String ToString(){
        return String.format("Chat %1$s de %2$s está %3$s", this.id, this.name, this.state);
    }
}
