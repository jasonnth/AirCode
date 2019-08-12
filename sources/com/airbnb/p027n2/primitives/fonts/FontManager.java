package com.airbnb.p027n2.primitives.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.SpannableString;
import android.text.TextUtils;
import java.util.HashMap;

/* renamed from: com.airbnb.n2.primitives.fonts.FontManager */
public class FontManager {
    private static final HashMap<String, Typeface> sFontMap = new HashMap<>();

    public static Typeface getTypeface(Font font, Context context) {
        if (font == null) {
            return null;
        }
        Typeface tf = (Typeface) sFontMap.get(font.mFilename);
        if (tf != null) {
            return tf;
        }
        try {
            tf = Typeface.createFromAsset(context.getAssets(), font.mFilename);
            sFontMap.put(font.mFilename, tf);
            return tf;
        } catch (RuntimeException e) {
            return tf;
        }
    }

    public CharSequence wrapActionbarSpan(CharSequence s, Context context) {
        return isBrokenFontSpansLG412() ? s : wrapSpan(s, context);
    }

    public CharSequence wrapSpan(CharSequence s, Context context) {
        if (TextUtils.isEmpty(s) || FontHelper.forceSystemFont()) {
            return s;
        }
        SpannableString spannable = new SpannableString(s);
        spannable.setSpan(new CustomFontSpan(context, Font.CircularBook.mFilename), 0, s.length(), 33);
        return spannable;
    }

    public static boolean isBrokenFontSpansLG412() {
        return VERSION.SDK_INT == 16 && "LGE".equalsIgnoreCase(Build.MANUFACTURER);
    }
}
