package stepsDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import utility.Hooks;

public class SearchPlaceHolder {
    private final WebDriver driver;

    // Use constructor injection to get the Hooks class instance and access the WebDriver
    public SearchPlaceHolder(Hooks hooks) {
        this.driver = hooks.getDriver();
    }

    @When("the user looks at the search field")
    public void the_user_looks_at_the_search_field() {
        // No code needed
    }
    @Then("the user should see the placeholder text {string}")
    public void the_user_should_see_the_placeholder_text(String string) {
        MainPage mainPage = new MainPage(driver);
        String text = mainPage.getSearchFieldNavPlaceholder();
        Assert.assertEquals(string, text );
    }
}
