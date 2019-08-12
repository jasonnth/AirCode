package com.airbnb.android.core.requests.base;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.airbnb.airrequest.DefaultErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.ButtonPartnership;
import com.airbnb.android.core.airlock.models.Airlock;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.authentication.AuthorizedAccountHelper;
import com.airbnb.android.core.intents.AirlockActivityIntents;
import com.airbnb.android.core.models.Account;
import com.airbnb.android.core.models.Login;
import com.airbnb.android.core.responses.AirlockErrorResponse;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.fasterxml.jackson.databind.ObjectMapper;
import p032rx.functions.Action1;

public class AirlockErrorHandler implements Action1<Throwable> {
    private static final String TAG = AirlockErrorHandler.class.getSimpleName();
    private final AirbnbAccountManager accountManager;
    private final Context context;
    private final ObjectMapper objectMapper;

    public AirlockErrorHandler(Context context2, AirbnbAccountManager accountManager2, ObjectMapper objectMapper2) {
        this.context = context2;
        this.accountManager = accountManager2;
        this.objectMapper = objectMapper2;
    }

    public void call(Throwable throwable) {
        if (throwable instanceof NetworkException) {
            NetworkException exception = (NetworkException) throwable;
            if (new DefaultErrorResponse(exception).isUserAirlocked() && Trebuchet.launch(TrebuchetKeys.AIRLOCK, false)) {
                parseAndLaunchAirlock(exception);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void parseAndLaunchAirlock(NetworkException exception) {
        AirlockErrorResponse response = airlockErrorResponseFromErrorResponse(exception.bodyString());
        if (hasAirlockObject(response)) {
            loginUserIfNecessary(this.context, response.login);
            launchAirlockActivity(this.context, response.clientErrorInfo.airlock, response.login != null);
        }
    }

    public boolean isAirlockError(NetworkException exception) {
        return hasAirlockObject(airlockErrorResponseFromErrorResponse(exception.bodyString()));
    }

    /* access modifiers changed from: 0000 */
    public boolean hasAirlockObject(AirlockErrorResponse response) {
        return (response == null || response.clientErrorInfo == null || response.clientErrorInfo.airlock == null) ? false : true;
    }

    /* access modifiers changed from: 0000 */
    public void launchAirlockActivity(Context context2, Airlock airlock, boolean isLoginAirlock) {
        Intent airlockIntent = AirlockActivityIntents.forAirlock(context2, airlock, isLoginAirlock);
        airlockIntent.setFlags(805306368);
        context2.startActivity(airlockIntent);
    }

    private void loginUserIfNecessary(Context context2, Login login) {
        if (!this.accountManager.isCurrentUserAuthorized() && login != null) {
            Account account = login.getAccount();
            this.accountManager.setCurrentUser(account.getUser());
            this.accountManager.storeCurrentUser();
            this.accountManager.setIsVRPlatformPoweredHost(account.isVrPlatformPoweredHost());
            AuthorizedAccountHelper.get(context2).addOrUpdateCurrentUser();
            ButtonPartnership.get().onLogin(account.getUser());
            String accessToken = login.getId();
            if (!TextUtils.isEmpty(accessToken)) {
                this.accountManager.setAccessToken(accessToken);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public AirlockErrorResponse airlockErrorResponseFromErrorResponse(String errorResponse) {
        AirlockErrorResponse response = null;
        try {
            response = (AirlockErrorResponse) this.objectMapper.readValue(errorResponse, AirlockErrorResponse.class);
        } catch (Exception e) {
            Log.d(TAG, "Unable to parse AirlockErrorResponse from ErrorResponse", e);
        }
        return response != null ? response : new AirlockErrorResponse();
    }
}
