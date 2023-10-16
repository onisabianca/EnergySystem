package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.UsersDTO;
import ro.tuc.ds2020.dtos.UsersDetailsDTO;
import ro.tuc.ds2020.dtos.builders.UserBuilder;
import ro.tuc.ds2020.entities.Users;
import ro.tuc.ds2020.repositories.UserRepository;
import ro.tuc.ds2020.security.Credentials;
import ro.tuc.ds2020.security.CryptPassword;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UsersDTO> findUsers()
    {
        List<Users> usersList = userRepository.findAll();
        return usersList.stream().map(UserBuilder::toUsersDTO).collect(Collectors.toList());
    }

    public UsersDetailsDTO findUserById(UUID id)
    {
        Optional<Users> prosumerOptional = userRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(Users.class.getSimpleName() + " with id: " + id);
        }
        return UserBuilder.toUsersDetailsDTO(prosumerOptional.get());
    }

    public UUID insert(UsersDetailsDTO userDTO) {
        CryptPassword cryptPassword= new CryptPassword();
        String password= cryptPassword.encrypt(userDTO.getPassword());
        userDTO.setPassword(password);

        Users user = UserBuilder.toEntity(userDTO);
        user = userRepository.save(user);
        LOGGER.debug("User with id {} was inserted in db", user.getId());
        return user.getId();
    }

    public void deleteUser(UUID id)
    {
        Optional<Users> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(Users.class.getSimpleName() + " with id: " + id);
        }
        else {
            userRepository.updateDeviceForUser(id);
            userRepository.deleteUser(id);
        }
    }

    public void updateUser(UUID id, UsersDetailsDTO userNou)
    {
        Optional<Users> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(Users.class.getSimpleName() + " with id: " + id);
        }
        else {
            userRepository.updateUser(id,userNou.getName(),userNou.getUsername(),userNou.getRole());
        }
    }

    public String findRole(String username)
    {
        Users user = userRepository.findByUsername(username);
        return user.getRole();
    }

    public Users login(String username, String password) {
        CryptPassword cryptPassword= new CryptPassword();
        password= cryptPassword.encrypt(password);
        Users user = userRepository.findByUsernameAndPassword(username, password);
        if(user!=null) {
            System.out.println("Logat");
            return user;
        }
        else {
            System.out.println("Nelogat");
            return null;
        }
    }
}
