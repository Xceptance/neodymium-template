package util.applitools;

import static com.google.common.base.Strings.isNullOrEmpty;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.xceptance.neodymium.util.AllureAddons;
import com.xceptance.neodymium.util.Neodymium;

public class ApplitoolsApi
{
    // have to be saved in class because needed to be passed for setup before each test method
    private static BatchInfo batch;

    private Eyes eyes;

    private ClassicRunner runner;

    public static void setupGlobal(String batchName)
    {
        batch = new BatchInfo(batchName);
    }

    public void setupForTest()
    {
        // Initialize the Runner for your test.
        runner = new ClassicRunner();

        // Initialize the eyes SDK
        eyes = new Eyes(runner);

        // Raise an error if no API Key has been found.
        String apiKey = ApplitoolsConfiguration.apiKey();
        if (isNullOrEmpty(apiKey))
        {
            throw new RuntimeException("No API Key found; Please set environment variable 'APPLITOOLS_API_KEY'.");
        }

        // Set your personal Applitols API Key from your environment variables.
        eyes.setApiKey(apiKey);

        // set batch name
        eyes.setBatch(batch);

    }

    public void openEyes(String testName)
    {
        // get driver instance from neodymium and cast to webdriver
        EventFiringWebDriver eventFiringWebDriver = (EventFiringWebDriver) Neodymium.getDriver();
        WebDriver driver = (RemoteWebDriver) eventFiringWebDriver.getWrappedDriver();

        eyes.open(driver, ConfigFactory.create(ApplitoolsConfiguration.class).projectName(), testName);
    }

    public void assertPage(String pageName)
    {
        eyes.checkWindow(pageName);
    }

    public void endAssertions()
    {
        eyes.closeAsync();
        TestResults allTestResults = runner.getAllTestResultsImpl(false).getAllResults()[0].getTestResults();
        AllureAddons.addToReport("number of missmatches", allTestResults.getMismatches());
        AllureAddons.addToReport("link to results of visual assetions in this test", allTestResults.getUrl());
        eyes.abortIfNotClosed();
    }
}
