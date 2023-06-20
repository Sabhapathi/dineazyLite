package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features"
        , glue = "Steps"
        ,plugin = "json:target/jsonReports/cucumber-report.json"
//        , tags = "@wip"
)
public class TestRunner {
}
