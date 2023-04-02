package com.ksupwlt.stepcounttracker.steps;

import com.ksupwlt.stepcounttracker.models.TestUserDetails;
import com.ksupwlt.stepcounttracker.models.UserDetails;
import com.ksupwlt.stepcounttracker.pages.LandingPage;
import com.ksupwlt.stepcounttracker.pages.LoginPage;
import com.ksupwlt.stepcounttracker.pages.QuestionnairePage;
import com.ksupwlt.stepcounttracker.pages.SignUpPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;

import java.sql.SQLOutput;

public class LoginSteps {
    @Autowired
    private WebDriver webDriver;

    @Autowired
    private LandingPage landingPage;

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private SignUpPage signUpPage;

    @Autowired
    private QuestionnairePage questionnairePage;

    @Autowired
    private TestUserDetails testUserDetails;

    @Given("User is on Landing Page")
    public void userIsOnLandingPage() throws InterruptedException {
//        Thread.sleep(3000);
        Assert.assertTrue(landingPage.btnGetStarted.isDisplayed());
    }

    @And("Navigate to Login Page")
    public void navigateToLoginPage() {
        landingPage.btnGetStarted.click();
        testUserDetails.setUserDetails(new UserDetails("santhiya", "santhiya@123"));
    }

    @And("User click Login Button")
    public void userClickLoginButton() {
        loginPage.btnLogininLoginPage.click();
    }

    @Then("User should get error message")
    public void userShouldGetErrorMessage() {
        System.out.println("Error Message");
    }

    @And("Navigate to SignUp Page")
    public void navigateToSignUpPage() {
       landingPage.btnsignUpForFree.click();
    }

    @And("Click on Login link")
    public void clickOnLoginLink() throws InterruptedException {
        signUpPage.scrollDown();
        Thread.sleep(3000);
        signUpPage.lnkLogin.click();
        testUserDetails.setUserDetails(new UserDetails("santhiya", "santhiya@123"));
    }

    @And("Click on Login button in NavBar")
    public void clickOnLoginButtonInNavBar() {
        landingPage.btnNavBarLogin.click();
        testUserDetails.setUserDetails(new UserDetails("santhiya", "santhiya@123"));
    }


    @Then("User should be navigated to DashBoard")
    public void userShouldBeNavigatedToDashBoard() {
        System.out.println("User Navigated to Dashboard");
    }
}
