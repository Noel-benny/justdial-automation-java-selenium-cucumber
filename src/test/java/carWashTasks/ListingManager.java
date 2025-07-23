package carWashTasks;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.io.FileHandler;

public class ListingManager {
    WebDriver driver;
    WebDriverWait wait;

    public ListingManager(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void submitFreeListing(String mobileNumber) {
        driver.findElement(By.xpath("//*[text()='Free Listing']")).click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'modal_modal__')]")));
            driver.findElement(By.xpath("//*[@class='iconwrap closeicon__grey']")).click();
        } catch (Exception ignored) {}

        WebElement phoneInput = driver.findElement(By.xpath("//input[@aria-label='Enter Mobile Number' and @id='1']"));
        phoneInput.clear();
        phoneInput.sendKeys(mobileNumber);

        driver.findElement(By.xpath("//button[@aria-label='Start Now']")).click();

        try {
            String error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(@class,'entermobilenumber_error__text')]"))).getText();
            System.out.println("ERROR MESSAGE: " + error);
            takeScreenshot("error_invalid_number");
        } catch (TimeoutException e) {
            System.out.println("No error message found. Assuming valid input.");
            takeScreenshot("success_valid_number");
        }
    }

    public void retryWithValidNumber(String validNumber) {
        try {
            WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@id='homebreadcrum']")));
            logo.click();

            wait.until(ExpectedConditions.titleContains("Justdial"));

            driver.findElement(By.xpath("//*[text()='Free Listing']")).click();

            WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@aria-label='Enter Mobile Number' and @id='1']")));
            phoneInput.clear();
            phoneInput.sendKeys(validNumber);
            driver.findElement(By.xpath("//button[@aria-label='Start Now']")).click();

            Thread.sleep(2000);
            System.out.println("Valid number submitted after homepage navigation: " + validNumber);
            takeScreenshot("success_valid_number");

        } catch (Exception e) {
            System.out.println("Unexpected issue while retrying with valid number: " + validNumber);
            takeScreenshot("error_valid_number");
            e.printStackTrace();
        }
    }

    private void takeScreenshot(String label) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        try {
            FileHandler.copy(screenshot, new File("screenshots/" + label + "_" + timestamp + ".png"));
            System.out.println("Screenshot saved: " + label + " at " + timestamp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void returnToHomepage() {
        try {
            WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@id='homebreadcrum']")));
            logo.click();
        } catch (Exception e) {
            driver.navigate().to("https://www.justdial.com/");
        }
        wait.until(ExpectedConditions.titleContains("Justdial"));
    }
}
