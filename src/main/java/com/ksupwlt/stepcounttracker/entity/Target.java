package com.ksupwlt.stepcounttracker.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Target {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer dailySteps;
    private Float weightLoss;
    private Float weightLossPercentage;

    private Date dateUpdated;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Person person;

    public Target() {
    }

    public Target(Integer dailySteps, Float weightLoss, Float weightLossPercentage, Person person, Date dateUpdated) {
        this.dailySteps = dailySteps;
        this.weightLoss = weightLoss;
        this.weightLossPercentage = weightLossPercentage;
        this.person = person;
        this.dateUpdated = dateUpdated;
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

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
