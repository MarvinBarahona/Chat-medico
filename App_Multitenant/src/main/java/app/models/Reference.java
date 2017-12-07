package app.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "referencia")
public class Reference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_referencia")
    private Integer id;
    
    @Size(max = 150)
    @Column(name = "motivo")
    private String description;
    
    @Size(max = 50)
    @Column(name = "especialidad")
    private String speciality;

    public Reference() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
