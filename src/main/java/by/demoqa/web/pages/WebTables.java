package by.demoqa.web.pages;

import by.demoqa.web.components.RegistrationForm;
import by.demoqa.web.components.UserItem;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WebTables extends AbstractPage {

    @FindBy(id = "addNewRecordButton")
    private ExtendedWebElement addButton;

    @FindBy(id = "userForm")
    private RegistrationForm registrationForm;

    @FindBy(xpath = "//div[@role='rowgroup']")
    private List<UserItem> users;

    public WebTables(WebDriver driver) {
        super(driver);
        setPageURL(R.CONFIG.get(Configuration.get(Configuration.Parameter.URL)) + "/webtables");
    }

    public void clickRegistationForm() {
        addButton.click();
    }

    public RegistrationForm getRegistationForm() {
        return registrationForm;
    }

    public List<UserItem> getUsers() {
        return users;
    }
}
