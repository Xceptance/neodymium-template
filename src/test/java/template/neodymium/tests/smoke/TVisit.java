package template.neodymium.tests.smoke;

import org.junit.Test;

import template.flows.OpenHomePageFlow;
import template.neodymium.tests.BasicTest;
import template.pageObjects.pages.HomePage;

public class TVisit extends BasicTest
{
    @Test
    public void testVisitingHomepage()
    {
        // Goto the home page
        HomePage homePage = new OpenHomePageFlow().flow();
        homePage.validateStructure();
        homePage.title().validateTitle("Xceptance - The Software Testing Experts");
    }
}
