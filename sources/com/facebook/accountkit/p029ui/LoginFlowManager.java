package com.facebook.accountkit.p029ui;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;

/* renamed from: com.facebook.accountkit.ui.LoginFlowManager */
abstract class LoginFlowManager implements Parcelable {
    protected ActivityHandler activityHandler;
    private LoginFlowState flowState;
    private boolean isValid = true;
    private final LoginType loginType;

    public LoginFlowManager(LoginType loginType2) {
        this.loginType = loginType2;
        this.flowState = LoginFlowState.NONE;
    }

    public void cancel() {
        this.isValid = false;
        AccountKit.cancelLogin();
    }

    public AccessToken getAccessToken() {
        if (!this.isValid) {
            return null;
        }
        return AccountKit.getCurrentAccessToken();
    }

    public boolean isValid() {
        return this.isValid;
    }

    public LoginType getLoginType() {
        return this.loginType;
    }

    public LoginFlowState getFlowState() {
        return this.flowState;
    }

    public final void setFlowState(LoginFlowState newState) {
        this.flowState = newState;
    }

    public ActivityHandler getActivityHandler() {
        return this.activityHandler;
    }

    protected LoginFlowManager(Parcel parcel) {
        boolean z = true;
        if (parcel.readByte() != 1) {
            z = false;
        }
        this.isValid = z;
        this.loginType = LoginType.valueOf(parcel.readString());
        this.flowState = LoginFlowState.values()[parcel.readInt()];
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.isValid ? 1 : 0));
        dest.writeString(this.loginType.name());
        dest.writeInt(this.flowState.ordinal());
    }
}
