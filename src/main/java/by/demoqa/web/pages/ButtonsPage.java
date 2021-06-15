package by.demoqa.web.pages;


import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ButtonsPage extends AbstractPage {

    @FindBy(xpath = "//button[@id='doubleClickBtn']")
    private ExtendedWebElement doubleClickButton;

    @FindBy(xpath = "//button[@id='rightClickBtn']")
    private ExtendedWebElement rightClickButton;

    @FindBy(xpath = "//button[text()='Click Me']")
    private ExtendedWebElement clickMeButton;

    @FindBy(xpath = "//p[@id='doubleClickMessage']")
    private ExtendedWebElement resultTextDoubleClickButton;

    @FindBy(xpath = "//p[@id='rightClickMessage']")
    private ExtendedWebElement resultTextRightClickButton;

    @FindBy(xpath = "//p[@id='dynamicClickMessage']")
    private ExtendedWebElement resultTextClickButton;

    public ButtonsPage(WebDriver driver) {
        super(driver);
        setPageURL(R.CONFIG.get(Configuration.get(Configuration.Parameter.URL)) + "/buttons");
    }

    public void clickDoubleClickButton() {
        doubleClickButton.doubleClick();
    }

    public void clickRightClickButton() {
        rightClickButton.rightClick();
    }

    public void clickButton() {
        clickMeButton.click();
    }

    public String getTextDoubleClickButton() {
        return resultTextDoubleClickButton.getElement().getText();
    }

    public String getTextRightClickButton() {
        return resultTextRightClickButton.getElement().getText();
    }

    public String getTextClickButton() {
        return resultTextClickButton.getElement().getText();
    }

    public boolean isElementResultTextClickButtonPresent() {
        return !isElementNotPresent(resultTextClickButton);
    }

    public boolean isElementResultTextDoubleClickButtonPresent() {
        return !isElementNotPresent(resultTextDoubleClickButton);
    }

    public boolean isElementResultTextRightClickButtonPresent() {
        return !isElementNotPresent(resultTextRightClickButton);
    }
}
