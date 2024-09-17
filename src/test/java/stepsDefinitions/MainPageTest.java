package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import utility.Hooks;

public class MainPageTest {
    private MainPage mainPage;
    private final WebDriver driver;

    // Use constructor injection to get the Hooks class instance and access the WebDriver
    public MainPageTest(Hooks hooks) {
        this.driver = hooks.getDriver();  // Get the WebDriver instance from Hooks
    }
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
    @When("the user looks at the search field")
    public void the_user_looks_at_the_search_field() {
        // No code needed
    }
    @Then("the user should see the placeholder text {string}")
    public void the_user_should_see_the_placeholder_text(String string) {
        String text = mainPage.getSearchFieldNavPlaceholder();
        Assert.assertEquals(string, text );
    }
}
