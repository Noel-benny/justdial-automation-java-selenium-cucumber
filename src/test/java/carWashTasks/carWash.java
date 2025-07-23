package carWashTasks;
 
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
 
import utils.carWashInfo;
 
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
 
public class  carWash {
 
    WebDriver driver;
    WebDriverWait wait;
    private List<carWashInfo> collectedData = new ArrayList<>();
 
    public  carWash(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
 
    public List<carWashInfo> getCollectedData() {
        return collectedData;
    }
 
    public void openJustDial(String baseURL) {
        driver.get(baseURL);
    }
 
    public void handleInitialPopups() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Maybe Later"))).click();
        } catch (Exception e) {
            System.out.println("No 'Maybe Later' popup found.");
        }
 
        wait.until(ExpectedConditions.elementToBeClickable(By.id("city-auto-sug"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@class='location_text font14 fw400 color007']"))).click();
        Thread.sleep(2000);
 
        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@onclick=\"closePopUp('best_deal_div');\"]"))).click();
        } catch (Exception e) {
            System.out.println("No second popup found.");
        }
    }
 
    public void searchService(String query) throws InterruptedException {
        driver.findElement(By.xpath("//input[@class='input_search font14 fw400 color111']")).sendKeys(query);
        driver.findElement(By.id("srchbtn")).click();
        Thread.sleep(2000);
    }
 
    public void applyRatingFilter() {
        driver.findElement(By.id("all_filters_btn")).click();
        WebElement rating = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label='4.0+']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", rating);
        rating.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label='Rating']"))).click();
        driver.findElement(By.xpath("//button[text()='Apply Filters']")).click();
    }
 
    public void printTopRatedServices() throws InterruptedException {
        List<WebElement> servicesList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@class='jsx-7cbb814d75c86232 resultbox ']")));
 
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int count = 0;
 
        for (WebElement service : servicesList) {
            if (count >= 5) break;
 
            try {
                js.executeScript("arguments[0].scrollIntoView()", service);
 
                String voteText = "";
                try {
                    WebElement votes = service.findElement(By.xpath(".//*[@class='resultbox_countrate mr-12 font15 fw400 color777']"));
                    voteText = votes.getText().replaceAll("[^0-9]", "");
                } catch (Exception e) {
                    continue;
                }
 
                int voteCount = voteText.isEmpty() ? 0 : Integer.parseInt(voteText);
                if (voteCount <= 20) continue;
 
                String name = service.findElement(By.xpath(".//h3")).getText();
                String phone = "";
 
                try {
                    service.findElement(By.xpath(".//*[@class='jsx-7cbb814d75c86232  greenfill_animate callbutton font15 fw500 colorFFF mr-15 ']")).click();
                    Thread.sleep(2000);
                    phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='font20 fw600 colorFFF mt-15']"))).getText();
                    driver.findElement(By.xpath("//*[@class='jsx-dcde576cdf171c2a jd_modal_close jdicon']")).click();
                } catch (Exception e) {
                    phone = service.findElement(By.xpath(".//*[@class='jsx-7cbb814d75c86232 callcontent callNowAnchor']")).getText();
                }
 
                System.out.println("Name: " + name);
                System.out.println("Ratings: " + voteCount);
 
                collectedData.add(new carWashInfo(name, phone, voteCount));
                count++;
 
            } catch (Exception e) {
                continue;
            }
        }
    }
}