package com.ksupwlt.stepcounttracker.pages;

import com.ksupwlt.stepcounttracker.annotation.PageScope;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@PageScope
public class QuestionnairePage extends basePage{
    @Autowired
    private WebDriver webDriver;

    @Autowired
    private SignUpPage signUpPage;

    @FindBy(how = How.XPATH, using = "//h1[contains(text(),'Questionnaire Form')]")
    public WebElement qusTitle;

    @FindBy(how = How.XPATH, using = "//input[@id='age']")
    public WebElement ageTextBox;

    @FindBy(how = How.XPATH, using = "//select[@id='sex']")
    public WebElement sexSelection;

    @FindBy(how = How.XPATH, using = "//option[@value='Female']")
    public WebElement femaleOption;

    @FindBy(how = How.XPATH, using = "//option[@value='Male']")
    public WebElement maleOption;

    @FindBy(how = How.XPATH, using = "//select[@id='race']")
    public WebElement receSelection;

    @FindBy(how = How.XPATH, using = "//option[contains(text(),'Black or African American')]")
    public WebElement blackRaceOption;

    @FindBy(how = How.XPATH, using = "//option[contains(text(),'White')]")
    public WebElement whiteRaceOption;

    @FindBy(how = How.XPATH, using = "//option[contains(text(),'American Indian or Alaskan Native')]")
    public WebElement nativeRaceOption;

    @FindBy(how = How.XPATH, using = "//option[contains(text(),'Asian')]")
    public WebElement asianRaceOption;

    @FindBy(how = How.XPATH, using = "//option[contains(text(),'Native Hawaiian or Other Pacific Islander')]")
    public WebElement nativeHawaiRaceOption;


    @FindBy(how = How.XPATH, using = "//input[@id='height']")
    public WebElement heightTextBox;

    @FindBy(how = How.XPATH, using = "//input[@id='weight']")
    public WebElement weightTextBox;

    @FindBy(how = How.XPATH, using = "//input[@id='waistCircumference']")
    public WebElement wtCirTextBox;

    @FindBy(how = How.XPATH, using = "//input[@id='neckCircumference']")
    public WebElement neckCirTextBox;

    @FindBy(how = How.XPATH, using = "//input[@id='bodyFatPercentage']")
    public WebElement bdyFatPctgTextBox;

    @FindBy(how = How.XPATH, using = "//select[@id='targetWeightLossPercentage']")
    public WebElement tgtWgtLossPctgSelection;

    @FindBy(how = How.XPATH, using = "//option[@value='5']")
    public WebElement percentageOptionFv;

    @FindBy(how = How.XPATH, using = "//option[@value='6']")
    public WebElement percentageOptionSix;

    @FindBy(how = How.XPATH, using = "//option[@value='7']")
    public WebElement percentageOptionSvn;

    @FindBy(how = How.XPATH, using = "//option[@value='8']")
    public WebElement percentageOptionEgt;

    @FindBy(how = How.XPATH, using = "//option[@value='9']")
    public WebElement percentageOptionNne;

    @FindBy(how = How.XPATH, using = "//option[@value='10']")
    public WebElement percentageOptionTen;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Submit')]")
    public WebElement btnSubmit;

    public void UserDetails() throws InterruptedException {
        ageTextBox.sendKeys("30");
        signUpPage.scrollDown();
        Thread.sleep(1000);
        sexSelection.click();
        femaleOption.click();
        receSelection.click();
        asianRaceOption.click();
        heightTextBox.sendKeys("62");
        weightTextBox.sendKeys("152");
        wtCirTextBox.sendKeys("29");
        neckCirTextBox.sendKeys("22.44");
        bdyFatPctgTextBox.sendKeys("34");
        signUpPage.scrollDown();
        Thread.sleep(3000);
        tgtWgtLossPctgSelection.click();
        Thread.sleep(1000);
        percentageOptionSvn.click();
    }
}
