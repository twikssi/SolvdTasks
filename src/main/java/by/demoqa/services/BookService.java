package by.demoqa.services;

import by.demoqa.api.bookstore.GetAllBooksMethod;
import by.demoqa.api.bookstore.PostAddBookToUser;
import by.demoqa.beans.Book;
import by.demoqa.beans.BookProfile;
import by.demoqa.beans.UserBookStore;
import by.demoqa.util.CalendarUtil;
import by.demoqa.web.components.ProfileItem;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    public static List<BookProfile> mapProfileItemListToBookList(List<ProfileItem> profileItemList) {
        List<BookProfile> bookProfileList = new ArrayList<>();
        for (int i = 0; i < profileItemList.size(); i++) {
            if (!profileItemList.get(i).getTitleCells().equals(" ")) {
                bookProfileList.add(BookProfile.builder().
                        image(profileItemList.get(i).getImageCells()).
                        title(profileItemList.get(i).getTitleCells()).
                        author(profileItemList.get(i).getAuthorCells()).
                        publisher(profileItemList.get(i).getPublisherCells()).
                        action(profileItemList.get(i).getActionCells()).
                        build());
            }
        }
        return bookProfileList;
    }

    public static boolean contains(List<Book> bookList, List<Long> isbnList) {
        List<Long> allIsbnList = bookList.stream().map(Book::getIsbn).collect(Collectors.toList());
        return allIsbnList.containsAll(isbnList);
    }

    public static boolean contains(List<Book> bookList, String isbnList) {
        List<Long> allIsbnList = bookList.stream().map(Book::getIsbn).collect(Collectors.toList());
        return allIsbnList.contains(isbnList);
    }

    public static List<Book> mapJSonBookToBeanBook(ResponseBody body) {
        List<Book> books = new ArrayList<>();
        List<String> listISBNes = body.jsonPath().getList("books.isbn");
        List<String> listTitles = body.jsonPath().getList("books.title");
        List<String> listSubTitles = body.jsonPath().getList("books.subTitle");
        List<String> listAuthores = body.jsonPath().getList("books.author");
        List<String> listPublishDates = body.jsonPath().getList("books.publish_date");
        List<String> listPublisheres = body.jsonPath().getList("books.publisher");
        List<Integer> listPages = body.jsonPath().getList("books.pages");
        List<String> listDescriptiones = body.jsonPath().getList("books.description");
        List<String> listWebsites = body.jsonPath().getList("books.website");

        for (int i = 0; i < listISBNes.size(); i++) {
            books.add(Book.builder().
                    isbn(Long.parseLong(listISBNes.get(i))).
                    title(listTitles.get(i)).
                    subTitle(listSubTitles.get(i)).
                    author(listAuthores.get(i)).
                    publishDate(CalendarUtil.parseStringToDate(listPublishDates.get(i), CalendarUtil.dateFormatFullWithMill)).
                    publisher(listPublisheres.get(i)).
                    pages(listPages.get(i)).
                    description(listDescriptiones.get(i)).
                    website(listWebsites.get(i)).
                    build());
        }
        return books;
    }

    public static void addBookToUser(String ISBN, UserBookStore user) {
        PostAddBookToUser postAddBookToUser = new PostAddBookToUser();

        postAddBookToUser.getProperties().setProperty("id", user.getId());
        postAddBookToUser.getProperties().setProperty("isbn", ISBN);
        postAddBookToUser.request.header("Authorization", "Bearer " + user.getToken());
        postAddBookToUser.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        postAddBookToUser.callAPI();
    }

    public static List<Book> getAllBooks() {
        GetAllBooksMethod getAllBooksMethod = new GetAllBooksMethod();
        getAllBooksMethod.expectResponseStatus(HttpResponseStatusType.OK_200);

        Response response = getAllBooksMethod.callAPI();
        List<Book> books = BookService.mapJSonBookToBeanBook(response.body());
        Assert.assertTrue(books.size() != 0, "BookList is empty");
        return books;
    }

}
