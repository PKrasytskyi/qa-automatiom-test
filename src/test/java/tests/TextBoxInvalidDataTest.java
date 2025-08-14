package tests;

import Data.TextBoxDataProvider;
import Pages.TextBoxPage;
import TestSetUp.BaseTestAbstract;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextBoxInvalidDataTest extends BaseTestAbstract {

    @Test(dataProvider = "InvalidData", dataProviderClass = TextBoxDataProvider.class)
    public void runTest(String fullName, String email, String currentAddress, String permanentAddress){

        TextBoxPage textBoxPage = new TextBoxPage(driver);

        textBoxPage
                .open()
                .setUpForm(fullName, email, currentAddress, permanentAddress)
                .clickSubmit();

        Assert.assertTrue(textBoxPage.getEmailOutAler().contains("field-error"), "Email field is not marked as error");
        System.out.println(textBoxPage.getEmailOutAler());
    }

    @Override
    public void runTest() {}
}
