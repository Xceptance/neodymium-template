package template.pageobjects.pages;

import com.xceptance.neodymium.util.SelenideAddons;
import org.junit.jupiter.api.Assertions;
import template.pageobjects.components.Title;

/**
 * @author pfotenhauer
 */
public abstract class AbstractPageObject<T extends AbstractPageObject<T>>
{
    public Title title = new Title();

    public abstract boolean isExpectedPage();

    public T assertExpectedPage()
    {
        SelenideAddons.wrapAssertionError(() -> {
            Assertions.assertTrue(isExpectedPage(), "Page could not be identified");
        });
        return (T) this;
    }

    public abstract T validateStructure();
}
