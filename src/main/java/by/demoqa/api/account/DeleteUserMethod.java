package by.demoqa.api.account;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class DeleteUserMethod extends AbstractApiMethodV2 {
    public DeleteUserMethod(String id) {
        super();
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
        replaceUrlPlaceholder("UUID", id);
    }
}
