package com.airbnb.android.utils;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Telephony.Sms;
import android.widget.Toast;
import com.airbnb.utils.R;

public class CallHelper {
    public static void dial(Context context, String phone) {
        try {
            context.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phone)));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, R.string.no_phone, 0).show();
        }
    }

    public static void text(Context context, String phone) {
        try {
            Intent textIntent = new Intent("android.intent.action.VIEW", Uri.parse("sms:" + phone));
            if (AndroidVersion.isAtLeastKitKat()) {
                textIntent.setPackage(getDefaultSmsPackage(context));
            }
            context.startActivity(Intent.createChooser(textIntent, context.getString(R.string.send_sms)));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, R.string.no_phone, 0).show();
        }
    }

    @TargetApi(19)
    private static String getDefaultSmsPackage(Context context) {
        return Sms.getDefaultSmsPackage(context);
    }
}
