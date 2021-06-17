package by.demoqa.web.pages;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AlertsPage extends AbstractPage {

    @FindBy(xpath = "//span[contains(text(),'Click Button to see alert')]/parent::div/following-sibling::div/button")
    private ExtendedWebElement clickAlertButton;

    @FindBy(id = "timerAlertButton")
    private ExtendedWebElement clickTimerAlertButton;

    @FindBy(id = "confirmButton")
    private ExtendedWebElement clickConfirmButton;

    @FindBy(id = "promtButton")
    private ExtendedWebElement promtButton;

    @FindBy(id = "confirmResult")
    private ExtendedWebElement confirmResult;

    @FindBy(id = "promptResult")
    private ExtendedWebElement promtResult;

    @FindBy(tagName = "footer")
    private ExtendedWebElement footer;

    public AlertsPage(WebDriver driver) {
        super(driver);
        setPageURL(R.CONFIG.get(Configuration.get(Configuration.Parameter.URL)) + "/alerts");
    }

    public boolean isFooterPresent(){
        return footer.isPresent();
    }

    public ExtendedWebElement getPromtResult(){
        return promtResult;
    }

    public String getConfirmResult(){
        return confirmResult.getText();
    }

    public void clickAlertButton(){
        clickAlertButton.doubleClick();
    }

    public void clickTimerAlertButton(){
        clickTimerAlertButton.click();
    }

    public void clickConfirmButton(){
        clickConfirmButton.doubleClick();
    }

    public void clickPromtButton(){
        promtButton.doubleClick();
    }
}
