package template.pageobjects.components;

import org.junit.Assert;

import com.xceptance.neodymium.util.SelenideAddons;

import static com.codeborne.selenide.Selenide.title;

import io.cucumber.java.en.Then;
import io.qameta.allure.Step;

public class Title extends AbstractComponent
{
    public void isComponentAvailable()
    {
    }

    @Then("^The page title should be \"([^\"]*)\"$")
    @Step("validate title matches: '{title}'")
    public void validateTitle(String title)
    {
        SelenideAddons.wrapAssertionError(() -> {
            Assert.assertEquals(title, title());
        });
    }
}
