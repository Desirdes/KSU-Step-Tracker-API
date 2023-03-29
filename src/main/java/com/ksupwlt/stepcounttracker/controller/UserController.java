package com.ksupwlt.stepcounttracker.controller;

import com.ksupwlt.stepcounttracker.entity.Person;
import com.ksupwlt.stepcounttracker.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private PersonService personService;

    public UserController(PersonService personService) {
        this.personService = personService;
    }

//    @GetMapping("/persons/{id}")
//    @PreAuthorize("hasRole('USER')")
//    @PostAuthorize("returnObject.username == authentication.name")
//    public Person getPerson(@PathVariable long id){
//        Person person = personService.getPersonById(id);
//        return person;
//    }

    @GetMapping("{username}/persons")
    @PreAuthorize("hasRole('USER') and #username == authentication.name")
    @PostAuthorize("returnObject.username == authentication.name")
    public Person getPerson(@PathVariable String username){
        Person person = personService.getPersonByUsername(username);
        return person;
    }
}
