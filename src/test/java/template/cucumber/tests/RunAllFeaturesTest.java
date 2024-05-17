package template.cucumber.tests;

import org.junit.runner.RunWith;

import com.xceptance.neodymium.junit4.NeodymiumCucumberRunner;

import io.cucumber.junit.CucumberOptions;

@RunWith(NeodymiumCucumberRunner.class)
@CucumberOptions(features = "src/test/java/template/cucumber/features", glue = "template", monochrome = true, plugin =
{
  "pretty", // console output
  "html:target/cucumber-report/", // html report
})
public class RunAllFeaturesTest
{
}
