Feature: Verify the functinality of the main page middle search field

  Scenario: Serach for city and verify the selected city, date and time
    Given the user is on main page
    When the user type "Sydney" in the search field
    And the user clicks on search buton
    And the user selects "Sydney, AU"
    Then the user should see city title "Sydney, AU"
    And the user should see the correct date
    And the user should see the correct time