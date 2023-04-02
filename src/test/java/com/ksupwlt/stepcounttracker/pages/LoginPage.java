package com.ksupwlt.stepcounttracker.pages;

import com.ksupwlt.stepcounttracker.annotation.PageScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@PageScope
public class LoginPage extends basePage{

    @FindBy(how = How.XPATH, using = "//input[@id='username']")
    public WebElement emailTextBox;

    @FindBy(how = How.XPATH, using = "//input[@id='password']")
    public WebElement passwordTextBox;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Login')]")
    public WebElement btnLogininLoginPage;

    @FindBy(how = How.XPATH, using = "//a[@ng-reflect-router-link='/signup']//u")
    public WebElement lnkSignup;

    public void LoginDetails(String userName, String password){
        emailTextBox.sendKeys(userName);
        passwordTextBox.sendKeys(password);
    }
}
