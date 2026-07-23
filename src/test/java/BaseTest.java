import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.junit.After;
import org.junit.Before;

import javax.annotation.Nullable;
import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;

public class BaseTest {
    private static final String APP_ANDROID = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
    private static final String APP_IOS = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.app.zip";

    // FIX 1: Append standard Appium port 4723
    private static final String APPIUM = "http://127.0.0";
    // Strongly typed driver handles Android natively
    public AndroidDriver driver;

    private void setUpAndroid() throws Exception {
        UiAutomator2Options caps = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion("13") // Change to match your actual phone's OS version
                .setUdid("9B071FFAZ001VR")
                .setAutomationName("UiAutomator2")
                .setApp(APP_ANDROID)
                .setAppWaitDuration(Duration.ofMillis(60000))
                .setNoReset(false)
                .setChromedriverExecutable("/Users/saurav/chromedriver/mac-149.0.7827.155/chromedriver-mac-x64/chromedriver");


        // HARDCODE DIRECTLY HERE FOR TROUBLESHOOTING:
        driver = new AndroidDriver(URI.create("http://localhost:4723/").toURL(), caps);
    }


    private void setUpIOS() throws Exception {
        // FIX 2: Modernized iOS configuration to use XCUITestOptions instead of DesiredCapabilities
        XCUITestOptions caps = new XCUITestOptions()
                .setPlatformName("iOS")
                .setPlatformVersion("12.0")
                .setDeviceName("iPhone X")
                .setApp(APP_IOS);

        // Note: Assigning this requires updating 'driver' type declaration to AppiumDriver or IOSDriver
        // driver = new IOSDriver(URI.create(APPIUM).toURL(), caps);
    }

    @Before
    public void setUp() throws Exception {
        setUpAndroid();
//        setUpIOS();

        // FIX 3: Replaced deprecated TimeUnit configuration with standard W3C Duration
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // FIX 4: Changed signature from AppiumDriver to AndroidDriver to access context handles natively
    @Nullable
    public String getWebContext(AndroidDriver driver) {

        ArrayList<String> contexts = new ArrayList<>(driver.getContextHandles());
        for (String context : contexts) {
            if (!context.equals("NATIVE_APP") && context.contains("WEBVIEW_io")) {
                return context;
            }
        }
        return null;
    }
}
