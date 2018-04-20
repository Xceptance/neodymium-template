/**
 * 
 */
package template.neodymium.tests;

import org.junit.Before;
import org.junit.runner.RunWith;

import com.codeborne.selenide.Configuration;
import com.xceptance.neodymium.NeodymiumRunner;
import com.xceptance.neodymium.multibrowser.Browser;
import com.xceptance.neodymium.util.Context;

/**
 * @author pfotenhauer
 */
@RunWith(NeodymiumRunner.class)
@Browser(
{
  "Chrome_1024x768"
})
public abstract class AbstractTest
{
    /**
     * Build it up and set the basic timeouts
     */
    @Before
    public void before()
    {
        Configuration.timeout = Context.get().configuration.timeout();
        Configuration.collectionsTimeout = 2 * Configuration.timeout;
    }
}
