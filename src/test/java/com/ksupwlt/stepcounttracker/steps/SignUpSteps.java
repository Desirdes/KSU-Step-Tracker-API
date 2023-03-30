package com.ksupwlt.stepcounttracker.steps;

import com.ksupwlt.stepcounttracker.pages.LandingPage;
import com.ksupwlt.stepcounttracker.pages.LoginPage;
import com.ksupwlt.stepcounttracker.pages.QuestionnairePage;
import com.ksupwlt.stepcounttracker.pages.SignUpPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import javax.swing.*;

public class SignUpSteps {
    @Autowired
    private LandingPage landingPage;

    @Autowired
    private WebDriver webDriver;

    @Autowired
    private SignUpPage signUpPage;

    @Autowired
    private QuestionnairePage questionnairePage;

    @Autowired
    private LoginPage loginPage;

    @And("User Click on SignUp button in NavBar")
    public void userClickOnSignUpButtonInNavBar(){
        landingPage.btnsignUpForFree.click();
    }

    @And("User enter the Following Details")
    public void userEnterTheFollowingDetails() {
//        System.out.println(signUpPage.nameSTextBox.isDisplayed());
        signUpPage.signUpDetails();
    }

    @And("User click Register Button")
    public void userClickRegisterButton() throws InterruptedException {
        signUpPage.scrollDown();
        Thread.sleep(3000);
        signUpPage.registerButton.click();
    }

    @Then("User should be navigated to Questionnaire Page")
    public void userShouldBeNavigatedToQuestionnairePage() {
        Assert.assertTrue(questionnairePage.qusTitle.isDisplayed());
    }

    @And("User Click on Login button in NavBar")
    public void userClickOnLoginButtonInNavBar() {
        landingPage.btnNavBarLogin.click();
    }

    @And("User Click on SignUp link in Login Page")
    public void userClickOnSignUpLinkInLoginPage() {
        loginPage.lnkSignup.click();
    }

    @Then("User should get the Error Message")
    public void userShouldGetTheErrorMessage() {
        System.out.println("Username is already exist");
    }


}
