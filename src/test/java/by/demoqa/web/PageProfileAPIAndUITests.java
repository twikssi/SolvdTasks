package by.demoqa.web;

import by.demoqa.beans.BookProfile;
import by.demoqa.beans.UserBookStore;
import by.demoqa.services.BookService;
import by.demoqa.services.RegistrationService;
import by.demoqa.web.components.ProfileItem;
import by.demoqa.web.pages.LoginPage;
import by.demoqa.web.pages.ProfilePage;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.List;


public class PageProfileAPIAndUITests extends AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(PageProfileAPIAndUITests.class);
    private UserBookStore user;
    private RegistrationService registrationService;
    private String isbn = "9781593277574";
    private String isbn2 = "9781449337711";
    private String isbn3 = "9781449365035";
    private List<BookProfile> expectedBookProfileList;

    @BeforeClass
    private void setup() {
        Calendar calendar = Calendar.getInstance();
        user = UserBookStore.builder().
                name("SadAlesha" + calendar.getTime()).
                password("Pa$$word123").
                build();

        registrationService = new RegistrationService(user);
        registrationService.signUp();

        expectedBookProfileList = List.of(
                BookProfile.builder().action("").image("").publisher("No Starch Press").author("Nicholas C. Zakas").title("Understanding ECMAScript 6").build(),
                BookProfile.builder().action("").image("").publisher("O'Reilly Media").author("Glenn Block et al.").title("Designing Evolvable Web APIs with ASP.NET").build(),
                BookProfile.builder().action("").image("").publisher("O'Reilly Media").author("Axel Rauschmayer").title("Speaking JavaScript").build()
        );
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        Assert.assertTrue(loginPage.isPageOpened(), "Login page isn't opened");
        loginPage.inputName(user.getName());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickLoginButton();
        Assert.assertFalse(loginPage.isPageOpened(), "Login page isn't opened");

        ProfilePage profilePage = new ProfilePage(getDriver());
        Assert.assertTrue(profilePage.isPageOpened(), "Profile page isn't opened");
        Assert.assertEquals(profilePage.getRegisteredUserName(), user.getName(), "Users don't match");

        user.setToken(getDriver().manage().getCookieNamed("token").getValue());
        log.info("TOKEN: " + user.getToken());
        log.info("USER_ID: " + user.getId());
    }

    @Test(dependsOnMethods = "testLogin")
    @MethodOwner(owner = "Andrew")
    public void testAddBook() {
        ProfilePage profilePage = new ProfilePage(getDriver());
        Assert.assertTrue(profilePage.isPageOpened(), "Profile page isn't opened");
        BookService.addBookToUser(isbn, user);
        BookService.addBookToUser(isbn2, user);
        BookService.addBookToUser(isbn3, user);

        profilePage.refresh();

        List<ProfileItem> profileItem = profilePage.getProfilePageList();
        List<BookProfile> actualBookList = BookService.mapProfileItemListToBookList(profileItem);

        actualBookList.forEach(a -> log.info(a.toString()));
        Assert.assertEquals(actualBookList, expectedBookProfileList, "The books don't match");
    }

    @Test(dependsOnMethods = "testAddBook")
    @MethodOwner(owner = "Andrew")
    public void testLogout() {
        ProfilePage profilePage = new ProfilePage(getDriver());
        Assert.assertTrue(profilePage.isPageOpened(), "Profile page isn't opened");
        profilePage.clickLogouButton();

        Assert.assertFalse(profilePage.isPageOpened(), "Login page isn't opened");
        LoginPage loginPage = new LoginPage(getDriver());
        Assert.assertTrue(loginPage.isPageOpened(), "Login page isn't opened");
        Assert.assertTrue(loginPage.getLoginInBookStoreH5().isElementPresent(), "Text 'Login in Book Store' isn't present");
    }


}
