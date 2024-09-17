Feature: Verify the placeholder text in the main page's search field

  Scenario: The Navbar search field contains the correct placeholder text
    Given the user is on the main page
    When the user looks at the search field
    Then the user should see the placeholder text "Weather in your city"