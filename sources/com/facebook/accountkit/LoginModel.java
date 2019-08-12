package com.facebook.accountkit;

import android.os.Parcelable;

public interface LoginModel extends Parcelable {
    AccessToken getAccessToken();

    String getCode();

    String getFinalAuthState();
}
