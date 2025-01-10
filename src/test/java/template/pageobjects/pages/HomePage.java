package template.pageobjects.pages;

import com.xceptance.neodymium.util.SelenideAddons;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage extends AbstractPageObject<HomePage>
{
    @Override
    @Step("ensure this is a homepage")
    public HomePage assertExpectedPage()
    {
        return super.assertExpectedPage();
    }

    @Override
    @Step("check if this is a homepage")
    public boolean isExpectedPage()
    {
        SelenideAddons.optionalWaitUntilCondition($("#teaser"), exist);
        return $("#teaser").exists();
    }

    @Then("^The home page should have heading, carousel, services and the company button$")
    @Step("validate the home page")
    public HomePage validateStructure()
    {
        // Verifies the company Logo and name are visible.
        $("#navigation .navbar-brand a").shouldBe(visible);

        // Verifies the Navigation bar is visible
        $("#navigation .navbar-header ul.nav").shouldBe(visible);

        // Asserts there's categories in the nav bar.
        $$("#navigation .navbar-header ul.nav > li > a").shouldHave(sizeGreaterThan(0));

        // Asserts the first headline is there.
        $("#main h1").shouldBe(matchText("[A-Z].{3,}"));

        // Verifies the "services" section is there.
        // Asserts there's at least 1 item in the list.
        $$("#main .row.strip a").shouldHave(sizeGreaterThan(0));

        // Verifies the company button is there.
        $$("p.lead > a").shouldHave(sizeGreaterThan(0));

        return this;
    }
}
