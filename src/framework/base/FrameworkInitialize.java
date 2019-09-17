package framework.base;

import cucumber.api.Scenario;
import framework.config.Settings;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FrameworkInitialize extends Base {

    //TODO delete
    private void ConfigureAuth(String password) {
        DriverContext.Driver.get("chrome-extension://ggmdpepbjljkkkdaklfihhngmmgmpggp/options.html");

        String windowHandle = DriverContext.Driver.getWindowHandle();
        DriverContext.Driver.switchTo().window(windowHandle);

        DriverContext.Driver.findElement(By.id("login")).sendKeys("TR662920");
        DriverContext.Driver.findElement(By.id("password")).sendKeys(password);
        DriverContext.Driver.findElement(By.id("retry")).clear();
        DriverContext.Driver.findElement(By.id("retry")).sendKeys("5");
        DriverContext.Driver.findElement(By.id("save")).click();
    }

    public void InitializeBrowser(Browser.BrowserType browserType) {

        WebDriver driver = null;
        switch (browserType) {
            case Chrome: {
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\Stefanini" +
                        "\\Documents\\Libs\\chromedriver.exe");
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.addExtensions(new File("C:\\Users\\Stefanini\\Documents" +
                        "\\Libs\\Proxy-Auto-Auth_v2.0.crx"));
                driver = new ChromeDriver(cOptions);
                break;
            }
            case Firefox: {
                //--
                break;
            }
        }
        //Set the driver
        DriverContext.setDriver(driver);
        //Browser
        DriverContext.Browser = new Browser(driver);

        ConfigureAuth(
                "y5g4schr"
        );
    }

    public void TakeScreenshot(Scenario scenario) {

        // Take screenshot and store as a file format
        File src = ((TakesScreenshot) DriverContext.Driver).getScreenshotAs(OutputType.FILE);
        ZonedDateTime date = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHMMSS");
        String fileNameFormat = date.format(formatter);

        try {
            FileUtils.copyFile(src, new File(Settings.ScreenshotPath + scenario.getName()
                    + " - " + fileNameFormat + ".png"));
        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


