package template.neodymium.tests.smoke;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class MyRunListener extends RunListener
{
    /*
    this could work for JUnit4
     */

    @Override
    public void testFinished(Description description) throws Exception
    {
        System.out.println("MyRunListener");
        System.out.println("testFinished");
    }

    @Override
    public void testFailure(Failure failure) throws Exception
    {
        System.out.println("MyRunListener");
        System.out.println("testFailure");
    }

    @Override
    public void testAssumptionFailure(Failure failure)
    {
        System.out.println("MyRunListener");
        System.out.println("testAssumptionFailure");
    }
}
