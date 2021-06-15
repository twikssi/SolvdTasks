package by.demoqa.services;

import by.demoqa.api.account.DeleteUserMethod;
import by.demoqa.api.account.PostUserMethod;
import by.demoqa.api.account.PostUserTokenMethod;
import by.demoqa.beans.UserBookStore;
import by.demoqa.util.CalendarUtil;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import io.restassured.response.Response;
import lombok.Data;
import org.testng.Assert;

@Data
public class RegistrationService {
    private UserBookStore userBookStore;

    public RegistrationService(UserBookStore userBookStore) {
        this.userBookStore = userBookStore;
    }

    public void signUp() {
        PostUserMethod postUserMethod = new PostUserMethod();
        postUserMethod.getProperties().setProperty("name", userBookStore.getName());
        postUserMethod.getProperties().setProperty("user_password", userBookStore.getPassword());
        postUserMethod.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        Response response = postUserMethod.callAPI();
        userBookStore.setId(response.getBody().jsonPath().get("userID"));
        String name = response.getBody().jsonPath().get("username");
        Assert.assertEquals(name, userBookStore.getName(), "Names don't match");
    }

    public void signIn(){
        PostUserTokenMethod postUserTokenMethod = new PostUserTokenMethod();
        postUserTokenMethod.getProperties().setProperty("name", userBookStore.getName());
        postUserTokenMethod.getProperties().setProperty("user_password", userBookStore.getPassword());
        Response response = postUserTokenMethod.callAPI();
        userBookStore.setToken(response.getBody().jsonPath().get("token"));
        String status = response.getBody().jsonPath().get("status");
        String result = response.getBody().jsonPath().get("result");
        Assert.assertEquals(status, "Success", "Statuses don't match");
        Assert.assertEquals(result, "User authorized successfully.", "Results don't match");
    }

    public void deleteUser(){
        DeleteUserMethod deleteUserMethod = new DeleteUserMethod(userBookStore.getId());
        deleteUserMethod.request.header("Authorization", "Bearer " + userBookStore.getToken());
        deleteUserMethod.expectResponseStatus(HttpResponseStatusType.NO_CONTENT_204);
        deleteUserMethod.callAPI();
    }

}
