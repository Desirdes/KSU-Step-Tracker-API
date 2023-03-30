package com.ksupwlt.stepcounttracker.pages;

import com.ksupwlt.stepcounttracker.annotation.PageScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@PageScope
public class LandingPage extends basePage{

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Get Started')]")
    public WebElement btnGetStarted;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),' Login ')]")
    public WebElement btnNavBarLogin;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),' Sign up for free ')]")
    public WebElement btnsignUpForFree;

    @FindBy(how = How.XPATH, using = "//a[@class='navbar-brand']")
    public WebElement homepgLinkInNavBar;

    public void ClickGetStarted(){

    }
}
