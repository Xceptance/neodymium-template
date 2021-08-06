package template.pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import io.qameta.allure.Step;

public class NewsPage extends AbstractXceptancePage
{
    @Step("validate the News Page Headline")
    public void validateNewsPageHeadLine()
    {
        Selenide.$("h1.news-headline").shouldHave(Condition.exactText("News"));
    }

    @Step("ensure this is a news page")
    public NewsPage isExpectedPage()
    {
        Selenide.$("div#news-overview").shouldBe(Condition.visible);
        return this;
    }

    @Override
    public void validateStructure()
    {
        validatePreviousNews();
        validateTheAllNews();
        validateNewsPageHeadLine();
    }

    @Step("validate the previous news Line")
    public void validatePreviousNews()
    {
        Selenide.$("h3.news-headline").should(Condition.exactText("Previous News"));
    }

    @Step("validate the All News Line ")
    public void validateTheAllNews()
    {
        Selenide.$("p#preview-end").should(Condition.exactText("All News"));
    }
}
