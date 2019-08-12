package com.facebook.accountkit.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.EmailLoginModel;

public final class EmailLoginModelImpl extends LoginModelImpl implements EmailLoginModel {
    public static final Creator<EmailLoginModelImpl> CREATOR = new Creator<EmailLoginModelImpl>() {
        public EmailLoginModelImpl createFromParcel(Parcel source) {
            return new EmailLoginModelImpl(source);
        }

        public EmailLoginModelImpl[] newArray(int size) {
            return new EmailLoginModelImpl[size];
        }
    };
    private String email;
    private int interval;

    public /* bridge */ /* synthetic */ AccessToken getAccessToken() {
        return super.getAccessToken();
    }

    public /* bridge */ /* synthetic */ String getCode() {
        return super.getCode();
    }

    public /* bridge */ /* synthetic */ AccountKitError getError() {
        return super.getError();
    }

    public /* bridge */ /* synthetic */ String getFinalAuthState() {
        return super.getFinalAuthState();
    }

    public /* bridge */ /* synthetic */ String getInitialAuthState() {
        return super.getInitialAuthState();
    }

    public /* bridge */ /* synthetic */ String getResponseType() {
        return super.getResponseType();
    }

    public /* bridge */ /* synthetic */ LoginStatus getStatus() {
        return super.getStatus();
    }

    public String getEmail() {
        return this.email;
    }

    public int getInterval() {
        return this.interval;
    }

    EmailLoginModelImpl(String email2, String requestType) {
        super(requestType);
        this.email = email2;
    }

    /* access modifiers changed from: 0000 */
    public void setEmail(String email2) {
        this.email = email2;
    }

    /* access modifiers changed from: 0000 */
    public void setInterval(int interval2) {
        this.interval = interval2;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EmailLoginModelImpl)) {
            return false;
        }
        EmailLoginModelImpl o = (EmailLoginModelImpl) other;
        if (!super.equals(other) || this.interval != o.interval || !Utility.areObjectsEqual(this.email, o.email)) {
            return false;
        }
        return true;
    }

    private EmailLoginModelImpl(Parcel parcel) {
        super(parcel);
        this.email = parcel.readString();
        this.interval = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.email);
        dest.writeInt(this.interval);
    }
}
