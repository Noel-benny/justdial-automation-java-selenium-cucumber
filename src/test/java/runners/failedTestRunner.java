package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "@target/rerun.txt",
    glue = "stepDefinitions",
    plugin = {
    		"pretty",
            "json:target/cucumber-report/cucumber.json",
            "html:target/cucumber-report/cucumber.html",
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
            "rerun:target/rerun.txt"
    },
    monochrome = true
)
public class failedTestRunner {

}