package template.neodymium.tests.smoke;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.Neodymium;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import template.flows.OpenPageFlows;
import template.neodymium.tests.AbstractTest;

import java.io.File;

import static template.neodymium.tests.smoke.util.printAllFiles;

//@ExtendWith(MyTestListener.class)
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

    @NeodymiumTest
    public void secondTest()
    {
        Selenide.open("https://www.google.de/");
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

        printAllFiles();
    }
}

class util
{
    public static void printAllFiles()
    {
        File folder = new File("./target/allure-results");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null)
        {
            System.out.println(listOfFiles.length);

            for (File listOfFile : listOfFiles)
            {
                if (listOfFile.isFile())
                {
                    System.out.println("File " + listOfFile.getName());
                }
                else if (listOfFile.isDirectory())
                {
                    System.out.println("Directory " + listOfFile.getName());
                }
            }
        }
    }
}
