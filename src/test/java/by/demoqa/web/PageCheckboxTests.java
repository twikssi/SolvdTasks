package by.demoqa.web;

import by.demoqa.web.pages.CheckBoxPage;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PageCheckboxTests extends AbstractTest {

    @Test
    @MethodOwner(owner = "Andrew")
    public void testCollapseAndExpandButton() {
        int expectedAllCheckbox = 17;
        int expectedChechboxOne = 1;

        CheckBoxPage checkBoxPage = new CheckBoxPage(getDriver());
        checkBoxPage.open();
        Assert.assertTrue(checkBoxPage.isPageOpened());
        Assert.assertEquals(checkBoxPage.getSizeCheckBoxList(), expectedChechboxOne, "There is more then one checkbox");

        checkBoxPage.clickExpandButton();
        Assert.assertEquals(checkBoxPage.getSizeCheckBoxList(), expectedAllCheckbox, "There are less then all checkboxes");

        checkBoxPage.clickCollapseButton();
        Assert.assertEquals(checkBoxPage.getSizeCheckBoxList(), expectedChechboxOne, "There is more then one checkbox");
    }

    @Test()
    @MethodOwner(owner = "Andrew")
    public void testAllSelectedCheckboxes() {
        List<String> expectedChechboxList = List.of(
                "home",
                "desktop",
                "notes",
                "commands",
                "documents",
                "workspace",
                "react",
                "angular",
                "veu",
                "office",
                "public",
                "private",
                "classified",
                "general",
                "downloads",
                "wordFile",
                "excelFile"
        );

        CheckBoxPage checkBoxPage = new CheckBoxPage(getDriver());
        checkBoxPage.open();
        Assert.assertTrue(checkBoxPage.isPageOpened(), "Page hasn't been opened");

        checkBoxPage.clickExpandButton();
        checkBoxPage.clickCheckBoxHome();
        Assert.assertTrue(checkBoxPage.getResultCheckboxList().containsAll(expectedChechboxList), "There are not select all element");

        checkBoxPage.clickCheckBoxHome();
        Assert.assertFalse(checkBoxPage.getResultCheckboxList().containsAll(expectedChechboxList), "There are select all elements or several");
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testPartsOfCheckbox() {
        List<String> expectedlistDocuments = List.of(
                "documents",
                "workspace",
                "react",
                "angular",
                "veu",
                "office",
                "public",
                "private",
                "classified",
                "general"
        );

        List<String> expectedlistDownloads = List.of(
                "downloads",
                "wordFile",
                "excelFile"
        );

        CheckBoxPage checkBoxPage = new CheckBoxPage(getDriver());
        checkBoxPage.open();
        Assert.assertTrue(checkBoxPage.isPageOpened());

        checkBoxPage.clickExpandButton();
        checkBoxPage.clickCheckBoxHome();

        checkBoxPage.clickCheckboxDocuments();
        Assert.assertFalse(checkBoxPage.getResultCheckboxList().containsAll(expectedlistDocuments), "Result has documents checkboxes");
        Assert.assertTrue(checkBoxPage.isCheckboxHomeHalfCheckExist(), "Home has no half-check");

        checkBoxPage.clickCheckBoxHome();
        checkBoxPage.clickCheckBoxHome();

        checkBoxPage.clickCheckboxDownloads();
        Assert.assertTrue(checkBoxPage.getResultCheckboxList().containsAll(expectedlistDownloads), "Result has no downloads");
        Assert.assertTrue(checkBoxPage.isCheckboxHomeHalfCheckExist(), "Home has no half-check");
    }
}
