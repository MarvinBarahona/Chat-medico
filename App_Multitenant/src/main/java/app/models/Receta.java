package app.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "receta")
public class Receta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_receta")
    private Integer idReceta;
    
    @Size(max = 75)
    @Column(name = "medicamento")
    private String medicamento;
    
    @Size(max = 200)
    @Column(name = "indicacion")
    private String indicacion;
    
    @Size(max = 150)
    @Column(name = "dosis")
    private String dosis;
       
    public Receta() {
    }

    public Receta(Integer idReceta, String medicamento, String dosis, String indicacion) {
        this.idReceta = idReceta;
        this.medicamento = medicamento;
        this.indicacion = indicacion;
    }

    public Integer getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }


    public String getIndicacion() {
        return indicacion;
    }

    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }
}
