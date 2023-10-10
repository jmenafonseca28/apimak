package mye.fisio.mak.apimak.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String cedula;
    private String role;
    private String name;
    private String last_name;
    private String enterprise_name;
    private String email;
    private String password;
    private String phone;

    public Client() {
    }

    public Client(int id, String cedula, String role, String name, String last_name, String enterprise_name, String email, String password, String phone) {
        this.id = id;
        this.cedula = cedula;
        this.role = role;
        this.name = name;
        this.last_name = last_name;
        this.enterprise_name = enterprise_name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public Client(String cedula, String role, String name, String last_name, String enterprise_name, String email, String password, String phone) {
        this.cedula = cedula;
        this.role = role;
        this.name = name;
        this.last_name = last_name;
        this.enterprise_name = enterprise_name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", cedula=" + cedula + ", role=" + role + ", name=" + name + ", last_name="
                + last_name + ", enterprise_name=" + enterprise_name + ", email=" + email + ", password=" + password
                + ", phone=" + phone + "]";
    }
 
}
