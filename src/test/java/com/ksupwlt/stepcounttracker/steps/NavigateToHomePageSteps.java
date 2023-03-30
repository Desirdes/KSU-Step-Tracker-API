package com.ksupwlt.stepcounttracker.steps;

import com.ksupwlt.stepcounttracker.pages.LandingPage;
import com.ksupwlt.stepcounttracker.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;


public class NavigateToHomePageSteps {
    @Autowired
    private LandingPage landingPage;

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private WebDriver webDriver;

    @And("Click on KSU Weightloss Tracker Link in Navigation Bar")
    public void clickOnKSUWeightlossTrackerLinkInNavigationBar() {
        landingPage.homepgLinkInNavBar.click();
    }

    @Then("User should be navigated to Landing Page")
    public void userShouldBeNavigatedToLandingPage() {
        Assert.assertTrue(landingPage.btnGetStarted.isDisplayed());
    }

    @And("Navigate to UserProfile Page")
    public void navigateToUserProfilePage() {
        landingPage.btnNavBarLogin.click();
//        loginPage.LoginDetails();
//        loginPage.btnLogininLoginPage.click();
        webDriver.navigate().to("http://localhost:4200/user-profile");
    }
}
