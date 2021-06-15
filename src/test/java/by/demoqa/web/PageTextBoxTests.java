package by.demoqa.web;

import by.demoqa.web.pages.TextBoxPage;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PageTextBoxTests extends AbstractTest {

    @DataProvider(name = "dataForSignIn")
    public static Object[][] getDataForSignIn() {
        return new Object[][]{
                {"My name", "email@gmail.com", "My current adress", "My permanent adress"}
        };
    }

    @DataProvider(name = "dataForWrongEmail")
    public static Object[][] getDataForWrongEmail() {
        return new Object[][]{
                {"My wrong email"}
        };
    }

    @Test(dataProvider = "dataForSignIn")
    @MethodOwner(owner = "Andrew")
    public void testSignInInformations(String name, String email, String currentAdress, String permanentAdress) {
        TextBoxPage textBoxPage = new TextBoxPage(getDriver());
        textBoxPage.open();
        Assert.assertTrue(textBoxPage.isPageOpened());

        textBoxPage.inputUserName(name);
        textBoxPage.inputCurrentAdress(currentAdress);
        textBoxPage.inputPermanentAdress(permanentAdress);
        textBoxPage.inputUserEmail(email);

        textBoxPage.clickSentInformatonButton();
        Assert.assertFalse(textBoxPage.isWrongEmailPresent());
        Assert.assertEquals(textBoxPage.getResultName(), name, "Name is not match");
        Assert.assertEquals(textBoxPage.getResultEmail(), email, "Email is not match");
        Assert.assertEquals(textBoxPage.getResultCurrentAddress(), currentAdress, "Current adress is not match");
        Assert.assertEquals(textBoxPage.getResultPermanentAddress(), permanentAdress, "Permanent adress is not match");
    }

    @Test(dataProvider = "dataForWrongEmail")
    @MethodOwner(owner = "Andrew")
    public void testWrongEmail(String wrongEmail) {
        TextBoxPage textBoxPage = new TextBoxPage(getDriver());
        textBoxPage.open();
        Assert.assertTrue(textBoxPage.isPageOpened());

        textBoxPage.inputUserEmail(wrongEmail);
        textBoxPage.clickSentInformatonButton();

        Assert.assertTrue(textBoxPage.isWrongEmailPresent(), "Email is not match");
        Assert.assertTrue(textBoxPage.islistSignInResultEmpty(), "Email has been come up");
    }
}
