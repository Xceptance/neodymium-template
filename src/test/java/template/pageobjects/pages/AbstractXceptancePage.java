package template.pageobjects.pages;

import template.pageobjects.components.Footer;
import template.pageobjects.components.Header;

public abstract class AbstractXceptancePage extends AbstractPageObject
{
    public Header header = new Header();

    public Footer footer = new Footer();
}
