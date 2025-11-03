package tests;

import data.DemoqaDataProviders.TextBoxDataProvider;
import pages.Demoqa.pages.TextBoxPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.BaseTest;

public class TextBoxInvalidDataTest extends BaseTest {

    @Test(dataProvider = "InvalidData", dataProviderClass = TextBoxDataProvider.class, priority = 2)
    public void runTest(String fullName, String email, String currentAddress, String permanentAddress){

        TextBoxPage textBoxPage = new TextBoxPage(driver);

        textBoxPage
                .open()
                .setUpForm(fullName, email, currentAddress, permanentAddress)
                .clickSubmit();

        Assert.assertTrue(textBoxPage.getEmailOutAlert().contains("field-error"), "Email field is not marked as error");
        System.out.println(textBoxPage.getEmailOutAlert());
    }
}
