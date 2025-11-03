package pages.SaucedemoPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcPrice {

    private WebDriver driver;


    public CalcPrice(WebDriver driver) {
        this.driver = driver;
    }

    //Collect text and convert to double
    public double getPriceFromElement(By locator){
        WebElement element = driver.findElement(locator);
        return parsePrice(element.getText());
    }

    //Parse price from text
    public double parsePrice (String priceText){
        return Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
    }

    //round up price
    public double roundUp(double value, int scale){
        return new BigDecimal(value).setScale(scale, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public static BigDecimal toPrice(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
    }
}
