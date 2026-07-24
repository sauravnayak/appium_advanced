import io.appium.java_client.AppiumBy;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebView_Automation_Before extends  BaseTest{


    @Test
    public void testHybridApp() throws InterruptedException {
        // FIX 5: Modernized locator method using AppiumBy
        driver.findElement(AppiumBy.accessibilityId("Webview Demo")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        var urlInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("urlInput")
        ));

        urlInput.sendKeys("https://appiumpro.com");

        // 3. Click the native "Navigate" or "Go" button next to the input
        var navigateBtn = driver.findElement(AppiumBy.accessibilityId("navigateBtn"));
        navigateBtn.click();

        //Thread.sleep(1000);
        wait.until(d -> driver.getContextHandles().size() > 1);
        driver.context(getWebContext(driver));

        // 2. Perform a clean assertion
        Assert.assertEquals( "HAO788 appiumpro | Game Online Tempat Momen Menang Besar Terjadi",driver.getTitle());
    }
}
