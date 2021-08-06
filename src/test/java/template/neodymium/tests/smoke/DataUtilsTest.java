package template.neodymium.tests.smoke;

import org.junit.Assert;
import org.junit.Test;

import com.xceptance.neodymium.util.DataUtils;

import template.dataobject.DataObject;
import template.dataobject.GlobalTestData;
import template.neodymium.tests.AbstractTest;

public class DataUtilsTest extends AbstractTest
{
    @Test
    public void test()
    {
        // Assert.assertEquals("ids don't match", "76543", DataUtils.asString("id"));
        // Assert.assertEquals("numbers don't match", 12345, DataUtils.asInt("number"));
        DataObject dataobject = DataUtils.get(DataObject.class);

        // Assert.assertEquals("ids don't match", "76543", dataobject.getId());
        Assert.assertEquals("numbers don't match", Integer.valueOf(12345), dataobject.getNumber());
        Assert.assertEquals("active does not match", false, dataobject.getIsActive());

        GlobalTestData testdataobject = DataUtils.get(GlobalTestData.class);

        Assert.assertEquals("The ids don't match", "12345", testdataobject.getEmployee_id());
        Assert.assertEquals("names don't match", "Doshi", testdataobject.getName());
        Assert.assertEquals("salaries dont't match", Integer.valueOf(2000), testdataobject.getSalary());
        Assert.assertEquals("active info does not match", false, testdataobject.stillActive());
    }

}
