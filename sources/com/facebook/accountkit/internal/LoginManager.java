package com.facebook.accountkit.internal;

import android.app.Activity;
import android.os.Bundle;
import android.support.p000v4.content.LocalBroadcastManager;
import android.util.Log;
import android.util.Pair;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.LoginModel;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.internal.AccountKitGraphRequest.Callback;
import com.facebook.accountkit.internal.SeamlessLoginClient.CompletedListener;
import com.facebook.accountkit.p029ui.NotificationChannel;
import com.facebook.internal.NativeProtocol;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

final class LoginManager {
    private static final String LOGOUT_PATH = "logout/";
    private static final String SAVED_LOGIN_MODEL = "accountkitLoginModel";
    private static final String TAG = LoginManager.class.getName();
    /* access modifiers changed from: private */
    public final AccessTokenManager accessTokenManager;
    private volatile Activity currentActivity;
    private volatile LoginController currentLoginController;
    private volatile boolean isActivityAvailable = false;
    private final LocalBroadcastManager localBroadcastManager;
    /* access modifiers changed from: private */
    public final InternalLogger logger;
    private String requestInstanceToken;
    private SeamlessLoginClient seamlessLoginClient;
    private long seamlessLoginExpirationMillis;
    private String seamlessLoginToken;

    LoginManager(InternalLogger internalLogger, AccessTokenManager accessTokenManager2, LocalBroadcastManager localBroadcastManager2) {
        this.accessTokenManager = accessTokenManager2;
        this.localBroadcastManager = localBroadcastManager2;
        this.logger = internalLogger;
        resetRequestInstanceToken();
    }

