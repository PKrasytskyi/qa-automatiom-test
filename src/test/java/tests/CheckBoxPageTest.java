package tests;

import data.DemoqaDataProviders.CheckBoxDataProvider;
import pages.Demoqa.pages.CheckBoxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.BaseTest;

import java.util.List;

public class CheckBoxPageTest extends BaseTest {
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


