package template.pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import io.qameta.allure.Step;

public class TwitterPage extends AbstractPageObject
{
    @Step("ensure this is a twitter page")
    public TwitterPage isExpectedPage()
    {
        Selenide.$("a[aria-label='Twitter']").shouldBe(Condition.visible);
        return this;
    }

    @Override
    @Step("validate structure of twitter page")
    public void validateStructure()
    {
        Selenide.$("div[data-testid='primaryColumn'] h2").shouldHave(Condition.exactText("Xceptance"));
    }

}
