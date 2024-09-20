package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private final By searchFieldNav = By.xpath("//*[@id=\"desktop-menu\"]/form/input[1]");
    private final By searchFieldWidget = By.xpath("//*[@id=\"weather-widget\"]/div[2]/div/div/div[2]/div[1]/div/input");
    private final By dropdownOptions = By.xpath("//ul[@class='search-dropdown-menu']/li");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }/*
    public WebElement getSearchFieldNav() {
        return driver.findElement(searchFieldNav);
    }*/
    public String getSearchFieldNavPlaceholder() {
        return driver.findElement(searchFieldNav).getAttribute("placeholder");
    }/*
    public WebElement getSearchFieldWidget() {
        return driver.findElement(searchFieldWidget);
    }*/
    public List<WebElement> searchDropdownByText(String searchText) {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchField = driver.findElement(searchFieldWidget);

        searchField.sendKeys(searchText);
        // Wait for the dropdown to populate with options
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownOptions));
        // Get all the dropdown options
        return driver.findElements(dropdownOptions);
    }
    public void searchSelectOptionByText(String searchText, String optionText) {
        List<WebElement> optionList =  searchDropdownByText(searchText);

        // Iterate over the options and find the one that matches the desired text
        for (WebElement option : optionList) {
            List<WebElement> spanList = option.findElements(By.tagName("span"));
            for (WebElement span : spanList) {
                if (span.getText().equalsIgnoreCase(optionText)) {
                    // Click on the matching option
                    option.click();
                    return;
                }
            }
        }
        // If no matching option is found, return a message or handle it accordingly
        throw new NoSuchElementException("No dropdown option found with text: " + optionText);
    }
}
