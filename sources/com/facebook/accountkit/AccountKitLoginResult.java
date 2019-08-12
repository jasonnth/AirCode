package com.facebook.accountkit;

import android.os.Parcelable;

public interface AccountKitLoginResult extends Parcelable {
    public static final String RESULT_KEY = "account_kit_log_in_result";

    AccessToken getAccessToken();

    String getAuthorizationCode();

    AccountKitError getError();

    String getFinalAuthorizationState();

    long getTokenRefreshIntervalInSeconds();

    boolean wasCancelled();
}
