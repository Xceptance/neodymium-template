package template.flows;

import com.xceptance.neodymium.util.Neodymium;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;

import io.cucumber.java.en.And;
import io.qameta.allure.Step;
import template.pageobjects.pages.HomePage;

public class OpenPageFlows
{
    @And("^I navigate to the home page of the project$")
    @Step("clear cookies and open home page")
    public static HomePage openHomePage()
    {
        // clear browser cookies to remove old data
        clearBrowserCookies();

        // navigate to the home page
        open(Neodymium.configuration().url());
        return new HomePage().isExpectedPage();
    };
}
