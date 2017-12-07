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
public class Consultation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id_consulta")
    private Integer id;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @ManyToOne
    @JoinColumn(name = "id_diagnostico")
    private Diagnostic diagnostic;
    
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Doctor doctor;
    
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(name = "id_receta", nullable = true)
    private Prescription prescription;    
    
    @ManyToOne
    @JoinColumn(name = "id_referencia", nullable = true)
    private Reference reference;


    public Consultation() {
    }

    public Consultation(Date date, Diagnostic diagnostic, Doctor doctor, Patient patient, Prescription prescription, Reference reference) {
        this.date = date;
        this.diagnostic = diagnostic;
        this.doctor = doctor;
        this.patient = patient;
        this.prescription = prescription;
        this.reference = reference;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Diagnostic getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(Diagnostic diagnostic) {
        this.diagnostic = diagnostic;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

    
}
