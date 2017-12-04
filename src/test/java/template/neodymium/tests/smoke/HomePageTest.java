package template.neodymium.tests.smoke;

import org.junit.Test;

import template.flows.OpenHomePageFlow;
import template.neodymium.tests.BasicTest;
import template.pageObjects.pages.HomePage;

public class HomePageTest extends BasicTest
{
    @Test
    public void testVisitingHomepage()
    {
        // Goto the home page
        HomePage homePage = new OpenHomePageFlow().flow();

        // short validation to check that the correct page was opened, should be moved to OpenHomePageFlow
        homePage.isExpectedPage();
        // basic validation
        homePage.validateStructure();
        homePage.title().validateTitle("Xceptance - The Software Testing Experts");
    }
}
