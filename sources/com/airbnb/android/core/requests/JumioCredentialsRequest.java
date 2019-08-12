package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.JumioCredentialsResponse;
import java.lang.reflect.Type;

public class JumioCredentialsRequest extends BaseRequestV2<JumioCredentialsResponse> {
    public String getPath() {
        return "jumio_credentials";
    }

    public Type successResponseType() {
        return JumioCredentialsResponse.class;
    }
}
