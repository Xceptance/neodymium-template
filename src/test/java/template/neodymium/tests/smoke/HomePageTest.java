package template.neodymium.tests.smoke;

import org.junit.Test;
import org.junit.runners.model.InitializationError;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.applitools.eyes.selenium.Eyes;
import com.xceptance.neodymium.util.Neodymium;

import template.flows.OpenHomePageFlow;
import template.neodymium.tests.AbstractTest;
import template.pageobjects.pages.HomePage;

public class HomePageTest extends AbstractTest
{

    @Test
    public void testVisitingHomepage() throws InitializationError
    {
        Eyes eyes = new Eyes();

        String apiKey = "";
        eyes.setApiKey(apiKey);
        // Goto the home page

        HomePage homePage = OpenHomePageFlow.flow();
        EventFiringWebDriver eventFiringWebDriver = (EventFiringWebDriver) Neodymium.getDriver();
        RemoteWebDriver remoteWebDriver = (RemoteWebDriver) eventFiringWebDriver.getWrappedDriver();
        eyes.open(remoteWebDriver, "HomePage", "HomePageTest");

        // short validation to check that the correct page was opened, should be moved to OpenHomePageFlow
        homePage.isExpectedPage();
        // basic validation
        homePage.validateStructure();
        homePage.title.validateTitle(Neodymium.localizedText("homepage.title"));

        eyes.checkWindow("HomePage Window");
        eyes.closeAsync();
    }
}
