package framework.config;

import framework.base.Browser;
import framework.utilities.ExcelUtil;
import framework.utilities.LogUtil;

public class Settings {

    public static String AUT;

    public static String LogPath;
    public static LogUtil Logs;
    public static Browser.BrowserType BrowserType;
    public static String ScreenshotPath;

    public static ExcelUtil ExcelSheet;
    public static String ExcelSheetPath;

    public static String ReportConfigPath;

    //TODO use the project path
    public static String ProjectPath;
}