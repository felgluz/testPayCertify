package test.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import framework.base.DriverContext;
import framework.base.FrameworkInitialize;
import framework.config.ConfigReader;
import framework.config.Settings;
import framework.utilities.ExcelUtil;
import framework.utilities.LogUtil;
import jxl.read.biff.BiffException;

import java.io.IOException;

public class TestInitialize extends FrameworkInitialize {
    @Before
    public void Initialize() throws IOException, BiffException {
        //Initialize config
        ConfigReader.PopulateSettings();

        //Log
        Settings.Logs = new LogUtil();
        Settings.Logs.CreateLogFile();

        Settings.Logs.Write("Test cicle created");
        InitializeBrowser(Settings.BrowserType);
        Settings.Logs.Write("Browser initialized");
        Settings.ExcelSheet = new ExcelUtil(Settings.ExcelSheetPath);

        DriverContext.Browser.Maximize();
        DriverContext.AcceptCookies();
    }

    @After
    public void Close(Scenario scenario) {
        if (scenario.isFailed()) {
            TakeScreenshot(scenario);
        }
        DriverContext.Driver.quit();
    }
}

