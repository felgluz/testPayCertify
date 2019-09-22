package framework.base;

import cucumber.api.Scenario;
import framework.config.Settings;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrameworkInitialize extends Base {

    //TODO delete
    private void ConfigureAuth() {
        DriverContext.Driver.get("chrome-extension://ggmdpepbjljkkkdaklfihhngmmgmpggp/options.html");

        String windowHandle = DriverContext.Driver.getWindowHandle();
        DriverContext.Driver.switchTo().window(windowHandle);

        DriverContext.Driver.findElement(By.id("login")).sendKeys("TR662920");
        DriverContext.Driver.findElement(By.id("password")).sendKeys("Q1mchTyd");
        DriverContext.Driver.findElement(By.id("retry")).clear();
        DriverContext.Driver.findElement(By.id("retry")).sendKeys("5");
        DriverContext.Driver.findElement(By.id("save")).click();
    }

    public void InitializeBrowser(Browser.BrowserType browserType) {

        WebDriver driver = null;
        switch (browserType) {
            case Chrome: {
                //todo set dynamic location
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\Stefanini" +
                        "\\Documents\\Libs\\chromedriver.exe");
                /*ChromeOptions cOptions = new ChromeOptions();
                cOptions.addExtensions(new File("C:\\Users\\Stefanini\\Documents" +
                        "\\Libs\\Proxy-Auto-Auth_v2.0.crx"));
                driver = new ChromeDriver(cOptions);*/
                driver = new ChromeDriver();
                break;
            }
            case Firefox: {
                //TODO set others drivers
                break;
            }
        }
        //Set the driver
        DriverContext.setDriver(driver);

        //Browser
        DriverContext.Browser = new Browser(driver);

        //ConfigureAuth();

    }


    public void TakeScreenshot(Scenario scenario) {

        // Take screenshot and store as a file format
        File src = ((TakesScreenshot) DriverContext.Driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("hh-mm-ss___dd_MM_yyyy").format(new Date());

        try {
            // now copy the  screenshot to desired location using copyFile //method
            FileUtils.copyFile(src, new File(Settings.ScreenshotPath + scenario.getName()
                    + " - " + timestamp + ".png"));
        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


