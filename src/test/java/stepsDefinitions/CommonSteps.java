package stepsDefinitions;

import io.cucumber.java.en.Given;
import pages.MainPage;

public class CommonSteps {
    @Given("the user is on the main page")
    public void the_user_is_on_the_main_page() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Ensure the Hooks class is properly setting up the WebDriver.");
        }
        // Initialize the MainPage object using the driver
        mainPage = new MainPage(driver);
        // Navigate to main page
        driver.get("https://openweathermap.org/");
    }
}
