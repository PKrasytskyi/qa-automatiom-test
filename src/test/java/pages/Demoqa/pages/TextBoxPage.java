package pages.Demoqa.pages;

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
    //private final By submitButton = By.id("submit");
    private final By submitButton = By.xpath("//*[@id=\"submit\"]");

    private final By fullNameOut = By.xpath("//*[@id=\"name\"]");
    private final By emailOut = By.xpath("//*[@id=\"email\"]");
    private final By currentAddressOut = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[6]/div/p[3]");
    private final By permanentAddressOut = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[6]/div/p[4]");
    private final By emailOutAlert = By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[2]/div[2]/input");

    public TextBoxPage setUpForm(String name, String email, String currentAddress, String permanentAddress){
        wait.until(ExpectedConditions.visibilityOfElementLocated(fullNameInp)).sendKeys(name);
        driver.findElement(emailInp).sendKeys(email);
        driver.findElement(currentAddressInp).sendKeys(currentAddress);
        driver.findElement(permanentAddressInp).sendKeys(permanentAddress);
        return this;
    }
    private void scrollToMiddle(double fraction) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight * arguments[0])", fraction);
    }

    public void clickSubmit(){
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }

    private String getElementText(By locator, boolean waitForVisible){
        if(waitForVisible){
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        }
        return driver.findElement(locator).getText();
    }

    private String getElementAttribute(By locator, String attribute, boolean waitForVisible){
        if(waitForVisible){
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute(attribute);
        }
        return  driver.findElement(locator).getAttribute(attribute);
    }

    public String getFullNameOut(){
        return getElementText(fullNameOut, true);
    }

    public String getEmailOut(){
        return getElementText(emailOut, false);
    }

    public String getEmailOutAlert(){
    return getElementAttribute(emailOutAlert, "class", true);
    }

    public String getCurrentAddressOut(){
        return getElementText(currentAddressOut, false);
    }

    public String getPermanentAddressOut(){
        return getElementText(permanentAddressOut, false);
    }

}
