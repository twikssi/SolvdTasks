package by.demoqa.web.pages;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicPropertiesPage extends AbstractPage {

    @FindBy(id = "enableAfter")
    private ExtendedWebElement enableButtonAfter;

    @FindBy(id = "colorChange")
    private ExtendedWebElement colorButtonAfter;

    @FindBy(id = "visibleAfter")
    private ExtendedWebElement invisibleButtonAfter;

    public DynamicPropertiesPage(WebDriver driver) {
        super(driver);
        setPageURL(R.CONFIG.get(Configuration.get(Configuration.Parameter.URL)) + "/dynamic-properties");
    }

    public void waitObjectAppears(int standby) {
        waitUntil(ExpectedConditions.and(ExpectedConditions.elementToBeClickable(enableButtonAfter.getBy()),
                ExpectedConditions.visibilityOfAllElementsLocatedBy(invisibleButtonAfter.getBy()),
                ExpectedConditions.attributeContains(colorButtonAfter.getBy(), "class", "text-danger"))
                , standby);
    }

    public boolean isInvisibleButtonVisible() {
        return invisibleButtonAfter.isVisible(0);
    }

    public boolean isColorButtonChangeColor() {
        return colorButtonAfter.getAttribute("class").contains("text-danger");
    }

    public boolean isEnableButtonEnable() {
       return enableButtonAfter.getElement().isEnabled();
    }
}
