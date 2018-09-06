package template.neodymium.tests.smoke;

import org.junit.Test;

import com.xceptance.neodymium.util.Neodymium;

import template.flows.OpenHomePageFlow;
import template.neodymium.tests.AbstractTest;
import template.pageObjects.pages.HomePage;

public class HomePageTest extends AbstractTest
{
    @Test
    public void testVisitingHomepage()
    {
        // Goto the home page
        HomePage homePage = OpenHomePageFlow.flow();

        // short validation to check that the correct page was opened, should be moved to OpenHomePageFlow
        homePage.isExpectedPage();
        // basic validation
        homePage.validateStructure();
        homePage.title.validateTitle(Neodymium.localizedText("homepage.title"));
    }
}
