package com.facebook.accountkit.internal;

import android.os.Parcel;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.LoginModel;

abstract class LoginModelImpl implements LoginModel {
    private static final int PARCEL_VERSION = 2;
    private AccessToken accessToken;
    private String code;
    private AccountKitError error;
    private long expiresInSeconds;
    private String finalAuthState;
    private String initialAuthState;
    private String loginModelCode;
    private String responseType;
    private LoginStatus status = LoginStatus.EMPTY;

    LoginModelImpl(String responseType2) {
        this.responseType = responseType2;
    }

    public String getCode() {
        return this.code;
    }

    public AccountKitError getError() {
        return this.error;
    }

    /* access modifiers changed from: 0000 */
    public String getLoginRequestCode() {
        return this.loginModelCode;
    }

    /* access modifiers changed from: 0000 */
    public void setLoginCode(String loginModelCode2) {
        this.loginModelCode = loginModelCode2;
    }

    /* access modifiers changed from: 0000 */
    public long getExpiresInSeconds() {
        return this.expiresInSeconds;
    }

    /* access modifiers changed from: 0000 */
    public void setExpiresInSeconds(long expiresInSeconds2) {
        this.expiresInSeconds = expiresInSeconds2;
    }

    /* access modifiers changed from: 0000 */
    public void setStatus(LoginStatus status2) {
        this.status = status2;
    }

    public LoginStatus getStatus() {
        return this.status;
    }

    /* access modifiers changed from: 0000 */
    public void setError(AccountKitError error2) {
        this.error = error2;
    }

    public String getResponseType() {
        return this.responseType;
    }

    /* access modifiers changed from: 0000 */
    public void setInitialAuthState(String initialAuthState2) {
        this.initialAuthState = initialAuthState2;
    }

    public String getInitialAuthState() {
        return this.initialAuthState;
    }

    /* access modifiers changed from: 0000 */
    public void setFinalAuthState(String finalAuthState2) {
        this.finalAuthState = finalAuthState2;
    }

    public String getFinalAuthState() {
        return this.finalAuthState;
    }

    /* access modifiers changed from: 0000 */
    public void setCode(String code2) {
        this.code = code2;
    }

    /* access modifiers changed from: 0000 */
    public void setAccessToken(AccessToken accessToken2) {
        this.accessToken = accessToken2;
    }

    public AccessToken getAccessToken() {
        return this.accessToken;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoginModelImpl)) {
            return false;
        }
        LoginModelImpl o = (LoginModelImpl) other;
        if (this.expiresInSeconds != o.expiresInSeconds || !Utility.areObjectsEqual(this.error, o.error) || !Utility.areObjectsEqual(this.loginModelCode, o.loginModelCode) || !Utility.areObjectsEqual(this.status, o.status) || !Utility.areObjectsEqual(this.responseType, o.responseType) || !Utility.areObjectsEqual(this.finalAuthState, o.finalAuthState) || !Utility.areObjectsEqual(this.code, o.code)) {
            return false;
        }
        return true;
    }

    LoginModelImpl(Parcel parcel) {
        if (parcel.readInt() == 2) {
            this.error = (AccountKitError) parcel.readParcelable(AccountKitError.class.getClassLoader());
            this.expiresInSeconds = parcel.readLong();
            this.loginModelCode = parcel.readString();
            this.status = LoginStatus.valueOf(parcel.readString());
            this.responseType = parcel.readString();
            this.finalAuthState = parcel.readString();
            this.code = parcel.readString();
            return;
        }
        this.error = new AccountKitError(Type.LOGIN_INVALIDATED);
        this.status = LoginStatus.ERROR;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(2);
        dest.writeParcelable(this.error, flags);
        dest.writeLong(this.expiresInSeconds);
        dest.writeString(this.loginModelCode);
        dest.writeString(this.status.name());
        dest.writeString(this.responseType);
        dest.writeString(this.finalAuthState);
        dest.writeString(this.code);
    }
}
