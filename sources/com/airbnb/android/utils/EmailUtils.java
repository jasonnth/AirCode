package com.airbnb.android.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;
import com.airbnb.utils.R;

public class EmailUtils {
    public static void send(Context context, String address, String subject, String body) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        StringBuilder sb = new StringBuilder("mailto:").append(Uri.encode(address));
        StringBuilder append = sb.append("?subject=");
        if (TextUtils.isEmpty(subject)) {
            subject = "";
        }
        append.append(Uri.encode(subject));
        StringBuilder append2 = sb.append("&body=");
        if (TextUtils.isEmpty(body)) {
            body = "";
        }
        append2.append(Uri.encode(body));
        intent.setData(Uri.parse(sb.toString()));
        try {
            context.startActivity(Intent.createChooser(intent, context.getString(R.string.send_mail)));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, R.string.no_email_client, 0).show();
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
