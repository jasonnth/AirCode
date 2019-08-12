package com.facebook.accountkit.internal;

import android.content.Context;
import android.os.Bundle;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

final class FacebookAppEventLogger {
    private static final String FB_EVENT_NAME_LOGIN_ATTEMPT = "fb_ak_login_attempt";
    private static final String FB_EVENT_NAME_LOGIN_COMPLETE = "fb_ak_login_complete";
    private static final String FB_EVENT_NAME_LOGIN_DIALOG_IMPRESSION = "fb_ak_login_dialog_impression";
    private static final String FB_EVENT_NAME_LOGIN_START = "fb_ak_login_start";
    private static final String TAG = FacebookAppEventLogger.class.getCanonicalName();
    private static final Map<String, String> eventNameMap = new HashMap<String, String>() {
        {
            put(InternalLogger.EVENT_NAME_EMAIL_VIEW, FacebookAppEventLogger.FB_EVENT_NAME_LOGIN_DIALOG_IMPRESSION);
            put(InternalLogger.EVENT_NAME_PHONE_NUMBER_VIEW, FacebookAppEventLogger.FB_EVENT_NAME_LOGIN_DIALOG_IMPRESSION);
            put(InternalLogger.EVENT_NAME_LOGIN_START, FacebookAppEventLogger.FB_EVENT_NAME_LOGIN_START);
            put(InternalLogger.EVENT_NAME_LOGIN_VERIFY, FacebookAppEventLogger.FB_EVENT_NAME_LOGIN_ATTEMPT);
            put(InternalLogger.EVENT_NAME_CONFIRM_SEAMLESS_PENDING, FacebookAppEventLogger.FB_EVENT_NAME_LOGIN_ATTEMPT);
            put(InternalLogger.EVENT_NAME_LOGIN_COMPLETE, FacebookAppEventLogger.FB_EVENT_NAME_LOGIN_COMPLETE);
        }
    };
    private Object fbAppEventLogger = null;

    FacebookAppEventLogger(Context applicationContext) {
        if (isFacebookSDKInitialized()) {
            try {
                try {
                    try {
                        this.fbAppEventLogger = Class.forName("com.facebook.appevents.AppEventsLogger").getMethod("newLogger", new Class[]{Context.class}).invoke(null, new Object[]{applicationContext});
                    } catch (Exception e) {
                        Utility.logd(TAG, e);
                    }
                } catch (NoSuchMethodException e2) {
                    Utility.logd(TAG, e2);
                }
            } catch (ClassNotFoundException e3) {
            }
        }
    }

    public void logImpression(String eventName, Bundle parameters, boolean isPresented) {
        if (isPresented) {
            logFacebookAppEvents(eventName, null, parameters);
        }
    }

    /* access modifiers changed from: 0000 */
    public void logFacebookAppEvents(String eventName, Double valueToSum, Bundle parameters) {
        String fbEventName = (String) eventNameMap.get(eventName);
        if (fbEventName != null && this.fbAppEventLogger != null) {
            try {
                Method logSDKEventMethod = this.fbAppEventLogger.getClass().getMethod("logSdkEvent", new Class[]{String.class, Double.class, Bundle.class});
                try {
                    logSDKEventMethod.invoke(this.fbAppEventLogger, new Object[]{fbEventName, valueToSum, parameters});
                } catch (Exception e) {
                    Utility.logd(TAG, e);
                }
            } catch (NoSuchMethodException e2) {
                Utility.logd(TAG, e2);
            }
        }
    }

    static boolean isFacebookSDKInitialized() {
        boolean z = false;
        try {
            try {
                try {
                    return ((Boolean) Class.forName("com.facebook.FacebookSdk").getMethod("isInitialized", new Class[0]).invoke(null, new Object[0])).booleanValue();
                } catch (Exception e) {
                    Utility.logd(TAG, e);
                    return z;
                }
            } catch (NoSuchMethodException e2) {
                Utility.logd(TAG, e2);
                return z;
            }
        } catch (ClassNotFoundException e3) {
            return z;
        }
    }
}
