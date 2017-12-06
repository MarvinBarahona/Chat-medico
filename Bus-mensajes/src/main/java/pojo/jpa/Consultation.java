package pojo.jpa;

import java.util.Date;

public class Consultation {
   private long id; 
   private Date date; 
   private Doctor doctor; 
   private Patient patient; 
   private Diagnostic diagnostic; 
   private Prescription prescription; 
   private Reference reference; 

    public Consultation() {
    }

    public Consultation(long id, Date date, Doctor doctor, Patient patient, Diagnostic diagnostic, Prescription prescription, Reference reference) {
        this.id = id;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
        this.diagnostic = diagnostic;
        this.prescription = prescription;
        this.reference = reference;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Diagnostic getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(Diagnostic diagnostic) {
        this.diagnostic = diagnostic;
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
