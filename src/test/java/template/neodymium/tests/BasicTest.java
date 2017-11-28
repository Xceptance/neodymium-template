/**
 * 
 */
package template.neodymium.tests;

import java.util.Map;

import org.junit.runner.RunWith;

import com.xceptance.neodymium.NeodymiumRunner;
import com.xceptance.neodymium.TestData;
import com.xceptance.neodymium.multibrowser.Browser;

/**
 * @author pfotenhauer
 */
@RunWith(NeodymiumRunner.class)
@Browser(
{
  "Chrome_1024x768"
})
public class BasicTest
{
    @TestData
    public Map<String, String> data;
}
