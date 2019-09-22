package test.pages;

import framework.base.BasePage;
import framework.base.DriverContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;
import java.util.List;

public class AdvancedSearchPage extends BasePage {

    @FindBy(css = "[data-type='height'] [data-value='medium']")
    WebElement btnHeightMedium;
    @FindBy(css = "[data-type='height'] [data-value='tall']")
    WebElement btnHeightTall;

    @FindBy(css = "[data-value='water']")
    List<WebElement> btnWater;
    @FindBy(css = "[data-value='fire']")
    List<WebElement> btnFire;

    @FindBy(xpath = "//label[contains(text(),'All')]")
    WebElement dropDownAbilities;
    @FindBy(id = "search")
    List<WebElement> btnSearch;

    @FindBy(id = "minRangeBox")
    WebElement txtMinRangeBox;
    @FindBy(id = "maxRangeBox")
    WebElement txtMaxRangeBox;

    public void SelectDetails(String type, String weakness, String ability, String height) {
        DriverContext.ScrollDownUntilPixel("400");

        if (type.equals("Fire")) {
            DriverContext.WaitForElementVisible(btnFire.get(0));
            btnFire.get(0).click();
        }
        if (weakness.equals("Water")) {
            DriverContext.WaitForElementVisible(btnWater.get(1));
            btnWater.get(1).click();
        }
        if (ability.equals("Blaze")) {
            DriverContext.WaitForElementVisible(dropDownAbilities);
            dropDownAbilities.click();

            WebElement options = DriverContext.Driver.findElement(By.cssSelector(".viewport"));
            DriverContext.Driver.findElement(By.xpath(String.format("//select[@id='abilities']/option[contains(text(),'%s')]", ability))).click();
        }
        if (height.equals("Medium and Tall")) {
            btnHeightMedium.click();
            btnHeightTall.click();
        }
    }

    public void ClickBtnSearch() {
        DriverContext.ScrollDownToElementVisibled(btnFire.get(1));
        btnSearch.get(1).click();
    }

    public void SetNumberRange(String minRange, String maxRange) {
        DriverContext.ScrollDownToElementVisibled(txtMinRangeBox);
        txtMinRangeBox.clear();
        txtMinRangeBox.sendKeys(minRange);

        txtMaxRangeBox.clear();
        txtMaxRangeBox.sendKeys(maxRange);
    }

}
