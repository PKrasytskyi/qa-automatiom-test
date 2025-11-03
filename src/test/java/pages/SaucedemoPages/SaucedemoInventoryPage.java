package pages.SaucedemoPages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


public class SaucedemoInventoryPage {

    private final WebDriver driver;

    public SaucedemoInventoryPage(WebDriver driver){
        this.driver = driver;
    }

    public SaucedemoInventoryPage open(){
        driver.get("https://www.saucedemo.com/");
        return this;
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    //Locators
    private final By loginInputField = By.id("user-name");
    private final By passwordInputField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By cart = By.className("shopping_cart_link");

    public SaucedemoInventoryPage login(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            LoginData loginData = mapper.readValue(new File("src/test/resources/testData/logPassDataInventPage.json"),
                    LoginData.class);
            driver.findElement(loginInputField).sendKeys(loginData.getLogin());
            driver.findElement(passwordInputField).sendKeys(loginData.getPassword());
            driver.findElement(loginButton).click();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public static class LoginData {
        private String login;
        private String password;

        public String getLogin() {
            return login;
        }
        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }

    public SaucedemoInventoryPage addItemByName(String... itemNames) {

        for (String itemName : itemNames) {

            WebElement el = driver.findElement
                    (By.xpath("//div[@class='inventory_item'][.//div[@class='inventory_item_name ' and text()='" + itemName +"']]"));
            WebElement addButton = el.findElement(By.xpath(".//button[@class='btn btn_primary btn_small btn_inventory ']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
            addButton.click();
        }
        return this;
}

    public  SaucedemoInventoryPage  clickAlert(){
        driver.switchTo().alert().accept();
        return this;

    }

    public void clickCart(){
        WebElement el = driver.findElement(cart);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", el);
        el.click();
    }
}
