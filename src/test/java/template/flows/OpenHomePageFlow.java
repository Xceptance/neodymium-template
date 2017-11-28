package template.flows;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;

import cucumber.api.java.en.And;
import template.pageObjects.pages.HomePage;
import template.settings.Settings;

public class OpenHomePageFlow
{

    @And("^I am on the homepage of the project$")
    public HomePage flow()
    {
        // clear browser cookies to remove old data
        clearBrowserCookies();

        // navigate to the home page
        HomePage homePage = open(Settings.homePageUrl, HomePage.class);

        // basic check to make sure your on the home page
        homePage.isExpectedPage();
        return homePage;
    };
}
