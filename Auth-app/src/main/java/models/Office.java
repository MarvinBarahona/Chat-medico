package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="consultorio")
public class Office implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="consultorio_id")
    private long id;
    
    @Column(name="nombre")
    private String name;
    
    @Column(name="ubicacion")
    private String address;
    
    @Column(name="descripcion")
    private String description;
    
    @Column(name="esquema")
    private String schema;
    
    @Transient
    private LoginUser admin;
    
    @Transient
    private LoginUser nurse;

    public Office() {
    }

    public Office(String name, String address, String schema) {
        this.name = name;
        this.address = address;
        this.schema = schema;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public LoginUser getAdmin() {
        return admin;
    }

    public void setAdmin(LoginUser admin) {
        this.admin = admin;
    }

    public LoginUser getNurse() {
        return nurse;
    }

    public void setNurse(LoginUser nurse) {
        this.nurse = nurse;
    }
}
