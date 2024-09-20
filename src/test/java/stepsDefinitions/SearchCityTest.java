package stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import utility.Hooks;

public class SearchCityTest {
    private final MainPage mainPage;

    public SearchCityTest(Hooks hooks) {
        WebDriver driver = hooks.getDriver();
        this.mainPage = new MainPage(driver);
    }
    @When("the user type {string} in the search field")
    public void theUserTypeInTheSearchField(String city) {
        mainPage.getSearchFieldWidget().sendKeys(city);
    }

    @And("the user clicks on search buton")
    public void theUserClicksOnSearchButon() {
        mainPage.getSearchBtn().click();
    }

    @And("the user selects {string}")
    public void theUserSelects(String city) {
        mainPage.searchSelectOptionByText(city);
    }

    @Then("the user should see city title {string}")
    public void theUserShouldSeeCityTitle(String arg0) {
        
    }

    @And("the user should see the correct date")
    public void theUserShouldSeeTheCorrectDate() {
        
    }

    @And("the user should see the correct time")
    public void theUserShouldSeeTheCorrectTime() {
    }
}
