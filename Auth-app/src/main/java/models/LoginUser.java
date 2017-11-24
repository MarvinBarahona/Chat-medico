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
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="cuenta_id")
    private long id;
    
    @Column(name="usuario")
    private String username;
    
    @Column(name="contra")
    private String password;
    
    @Column(name="nombre")
    private String name;
    
    @ManyToOne
    @JoinColumn(name="rol_id")
    private Role role;
    
    @ManyToOne
    @JoinColumn(name="consultorio_id")
    private Office office;
    
    public LoginUser() { }

    public LoginUser(String username, String name, String password, String role) {
        this.username = username;
        this.name = name;
        this.password = password;
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
    
    
}