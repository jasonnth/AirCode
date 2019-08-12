package com.airbnb.android.checkin.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import com.airbnb.android.checkin.C5618R;

public final class CheckInTextUtils {
    private CheckInTextUtils() {
    }

    public static Spannable getTranslateAttribution(Context context) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        int length = builder.length();
        builder.append("�").setSpan(new ImageSpan(context, C5618R.C5619drawable.n2_translated_by_google, 0), length, length + 1, 17);
        return builder;
    }
}
