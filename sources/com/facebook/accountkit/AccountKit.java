package com.facebook.accountkit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.p029ui.NotificationChannel;
import java.util.concurrent.Executor;

public final class AccountKit {
    public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
    public static final String APPLICATION_NAME_PROPERTY = "com.facebook.accountkit.ApplicationName";
    public static final String CLIENT_TOKEN_PROPERTY = "com.facebook.accountkit.ClientToken";
    public static final String FACEBOOK_APP_EVENTS_ENABLED_PROPERTY = "com.facebook.accountkit.AccountKitFacebookAppEventsEnabled";
    private static final Object LOCK = new Object();
    private static volatile Executor executor;
    private static final LoggingBehaviorCollection loggingBehaviors = new LoggingBehaviorCollection();

    public interface InitializeCallback {
        void onInitialized();
    }

    public static LoggingBehaviorCollection getLoggingBehaviors() {
        return loggingBehaviors;
    }

    public static boolean isInitialized() {
        return AccountKitController.isInitialized();
    }

    @Deprecated
    public static synchronized void initialize(Context applicationContext) {
        synchronized (AccountKit.class) {
            initialize(applicationContext, null);
        }
    }

    public static void initialize(Context applicationContext, InitializeCallback callback) {
        AccountKitController.initialize(applicationContext, callback);
    }

    @Deprecated
    public static EmailLoginModel logInWithEmail(String email, String responseType, String initialAuthState) {
        return AccountKitController.logInWithEmail(email, responseType, initialAuthState);
    }

    @Deprecated
    public static PhoneLoginModel logInWithPhoneNumber(PhoneNumber phoneNumber, NotificationChannel sendWithFacebookNotification, String responseType, String initialAuthState) {
        if (getCurrentAccessToken() != null) {
            logOut();
        }
        return AccountKitController.logInWithPhoneNumber(phoneNumber, sendWithFacebookNotification, responseType, initialAuthState);
    }

    public static void logOut() {
        AccountKitController.logOut();
    }

    private static void logOut(AccountKitCallback<Void> callback) {
        AccountKitController.logOut(callback);
    }

    public static void cancelLogin() {
        AccountKitController.cancelLogin();
    }

    public static AccessToken getCurrentAccessToken() {
        return AccountKitController.getCurrentAccessToken();
    }

    public static AccountPreferences getAccountPreferences() {
        return AccountKitController.getAccountPreferences();
    }

    public static void getCurrentAccount(AccountKitCallback<Account> callback) {
        AccountKitController.getCurrentAccount(callback);
    }

    public static EmailLoginModel getCurrentEmailLogInModel() {
        return AccountKitController.getCurrentEmailLogInModel();
    }

    public static PhoneLoginModel getCurrentPhoneNumberLogInModel() {
        return AccountKitController.getCurrentPhoneNumberLogInModel();
    }

    public static void onActivityCreate(Activity activity, Bundle savedInstanceState) {
        AccountKitController.onActivityCreate(activity, savedInstanceState);
    }

    public static void onActivityDestroy(Activity activity) {
        AccountKitController.onActivityDestroy(activity);
    }

    public static void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        AccountKitController.onActivitySaveInstanceState(activity, outState);
    }

    public static String getApplicationId() {
        return AccountKitController.getApplicationId();
    }

    public static String getApplicationName() {
        return AccountKitController.getApplicationName();
    }

    public static String getClientToken() {
        return AccountKitController.getClientToken();
    }

    public static boolean getAccountKitFacebookAppEventsEnabled() {
        return AccountKitController.getAccountKitFacebookAppEventsEnabled();
    }

    public static Executor getExecutor() {
        synchronized (LOCK) {
            if (executor == null) {
                executor = AsyncTask.THREAD_POOL_EXECUTOR;
            }
        }
        return executor;
    }

    public static void setExecutor(Executor executor2) {
        synchronized (LOCK) {
            executor = executor2;
        }
    }

    public static AccountKitLoginResult loginResultWithIntent(Intent data) {
        if (data == null) {
            return null;
        }
        Parcelable loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
        if (!(loginResult instanceof AccountKitLoginResult)) {
            return null;
        }
        return (AccountKitLoginResult) loginResult;
    }
}
