package com.ksupwlt.stepcounttracker.service;

import com.ksupwlt.stepcounttracker.entity.Activity;
import com.ksupwlt.stepcounttracker.entity.Biometric;
import com.ksupwlt.stepcounttracker.entity.Person;
import com.ksupwlt.stepcounttracker.entity.Target;
import com.ksupwlt.stepcounttracker.repository.ActivityRepository;
import com.ksupwlt.stepcounttracker.repository.BiometricRepository;
import com.ksupwlt.stepcounttracker.repository.PersonRepository;
import com.ksupwlt.stepcounttracker.repository.TargetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private BiometricRepository biometricRepository;
    private TargetRepository targetRepository;
    private ActivityRepository activityRepository;

    public PersonService(PersonRepository personRepository, BiometricRepository biometricRepository, TargetRepository targetRepository, ActivityRepository activityRepository) {
        this.personRepository = personRepository;
        this.biometricRepository = biometricRepository;
        this.targetRepository = targetRepository;
        this.activityRepository = activityRepository;
    }

    public List<Person> getAllPersons(){
        return (List<Person>) personRepository.findAll();
    }

    public Person getPersonById(long id){
        return personRepository.findById(id).get();
    }

    public List<Person> getAllPersonsDetails() {
        List<Person> persons = personRepository.findAll();
        for (Person person : persons) {
            List<Biometric> biometrics = biometricRepository.findByPerson(person);
            person.setBiometrics(biometrics);

            List<Target> targets = targetRepository.findByPerson(person);
            person.setTargets(targets);

            List<Activity> activities = activityRepository.findByPerson(person);
            person.setActivities(activities);
        }
        return persons;
    }
}
