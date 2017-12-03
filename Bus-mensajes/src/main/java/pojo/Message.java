package pojo;

public class Message {

    private User user;
    private Object object;

    public Message(User user, Object object) {
        this.user = user;
        this.object = object;
    }

    public Message() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }   
}