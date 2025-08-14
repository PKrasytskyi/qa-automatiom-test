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


    public WebTablesPage clickAddButton(){
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        return this;
    }

    public WebTablesPage setFirstname(String firstname){
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstnameInp)).sendKeys(firstname);
        return this;
    }

    public WebTablesPage setLastName(String lastName){
        driver.findElement(lastNameInp).sendKeys(lastName);
        return this;
    }

    public WebTablesPage setEmail(String email){
        driver.findElement(emailInp).sendKeys(email);
        return this;
    }

    public WebTablesPage setAge(String age){
        driver.findElement(ageInp).sendKeys(age);
        return this;
    }

    public WebTablesPage setSalary (String salary){
        driver.findElement(salaryInp).sendKeys(salary);
        return this;
    }

    public WebTablesPage setDepartment(String department){
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

    public WebElement getWebTable(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(webTable));
    }
}
