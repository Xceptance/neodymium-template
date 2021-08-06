package template.pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;

public class AboutUsPage extends AbstractXceptancePage
{
    @Override
    public void validateStructure()
    {
        validatePageHeadLine();
        validatePageDescription();
        validateTitleOfTheLogo();
        validateQuickFactsHeadLine();
        validateAboutUsPageTitles();
        validateQuickFacts();
    }

    @Step("ensure this is a about us page")
    public AboutUsPage isExpectedPage()
    {
        Selenide.$("div#about-us").shouldBe(Condition.visible);
        return this;
    }

    @Step("validate page headline")
    public void validatePageHeadLine()
    {
        Selenide.$("h1").shouldHave(Condition.exactText("About Us"));
    }

    @Step("validate the page description")
    public void validatePageDescription()
    {
        Selenide.$("p.lead")
                .shouldHave(Condition.exactText("We are dedicated experts for software testing: together we comprise more than 200 years of software testing experience and we absolutely love what we do."));
    }

    @Step("validate the logo title")
    public void validateTitleOfTheLogo()
    {
        Selenide.$(".navbar-brand a img").shouldHave(Condition.attribute("title", "Xceptance company logo"));
    }

    @Step("validate quick facts")
    public void validateQuickFacts()
    {
        validateQuickFactsHeadLine();
        // validate key-value pairs
        Selenide.$$("td.key").shouldHaveSize(6);
        Selenide.$$("td.value").shouldHaveSize(6);

        validateQuickFactsPair("Founded", "2004");
        validateQuickFactsPair("Company Size", "50 employees");
        validateQuickFactsPair("Headquarters", "Jena (Germany)");
        validateQuickFactsPair("Offices", "Jena and Erfurt (Germany) & Cambridge (USA)");
        validateQuickFactsPair("Customers", "More than 450");
        validateQuickFactsPair("Salesforce Commerce Cloud Partner", "Since 2005");
    }

    @Step("validate the Headline of Quick Facts")
    private void validateQuickFactsHeadLine()
    {
        Selenide.$$("h2").filter(Condition.exactText("Quick Facts")).shouldHaveSize(1);
    }

    @Step("validate quick facts key-values ")
    private void validateQuickFactsPair(String key, String value)
    {
        SelenideElement keyElement = Selenide.$$("td.key").filter(Condition.exactText(key)).shouldHaveSize(1).first();
        keyElement.closest("tr").find("td.value").shouldHave(Condition.exactText(value));
    }

    @Step("validate the undertitles in page About Us")
    public void validateAboutUsPageTitles()
    {
        validateSubHeadLines("We Know Testing");
        validateSubHeadLines("We are Flexible and Efficient");
        validateSubHeadLines("We are Trusted By Many");
        validateSubHeadLines("We Collaborate");
        validateSubHeadLines("We Share and Contribute to the Testing Community");
        validateSubHeadLines("We are a Team");
    }

    @Step("validate the sub-headlines")
    private void validateSubHeadLines(String headLine)
    {
        Selenide.$$("h2").filter(Condition.exactText(headLine)).shouldHaveSize(1);
    }

}
