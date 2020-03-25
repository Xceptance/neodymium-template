package util.applitools;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import com.xceptance.neodymium.NeodymiumRunner;

// this class can be deleted after the line will be included in runChild method of NeodymiumRunner
public class TestRunner extends NeodymiumRunner
{

    public TestRunner(Class<?> klass) throws InitializationError
    {
        super(klass);
    }

    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier)
    {
        notifier.addListener(new TestListener()); // the line
        super.runChild(method, notifier);
    }
}
