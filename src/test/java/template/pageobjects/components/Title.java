package template.pageobjects.components;

import static com.codeborne.selenide.Selenide.title;

import org.junit.Assert;

import com.xceptance.neodymium.util.SelenideAddons;

import cucumber.api.java.en.Then;

public class Title extends AbstractComponent
{
    public void isComponentAvailable()
    {
    }

    @Then("^The page title should be \"([^\"]*)\"$")
    public void validateTitle(String title)
    {
        SelenideAddons.wrapAssertionError(() -> {
            Assert.assertEquals(title, title());
        });
    }
}
