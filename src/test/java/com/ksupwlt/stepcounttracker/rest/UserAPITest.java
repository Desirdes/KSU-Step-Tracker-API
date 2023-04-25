package com.ksupwlt.stepcounttracker.rest;

import com.ksupwlt.stepcounttracker.entity.Biometric;
import com.ksupwlt.stepcounttracker.entity.Person;
import com.ksupwlt.stepcounttracker.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserAPITest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getPersonById(){
        // Testing successful call
        String personId = "1";
        HttpEntity<String> request = new HttpEntity<>(getUserHttpHeaders());
        ResponseEntity<String> responseGood = restTemplate.exchange(URI.create("http://localhost:" + port + "/api/v1/user/persons/" + personId), HttpMethod.GET, request, String.class);
        assertThat(responseGood.getStatusCode(), equalTo((HttpStatus.OK)));
        assertThat(responseGood.getBody(), containsString("anegrona"));

        // Testing call when role is only USER
        String otherPersonId = "2";
        HttpEntity<String> requestByUser = new HttpEntity<>(getUserHttpHeaders());
        ResponseEntity<String> responseBad = restTemplate.exchange(URI.create("http://localhost:" + port + "/api/v1/user/persons/" + otherPersonId), HttpMethod.GET, requestByUser, String.class);
        assertThat(responseBad.getStatusCode(), equalTo((HttpStatus.FORBIDDEN)));
    }

    @Test
    public void createBiometric(){
        // Testing successful call
        String personId = "1";
        Biometric newBiometric = new Biometric();
        newBiometric.setHeight((float) 70);
        newBiometric.setWeight((float) 80);
        newBiometric.setWaistCircumference((float) 32);
        newBiometric.setNeckCircumference((float) 20);
        newBiometric.setDateUpdated(new Date());
        HttpEntity<Biometric> request = new HttpEntity<>(newBiometric, getUserHttpHeaders());
        ResponseEntity<String> responseGood = restTemplate.postForEntity("http://localhost:" + port + "/api/v1/user/persons/" + personId + "/biometrics", request, String.class);
        assertThat(responseGood.getStatusCode(), equalTo((HttpStatus.CREATED)));
        assertThat(responseGood.getHeaders().getLocation(), notNullValue());
        //assertThat(responseGood.getBody(), containsString("anegrona"));

        // Testing call when role is only USER
        String otherPersonId = "2";
        ResponseEntity<String> responseBad = restTemplate.postForEntity("http://localhost:" + port + "/api/v1/user/persons/" + otherPersonId + "/biometrics", request, String.class);
        assertThat(responseBad.getStatusCode(), equalTo((HttpStatus.FORBIDDEN)));
    }



    private static HttpHeaders getUserHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);
        responseHeaders.setAccept(list);
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.setBasicAuth("anegrona", "password");
        return responseHeaders;
    }
}
