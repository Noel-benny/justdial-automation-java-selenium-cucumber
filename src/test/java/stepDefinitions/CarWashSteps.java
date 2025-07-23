package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import carWashTasks.carWash;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ExcelWriter;
import utils.WebDriverFactory;
import utils.carWashInfo;
import utils.configReader;

public class CarWashSteps {

    static WebDriver driver;
    carWash carwash;
    static String browserName;
    static configReader config;

    private static final Logger logger = LogManager.getLogger(CarWashSteps.class);

    @Given("User launches browser {string} and opens JustDial website")
    public void launchBrowserAndOpenWebsite(String browser) throws IOException {
        logger.info("Launching browser: " + browser + " and opening JustDial...");
        
        browserName = browser;
        config = new configReader();
        configReader.loadProperties();
        driver = WebDriverFactory.getDriver(browser);
        carwash = new carWash(driver);

        String url = configReader.get("baseURL");
        carwash.openJustDial(url);
        logger.debug("Navigated to URL: " + url);
    }

    @When("User handles car wash popups")
    public void handlePopups() throws InterruptedException {
        logger.info("Handling initial popups...");
        carwash.handleInitialPopups();
        logger.debug("Popups handled successfully.");
    }

    @And("User searches for car washing services")
    public void searchService() throws InterruptedException {
        String searchTerm = configReader.get("searchItem");
        logger.info("Searching for: " + searchTerm);
        carwash.searchService(searchTerm);
        logger.debug("Search action completed.");
    }

    @And("User applies rating filter")
    public void applyRatingFilter() {
        logger.info("Applying rating filter...");
        carwash.applyRatingFilter();
        logger.debug("Rating filter applied.");
    }

    @And("User prints top rated services")
    public void printTopRatedServices() throws InterruptedException {
        logger.info("Collecting and printing top-rated services...");
        carwash.printTopRatedServices();

        List<carWashInfo> data = carwash.getCollectedData();
        logger.debug("Number of listings collected: " + data.size());
        ExcelWriter.writeToExcel(browserName, data);
        logger.info("Data written to Excel for browser: " + browserName);
    }

    public static WebDriver getSharedDriver() {
        return driver;
    }
}
