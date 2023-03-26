package com.ksupwlt.stepcounttracker.controller;

import com.ksupwlt.stepcounttracker.entity.Activity;
import com.ksupwlt.stepcounttracker.entity.Biometric;
import com.ksupwlt.stepcounttracker.entity.Person;
import com.ksupwlt.stepcounttracker.entity.Target;
import com.ksupwlt.stepcounttracker.service.ActivityService;
import com.ksupwlt.stepcounttracker.service.BiometricService;
import com.ksupwlt.stepcounttracker.service.PersonService;
import com.ksupwlt.stepcounttracker.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private BiometricService biometricService;

    @Autowired
    private TargetService targetService;

    @Autowired
    private ActivityService activityService;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons(){
        List<Person> list = personService.getAllPersons();
        return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
    }

    // Get person by ID
    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long personId) {
        Person person = personService.getPersonById(personId);
        return ResponseEntity.ok(person);
    }

    // Update person
    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long personId, @RequestBody Person personDetails) {
        Person updatedPerson = personService.updatePerson(personId, personDetails);
        if (updatedPerson!=null){
            return ResponseEntity.ok(updatedPerson);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete person
    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable(value= "id") Long personId){
        Person person = personService.deletePerson(personId);
        if (person!=null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Create Person
    @PostMapping("/persons")
    public ResponseEntity<Void> createPerson(@RequestBody Person personDetails){
        Person newPerson = personService.createPerson(personDetails);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newPerson.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    // Biometrics calls
    // Create biometric
    @PostMapping("/persons/{id}/biometrics")
    public ResponseEntity<Void> addBiometricsToPerson(@PathVariable(value = "id") Long personId, @RequestBody Biometric biometricDetails){
        Biometric newBiometric = biometricService.createBiometric(personId, biometricDetails);
        if (newBiometric!=null){
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(newBiometric.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.notFound().build();
    }

    // Delete biometric
    @DeleteMapping("/persons/{personId}/biometrics/{biometricId}")
    public ResponseEntity<Void> deleteBiometric(@PathVariable(value = "personId") Long personId, @PathVariable(value = "biometricId") Long biometricId){
        Biometric biometric = biometricService.deleteBiometricById(biometricId);
        if (biometric!=null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    // Update biometric
    @PutMapping("/persons/{personId}/biometrics/{biometricId}")
    public ResponseEntity<Biometric> updateBiometric(@PathVariable(value = "personId") Long personId, @PathVariable(value = "biometricId") Long biometricId, @RequestBody Biometric biometricDetails) {
        Biometric updatedBiometric = biometricService.updateBiometric(biometricId, biometricDetails);
        if (updatedBiometric!=null){
            return ResponseEntity.ok(updatedBiometric);
        }
        return ResponseEntity.notFound().build();
    }

    // Get biometrics by person ID
    @GetMapping("/persons/{id}/biometrics")
    public ResponseEntity<List<Biometric>> getBiometricByPersonId(@PathVariable(value = "id") Long personId) {
        List<Biometric> biometrics = biometricService.getBiometricByPersonId(personId);
        return ResponseEntity.ok().body(biometrics);
    }

    // Get biometric by ID
    @GetMapping("/persons/{personId}/biometrics/{biometricId}")
    public ResponseEntity<Biometric> getBiometricById(@PathVariable(value = "personId") Long personId, @PathVariable(value = "biometricId") Long biometricId){
        Biometric biometric = biometricService.getBiometricById(biometricId);
        if (biometric!=null){
            return ResponseEntity.ok(biometric);
        }
        return ResponseEntity.notFound().build();
    }

    // Target Calls
    // Create target to person ID
    @PostMapping("/persons/{id}/targets")
    public ResponseEntity<Void> createTargetByPersonId(@PathVariable(value = "id") Long personId, @RequestBody Target targetDetails){
        Target newTarget = targetService.createTarget(personId, targetDetails);
        if (newTarget!=null){
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(newTarget.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.notFound().build();
    }

    // Get target by id
    @GetMapping("/persons/{personId}/targets/{targetId}")
    public ResponseEntity<Target> getTargetById(@PathVariable(value = "personId") Long personId, @PathVariable(value = "targetId") Long targetId){
        Target target = targetService.getTargetById(targetId);
        if (target!=null){
            return ResponseEntity.ok(target);
        }
        return ResponseEntity.notFound().build();
    }

    // Get target by person ID
    @GetMapping("/persons/{id}/targets")
    public ResponseEntity<List<Target>> getTargetByPersonId(@PathVariable(value = "id") Long personId) {
        List<Target> targets = targetService.getTargetByPersonId(personId);
        return ResponseEntity.ok().body(targets);
    }


    // Delete target
    @DeleteMapping("/persons/{personId}/targets/{targetId}")
    public ResponseEntity<Void> deleteTargetById(@PathVariable(value = "personId") Long personId, @PathVariable(value = "targetId") Long targetId){
        Target target = targetService.deleteTargetById(targetId);
        if (target!=null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Update target
    @PutMapping("/persons/{personId}/targets/{targetId}")
    public ResponseEntity<Target> updateTarget(@PathVariable(value = "personId") Long personId, @PathVariable(value = "targetId") Long targetId, @RequestBody Target targetDetails) {
        Target updatedTarget = targetService.updateTarget(targetId, targetDetails);
        if (updatedTarget!=null){
            return ResponseEntity.ok(updatedTarget);
        }
        return ResponseEntity.notFound().build();
    }

    // Activity Calls
    // Get all activities for person
    @GetMapping("/persons/{id}/activities")
    public ResponseEntity<List<Activity>> getAllActivitiesForPerson(@PathVariable(value = "id") Long personId) {
        List<Activity> activities = activityService.getAllActivitiesForPerson(personId);
        return ResponseEntity.ok().body(activities);
    }

    // Get activity by id
    @GetMapping("/persons/{personId}/activities/{activityId}")
    public ResponseEntity<Activity> getActivityById(@PathVariable(value = "personId") Long personId, @PathVariable(value = "activityId") Long activityId){
        Activity activity = activityService.getActivityById(activityId);
        if (activity!=null){
            return ResponseEntity.ok(activity);
        }
        return ResponseEntity.notFound().build();
    }

    // Update activity for person
    @PutMapping("/persons/{personId}/activities/{activityId}")
    public ResponseEntity<Activity> updateActivity(@PathVariable(value = "personId") Long personId, @PathVariable(value = "activityId") Long activityId, @RequestBody Activity activityDetails){
        Activity updateActivity = activityService.updateActivityById(activityId, activityDetails);
        if (updateActivity!=null){
            return ResponseEntity.ok(updateActivity);
        }
        return ResponseEntity.notFound().build();
    }

    // Add activity for person
    @PostMapping("/persons/{id}/activities")
    public ResponseEntity<Void> addActivityForPerson(@PathVariable(value = "id") Long personId, @RequestBody Activity activityDetails) {
        Activity newActivity = activityService.addActivityForPerson(personId, activityDetails);
        if (newActivity!=null){
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(newActivity.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.notFound().build();
    }

    // Delete activity for person
    @DeleteMapping("/persons/{personId}/activities/{activityId}")
    public ResponseEntity<Void> deleteActivity(@PathVariable(value = "personId") Long personId, @PathVariable(value = "activityId") Long activityId){
        Activity activity = activityService.deleteActivityById(activityId);
        if (activity!=null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
