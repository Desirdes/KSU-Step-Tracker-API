package com.ksupwlt.stepcounttracker.steps;

import com.ksupwlt.stepcounttracker.models.TestUserDetails;
import com.ksupwlt.stepcounttracker.pages.LoginPage;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDetailsSteps {
    @Autowired
    private LoginPage loginPage;

    @Autowired
    private TestUserDetails testUserDetails;

    @And("User enter email and Password")
    public void userEnterEmailAndPassword() {
        loginPage.LoginDetails(testUserDetails.getUserDetails().getUserName(), testUserDetails.getUserDetails().getPassword());
    }
}
