package stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import utility.Hooks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SearchCityTest {
    private final MainPage mainPage;
    private String selectionDate;
    private String selectionTime;

    public SearchCityTest(Hooks hooks) {
        WebDriver driver = hooks.getDriver();
        this.mainPage = new MainPage(driver);
        this.selectionDate = "";
        this.selectionTime = "";
    }
    @When("the user type {string} in the search field")
    public void theUserTypeInTheSearchField(String city) {

        mainPage.typeTextFieldWidget(city);
    }

    @And("the user clicks on search button")
    public void theUserClicksOnSearchButton() throws InterruptedException {
        Thread.sleep(10000);

        mainPage.clickSearchBtn();
    }

    @And("the user selects {string}")
    public void theUserSelects(String city) {
        mainPage.searchSelectOptionByText(city);
    }

    @And("the user gets the Sydney date and time")
    public void theUserGetsTheSydneyDateAndTime() {
        LocalDateTime selectionDateTime = mainPage.getSelectionDateTime();
        DateTimeFormatter dateFormatter;

        dateFormatter = DateTimeFormatter.ofPattern("MMM dd");
        selectionDate = selectionDateTime.format(dateFormatter);
        dateFormatter = DateTimeFormatter.ofPattern("HH:mm a");
        selectionTime = selectionDateTime.format(dateFormatter);
    }

    @Then("the user should see city title {string}")
    public void theUserShouldSeeCityTitle(String title) {
        String actualTitle = mainPage.getWidgetTitle();

        Assert.assertEquals(title, actualTitle);
    }

    @And("the user should see the correct date")
    public void theUserShouldSeeTheCorrectDate() {
        String actualDateTime = mainPage.getWidgetDate();

        String actualDate = actualDateTime.split(", ")[0];
        Assert.assertEquals(selectionDate, actualDate);
    }

    @And("the user should see the correct time")
    public void theUserShouldSeeTheCorrectTime() {
        String actualDateTime = mainPage.getWidgetDate();

        String actualTime = actualDateTime.split(", ")[1];
        Assert.assertEquals(selectionTime, actualTime);
    }
}
