package com.ksupwlt.stepcounttracker.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = {"src/test/java/com/ksupwlt/stepcounttracker/features"},
        glue = "com.ksupwlt.stepcounttracker.steps"
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
