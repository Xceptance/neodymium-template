package template.neodymium.tests.smoke;

import org.junit.Assert;
import org.junit.Test;

import com.xceptance.neodymium.module.statement.testdata.DataSet;
import com.xceptance.neodymium.util.DataUtils;

import template.dataobject.DataObject;
import template.neodymium.tests.AbstractTest;

public class FilmDataTest extends AbstractTest
{

    @Test
    @DataSet(1)
    public void testDataSet1()
    {
        DataObject dataobject = DataUtils.get(DataObject.class);
        dataobject.getFilm().getFilmName();
        dataobject.getFilm().getActors().get(0).getName();
        dataobject.getFilm().getActors().get(1).getName();
        dataobject.getFilm().getYearOfPublication();
        Assert.assertEquals("Film name does not match", "Fight Club", dataobject.getFilm().getFilmName());
        // QUESTION
        Assert.assertEquals("The first actor is", "Brad Pitt", dataobject.getFilm().getActors().get(0).getName());
        Assert.assertEquals("The second is actor is", "Johny Depp", dataobject.getFilm().getActors().get(1).getName());
        Assert.assertEquals("The year of publication matches", "1992", dataobject.getFilm().getYearOfPublication());
    }

    @Test
    @DataSet(2)
    public void testDataSet2()
    {
        DataObject dataobject = DataUtils.get(DataObject.class);
        dataobject.getFilm().getTestId();
        dataobject.getFilm().getFilmName();
        dataobject.getFilm().getActors().get(0).getName();

        dataobject.getFilm().getActors().get(1).getName();
        dataobject.getFilm().getYearOfPublication();
        Assert.assertEquals("Film names don't match", "Black Widow", dataobject.getFilm().getFilmName());
        // Assert.assertEquals("the testid matches", "Second Movie", dataobject.getFilm().getTestId());
    }
}