package com.airbnb.android.core.requests;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.FormUrlRequest;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.responses.AuthorizeServiceResponse;
import com.airbnb.android.utils.Strap;
import com.braintreepayments.api.models.PayPalRequest;
import java.lang.reflect.Type;
import java.util.Map;

public class AuthorizeServiceRequest extends FormUrlRequest<AuthorizeServiceResponse> {
    private static final String FACEBOOK_ASSERTION = "https://graph.facebook.com/me";
    private static final String WEIBO_OAUTH_ASSERTION = "https://api.weibo.com/oauth2/authorize";
    AirbnbAccountManager accountManager;
    AirbnbApi airbnbApi;
    private final Strap params;

    public static AuthorizeServiceRequest forWeibo(Context context, boolean isLogin, String accessCode) {
        return new AuthorizeServiceRequest(context, getWeiboParams(accessCode), isLogin);
    }

    public static AuthorizeServiceRequest forFacebook(Context context, String accessToken) {
        return new AuthorizeServiceRequest(context, getFacebookParams(accessToken), false);
    }

    private AuthorizeServiceRequest(Context context, Strap strap, boolean isLogin) {
        ((CoreGraph) CoreApplication.instance(context).component()).inject(this);
        setNullOauthToken(isLogin);
        this.params = strap;
    }

    private void setNullOauthToken(boolean isLogin) {
        if (isLogin) {
            this.accountManager.setAccessToken(null);
        }
    }

    public String getPath() {
        return PayPalRequest.INTENT_AUTHORIZE;
    }

    public QueryStrap getFields() {
        return QueryStrap.make().mix((Map<String, String>) this.params);
    }

    public String getBody() {
        return null;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public AirResponse<AuthorizeServiceResponse> transformResponse(AirResponse<AuthorizeServiceResponse> response) {
        AuthorizeServiceResponse data = (AuthorizeServiceResponse) response.body();
        if (data.user != null) {
            this.accountManager.setCurrentUser(data.user);
            this.accountManager.storeCurrentUser();
        }
        if (!TextUtils.isEmpty(data.mAccessToken)) {
            this.accountManager.setAccessToken(data.mAccessToken);
            this.airbnbApi.enablePushNotifications();
        }
        return response;
    }

    private static Strap getFacebookParams(String facebookToken) {
        return Strap.make().mo11639kv("assertion", facebookToken).mo11639kv("assertion_type", FACEBOOK_ASSERTION);
    }

    private static Strap getWeiboParams(String weiboCode) {
        return Strap.make().mo11639kv("code", weiboCode).mo11639kv("assertion_type", WEIBO_OAUTH_ASSERTION).mo11639kv("assertion", RegistrationAnalytics.WEIBO);
    }

    public Type successResponseType() {
        return AuthorizeServiceResponse.class;
    }
}
