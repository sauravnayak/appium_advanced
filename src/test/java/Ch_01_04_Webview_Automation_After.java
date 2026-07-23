import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.SupportsContextSwitching; // FIX 1: Required for context switching on generic AppiumDriver

import java.net.URI; // FIX 2: Replaces deprecated URL constructor strings
import java.time.Duration; // FIX 3: Replaces obsolete TimeUnit flags
import java.util.ArrayList;
import javax.annotation.Nullable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Ch_01_04_Webview_Automation_After {
    private static final String APP_ANDROID = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
    private static final String APP_IOS = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.app.zip";

    // FIX 4: Updated to root URL to resolve routing errors
    private static final String APPIUM = "http://localhost:4723/";

    private AppiumDriver driver;

    private void setUpAndroid() throws Exception {
        // FIX 5: Use modern options config with implicit W3C formatting
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion("10")
                .setDeviceName("Android Emulator")
                .setAutomationName("UiAutomator2")
                .setApp(APP_ANDROID);

        driver = new AndroidDriver(URI.create(APPIUM).toURL(), options);
    }

    private void setUpIOS() throws Exception {
        // FIX 6: Use modern options config with implicit W3C formatting
        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName("iOS")
                .setPlatformVersion("12.0")
                .setDeviceName("iPhone X")
                .setAutomationName("XCUITest")
                .setApp(APP_IOS);

        driver = new IOSDriver(URI.create(APPIUM).toURL(), options);
    }

    @Before
    public void setUp() throws Exception {
//        setUpAndroid();
        setUpIOS();

        // FIX 7: Use Duration instead of TimeUnit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Nullable
    private String getWebContext(AppiumDriver driver) {
        // FIX 8: Cast AppiumDriver to SupportsContextSwitching to get context lists in 9.x
        if (driver instanceof SupportsContextSwitching) {
            ArrayList<String> contexts = new ArrayList<>(((SupportsContextSwitching) driver).getContextHandles());
            for (String context : contexts) {
                if (!context.equals("NATIVE_APP")) {
                    return context;
                }
            }
        }
        return null;
    }

    @Test
    public void testHybridApp() throws InterruptedException {
        // FIX 9: Swapped out short locator wrapper for standard AppiumBy syntax
        driver.findElement(AppiumBy.accessibilityId("Webview Demo")).click();
        Thread.sleep(1000);

        // FIX 10: Cast driver configuration to switch to the target web view context
        String webContext = getWebContext(driver);
        if (webContext != null && driver instanceof SupportsContextSwitching) {
            ((SupportsContextSwitching) driver).context(webContext);
        }

        driver.get("https://cloudgrey.io");
        assert driver.getTitle().equals("Cloud Grey: Appium Delivered");
    }
}
