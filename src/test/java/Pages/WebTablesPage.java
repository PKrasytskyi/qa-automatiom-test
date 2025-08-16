package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebTablesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public WebTablesPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public WebTablesPage open(){
        driver.get("https://demoqa.com/webtables");
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
    private final By closeButtonOfRegistrationForm = By.xpath("/html/body/div[4]/div/div/div[1]/button/span[1]");

    private final By webTable = By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]");
    private final By searchField = By.id("searchBox");

//    private final By getFirstnameInp = By
//            .xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/div/div[1]");
//    private final By getLastNameInp = By
//            .xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/div/div[2]");
//    private final By getEmailInp = By
//            .cssSelector("#app > div > div > div > div.col-12.mt-4.col-md-6 > div.web-tables-wrapper > div.ReactTable.-striped.-highlight > div.rt-table > div.rt-tbody > div:nth-child(1) > div > div:nth-child(4)");
//    private final By getAgeInp = By
//            .xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/div/div[3]");
//    private final By getSalaryInp = By
//            .xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/div/div[5]");
//    private final By getDepartmentInt = By
//            .xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/div/div[6]");
//
//    private final By getRow = By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/div");

    //private final By deleteActionButton = By.xpath("");


    public WebTablesPage clickAddButton(){
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        return this;
    }

    public WebTablesPage setUpForm(String firstName, String lastName, String age, String email, String salary, String department){
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstnameInp)).sendKeys(firstName);
        driver.findElement(lastNameInp).sendKeys(lastName);
        driver.findElement(ageInp).sendKeys(age);
        driver.findElement(emailInp).sendKeys(email);
        driver.findElement(salaryInp).sendKeys(salary);
        driver.findElement(departmentInt).sendKeys(department);
        return this;
    }

    public WebTablesPage clickSubmit(){
       WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
       submit.click();
       return this;
    }
    public WebTablesPage clickCloseButtonOfRegistrationForm(){
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(closeButtonOfRegistrationForm));
        closeButton.click();
        return this;
    }

    public void searchField(String searchInp){
        wait.until(ExpectedConditions.elementToBeClickable(searchField)).click();
        driver.findElement(searchField).sendKeys(searchInp);
    }

    public String getElementText(By locator, boolean waitForVisible){
        if(waitForVisible){
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        }
        return driver.findElement(locator).getText();
    }

    public String getCollumValue(int columnIndex) {
        By cellLocator = By.xpath("//*[@id='app']//div[@class='rt-tr-group'][1]//div[@class='rt-td'][" + columnIndex + "]");
                return wait.until(ExpectedConditions.visibilityOfElementLocated(cellLocator)).getText();
    }
}

