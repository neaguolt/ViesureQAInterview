package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private final WebDriver driver;

    public WebDriver initDriver(String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions optionsFF = new FirefoxOptions();
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")
                    + "/src/test/resources/drivers/geckodriver");
            driver = new FirefoxDriver(optionsFF);
        } else if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions optionsCh = new ChromeOptions();
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                    + "/src/test/resources/drivers/chromedriver");
            driver = new ChromeDriver(optionsCh);
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }
    public WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Call initDriver() first.");
        }
        return this.driver;
    }
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
