package TestSetUp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public abstract class BaseTestAbstract {

    protected WebDriver driver;
    private static final Logger logger = Logger.getLogger(BaseTestAbstract.class.getName());


    @BeforeMethod
    public void setUp() {
        try {
            //setUp logger
            File logDir = new File("log");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            FileHandler fileHandler = new FileHandler("log/test_log.log", true);
            logger.addHandler(fileHandler);
            logger.info("Test started: " + this.getClass().getSimpleName());


            //init webdriver
            System.setProperty("webdriver.chrome.driver", "D:\\qa-demo-automation\\chromedriver-win64\\chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            logger.info("Test finished: " + this.getClass().getSimpleName());
            takeScreenshot();
            //driver.quit();
        }
    }

    //Screenshot method
    public void takeScreenshot(){

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenshotFile, new File("screenshots/screenshot_" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //abstract method for tests
    public abstract void runTest() throws InterruptedException;
}
