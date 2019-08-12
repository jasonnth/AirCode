package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.BraintreeClientTokenResponse;
import java.lang.reflect.Type;

public class CreateBraintreeClientTokenRequest extends BaseRequestV2<BraintreeClientTokenResponse> {
    public String getPath() {
        return "braintree_client_tokens";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return BraintreeClientTokenResponse.class;
    }
}
