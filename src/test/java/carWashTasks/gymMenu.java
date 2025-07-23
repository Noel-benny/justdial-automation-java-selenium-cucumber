package carWashTasks;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.carWashInfo;

public class gymMenu {

    WebDriver driver;
    WebDriverWait wait;

    public gymMenu(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void navigateToGymMenu() throws InterruptedException {
		
    	System.out.println("Navigating to Gym menu...");
        WebElement targetElement = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id=\"mainContent\"]/ul[2]/li[14]/a/div[1]")));
        targetElement.click();
        Thread.sleep(5000);
    }

    public void printGymSubmenuItems() 
    {
    	System.out.println("The Sub-menu items of the Gym menu:");
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.xpath("//span[@class='jsx-2394d511e76092fb font15 fw500 mr-6 block-item']")));
 
        List<String> elementTexts = new ArrayList<>();
        for (WebElement element : elements) {
            String text = element.getText().trim();
            if (!text.isEmpty()) {
                elementTexts.add(text);
            }
        }
 
        for (String text : elementTexts) {
            System.out.println(text);
        }
    }
   
    public void closeBrowser() {
        driver.quit();
    }
}
