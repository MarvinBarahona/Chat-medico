package pojo.jpa;

public class Prescription {
    private long id; 
    private String medicine; 
    private String dose; 
    private String indication; 

    public Prescription() {
    }

    public Prescription(long id, String medicine, String dose, String indication) {
        this.id = id;
        this.medicine = medicine;
        this.dose = dose;
        this.indication = indication;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }
    
    
}