    /* access modifiers changed from: 0000 */
    public void continueWithCode(String code) {
        PhoneLoginModelImpl loginModel = getCurrentPhoneNumberLogInModel();
        if (loginModel != null) {
            try {
                loginModel.setConfirmationCode(code);
                handle(loginModel);
            } catch (AccountKitException e) {
                if (!Utility.isDebuggable(AccountKitController.getApplicationContext())) {
                    this.logger.logLoginModel(InternalLogger.EVENT_NAME_SET_CONFIRMATION_CODE, loginModel);
                    return;
                }
                throw e;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void continueSeamlessLogin() {
        PhoneLoginModelImpl loginModel = getCurrentPhoneNumberLogInModel();
        if (loginModel != null) {
            try {
                handle(loginModel);
            } catch (AccountKitException e) {
                if (!Utility.isDebuggable(AccountKitController.getApplicationContext())) {
                    this.logger.logLoginModel(InternalLogger.EVENT_NAME_CONFIRM_SEAMLESS_PENDING, loginModel);
                    return;
                }
                throw e;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void onActivityCreate(Activity activity, Bundle savedInstanceState) {
        this.isActivityAvailable = true;
        this.currentActivity = activity;
        this.logger.onActivityCreate(savedInstanceState);
        if (savedInstanceState != null) {
            LoginModelImpl loginModel = (LoginModelImpl) savedInstanceState.getParcelable(SAVED_LOGIN_MODEL);
            if (loginModel != null) {
                startWith(loginModel);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void onActivityDestroy(Activity activity) {
        if (this.currentActivity == activity) {
            this.isActivityAvailable = false;
            this.currentLoginController = null;
            this.currentActivity = null;
            AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
            AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(null);
        }
    }

    /* access modifiers changed from: 0000 */
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        if (this.currentActivity == activity) {
            this.logger.saveInstanceState(outState);
            if (this.currentLoginController != null) {
                outState.putParcelable(SAVED_LOGIN_MODEL, this.currentLoginController.getLoginModel());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public InternalLogger getLogger() {
        return this.logger;
    }

    /* access modifiers changed from: 0000 */
    public void handle(LoginModelImpl loginModel) {
        Validate.loginModelsEqual(loginModel, this.currentLoginController.getLoginModel());
        Utility.assertUIThread();
        switch (loginModel.getStatus()) {
            case PENDING:
                this.currentLoginController.onPending();
                return;
            case ACCOUNT_VERIFIED:
                this.currentLoginController.onAccountVerified();
                return;
            case ERROR:
                this.currentLoginController.onError(loginModel.getError());
                return;
            case CANCELLED:
                this.currentLoginController.onCancel();
                return;
            default:
                return;
        }
    }

    private void onLoginStart(LoginModelImpl loginModel) {
        this.logger.logLoginModel(InternalLogger.EVENT_NAME_LOGIN_START, loginModel);
    }

    /* access modifiers changed from: 0000 */
    public void onLoginVerify(LoginModelImpl loginModel) {
        this.logger.logLoginModel(InternalLogger.EVENT_NAME_LOGIN_VERIFY, loginModel);
    }

    /* access modifiers changed from: 0000 */
    public void onSeamlessLoginPending(LoginModelImpl loginModel) {
        this.logger.logLoginModel(InternalLogger.EVENT_NAME_CONFIRM_SEAMLESS_PENDING, loginModel);
    }

    /* access modifiers changed from: 0000 */
    public void onLoginComplete(LoginModelImpl loginModel) {
        this.logger.logLoginModel(InternalLogger.EVENT_NAME_LOGIN_COMPLETE, loginModel);
    }

    /* access modifiers changed from: 0000 */
    public EmailLoginModelImpl getCurrentEmailLogInModel() {
        if (this.currentLoginController == null) {
            return null;
        }
        LoginModelImpl loginModel = this.currentLoginController.getLoginModel();
        if (!(loginModel instanceof EmailLoginModelImpl)) {
            return null;
        }
        return (EmailLoginModelImpl) loginModel;
    }

    /* access modifiers changed from: 0000 */
    public PhoneLoginModelImpl getCurrentPhoneNumberLogInModel() {
        if (this.currentLoginController == null) {
            return null;
        }
        LoginModelImpl loginModel = this.currentLoginController.getLoginModel();
        if (!(loginModel instanceof PhoneLoginModelImpl)) {
            return null;
        }
        return (PhoneLoginModelImpl) loginModel;
    }

    /* access modifiers changed from: 0000 */
    public void cancelLogin() {
        Utility.assertUIThread();
        resetRequestInstanceToken();
        if (this.currentLoginController != null) {
            this.currentLoginController.onCancel();
            AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(null);
            this.currentLoginController = null;
        }
        AccountKitGraphRequestAsyncTask currentAsyncTask = AccountKitGraphRequestAsyncTask.getCurrentAsyncTask();
        if (currentAsyncTask != null) {
            currentAsyncTask.cancel(true);
            AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(null);
        }
    }

    /* access modifiers changed from: 0000 */
    public String getRequestInstanceToken() {
        return this.requestInstanceToken;
    }

    /* access modifiers changed from: 0000 */
    public void initializeLogin() {
        this.seamlessLoginToken = null;
        this.logger.logFetchEvent(InternalLogger.EVENT_NAME_FETCH_SEAMLESS_LOGIN_TOKEN, InternalLogger.EVENT_PARAM_EXTRAS_STARTED);
        this.seamlessLoginClient = new SeamlessLoginClient(AccountKitController.getApplicationContext(), AccountKit.getApplicationId(), this.logger);
        if (this.seamlessLoginClient.start()) {
            this.seamlessLoginClient.setCompletedListener(new CompletedListener() {
                public void completed(Bundle result) {
                    LoginManager.this.seamlessLoginCompleted(result);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void seamlessLoginCompleted(Bundle result) {
        if (result != null) {
            this.seamlessLoginExpirationMillis = result.getLong(NativeProtocol.EXTRA_EXPIRES_SECONDS_SINCE_EPOCH) * 1000;
            this.seamlessLoginToken = result.getString("com.facebook.platform.extra.SEAMLESS_LOGIN_TOKEN");
            this.logger.logFetchEvent(InternalLogger.EVENT_NAME_FETCH_SEAMLESS_LOGIN_TOKEN, InternalLogger.EVENT_PARAM_EXTRAS_COMPLETED);
        }
    }

    /* access modifiers changed from: 0000 */
    public String getSeamlessLoginToken() {
        if (this.seamlessLoginExpirationMillis < System.currentTimeMillis()) {
            this.seamlessLoginToken = null;
        }
        return this.seamlessLoginToken;
    }

    /* access modifiers changed from: 0000 */
    public boolean isSeamlessLoginRunning() {
        return this.seamlessLoginToken == null && this.seamlessLoginClient != null && this.seamlessLoginClient.isRunning();
    }

    /* access modifiers changed from: 0000 */
    public EmailLoginModelImpl logInWithEmail(String email, String responseType, String initialAuthState) {
        Utility.assertUIThread();
        cancelExisting();
        EmailLoginModelImpl loginModel = new EmailLoginModelImpl(email, responseType);
        EmailLoginController loginHandler = new EmailLoginController(this.accessTokenManager, this, loginModel);
        loginHandler.logIn(initialAuthState);
        onLoginStart(loginModel);
        this.currentLoginController = loginHandler;
        return loginModel;
    }

    /* access modifiers changed from: 0000 */
    public PhoneLoginModelImpl logInWithPhoneNumber(PhoneNumber phoneNumber, NotificationChannel notificationChannel, String responseType, String initialAuthState) {
        Utility.assertUIThread();
        if (notificationChannel == NotificationChannel.SMS) {
            cancelExisting();
        }
        PhoneLoginModelImpl loginModel = new PhoneLoginModelImpl(phoneNumber, notificationChannel, responseType);
        PhoneLoginController loginHandler = new PhoneLoginController(this.accessTokenManager, this, loginModel);
        loginHandler.logIn(initialAuthState);
        onLoginStart(loginModel);
        this.currentLoginController = loginHandler;
        return loginModel;
    }

    /* access modifiers changed from: 0000 */
    public void logOut() {
        logOut(null);
        this.accessTokenManager.setCurrentAccessToken(null);
    }

    /* access modifiers changed from: 0000 */
    public void logOut(final AccountKitCallback<Void> callback) {
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken == null) {
            Log.w(TAG, "No access token: cannot log out");
            if (callback != null) {
                callback.onSuccess(null);
                return;
            }
            return;
        }
        AccountKitGraphRequest.executeAsync(new AccountKitGraphRequest(accessToken, LOGOUT_PATH, null, false, HttpMethod.POST), new Callback() {
            public void onCompleted(AccountKitGraphResponse response) {
                if (response.getError() != null) {
                    Pair<AccountKitError, InternalAccountKitError> error = Utility.createErrorFromServerError(response.getError());
                    LoginManager.this.logger.logEvent(InternalLogger.EVENT_NAME_LOG_OUT_ERROR);
                    if (callback != null) {
                        callback.onError((AccountKitError) error.first);
                        return;
                    }
                    return;
                }
                LoginManager.this.accessTokenManager.setCurrentAccessToken(null);
                LoginManager.this.logger.logEvent(InternalLogger.EVENT_NAME_LOG_OUT);
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void clearLogIn() {
        this.currentLoginController = null;
    }

    private void cancelExisting() {
        if (this.currentLoginController != null) {
            this.currentLoginController.getLoginModel().setStatus(LoginStatus.CANCELLED);
            this.currentLoginController.onCancel();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isActivityAvailable() {
        return this.isActivityAvailable;
    }

    /* access modifiers changed from: 0000 */
    public LocalBroadcastManager getLocalBroadcastManager() {
        return this.localBroadcastManager;
    }

    /* access modifiers changed from: 0000 */
    public boolean isLoginInProgress() {
        return this.currentLoginController != null;
    }

    private void cancelCurrentRequest() {
        this.currentLoginController = null;
        AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
        AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(null);
    }

    /* access modifiers changed from: 0000 */
    public void cancel(LoginModel loginModel) {
        this.seamlessLoginToken = null;
        if (this.currentLoginController != null && Utility.areObjectsEqual(loginModel, this.currentLoginController.getLoginModel())) {
            cancelCurrentRequest();
        }
    }

    private void startWith(LoginModelImpl loginModel) {
        Utility.assertUIThread();
        if (loginModel instanceof EmailLoginModelImpl) {
            this.currentLoginController = new EmailLoginController(this.accessTokenManager, this, (EmailLoginModelImpl) loginModel);
        } else if (loginModel instanceof PhoneLoginModelImpl) {
            this.currentLoginController = new PhoneLoginController(this.accessTokenManager, this, (PhoneLoginModelImpl) loginModel);
        } else {
            throw new AccountKitException(Type.ARGUMENT_ERROR, InternalAccountKitError.INVALID_LOGIN_TYPE, loginModel.getClass().getName());
        }
        handle(loginModel);
    }

    /* access modifiers changed from: 0000 */
    public void getCurrentAccount(final AccountKitCallback<Account> callback) {
        final AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken == null) {
            Log.w(TAG, "No access token: cannot retrieve account");
            callback.onError(new AccountKitError(Type.INTERNAL_ERROR, InternalAccountKitError.CANNOT_RETRIEVE_ACCESS_TOKEN_NO_ACCOUNT));
            return;
        }
        AccountKitGraphRequest.executeAsync(new AccountKitGraphRequest(accessToken, accessToken.getAccountId(), null, false, HttpMethod.GET), new Callback() {
            public void onCompleted(AccountKitGraphResponse response) {
                if (response.getError() != null) {
                    callback.onError((AccountKitError) Utility.createErrorFromServerError(response.getError()).first);
                    return;
                }
                JSONObject result = response.getResponseObject();
                if (result == null) {
                    callback.onError(new AccountKitError(Type.LOGIN_INVALIDATED, InternalAccountKitError.NO_RESULT_FOUND));
                    return;
                }
                try {
                    String accountId = result.getString("id");
                    JSONObject emailBundle = result.optJSONObject("email");
                    String email = null;
                    if (emailBundle != null) {
                        email = emailBundle.getString("address");
                    }
                    JSONObject phoneNumberBundle = result.optJSONObject("phone");
                    String nationalPhoneNumber = null;
                    String countryCode = null;
                    if (phoneNumberBundle != null) {
                        nationalPhoneNumber = phoneNumberBundle.getString(AccountKitGraphConstants.PHONE_NATIONAL_NUMBER);
                        countryCode = phoneNumberBundle.getString(AccountKitGraphConstants.PHONE_COUNTRY_PREFIX);
                    }
                    if (countryCode == null && nationalPhoneNumber == null && email == null) {
                        callback.onError(new AccountKitError(Type.LOGIN_INVALIDATED, InternalAccountKitError.NO_ACCOUNT_FOUND));
                    } else if ((countryCode != null || nationalPhoneNumber == null) && (countryCode == null || nationalPhoneNumber != null)) {
                        PhoneNumber phoneNumber = null;
                        if (countryCode != null) {
                            phoneNumber = new PhoneNumber(countryCode, nationalPhoneNumber, null);
                        }
                        AccessToken currentAccessToken = AccountKit.getCurrentAccessToken();
                        if (currentAccessToken != null && accessToken.equals(currentAccessToken)) {
                            LoginManager.this.accessTokenManager.refreshCurrentAccessToken(currentAccessToken);
                        }
                        callback.onSuccess(new Account(accountId, phoneNumber, email));
                    } else {
                        callback.onError(new AccountKitError(Type.LOGIN_INVALIDATED, InternalAccountKitError.NO_ACCOUNT_FOUND));
                    }
                } catch (JSONException e) {
                    callback.onError(new AccountKitError(Type.LOGIN_INVALIDATED, InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT));
                }
            }
        });
    }

    private void resetRequestInstanceToken() {
        this.requestInstanceToken = UUID.randomUUID().toString();
    }
}
