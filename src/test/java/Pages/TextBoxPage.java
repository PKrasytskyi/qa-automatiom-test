package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TextBoxPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public TextBoxPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public TextBoxPage open(){
        driver.get("https://demoqa.com/text-box");
        return this;
    }

    private final By fullNameInp = By.xpath("//*[@id=\"userName\"]");
    private final By emailInp = By.xpath("//*[@id=\"userEmail\"]");
    private final By currentAddressInp = By.xpath("//*[@id=\"currentAddress\"]");
    private final By permanentAddressInp = By.xpath("//*[@id=\"permanentAddress\"]");
    private final By submitButton = By.id("submit");

    private final By fullNameOut = By.xpath("//*[@id=\"name\"]");
    private final By emailOut = By.xpath("//*[@id=\"email\"]");
    private final By currentAddressOut = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[6]/div/p[3]");
    private final By permanentAddressOut = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[6]/div/p[4]");



    public TextBoxPage setFullName(String name){
        wait.until(ExpectedConditions.visibilityOfElementLocated(fullNameInp)).sendKeys(name);
         return this;
    }

    public TextBoxPage setEmail(String email){
        driver.findElement(emailInp).sendKeys(email);
        return this;
    }

    public TextBoxPage setCurrentAddres(String currentAddres){
        driver.findElement(currentAddressInp).sendKeys(currentAddres);
        return this;
    }

    public TextBoxPage setPermanentAddress(String permanentAddress){
        driver.findElement(permanentAddressInp).sendKeys(permanentAddress);
        return this;
    }

    public TextBoxPage clickSubmit(){
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Скрол на половину висоти сторінки
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 4)");
        //WebElement element = driver.findElement(By.id("submit"));
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].scrollIntoView(true);", element);
        submit.click();
        return this;

    }

    public String getFullNameOut(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(fullNameOut)).getText();
    }

    public String getEmailOut(){
        return driver.findElement(emailOut).getText();
    }

    public String getEmailOutAler(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[2]/div[2]/input")))
                .getAttribute("class");
        //return driver.findElement(By.xpath("//*[@id=\"userEmail\"]")).getAttribute("class");
    }

    public String getCurrentAddressOut(){
        return driver.findElement(currentAddressOut).getText();
    }

    public String getPermanentAddressOut(){
        return driver.findElement(permanentAddressOut).getText();
    }

    public String getEmailFieldClass(){
        return driver.findElement(emailOut).getAttribute("class");
    }
}
