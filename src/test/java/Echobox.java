import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Echobox {

    private static final String APP = "/Users/rahullokurte/Desktop/TheApp-v1.9.0.apk";
    private static final String APPIUM = "http://localhost:4723/wd/hub";

    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", APP);
        driver = new AndroidDriver(new URL(APPIUM), caps);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement echobox = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Echo Box")));
        echobox.click();

        WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("messageInput")));

        message.sendKeys("Hello Alice");

        WebElement save = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("messageSaveBtn")));
        save.click();

        WebElement savedmessage = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.TextView[contains(@text,'Hello')]")));

        assert(savedmessage.getText().contains("Alice"));
    }
}
