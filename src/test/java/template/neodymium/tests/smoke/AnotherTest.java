package template.neodymium.tests.smoke;

import org.junit.BeforeClass;
import org.junit.Test;

import com.codeborne.selenide.Selenide;

import template.flows.OpenHomePageFlow;
import template.neodymium.tests.AbstractTest;
import template.pageobjects.pages.HomePage;
import util.applitools.ApplitoolsApi;

public class AnotherTest extends AbstractTest
{
    @BeforeClass
    public static void setupBatch()
    {
        ApplitoolsApi.setupForGroupOfTests("category");
    }

    @Test
    public void testCategoryXlt()
    {
        // Goto the home page
        HomePage homePage = OpenHomePageFlow.flow();

        // short validation to check that the correct page was opened, should be moved to OpenHomePageFlow
        homePage.isExpectedPage();
        Selenide.open("https://www.xceptance.com/en/xlt/");
        ApplitoolsApi.assertPage("Category Xlt Page");
    }
}
