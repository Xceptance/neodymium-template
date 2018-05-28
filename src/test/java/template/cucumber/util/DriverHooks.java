package template.cucumber.util;

import com.xceptance.neodymium.util.Driver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;

public class DriverHooks
{
    @Given("^The browser \"([^\"]*)\" is open$")
    public static void setUp(String browserProfileName)
    {
        Driver.setUp(browserProfileName);
    }

    // have a lower order number than default in order to shut down the driver after the test case specific after hooks
    @After(order = 100)
    public void tearDown(Scenario scenario)
    {
        Driver.tearDown(scenario);
    }
}
