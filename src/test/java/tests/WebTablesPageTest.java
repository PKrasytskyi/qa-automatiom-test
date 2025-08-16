package tests;

import Data.WebTextBoxDataProvider;
import Pages.WebTablesPage;
import TestSetUp.BaseTestAbstract;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.Map;


public class WebTablesPageTest extends BaseTestAbstract {

    private WebTablesPage webTablesPage;

    @BeforeMethod
    public void setUpPage() {
        webTablesPage = new WebTablesPage(driver);
    }

    @Test(dataProvider = "formData", dataProviderClass = WebTextBoxDataProvider.class, priority = 3)
    public void WebTableAddRow(Map<String, String> userData) {

        webTablesPage
                .open()
                .clickAddButton()
                .setUpForm(userData)
                .clickSubmit();


        Assert.assertEquals(webTablesPage.getCellValue(webTablesPage.checkLastFilledRow(), "First Name"), userData.get("firstName"));
        Assert.assertEquals(webTablesPage.getCellValue(webTablesPage.checkLastFilledRow(), "Last Name"), userData.get("lastName"));
        Assert.assertEquals(webTablesPage.getCellValue(webTablesPage.checkLastFilledRow(), "Age"), userData.get("age"));
        Assert.assertEquals(webTablesPage.getCellValue(webTablesPage.checkLastFilledRow(), "Email"), userData.get("email"));
        Assert.assertEquals(webTablesPage.getCellValue(webTablesPage.checkLastFilledRow(), "Salary"), userData.get("salary"));
        Assert.assertEquals(webTablesPage.getCellValue(webTablesPage.checkLastFilledRow(), "Department"), userData.get("department"));

    }

    @Test(dataProvider = "formData", dataProviderClass = WebTextBoxDataProvider.class, priority = 4)
    public void EditRow(Map<String, String> userData){

            String editedFirstName = "tron";
            String editedEmail = "editedemail@gmail.com";

        webTablesPage
                .open()
                .clickAddButton()
                .setUpForm(userData)
                .clickSubmit()
                .clickActionButton("Edit")
                .editFirstName(editedFirstName)
                .editEmail(editedEmail)
                .clickSubmit();

        Assert.assertEquals(webTablesPage.getCellValue(webTablesPage.checkLastFilledRow(), "First Name"), editedFirstName);
        Assert.assertEquals(webTablesPage.getCellValue(webTablesPage.checkLastFilledRow(), "Email"), editedEmail);
    }

    @Override
    public void runTest() {

    }
}