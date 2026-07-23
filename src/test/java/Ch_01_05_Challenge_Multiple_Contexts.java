import io.appium.java_client.AppiumBy;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ch_01_05_Challenge_Multiple_Contexts extends BaseTest {
    WebDriverWait wait ;

    @Test
    public void testHybridApp() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // 1. Navigate to the webview page
        driver.findElement(AppiumBy.accessibilityId("Webview Demo")).click();

        // 2. Attempt to navigate to an incorrect site
        driver.findElement(AppiumBy.accessibilityId("urlInput")).sendKeys("https://google.com");
        driver.findElement(AppiumBy.accessibilityId("navigateBtn")).click();

        // 3. Assert that an error message pops up
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        Assert.assertEquals("Alert\nSorry, you are not allowed to visit that url",message);
        alert.accept();

        // 4. assert that the webview did not actually go anywhere
        // TODO
        String web=getWebContext(driver);
        driver.context(web);
        driver.findElement(By.tagName("h1")).getText().contains("Please Navigate to a webpage");
        driver.context("NATIVE_APP");



        // 5. attempt to navigate to the correct site
        // TODO
        driver.findElement(AppiumBy.accessibilityId("urlInput")).sendKeys("https://appiumpro.com");
        driver.findElement(AppiumBy.accessibilityId("navigateBtn")).click();

        // 6. assert that the webview went to the right place
        // TODO
        driver.context(getWebContext(driver));
        Assert.assertEquals("HAO788 appiumpro | Game Online Tempat Momen Menang Besar Terjadi",driver.getTitle());
    }
}
