Feature: Signup Feature
  This feature will deal with Signup Functionality of the application

  Scenario: Create new User through NavBar
    Given User is on Landing Page
    And User Click on SignUp button in NavBar
    And User enter the Following Details
    And User click Register Button
    Then User should be navigated to Questionnaire Page

  Scenario: Create new User through Login Page
    Given User is on Landing Page
    And User Click on Login button in NavBar
    And User Click on SignUp link in Login Page
    And User enter the Following Details
    And User click Register Button
    Then User should be navigated to Questionnaire Page

  Scenario: Create Already Existing User
    Given User is on Landing Page
    And User Click on SignUp button in NavBar
    And User enter the Following Details
    And User click Register Button
    Then User should get the Error Message