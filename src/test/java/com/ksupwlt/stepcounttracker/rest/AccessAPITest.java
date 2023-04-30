package com.ksupwlt.stepcounttracker.rest;

import com.ksupwlt.stepcounttracker.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccessAPITest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void credentialsCheck(){
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");

        ResponseEntity<User> response = restTemplate.postForEntity("http://localhost:" + port + "/access/login", user, User.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().getUsername(), equalTo(user.getUsername()));
        assertThat(response.getBody().getPassword(), equalTo(""));
        System.out.println(response.getBody().getUsername());
    }

    @Test
    public void passwordResetNotAuthorized(){
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");

        ResponseEntity<User> response = restTemplate.postForEntity("http://localhost:" + port + "/access/password-reset", user, User.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
    }

    @Test
    public void passwordResetAuthorized(){
        User user = new User();
        user.setUsername("user");

        HttpEntity<User> request = new HttpEntity<>(user, getHttpHeaders());
        ResponseEntity<String> responseAdmin = restTemplate.postForEntity("http://localhost:" + port + "/api/v1/admin/password-reset", request, String.class);
        assertThat(responseAdmin.getStatusCode(), equalTo((HttpStatus.NO_CONTENT)));

        user.setPassword("password2");
        ResponseEntity<User> response = restTemplate.postForEntity("http://localhost:" + port + "/access/password-reset", user, User.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));

    }

    @Test
    public void signupNewUser(){
        String json = "{\"username\": \"john007\"," +
                "\"password\": \"KSUTracker\"," +
                "\"email\": \"john007@ksu.com\"," +
                " \"full_name\": \"John Doe\" }";
        HttpEntity<String> request = new HttpEntity<>(json,getHttpHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/access/signup", request, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        ResponseEntity<String> responseIssue = restTemplate.postForEntity("http://localhost:" + port + "/access/signup", request, String.class);
        assertThat(responseIssue.getStatusCode(), equalTo(HttpStatus.CONFLICT));
    }



    private static HttpHeaders getHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);
        responseHeaders.setAccept(list);
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.setBasicAuth("admin", "password");
        return responseHeaders;
    }



}
