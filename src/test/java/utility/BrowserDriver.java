package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserDriver {
    private final WebDriver driver;

    public BrowserDriver(String browser) {
        FirefoxOptions optionsFF;
        ChromeOptions optionsCh;

        if (browser.equalsIgnoreCase("firefox")) {
            // Setup FirefoxDriver
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                    "/src/test/resources/drivers/geckodriver");
            optionsFF = new FirefoxOptions();
            driver = new FirefoxDriver(optionsFF);
            driver.manage().window().maximize();
            driver.get("https://openweathermap.org/");
        } else if (browser.equalsIgnoreCase("chrome")) {
            // Setup ChromeDriver
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                    "/src/test/resources/drivers/chromedriver");
            optionsCh = new ChromeOptions();
            driver = new ChromeDriver(optionsCh);
            driver.manage().window().maximize();
            driver.get("https://openweathermap.org/");
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }
    public WebDriver getDriver() {
        return this.driver;
    }
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
