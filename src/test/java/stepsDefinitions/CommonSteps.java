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
        MainPage mainPage = new MainPage(driver);

        mainPage.openURL();
    }
}
