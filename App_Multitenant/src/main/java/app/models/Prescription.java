package app.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "receta")
public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_receta")
    private Integer id;
    
    @Size(max = 75)
    @Column(name = "medicamento")
    private String medicine;
    
    @Size(max = 200)
    @Column(name = "indicacion")
    private String indication;
    
    @Size(max = 150)
    @Column(name = "dosis")
    private String dose;
       
    public Prescription() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

}
