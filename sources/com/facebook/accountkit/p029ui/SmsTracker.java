package com.facebook.accountkit.p029ui;

import android.content.Intent;
import android.telephony.SmsMessage;
import com.facebook.accountkit.Tracker;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.facebook.accountkit.ui.SmsTracker */
abstract class SmsTracker extends Tracker {
    private static final Pattern ACCOUNT_KIT_PATTERN = Pattern.compile("(\\d{6})(?=.*\\bAccount Kit\\b)(?=.*\\bFacebook\\b)");
    static final String SMS_INTENT = "android.provider.Telephony.SMS_RECEIVED";

    /* access modifiers changed from: protected */
    public abstract void confirmationCodeReceived(String str);

    public SmsTracker() {
        startTracking();
    }

    /* access modifiers changed from: protected */
    public String getActionStateChanged() {
        return SMS_INTENT;
    }

    /* access modifiers changed from: protected */
    public boolean isLocal() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onReceive(Intent intent) {
        String message = "";
        for (Object o : (Object[]) intent.getSerializableExtra("pdus")) {
            message = message + SmsMessage.createFromPdu((byte[]) o).getDisplayMessageBody();
        }
        String confirmationCode = getCodeFromString(message);
        if (confirmationCode != null) {
            confirmationCodeReceived(confirmationCode);
        }
    }

    static String getCodeFromString(String body) {
        Matcher matcher = ACCOUNT_KIT_PATTERN.matcher(body);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
