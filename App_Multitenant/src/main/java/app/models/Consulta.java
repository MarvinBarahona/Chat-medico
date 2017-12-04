package app.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "consulta")
public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_consulta")
    private Integer idConsulta;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @JoinColumn(name = "id_diagnostico", referencedColumnName = "id_diagnostico")
    @ManyToOne
    private Diagnostico diagnostico;
    
    @JoinColumn(name = "id_medico", referencedColumnName = "id_medico")
    @ManyToOne
    private Medico medico;
    
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @ManyToOne
    private Paciente paciente;
    
    @Basic(optional = true)
    @JoinColumn(name = "id_receta", referencedColumnName = "id_receta")
    @ManyToOne
    private Receta receta;

    public Consulta() {
    }

    public Consulta(Integer idConsulta, Date fecha, Diagnostico diagnostico, Medico medico, Paciente paciente) {
        this.idConsulta = idConsulta;
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.medico = medico;
        this.paciente = paciente;
    }

    public Consulta(Integer idConsulta, Date fecha) {
        this.idConsulta = idConsulta;
        this.fecha = fecha;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
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

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }  
}
