package pojo.jpa;

import java.util.Date;

public class Doctor {
    private long id; 
    private String name; 
    private String code; 
    private String speciality; 
    private Date birthday;

    public Doctor() {
    }

    public Doctor(long id, String name, String code, String speciality, Date birthday) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.speciality = speciality;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
