package util.applitools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

public class BatchHelper
{
    private static String filename = "target/batch.properties";

    public static String addBatch(String batchName)
    {
        String batchId = "";
        try
        {
            new File(filename.replaceAll("batch", batchName)).createNewFile();
            OutputStream output = new FileOutputStream(filename.replaceAll("batch", batchName));

            Properties prop = new Properties();
            batchId = batchName + new Date().toString();
            // set the properties value
            prop.setProperty("batch." + batchName, batchId);

            // save properties to project root folder
            prop.store(output, null);
        }
        catch (IOException io)
        {
            io.printStackTrace();
        }
        return batchId;
    }

    public static String getBatch(String batchName)
    {
        String batchId = null;
        try
        {
            new File(filename.replaceAll("batch", batchName)).createNewFile();
            InputStream input = new FileInputStream(filename.replaceAll("batch", batchName));
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            batchId = prop.getProperty("batch." + batchName);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return batchId;

    }
}
