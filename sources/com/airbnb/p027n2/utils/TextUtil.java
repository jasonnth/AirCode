package com.airbnb.p027n2.utils;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.p000v4.content.ContextCompat;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.Spannable.Factory;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Patterns;
import android.widget.TextView;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.primitives.fonts.CustomFontSpan;
import com.airbnb.p027n2.primitives.fonts.Font;
import java.io.IOException;
import java.util.List;

/* renamed from: com.airbnb.n2.utils.TextUtil */
public final class TextUtil {
    public static Spannable makeBold(String string, String subString) {
        return applySpan(new StyleSpan(1), string, subString, true);
    }

    public static Spannable makeBold(String string) {
        return makeBold(string, string);
    }

    public static Spannable makeColored(Context context, int color, String string, String substring) {
        return applySpan(new ForegroundColorSpan(ContextCompat.getColor(context, color)), string, substring);
    }

    public static Spannable makeColored(Context context, int color, String string) {
        return makeColored(context, color, string, string);
    }

    public static Spannable changeFontFamily(Context context, Font font, String string, String subString) {
        return applySpan(new CustomFontSpan(context, font), string, subString);
    }

    public static Spannable changeFontFamily(Context context, Font font, CharSequence string) {
        return applySpan(new CustomFontSpan(context, font), string);
    }

    public static Spannable changeFontFamily(Context context, Font font, CharSequence string, int color) {
        return applySpan(new CustomFontSpan(context, font, color), string);
    }

    public static Spannable makeCircularBold(Context context, String string, String subString) {
        return changeFontFamily(context, Font.CircularBold, string, subString);
    }

    public static Spannable makeCircularBold(Context context, CharSequence string) {
        return changeFontFamily(context, Font.CircularBold, string);
    }

    public static Spannable makeCircularBold(Context context, CharSequence string, int color) {
        return changeFontFamily(context, Font.CircularBold, string, ContextCompat.getColor(context, color));
    }

    public static SpannableStringBuilder addSpan(Object span, SpannableStringBuilder stringBuilder) {
        stringBuilder.setSpan(span, 0, stringBuilder.length(), 33);
        return stringBuilder;
    }

    public static Spannable applySpan(Object span, CharSequence string) {
        SpannableStringBuilder str = new SpannableStringBuilder(string);
        str.setSpan(span, 0, string.length(), 17);
        return str;
    }

    public static Spannable applySpan(Object span, String string, String subString) {
        return applySpan(span, string, subString, true);
    }

    public static Spannable applySpan(Object span, String string, String subString, boolean caseSensitive) {
        SpannableStringBuilder str = new SpannableStringBuilder(string);
        int start = caseSensitive ? string.indexOf(subString) : string.toLowerCase().indexOf(subString.toLowerCase());
        if (start != -1) {
            str.setSpan(span, start, subString.length() + start, 17);
        }
        return str;
    }

    public static String titleCase(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int strLen = str.length();
        StringBuilder builder = new StringBuilder(strLen);
        boolean capitalizeNext = true;
        for (int i = 0; i < strLen; i++) {
            char ch = str.charAt(i);
            if (Character.isWhitespace(ch)) {
                builder.append(ch);
                capitalizeNext = true;
            } else if (capitalizeNext) {
                builder.append(Character.toTitleCase(ch));
                capitalizeNext = false;
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    public static String join(List<String> strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append(s);
        }
        return sb.toString();
    }

    public static String trimTextToFirstComma(String text) {
        return text.split(",")[0];
    }

    public static boolean isValidEmail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean fieldNotEmptyWithTrimming(SheetInputText field) {
        return !TextUtils.isEmpty(getFieldTextTrimmed(field.getText()));
    }

    public static boolean textNotEmptyWithTriming(Editable text) {
        return !TextUtils.isEmpty(getFieldTextTrimmed(text));
    }

    public static void showStrikeThrough(TextView view, boolean show) {
        if (show) {
            view.setPaintFlags(view.getPaintFlags() | 16);
        } else if ((view.getPaintFlags() & 16) == 16) {
            view.setPaintFlags(view.getPaintFlags() ^ 16);
        }
    }

    public static String getFieldTextTrimmed(CharSequence charSequence) {
        return charSequence.toString().trim();
    }

    public static Spanned fromHtmlSafe(String html) {
        if (html == null) {
            return null;
        }
        try {
            if (VERSION.SDK_INT >= 24) {
                return Html.fromHtml(html.replace("\r\n", "<br>").replace("\n", "<br>"), 0);
            }
            return Html.fromHtml(html.replace("\r\n", "<br>").replace("\n", "<br>"));
        } catch (RuntimeException ex) {
            if (ex.getCause() instanceof IOException) {
                return new Factory().newSpannable(html);
            }
            throw ex;
        }
    }
}
