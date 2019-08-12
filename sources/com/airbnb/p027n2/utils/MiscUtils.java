package com.airbnb.p027n2.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.utils.MiscUtils */
public class MiscUtils {
    public static void copyToClipboard(Context context, String text) {
        Toast.makeText(context, R.string.n2_copied_to_clipboard, 0).show();
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("airbnb-text", text));
    }
}
