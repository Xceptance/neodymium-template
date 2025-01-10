package template.pageobjects.components;

import com.xceptance.neodymium.util.SelenideAddons;
import org.junit.jupiter.api.Assertions;

public abstract class AbstractComponent<T extends AbstractComponent<T>>
{
    public abstract boolean isComponentAvailable();

    public T assertComponentAvailable()
    {
        SelenideAddons.wrapAssertionError(() -> {
            Assertions.assertTrue(isComponentAvailable(), "Component could not be identified");
        });
        return (T) this;
    }

    public abstract T validateStructure();
}
