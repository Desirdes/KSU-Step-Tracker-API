Feature: Questionnaire form Feature
  This feature will deal with questionnaire form feature

  Scenario: Enter the details and Verify details updated
    Given User is on Landing Page
    And Navigate to Questionnaire Page
    And Enter the following details
    And Click on Submit button
    Then Verify Submitted Details in User Profile Page