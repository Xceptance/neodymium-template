package template.neodymium.tests.smoke;

import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.Neodymium;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import template.flows.OpenPageFlows;
import template.neodymium.tests.AbstractTest;

@ExtendWith(MyTestListener.class)
public class HomePageTest extends AbstractTest
{
    @NeodymiumTest
    public void testVisitingHomepage()
    {
        // Goto the home page and perform a short validation that we are on the correct page
        var homePage = OpenPageFlows.openHomePage();

        // basic validation
        homePage.validateStructure();
        homePage.title.validateTitle(Neodymium.localizedText("homepage.title"));
    }
}

class MyTestListener implements AfterTestExecutionCallback
{
    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception
    {
        System.out.println("afterTestExecution");

        // Get the test method name
        String testMethodName = extensionContext.getRequiredTestMethod().getName();
        System.out.println(testMethodName);
        System.out.println(extensionContext.getTestClass());
        System.out.println(extensionContext.getTestInstance());

        // Find the Allure result file (adjust the filename pattern as needed)
        // ... (Code to find the Allure result file as shown in the previous example) ...

        // Process the test result (e.g., read the result file, extract data)
        // ... (Your logic to process the result file) ...

        // Send the processed data to the server
        // ... (Your logic to send data to the server) ...

    }
}
