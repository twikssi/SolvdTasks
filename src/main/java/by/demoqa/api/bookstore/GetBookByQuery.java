package by.demoqa.api.bookstore;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class GetBookByQuery extends AbstractApiMethodV2 {
    public GetBookByQuery() {
        super();
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
        setResponseTemplate("api/book_store/_get_book_by_query/rsBook.json");
    }
}
