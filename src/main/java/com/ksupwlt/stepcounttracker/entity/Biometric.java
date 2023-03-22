package com.ksupwlt.stepcounttracker.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Biometric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float height;
    private Float weight;
    private Float waistCircumference;
    private Float neckCircumference;
    private Float bodyFatPercentage;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Biometric() {
    }

    public Biometric(Long id, Float height, Float weight, Float waistCircumference, Float neckCircumference, Float bodyFatPercentage, Person person) {
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

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getWaistCircumference() {
        return waistCircumference;
    }

    public void setWaistCircumference(Float waistCircumference) {
        this.waistCircumference = waistCircumference;
    }

    public Float getNeckCircumference() {
        return neckCircumference;
    }

    public void setNeckCircumference(Float neckCircumference) {
        this.neckCircumference = neckCircumference;
    }

    public Float getBodyFatPercentage() {
        return bodyFatPercentage;
    }

    public void setBodyFatPercentage(Float bodyFatPercentage) {
        this.bodyFatPercentage = bodyFatPercentage;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
