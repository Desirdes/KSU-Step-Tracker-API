package com.ksupwlt.stepcounttracker.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String roles;
    private String password;
    private String groupID;

    private Long personID;

//    @OneToOne
//    private Person person;

    public User() {
    }

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(String username, String roles, String password, String groupID) {
        this.username = username;
        this.roles = roles;
        this.password = password;
        this.groupID = groupID;
    }

    public User(String username, String password, String roles, String groupID, Long personID) {
        this.username = username;
        this.roles = roles;
        this.password = password;
        this.groupID = groupID;
        this.personID = personID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

    public Long getPersonID() {
        return personID;
    }

    public void setPersonId(Long personID) {
        this.personID = personID;
    }
}
