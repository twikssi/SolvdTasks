package by.demoqa.web.pages;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CheckBoxPage extends AbstractPage {

    @FindBy(xpath = "//button[@title='Expand all']")
    private ExtendedWebElement expandButton;

    @FindBy(xpath = "//button[@title='Collapse all']")
    private ExtendedWebElement collapseButton;

    @FindBy(xpath = "//ol/li")
    private List<ExtendedWebElement> checkboxList;

    @FindBy(xpath = "//label[@for='tree-node-home']")
    private ExtendedWebElement checkboxHome;

    @FindBy(xpath = "//span[@class='text-success']")
    private List<ExtendedWebElement> resultCheckboxList;

    @FindBy(xpath = "//label[@for='tree-node-documents']")
    private ExtendedWebElement checkboxDocuments;

    @FindBy(className = "rct-icon-half-check")
    private ExtendedWebElement checkboxHomeHalfCheck;

    @FindBy(xpath = "//label[@for='tree-node-downloads']")
    private ExtendedWebElement checkboxDownloads;

    public CheckBoxPage(WebDriver driver) {
        super(driver);
        setPageURL(R.CONFIG.get(Configuration.get(Configuration.Parameter.URL)) + "/checkbox");
    }

    public boolean isCheckboxHomeHalfCheckExist() {
        return !isElementNotPresent(checkboxHome.findExtendedWebElement(By.xpath("./span[1]//*[@class='rct-icon rct-icon-half-check']")));
    }

    public void clickCheckboxDownloads() {
        checkboxDownloads.click();
    }

    public void clickCheckboxDocuments() {
        checkboxDocuments.click();
    }

    public List<String> getResultCheckboxList() {
        return resultCheckboxList.stream().map(ExtendedWebElement::getText).collect(Collectors.toList());
    }

    public void clickCheckBoxHome() {
        checkboxHome.click();
    }

    public int getSizeCheckBoxList() {
        return checkboxList.size();
    }

    public void clickExpandButton() {
        expandButton.click();
    }

    public void clickCollapseButton() {
        collapseButton.click();
    }


}
