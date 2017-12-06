package pojo.general;

import pojo.LoginUser;

public class Office {
    private long id;
    private String name;
    private String address;
    private String description;
    private String schema;
    private LoginUser admin;
    private LoginUser nurse;

    public Office() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public LoginUser getAdmin() {
        return admin;
    }

    public void setAdmin(LoginUser admin) {
        this.admin = admin;
    }

    public LoginUser getNurse() {
        return nurse;
    }

    public void setNurse(LoginUser nurse) {
        this.nurse = nurse;
    }
}
