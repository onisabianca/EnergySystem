package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Type(type="uuid-binary")
    private UUID id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="role", nullable=false)
    private String role;

    @Column(name="username", nullable=false)
    private String username;
    @Column(name="password", nullable=false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Device> deviceList;


    public Users(){
    }

    public Users(String name, String role,String username, String password)
    {
        this.name=name;
        this.role=role;
        this.username=username;
        this.password=password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
