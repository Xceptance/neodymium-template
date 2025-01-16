package template.neodymium.tests.smoke;

import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.Neodymium;
import template.flows.OpenPageFlows;
import template.neodymium.tests.AbstractTest;

public class HomePageTest extends AbstractTest
{
    @NeodymiumTest
    public void testVisitingHomepage()
    {
        // Goto the home page and perform a short validation that we are on the correct page
        var homePage = OpenPageFlows.openHomePage();

        // basic validation
        homePage.validateStructure();
        homePage.title.validateTitle(Neodymium.localizedText("homepage.title"));
    }
}
