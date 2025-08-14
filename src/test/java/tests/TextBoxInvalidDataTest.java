package tests;

import Pages.TextBoxPage;
import TestSetUp.BaseTestAbstract;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextBoxInvalidDataTest extends BaseTestAbstract {

    @Override
    @Test
    public void runTest(){

        TextBoxPage textBoxPage = new TextBoxPage(driver);

        textBoxPage
                .open()
                .setFullName("Ptr Kr")
                .setEmail("assad-sdad")
                .clickSubmit();

        Assert.assertTrue(textBoxPage.getEmailOutAler().contains("field-error"), "Email field is not marked as error");
        System.out.println(textBoxPage.getEmailOutAler());
    }
}
