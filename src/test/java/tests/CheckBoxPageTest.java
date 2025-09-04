package tests;

import Data.CheckBoxDataProvider;
import Pages.Demoqa.pages.CheckBoxPage;
import TestSetUp.BaseTestAbstract;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CheckBoxPageTest extends BaseTestAbstract {
    private CheckBoxPage checkBoxPage;

    @BeforeMethod
    public void setUpPage() {
        checkBoxPage = new CheckBoxPage(driver);
    }

    @Test
    public void checkAllCheckbox() {

        checkBoxPage
                .open()
                .clickExpandButton()
                .selectAllCheckboxes();

        List<WebElement> allCheckboxes = checkBoxPage.getCheckBoxes();

        boolean allSelected = allCheckboxes.stream()
                .map(ch -> ch.findElement(By.cssSelector("input[type='checkbox']")))
                .allMatch(WebElement::isSelected);

        Assert.assertTrue(allSelected, "Not all checkboxes were selected");

    }

    @Test(dataProvider = "checkBox", dataProviderClass = CheckBoxDataProvider.class)
    public void checkCheckBox(String[] titles){
        checkBoxPage.open()
                .clickExpandButton()
                .selectCheckBoxes(titles);

        Assert.assertTrue(checkBoxPage.areCheckBoxesSelected(titles),
                "Not all checkboxes from DataProvider are selected");
    }
}


