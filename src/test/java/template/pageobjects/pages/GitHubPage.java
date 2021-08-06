package template.pageobjects.pages;

import com.codeborne.selenide.Condition;
//package template.pageobjects.pages;
import com.codeborne.selenide.Selenide;

import io.qameta.allure.Step;

public class GitHubPage extends AbstractPageObject
{
    @Step("ensure this is a github page")
    public GitHubPage isExpectedPage()
    {
        Selenide.$("header svg.octicon-mark-github").shouldBe(Condition.visible);
        return this;
    }

    @Override
    public void validateStructure()
    {
        validateCompanyHeadLine();
    }

    public TwitterPage gotoTwitterPage()
    {
        Selenide.$("a[href*='twitter.com']").click();
        return new TwitterPage().isExpectedPage();
    }

    @Step("validate the company headline")
    public void validateCompanyHeadLine()
    {
        Selenide.$("h1.h2").should(Condition.exactText("Xceptance"));
    }
}
