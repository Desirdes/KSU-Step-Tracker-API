package com.ksupwlt.stepcounttracker.controller;

import com.ksupwlt.stepcounttracker.entity.Person;
import com.ksupwlt.stepcounttracker.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private PersonService personService;

    public AdminController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> list = personService.getAllPersons();
        return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
    }
}
