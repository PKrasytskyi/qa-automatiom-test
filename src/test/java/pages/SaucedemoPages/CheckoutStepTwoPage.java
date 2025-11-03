package pages.SaucedemoPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class CheckoutStepTwoPage {

    private final WebDriver driver;
    private final CalcPrice calcPrice;
    private By tax = By.className("summary_tax_label");

    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
        this.calcPrice = new CalcPrice(driver);
    }

    //Locators
    private By totalPrice = By.className("summary_total_label");

    public Map<String, Double> getPriceOfItem(String... items){
        Map<String, Double> result = new HashMap<>();

        for (String item : items) {

            WebElement cartItem = driver.findElement
                    (By.xpath("//div[@class='cart_item'][.//div[@class='inventory_item_name' and text()='" + item + "']]"));

            double price = calcPrice.parsePrice(cartItem.findElement(By.className("inventory_item_price")).getText());

            result.put(item, price);

        }
        return result;
    }

    public double getTotalPrice(){
        return calcPrice.parsePrice(driver.findElement(totalPrice).getText());
    }

    public double calculateExpectedTotal(String... items){
        Map<String, Double> prices = getPriceOfItem(items);

        double totalPrice = prices.values().stream().mapToDouble(Double::doubleValue).sum();
        double displayedTax = getDisplayedTax();

        return calcPrice.roundUp(totalPrice + displayedTax, 2);
    }

//    public double getPriceWithoutTax(String... items){
//        Map<String, Double> prices = getPriceOfItem(items);
//        return prices.values().stream().mapToDouble(Double::doubleValue).sum();
//    }

    public double getDisplayedTax(){
        return Double.parseDouble(driver.findElement(tax)
                .getText().replaceAll("[^0-9.]", ""));
    }
}
