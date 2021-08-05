package template.neodymium.tests.smoke;

import org.junit.Test;

import com.xceptance.neodymium.util.Neodymium;

import template.flows.OpenPageFlows;
import template.neodymium.tests.AbstractTest;

public class HomePageTest extends AbstractTest
{
    @Test
    public void testVisitingHomepage()
    {
        // Goto the home page
        var homePage = OpenPageFlows.openHomePage();

        // basic validation
        homePage.validateStructure();
        homePage.title.validateTitle(Neodymium.localizedText("homepage.title"));
    }
}
