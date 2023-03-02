package com.ksupwlt.stepcounttracker.service;

import com.ksupwlt.stepcounttracker.entity.Person;
import com.ksupwlt.stepcounttracker.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons(){
        return (List<Person>) personRepository.findAll();
    }

    public Person getPersonById(long id){
        return personRepository.findById(id).get();
    }
}
