package by.demoqa.web.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DateOfBirth extends AbstractUIObject {

    @FindBy(xpath = ".//select[@class='react-datepicker__month-select']")
    private ExtendedWebElement monthSelect; //todo mounth

    @FindBy(xpath = ".//select[@class='react-datepicker__year-select']")
    private ExtendedWebElement yearSelect; //todo mounth

    @FindBy(xpath = "//div[@class='react-datepicker__week']/child::div")
    private List<ExtendedWebElement> daysSelect; //todo mounth

    public DateOfBirth(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    //todo remove days
    public void getMonthDays(String day){
        daysSelect.stream().filter(a->a.getText().equals(day)).findAny().get().click();
    }

    public void inputYear(String year){
        yearSelect.select(year);
    }

    public void inputMonth(int mounthNumber){
        monthSelect.select(mounthNumber);
    }
}
