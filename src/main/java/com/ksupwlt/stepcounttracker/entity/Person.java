package com.ksupwlt.stepcounttracker.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String full_name;
    private String email;
    private String demographic;
    private String gender;
    private Integer age;
    private String username;

    private Date signupDate;

//    @OneToOne(fetch =FetchType.EAGER, mappedBy = "person", cascade = CascadeType.ALL)
//    private User user;

    @OneToMany(fetch =FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
    private List<Activity> activities = new ArrayList<>();
    @OneToMany(fetch =FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
    private List<Target> targets = new ArrayList<>();
    @OneToMany(fetch =FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
    private List<Biometric> biometrics = new ArrayList<>();

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDemographic() {
        return demographic;
    }

    public void setDemographic(String demographic) {
        this.demographic = demographic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    public List<Biometric> getBiometrics() {
        return biometrics;
    }

    public void setBiometrics(List<Biometric> biometrics) {
        this.biometrics = biometrics;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Date signupDate) {
        this.signupDate = signupDate;
    }
}
