package app.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
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
    
    @Min(value=1)
    @Column(name = "dosis")
    private Float dosis;
    
    @Size(max = 200)
    @Column(name = "indicacion")
    private String indicacion;
    
    @JoinColumn(name = "id_medico", referencedColumnName = "id_medico")
    @ManyToOne
    private Medico medico;
    
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @ManyToOne
    private Paciente paciente;
   
    public Receta() {
    }

    public Receta(Integer idReceta, String medicamento, Float dosis, String indicacion, Medico medico, Paciente paciente) {
        this.idReceta = idReceta;
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.indicacion = indicacion;
        this.medico = medico;
        this.paciente = paciente;
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

    public Float getDosis() {
        return dosis;
    }

    public void setDosis(Float dosis) {
        this.dosis = dosis;
    }

    public String getIndicacion() {
        return indicacion;
    }

    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }    
}
