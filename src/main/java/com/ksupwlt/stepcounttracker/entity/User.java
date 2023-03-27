package com.ksupwlt.stepcounttracker.entity;

import javax.persistence.*;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String role;
    private String password;
    private String groupID;

//    @OneToOne
//    private Person person;

    public User() {
    }

    public User(Long id, String username, String role, String password, String groupID) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.password = password;
        this.groupID = groupID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}