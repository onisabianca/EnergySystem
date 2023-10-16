package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.entities.Users;
import ro.tuc.ds2020.security.Credentials;
import ro.tuc.ds2020.services.UserService;

import java.util.UUID;

@RestController
@CrossOrigin()
@RequestMapping(value = "/login")
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService)
    {
        this.userService=userService;
    }

    @PostMapping()
    public Users login(@RequestBody Credentials credentials) {
        Users user = userService.login(credentials.username, credentials.password);
        if(user != null)
            return user;
        else
            return null;
    }


}
