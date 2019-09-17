package framework.base;

import org.openqa.selenium.WebElement;

public class BasePage extends Base{

    public <TPage extends BasePage> TPage As(Class<TPage> pageInstance)
    {
        try {
            return (TPage)this;
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }
}

