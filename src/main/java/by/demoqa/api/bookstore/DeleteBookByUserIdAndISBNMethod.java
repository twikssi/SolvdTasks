package by.demoqa.api.bookstore;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class DeleteBookByUserIdAndISBNMethod extends AbstractApiMethodV2 {
    public DeleteBookByUserIdAndISBNMethod() {
        super();
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}
