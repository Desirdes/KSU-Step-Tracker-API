package com.ksupwlt.stepcounttracker.controller;

import com.ksupwlt.stepcounttracker.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@CrossOrigin
@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    public static class EmailDetails {
        public String recipient;
        public String subject;
        public String content;
    }

    @PostMapping(
            value = "/sendEmail",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void sendEmail(@RequestBody EmailDetails emailDetails) {
        try {
            emailService.sendEmail(emailDetails.recipient, emailDetails.subject, emailDetails.content);
        } catch (UnsupportedEncodingException | MessagingException e) {
            System.out.println(e.getStackTrace());
        }
    }
}
