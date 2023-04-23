package com.ksupwlt.stepcounttracker.controller;

import com.ksupwlt.stepcounttracker.entity.Activity;
import com.ksupwlt.stepcounttracker.entity.Person;
import com.ksupwlt.stepcounttracker.entity.User;
import com.ksupwlt.stepcounttracker.service.PersonService;
import com.ksupwlt.stepcounttracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private PersonService personService;
    private UserService userService;

    public AdminController(PersonService personService, UserService userService) {
        this.personService = personService;
        this.userService = userService;
    }

    @GetMapping("/persons")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> list = personService.getAllPersons();
        return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
    }

    @PostMapping("/password-reset")
    public ResponseEntity flagUserPassword(@RequestBody User user){
        // Turn user reset password flag to allow them to reset their password
        if(userService.flagUserResetPassword(user.getUsername())){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
