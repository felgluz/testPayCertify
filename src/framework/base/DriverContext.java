package framework.base;

import framework.config.Settings;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DriverContext {
    public static WebDriver Driver;

    public static Browser Browser;

    static void setDriver(WebDriver driver) {
        Driver = driver;
    }

    public static By toByValue(WebElement we) {
        String[] data = we.toString().split(" -> ")[1].replace("]", "").split(": ");
        String locator = data[0];
        String term = data[1];

        switch (locator) {
            case "xpath":
                return By.xpath(term);
            case "css selector":
                return By.cssSelector(term);
            case "id":
                return By.id(term);
            case "tag name":
                return By.tagName(term);
            case "name":
                return By.name(term);
            case "link text":
                return By.linkText(term);
            case "class name":
                return By.className(term);
        }
        return (By) we;
    }

    public static void WaitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(Driver, 20);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver;

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver WebDriver) {
                return ((JavascriptExecutor) Driver)
                        .executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Get JS Ready
        boolean jsReady = jsExecutor.executeScript("return document.readyState")
                .toString().equals("complete");

        if (!jsReady)
            wait.until(jsLoad);
        else
            Settings.Logs.Write("Página carregada!");
    }

    public static void WaitForElementVisible(final WebElement elementFindBy) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver, 10);
            wait.until(ExpectedConditions.visibilityOf(elementFindBy));
        } catch (Exception e) {
            e.getMessage();
            e.getStackTrace();
        }
    }

    public static void WaitForElementTextVisibled(final WebElement elementFindBy, String text) {
        WebDriverWait wait = new WebDriverWait(Driver, 30);
        wait.until(ExpectedConditions.textToBePresentInElement(elementFindBy, text));
    }

    public static void WaitForElementTextDisplayed(final By element, String text) {
        WebDriverWait wait = new WebDriverWait(Driver, 30);
        wait.until(textDisplayed(element, text));
    }

    private static ExpectedCondition<Boolean> textDisplayed(final By elementFindBy, String text) {
        return webDriver -> webDriver.findElement(elementFindBy).getText().contains(text);
    }

    public static void WaitForElementEnabled(final By elementFindBy) {
        WebDriverWait wait = new WebDriverWait(Driver, 30);
        wait.until(webDriver -> webDriver.findElement(elementFindBy).isEnabled());
    }

    public static void ScrollDownUntilTextVisibled(String text) {
        JavascriptExecutor js = (JavascriptExecutor) Driver;
        WebElement element = Driver.findElement(By.xpath(String.format("//*[contains(text(),'%s')]", text)));
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void ScrollDownUntilElementVisibled(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void ScrollDownUntilPixel(String posicaoPixel) {
        JavascriptExecutor js = (JavascriptExecutor) Driver;
        js.executeScript("window.scrollBy(0," + posicaoPixel + ")");
    }

    public static void WaitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static String GetFieldAttribute(String attribute, final WebElement element) {
        WebElement inputBox = element;
        String textInsideInputBox = inputBox.getAttribute(attribute);

        if (textInsideInputBox.isEmpty()) {
            System.out.println("Input field is empty");
        }

        return textInsideInputBox;
    }

    public static void FindElement(By by) {
        Driver.findElement(by);
    }

    public static void SwitchWindow(String nextWindow_Or_ParentWindow) {
        String parentWindowHandler = Driver.getWindowHandle(); // Store your parent window
        String subWindowHandler = null;

        Set<String> handles = Driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }

        if (nextWindow_Or_ParentWindow == "nextWindow") {
            Driver.switchTo().window(subWindowHandler); // switch to popup window
        } else {
            Driver.switchTo().window(parentWindowHandler);  // switch back to parent window
        }
    }

    public static void SwitchAlert() {
        Alert alert = Driver.switchTo().alert();
    }

    public static void SwitchToFrame(By by) {
        WebElement frame = DriverContext.Driver.findElement(by);
        DriverContext.Driver.switchTo().frame(frame);
    }

    public static void RobotPressKey(int keyEvent) throws AWTException {
        Robot r = new Robot();
        r.keyPress(keyEvent);
        r.keyRelease(keyEvent);
    }

    public static void RobotMouseMove(int x, int y) throws AWTException {
        Robot r = new Robot();
        r.mouseMove(x, y);
    }

    public static void RobotMouseClick() throws AWTException {
        Robot r = new Robot();
        r.mousePress(InputEvent.BUTTON1_MASK);
    }

    public static boolean IsFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();

        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                // File has been found, it can now be deleted:
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }

    public static void MouseHoverAndClick(WebElement hoverElement, String clickElementXpath) throws InterruptedException {
        Actions builder = new Actions(Driver);
        builder.moveToElement(hoverElement).perform();
        By locator = By.xpath(clickElementXpath);
        Thread.sleep(500);
        Driver.findElement(locator).click();
    }

    public static void MouseHover(WebElement hoverElement){
        Actions builder = new Actions(Driver);
        builder.moveToElement(hoverElement).perform();
    }

}

