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

    public void InitializeBrowser(Browser.BrowserType browserType) {

        WebDriver driver = null;
        switch (browserType) {
            case Chrome: {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            }
            case Firefox: {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\geckodriver.exe");
                driver = new ChromeDriver();
                break;
            }
        }
        //Set the driver
        DriverContext.setDriver(driver);

        //Browser
        DriverContext.Browser = new Browser(driver);
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


