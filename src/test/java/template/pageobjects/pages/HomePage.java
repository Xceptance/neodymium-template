package template.pageobjects.pages;

import com.xceptance.neodymium.util.SelenideAddons;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage extends AbstractPageObject<HomePage>
{
    @Override
    @Step("ensure this is a homepage")
    public HomePage reached()
    {
        return super.reached();
    }

    @Override
    @Step("check if this is a homepage")
    public boolean isExpectedPage()
    {
        SelenideAddons.optionalWaitUntilCondition($("#service-areas"), exist);
        return $("#service-areas").exists();
    }

    @Then("^The home page should have heading, carousel, services and the company button$")
    @Step("validate the home page")
    public HomePage validateStructure()
    {
        // Verifies the company Logo and name are visible.
        $("#navigation .navbar-brand a[title=Home]").shouldBe(visible);

        // Verifies the Navigation bar is visible
        $("#navigation .navbar-header ul.nav").shouldBe(visible);

        // Asserts there's categories in the nav bar.
        $$("#navigation .navbar-header ul.nav > li > a").shouldHave(sizeGreaterThan(0));

        // Asserts the first headline is there.
        $("#service-areas .landing-intro > h1").shouldBe(matchText("[A-Z].{3,}"));

        // Asserts the animated carousel is there.
        $("#myCarousel").shouldBe(visible);

        // Verifies the "services" section is there.
        // Asserts there's at least 1 item in the list.
        $$("#service-areas .container .thumbnail").shouldHave(sizeGreaterThan(0));

        // Verifies the company button is there.
        $$("#xlt-background .container p.lead > a.btn-primary").shouldHave(sizeGreaterThan(0));

        return this;
    }
}
