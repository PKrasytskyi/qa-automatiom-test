package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebTablesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Map<String, Integer> columIndexMap;

    public WebTablesPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.columIndexMap = new HashMap<>();
    }

    public WebTablesPage open(){
        driver.get("https://demoqa.com/webtables");
        scrollToMiddle(0.25);
        initColumMap();
        return this;
    }

    private void initColumMap(){
        List<WebElement> header = driver.findElements(By.xpath("//div[@class='rt-th']"));
        for (int i = 0; i < header.size(); i++){
            String headerText = header.get(i).getText().trim();
            columIndexMap.put(headerText, i + 1);
        }
    }

    private final By addButton = By.xpath("//*[@id=\"addNewRecordButton\"]");
    private final By firstnameInp = By.xpath("//*[@id=\"firstName\"]");
    private final By lastNameInp = By.xpath("//*[@id=\"lastName\"]");
    private final By emailInp = By.xpath("//*[@id=\"userEmail\"]");
    private final By ageInp = By.xpath("//*[@id=\"age\"]");
    private final By salaryInp = By.xpath("//*[@id=\"salary\"]");
    private final By departmentInt = By.xpath("//*[@id=\"department\"]");
    private final By submitButton = By.xpath("//*[@id=\"submit\"]");

    public WebTablesPage clickAddButton(){
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        return this;
    }

    private void scrollToMiddle(double fraction) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight * arguments[0])", fraction);
    }

    public WebTablesPage setUpForm(Map<String, String> userData){
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstnameInp)).sendKeys(userData.get("firstName"));
        driver.findElement(lastNameInp).sendKeys(userData.get("lastName"));
        driver.findElement(ageInp).sendKeys(userData.get("age"));
        driver.findElement(emailInp).sendKeys(userData.get("email"));
        driver.findElement(salaryInp).sendKeys(userData.get("salary"));
        driver.findElement(departmentInt).sendKeys(userData.get("department"));
        return this;
    }

    public void clickSubmit(){
       WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
       submit.click();
    }

//    public Map<String, Integer> getColumnIndexMap() {
//        Map<String, Integer> map = new HashMap<>();
//        List<WebElement> headers = driver.findElements(By.xpath("//div[@class='rt-thead -header']//div[@role='columnheader']"));
//        for (int i = 0; i < headers.size(); i++) {
//            String colName = headers.get(i).getText().trim();
//            map.put(colName, i);
//        }
//        return map;
//    }

    public String getCellValue(int rowIndex, String columnName) {
        List<WebElement> headers = driver.findElements(By.cssSelector(".rt-th"));
        Map<String, Integer> headerMap = new HashMap<>();

        for (int i = 0; i < headers.size(); i++) {
            headerMap.put(headers.get(i).getText().trim(), i);
        }

        if (!headerMap.containsKey(columnName)) {
            throw new IllegalArgumentException("Column `" + columnName + "` not found. Available: " + headerMap.keySet());
        }

        List<WebElement> rows = driver.findElements(By.cssSelector(".rt-tr-group"));
        WebElement row = rows.get(rowIndex);
        List<WebElement> cells = row.findElements(By.cssSelector(".rt-td"));

        return cells.get(headerMap.get(columnName)).getText();
    }
}

