package by.demoqa.web.pages;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextBoxPage extends AbstractPage {

    @FindBy(id = "userName")
    private ExtendedWebElement fieldInputUserName;

    @FindBy(id = "userEmail")
    private ExtendedWebElement fieldInputUserEmail;

    @FindBy(id = "currentAddress")
    private ExtendedWebElement textAreaInputUserCurrentAddress;

    @FindBy(id = "permanentAddress")
    private ExtendedWebElement textAreaInputUserPermanentAddress;

    @FindBy(id = "submit")
    private ExtendedWebElement sendInformationButton;

    @FindBy(className = "mb-1")
    private List<ExtendedWebElement> listSignInResult;

    @FindBy(id = "name")
    private ExtendedWebElement resultName;

    @FindBy(id = "email")
    private ExtendedWebElement resultEmail;

    @FindBy(xpath = "//p[@id='currentAddress']")
    private ExtendedWebElement resultCurrentAddress;

    @FindBy(xpath = "//p[@id='permanentAddress']")
    private ExtendedWebElement resultPermanentAddress;

    private Pattern resultPattern = Pattern.compile("^[\\w\\s]+:\\s*(.*)$");

    public TextBoxPage(WebDriver driver) {
        super(driver);
        setPageURL(R.CONFIG.get(Configuration.get(Configuration.Parameter.URL)) + "/text-box");
    }

    public String getResultEmail() {
        Matcher matcher = resultPattern.matcher(resultEmail.getText());
        if (matcher.find()) return matcher.group(1);
        return "";
    }

    public String getResultCurrentAddress() {
        Matcher matcher = resultPattern.matcher(resultCurrentAddress.getText());
        if (matcher.find()) return matcher.group(1);
        return "";
    }

    public String getResultName() {
        Matcher matcher = resultPattern.matcher(resultName.getText());
        if (matcher.find()) return matcher.group(1);
        return "";
    }

    public String getResultPermanentAddress() {
        Matcher matcher = resultPattern.matcher(resultPermanentAddress.getText());
        if (matcher.find()) return matcher.group(1);
        return "";
    }

    public void inputUserName(String name) {
        fieldInputUserName.type(name);
    }

    public void inputUserEmail(String email) {
        fieldInputUserEmail.type(email);
    }

    public void inputCurrentAdress(String currentAdress) {
        textAreaInputUserCurrentAddress.type(currentAdress);
    }

    public void inputPermanentAdress(String permanentAdress) {
        textAreaInputUserPermanentAddress.type(permanentAdress);
    }

    public void clickSentInformatonButton() {
        sendInformationButton.click();
    }

    public boolean isWrongEmailPresent() {
        return fieldInputUserEmail.getAttribute("class").contains("field-error");
    }

    public boolean islistSignInResultEmpty() {
        return listSignInResult.isEmpty();
    }

}
