package com.ksupwlt.stepcounttracker.pages;

import com.ksupwlt.stepcounttracker.annotation.PageScope;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@PageScope
public class SignUpPage extends basePage{
    @Autowired
    private WebDriver webDriver;

    @FindBy(how = How.XPATH, using = "//input[@id='name']")
    public WebElement nameSTextBox;

    @FindBy(how = How.XPATH, using = "//input[@id='email']")
    public WebElement emailSTextBox;

    @FindBy(how = How.XPATH, using = "//input[@id='password']")
    public WebElement pwdSTextBox;

    @FindBy(how = How.XPATH, using = "//input[@id='confirmPassword']")
    public WebElement repeatsPwdTextBox;

    @FindBy(how = How.XPATH, using = "//input[@id='username']")
    public WebElement userNameTextBox;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Register')]")
    public WebElement registerButton;

    @FindBy(how = How.XPATH, using = "//a[@ng-reflect-router-link='/login']//u")
    public WebElement lnkLogin;

    public void signUpDetails(){
        nameSTextBox.sendKeys("Santhiya");
        emailSTextBox.sendKeys("ssubram6@students.kennesaw.edu");
        userNameTextBox.sendKeys("Santhiya");
        pwdSTextBox.sendKeys("Santhiya@123");
        repeatsPwdTextBox.sendKeys("Santhiya@123");
    }

    public void scrollDown(){
        Actions scroll = new Actions(webDriver);
        scroll.sendKeys(Keys.PAGE_DOWN).build().perform();
    }
}
