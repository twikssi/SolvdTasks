package by.demoqa.web.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SubmitingForm extends AbstractUIObject {

    @FindBy(xpath = ".//div[@class='table-responsive']/table/tbody/tr/td[last()]")
    private List<ExtendedWebElement> submitResults;

    public SubmitingForm(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getNameAndLastName() {
        return submitResults.get(0).getText();
    }

    public String getEmail() {
        return submitResults.get(1).getText();
    }

    public String getGender() {
        return submitResults.get(2).getText();
    }

    public String getMobile() {
        return submitResults.get(3).getText();
    }

    public String getDateOfBirth() {
        return submitResults.get(4).getText();
    }

    public String getSubjects() {
        return submitResults.get(5).getText();
    }

    public String getHobbie() {
        return submitResults.get(6).getText();
    }

    public String getPicture() {
        return submitResults.get(7).getText();
    }

    public String getAddress() {
        return submitResults.get(8).getText();
    }

    public String getStateAndCity() {
        return submitResults.get(9).getText();
    }
}
