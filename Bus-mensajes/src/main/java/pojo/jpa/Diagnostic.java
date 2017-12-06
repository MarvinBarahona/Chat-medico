package pojo.jpa;

public class Diagnostic {
    private long id; 
    private String title; 
    private String description; 
    private String symptom; 

    public Diagnostic() {
    }

    public Diagnostic(long id, String title, String description, String symptom) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.symptom = symptom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
    
    
}
