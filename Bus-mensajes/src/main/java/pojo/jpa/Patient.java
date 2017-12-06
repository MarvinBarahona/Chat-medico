package pojo.jpa;

import java.util.Date;

public class Patient {
    private long id; 
    private String name; 
    private String code; 
    private Date birthday;

    public Patient() {
    }

    public Patient(long id, String name, String code, Date birthday) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.birthday = birthday;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
