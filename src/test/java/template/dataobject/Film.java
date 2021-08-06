package template.dataobject;

import java.util.List;

public class Film
{

    String film_id;

    String filmName;

    Double dauer;

    String yearOfPublication;

    String testId;

    public String getYearOfPublication()
    {
        return yearOfPublication;
    }

    public String getTestId()
    {
        return testId;
    }

    List<Actor> actors;

    public List<Actor> getActors()
    {
        return actors;
    }

    public String getFilm_id()
    {
        return film_id;
    }

    public String getFilmName()
    {
        return filmName;
    }

    public Double getDauer()
    {
        return dauer;
    }

}
