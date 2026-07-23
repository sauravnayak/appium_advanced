import io.appium.java_client.AppiumBy;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ch_02_02_Android_Devices_Before  extends BaseTest{

    WebDriverWait wait;
    @Test
    public void testLoginOnRealDevice() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement loginButton=wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Login Screen")));
        loginButton.click();

        WebElement userNameField=wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("username")));
        userNameField.sendKeys("alice");

        WebElement pwdField= wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("password")));
        pwdField.sendKeys("mypassword");

        WebElement login = driver.findElement(AppiumBy.accessibilityId("loginBtn"));
        login.click();

        WebElement secureMsg =wait.until(ExpectedConditions.presenceOfElementLocated(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"You are logged in as alice\")")));
        Assert.assertEquals("You are logged in as alice",secureMsg.getText());

    }
}
