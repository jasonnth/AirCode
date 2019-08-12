package com.airbnb.android.utils;

import android.os.Build.VERSION;
import android.text.Html;
import android.text.Spannable.Factory;
import android.text.Spanned;
import java.io.IOException;

public final class TextUtil {
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

    public static String removeNonDigits(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("[^\\d]", "");
    }

    public static String compressWhitespace(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("\\s+", " ");
    }

    public static boolean equals(String a, String b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null || a.length() != b.length() || !a.equals(b)) {
            return false;
        }
        return true;
    }
}
