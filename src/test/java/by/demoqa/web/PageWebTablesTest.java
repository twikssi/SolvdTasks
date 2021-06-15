package by.demoqa.web;

import by.demoqa.beans.User;
import by.demoqa.services.UserService;
import by.demoqa.web.components.RegistrationForm;
import by.demoqa.web.components.UserItem;
import by.demoqa.web.pages.WebTables;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class PageWebTablesTest extends AbstractTest {
    private WebTables webTables;
    private User user;
    private User updatedUser;

    @BeforeClass
    public void openMainPage() {
        webTables = new WebTables(getDriver());
        webTables.open();

        user = User.builder().
                name("Tolia").
                lastName("Hori").
                age(24).
                email("tolyastar@gmail.com").
                salary(300).
                department("product").
                build();

        updatedUser = User.builder().
                name("Gala").
                lastName("Galala").
                age(46).
                email("galacandy@gmail.com").
                salary(400).
                department("cleaner").
                build();
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testAddUser() {
        Assert.assertTrue(webTables.isPageOpened(), "Web tables page isn't opened");
        RegistrationForm reg = webTables.getRegistationForm();
        webTables.clickRegistationForm();
        Assert.assertTrue(reg.isUIObjectPresent(), "Registration window is closed");
        reg.fillRegistrationForm(user);
        Assert.assertFalse(reg.isUIObjectPresent(), "Registration window is opened");
    }

    @Test(dependsOnMethods = "testAddUser")
    @MethodOwner(owner = "Andrew")
    public void testCheckUserAdded() {
        Assert.assertTrue(webTables.isPageOpened(), "Web tables page isn't opened");

        List<UserItem> usersItems = webTables.getUsers();
        List<User> users = UserService.mapUserItemListToUserList(usersItems);
        Assert.assertTrue(UserService.isUserPresentInList(users, user), "User isn't existed");
    }

    @Test()
    @MethodOwner(owner = "Andrew")
    public void testCheckUpdateUser() {
        Assert.assertTrue(webTables.isPageOpened(), "Web tables page isn't opened");

        List<UserItem> userItems = webTables.getUsers();
        List<User> users = UserService.mapUserItemListToUserList(userItems);
        UserItem oldUserItem = userItems.get(0);
        oldUserItem.clickEditUser();
        User oldUser = UserService.mapUser(oldUserItem);

        RegistrationForm reg = webTables.getRegistationForm();
        reg.fillRegistrationForm(updatedUser);
        List<User> updatedUsers = UserService.mapUserItemListToUserList(webTables.getUsers());
        Assert.assertTrue(UserService.isUserPresentInList(updatedUsers, updatedUser), "Updated user is existed");
        Assert.assertFalse(UserService.isUserPresentInList(updatedUsers, oldUser), "Old user isn't existed");
    }

    @Test(dependsOnMethods = "testCheckUpdateUser")
    @MethodOwner(owner = "Andrew")
    public void testCheckDelete() {
        Assert.assertTrue(webTables.isPageOpened(), "Web tables page isn't opened");

        List<UserItem> userItems = webTables.getUsers();
        List<User> users = UserService.mapUserItemListToUserList(userItems);

        int index = UserService.findIndexUser(users, updatedUser);
        UserItem oldUserItems = userItems.get(index);
        oldUserItems.clickButtonDeleteUser();

        List<User> usersBeforeDeleted = UserService.mapUserItemListToUserList(webTables.getUsers());
        Assert.assertFalse(UserService.isUserPresentInList(usersBeforeDeleted, updatedUser));
    }
}
