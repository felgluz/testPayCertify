package test.runner;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentTest;
import cucumber.api.CucumberOptions;

import cucumber.api.Scenario;
import cucumber.api.junit.Cucumber;

import framework.base.DriverContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.TakesScreenshot;

import java.io.UnsupportedEncodingException;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = {"src/test/test/features/"},
        glue = {"test.steps"},
        tags = {"@wip"},
        plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" +
                "src/test/test/data/report/"
        }
)

public class TestRunner {

}