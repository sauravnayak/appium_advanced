import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import java.io.File;
import java.io.IOException;
import java.net.URI; // FIX 1: Use URI instead of URL to avoid deprecated constructors

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;

public class Ch_03_04_Orientation_Etc_After {
    private static final String APP_IOS = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.app.zip";

    // FIX 2: Point directly to the standard Appium 2.x server root path without "/wd/hub"
    private static final String APPIUM = "http://localhost:4723/";

    // FIX 3: Change variable type to IOSDriver to directly expose orientation/rotation methods
    private IOSDriver driver;

    @Before
    public void setUp() throws Exception {
        XCUITestOptions caps = new XCUITestOptions();

        // FIX 4: Explicitly map parameters cleanly to modern W3C/XCUITest requirements
        caps.setPlatformName("iOS");
        caps.setPlatformVersion("12.0");
        caps.setDeviceName("iPhone X");
        caps.setApp(APP_IOS);

        // FIX 5: Instantiate using modern URI protocol
        driver = new IOSDriver(URI.create(APPIUM).toURL(), caps);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testScreenMethods() throws IOException {
        // This natively compiles now because driver is explicitly initialized as an IOSDriver
        ScreenOrientation curOrientation = driver.getOrientation();
        System.out.println("Current Orientation: " + curOrientation);

        if (curOrientation != ScreenOrientation.LANDSCAPE) {
            driver.rotate(ScreenOrientation.LANDSCAPE);
        }

        Dimension size = driver.manage().window().getSize();
        System.out.println("Window Size: " + size);

        File screenshot = driver.getScreenshotAs(OutputType.FILE);

        // RECOMMENDATION: Update this file directory string to your local Macbook user directory
        // (e.g., "/Users/saurav/Desktop/screen.png") to prevent directory permission errors.
        File saveFile = new File("/Users/saurav/Desktop/screen.png");
        FileUtils.copyFile(screenshot, saveFile);

        driver.rotate(ScreenOrientation.PORTRAIT);
    }
}
