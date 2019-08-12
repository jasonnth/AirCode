package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class AcceptTermsOfServiceRequest extends BaseRequestV2<UserResponse> {
    public String getPath() {
        return "users/me";
    }

    public Object getBody() {
        return Strap.make().mo11640kv("tos_accepted", true);
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return UserResponse.class;
    }
}
