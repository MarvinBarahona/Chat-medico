package models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cuenta")
public class LoginUser implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cuenta_id")
    private long id;
    
    @Column(name="usuario")
    private String username;
    
    @Column(name="contra", nullable = true)
    private String password;
    
    @Column(name="nombre")
    private String name;
    
    @Column(name="activo")
    private boolean active;
    
    @ManyToOne
    @JoinColumn(name="rol_id")
    private Role role;
    
    @ManyToOne
    @JoinColumn(name="consultorio_id")
    private Office office;
    
    public LoginUser() { }

    public LoginUser(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }
    
    public LoginUser(User user, Office office){
        this.name = user.getName();
        this.office = office;
        this.username = user.getOfficeName() + "@" + user.getSchema();
        if(user.getRole().equals("medico")) this.role = new Role(4, "medico");
        if(user.getRole().equals("paciente")) this.role = new Role(3, "paciente");
        this.active = false;
    }

    public long getId() {
        return id;
    }    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }   

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}