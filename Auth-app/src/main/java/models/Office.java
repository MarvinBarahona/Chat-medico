package models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    
    @Column(name="esquema")
    private String schema;

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
}
