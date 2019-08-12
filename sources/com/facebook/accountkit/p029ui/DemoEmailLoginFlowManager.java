package com.facebook.accountkit.p029ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.p000v4.content.LocalBroadcastManager;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.EmailLoginModel;
import com.facebook.accountkit.EmailLoginTracker;
import com.facebook.accountkit.Tracker;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.internal.InternalAccountKitError;
import com.facebook.accountkit.internal.LoginStatus;
import com.facebook.accountkit.p029ui.AccountKitActivity.ResponseType;
import com.facebook.login.widget.ToolTipPopup;
import java.util.Date;

/* renamed from: com.facebook.accountkit.ui.DemoEmailLoginFlowManager */
final class DemoEmailLoginFlowManager extends EmailLoginFlowManager {
    public static final Creator<DemoEmailLoginFlowManager> CREATOR = new Creator<DemoEmailLoginFlowManager>() {
        public DemoEmailLoginFlowManager createFromParcel(Parcel source) {
            return new DemoEmailLoginFlowManager(source);
        }

        public DemoEmailLoginFlowManager[] newArray(int size) {
            return new DemoEmailLoginFlowManager[size];
        }
    };
    private static final int MOCK_EMAIL_DELAY_MS = 6000;
    private static final int MOCK_NETWORK_DELAY_MS = 2000;
    private boolean isValid = true;
    private DemoEmailLoginModel loginModel;

    /* renamed from: com.facebook.accountkit.ui.DemoEmailLoginFlowManager$DemoEmailLoginModel */
    private static class DemoEmailLoginModel implements EmailLoginModel {
        public static final Creator<DemoEmailLoginModel> CREATOR = new Creator<DemoEmailLoginModel>() {
            public DemoEmailLoginModel createFromParcel(Parcel source) {
                return new DemoEmailLoginModel(source);
            }

            public DemoEmailLoginModel[] newArray(int size) {
                return new DemoEmailLoginModel[size];
            }
        };
        private final AccessToken accessToken;
        private final String authState;
        private final String confirmationCode;
        private final String email;

        public DemoEmailLoginModel(String email2, String authState2, String confirmationCode2, AccessToken accessToken2) {
            this.email = email2;
            this.authState = authState2;
            this.confirmationCode = confirmationCode2;
            this.accessToken = accessToken2;
        }

        public String getEmail() {
            return this.email;
        }

        public String getFinalAuthState() {
            return this.authState;
        }

        public String getCode() {
            return null;
        }

        public AccessToken getAccessToken() {
            return this.accessToken;
        }

        DemoEmailLoginModel(Parcel parcel) {
            this.accessToken = (AccessToken) parcel.readParcelable(AccessToken.class.getClassLoader());
            this.authState = parcel.readString();
            this.confirmationCode = parcel.readString();
            this.email = parcel.readString();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.accessToken, flags);
            dest.writeString(this.authState);
            dest.writeString(this.confirmationCode);
            dest.writeString(this.email);
        }
    }

    public DemoEmailLoginFlowManager(AccountKitConfiguration configuration) {
        super(configuration);
    }

    public void cancel() {
        this.isValid = false;
        broadcastLoginState(LoginStatus.CANCELLED, null);
    }

    public AccessToken getAccessToken() {
        if (!this.isValid) {
            return null;
        }
        return new AccessToken("TEST_ACCESS_TOKEN", "TEST_ACCOUNT_ID", AccountKit.getApplicationId(), 300000, new Date());
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void logInWithEmail(ResponseType responseType, String initialAuthState) {
        String confirmationCode;
        AccessToken accessToken;
        if (this.isValid) {
            final String email = getEmail();
            if (responseType == ResponseType.CODE) {
                confirmationCode = "DEMOCODE";
            } else {
                confirmationCode = null;
            }
            if (responseType == ResponseType.TOKEN) {
                accessToken = getAccessToken();
            } else {
                accessToken = null;
            }
            this.loginModel = new DemoEmailLoginModel(email, initialAuthState, confirmationCode, accessToken);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (email.endsWith("@example.com")) {
                        DemoEmailLoginFlowManager.this.broadcastLoginState(LoginStatus.PENDING, null);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                DemoEmailLoginFlowManager.this.broadcastLoginState(LoginStatus.SUCCESS, null);
                            }
                        }, ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME);
                        return;
                    }
                    DemoEmailLoginFlowManager.this.broadcastLoginState(LoginStatus.ERROR, new AccountKitError(Type.ARGUMENT_ERROR, new InternalAccountKitError(InternalAccountKitError.INVALID_CONFIRMATION_CODE, null, "[Demo] use *@example.com")));
                }
            }, 2000);
        }
    }

    /* access modifiers changed from: private */
    public void broadcastLoginState(LoginStatus status, AccountKitError error) {
        LocalBroadcastManager.getInstance(AccountKitController.getApplicationContext()).sendBroadcast(new Intent(EmailLoginTracker.ACTION_EMAIL_LOGIN_STATE_CHANGED).putExtra(Tracker.EXTRA_LOGIN_MODEL, this.loginModel).putExtra(Tracker.EXTRA_LOGIN_STATUS, status).putExtra(Tracker.EXTRA_LOGIN_ERROR, error));
    }

    protected DemoEmailLoginFlowManager(Parcel parcel) {
        super(parcel);
    }
}
