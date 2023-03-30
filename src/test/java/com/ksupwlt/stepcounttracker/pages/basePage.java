package com.ksupwlt.stepcounttracker.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class basePage {

    @Autowired
    public WebDriver webDriver;

    @PostConstruct
    public void InitPage(){
       PageFactory.initElements(webDriver, this);
    }
}
