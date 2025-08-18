package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SelectMenuPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SelectMenuPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    // Open Select Menu page and scroll to the middle
    public SelectMenuPage open(){
        driver.get("https://demoqa.com/select-menu");
        scrollToMiddle(0.25);
        return this;
    }
    // Locators
    private final By selectValueDropDownMenu = By.xpath("//*[@id=\"withOptGroup\"]/div/div[1]");
    private final By selectOneDropDownMenu = By.xpath("//*[@id=\"selectOne\"]/div/div[1]");
    private final By oldStyleDropDownMenu = By.id("oldSelectMenu");
    private final By standartMultiSelect = By.id("cars");

    // Scroll page to a fraction of total height
    private void scrollToMiddle(double fraction) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight * arguments[0])", fraction);
    }

    // Select a value from "Select Value" dropdown
    public SelectMenuPage selectValueOfDropDownMenu(String value){
        wait.until(ExpectedConditions.elementToBeClickable(selectValueDropDownMenu)).click();
        WebElement option = wait
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id='withOptGroup']//div[text()='" + value + "']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
        return this;
    }

    // Get currently selected value from "Select Value" dropdown
    public String getSelectedValue(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='withOptGroup']/div/div[1]//div[contains(@class,'singleValue')]")
        )).getText();
    }

    // Select a value from "Select One" dropdown
    public SelectMenuPage selectOneOfDropDownMenu(String value){
        wait.until(ExpectedConditions.elementToBeClickable(selectOneDropDownMenu)).click();
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='selectOne']//div[text()='" + value + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", option);
        return this;
    }

    // Get currently selected value from "Select One" dropdown
    public String getOneSelectedValue(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id='selectOne']/div/div[1]//div[contains(@class,'singleValue')]")))
                .getText();
    }

    // Select a value from old-style HTML <select>
    public SelectMenuPage selectOldStyleDropDownMenu(String text){
        WebElement dropDown = driver.findElement(oldStyleDropDownMenu);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
        return this;
    }

    // Get currently selected value from old-style HTML <select>
    public String getOldStyleSelectMenuValue(){
        WebElement dropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"oldSelectMenu\"]")));
        Select select = new Select(dropDown);
        return select.getFirstSelectedOption().getText();

    }

    // Select multiple values in React multi-select
    public SelectMenuPage multiselectDropDownMenu(String... values) {
        By input = By.cssSelector("#react-select-4-input"); // Input field for typing values

        for (String value : values) {
            WebElement inputBox = wait.until(ExpectedConditions.elementToBeClickable(input));
            inputBox.sendKeys(value); // Type the value

            // Wait for option to appear
            String optionXpath = "//div[contains(@id,'react-select') and text()='" + value + "']";
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));

            // Click using JS to avoid ElementClickInterceptedException
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

            // Clear input for the next value
            inputBox.clear();
        }
        return this;
    }

    // Get list of selected values from React multi-select
    public List<String> getMultiSelectMenuValues(){
        List<WebElement> selectedElements = driver.findElements(
                By.xpath("//*[@id='selectMenuContainer']//div[contains(@class,'css-1rhbuit-multiValue')]"));

        return selectedElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    // Select multiple values in standard HTML multi-select
    public SelectMenuPage standartMultiSelect(String text1, String text2){
        WebElement multiSelect = driver.findElement(standartMultiSelect);
        Select select = new Select(multiSelect);
        select.selectByVisibleText(text1);
        select.selectByVisibleText(text2);
        return this;

    }

}
