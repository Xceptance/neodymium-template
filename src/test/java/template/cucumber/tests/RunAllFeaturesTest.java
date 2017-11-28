package template.cucumber.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/template/cucumber/features", glue = "template", monochrome = true, plugin =
{
  "pretty", // console output
  "html:target/cucumber-report/", // html report
})
public class RunAllFeaturesTest
{
}
