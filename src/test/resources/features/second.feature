Feature: Pricing Calculator

  Scenario: Calculating the platform price
    Given I open the Cloud Calculator Page "https://cloud.google.com/products/calculator"
    When I input the required data into the form
    And Click on detailed view icon
    And Move to the new tab
    Then All inputted data should be displayed on the View page