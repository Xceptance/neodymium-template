package template.neodymium.tests.smoke;

import static com.codeborne.selenide.Selenide.$;

import org.junit.BeforeClass;
import org.junit.Test;

import template.flows.OpenHomePageFlow;
import template.neodymium.tests.AbstractTest;
import template.pageobjects.pages.HomePage;
import util.applitools.ApplitoolsApi;

public class HomePageTest extends AbstractTest
{
    // can't be moved to AbstractTest because the it will overwrite setup of batches with another names
    @BeforeClass
    public static void setupBatch()
    {
        ApplitoolsApi.setupGlobal();
    }

    @Test
    public void testHomepage()
    {
        // Goto the home page
        HomePage homePage = OpenHomePageFlow.flow();

        // short validation to check that the correct page was opened, should be moved to OpenHomePageFlow
        homePage.isExpectedPage();

        // this can be moved to page object
        ApplitoolsApi.assertPage("Home Page");
    }

    @Test
    public void testHomepageWithCookies()
    {
        // Goto the home page
        HomePage homePage = OpenHomePageFlow.flow();

        // short validation to check that the correct page was opened, should be moved to OpenHomePageFlow
        homePage.isExpectedPage();
        // accept cookies
        $(".text-right .btn.btn-primary").click();

        // this can be moved to page object
        ApplitoolsApi.assertPage("Home Page after cookies accepted");
    }

}
