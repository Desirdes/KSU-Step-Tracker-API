package com.ksupwlt.stepcounttracker.controller.prototype;

import com.ksupwlt.stepcounttracker.service.AccessService;
import com.ksupwlt.stepcounttracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/access")
public class AuthenticationController {

    @Autowired
    private AccessService accessService;

    @PostMapping("/signup")
    public ResponseEntity signupUser(@RequestBody User user){
        if(!accessService.isUsernameAvailable(user.getUsername())){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken.");
        }
        User newUser = accessService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User user){
        if(accessService.validateUser(user.getUsername(), user.getPassword())){
            return ResponseEntity.status(HttpStatus.OK).body("Login Authorized");
        } else {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username/Password Invalid.");
        }
    }
}
