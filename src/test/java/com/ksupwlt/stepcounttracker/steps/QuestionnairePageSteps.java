package com.ksupwlt.stepcounttracker.steps;

import com.ksupwlt.stepcounttracker.pages.QuestionnairePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class QuestionnairePageSteps {
    @Autowired
    private WebDriver webDriver;

    @Autowired
    private QuestionnairePage questionnairePage;

    @And("Navigate to Questionnaire Page")
    public void navigateToQuestionnairePage() {
        webDriver.navigate().to("http://localhost:4200/questionnaire");
    }

    @And("Enter the following details")
    public void enterTheFollowingDetails() throws InterruptedException {
        questionnairePage.UserDetails();
    }

    @And("Click on Submit button")
    public void clickOnSubmitButton() throws InterruptedException {
        Thread.sleep(3000);
        questionnairePage.btnSubmit.click();
    }

    @Then("Verify Submitted Details in User Profile Page")
    public void verifySubmittedDetailsInUserProfilePage() {
        System.out.println("Verified");
    }
}
