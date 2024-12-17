package template.pageobjects.components;

import com.xceptance.neodymium.util.SelenideAddons;
import org.junit.jupiter.api.Assertions;

public abstract class AbstractComponent<T extends AbstractComponent<T>>
{
    public abstract boolean isAvailable();

    public T ensureComponentAvailable()
    {
        SelenideAddons.wrapAssertionError(() -> {
            Assertions.assertTrue(isAvailable(), "Component could not be identified");
        });
        return (T) this;
    }

    public abstract T validateStructure();
}
