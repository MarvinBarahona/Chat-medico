package models;

public class User {
    String name; 
    String role;
    String officeName;
    String schema;

    public User(){}

    public User(String name, String role, String officeName, String schema) {
        this.name = name;
        this.role = role;
        this.officeName = officeName;
        this.schema = schema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
    
}

