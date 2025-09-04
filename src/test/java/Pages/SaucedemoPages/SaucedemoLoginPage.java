package Pages.SaucedemoPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SaucedemoLoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public SaucedemoLoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public SaucedemoLoginPage openPage(){
        driver.get("https://www.saucedemo.com/");
        return this;
    }

    //Locators
    private final By loginInputField = By.id("user-name");
    private final By passwordInputField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector(".error-message-container.error");

    public SaucedemoLoginPage setUserName(String userName){
        driver.findElement(loginInputField).sendKeys(userName);
        return this;
    }

    public SaucedemoLoginPage setPassword(String userPassword){
        driver.findElement(passwordInputField).sendKeys(userPassword);
        return this;
    }

    public SaucedemoLoginPage clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return this;
    }

    public String getErrorMessage(){
        return driver.findElement(errorMessage).getText();
    }

    public SaucedemoInventoryPage toInventoryPage(){
        driver.findElement(loginButton).click();
        return new SaucedemoInventoryPage(driver);
    }
}
