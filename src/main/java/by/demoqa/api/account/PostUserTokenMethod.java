package by.demoqa.api.account;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PostUserTokenMethod extends AbstractApiMethodV2 {
    public PostUserTokenMethod() {
        super();
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
        setProperties("api/account/_post_user_token/user.properties");
        setRequestTemplate("api/account/_post_user_token/rq.json");
    }
}
