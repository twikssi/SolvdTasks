package by.demoqa.web.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProfileItem extends AbstractUIObject {

    @FindBy(xpath = ".//div[@class='rt-td'][1]")
    private ExtendedWebElement imageCells;

    @FindBy(xpath = ".//div[@class='rt-td'][2]")
    private ExtendedWebElement titleCells;

    @FindBy(xpath = ".//div[@class='rt-td'][3]")
    private ExtendedWebElement authorCells;

    @FindBy(xpath = ".//div[@class='rt-td'][4]")
    private ExtendedWebElement publisherCells;

    @FindBy(xpath = ".//div[@class='rt-td'][5]")
    private ExtendedWebElement actionCells;

    public ProfileItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getImageCells() {
        return imageCells.getText();
    }

    public String getTitleCells() {
        return titleCells.getText();
    }

    public String getAuthorCells() {
        return authorCells.getText();
    }

    public String getPublisherCells() {
        return publisherCells.getText();
    }

    public String getActionCells() {
        return actionCells.getText();
    }
}
