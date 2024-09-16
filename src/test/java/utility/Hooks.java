package utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    private BrowserDriver browserDriver;
    private WebDriver driver;

    @Before
    public void setUp() throws IOException {
        // Load the config file to get the browser name
        Properties config = new Properties();
        FileInputStream fis = new FileInputStream("src/test/config.properties");
        config.load(fis);

        // Initialize the browser (default to Chrome if not specified)
        String browser = config.getProperty("browser", "chrome");
        logger.info("Starting test with browser: {}", browser);
        browserDriver = new BrowserDriver(browser);
        driver = browserDriver.getDriver();
        logger.info("Browser {} has been initialized successfully.", browser);  // Confirm browser started
    }

    @After
    public void tearDown() {
        if (driver != null) {
            logger.info("Test finished, quitting the browser.");
            browserDriver.quit();
            logger.info("Browser has been closed.");
        }
    }

    public WebDriver getDriver() {
        // Return the WebDriver instance
        return this.driver;
    }
}
