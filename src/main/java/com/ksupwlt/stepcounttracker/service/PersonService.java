package com.ksupwlt.stepcounttracker.service;

import com.ksupwlt.stepcounttracker.entity.Activity;
import com.ksupwlt.stepcounttracker.entity.Biometric;
import com.ksupwlt.stepcounttracker.entity.Person;
import com.ksupwlt.stepcounttracker.entity.Target;
import com.ksupwlt.stepcounttracker.repository.ActivityRepository;
import com.ksupwlt.stepcounttracker.repository.BiometricRepository;
import com.ksupwlt.stepcounttracker.repository.PersonRepository;
import com.ksupwlt.stepcounttracker.repository.TargetRepository;
import org.springframework.http.ResponseEntity;
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

    // New Code
    public Person getPersonById(Long personId) {
        return personRepository.findById(personId).orElse(null);
    }

    public Person updatePerson(Long personId, Person personDetails) {
        Person person = personRepository.findById(personId).orElse(null);
        if(person == null) return null;
        person.setFull_name(personDetails.getFull_name());
        person.setEmail(personDetails.getEmail());
        person.setDemographic(personDetails.getDemographic());
        person.setGender(personDetails.getGender());
        person.setAge(personDetails.getAge());
        return personRepository.save(person);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person deletePerson(Long personId) {
        Person person = personRepository.findById(personId).orElse(null);
        if(person == null) return null;
        personRepository.delete(person);
        return person;
    }

    // TODO: To be reviewed
    public List<Person> getAllPersonsDetails() {
        List<Person> persons = personRepository.findAll();
        for (Person person : persons) {
            List<Biometric> biometrics = biometricRepository.findByPersonId(person.getId());
            person.setBiometrics(biometrics);

            List<Target> targets = targetRepository.findByPersonId(person.getId());
            person.setTargets(targets);

            List<Activity> activities = activityRepository.findByPersonId(person.getId());
            person.setActivities(activities);
        }
        return persons;
    }

    public Person patchPerson(Long personId, Person personDetails) {
        Person person = personRepository.findById(personId).orElse(null);
        if(person == null) return null;

        if(personDetails.getFull_name() != null){
            person.setFull_name(personDetails.getFull_name());
        }
        if(personDetails.getEmail() != null){
            person.setEmail(personDetails.getEmail());
        }
        if(personDetails.getDemographic() != null){
            person.setDemographic(personDetails.getDemographic());
        }
        if(personDetails.getGender() != null){
            person.setGender(personDetails.getGender());
        }
        if(personDetails.getAge() != null){
            person.setAge(personDetails.getAge());
        }
        return personRepository.save(person);
    }
}
