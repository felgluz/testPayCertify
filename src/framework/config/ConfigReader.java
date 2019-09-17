package framework.config;

import framework.base.Browser;

import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static void PopulateSettings() throws IOException {
        ConfigReader reader = new ConfigReader();
        reader.ReadProperty();
    }

    private void ReadProperty() throws IOException {
        Properties p = new Properties();
        p.load(getClass().getResourceAsStream("GlobalConfig.properties"));

        Settings.AUT = p.getProperty("AUT");
        Settings.UrlPokedex = p.getProperty("UrlPokedex");

        Settings.LogPath = p.getProperty("LogPath");
        Settings.BrowserType = Browser.BrowserType.valueOf(p.getProperty("BrowserType"));
        Settings.ExcelSheetPath = p.getProperty("ExcelSheetPath");
        Settings.ScreenshotPath = p.getProperty("ScreenshotPath");
        Settings.ReportConfigPath = p.getProperty("ReportConfigPath");
    }
}
