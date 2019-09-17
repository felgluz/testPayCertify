package framework.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.Driver;

public class Browser {
    public Browser(WebDriver driver) {
        _driver = driver;
    }

    private WebDriver _driver;

    public BrowserType Type;

    public enum BrowserType {
        Chrome,
        Firefox,
        IE,
        Edge
    }

    public void GoToUrl(String url) {
        _driver.get(url);
    }

    public void Maximize() {
        _driver.manage().window().maximize();
    }


}