package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import carWashTasks.ListingManager;
import io.cucumber.java.en.Then;
import utils.configReader;

public class ListingSteps {

    private static final Logger logger = LogManager.getLogger(ListingSteps.class);

    ListingManager listingManager;
    static configReader config = new configReader();

    public ListingSteps() {
        WebDriver driver = CarWashSteps.getSharedDriver();
        listingManager = new ListingManager(driver);
    }

    @Then("User submits Free Listing with mobile number 1234567890 and retries with valid number")
    public void submitFreeListing() {
        String invalid = configReader.get("invalidMobileNumber");
        String valid = configReader.get("validMobileNumber");

        logger.info("Submitting Free Listing with invalid mobile: " + invalid);
        listingManager.submitFreeListing(invalid);
        logger.warn("Expecting alert or error popup for invalid submission.");

        logger.info("Retrying Free Listing with valid mobile: " + valid);
        listingManager.retryWithValidNumber(valid);
        logger.debug("Valid mobile submission flow executed.");
    }

    @Then("User returns to homepage")
    public void returnToHomepage() {
        logger.info("Returning to homepage...");
        listingManager.returnToHomepage();
        logger.debug("Homepage navigation completed.");
    }
}
