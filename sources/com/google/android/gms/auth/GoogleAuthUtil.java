package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.Context;
import java.io.IOException;

public final class GoogleAuthUtil extends zze {
    public static final String KEY_ANDROID_PACKAGE_NAME = zze.KEY_ANDROID_PACKAGE_NAME;
    public static final String KEY_CALLER_UID = zze.KEY_CALLER_UID;

    public static String getToken(Context context, Account account, String str) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zze.getToken(context, account, str);
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zze.getToken(context, str, str2);
    }

    @Deprecated
    public static void invalidateToken(Context context, String str) {
        zze.invalidateToken(context, str);
    }
}
