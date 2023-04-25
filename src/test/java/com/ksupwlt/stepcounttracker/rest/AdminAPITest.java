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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AdminAPITest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void passwordResetAuthorization() {
        // Testing successful call
        User user = new User();
        user.setUsername("user");
        HttpEntity<User> request = new HttpEntity<>(user, getHttpHeaders());
        ResponseEntity<String> responseAdmin = restTemplate.postForEntity("http://localhost:" + port + "/api/v1/admin/password-reset", request, String.class);
        assertThat(responseAdmin.getStatusCode(), equalTo((HttpStatus.NO_CONTENT)));

        // Testing call when role is only USER
        HttpEntity<User> requestByUser = new HttpEntity<>(user, getUserHttpHeaders());
        ResponseEntity<String> responseUser = restTemplate.postForEntity("http://localhost:" + port + "/api/v1/admin/password-reset", requestByUser, String.class);
        assertThat(responseUser.getStatusCode(), equalTo((HttpStatus.FORBIDDEN)));
    }

    @Test
    public void getAllPersons(){
        // Testing successful call
        HttpEntity<String> request = new HttpEntity<>(getHttpHeaders());
        ResponseEntity<List> responseAdmin = restTemplate.exchange(URI.create("http://localhost:" + port + "/api/v1/admin/persons"), HttpMethod.GET, request, List.class);
        assertThat(responseAdmin.getStatusCode(), equalTo((HttpStatus.OK)));
        assertThat(responseAdmin.getBody().isEmpty(), equalTo(false));

        // Testing call when role is only USER
        HttpEntity<String> requestByUser = new HttpEntity<>(getUserHttpHeaders());
        ResponseEntity<List> responseUser = restTemplate.exchange(URI.create("http://localhost:" + port + "/api/v1/admin/persons"), HttpMethod.GET, requestByUser, List.class);
        assertThat(responseUser.getStatusCode(), equalTo((HttpStatus.FORBIDDEN)));
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

    private static HttpHeaders getUserHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);
        responseHeaders.setAccept(list);
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.setBasicAuth("user", "password");
        return responseHeaders;
    }
}
