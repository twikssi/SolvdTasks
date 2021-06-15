package by.demoqa.web.pages;

import by.demoqa.web.components.SubmitingForm;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FormPage extends AbstractPage {

    @FindBy(id = "firstName")
    private ExtendedWebElement firstNameField;

    @FindBy(id = "lastName")
    private ExtendedWebElement lastNameField;

    @FindBy(xpath = "//input[@id='userEmail']")
    private ExtendedWebElement emailField;

    @FindBy(xpath = "//div[@id='genterWrapper']//label")
    private List<ExtendedWebElement> genderLabels;

    @FindBy(xpath = "//input[@id='userNumber']")
    private ExtendedWebElement mobileNumberField;

    @FindBy(id = "dateOfBirthInput")
    private ExtendedWebElement dateOfBirthField;

    @FindBy(xpath = "//div[@id = 'subjectsWrapper']//input")
    private ExtendedWebElement subjectField;

    @FindBy(xpath = "//input[@type='checkbox']//following-sibling::label")
    private List<ExtendedWebElement> hobbieCheckboxes;

    @FindBy(id = "uploadPicture")
    private ExtendedWebElement pictureUploading;

    @FindBy(xpath = "//textarea[@id='currentAddress']")
    private ExtendedWebElement currentAddressArea;

    @FindBy(xpath = "//div[@id='state']//input")
    private ExtendedWebElement stateInput;

    @FindBy(xpath = "//div[@id='city']//input")
    private ExtendedWebElement cityInput;

    @FindBy(xpath = "//button[@id='submit']")
    private ExtendedWebElement buttonSubmit;

    @FindBy(className = "modal-content")
    private SubmitingForm submitingForm;

    public FormPage(WebDriver driver) {
        super(driver);
        setPageURL(R.CONFIG.get(Configuration.get(Configuration.Parameter.URL)) + "/automation-practice-form");
    }

    public SubmitingForm getSubmitingForm(){
        return submitingForm;
    }

    public void clickSubmitButton(){
        buttonSubmit.click();
    }

    public void inputCity(String city) {
        cityInput.type(city);
        cityInput.sendKeys(Keys.ARROW_DOWN);
        cityInput.sendKeys(Keys.ENTER);
    }

    public void inputState(String state) {
        stateInput.type(state);
        stateInput.sendKeys(Keys.ENTER);
    }

    public void inputFirstNameField(String firstName) {
        firstNameField.type(firstName);
    }

    public void inputEmailField(String email) {
        emailField.type(email);
    }

    public void inputMobileNumberField(String mobileNumber) {
        mobileNumberField.type(mobileNumber);
    }

    public void inputLastNameField(String lastName) {
        lastNameField.type(lastName);
    }

    public void inputCurrentAddressArea(String currentAddress) {
        currentAddressArea.type(currentAddress);
    }

    public void uploadPicture(String path) {
        pictureUploading.attachFile(path);
    }

    public void inputSubject(String subject) {
        subjectField.type(subject);
        subjectField.sendKeys(Keys.ENTER);
    }

    public void inputDateOfBirth(String dateOfBirth) {
        Actions actions = new Actions(driver);
        actions.click(dateOfBirthField.getElement());
        actions.perform();
        actions.click(dateOfBirthField.getElement());
        actions.doubleClick(dateOfBirthField.getElement());
        actions.perform();
        actions.sendKeys(dateOfBirth);
        actions.perform();
        dateOfBirthField.sendKeys(Keys.ENTER);
    }

    private ExtendedWebElement clickRandomElement(List<ExtendedWebElement> extendedWebElements){
        List<ExtendedWebElement> gendersList = new ArrayList<>(extendedWebElements);
        Collections.shuffle(gendersList);
        gendersList.get(0).click();
        return gendersList.get(0);
    }

    public ExtendedWebElement clickRandomHobbie() {
       return clickRandomElement(hobbieCheckboxes);
    }

    public ExtendedWebElement clickRandomGender() {
       return clickRandomElement(genderLabels);
    }

    public String getColorFieldEmail(){
       return emailField.getElement().getCssValue("border-color");
    }

    public String getColorName(){
        return firstNameField.getElement().getCssValue("border-color");
    }

    public String getColorMobileNumberField(){
        return mobileNumberField.getElement().getCssValue("border-color");
    }
}
