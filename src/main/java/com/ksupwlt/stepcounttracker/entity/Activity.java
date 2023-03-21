package com.ksupwlt.stepcounttracker.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private Integer steps;
    @ManyToOne
    private Person person;

    public Activity() {
    }

    public Activity(Long id, Date date, Integer steps, Person person) {
        this.id = id;
        this.date = date;
        this.steps = steps;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
