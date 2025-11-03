package tests;

import data.SaucedemoDataProviders.SaucInventoryDataProvider;
import pages.SaucedemoPages.*;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import setup.AllureListener;
import setup.BaseTest;

import java.math.BigDecimal;
import java.util.List;

import static pages.SaucedemoPages.CalcPrice.toPrice;

@Epic("Saucedemo QA Automation")
@Feature("Shopping Cart Tests")
@Listeners({AllureListener.class})
public class SaucedemoInventoryPageTests extends BaseTest {

    private SaucedemoInventoryPage inventoryPage;
    private SaucedemoCartPage cartPage;
    private SaucedemoCheckoutPage checkoutPage;
    private CheckoutStepTwoPage checkoutStepTwoPage;

    @BeforeMethod
    public void setUpPage(){
        inventoryPage = new SaucedemoInventoryPage(driver);
        cartPage = new SaucedemoCartPage(driver);
        checkoutPage = new SaucedemoCheckoutPage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
    }

    @Test(dataProvider = "itemList", dataProviderClass = SaucInventoryDataProvider.class)
    @Story("Add multiple items to the cart")
    @Description("Verifies that items are successfully added to the cart")
    public void addItemByName(String... items){
        Allure.step("Open inventory page and login");
        inventoryPage
                .open()
                .login()
                .addItemByName(items)
                .clickCart();

        Allure.step("Verify items are in cart");
        for(String item : items){
        Assert.assertTrue(cartPage.getItemNameFromCart().contains(item), "Invalid item");
        }
    }

    @Test(dataProvider = "singleItem", dataProviderClass = SaucInventoryDataProvider.class)
    @Story("Add single item to the cart")
    @Description("Verifies that a single item is added successfully")
    public void addSingleItemByName(String item){
        Allure.step("Open inventory page and login");
        inventoryPage
                .open()
                .login()
                .addItemByName(item)
                .clickCart();

        Allure.step("Verify the single item is in cart");
        Assert.assertTrue(cartPage.getItemNameFromCart().contains(item), "Item not found");
    }

    @Test(dataProvider = "itemList", dataProviderClass = SaucInventoryDataProvider.class)
    public void checkQuantityOfTheItems(String... items){
        Allure.step("Open inventory page,login and add items");
        inventoryPage
                .open()
                .login()
                .addItemByName(items);

        cartPage
                .open();

        Allure.step("Verify quantity of items ");
        for(String item : items) {
            Assert.assertTrue(cartPage.getItemQuantity(items).containsKey(item), "The name of the item is invalid");
            Assert.assertTrue(cartPage.getItemQuantity(items).containsValue("1"), "Quantity of item is invalid");
        }
    }

    @Test(dataProvider = "itemList", dataProviderClass = SaucInventoryDataProvider.class)
    @Story("Remove item from the cart")
    @Description("Verifies that a selected item is removed and others remain")
    public void removeItemFromCart(String... items){
        Allure.step("Open inventory page and login, add items");
        inventoryPage
                .open()
                .login()
                .addItemByName(items);

        cartPage
                .open();

        List<String> beforeRemove = cartPage.getItemNameFromCart();

        String itemToRemove = items[0];
        Allure.step("Remove item: " + itemToRemove);
        cartPage.clickRemoveButton(itemToRemove);

        List<String> afterRemove = cartPage.getItemNameFromCart();

        Allure.step("Verify removed item is not in cart and others remain");
        Assert.assertEquals(afterRemove.size(), beforeRemove.size() - 1, "Item count did not decrease by 1");
        Assert.assertFalse(afterRemove.contains(itemToRemove), "Removed item is still in the cart");

        for(int i = 1; i < items.length; i++){
            Assert.assertTrue(afterRemove.contains(items[i]), "Other item was removed incorrectly: " + items[i]);
        }
    }

    @Test(dataProvider = "itemList", dataProviderClass = SaucInventoryDataProvider.class)
    @Story("Check total price in checkout")
    @Description("Verifies that the total price including tax matches expected calculation")
    public void checkTotalPriceOfItems(String... items){
        Allure.step("Open inventory page, login, add items and go to checkout");

        inventoryPage
                .open()
                .login()
                .addItemByName(items);

        cartPage
                .open()
                .clickCheckoutButton();

        checkoutPage
                .setUserData()
                .clickContinueButton();

            BigDecimal expectedResult = toPrice(checkoutStepTwoPage.calculateExpectedTotal(items));
            BigDecimal actualResult  = toPrice(checkoutStepTwoPage.getTotalPrice());

        Allure.step("Verify total price");
        Assert.assertEquals(actualResult.compareTo(expectedResult), 0, "Total price does not match expected value");
    }
}


