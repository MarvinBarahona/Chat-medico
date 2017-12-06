package pojo.jpa;

public class Reference {
    private long id; 
    private String speciality; 
    private String description; 

    public Reference() {
    }

    public Reference(long id, String speciality, String description) {
        this.id = id;
        this.speciality = speciality;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
