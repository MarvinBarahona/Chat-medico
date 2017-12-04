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
@Table(name = "diagnostico")
public class Diagnostico implements Serializable {

    @Column(name = "id_referencia")
    private Integer idReferencia;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_diagnostico")
    private Integer idDiagnostico;

    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    //Es un smallint, grados: leve:3, medio:2 y grave:1    
    @Column(name = "severidad")
    private Short severidad;

    public Diagnostico(Integer idDiagnostico, String descripcion, Short severidad) {
        this.idDiagnostico = idDiagnostico;
        this.descripcion = descripcion;
        this.severidad = severidad;
    }

    public Diagnostico() {
    }

    public Integer getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Integer idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Short getSeveridad() {
        return severidad;
    }

    public void setSeveridad(Short severidad) {
        this.severidad = severidad;
    }

    public Integer getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(Integer idReferencia) {
        this.idReferencia = idReferencia;
    }

}
