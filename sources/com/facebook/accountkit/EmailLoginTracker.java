package com.facebook.accountkit;

import android.content.Intent;
import com.facebook.accountkit.internal.LoginStatus;

public abstract class EmailLoginTracker extends Tracker {
    public static final String ACTION_EMAIL_LOGIN_STATE_CHANGED = "com.facebook.accountkit.sdk.ACTION_EMAIL_LOGIN_STATE_CHANGED";

    /* access modifiers changed from: protected */
    public abstract void onCancel(EmailLoginModel emailLoginModel);

    /* access modifiers changed from: protected */
    public abstract void onError(AccountKitException accountKitException);

    /* access modifiers changed from: protected */
    public abstract void onStarted(EmailLoginModel emailLoginModel);

    /* access modifiers changed from: protected */
    public abstract void onSuccess(EmailLoginModel emailLoginModel);

    /* access modifiers changed from: protected */
    public String getActionStateChanged() {
        return ACTION_EMAIL_LOGIN_STATE_CHANGED;
    }

    /* access modifiers changed from: protected */
    public void onReceive(Intent intent) {
        EmailLoginModel loginModel = (EmailLoginModel) intent.getParcelableExtra(Tracker.EXTRA_LOGIN_MODEL);
        LoginStatus status = (LoginStatus) intent.getSerializableExtra(Tracker.EXTRA_LOGIN_STATUS);
        if (loginModel != null && status != null) {
            switch (status) {
                case PENDING:
                    onStarted(loginModel);
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
