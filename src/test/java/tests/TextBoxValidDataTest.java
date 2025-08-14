package tests;

import Pages.TextBoxPage;
import TestSetUp.BaseTestAbstract;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextBoxValidDataTest extends BaseTestAbstract {

    @Override
    @Test
    public void runTest() {

        TextBoxPage textBoxPage = new TextBoxPage(driver);


        String fullName = "Ptr Kr";
        String email = "testtext@gmail.com";
        String currentAddress = "Dream City";
        String permanentAddress = "Sad City";

        textBoxPage
                .open()
                .setFullName(fullName)
                .setEmail(email)
                .setCurrentAddres(currentAddress)
                .setPermanentAddress(permanentAddress)
                .clickSubmit();

        WebElement element = driver.findElement(By.id("submit"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        Assert.assertEquals(textBoxPage.getFullNameOut(), "Name:" + fullName, "Name mismatch");
        Assert.assertEquals(textBoxPage.getEmailOut(), "Email:" + email, "Email mismatch");
        Assert.assertTrue(textBoxPage.getCurrentAddressOut().contains("Current Address :" + currentAddress), "Current address mismatch");
        Assert.assertTrue(textBoxPage.getPermanentAddressOut().contains("Permananet Address :" + permanentAddress), "Permanent address mismatch");
    }
}
