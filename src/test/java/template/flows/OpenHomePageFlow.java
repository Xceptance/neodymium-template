package template.flows;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;

import com.xceptance.neodymium.util.Neodymium;

import io.cucumber.java.en.And;
import template.pageobjects.pages.HomePage;

public class OpenHomePageFlow
{
    @And("^I navigate to the home page of the project$")
    public static HomePage flow()
    {
        // clear browser cookies to remove old data
        clearBrowserCookies();

        // navigate to the home page
        open(Neodymium.configuration().url());
        return new HomePage().isExpectedPage();
    };
}
