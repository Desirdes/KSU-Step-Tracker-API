package com.ksupwlt.stepcounttracker.controller.prototype;

import com.ksupwlt.stepcounttracker.entity.AccessService;
import com.ksupwlt.stepcounttracker.entity.User;
import com.ksupwlt.stepcounttracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
        newUser.setPassword("");
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
