package pojo;

public class Message {

    private String token;
    private Object object;

    public Message(String token, Object object) {
        this.token = token;
        this.object = object;
    }

    public Message() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
    
    
   
}