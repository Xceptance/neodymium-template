package template.neodymium.tests.smoke;

import static com.codeborne.selenide.Selenide.$;

import org.junit.Test;

import template.flows.OpenHomePageFlow;
import template.neodymium.tests.AbstractTest;
import template.pageobjects.pages.HomePage;

public class HomePageTest extends AbstractTest
{

    @Test
    public void testVisitingHomepage()
    {
        // Goto the home page

        HomePage homePage = OpenHomePageFlow.flow();
        // applitoolsApi.openEyes("testVisitingHomepage");

        // short validation to check that the correct page was opened, should be moved to OpenHomePageFlow
        homePage.isExpectedPage();

        applitoolsApi.assertPage("Home Page");
    }

    @Test
    public void testHomepage()
    {
        // Goto the home page

        HomePage homePage = OpenHomePageFlow.flow();
        // applitoolsApi.openEyes("testHomepage");

        // short validation to check that the correct page was opened, should be moved to OpenHomePageFlow
        homePage.isExpectedPage();
        $(".text-right .btn.btn-primary").click();

        applitoolsApi.assertPage("Home Page");
    }

}
