package com.facebook.accountkit.p029ui;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/* renamed from: com.facebook.accountkit.ui.LoginFlowBroadcastReceiver */
abstract class LoginFlowBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION_UPDATE = (TAG + ".action_update");
    public static final String EXTRA_CONFIRMATION_CODE = (TAG + ".extra_confirmationCode");
    public static final String EXTRA_EMAIL = (TAG + ".extra_email");
    public static final String EXTRA_EVENT = (TAG + ".extra_event");
    public static final String EXTRA_PHONE_NUMBER = (TAG + ".extra_phoneNumber");
    public static final String EXTRA_RETURN_LOGIN_FLOW_STATE = (TAG + ".EXTRA_RETURN_LOGIN_FLOW_STATE");
    private static final String TAG = LoginFlowBroadcastReceiver.class.getSimpleName();

    /* renamed from: com.facebook.accountkit.ui.LoginFlowBroadcastReceiver$Event */
    public enum Event {
        SENT_CODE_COMPLETE,
        ACCOUNT_VERIFIED_COMPLETE,
        CONFIRM_SEAMLESS_LOGIN,
        EMAIL_LOGIN_COMPLETE,
        EMAIL_VERIFY_RETRY,
        ERROR_RESTART,
        PHONE_LOGIN_COMPLETE,
        PHONE_CONFIRMATION_CODE_COMPLETE,
        PHONE_CONFIRMATION_CODE_RETRY,
        PHONE_RESEND,
        PHONE_RESEND_FACEBOOK_NOTIFICATION,
        PHONE_RESEND_VOICE_CALL_NOTIFICATION
    }

    LoginFlowBroadcastReceiver() {
    }

    public static IntentFilter getIntentFilter() {
        return new IntentFilter(ACTION_UPDATE);
    }
}
