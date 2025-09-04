package Pages.SaucedemoPages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class SaucedemoCheckoutPage {

    private WebDriver driver;

    public SaucedemoCheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By zipPostalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public SaucedemoCheckoutPage setUserData(){

        try {

            ObjectMapper mapper = new ObjectMapper();
            LoginData loginData = mapper.readValue(new File("src/test/resources/testData/chekoutUserData.json"),
                    LoginData.class);

            driver.findElement(firstName).sendKeys(loginData.firstName);
            driver.findElement(lastName).sendKeys(loginData.lastName);
            driver.findElement(zipPostalCode).sendKeys(loginData.zipPostalCode);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public static class LoginData{
        private String firstName;
        private String lastName;
        private String zipPostalCode;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getZipPostalCode() {
            return zipPostalCode;
        }

        public void setZipPostalCode(String zipPostalCode) {
            this.zipPostalCode = zipPostalCode;
        }
    }

    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }
}
