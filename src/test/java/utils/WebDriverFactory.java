package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class WebDriverFactory {
    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public static WebDriver getDriver(String browserName) {
        WebDriver driver;
        logger.info("Launching browser: " + browserName);

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(
                    "--disable-notifications",
                    "--disable-popup-blocking",
                    "--disable-blink-features=AutomationControlled",
                    "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
                );
                driver = new ChromeDriver(chromeOptions);
                logger.info("Chrome browser launched successfully.");
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments(
                    "--disable-notifications",
                    "--disable-popup-blocking",
                    "--disable-blink-features=AutomationControlled",
                    "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
                );
                driver = new EdgeDriver(edgeOptions);
                logger.info("Edge browser launched successfully.");
                break;

            default:
                logger.error("Unsupported browser requested: " + browserName);
                throw new RuntimeException("Unsupported browser: " + browserName);
        }

        driver.manage().window().maximize();
        return driver;
    }
}
