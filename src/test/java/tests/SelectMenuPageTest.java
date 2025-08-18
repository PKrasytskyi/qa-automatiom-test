package tests;

import Pages.SelectMenuPage;
import TestSetUp.BaseTestAbstract;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SelectMenuPageTest extends BaseTestAbstract {

    private SelectMenuPage selectMenuPage;

    @BeforeMethod
    public void setUpPage(){
        selectMenuPage = new SelectMenuPage(driver);
    }

    @Test(priority = 8)
    public void getSelectValueGroup2Option1(){

        String expectedValue = "Group 2, option 1";

        selectMenuPage
                .open()
                .selectValueOfDropDownMenu(expectedValue);


        Assert.assertEquals(selectMenuPage.getSelectedValue(), expectedValue, "Selected value mismatch!");
        System.out.println(selectMenuPage.getSelectedValue());
    }

    @Test(priority = 9)
    public void getOneSelectedValue(){

        String expectedValue = "Mr.";

        selectMenuPage
                .open()
                .selectOneOfDropDownMenu(expectedValue);

        Assert.assertEquals(selectMenuPage.getOneSelectedValue(), expectedValue, "Selected value mismatch");
        System.out.println(selectMenuPage.getOneSelectedValue());
    }

    @Test(priority = 10)
    public void getOldStyleMenuValue(){

        String expectedValue = "White";

        selectMenuPage
                .open()
                .selectOldStyleDropDownMenu(expectedValue);

        Assert.assertEquals(selectMenuPage.getOldStyleSelectMenuValue(), expectedValue, "Selected value mismatch");
        System.out.println(selectMenuPage.getOldStyleSelectMenuValue());
    }

    @Test(priority = 11)
    public void getMultiselectValues(){

        String value1 = "Blue";
        String value2 = "Black";

        selectMenuPage
                .open()
                .multiselectDropDownMenu(value1, value2);

        List<String> selected = selectMenuPage.getMultiSelectMenuValues();
            Assert.assertTrue(selected.contains(value1));
            Assert.assertTrue(selected.contains(value2));
    }
}
