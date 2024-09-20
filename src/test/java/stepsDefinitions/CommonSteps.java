package stepsDefinitions;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import utility.Hooks;

public class CommonSteps {
    private final WebDriver driver;

    public CommonSteps(Hooks hooks) {
        this.driver = hooks.getDriver();
    }

    @Given("the user is on the main page")
    public void the_user_is_on_the_main_page() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Ensure the Hooks class is properly setting up the WebDriver.");
        }
        // Navigate to main page
        driver.get("https://openweathermap.org/");
    }
}
