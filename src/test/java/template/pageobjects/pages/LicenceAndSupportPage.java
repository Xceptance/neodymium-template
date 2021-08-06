package template.pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import io.qameta.allure.Step;

public class LicenceAndSupportPage extends AbstractXceptancePage
{

    @Override
    public void validateStructure()
    {
        validateStructure();
        validatTheHeadLine1();
        validateTheHeadLine2();

    }

    @Step("ensure this is a licence and support page")
    public LicenceAndSupportPage isExpectedPage()
    {
        Selenide.$("div#licensing-pricing").shouldBe(Condition.visible);
        return this;
    }

    @Step("validate the headline")
    public void validatTheHeadLine1()
    {
        Selenide.$("#main h1").shouldHave(Condition.exactText("Licencing"));
    }

    @Step("test the button of buy online")
    public void TestBuyOnline()
    {
        Selenide.$("tr.td a.btn.btn-primary, a[href*='EUR&runtime=4' ]").shouldHave(Condition.exactText("Buy Online"));
    }

    @Step("validate the Headline")
    public void validateTheHeadLine2()
    {
        Selenide.$("div#support-and-training h1").shouldHave(Condition.exactText("Support & Training"));
    }

}
