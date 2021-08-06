package template.pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import io.qameta.allure.Step;

public class TeamPage extends AbstractXceptancePage
{
    @Step("ensure this is a team page")
    public TeamPage isExpectedPage()
    {
        Selenide.$("div#team").shouldBe(Condition.visible);
        return this;
    }

    @Override
    public void validateStructure()
    {
        validateHeadLine();
    }

    @Step("Headline validation")
    public void validateHeadLine()
    {
        Selenide.$("h1.text-center").should(Condition.exactText("Behind the Scenes"));
    }

}
