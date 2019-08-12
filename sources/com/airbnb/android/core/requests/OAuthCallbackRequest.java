package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.FormUrlRequest;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import java.lang.reflect.Type;

public class OAuthCallbackRequest extends FormUrlRequest<Object> {
    public static final String SERVICE_GOOGLE = "google";
    public static final String SERVICE_LINKEDIN = "linked_in_v2";
    private final String authorizationCode;
    private final String service;

    public static OAuthCallbackRequest oauthCallbackServerRequest(String authorizationCode2, String service2, BaseRequestListener<Object> listener) {
        return new OAuthCallbackRequest(authorizationCode2, service2, listener);
    }

    private OAuthCallbackRequest(String authorizationCode2, String service2, BaseRequestListener<Object> listener) {
        withListener(listener);
        this.authorizationCode = authorizationCode2;
        this.service = service2;
    }

    public String getPath() {
        return "mobile_oauth_callback";
    }

    public QueryStrap getFields() {
        return QueryStrap.make().mo7895kv("service", this.service).mo7895kv("authorization_code", this.authorizationCode);
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return Object.class;
    }
}
