package test.pages;

import framework.base.BasePage;
import framework.base.DriverContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class AdvancedSearchPage extends BasePage {

    @FindBy(css = "[data-type='height'] [data-value='medium']")
    WebElement btnHeightMedium;
    @FindBy(css = "[data-type='height'] [data-value='tall']")
    WebElement btnHeightTall;

    @FindBy(css = "[data-type='type']")
    List<WebElement> btnTypes;
    @FindBy(css = "[data-value='fire']")
    List<WebElement> btnFire;

    @FindBy(id = "abilities")
    WebElement dropDownAbilities;

    public void SelectDetails(String type, String weakness, String ability, String height) {

        DriverContext.ScrollDownUntilTextVisibled("Hide Advanced Search");
        if (type.equals("Fire")) {
            btnFire.get(0).click();
        }
        if (weakness.equals("Water")) {
            btnTypes.get(1).click();
        }
        if (ability.equals("Blaze")) {
            dropDownAbilities.click();
            DriverContext.Driver.findElement(By.xpath(String.format("//select[@id='abilities']/option[contains(text(),'%s')]", ability))).click();
        }
        if (height.equals("Medium and tall")) {
            btnHeightMedium.click();
            btnHeightTall.click();
        }
    }

}
