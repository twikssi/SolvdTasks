package by.demoqa.api.bookstore;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PostAddBookToUser extends AbstractApiMethodV2 {
    public PostAddBookToUser() {
        super();
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
        setRequestTemplate("api/book_store/_post_add_book_to_user/rqISBN.json");
        setProperties("api/book_store/_post_add_book_to_user/isbn.properties");
    }
}
