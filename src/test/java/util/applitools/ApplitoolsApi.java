package util.applitools;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.function.Supplier;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.Eyes;
import com.xceptance.neodymium.util.AllureAddons;
import com.xceptance.neodymium.util.Neodymium;

public class ApplitoolsApi
{
    // have to be saved in class because needed to be passed for setup before each test method
    private static BatchInfo batch;

    private static ThreadLocal<Eyes> eyes = ThreadLocal.withInitial(new Supplier<Eyes>()
    {
        @Override
        public Eyes get()
        {
            return new Eyes();
        }
    });

    public static void setupGlobal(String batchName)
    {
        batch = new BatchInfo(batchName);
    }

    public static void setupForTest()
    {
        setMatchLevel(ConfigFactory.create(ApplitoolsConfiguration.class).macthLevel());

        // Set your personal Applitols API Key from your environment variables.
        eyes.get().setApiKey(getApiKey());

        // set batch name
        eyes.get().setBatch(batch);

    }

    public static void setMatchLevel(String matchLevel)
    {
        eyes.get().setMatchLevel(parseMatchLevel(matchLevel));
    }

    public static void openEyes(String testName)
    {
        // get driver instance from neodymium and cast to webdriver
        EventFiringWebDriver eventFiringWebDriver = (EventFiringWebDriver) Neodymium.getDriver();
        WebDriver driver = (RemoteWebDriver) eventFiringWebDriver.getWrappedDriver();

        eyes.get().open(driver, ConfigFactory.create(ApplitoolsConfiguration.class).projectName(), testName);
    }

    public static void assertPage(String pageName)
    {
        eyes.get().checkWindow(pageName);
    }

    public static void endAssertions()
    {
        TestResults allTestResults = eyes.get().close(Boolean.parseBoolean(ConfigFactory.create(ApplitoolsConfiguration.class).throwException()));
        AllureAddons.addToReport("number of missmatches", allTestResults.getMismatches());
        AllureAddons.addToReport("link to results of visual assetions in this test", allTestResults.getUrl());
        eyes.get().abortIfNotClosed();
    }

    private static String getApiKey()
    {
        String apiKey = ConfigFactory.create(ApplitoolsConfiguration.class).apiKey();
        if (isNullOrEmpty(apiKey))
        {
            throw new RuntimeException("No API Key found; Please set applitools.apiKey property in applitools.properties");
        }
        return apiKey;
    }

    private static MatchLevel parseMatchLevel(String matchLevel)
    {
        switch (matchLevel)
        {
            case "LAYOUT2":
                return MatchLevel.LAYOUT2;
            case "LAYOUT":
                return MatchLevel.LAYOUT;
            case "CONTENT":
                return MatchLevel.CONTENT;
            case "EXACT":
                return MatchLevel.EXACT;
            case "NONE":
                return MatchLevel.NONE;
            default:
                return MatchLevel.STRICT;
        }
    }
}
