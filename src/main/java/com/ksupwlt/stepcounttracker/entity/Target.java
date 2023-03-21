package com.ksupwlt.stepcounttracker.entity;

import javax.persistence.*;

@Entity
public class Target {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String dailySteps;
    private Float weightLoss;
    private Float weightLossPercentage;

    @ManyToOne
    private Person person;

    public Target() {
    }

    public Target(Long id, String dailySteps, Float weightLoss, Float weightLossPercentage, Person person) {
        this.id = id;
        this.dailySteps = dailySteps;
        this.weightLoss = weightLoss;
        this.weightLossPercentage = weightLossPercentage;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDailySteps() {
        return dailySteps;
    }

    public void setDailySteps(String dailySteps) {
        this.dailySteps = dailySteps;
    }

    public Float getWeightLoss() {
        return weightLoss;
    }

    public void setWeightLoss(Float weightLoss) {
        this.weightLoss = weightLoss;
    }

    public Float getWeightLossPercentage() {
        return weightLossPercentage;
    }

    public void setWeightLossPercentage(Float weightLossPercentage) {
        this.weightLossPercentage = weightLossPercentage;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
