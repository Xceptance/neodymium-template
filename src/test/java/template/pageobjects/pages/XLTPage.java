package template.pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import io.qameta.allure.Step;

public class XLTPage extends AbstractXceptancePage
{
    @Override
    @Step("ensure this is a XLT page")
    public XLTPage isExpectedPage()
    {
        Selenide.$("#xlt-overview").shouldBe(Condition.visible);
        return this;
    }

    @Override
    public void validateStructure()
    {
        validatePageLogo();
    }

    @Step("validate the page logo")
    public void validatePageLogo()
    {
        Selenide.$("img#product-logo").shouldHave(Condition.attribute("title", "Xceptance LoadTest"));
    }

}
