package by.demoqa.web;

import by.demoqa.web.pages.AlertsPage;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PageAlertTests extends AbstractTest {

    @Test
    @MethodOwner(owner = "Andrew")
    public void testAlertButton() {
        String expectedAlertButton = "You clicked a button";
        AlertsPage alertsPage = new AlertsPage(getDriver());
        alertsPage.open();

        Assert.assertTrue(alertsPage.isPageOpened(), "Alert page isn't present");
        alertsPage.clickAlertButton();
        Assert.assertTrue(alertsPage.isAlertPresent(), "Alert isn't present");
        String actualAlertButton = getDriver().switchTo().alert().getText();
        Assert.assertEquals(actualAlertButton, expectedAlertButton, "Alerts don't match");
        alertsPage.acceptAlert();
        Assert.assertFalse(alertsPage.isAlertPresent(), "Alert is present");
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testAlertTimerAlertButton() {
        String expectedClickTimerAlertButton = "This alert appeared after 5 seconds";
        AlertsPage alertsPage = new AlertsPage(getDriver());
        alertsPage.open();

        alertsPage.clickTimerAlertButton();
        pause(5);
        Assert.assertTrue(alertsPage.isAlertPresent(), "Alert isn't present");
        String actualClickTimerAlertButton = getDriver().switchTo().alert().getText();
        Assert.assertEquals(actualClickTimerAlertButton, expectedClickTimerAlertButton, "Alerts don't match");
        alertsPage.acceptAlert();
        Assert.assertFalse(alertsPage.isAlertPresent(), "Alert is present");
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testAlertConfirmButton() {
        String expectedClickConfirmButtonOK = "You selected Ok";
        String expectedClickConfirmButtonCancel = "You selected Cancel";
        AlertsPage alertsPage = new AlertsPage(getDriver());
        alertsPage.open();

        alertsPage.clickConfirmButton();
        Assert.assertTrue(alertsPage.isAlertPresent(), "Alert isn't present");
        alertsPage.acceptAlert();
        Assert.assertEquals(expectedClickConfirmButtonOK, alertsPage.getConfirmResult(), "Result confirm alert button doesn't match");
        alertsPage.clickConfirmButton();
        alertsPage.cancelAlert();
        Assert.assertEquals(expectedClickConfirmButtonCancel, alertsPage.getConfirmResult(), "Result confirm alert button doesn't match");
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testAlertPromtButton() {
        String name = "Alesha";
        String expectedClickPromtButtonOK = "You entered " + name;
        AlertsPage alertsPage = new AlertsPage(getDriver());
        alertsPage.open();

        alertsPage.clickPromtButton();
        Assert.assertTrue(alertsPage.isAlertPresent(), "Alert isn't present");
        getDriver().switchTo().alert().sendKeys(name);
        alertsPage.acceptAlert();
        Assert.assertTrue(alertsPage.getPromtResult().isElementPresent(), "Promt result isn't present");
        Assert.assertEquals(alertsPage.getPromtResult().getText(), expectedClickPromtButtonOK, "Promt result doesn't match");
        alertsPage.clickPromtButton();
        alertsPage.cancelAlert();
        Assert.assertFalse(alertsPage.getPromtResult().isElementPresent(), "Promt result is present");
    }
}
