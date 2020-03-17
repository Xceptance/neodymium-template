/**
 * 
 */
package template.neodymium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
    protected ApplitoolsApi applitoolsApi;

    @BeforeClass
    public static void setBatch()
    {
        ApplitoolsApi.setupGlobal();
    }

    @Before
    public void beforeEach()
    {
        applitoolsApi = new ApplitoolsApi();
        // applitoolsApi = new ApplitoolsApi();
        // applitoolsApi.setupForTest();
    }

    @After
    public void afterEach()
    {
        applitoolsApi.endAssertions();
    }
}
