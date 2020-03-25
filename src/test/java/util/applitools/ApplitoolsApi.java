package util.applitools;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.function.Supplier;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
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
    private static ThreadLocal<Eyes> eyes = ThreadLocal.withInitial(new Supplier<Eyes>()
    {
        @Override
        public Eyes get()
        {
            return new Eyes();
        }
    });

    public static void setupGlobal()
    {
        setupForGroupOfTests(ConfigFactory.create(ApplitoolsConfiguration.class).batch());
    }

    public static void setupForGroupOfTests(String batchNameForGroup)
    {
        BatchInfo batch = new BatchInfo(batchNameForGroup);
        String batchId = BatchHelper.getBatch(batchNameForGroup);
        if (batchId == null)
        {
            String newBatchId = BatchHelper.addBatch(batchNameForGroup);
            if (newBatchId != null)
            {
                batch.setId(newBatchId);
            }
        }
        else
        {
            batch.setId(batchId);
        }
        eyes.get().setBatch(batch);
        setupForSingleTest();
    }

    public static void addPropertiy(String name, String value)
    {
        eyes.get().addProperty(name, value);
    }

    public static void setupForSingleTest()
    {
        setMatchLevel(ConfigFactory.create(ApplitoolsConfiguration.class).matchLevel());

        eyes.get().setApiKey(getApiKey());
    }

    public static void setMatchLevel(String matchLevel)
    {
        eyes.get().setMatchLevel(parseMatchLevel(matchLevel));
    }

    public static void openEyes(String testName)
    {
        eyes.get().open(getDriver(), ConfigFactory.create(ApplitoolsConfiguration.class).projectName(), testName);
    }

    /**
     * Use this method to set if Eyes should hide the cursor before the screenshot is captured.
     * 
     * @param hideCaret
     */
    public static void setHideCaret(boolean hideCaret)
    {
        eyes.get().setHideCaret(hideCaret);
    }

    public static void assertPage(String pageName)
    {
        eyes.get().checkWindow(pageName);
    }

    public static void assertElement(String elementSelector)
    {
        assertElement(elementSelector, elementSelector);
    }

    public static void assertElements(String elementSelector)
    {
        WebDriver driver = getDriver();
        if (elementSelector.substring(0, 1).equals("//"))
        {
            driver.findElements(By.xpath(elementSelector)).forEach(element -> eyes.get().checkElement(element, elementSelector));
        }
        else
        {
            driver.findElements(By.cssSelector(elementSelector)).forEach(element -> eyes.get().checkElement(element, elementSelector));
        }
    }

    public static void setWaitBeforeScreenshot(int waitBeforeScreenshots)
    {
        eyes.get().setWaitBeforeScreenshots(waitBeforeScreenshots);
    }

    public static void assertElement(String elementSelector, String tag)
    {
        if (elementSelector.substring(0, 1).equals("//"))
        {
            eyes.get().checkElement(By.xpath(elementSelector), tag);

        }
        else
        {
            eyes.get().checkElement(By.cssSelector(elementSelector), tag);

        }
    }

    public static void endAssertions()
    {
        TestResults allTestResults = eyes.get().close(Boolean.parseBoolean(ConfigFactory.create(ApplitoolsConfiguration.class).throwException()));
        if (allTestResults == null)
        {
            throw new RuntimeException("something went wrong, maybe you have not called Applitools.openEyes() before calling this method");
        }
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

    private static RemoteWebDriver getDriver()
    {
        EventFiringWebDriver eventFiringWebDriver = (EventFiringWebDriver) Neodymium.getDriver();
        return (RemoteWebDriver) eventFiringWebDriver.getWrappedDriver();
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
