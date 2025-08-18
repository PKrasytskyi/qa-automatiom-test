package tests;

import Data.TextBoxDataProvider;
import Pages.TextBoxPage;
import TestSetUp.BaseTestAbstract;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Demo")
@Feature("TextBoxPage")
public class TextBoxValidDataTest extends BaseTestAbstract {

    @Test(dataProvider = "formData", dataProviderClass = TextBoxDataProvider.class, priority = 1)
    @Story("Check Valid Data")
    @Description("Check input valid data on the TextBoxPage")
    public void runTest(String fullName, String email, String currentAddress, String permanentAddress) {

        TextBoxPage textBoxPage = new TextBoxPage(driver);

        textBoxPage
                .open()
                .setUpForm(fullName, email, currentAddress, permanentAddress)
                .clickSubmit();

        WebElement element = driver.findElement(By.id("submit"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        Assert.assertEquals(textBoxPage.getFullNameOut(), "Name:" + fullName, "Name mismatch");
        Assert.assertEquals(textBoxPage.getEmailOut(), "Email:" + email, "Email mismatch");
        Assert.assertTrue(textBoxPage.getCurrentAddressOut().contains("Current Address :" + currentAddress), "Current address mismatch");
        Assert.assertTrue(textBoxPage.getPermanentAddressOut().contains("Permananet Address :" + permanentAddress), "Permanent address mismatch");
    }

    @Override
    public void runTest() {}
}
