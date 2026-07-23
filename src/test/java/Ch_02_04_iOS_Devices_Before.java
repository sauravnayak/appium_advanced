import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;
import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ch_02_04_iOS_Devices_Before {
    private static final String APP_IOS = "https://github.com/cloudgrey-io/the-app/releases/download/v1.10.0/TheApp-v1.10.0.ipa";
    private static final String APPIUM = "http://localhost:4723/wd/hub";

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "12.4");
        caps.setCapability("deviceName", "iPhone 7");
        caps.setCapability("app", APP_IOS);
        driver = new IOSDriver(new URL(APPIUM), caps);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement screen = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Login Screen")));
        screen.click();

        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("username")));
        username.sendKeys("alice");

        WebElement password = driver.findElement(AppiumBy.accessibilityId("password"));
        password.sendKeys("mypassword");

        WebElement login = driver.findElement(AppiumBy.accessibilityId("loginBtn"));
        login.click();

        WebElement loginText = wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.xpath("//XCUIElementTypeStaticText[contains(@label, 'You are logged in')]")));

        assert(loginText.getText().contains("alice"));
    }
}
