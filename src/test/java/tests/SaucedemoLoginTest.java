package tests;

import data.SaucedemoDataProviders.SaucedemoLoginPageDataProvider;
import pages.SaucedemoPages.SaucedemoInventoryPage;
import pages.SaucedemoPages.SaucedemoLoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.BaseTest;


public class SaucedemoLoginTest extends BaseTest  {

    private SaucedemoLoginPage loginPage;
    private SaucedemoInventoryPage inventoryPage;

    @BeforeMethod
    public void setUpPage(){
        loginPage = new SaucedemoLoginPage(driver);
        inventoryPage = new SaucedemoInventoryPage(driver);
    }


    @Test(dataProvider = "successfulLogin", dataProviderClass = SaucedemoLoginPageDataProvider.class, priority = 1)
    public void successfulLogin(String userLogin, String userPassword){

        inventoryPage = loginPage
                .openPage()
                .setUserName(userLogin)
                .setPassword(userPassword)
                .toInventoryPage();


        Assert.assertTrue(inventoryPage.getCurrentUrl().contains("inventory"));
    }

    @Test(dataProvider = "invalidPassword", dataProviderClass = SaucedemoLoginPageDataProvider.class, priority = 2)
    public void invalidLogin(String userLogin, String userPassword){

        String errorMessage = "Epic sadface: Username and password do not match any user in this service";

        loginPage
                .openPage()
                .setUserName(userLogin)
                .setPassword(userPassword)
                .clickLoginButton()
                .getErrorMessage();

        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage, "Error message missed");
        System.out.println(loginPage.getErrorMessage());
    }
}
