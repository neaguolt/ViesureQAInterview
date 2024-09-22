package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class MainPage {
    private final WebDriver driver;
    private final By searchFieldNav = By.xpath("//*[@id=\"desktop-menu\"]/form/input[1]");
    private final By searchFieldWidget = By.xpath("//*[@id=\"weather-widget\"]/div[2]/div/div/div[2]/div[1]/div/input");
    private final By searchBtn = By.xpath("//*[@id=\"weather-widget\"]/div[2]/div/div/div[2]/div[1]/button");
    private final By dropdownOptions = By.xpath("//ul[@class='search-dropdown-menu']/li");
    private final By WidgetTitle = By.xpath("//*[@id=\"weather-widget\"]/div[3]/div[1]/div[1]/div[1]/h2");
    private final By WidgetDate = By.xpath("//*[@id=\"weather-widget\"]/div[3]/div[1]/div[1]/div[1]/span");
    private LocalDateTime selectionDateTime;
    private static final Logger logger = LoggerFactory.getLogger(MainPage.class);

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }/*
    public WebElement getSearchFieldNav() {
        return driver.findElement(searchFieldNav);
    }*/
    public void openURL(){
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Ensure the Hooks class is properly setting up the WebDriver.");
        }
        // Navigate to main page
        driver.get("https://openweathermap.org/");
    }
    public String getSearchFieldNavPlaceholder() {
        return driver.findElement(searchFieldNav).getAttribute("placeholder");
    }
    public void typeTextFieldWidget(String text) {

        driver.findElement(searchFieldWidget).sendKeys(text);
    }
    public void clickSearchBtn() {

        driver.findElement(searchBtn).click();
    }
    public List<WebElement> getDropdownList() {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the dropdown to populate with options
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownOptions));
        // Get all the dropdown options
        return driver.findElements(dropdownOptions);
    }
    public void searchSelectOptionByText(String optionText) {
        List<WebElement> optionList =  getDropdownList();

        // Iterate over the options and find the one that matches the desired text
        Optional<WebElement> elementToClick = optionList.stream()
                .flatMap(option ->option.findElements(By.tagName("span")).stream()
                        .filter(span -> span.getText().equalsIgnoreCase(optionText))
                        .map(span->option))
                        .findFirst();

        if (elementToClick.isPresent()) {
            WebElement element = elementToClick.get();
            element.click();
            logger.info("Clicked on the option with text: {}", optionText);
            selectionDateTime = LocalDateTime.now();  // Return the current date and time when the click occurs
        } else {
            // If no matching element is found, throw an exception
            throw new NoSuchElementException("No dropdown option found with text: " + optionText);
        }
    }
    public String getWidgetTitle() {

        return driver.findElement(WidgetTitle).getText();
    }

    public String getWidgetDate() {
        return driver.findElement(WidgetDate).getText();
    }

    public LocalDateTime getSelectionDateTime() {

        return selectionDateTime;
    }
}
