Feature: Search Engine

  Scenario: Redirecting to search results
    Given I open the Google Cloud "https://cloud.google.com"
    When I type a search prompt in the search bar "Google Cloud Pricing Calculator"
    And I click on one of the provided results "Google Cloud Pricing Calculator"
    Then The calculator page should be displayed