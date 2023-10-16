package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.UsersDTO;
import ro.tuc.ds2020.dtos.UsersDetailsDTO;
import ro.tuc.ds2020.entities.Users;
import ro.tuc.ds2020.services.UserService;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin()
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

     @Autowired
    public UserController(UserService userService)
     {
         this.userService=userService;
     }

    @GetMapping()
    public ResponseEntity<List<UsersDTO>> getUsers() {
        List<UsersDTO> dtos = userService.findUsers();
        for (UsersDTO dto : dtos) {
            Link userLink = linkTo(methodOn(UserController.class)
                    .getUser(dto.getId())).withRel("userDetails");
            dto.add(userLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<UUID> insertUser(@Valid @RequestBody UsersDetailsDTO userDTO) {
        UUID userID = userService.insert(userDTO);
        return new ResponseEntity<>(userID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public UsersDetailsDTO getUser(@PathVariable("id") UUID userId) {
        UsersDetailsDTO dto = userService.findUserById(userId);
        return userService.findUserById(userId);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<UUID> deleteUser(@PathVariable("id") UUID userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("update/{id}")
    public UsersDetailsDTO updateUser(@PathVariable("id") UUID userId, @Valid @RequestBody UsersDetailsDTO userDTO) {
        userService.updateUser(userId,userDTO);
        return userService.findUserById(userId);
    }
}
