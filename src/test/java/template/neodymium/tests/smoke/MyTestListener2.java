package template.neodymium.tests.smoke;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import java.io.File;

public class MyTestListener2 implements TestExecutionListener
{
    /*
    IT WORKS! (for JUnit5)

    it's called too often because it is called for:
    - junit jupyter,
    - junit vintage engine
    - the test class itself
    - each test method twice

    but can check if a new result was written and can use this.

    How to set up:
    - create Listener which implements TestExecutionListener
    - create org.junit.platform.launcher.TestExecutionListener file in src/test/resources/META-INF/services/
      containing the listener with package like template.neodymium.tests.smoke.MyTestListener2

    How does it work:
    - custom listener can process the files after each test execution if necessary
    - triggered by maven surefire

    What remains:
    - filter to get only the results.json files (perhaps also other files like the gifs)
    - only process the results of the current test:
        - force allure to name the file after the test name or
        - process only the most recent file or
        - check the results files for the current test or
        - keep track of the processed files to only get the newest ones (like in storage singleton)
     - process the files according to the PO decision

     JUnit4:
     - probably this should do it:
        https://stackoverflow.com/questions/14771668/running-code-before-and-after-all-tests-in-a-surefire-execution
        https://maven.apache.org/surefire/maven-surefire-plugin/examples/junit.html#Using_custom_listeners_and_reporters
     */

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult)
    {
        System.out.println("\n\n");
        System.out.println("#############################################################");
        System.out.println("executionFinished");

        // Get the test method name
        System.out.println(testIdentifier.getDisplayName());
        System.out.println(testExecutionResult.getStatus());

        // Find the Allure result file (adjust the filename pattern as needed)
        // ... (Code to find the Allure result file as shown in previous examples) ...

        // Process the test result
        // ... (Your logic to process the result file) ...

        printAllFiles();
        System.out.println("\n\n");
    }

    private static void printAllFiles()
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
