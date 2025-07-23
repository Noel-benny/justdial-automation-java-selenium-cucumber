package stepDefinitions;

import carWashTasks.gymMenu;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GymSteps {

    private static final Logger logger = LogManager.getLogger(GymSteps.class);
    WebDriver driver = CarWashSteps.getSharedDriver();
    gymMenu gym = new gymMenu(driver);

    @And("User navigates to Gym menu")
    public void navigateToGymMenu() throws InterruptedException {
        logger.info("Navigating to Gym menu...");
        gym.navigateToGymMenu();
        logger.debug("Gym menu navigation complete.");
    }

    @Then("User prints gym submenu items")
    public void printGymSubmenuItems() {
        logger.info("Retrieving and printing Gym submenu items...");
        gym.printGymSubmenuItems();
        logger.debug("Gym submenu items printed.");
    }

    @And("User closes browser")
    public void closeBrowser() {
        logger.info("Closing browser...");
        driver.quit();
        logger.debug("Browser closed successfully.");
    }
}
