package util.applitools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public class TestListener extends RunListener
{
    protected static List<String> filenames = new ArrayList<String>();

    @Override
    public void testRunFinished(Result result) throws Exception
    {
        filenames.forEach(file -> new File(file).delete());
    }
}
