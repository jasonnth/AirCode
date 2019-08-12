package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.braintreepayments.api.exceptions.InvalidArgumentException;

public abstract class Authorization implements Parcelable {
    private final String mRawValue;

    public abstract String getConfigUrl();

    public Authorization(String rawValue) {
        this.mRawValue = rawValue;
    }

    public static Authorization fromString(String authorizationString) throws InvalidArgumentException {
        if (isTokenizationKey(authorizationString)) {
            return new TokenizationKey(authorizationString);
        }
        return new ClientToken(authorizationString);
    }

    public String toString() {
        return this.mRawValue;
    }

    public static boolean isTokenizationKey(String tokenizationKey) {
        return !TextUtils.isEmpty(tokenizationKey) && tokenizationKey.matches("^[a-zA-Z0-9]+_[a-zA-Z0-9]+_[a-zA-Z0-9_]+$");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mRawValue);
    }

    public Authorization(Parcel in) {
        this.mRawValue = in.readString();
    }
}
