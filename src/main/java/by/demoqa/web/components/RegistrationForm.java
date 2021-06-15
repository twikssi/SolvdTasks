package by.demoqa.web.components;

import by.demoqa.beans.User;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class RegistrationForm extends AbstractUIObject {

    @FindBy(xpath = ".//input[@id = 'firstName']")
    private ExtendedWebElement firstNameField;

    @FindBy(xpath = ".//input[@id = 'lastName']")
    private ExtendedWebElement lastNameField;

    @FindBy(xpath = ".//input[@id = 'userEmail']")
    private ExtendedWebElement emailField;

    @FindBy(xpath = ".//input[@id = 'age']")
    private ExtendedWebElement ageField;

    @FindBy(xpath = ".//input[@id = 'salary']")
    private ExtendedWebElement salaryField;

    @FindBy(xpath = ".//input[@id = 'department']")
    private ExtendedWebElement departmentField;

    @FindBy(xpath = ".//button[@id = 'submit']")
    private ExtendedWebElement submitButton;

    public RegistrationForm(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void fillRegistrationForm(User user) {
        inputNameField(user.getName());
        inputlastNameField(user.getLastName());
        inputAgeField(user.getAge());
        inputEmailField(user.getEmail());
        inputSalaryField(user.getSalary());
        inputDepartmentField(user.getDepartment());
        submitButtonClick();
    }

    public void inputNameField(String name) {
        firstNameField.type(name);
    }

    public void inputlastNameField(String lastName) {
        lastNameField.type(lastName);
    }

    public void inputAgeField(int age) {
        ageField.type(String.valueOf(age));
    }

    public void inputSalaryField(long salary) {
        salaryField.type(String.valueOf(salary));
    }

    public void inputDepartmentField(String department) {
        departmentField.type(department);
    }

    public void inputEmailField(String email) {
        emailField.type(email);
    }

    public void submitButtonClick() {
        submitButton.click();
    }
}
