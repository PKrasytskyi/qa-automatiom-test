package setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.AllureListener;

import java.util.logging.Logger;

@Listeners({AllureListener.class})
public abstract class BaseTestAbstract {

    protected WebDriver driver;
    private static final Logger logger = Logger.getLogger(BaseTestAbstract.class.getName());

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        logger.info("Test started: " + this.getClass().getSimpleName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (ITestResult.FAILURE == result.getStatus()) {
                saveScreenshot(); //save only when test failed
            }
            logger.info("Test finished: " + this.getClass().getSimpleName());
            driver.quit();
        }
    }

    //Screenshot method
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
