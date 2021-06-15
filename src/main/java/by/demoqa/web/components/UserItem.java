package by.demoqa.web.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class UserItem extends AbstractUIObject {

    @FindBy(xpath = ".//div[@role='gridcell'][1]")
    private ExtendedWebElement nameCell;

    @FindBy(xpath = ".//div[@role='gridcell'][2]")
    private ExtendedWebElement lastNameCell;

    @FindBy(xpath = ".//div[@role='gridcell'][3]")
    private ExtendedWebElement ageCell;

    @FindBy(xpath = ".//div[@role='gridcell'][4]")
    private ExtendedWebElement emailCell;

    @FindBy(xpath = ".//div[@role='gridcell'][5]")
    private ExtendedWebElement salaryCell;

    @FindBy(xpath = ".//div[@role='gridcell'][6]")
    private ExtendedWebElement departmentCell;

    @FindBy(xpath = ".//span[starts-with(@id,'edit-record')]")
    private ExtendedWebElement buttonEdit;

    @FindBy(xpath = ".//span[@title='Delete']")
    private ExtendedWebElement buttonDeleteUser;

    public UserItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickButtonDeleteUser() {
        buttonDeleteUser.click();
    }

    public void clickEditUser() {
        buttonEdit.click();
    }

    public String getNameCell() {
        return nameCell.getText();
    }

    public String getLastNameCell() {
        return lastNameCell.getText();
    }

    public String getAgeCell() {
        return ageCell.getText();
    }

    public String getEmailCell() {
        return emailCell.getText();
    }

    public String getSalaryCell() {
        return salaryCell.getText();
    }

    public String getDepartmentCell() {
        return departmentCell.getText();
    }
}
