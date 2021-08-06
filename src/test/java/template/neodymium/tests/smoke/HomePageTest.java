package template.neodymium.tests.smoke;

import org.junit.Test;

import com.xceptance.neodymium.util.Neodymium;

import template.flows.OpenHomePageFlow;
import template.neodymium.tests.AbstractTest;
import template.pageobjects.pages.AboutUsPage;
import template.pageobjects.pages.BlogPage;
import template.pageobjects.pages.GitHubPage;
import template.pageobjects.pages.HomePage;
import template.pageobjects.pages.ManagementPage;
import template.pageobjects.pages.NewsPage;
import template.pageobjects.pages.TeamPage;
import template.pageobjects.pages.TwitterPage;
import template.pageobjects.pages.XLTPage;

public class HomePageTest extends AbstractTest
{
    @Test
    public void testVisitingHomepage()
    {
        // Goto the home page
        HomePage homePage = OpenHomePageFlow.flow();

        homePage.isExpectedPage();
        homePage.validateStructure();
        homePage.title.validateTitle(Neodymium.localizedText("homepage.title"));

        AboutUsPage aboutUsPage = homePage.goToAboutUs();
        aboutUsPage.title.validateTitle(Neodymium.localizedText("aboutUsPage.title"));
        aboutUsPage.validateStructure();
        aboutUsPage.validateTitleOfTheLogo();
        aboutUsPage.validateQuickFacts();
        aboutUsPage.validateAboutUsPageTitles();

        ManagementPage managementPage = aboutUsPage.header.goToManagement();
        managementPage.title.validateTitle("Xceptance - The Management");

        TeamPage teampage = managementPage.header.gotoTeamPage();
        teampage.validateHeadLine();

        XLTPage xltpage = managementPage.header.gotoXLTPage();
        xltpage.validatePageLogo();
        xltpage.validateStructure();

        BlogPage blogpage = xltpage.header.gotoBlogPage();
        blogpage.validateStructure();

        NewsPage newsPage = xltpage.header.gotoNewsPage();
        newsPage.validateStructure();

        GitHubPage gitHubPage = newsPage.footer.gotoGitHub();
        gitHubPage.title.validateTitle("Xceptance · GitHub");

        TwitterPage twitterPage = gitHubPage.gotoTwitterPage();
        twitterPage.validateStructure();

    }
}
