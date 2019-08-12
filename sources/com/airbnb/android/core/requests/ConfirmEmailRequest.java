package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.FormUrlRequest;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.HashMap;

public class ConfirmEmailRequest extends FormUrlRequest<BaseResponse> {
    private static final String COHOST_SOURCE_KEY = "cohosting_accept_invite";
    private static final String QUERY_EMAIL = "user[email]";
    private ConfirmEmailBody body;
    private final String email;

    private static final class ConfirmEmailBody {
        @JsonProperty("flow")
        final HashMap<String, Object> flow = new HashMap<>();
        @JsonProperty("user")
        final HashMap<String, Object> user;

        ConfirmEmailBody(String email, String source, HashMap<String, Object> payload) {
            this.flow.put("source", source);
            this.flow.put("payload", payload);
            this.user = new HashMap<>();
            this.user.put("email", email);
        }
    }

    public static ConfirmEmailRequest newInstance(String email2) {
        return new ConfirmEmailRequest(email2);
    }

    public static ConfirmEmailRequest newInstanceForCohosting(String email2, String firstName) {
        return new ConfirmEmailRequest(email2, firstName);
    }

    private ConfirmEmailRequest(String email2) {
        this.email = email2;
    }

    private ConfirmEmailRequest(String email2, String firstName) {
        this.email = email2;
        this.body = new ConfirmEmailBody(email2, COHOST_SOURCE_KEY, getPayloadForCohosting(firstName));
    }

    @Deprecated
    public ConfirmEmailRequest(String email2, BaseRequestListener<BaseResponse> listener) {
        withListener(listener);
        this.email = email2;
    }

    private HashMap<String, Object> getPayloadForCohosting(String firstName) {
        HashMap payload = new HashMap();
        payload.put("owner_first_name", firstName);
        return payload;
    }

    public String getPath() {
        return "account/update";
    }

    public QueryStrap getFields() {
        return QueryStrap.make().mo7895kv(QUERY_EMAIL, this.email);
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public Object getBody() {
        return this.body;
    }
}
