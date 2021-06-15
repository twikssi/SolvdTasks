package by.demoqa.api.bookstore;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class DeleteAllBooksByUserIdMethod extends AbstractApiMethodV2 {
    public DeleteAllBooksByUserIdMethod() {
        super();
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}
