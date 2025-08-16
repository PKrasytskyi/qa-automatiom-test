package tests;

import Data.WebTextBoxDataProvider;
import Pages.WebTablesPage;
import TestSetUp.BaseTestAbstract;
import org.testng.Assert;
import org.testng.annotations.Test;



public class WebTablesPageTest extends BaseTestAbstract {

    @Test(dataProvider = "formData", dataProviderClass = WebTextBoxDataProvider.class, priority = 3)
    public void runTest(String firstName, String lastName, String age, String email,  String salary, String department) {
        WebTablesPage webTablesPage = new WebTablesPage(driver);

        webTablesPage
                .open()
                .clickAddButton()
                .setUpForm(firstName, lastName, email, age, salary, department)
                .clickSubmit()
                .searchField("Ptr");

        Assert.assertEquals(webTablesPage.getCollumValue(1), firstName);
        Assert.assertEquals(webTablesPage.getCollumValue(2), lastName);
        Assert.assertEquals(webTablesPage.getCollumValue(3), email);
        Assert.assertEquals(webTablesPage.getCollumValue(4), age);
        Assert.assertEquals(webTablesPage.getCollumValue(5), salary);
        Assert.assertEquals(webTablesPage.getCollumValue(6), department);


    }



    @Override
    public void runTest() {}
}
