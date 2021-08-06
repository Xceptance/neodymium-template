package template.pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import io.qameta.allure.Step;

public class BlogPage extends AbstractPageObject
{
    @Step("ensure this is a blog page")
    public BlogPage isExpectedPage()
    {
        Selenide.$("#main-content.main-content").shouldBe(Condition.visible);
        return this;
    }

    @Override
    public void validateStructure()
    {
        validateThePicHeadline();
        validateTheDate();
    }

    /*
     * @Step("validate the Page Headline") public void validatePageHeadline() {
     * Selenide.$("h1.site-title").shouldHave(Condition.exactText("Passionate Testing")); ??????????????????? }
     */
    @Step("validate the headline of picture")
    public void validateThePicHeadline()
    {
        Selenide.$(" article#post-2282 h1.entry-title a[href*='one-year-one-picture']").click();

    }

    @Step("validate the date under pic")
    public void validateTheDate()
    {
        Selenide.$("time.entry-date").click();

    }

    @Step("validate the search tab")
    public void submitThePhraseInSearch()
    {
        Selenide.$("#search-container .search-field").val("searchphrase").submit();
    }
}
