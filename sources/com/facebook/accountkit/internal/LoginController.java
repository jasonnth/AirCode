package com.facebook.accountkit.internal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.Tracker;
import com.facebook.accountkit.internal.LoginModelImpl;
import java.lang.ref.WeakReference;

abstract class LoginController<E extends LoginModelImpl> {
    static final String GRAPH_PATH_LOGIN_REQUEST_CONFIRM = "confirm_login";
    static final String GRAPH_PATH_LOGIN_REQUEST_STATUS = "poll_login";
    static final String GRAPH_PATH_LOGIN_START = "start_login";
    static final String GRAPH_PATH_SEAMLESS_REQUEST_CONFIRM = "instant_verification_login";
    static final String PARAMETER_ARGUMENT_FACEBOOK = "facebook";
    static final String PARAMETER_ARGUMENT_VOICE = "voice";
    private static final String PARAMETER_CREDENTIALS_TYPE = "credentials_type";
    static final String PARAMETER_FB_USER_TOKEN = "fb_user_token";
    static final String PARAMETER_FIELDS_TYPE = "fields";
    private static final String PARAMETER_LOGGING_REF = "logging_ref";
    private static final String PARAMETER_LOGIN_REQUEST_CODE = "login_request_code";
    static final String PARAMETER_REDIRECT_URI = "redirect_uri";
    static final String PARAMETER_RESPONSE_TYPE = "response_type";
    static final String PARAMETER_STATE = "state";
    private static final String TAG = LoginController.class.getName();
    final AccessTokenManager accessTokenManager;
    private final WeakReference<LoginManager> loginManagerRef;
    protected final E loginModel;

    /* access modifiers changed from: protected */
    public abstract String getCredentialsType();

    /* access modifiers changed from: protected */
    public abstract String getLoginStateChangedIntentName();

    public abstract void logIn(String str);

    public abstract void onAccountVerified();

    public abstract void onCancel();

    public abstract void onPending();

    LoginController(AccessTokenManager accessTokenManager2, LoginManager loginManager, E loginModel2) {
        this.accessTokenManager = accessTokenManager2;
        this.loginManagerRef = new WeakReference<>(loginManager);
        this.loginModel = loginModel2;
    }

    public E getLoginModel() {
        return this.loginModel;
    }

    public void onError(AccountKitError error) {
        this.loginModel.setError(error);
        this.loginModel.setStatus(LoginStatus.ERROR);
        LoginManager loginManager = getLoginManager();
        if (loginManager != null) {
            loginManager.cancel(this.loginModel);
        }
    }

    /* access modifiers changed from: 0000 */
    public AccountKitGraphRequest buildGraphRequest(String graphPath, Bundle extraParameters) {
        String str;
        Bundle parameters = new Bundle();
        Utility.putNonNullString(parameters, PARAMETER_CREDENTIALS_TYPE, getCredentialsType());
        Utility.putNonNullString(parameters, "login_request_code", this.loginModel.getLoginRequestCode());
        String str2 = PARAMETER_LOGGING_REF;
        if (getLoginManager() != null) {
            str = getLoginManager().getLogger().getLoggingRef();
        } else {
            str = null;
        }
        Utility.putNonNullString(parameters, str2, str);
        parameters.putAll(extraParameters);
        return new AccountKitGraphRequest(null, graphPath, parameters, isLoginRequestPath(graphPath), HttpMethod.POST);
    }

    /* access modifiers changed from: 0000 */
    public LoginManager getLoginManager() {
        LoginManager loginManager = (LoginManager) this.loginManagerRef.get();
        if (loginManager == null) {
            return null;
        }
        if (loginManager.isActivityAvailable()) {
            return loginManager;
        }
        Log.w(TAG, "Warning: Callback issues while activity not available.");
        return null;
    }

    /* access modifiers changed from: protected */
    public void onError(Type errorType, InternalAccountKitError internalError) {
        onError(new AccountKitError(errorType, internalError));
    }

    /* access modifiers changed from: 0000 */
    public void broadcastLoginStateChange() {
        LoginManager loginManager = getLoginManager();
        if (loginManager != null) {
            loginManager.getLocalBroadcastManager().sendBroadcast(new Intent(getLoginStateChangedIntentName()).putExtra(Tracker.EXTRA_LOGIN_MODEL, this.loginModel).putExtra(Tracker.EXTRA_LOGIN_STATUS, this.loginModel.getStatus()).putExtra(Tracker.EXTRA_LOGIN_ERROR, this.loginModel.getError()));
        }
    }

    private boolean isLoginRequestPath(String requestPath) {
        return Utility.areObjectsEqual(requestPath, GRAPH_PATH_LOGIN_START) || Utility.areObjectsEqual(requestPath, GRAPH_PATH_LOGIN_REQUEST_STATUS) || Utility.areObjectsEqual(requestPath, GRAPH_PATH_LOGIN_REQUEST_CONFIRM);
    }
}
