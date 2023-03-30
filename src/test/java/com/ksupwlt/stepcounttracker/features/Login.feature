Feature: Login Feature
  This feature will deal with Login Functionality of the application

  Scenario: Login with Valid Credential
    Given User is on Landing Page
    And Navigate to Login Page
    And User enter email and Password
    And User click Login Button
    Then User should be navigated to DashBoard

  Scenario: Login with InValid Credential
    Given User is on Landing Page
    And Navigate to Login Page
    And User enter email and Password
    And User click Login Button
    Then User should get error message

  Scenario: Navigating to Login Page through SignUp Page
    Given User is on Landing Page
    And Navigate to SignUp Page
    And Click on Login link
    And User enter email and Password
    And User click Login Button
    Then User should get error message

  Scenario: Navigating to Login Page through Login button from NavBar
    Given User is on Landing Page
    And Click on Login button in NavBar
    And User enter email and Password
    And User click Login Button
    Then User should get error message


