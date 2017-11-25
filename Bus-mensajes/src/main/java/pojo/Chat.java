package pojo;

import java.util.Date;

public class Chat {
    private int id;
    private String patient;
    private Date waitingSince;

    public Chat(int id, String patient, Date waitingSince) {
        this.id = id;
        this.patient = patient;
        this.waitingSince = waitingSince;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public Date getWaitingSince() {
        return waitingSince;
    }

    public void setWaitingSince(Date waitingSince) {
        this.waitingSince = waitingSince;
    }
    
    
}
