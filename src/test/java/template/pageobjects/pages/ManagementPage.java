package template.pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import io.qameta.allure.Step;

public class ManagementPage extends AbstractXceptancePage
{
    @Step("ensure this is a management page")
    public ManagementPage isExpectedPage()
    {
        Selenide.$("div#management").shouldBe(Condition.visible);
        return this;
    }

    @Step("validate the management page headline ")
    public void validatePageHeadline()
    {
        Selenide.$("h1.text-center").should(Condition.exactText("Management"));
    }

    @Override
    @Step("validate structure of management page")
    public void validateStructure()
    {
        validatePageHeadline();
    }
}
