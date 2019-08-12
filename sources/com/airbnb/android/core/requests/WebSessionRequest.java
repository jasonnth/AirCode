package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.WebSessionResponse;
import java.lang.reflect.Type;
import p032rx.Observer;

public class WebSessionRequest extends BaseRequestV2<WebSessionResponse> {
    public WebSessionRequest(BaseRequestListener<WebSessionResponse> listener) {
        withListener((Observer) listener);
    }

    public String getPath() {
        return "user_sessions";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return WebSessionResponse.class;
    }
}
