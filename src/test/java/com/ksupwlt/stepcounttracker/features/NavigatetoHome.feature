Feature: Navigate to Home Page
  This feature will deal with Navigating to landing page from every page through Navigation Bar

  Scenario: Navigate to Home Page from Login Page
    Given User is on Landing Page
    And Navigate to Login Page
    And Click on KSU Weightloss Tracker Link in Navigation Bar
    Then User should be navigated to Landing Page

  Scenario: Navigate to Home Page from SignUp Page
    Given User is on Landing Page
    And Navigate to SignUp Page
    And Click on KSU Weightloss Tracker Link in Navigation Bar
    Then User should be navigated to Landing Page

  Scenario: Navigate to Home Page from UserPage Page
    Given User is on Landing Page
    And Navigate to UserProfile Page
    And Click on KSU Weightloss Tracker Link in Navigation Bar
    Then User should be navigated to Landing Page