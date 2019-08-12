package com.facebook.accountkit.p029ui;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;

/* renamed from: com.facebook.accountkit.ui.AccountKitLoginResultImpl */
class AccountKitLoginResultImpl implements AccountKitLoginResult {
    public static final Creator<AccountKitLoginResultImpl> CREATOR = new Creator<AccountKitLoginResultImpl>() {
        public AccountKitLoginResultImpl createFromParcel(Parcel source) {
            return new AccountKitLoginResultImpl(source);
        }

        public AccountKitLoginResultImpl[] newArray(int size) {
            return new AccountKitLoginResultImpl[size];
        }
    };
    private final AccessToken accessToken;
    private final String authorizationCode;
    private final boolean cancelled;
    private final AccountKitError error;
    private final String finalAuthorizationState;
    private final long tokenRefreshIntervalInSeconds;

    public AccountKitLoginResultImpl(AccessToken accessToken2, String authorizationCode2, String finalAuthorizationState2, long tokenRefreshIntervalInSeconds2, AccountKitError error2, boolean cancelled2) {
        this.accessToken = accessToken2;
        this.authorizationCode = authorizationCode2;
        this.tokenRefreshIntervalInSeconds = tokenRefreshIntervalInSeconds2;
        this.cancelled = cancelled2;
        this.error = error2;
        this.finalAuthorizationState = finalAuthorizationState2;
    }

    public AccessToken getAccessToken() {
        return this.accessToken;
    }

    public String getFinalAuthorizationState() {
        return this.finalAuthorizationState;
    }

    public String getAuthorizationCode() {
        return this.authorizationCode;
    }

    public AccountKitError getError() {
        return this.error;
    }

    public boolean wasCancelled() {
        return this.error == null && this.authorizationCode == null && this.accessToken == null;
    }

    public long getTokenRefreshIntervalInSeconds() {
        return this.tokenRefreshIntervalInSeconds;
    }

    private AccountKitLoginResultImpl(Parcel parcel) {
        this.accessToken = (AccessToken) parcel.readParcelable(AccessToken.class.getClassLoader());
        this.authorizationCode = parcel.readString();
        this.finalAuthorizationState = parcel.readString();
        this.tokenRefreshIntervalInSeconds = parcel.readLong();
        this.error = (AccountKitError) parcel.readParcelable(AccountKitError.class.getClassLoader());
        this.cancelled = parcel.readByte() == 1;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.accessToken, flags);
        dest.writeString(this.authorizationCode);
        dest.writeString(this.finalAuthorizationState);
        dest.writeLong(this.tokenRefreshIntervalInSeconds);
        dest.writeParcelable(this.error, flags);
        dest.writeByte((byte) (this.cancelled ? 1 : 0));
    }
}
