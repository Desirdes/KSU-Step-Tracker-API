package com.ksupwlt.stepcounttracker.controller;

import com.ksupwlt.stepcounttracker.entity.*;
import com.ksupwlt.stepcounttracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private AccessService accessService;
    private PersonService personService;
    private BiometricService biometricService;
    private TargetService targetService;
    private ActivityService activityService;

    public UserController(AccessService accessService, PersonService personService, BiometricService biometricService, TargetService targetService, ActivityService activityService) {
        this.accessService = accessService;
        this.personService = personService;
        this.biometricService = biometricService;
        this.targetService = targetService;
        this.activityService = activityService;
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPerson(Authentication authentication, @PathVariable(value = "id") Long personId) {
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Getting the person's details & Returning if found
        Person person = personService.getPersonById(personId);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping("/persons/{id}")
    public ResponseEntity<Person> patchPerson(Authentication authentication, @PathVariable(value = "id") Long personId, @RequestBody Person personDetails){
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // Patching Persons details
        Person patchedPerson = personService.patchPerson(personId, personDetails);
        if (patchedPerson!=null){
            return ResponseEntity.ok(patchedPerson);
        }
        return ResponseEntity.notFound().build();
    }

    // Biometrics calls
    // Create biometric
    @PostMapping("/persons/{id}/biometrics")
    public ResponseEntity<Void> addBiometricsToPerson(Authentication authentication, @PathVariable(value = "id") Long personId, @RequestBody Biometric biometricDetails){
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // Creating biometric for user
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
    public ResponseEntity<Void> deleteBiometric(Authentication authentication, @PathVariable(value = "personId") Long personId, @PathVariable(value = "biometricId") Long biometricId){
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Removing Biometric
        Biometric biometric = biometricService.deleteBiometricById(biometricId);
        if (biometric!=null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Update biometric
    @PutMapping("/persons/{personId}/biometrics/{biometricId}")
    public ResponseEntity<Biometric> updateBiometric(Authentication authentication, @PathVariable(value = "personId") Long personId, @PathVariable(value = "biometricId") Long biometricId, @RequestBody Biometric biometricDetails) {
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Updating biometric
        Biometric updatedBiometric = biometricService.updateBiometric(biometricId, biometricDetails);
        if (updatedBiometric!=null){
            return ResponseEntity.ok(updatedBiometric);
        }
        return ResponseEntity.notFound().build();
    }

    // Get biometric by ID
    @GetMapping("/persons/{personId}/biometrics/{biometricId}")
    public ResponseEntity<Biometric> getBiometricById(Authentication authentication, @PathVariable(value = "personId") Long personId, @PathVariable(value = "biometricId") Long biometricId){
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Getting biometric by ID
        Biometric biometric = biometricService.getBiometricById(biometricId);
        if (biometric!=null){
            return ResponseEntity.ok(biometric);
        }
        return ResponseEntity.notFound().build();
    }

    // Target Calls
    // Create target to person ID
    @PostMapping("/persons/{id}/targets")
    public ResponseEntity<Void> createTargetByPersonId(Authentication authentication, @PathVariable(value = "id") Long personId, @RequestBody Target targetDetails){
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // Creating new target
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
    public ResponseEntity<Target> getTargetById(Authentication authentication, @PathVariable(value = "personId") Long personId, @PathVariable(value = "targetId") Long targetId){
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // Getting target by ID
        Target target = targetService.getTargetById(targetId);
        if (target!=null){
            return ResponseEntity.ok(target);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete target
    @DeleteMapping("/persons/{personId}/targets/{targetId}")
    public ResponseEntity<Void> deleteTargetById(Authentication authentication, @PathVariable(value = "personId") Long personId, @PathVariable(value = "targetId") Long targetId){
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // Deleting target
        Target target = targetService.deleteTargetById(targetId);
        if (target!=null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Update target
    @PutMapping("/persons/{personId}/targets/{targetId}")
    public ResponseEntity<Target> updateTarget(Authentication authentication, @PathVariable(value = "personId") Long personId, @PathVariable(value = "targetId") Long targetId, @RequestBody Target targetDetails) {
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // Updating target
        Target updatedTarget = targetService.updateTarget(targetId, targetDetails);
        if (updatedTarget!=null){
            return ResponseEntity.ok(updatedTarget);
        }
        return ResponseEntity.notFound().build();
    }

    // Activity Calls
    // Get all activities for person
    @GetMapping("/persons/{id}/activities")
    public ResponseEntity<List<Activity>> getAllActivitiesForPerson(Authentication authentication, @PathVariable(value = "id") Long personId) {
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // Getting all activities
        List<Activity> activities = activityService.getAllActivitiesForPerson(personId);
        return ResponseEntity.ok().body(activities);
    }

    // Get activity by id
    @GetMapping("/persons/{personId}/activities/{activityId}")
    public ResponseEntity<Activity> getActivityById(Authentication authentication, @PathVariable(value = "personId") Long personId, @PathVariable(value = "activityId") Long activityId){
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // Getting activity by ID
        Activity activity = activityService.getActivityById(activityId);
        if (activity!=null){
            return ResponseEntity.ok(activity);
        }
        return ResponseEntity.notFound().build();
    }

    // Get latest activity
    @GetMapping("/persons/{id}/activities/latest")
    ResponseEntity<Activity> getLatestActivityForPerson(Authentication authentication, @PathVariable(value = "id") Long personId){
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // Getting latest activity for dashboard
        Activity activity = activityService.getLatestActivity(personId);
        if (activity!=null){
            return ResponseEntity.ok(activity);
        }
        return ResponseEntity.notFound().build();
    }

    // Add activity for person
    @PostMapping("/persons/{id}/activities")
    public ResponseEntity<Void> addActivityForPerson(Authentication authentication, @PathVariable(value = "id") Long personId, @RequestBody Activity activityDetails) {
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // Creating activity
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
    public ResponseEntity<Void> deleteActivity(Authentication authentication, @PathVariable(value = "personId") Long personId, @PathVariable(value = "activityId") Long activityId){
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // Removing the activity
        Activity activity = activityService.deleteActivityById(activityId);
        if (activity!=null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Update activity for person
    @PutMapping("/persons/{personId}/activities/{activityId}")
    public ResponseEntity<Activity> updateActivity(Authentication authentication, @PathVariable(value = "personId") Long personId, @PathVariable(value = "activityId") Long activityId, @RequestBody Activity activityDetails){
        // Validation of user requesting its own information
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        if(!accessService.userIsPerson(userSecurity, personId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // Updating activity
        Activity updateActivity = activityService.updateActivityById(activityId, activityDetails);
        if (updateActivity!=null){
            return ResponseEntity.ok(updateActivity);
        }
        return ResponseEntity.notFound().build();
    }

}
