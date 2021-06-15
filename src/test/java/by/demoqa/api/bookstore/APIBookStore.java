package by.demoqa.api.bookstore;

import by.demoqa.beans.Book;
import by.demoqa.beans.UserBookStore;
import by.demoqa.services.BookService;
import by.demoqa.services.RegistrationService;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.List;

public class APIBookStore extends AbstractTest {
    private List<Book> books;
    private UserBookStore user;
    private RegistrationService registrationService;
    private String isbn = "9781593277574";
    private String isbn2 = "9781449337711";
    private String isbn3 = "9781449365035";

    @BeforeClass
    public void setupRegistration() {
        Calendar calendar = Calendar.getInstance();
        user = UserBookStore.builder().
                name("Alexa1201110000000" + calendar.getTime()).
                password("Pa$$word123").
                build();

        registrationService = new RegistrationService(user);
        registrationService.signUp();
        registrationService.signIn();
    }

    @AfterClass
    public void deleteUser() {
        registrationService.deleteUser();
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testGetBooks() {
        int expectedNumbresOfBooks = 8;
        GetAllBooksMethod getAllBooksMethod = new GetAllBooksMethod();
        getAllBooksMethod.expectResponseStatus(HttpResponseStatusType.OK_200);
        Response response = getAllBooksMethod.callAPI();

        books = BookService.mapJSonBookToBeanBook(response.body());
        Assert.assertEquals(books.size(), expectedNumbresOfBooks, "Number of books don't match");
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testGetBookByISBN() {
        String expectISBN = "9781449331818";
        GetBookByQuery getBookByQuery = new GetBookByQuery();
        getBookByQuery.expectResponseStatus(HttpResponseStatusType.OK_200);
        getBookByQuery.addUrlParameter("ISBN", expectISBN);

        getBookByQuery.callAPI();
        getBookByQuery.validateResponseAgainstSchema("api/book_store/_get_book_by_query/rsBook.schema");
    }

    @Test
    @MethodOwner(owner = "Andrew")
    public void testGetBookByISBNNegative() {
        String expectedIncorrectISBN = "dsfsdf234";
        String expectedCode = "1205";
        GetBookByQuery getBookByQuery = new GetBookByQuery();
        getBookByQuery.addUrlParameter("ISBN", expectedIncorrectISBN);
        getBookByQuery.expectResponseStatus(HttpResponseStatusType.BAD_REQUEST_400);

        Response respons = getBookByQuery.callAPI();
        Assert.assertEquals(respons.getBody().jsonPath().get("code"), expectedCode, "Codes don't match");
    }

    @Test(dependsOnMethods = "testGetBooks")
    @MethodOwner(owner = "Andrew")
    public void testPostAddBookToUser() {
        PostAddBookToUser postAddBookToUser = new PostAddBookToUser();

        postAddBookToUser.getProperties().setProperty("id", user.getId());
        postAddBookToUser.request.header("Authorization", "Bearer " + user.getToken());
        postAddBookToUser.expectResponseStatus(HttpResponseStatusType.CREATED_201);

        Response response = postAddBookToUser.callAPI();
        List<Long> listIsbn = response.getBody().jsonPath().getList("books.isbn", Long.class);
        Assert.assertTrue(BookService.contains(books, listIsbn));

        BookService.addBookToUser(isbn2, user);
        BookService.addBookToUser(isbn3, user);

    }

    @Test(dependsOnMethods = "testPostAddBookToUser")
    @MethodOwner(owner = "Andrew")
    public void testDeleteBookByUserIdAndISBN() {
        DeleteBookByUserIdAndISBNMethod deleteApi = new DeleteBookByUserIdAndISBNMethod();
        deleteApi.request.header("Authorization", "Bearer " + user.getToken());
        deleteApi.expectResponseStatus(HttpResponseStatusType.NO_CONTENT_204);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isbn", isbn);
        jsonObject.put("userId", user.getId());
        deleteApi.request.body(jsonObject.toJSONString());

        deleteApi.callAPI();
        List<Book> listBooks = BookService.getAllBooks();
        Assert.assertFalse(BookService.contains(listBooks, isbn), "The book is present");
    }

    @Test(dependsOnMethods = "testDeleteBookByUserIdAndISBN")
    @MethodOwner(owner = "Andrew")
    public void testPutBook() {
        PutBookMethod putBookMethod = new PutBookMethod(isbn2);
        putBookMethod.getProperties().setProperty("userId", user.getId());
        putBookMethod.getProperties().setProperty("isbn", isbn);
        putBookMethod.request.header("Authorization", "Bearer " + user.getToken());
        putBookMethod.expectResponseStatus(HttpResponseStatusType.OK_200);

        Response response = putBookMethod.callAPI();
        List<String> listIsbn = response.getBody().jsonPath().getList("books.isbn");

        Assert.assertFalse(listIsbn.contains(isbn2), "The book is present");
        Assert.assertTrue(listIsbn.contains(isbn), "The book isn't present");
    }

   // doesn't work
    @Test(dependsOnMethods = "testPutBook", enabled = false)
    @MethodOwner(owner = "Andrew")
    public void testDeleteAllBooksByUserId() {
        DeleteAllBooksByUserIdMethod deleteApi = new DeleteAllBooksByUserIdMethod();
        deleteApi.request.header("Authorization", "Bearer " + user.getToken());
        deleteApi.addUrlParameter("userId", user.getId());
        deleteApi.expectResponseStatus(HttpResponseStatusType.NO_CONTENT_204);

        deleteApi.callAPI();
    }
}
