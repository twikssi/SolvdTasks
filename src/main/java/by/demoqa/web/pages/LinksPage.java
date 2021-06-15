package by.demoqa.web.pages;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LinksPage extends AbstractPage {

    @FindBy(linkText = "Home")
    private ExtendedWebElement linkHome;

    @FindBy(id = "dynamicLink")
    private ExtendedWebElement linkHomeDYHmW;

    @FindBy(linkText = "Created")
    private ExtendedWebElement createLink;

    @FindBy(linkText = "No Content")
    private ExtendedWebElement noContentLink;

    @FindBy(linkText = "Moved")
    private ExtendedWebElement movedLink;

    @FindBy(linkText = "Bad Request")
    private ExtendedWebElement badRequestLink;

    @FindBy(linkText = "Unauthorized")
    private ExtendedWebElement unauthorizedLink;

    @FindBy(linkText = "Forbidden")
    private ExtendedWebElement forbiddenLink;

    @FindBy(linkText = "Not Found")
    private ExtendedWebElement notFoundLink;

    @FindBy(id = "linkResponse")
    private ExtendedWebElement linkResponse;

    public LinksPage(WebDriver driver) {
        super(driver);
        setPageURL(R.CONFIG.get(Configuration.get(Configuration.Parameter.URL)) + "/links");
    }

    public String getLinkResponseText(){
        return linkResponse.getText();
    }

    public void clickNotFoundLink(){
        notFoundLink.click();
    }

    public void clickForbiddenLink(){
        forbiddenLink.click();
    }

    public void clickUnauthorizedLink(){
        unauthorizedLink.click();
    }

    public void clickBadRequestLink(){
        badRequestLink.click();
    }

    public void clickMovedLink(){
        movedLink.click();
    }

    public void clickNoContentLink(){
        noContentLink.click();
    }

    public void clickCreateLink(){
        createLink.click();
    }

    public void clickLinkHome(){
        linkHome.click();
    }

    public void clickLinkHomeDYHmW(){
        linkHomeDYHmW.click();
    }
}
