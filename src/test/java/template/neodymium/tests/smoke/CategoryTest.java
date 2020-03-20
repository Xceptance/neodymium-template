package template.neodymium.tests.smoke;

import org.junit.BeforeClass;
import org.junit.Test;

import com.codeborne.selenide.Selenide;

import template.flows.OpenHomePageFlow;
import template.neodymium.tests.AbstractTest;
import template.pageobjects.pages.HomePage;
import util.applitools.ApplitoolsApi;

public class CategoryTest extends AbstractTest
{
    // can't be moved to AbstractTest class because each test class should have it's own batch name
    @BeforeClass
    public static void setBatch()
    {
        ApplitoolsApi.setupGlobal("Category Page Test");
    }

    @Test
    public void testCategoryServices()
    {
        // Goto the home page
        HomePage homePage = OpenHomePageFlow.flow();

        // short validation to check that the correct page was opened, should be moved to OpenHomePageFlow
        homePage.isExpectedPage();
        Selenide.open("https://www.xceptance.com/en/services/");
        ApplitoolsApi.assertPage("Category Services Page");
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
