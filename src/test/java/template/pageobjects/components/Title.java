package template.pageobjects.components;

import com.xceptance.neodymium.util.SelenideAddons;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.title;

public class Title extends AbstractComponent<Title>
{
    @Override
    @Step("ensure title is available")
    public Title ensureComponentAvailable()
    {
        return super.ensureComponentAvailable();
    }

    @Override
    @Step("check if title is available")
    public boolean isAvailable()
    {
        return true;
    }

    @Override
    public Title validateStructure()
    {
        return this;
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
