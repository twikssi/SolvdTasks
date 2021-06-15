package by.demoqa.web;

import by.demoqa.web.components.SubmitingForm;
import by.demoqa.web.pages.FormPage;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.util.List;

public class PageFormTests extends AbstractTest {
    private static final Path path = Path.of(R.CONFIG.get("download_path"));

    @Test
    @MethodOwner(owner = "Andrew")
    public void testForm() {
        String expectedName = "Ignatus";
        String expectedLastName = "Pitrushevich";
        String expectedEmail = "ignatusstar@gmail.com";
        String mobilenumber = "1234567890";
        List<String> genderList = List.of(
                "Female", "Male", "Other"
        );
        List<String> hobbieList = List.of(
                "Sports", "Reading", "Music"
        );
        String expectedAddress = "pizza street 45";
        String expectedDateOfBirth = "07 May,1996";
        String expectedSubject = "Maths";
        String expectedState = "NCR";
        String expectedCity = "Delhi";
        String expectedPicture = "8NB_7247.jpg";

        FormPage formPage = new FormPage(getDriver());
        formPage.open();
        Assert.assertTrue(formPage.isPageOpened(), "Form page isn't opened");

        formPage.inputFirstNameField(expectedName);
        formPage.inputLastNameField(expectedLastName);
        formPage.inputEmailField(expectedEmail);
        formPage.inputMobileNumberField(mobilenumber);
        formPage.clickRandomGender().getText();
        formPage.clickRandomHobbie().getText();
        formPage.inputCurrentAddressArea(expectedAddress);
        formPage.uploadPicture(path.toString() + "/" + expectedPicture);
        formPage.inputDateOfBirth(expectedDateOfBirth);
        formPage.inputSubject(expectedSubject);
        formPage.inputState(expectedState);
        formPage.inputCity(expectedCity);
        formPage.clickSubmitButton();

        SubmitingForm submitingForm = formPage.getSubmitingForm();
        Assert.assertTrue(submitingForm.isUIObjectPresent(), "Submiting form isn't opened");
        Assert.assertEquals(submitingForm.getNameAndLastName(), expectedName + " " + expectedLastName, "Name or lastname don't match");
        Assert.assertEquals(submitingForm.getEmail(), expectedEmail, "Email doesn't match");
        Assert.assertTrue(genderList.contains(submitingForm.getGender()), "Gender isn't present");
        Assert.assertTrue(hobbieList.contains(submitingForm.getHobbie()), "Hobbie isn't present");
        Assert.assertEquals(submitingForm.getAddress(), expectedAddress, "Address doesn't match");
        Assert.assertEquals(submitingForm.getPicture(), expectedPicture, "Picture doesn't match");
        Assert.assertEquals(submitingForm.getSubjects(), expectedSubject, "Subjects don't match");
        Assert.assertEquals(submitingForm.getStateAndCity(), expectedState + " " + expectedCity, "City or State don't match");
        Assert.assertEquals(submitingForm.getDateOfBirth(), expectedDateOfBirth, "Data of birth doesn't match");
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testColorFields() {
        String expectedColorEmail = "rgb(40, 167, 69)";
        String expectedColorName = "rgb(220, 53, 69)";
        String expectedColorMobileNumber = "rgb(220, 53, 69)";
        FormPage formPage = new FormPage(getDriver());
        formPage.open();
        Assert.assertTrue(formPage.isPageOpened(), "Form page isn't opened");

        formPage.clickSubmitButton();
        pause(1);
        Assert.assertFalse(formPage.getSubmitingForm().isUIObjectPresent(), "Submiting form isn't present");
        Assert.assertEquals(formPage.getColorFieldEmail(), expectedColorEmail, "Email color doesn't match");
        Assert.assertEquals(formPage.getColorName(), expectedColorName, "Name color doesn't match");
        Assert.assertEquals(formPage.getColorMobileNumberField(), expectedColorMobileNumber, "Name color doesn't match");
    }
}
