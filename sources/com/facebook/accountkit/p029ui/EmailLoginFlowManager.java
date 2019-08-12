package com.facebook.accountkit.p029ui;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.p029ui.AccountKitActivity.ResponseType;

/* renamed from: com.facebook.accountkit.ui.EmailLoginFlowManager */
class EmailLoginFlowManager extends LoginFlowManager {
    public static final Creator<EmailLoginFlowManager> CREATOR = new Creator<EmailLoginFlowManager>() {
        public EmailLoginFlowManager createFromParcel(Parcel source) {
            return new EmailLoginFlowManager(source);
        }

        public EmailLoginFlowManager[] newArray(int size) {
            return new EmailLoginFlowManager[size];
        }
    };
    private String email;

    public EmailLoginFlowManager(AccountKitConfiguration configuration) {
        super(LoginType.EMAIL);
        this.activityHandler = new ActivityEmailHandler(configuration);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email2) {
        this.email = email2;
    }

    public void logInWithEmail(ResponseType responseType, String initialAuthState) {
        if (isValid() && this.email != null) {
            AccountKitController.logInWithEmail(this.email, responseType.getValue(), initialAuthState);
        }
    }

    protected EmailLoginFlowManager(Parcel parcel) {
        super(parcel);
        this.activityHandler = (ActivityHandler) parcel.readParcelable(ActivityEmailHandler.class.getClassLoader());
        this.email = parcel.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.activityHandler, flags);
        dest.writeString(this.email);
    }
}
