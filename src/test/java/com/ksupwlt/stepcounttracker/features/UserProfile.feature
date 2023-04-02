Feature: User Profile Page Feature
  This feature will deal with User Profile page Functionality

  Scenario: Update User Profile
    Given User is on Landing Page
    And Navigate to Login Page
    And Click on Edit button
    And Update the following details
      | Name | Sandy|
      | Email | ssubram6@students.kennesaw.edu|
    And Click on Update button
    Then Verify Updated Details