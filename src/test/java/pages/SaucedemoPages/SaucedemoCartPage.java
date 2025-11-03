package Pages.SaucedemoPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SaucedemoCartPage {

    private WebDriver driver;


    public SaucedemoCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public SaucedemoCartPage open(){
        driver.get("https://www.saucedemo.com/cart.html");
        return this;
    }

    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public List<String> getItemNameFromCart(){
        List<WebElement> el = driver.findElements
                (By.xpath("//div[@class='cart_item']//div[@class='inventory_item_name']"));

        return el.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        }

        public Map<String, String> getItemQuantity(String... items){

        Map<String, String> result = new HashMap<>();

        for (String item : items){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cartItem = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@class='cart_item'][.//div[@class='inventory_item_name' and text()='" + item + "']]")
                    ));

            String name = cartItem.findElement(By.className("inventory_item_name")).getText().trim();
            String quantity = cartItem.findElement(By.className("cart_quantity")).getText().trim();

            result.put(name, quantity);
        }

        return result;
        }

        public void clickRemoveButton(String... items){

            for(String item : items) {
                WebElement cartItem = new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//div[@class='cart_item'][.//div[@class='inventory_item_name' and text()='" + item + "']]")
                        ));


                WebElement removeButton = cartItem.findElement(By.xpath(".//button[@class='btn btn_secondary btn_small cart_button']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", removeButton);
            }
        }

        public void clickCheckoutButton(){
        driver.findElement(checkoutButton).click();
        }
}


