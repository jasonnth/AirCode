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
import com.facebook.accountkit.PhoneLoginModel;
import com.facebook.accountkit.PhoneLoginTracker;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.Tracker;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.internal.InternalAccountKitError;
import com.facebook.accountkit.internal.LoginStatus;
import com.facebook.accountkit.p029ui.AccountKitActivity.ResponseType;
import java.util.Date;

/* renamed from: com.facebook.accountkit.ui.DemoPhoneLoginFlowManager */
final class DemoPhoneLoginFlowManager extends PhoneLoginFlowManager {
    public static final Creator<DemoPhoneLoginFlowManager> CREATOR = new Creator<DemoPhoneLoginFlowManager>() {
        public DemoPhoneLoginFlowManager createFromParcel(Parcel source) {
            return new DemoPhoneLoginFlowManager(source);
        }

        public DemoPhoneLoginFlowManager[] newArray(int size) {
            return new DemoPhoneLoginFlowManager[size];
        }
    };
    private static final String MOCK_CONFIRMATION_CODE = "123456";
    private static final int MOCK_NETWORK_DELAY_MS = 2000;
    /* access modifiers changed from: private */
    public AccountKitActivity activity;
    private boolean isValid;
    private DemoPhoneLoginModel loginModel;
    /* access modifiers changed from: private */
    public ActivityPhoneHandler phoneListeners;

    /* renamed from: com.facebook.accountkit.ui.DemoPhoneLoginFlowManager$DemoPhoneLoginModel */
    private static class DemoPhoneLoginModel implements PhoneLoginModel {
        public static final Creator<DemoPhoneLoginModel> CREATOR = new Creator<DemoPhoneLoginModel>() {
            public DemoPhoneLoginModel createFromParcel(Parcel source) {
                return new DemoPhoneLoginModel(source);
            }

            public DemoPhoneLoginModel[] newArray(int size) {
                return new DemoPhoneLoginModel[size];
            }
        };
        private final AccessToken accessToken;
        private final String authState;
        private final String confirmationCode;
        private final PhoneNumber phoneNumber;

        DemoPhoneLoginModel(PhoneNumber phoneNumber2, String authState2, String confirmationCode2, AccessToken accessToken2) {
            this.phoneNumber = phoneNumber2;
            this.authState = authState2;
            this.confirmationCode = confirmationCode2;
            this.accessToken = accessToken2;
        }

        public String getConfirmationCode() {
            return this.confirmationCode;
        }

        public PhoneNumber getPhoneNumber() {
            return this.phoneNumber;
        }

        public String getPrivacyPolicy() {
            return null;
        }

        public String getTermsOfService() {
            return null;
        }

        public String getFinalAuthState() {
            return this.authState;
        }

        public String getCode() {
            return this.confirmationCode;
        }

        public AccessToken getAccessToken() {
            return this.accessToken;
        }

        public NotificationChannel getNotificationChannel() {
            return NotificationChannel.SMS;
        }

        public long getResendTime() {
            return System.currentTimeMillis();
        }

        DemoPhoneLoginModel(Parcel parcel) {
            this.accessToken = (AccessToken) parcel.readParcelable(AccessToken.class.getClassLoader());
            this.authState = parcel.readString();
            this.confirmationCode = parcel.readString();
            this.phoneNumber = (PhoneNumber) parcel.readParcelable(PhoneNumber.class.getClassLoader());
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.accessToken, flags);
            dest.writeString(this.authState);
            dest.writeString(this.confirmationCode);
            dest.writeParcelable(this.phoneNumber, flags);
        }
    }

    public DemoPhoneLoginFlowManager(AccountKitConfiguration configuration, AccountKitActivity activity2, ActivityPhoneHandler phoneListeners2) {
        super(configuration);
        this.isValid = true;
        this.activity = activity2;
        this.phoneListeners = phoneListeners2;
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

    public void logInWithPhoneNumber(final PhoneNumber phoneNumber, NotificationChannel notificationChannel, ResponseType responseType, String initialAuthState) {
        String confirmationCode;
        AccessToken accessToken;
        if (this.isValid) {
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
            this.loginModel = new DemoPhoneLoginModel(phoneNumber, initialAuthState, confirmationCode, accessToken);
            setLastUsedPhoneNumber(phoneNumber);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (phoneNumber.getPhoneNumber().length() == 10) {
                        DemoPhoneLoginFlowManager.this.broadcastLoginState(LoginStatus.PENDING, null);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                DemoPhoneLoginFlowManager.this.phoneListeners.startSmsTrackerIfPossible(DemoPhoneLoginFlowManager.this.activity);
                                DemoPhoneLoginFlowManager.this.phoneListeners.getSmsTracker().confirmationCodeReceived(DemoPhoneLoginFlowManager.MOCK_CONFIRMATION_CODE);
                            }
                        }, 2000);
                        return;
                    }
                    DemoPhoneLoginFlowManager.this.broadcastLoginState(LoginStatus.ERROR, new AccountKitError(Type.ARGUMENT_ERROR, new InternalAccountKitError(InternalAccountKitError.INVALID_CREDENTIALS_OR_LOGIN_REQUEST, null, "[Demo] use a 10 digit number")));
                }
            }, 2000);
        }
    }

    public void setConfirmationCode(final String confirmationCode) {
        if (this.isValid) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (confirmationCode.equals(DemoPhoneLoginFlowManager.MOCK_CONFIRMATION_CODE)) {
                        DemoPhoneLoginFlowManager.this.broadcastLoginState(LoginStatus.SUCCESS, null);
                        return;
                    }
                    DemoPhoneLoginFlowManager.this.broadcastLoginState(LoginStatus.ERROR, new AccountKitError(Type.ARGUMENT_ERROR, new InternalAccountKitError(InternalAccountKitError.INVALID_CREDENTIALS_OR_LOGIN_REQUEST, null, "[Demo] use confirmation code 123456")));
                }
            }, 2000);
        }
    }

    /* access modifiers changed from: private */
    public void broadcastLoginState(LoginStatus status, AccountKitError error) {
        LocalBroadcastManager.getInstance(AccountKitController.getApplicationContext()).sendBroadcast(new Intent(PhoneLoginTracker.ACTION_PHONE_LOGIN_STATE_CHANGED).putExtra(Tracker.EXTRA_LOGIN_MODEL, this.loginModel).putExtra(Tracker.EXTRA_LOGIN_STATUS, status).putExtra(Tracker.EXTRA_LOGIN_ERROR, error));
    }

    private DemoPhoneLoginFlowManager(Parcel parcel) {
        super(parcel);
        this.isValid = true;
    }
}
