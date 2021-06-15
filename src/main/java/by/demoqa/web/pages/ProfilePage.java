package by.demoqa.web.pages;

import by.demoqa.web.components.ProfileItem;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends AbstractPage {

    @FindBy(xpath = "//div[@class='rt-tr-group']")
    private List<ProfileItem> profileItemList;

    @FindBy(id = "userName-value")
    private ExtendedWebElement registeredUserNamelabel;

    @FindBy(xpath = "//label[@id='userName-value']/following-sibling::button")
    private ExtendedWebElement logoutButton;

    public ProfilePage(WebDriver driver) {
        super(driver);
        setPageURL(R.CONFIG.get(Configuration.get(Configuration.Parameter.URL)) + "/profile");
    }

    public List<ProfileItem> getProfilePageList() {
        return profileItemList;
    }

    public void clickLogouButton() {
        logoutButton.click();
    }

    public String getRegisteredUserName() {
        return registeredUserNamelabel.getText();
    }

}
