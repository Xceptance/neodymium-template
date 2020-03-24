/**
 * 
 */
package template.neodymium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import com.xceptance.neodymium.NeodymiumRunner;
import com.xceptance.neodymium.module.statement.browser.multibrowser.Browser;

import util.applitools.ApplitoolsApi;

/**
 * @author pfotenhauer
 */
@RunWith(NeodymiumRunner.class)
@Browser("Chrome_1024x768")
public abstract class AbstractTest
{
    @Rule
    public TestName name = new TestName();

    @Before
    public void beforeEach()
    {
        ApplitoolsApi.openEyes(name.getMethodName());
    }

    @After
    public void afterEach()
    {
        ApplitoolsApi.endAssertions();
    }
}
