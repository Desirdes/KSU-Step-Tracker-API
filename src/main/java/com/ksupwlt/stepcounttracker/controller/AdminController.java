package com.ksupwlt.stepcounttracker.controller;

import com.ksupwlt.stepcounttracker.entity.Activity;
import com.ksupwlt.stepcounttracker.entity.Person;
import com.ksupwlt.stepcounttracker.entity.User;
import com.ksupwlt.stepcounttracker.service.EmailService;
import com.ksupwlt.stepcounttracker.service.PersonService;
import com.ksupwlt.stepcounttracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private PersonService personService;
    private UserService userService;

    private EmailService emailService;

    public AdminController(PersonService personService, UserService userService, EmailService emailService) {
        this.personService = personService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/persons")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> list = personService.getAllPersons();
        return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
    }

    private static class passwordResetFlagData{
        private String username;
        private String email;

        public passwordResetFlagData(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }
        public String getEmail() {
            return email;
        }
    }
    @PostMapping("/password-reset")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity flagUserPassword(@RequestBody passwordResetFlagData data) throws MessagingException, UnsupportedEncodingException {
        // Turn user reset password flag to allow them to reset their password
        var tempPassword = userService.flagUserResetPassword(data.getUsername());
        if(tempPassword != null){
            var subject = "Your Password Has Been Reset";
            var content = "Your password has been reset for the KSU Weightloss Step Tracker. You have been given a temporary password and will be prompted to change your password once you have logged in. Your temporary password is: " + tempPassword;

            try {
                emailService.sendEmail(data.getEmail(), subject, content);
            } catch (UnsupportedEncodingException | MessagingException e) {
                System.out.println(e.getStackTrace());
            }
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
