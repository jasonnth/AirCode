package com.facebook.accountkit.p029ui;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.p029ui.AccountKitActivity.ResponseType;

/* renamed from: com.facebook.accountkit.ui.PhoneLoginFlowManager */
class PhoneLoginFlowManager extends LoginFlowManager {
    public static final Creator<PhoneLoginFlowManager> CREATOR = new Creator<PhoneLoginFlowManager>() {
        public PhoneLoginFlowManager createFromParcel(Parcel source) {
            return new PhoneLoginFlowManager(source);
        }

        public PhoneLoginFlowManager[] newArray(int size) {
            return new PhoneLoginFlowManager[size];
        }
    };
    private PhoneNumber lastUsedPhoneNumber;

    PhoneLoginFlowManager(AccountKitConfiguration configuration) {
        super(LoginType.PHONE);
        this.activityHandler = new ActivityPhoneHandler(configuration);
    }

    private PhoneNumber getLastUsedPhoneNumber() {
        return this.lastUsedPhoneNumber;
    }

    /* access modifiers changed from: 0000 */
    public void setLastUsedPhoneNumber(PhoneNumber lastUsedPhoneNumber2) {
        this.lastUsedPhoneNumber = lastUsedPhoneNumber2;
    }

    public void logInWithPhoneNumber(PhoneNumber phoneNumber, NotificationChannel notificationChannel, ResponseType responseType, String initialAuthState) {
        if (isValid()) {
            setLastUsedPhoneNumber(phoneNumber);
            AccountKitController.logInWithPhoneNumber(phoneNumber, notificationChannel, responseType.getValue(), initialAuthState);
        }
    }

    public void setConfirmationCode(String confirmationCode) {
        if (isValid()) {
            AccountKitController.continueLoginWithCode(confirmationCode);
        }
    }

    /* access modifiers changed from: 0000 */
    public void confirmSeamlessLogin() {
        if (isValid()) {
            AccountKitController.continueSeamlessLogin();
        }
    }

    PhoneLoginFlowManager(Parcel parcel) {
        super(parcel);
        this.activityHandler = (ActivityHandler) parcel.readParcelable(ActivityPhoneHandler.class.getClassLoader());
        setLastUsedPhoneNumber((PhoneNumber) parcel.readParcelable(PhoneNumber.class.getClassLoader()));
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.activityHandler, flags);
        dest.writeParcelable(getLastUsedPhoneNumber(), flags);
    }
}
