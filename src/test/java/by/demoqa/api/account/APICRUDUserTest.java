package by.demoqa.api.account;

import by.demoqa.util.CalendarUtil;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;

public class APICRUDUserTest extends AbstractTest {
    private static Logger log = LoggerFactory.getLogger(APICRUDUserTest.class);
    private String id;
    private String name;
    private String password;
    private String token;

    @Test()
    @MethodOwner(owner = "Andrew")
    public void testCreateWrongUser() {
        PostUserMethod postUserMethod = new PostUserMethod();
        String incorrectPassword = "fsffs";
        postUserMethod.getProperties().setProperty("user_password", incorrectPassword);
        postUserMethod.callAPI();
        postUserMethod.expectResponseStatus(HttpResponseStatusType.BAD_REQUEST_400);
        postUserMethod.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "Andrew")
    public void testCreateUser() {
        PostUserMethod postUserMethod = new PostUserMethod();
        String expectedName = postUserMethod.getProperties().getProperty("name");
        password = postUserMethod.getProperties().getProperty("user_password");

        postUserMethod.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        Response response = postUserMethod.callAPI();
        name = response.getBody().jsonPath().get("username");
        id = response.getBody().jsonPath().get("userID");
        log.info("User's id is " + id);
        Assert.assertEquals(name, expectedName, "Names don't match");
    }

    @Test(dependsOnMethods = "testCreateUser")
    @MethodOwner(owner = "Andrew")
    public void testCreateUserToken() throws ParseException {
        long expectedDaysTokenIsAvailable = 6;
        PostUserTokenMethod postUserTokenMethod = new PostUserTokenMethod();
        postUserTokenMethod.getProperties().setProperty("user_password", password);
        postUserTokenMethod.getProperties().setProperty("name", name);
        postUserTokenMethod.expectResponseStatus(HttpResponseStatusType.OK_200);

        Response respons = postUserTokenMethod.callAPI();
        String actualStatus = respons.getBody().jsonPath().get("status");
        String actualResult = respons.getBody().jsonPath().get("result");
        token = respons.getBody().jsonPath().get("token");
        Assert.assertEquals(actualStatus, postUserTokenMethod.getProperties().getProperty("status"), "Statuses don't match");
        Assert.assertEquals(actualResult, postUserTokenMethod.getProperties().getProperty("result"), "Results don't match");

        String data = respons.getBody().jsonPath().get("expires");
        long actualDaysTokenIsAvailable = CalendarUtil.getNumberOfDaysTokenIsAvailable(data);
        Assert.assertEquals(actualDaysTokenIsAvailable, expectedDaysTokenIsAvailable, "Days don't match");
    }

    @Test(dependsOnMethods = "testCreateUserToken")
    @MethodOwner(owner = "Andrew")
    public void testGetUser() {
        GetUserMethod getUserMethod = new GetUserMethod(id);
        getUserMethod.setHeaders("Authorization= Bearer " + token);
        getUserMethod.expectResponseStatus(HttpResponseStatusType.OK_200);

        Response response = getUserMethod.callAPI();
        String actualName = response.jsonPath().get("username");
        Assert.assertEquals(actualName, name, "Names don't match");
    }

    @Test(dependsOnMethods = "testGetUser")
    @MethodOwner(owner = "Andrew")
    public void testDeleteUser() {
        DeleteUserMethod deleteUserMethod = new DeleteUserMethod(id);
        deleteUserMethod.request.header("Authorization", "Bearer " + token);
        deleteUserMethod.expectResponseStatus(HttpResponseStatusType.NO_CONTENT_204);
        deleteUserMethod.callAPI();
    }
}
