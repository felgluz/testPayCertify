package test.pages;

import framework.base.BasePage;
import framework.base.DriverContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdvancedSearchPage extends BasePage {

    @FindBy (css = "[data-type='height'] [data-value='short']") WebElement btnHeight;

    public void ClickOnHeight() {
        DriverContext.WaitForElementVisible(btnHeight);
        btnHeight.click();
    }
}
