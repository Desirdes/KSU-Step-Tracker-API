package com.ksupwlt.stepcounttracker.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Target {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer dailySteps;
    private BigDecimal weightLoss;
    private BigDecimal weightLossPercentage;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Target() {
    }

    public Target(Long id, Integer dailySteps, BigDecimal weightLoss, BigDecimal weightLossPercentage, Person person) {
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

    public Integer getDailySteps() {
        return dailySteps;
    }

    public void setDailySteps(Integer dailySteps) {
        this.dailySteps = dailySteps;
    }

    public BigDecimal getWeightLoss() {
        return weightLoss;
    }

    public void setWeightLoss(BigDecimal weightLoss) {
        this.weightLoss = weightLoss;
    }

    public BigDecimal getWeightLossPercentage() {
        return weightLossPercentage;
    }

    public void setWeightLossPercentage(BigDecimal weightLossPercentage) {
        this.weightLossPercentage = weightLossPercentage;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
