Feature: Verify the functionality of the main page middle search field

  Scenario: Search for city and verify the selected city, date and time
    Given the user is on the main page
    When the user type "Sydney" in the search field
    And the user clicks on search button
    And the user selects "Sydney, AU"
    And the user gets the Sydney date and time
    Then the user should see city title "Sydney, AU"
    And the user should see the correct date
    And the user should see the correct time