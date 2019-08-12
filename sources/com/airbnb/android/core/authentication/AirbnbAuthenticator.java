package com.airbnb.android.core.authentication;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.Activities;

public class AirbnbAuthenticator extends AbstractAccountAuthenticator {
    public static final String AIRBNB_ACCOUNT_TOKEN_TYPE = "com.airbnb.android.authtoken";
    public static final String AIRBNB_ACCOUNT_TYPE = "com.airbnb.android";
    public static final String KEY_PICTURE_URL = "pictureurl";
    public static final String KEY_USER_ID = "id";
    private final Context context;

    public AirbnbAuthenticator(Context context2) {
        super(context2);
        this.context = context2;
    }

    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
        Intent intent = new Intent(this.context, Activities.login());
        intent.putExtra("accountAuthenticatorResponse", response);
        Bundle bundle = new Bundle();
        bundle.putParcelable("intent", intent);
        return bundle;
    }

    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        return null;
    }
}
