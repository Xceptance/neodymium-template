package template.pageobjects.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import io.qameta.allure.Step;
import template.pageobjects.pages.GitHubPage;

public class Footer extends AbstractComponent
{
    @Override
    public void isComponentAvailable()
    {
        validateFootHeadLine1();// ist es nötig?
        validateFootHeadLine2();
    }

    /*
     * @Step("go to the twitter page") public TwitterPage gotoTwitterPage() {
     * Selenide.$("a[href*='twitter.com']").click(); return new TwitterPage().isExpectedPage(); }
     */

    @Step("go to github")
    public GitHubPage gotoGitHub()
    {
        Selenide.$("a[href*='github.com']").click();
        return new GitHubPage().isExpectedPage();
    }

    @Step("validate the info under the page")
    public void validateFootHeadLine1()
    {
        Selenide.$$("div# addresses h3").filter(Condition.exactText("Jena, Germany")).shouldHaveSize(1).first();
    }

    @Step("validate the info under the page")
    public void validateFootHeadLine2()
    {
        Selenide.$$("div# addresses h3").filter(Condition.exactText("Cambridge, MA, USA")).shouldHaveSize(1).first();
    }

}