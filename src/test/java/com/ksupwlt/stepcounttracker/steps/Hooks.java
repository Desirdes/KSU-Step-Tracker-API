package com.ksupwlt.stepcounttracker.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class Hooks {
    @Lazy
    @Autowired
    private  WebDriver webDriver;

    @Value("${app.url}")
    private String appUrl;

    @Before
    public void InitializeTest(Scenario scenario){
        webDriver.get(appUrl);
        webDriver.manage().window().maximize();

    }

    @After
    public void TearDownTest(Scenario scenario){
        if(scenario.isFailed()){
            System.out.println(scenario.getName());
        }
//        webDriver.quit();
    }
}
