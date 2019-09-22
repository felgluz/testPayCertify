package framework.base;


import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;

import java.util.List;

public class DriverContext {
    public static WebDriver Driver;

    public static Browser Browser;

    static void setDriver(WebDriver driver) {
        Driver = driver;
    }

    static int timeOutInSeconds = 10;

    public static void WaitForPageToLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        };
        WebDriverWait wait = new WebDriverWait(DriverContext.Driver, timeOutInSeconds);
        wait.until(pageLoadCondition);
    }

    public static void WaitForElementVisible(final WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.getMessage();
            e.getStackTrace();
        }
    }

    public static void WaitForElementClickable(final WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (NoSuchElementException e) {
            e.getMessage();
            e.getStackTrace();
        }
    }

    public static void WaitForAnimation(final By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver, timeOutInSeconds);
            wait.until(SteadinessOfElementLocated(locator));
        } catch (Exception e) {
            e.getMessage();
            e.getStackTrace();
        }
    }

    public static void AcceptCookies() {
        By cookie = By.id("cookie-dismisser");
        DriverContext.WaitForAnimation(cookie);
        WebElement cookies = Driver.findElement(cookie);
        DriverContext.WaitForElementClickable(cookies);
        cookies.click();
    }

    public static ExpectedCondition<WebElement> SteadinessOfElementLocated(final By locator) {
        return new ExpectedCondition<WebElement>() {

            private WebElement _element = null;
            private Point _location = null;

            @Override
            public WebElement apply(WebDriver driver) {
                if (_element == null) {
                    try {
                        _element = DriverContext.Driver.findElement(locator);
                    } catch (NoSuchElementException e) {
                        return null;
                    }
                }

                try {
                    if (_element.isDisplayed()) {
                        Point location = _element.getLocation();
                        if (location.equals(_location) && isOnTop(_element)) {
                            return _element;
                        }
                        _location = location;
                    }
                } catch (StaleElementReferenceException e) {
                    _element = null;
                }

                return null;
            }

            @Override
            public String toString() {
                return "steadiness of element located by " + locator;
            }
        };
    }

    public static boolean isOnTop(WebElement element) {
        WebDriver driver = ((RemoteWebElement) element).getWrappedDriver();

        return (boolean) ((JavascriptExecutor) driver).executeScript(
                "var elm = arguments[0];" +
                        "var doc = elm.ownerDocument || document;" +
                        "var rect = elm.getBoundingClientRect();" +
                        "return elm === doc.elementFromPoint(rect.left + (rect.width / 2), rect.top + (rect.height / 2));"
                , element);
    }

    public static WebElement GetElementDisplayedInArray(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(Driver, timeOutInSeconds);
        for (WebElement element : elements)
            if (
                    element.isEnabled()
                            && element.isDisplayed()
                            && element.getSize().getHeight() > 0
                            && element.getSize().getWidth() > 0) {
                return wait.until(ExpectedConditions.visibilityOf(element));
            }
        throw new NullPointerException();
    }

    public static void ScrollDownToElementVisibled(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static boolean GetStatusOfEachElementVisibleInArray(List<WebElement> elements) {
        for(WebElement element : elements){
            if(!element.isDisplayed()){
                return false;
            }
            DriverContext.ScrollDownToElementVisibled(element);
        }
        return true;
    }
}





