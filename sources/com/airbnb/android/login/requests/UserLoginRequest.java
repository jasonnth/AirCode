package com.airbnb.android.login.requests;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.ButtonPartnership;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.authentication.AuthorizedAccountHelper;
import com.airbnb.android.core.models.Account;
import com.airbnb.android.core.models.Login;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.login.LoginGraph;
import com.airbnb.android.login.responses.UserLoginResponse;
import com.airbnb.android.registration.models.AccountLoginData;
import com.airbnb.android.utils.Strap;
import com.squareup.otto.Bus;
import java.lang.reflect.Type;

public class UserLoginRequest extends BaseRequestV2<UserLoginResponse> {
    private static final String ACCESS_TOKEN = "access_token";
    private static final String CODE = "code";
    private static final String EMAIL = "email";

    /* renamed from: ID */
    private static final String f10051ID = "id";
    private static final String MOBILE_WEB_TOKEN = "mobile_web_token";
    private static final String PASSWORD = "password";
    private static final String PHONE = "phone";
    private static final String TYPE = "type";
    AirbnbAccountManager accountManager;
    AirbnbApi airbnbApi;
    private final AuthorizedAccountHelper authorizedAccountHelper;
    Bus bus;
    CurrencyFormatter currencyFormatter;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Strap params;

    public static UserLoginRequest newRequest(Context context, AccountLoginData data) {
        data.throwIfInvalidForLogin();
        Strap sharedParams = Strap.make().mo11639kv("type", data.accountSource().getName());
        switch (data.accountSource()) {
            case Email:
                return new UserLoginRequest(context, sharedParams.mo11639kv("email", data.email()).mo11639kv(PASSWORD, data.password()));
            case Phone:
                return new UserLoginRequest(context, sharedParams.mo11639kv("phone", TextUtils.isEmpty(data.phone()) ? data.airPhone().formattedPhone() : data.phone()).mo11639kv(PASSWORD, data.password()));
            case Facebook:
            case Google:
                return new UserLoginRequest(context, sharedParams.mo11639kv("access_token", data.authToken()));
            case Weibo:
            case WeChat:
                return new UserLoginRequest(context, sharedParams.mo11639kv("code", data.authToken()));
            case Alipay:
                return new UserLoginRequest(context, sharedParams.mo11639kv("code", data.authToken()));
            case MoWeb:
                return new UserLoginRequest(context, sharedParams.mo11639kv("access_token", data.mowebAccessToken()).mo11639kv("type", MOBILE_WEB_TOKEN).mo11639kv("id", data.mowebAuthId()));
            default:
                throw new IllegalArgumentException(String.format("AccountSource %s not yet supported!", new Object[]{data.accountSource().name()}));
        }
    }

    private UserLoginRequest(Context context, Strap params2) {
        ((LoginGraph) CoreApplication.instance(context).component()).inject(this);
        this.authorizedAccountHelper = AuthorizedAccountHelper.get(context);
        this.params = params2;
    }

    public String getPath() {
        return "logins";
    }

    public Strap getBody() {
        return this.params;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public AirResponse<UserLoginResponse> transformResponse(AirResponse<UserLoginResponse> response) {
        Login login = ((UserLoginResponse) response.body()).login;
        Account account = login.getAccount();
        this.accountManager.setCurrentUser(account.getUser());
        this.accountManager.storeCurrentUser();
        this.accountManager.setIsVRPlatformPoweredHost(account.isVrPlatformPoweredHost());
        if (!TextUtils.isEmpty(account.getCurrency()) && !this.currencyFormatter.getLocalCurrencyString().equalsIgnoreCase(account.getCurrency())) {
            this.currencyFormatter.setCurrency(account.getCurrency(), false, false);
        }
        this.authorizedAccountHelper.addOrUpdateCurrentUser();
        ButtonPartnership.get().onLogin(account.getUser());
        String accessToken = login.getId();
        if (!TextUtils.isEmpty(accessToken)) {
            this.accountManager.setAccessToken(accessToken);
            this.airbnbApi.enablePushNotifications();
        }
        this.handler.post(UserLoginRequest$$Lambda$1.lambdaFactory$(this));
        return response;
    }

    public Strap getHeaders() {
        Strap headers = Strap.make().mix(super.getHeaders());
        headers.remove(ApiRequestHeadersInterceptor.HEADER_OAUTH);
        return headers;
    }

    public Type successResponseType() {
        return UserLoginResponse.class;
    }
}
