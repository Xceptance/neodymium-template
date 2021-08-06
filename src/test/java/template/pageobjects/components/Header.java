package template.pageobjects.components;

import com.codeborne.selenide.Selenide;

import io.qameta.allure.Step;
import template.pageobjects.pages.BlogPage;
import template.pageobjects.pages.LicenceAndSupportPage;
import template.pageobjects.pages.ManagementPage;
import template.pageobjects.pages.NewsPage;
import template.pageobjects.pages.TeamPage;
import template.pageobjects.pages.XLTPage;

public class Header extends AbstractComponent
{

    @Override
    public void isComponentAvailable()
    {

    }

    @Step("go to the management page")
    public ManagementPage goToManagement()
    {
        Selenide.$("a[href$='management.html']").click();
        return new ManagementPage().isExpectedPage();
    }

    @Step("go to the blog page")
    public BlogPage gotoBlogPage()
    {// click the start here button
        Selenide.$("div#navigation a[href*=blog]").click();
        return new BlogPage().isExpectedPage();
    }

    @Step("go to XLT page")
    public XLTPage gotoXLTPage()
    {
        Selenide.$("div#navigation a[href$='/xlt/']").click();
        return new XLTPage().isExpectedPage();
    }

    @Step("go to the news page")
    public NewsPage gotoNewsPage()
    {
        Selenide.$("#navigation a[href*='news']").click();
        return new NewsPage().isExpectedPage();
    }

    @Step("go to the team page")
    public TeamPage gotoTeamPage()
    {
        Selenide.$("a[href*='team.html']").click();
        return new TeamPage().isExpectedPage();
    }

    @Step("go to licence and support page")
    public LicenceAndSupportPage gotoLicenceAndSupportPage()
    {
        Selenide.$("div#sub-navigation a[href*='licensing-and-pricing']").click();
        return new LicenceAndSupportPage().isExpectedPage();
    }

    {

    }
}
