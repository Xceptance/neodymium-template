package util.applitools;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.exceptions.DiffsFoundException;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.xceptance.neodymium.util.Neodymium;

public class ApplitoolsApi
{
    private static BatchInfo batch;

    private Eyes eyes;

    private WebDriver driver;

    private EyesRunner runner;

    public static void setupGlobal()
    {
        batch = new BatchInfo(ConfigFactory.create(ApplitoolsConfiguration.class).batch());
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

        // Use Chrome browser
        EventFiringWebDriver eventFiringWebDriver = (EventFiringWebDriver) Neodymium.getDriver();
        RemoteWebDriver remoteWebDriver = (RemoteWebDriver) eventFiringWebDriver.getWrappedDriver();
        driver = remoteWebDriver;
    }

    public void openEyes(String testName)
    {
        setupForTest();
        eyes.open(driver, ConfigFactory.create(ApplitoolsConfiguration.class).projectName(), testName);
    }

    public void assertPage(String pageName)
    {
        openEyes(pageName);

        eyes.checkWindow(pageName);
    }

    public void endAssertions()
    {
        eyes.closeAsync();
        Pattern p = Pattern.compile("https\\:\\/\\/(.*)");
        String urlToPicture = "https://";
        // Wait and collect all test results
        try
        {
            TestResultsSummary allTestResults = runner.getAllTestResults();
            Matcher m = p.matcher(allTestResults.toString());
            m.find();
            urlToPicture += m.group(1);
            System.out.println(urlToPicture);
        }
        catch (DiffsFoundException diffException)
        {
            Matcher m = p.matcher(diffException.getMessage());
            m.find();
            urlToPicture += m.group(1);
            System.out.println(urlToPicture);
            throw diffException;
        }
        // Print results
        // If the test was aborted before eyes.close was called, ends the test as
        // aborted.
        eyes.abortIfNotClosed();

    }
}
