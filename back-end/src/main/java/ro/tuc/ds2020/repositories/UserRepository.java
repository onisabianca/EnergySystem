package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import ro.tuc.ds2020.dtos.UsersDetailsDTO;
import ro.tuc.ds2020.entities.Users;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {

    List<Users> findByName(String name);

    @Transactional
    @Query(value = "SELECT u from Users u where u.username = :username")
    Users findByUsername(@Param("username") String username);

    @Transactional
    @Query(value = "SELECT u from Users u where u.username = :username and u.password=:password")
    Users findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Transactional
    @Modifying
    @Query(value = "Delete from Users u where u.id = :id")
    void deleteUser(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query(value = "Update Device d set id_user=null where d.id_user = :id")
    void updateDeviceForUser(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query(value = "Update Users u set u.name= :name, u.username= :username, u.role= :role where u.id = :id")
    void updateUser(@Param("id") UUID id, @Param("name") String name,@Param("username") String username, @Param("role") String role);
}
