package by.demoqa.api.bookstore;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PutBookMethod extends AbstractApiMethodV2 {
    public PutBookMethod(String id) {
        super();
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
        replaceUrlPlaceholder("ISBN", id);
        setRequestTemplate("api/book_store/_put_book/rqBook.json");
    }
}
