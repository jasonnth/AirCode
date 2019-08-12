package com.facebook.accountkit;

import android.content.Intent;
import com.facebook.accountkit.internal.LoginStatus;

public abstract class PhoneLoginTracker extends Tracker {
    public static final String ACTION_PHONE_LOGIN_STATE_CHANGED = "com.facebook.accountkit.sdk.ACTION_PHONE_LOGIN_STATE_CHANGED";
    private String code;

    /* access modifiers changed from: protected */
    public abstract void onAccountVerified(PhoneLoginModel phoneLoginModel);

    /* access modifiers changed from: protected */
    public abstract void onCancel(PhoneLoginModel phoneLoginModel);

    /* access modifiers changed from: protected */
    public abstract void onError(AccountKitException accountKitException);

    /* access modifiers changed from: protected */
    public abstract void onStarted(PhoneLoginModel phoneLoginModel);

    /* access modifiers changed from: protected */
    public abstract void onSuccess(PhoneLoginModel phoneLoginModel);

    public String getCode() {
        return this.code;
    }

    public void setCode(String code2) {
        this.code = code2;
    }

    /* access modifiers changed from: protected */
    public String getActionStateChanged() {
        return ACTION_PHONE_LOGIN_STATE_CHANGED;
    }

    /* access modifiers changed from: protected */
    public void onReceive(Intent intent) {
        PhoneLoginModel loginModel = (PhoneLoginModel) intent.getParcelableExtra(Tracker.EXTRA_LOGIN_MODEL);
        LoginStatus status = (LoginStatus) intent.getSerializableExtra(Tracker.EXTRA_LOGIN_STATUS);
        if (loginModel != null && status != null) {
            switch (status) {
                case PENDING:
                    onStarted(loginModel);
                    return;
                case ACCOUNT_VERIFIED:
                    onAccountVerified(loginModel);
                    return;
                case SUCCESS:
                    onSuccess(loginModel);
                    return;
                case CANCELLED:
                    onCancel(loginModel);
                    return;
                case ERROR:
                    AccountKitError error = (AccountKitError) intent.getParcelableExtra(Tracker.EXTRA_LOGIN_ERROR);
                    if (error != null) {
                        onError(new AccountKitException(error));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
