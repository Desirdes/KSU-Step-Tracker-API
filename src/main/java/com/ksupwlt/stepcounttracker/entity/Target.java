package com.ksupwlt.stepcounttracker.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Target {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer dailySteps;
    private Float weightLoss;
    private Float weightLossPercentage;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Target() {
    }

    public Target(Integer dailySteps, Float weightLoss, Float weightLossPercentage, Person person) {
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

    public Integer getDailySteps() {
        return dailySteps;
    }

    public void setDailySteps(Integer dailySteps) {
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
