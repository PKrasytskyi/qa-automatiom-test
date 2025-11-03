package data.SaucedemoDataProviders;

import org.testng.annotations.DataProvider;

public class SaucInventoryDataProvider {

    @DataProvider(name="itemList")
    public Object[][] itemList()  {

        return new Object[][]
                {
                        {new String[]{"Sauce Labs Bike Light", "Sauce Labs Onesie"}},
                        {new String[]{"Sauce Labs Bolt T-Shirt", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Fleece Jacket"}}

                };
    }

    @DataProvider(name="singleItem")
        public Object[][] singleItem() {

        return new Object[][]{
                {"Sauce Labs Onesie"}
        };
    }
}
