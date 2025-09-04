package Pages.Demoqa.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CheckBoxPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckBoxPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public CheckBoxPage open(){
        driver.get("https://demoqa.com/checkbox");
        return this;
    }

    //Locators
    private final By extendButton = By.xpath("//*[@id=\"tree-node\"]/div/button[1]");
    private final By resultRow = By.id("result");

    public List<WebElement> getCheckBoxes() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("li.rct-node")));
    }

    public CheckBoxPage clickExpandButton() {

        WebElement expandButton = driver.findElement(extendButton);
        expandButton.click();
        return this;
    }


    public CheckBoxPage selectAllCheckboxes() {
        List<WebElement> checkboxes;

        while (true) {
            checkboxes = getCheckBoxes();
            boolean anyUnselected = false;

            for (WebElement checkbox : checkboxes) {
                WebElement input = checkbox.findElement(By.cssSelector("input[type='checkbox']"));
                WebElement clickableSpan = checkbox.findElement(By.cssSelector("span.rct-checkbox"));

                if (!input.isSelected()) {
                    anyUnselected = true;
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickableSpan);
                }
            }

            if (!anyUnselected) {
                break;
            }
        }

        return this;
    }

    public CheckBoxPage selectCheckBoxes(String[] titles) {
        boolean allSelected;

        do {
            allSelected = true;
            List<WebElement> checkboxes = getCheckBoxes();

            for (String title : titles) {
                WebElement checkboxLi = checkboxes.stream()
                        .filter(ch -> ch.findElement(By.cssSelector("span.rct-title")).getText().equals(title))
                        .findFirst()
                        .orElse(null);

                if (checkboxLi != null) {
                    WebElement input = checkboxLi.findElement(By.cssSelector("input[type='checkbox']"));
                    WebElement clickableSpan = checkboxLi.findElement(By.cssSelector("span.rct-checkbox"));

                    if (!input.isSelected()) {
                        allSelected = false;
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkboxLi);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickableSpan);
                    }
                }
            }
        } while (!allSelected);

        return this;
    }

    // Verify that exactly these checkboxes are selected
    public boolean areCheckBoxesSelected(String[] titles) {
        List<String> selectedTitles = getCheckBoxes().stream()
                .filter(ch -> ch.findElement(By.cssSelector("span[class='rct-checkbox']"))
                        .getAttribute("class").contains("check"))
                .map(WebElement::getText)
                .collect(Collectors.toList());

        for (String title : titles) {
            if (!selectedTitles.contains(title)) {
                return false; //
            }
        }
        return true;
    }
}
