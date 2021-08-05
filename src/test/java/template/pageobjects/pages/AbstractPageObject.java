package template.pageobjects.pages;

import template.pageobjects.components.Title;

/**
 * @author pfotenhauer
 */
public abstract class AbstractPageObject
{
    public Title title = new Title();

    public void validateStructure()
    {
    }

    public abstract AbstractPageObject isExpectedPage();
}
