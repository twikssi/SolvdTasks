package by.demoqa.web;

import by.demoqa.web.pages.ButtonsPage;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PageButtonsTests extends AbstractTest {

    @Test()
    @MethodOwner(owner = "Andrew")
    public void testPageButtons() {
        String expectedTextDoubleClick = "You have done a double click";
        String expectedTextRightClick = "You have done a right click";
        String expectedTextClick = "You have done a dynamic click";

        ButtonsPage buttonsPage = new ButtonsPage(getDriver());
        buttonsPage.open();
        Assert.assertTrue(buttonsPage.isPageOpened(), "ButtonPage is not open");

        buttonsPage.clickDoubleClickButton();

        String actualTextDoubleClick = buttonsPage.getTextDoubleClickButton();
        Assert.assertEquals(actualTextDoubleClick, expectedTextDoubleClick);
        Assert.assertFalse(buttonsPage.isElementResultTextClickButtonPresent());
        Assert.assertFalse(buttonsPage.isElementResultTextRightClickButtonPresent());

        buttonsPage.clickRightClickButton();
        String actualTextRightClick = buttonsPage.getTextRightClickButton();
        Assert.assertEquals(actualTextRightClick, expectedTextRightClick);
        Assert.assertFalse(buttonsPage.isElementResultTextClickButtonPresent());
        Assert.assertTrue(buttonsPage.isElementResultTextDoubleClickButtonPresent());

        buttonsPage.clickButton();
        String actualTextClick = buttonsPage.getTextClickButton();
        Assert.assertEquals(actualTextClick, expectedTextClick);
        Assert.assertTrue(buttonsPage.isElementResultTextRightClickButtonPresent());
        Assert.assertTrue(buttonsPage.isElementResultTextDoubleClickButtonPresent());
    }
}
