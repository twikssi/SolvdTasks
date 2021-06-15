package by.demoqa.web.pages;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//input[@id='userName']")
    private ExtendedWebElement userNameInput;

    @FindBy(xpath = "//input[@id='password']")
    private ExtendedWebElement passwordInput;

    @FindBy(xpath = "//button[@id='login']")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//h5[contains(text(),'Login in Book Store')]")
    private ExtendedWebElement loginInBookStoreH5;

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageURL(R.CONFIG.get(Configuration.get(Configuration.Parameter.URL)) + "/login");
    }

    public ExtendedWebElement getLoginInBookStoreH5() {
        return loginInBookStoreH5;
    }

    public void inputName(String name) {
        userNameInput.type(name);
    }

    public void inputPassword(String name) {
        passwordInput.type(name);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

}
