package com.ksupwlt.stepcounttracker.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Biometric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal height;
    private BigDecimal weight;
    private BigDecimal waistCircumference;
    private BigDecimal neckCircumference;
    private BigDecimal bodyFatPercentage;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Biometric() {
    }

    public Biometric(Long id, BigDecimal height, BigDecimal weight, BigDecimal waistCircumference, BigDecimal neckCircumference, BigDecimal bodyFatPercentage, Person person) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.waistCircumference = waistCircumference;
        this.neckCircumference = neckCircumference;
        this.bodyFatPercentage = bodyFatPercentage;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getWaistCircumference() {
        return waistCircumference;
    }

    public void setWaistCircumference(BigDecimal waistCircumference) {
        this.waistCircumference = waistCircumference;
    }

    public BigDecimal getNeckCircumference() {
        return neckCircumference;
    }

    public void setNeckCircumference(BigDecimal neckCircumference) {
        this.neckCircumference = neckCircumference;
    }

    public BigDecimal getBodyFatPercentage() {
        return bodyFatPercentage;
    }

    public void setBodyFatPercentage(BigDecimal bodyFatPercentage) {
        this.bodyFatPercentage = bodyFatPercentage;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
