package pages.Demoqa.pages;

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

public class WebTablesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private int lastFieledRow;

    public WebTablesPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public WebTablesPage open(){
        driver.get("https://demoqa.com/webtables");
        scrollToMiddle(0.25);
        return this;
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

    public WebTablesPage editFirstName(String editedFirstName){
        WebElement editFirstName = driver.findElement(firstnameInp);
        editFirstName.clear();
        editFirstName.sendKeys(editedFirstName);
        return this;
    }

    public WebTablesPage editEmail(String editedEmail){
        WebElement editEmail = driver.findElement(emailInp);
        editEmail.clear();
        editEmail.sendKeys(editedEmail);
        return this;
    }

    public WebTablesPage clickSubmit(){
       WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
        submit.click();
       return this;
    }

    public int checkLastFilledRow(){
       List<WebElement> rows = driver.findElements(By.cssSelector(".rt-tr-group"));
       for (int i = 0; i < rows.size(); i++){
           String text = rows.get(i).getText().trim();
           if(!text.isEmpty()){
               lastFieledRow = i;

           }

           if (lastFieledRow == -1) {
               throw new RuntimeException("Таблиця порожня – немає заповнених рядків!");
           }
       }
        return lastFieledRow;
    }

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

    public WebTablesPage clickEditButton(String action){

        WebElement row = driver.findElements(By.cssSelector(".rt-tr-group")).get(checkLastFilledRow());
        WebElement editButton = row.findElement(By.cssSelector("span[title='" + action + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editButton);
        editButton.click();
        return this;
    }

    public WebTablesPage clickDeleteButton(String action){
        int rowIndex = checkLastFilledRow();
        WebElement row = driver.findElements(By.cssSelector(".rt-tr-group")).get(rowIndex);
        WebElement deleteButton = row.findElement(By.cssSelector("span[title='" + action + "']"));
        deleteButton.click();
        return this;
        }
}
