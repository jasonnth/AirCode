package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.SendEmailConfirmationCodeResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class SendEmailConfirmationCodeRequest extends BaseRequest<SendEmailConfirmationCodeResponse> {
    private final String emailConfirmationCode;
    private final String userId;

    public SendEmailConfirmationCodeRequest(String userId2, String emailConfirmationCode2, BaseRequestListener<SendEmailConfirmationCodeResponse> listener) {
        withListener(listener);
        this.userId = userId2;
        this.emailConfirmationCode = emailConfirmationCode2;
    }

    public String getPath() {
        return "account/confirm_email";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("user_id", this.userId).mo7895kv("code", this.emailConfirmationCode);
    }

    public Type successResponseType() {
        return SendEmailConfirmationCodeResponse.class;
    }
}
