package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.responses.GetSecurityCheckResponse;
import java.lang.reflect.Type;
import p032rx.Observer;

public class GetSecurityCheckRequest extends BaseRequestV2<GetSecurityCheckResponse> {
    public GetSecurityCheckRequest(BaseRequestListener<GetSecurityCheckResponse> listener) {
        withListener((Observer) listener);
    }

    public String getPath() {
        return "security_checks/" + AirbnbAccountManager.currentUserId();
    }

    public Type successResponseType() {
        return GetSecurityCheckResponse.class;
    }
}
