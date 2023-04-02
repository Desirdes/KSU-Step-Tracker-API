package com.ksupwlt.stepcounttracker.controller;

import com.ksupwlt.stepcounttracker.entity.Person;
import com.ksupwlt.stepcounttracker.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private PersonService personService;

    public UserController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Person> getPerson(@PathVariable long id){
        Person person = personService.getPersonById(id);
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }
}
