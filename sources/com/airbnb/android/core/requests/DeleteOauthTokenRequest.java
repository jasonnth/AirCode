package com.airbnb.android.core.requests;

import android.text.TextUtils;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

public class DeleteOauthTokenRequest extends BaseRequestV2<Object> {
    AirbnbAccountManager accountManager;
    private final String oauthToken = this.accountManager.getAccessToken();

    public DeleteOauthTokenRequest() {
        ((CoreGraph) CoreApplication.instance().component()).inject(this);
        if (TextUtils.isEmpty(this.oauthToken)) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("you created a " + getClass().getSimpleName() + " when no oauth token currently exists"));
        }
    }

    public String getPath() {
        return "oauth2/authorizations/me";
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public Map<String, String> getHeaders() {
        return Collections.singletonMap(ApiRequestHeadersInterceptor.HEADER_OAUTH, this.oauthToken);
    }

    public Type successResponseType() {
        return Object.class;
    }
}
