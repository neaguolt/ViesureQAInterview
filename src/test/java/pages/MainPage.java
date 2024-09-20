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
import java.util.List;
import java.util.Optional;


public class MainPage {
    private final WebDriver driver;
    private final By searchFieldNav = By.xpath("//*[@id=\"desktop-menu\"]/form/input[1]");
    private final By searchFieldWidget = By.xpath("//*[@id=\"weather-widget\"]/div[2]/div/div/div[2]/div[1]/div/input");
    private final By dropdownOptions = By.xpath("//ul[@class='search-dropdown-menu']/li");
    private static final Logger logger = LoggerFactory.getLogger(MainPage.class);

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
        Optional<WebElement> elementToClick = optionList.stream()
                .flatMap(option ->option.findElements(By.tagName("span")).stream()
                        .filter(span -> span.getText().equalsIgnoreCase(optionText))
                        .map(span->option))
                        .findFirst();

        elementToClick.ifPresentOrElse(
                element -> {
                    element.click();
                    logger.info("Clicked on the option with text: {}", searchText);
                },
                () -> {
                    // If no matching element is found, print a message
                    throw new NoSuchElementException("No dropdown option found with text: " + optionText);
                }
        );
    }
}
