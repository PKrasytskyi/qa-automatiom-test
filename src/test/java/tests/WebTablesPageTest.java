package tests;

import Data.WebTextBoxDataProvider;
import Pages.WebTablesPage;
import TestSetUp.BaseTestAbstract;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;


public class WebTablesPageTest extends BaseTestAbstract {

    @Test(dataProvider = "formData", dataProviderClass = WebTextBoxDataProvider.class, priority = 3)
    public void runTest(Map<String, String> userData) {
        WebTablesPage webTablesPage = new WebTablesPage(driver);

        webTablesPage
                .open()
                .clickAddButton()
                .setUpForm(userData)
                .clickSubmit();

        Assert.assertEquals(webTablesPage.getCellValue(3, "First Name"), userData.get("firstName"));
        Assert.assertEquals(webTablesPage.getCellValue(3, "Last Name"), userData.get("lastName"));
        Assert.assertEquals(webTablesPage.getCellValue(3, "Age"), userData.get("age"));
        Assert.assertEquals(webTablesPage.getCellValue(3, "Email"), userData.get("email"));
        Assert.assertEquals(webTablesPage.getCellValue(3, "Salary"), userData.get("salary"));
        Assert.assertEquals(webTablesPage.getCellValue(3, "Department"), userData.get("department"));

    }
    @Override
    public void runTest() {}
}
