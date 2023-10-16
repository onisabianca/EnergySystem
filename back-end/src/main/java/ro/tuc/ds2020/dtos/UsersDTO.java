package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

public class UsersDTO extends RepresentationModel<UsersDTO> {

    private UUID id;
    private String name;
    private String role;
    private String username;
    private String password;

    public UsersDTO(){
    }

    public UsersDTO(UUID id, String name, String role,String username, String password)
    {
        this.id=id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersDTO usersDTO = (UsersDTO) o;
        return name == usersDTO.name &&
                Objects.equals(password, usersDTO.password)
                && Objects.equals(role, usersDTO.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, role);
    }
}
