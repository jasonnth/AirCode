package com.facebook.accountkit.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneLoginModel;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.p029ui.NotificationChannel;
import java.util.HashMap;
import java.util.Map;

public final class PhoneLoginModelImpl extends LoginModelImpl implements PhoneLoginModel {
    public static final Creator<PhoneLoginModelImpl> CREATOR = new Creator<PhoneLoginModelImpl>() {
        public PhoneLoginModelImpl createFromParcel(Parcel source) {
            return new PhoneLoginModelImpl(source);
        }

        public PhoneLoginModelImpl[] newArray(int size) {
            return new PhoneLoginModelImpl[size];
        }
    };
    private String confirmationCode;
    private Map<String, String> fields;
    private final NotificationChannel notificationChannel;
    private PhoneNumber phoneNumber;
    private long resendTime;

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

    PhoneLoginModelImpl(PhoneNumber phoneNumber2, NotificationChannel notificationChannel2, String requestType) {
        super(requestType);
        this.fields = new HashMap();
        this.notificationChannel = notificationChannel2;
        this.phoneNumber = phoneNumber2;
    }

    /* access modifiers changed from: 0000 */
    public void setConfirmationCode(String confirmationCode2) {
        Validate.isEquals(getStatus(), LoginStatus.PENDING, "Phone status");
        Validate.sdkInitialized();
        this.confirmationCode = confirmationCode2;
    }

    public PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getConfirmationCode() {
        return this.confirmationCode;
    }

    public String getPrivacyPolicy() {
        return (String) this.fields.get(AccountKitGraphConstants.PRIVACY_POLICY);
    }

    public String getTermsOfService() {
        return (String) this.fields.get(AccountKitGraphConstants.TERMS_OF_SERVICE);
    }

    public long getResendTime() {
        return this.resendTime;
    }

    /* access modifiers changed from: 0000 */
    public void putField(String key, String value) {
        this.fields.put(key, value);
    }

    public NotificationChannel getNotificationChannel() {
        return this.notificationChannel;
    }

    /* access modifiers changed from: 0000 */
    public void setPhoneNumber(PhoneNumber phoneNumber2) {
        this.phoneNumber = phoneNumber2;
    }

    /* access modifiers changed from: 0000 */
    public void setResendTime(long resendTime2) {
        this.resendTime = resendTime2;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PhoneLoginModelImpl)) {
            return false;
        }
        PhoneLoginModelImpl o = (PhoneLoginModelImpl) other;
        if (!super.equals(o) || !Utility.areObjectsEqual(this.confirmationCode, o.confirmationCode) || !Utility.areObjectsEqual(this.phoneNumber, o.phoneNumber) || this.notificationChannel != o.notificationChannel || this.resendTime != o.resendTime) {
            return false;
        }
        return true;
    }

    private PhoneLoginModelImpl(Parcel parcel) {
        super(parcel);
        this.fields = new HashMap();
        this.phoneNumber = (PhoneNumber) parcel.readParcelable(PhoneNumber.class.getClassLoader());
        this.confirmationCode = parcel.readString();
        this.notificationChannel = NotificationChannel.values()[parcel.readInt()];
        this.fields = new HashMap();
        int size = parcel.readInt();
        for (int i = 0; i < size; i++) {
            this.fields.put(parcel.readString(), parcel.readString());
        }
        this.resendTime = parcel.readLong();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.phoneNumber, flags);
        dest.writeString(this.confirmationCode);
        dest.writeInt(this.notificationChannel.ordinal());
        dest.writeInt(this.fields.size());
        for (String key : this.fields.keySet()) {
            dest.writeString(key);
            dest.writeString((String) this.fields.get(key));
        }
        dest.writeLong(this.resendTime);
    }
}
