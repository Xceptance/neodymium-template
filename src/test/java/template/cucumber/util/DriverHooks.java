package template.cucumber.util;

import com.xceptance.neodymium.util.WebDriverUtils;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;

public class DriverHooks
{
    @Given("^The browser \"([^\"]*)\" is open$")
    public static void setUp(String browserProfileName)
    {
        WebDriverUtils.setUp(browserProfileName);
    }

    // have a lower order number than default in order to shut down the driver after the test case specific after hooks
    @After(order = 100)
    public void tearDown(Scenario scenario)
    {
        WebDriverUtils.tearDown(scenario);
    }
}
