package com.threatmetrix.TrustDefender;

import android.util.Log;
import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.threatmetrix.TrustDefender.w */
final class C4834w {

    /* renamed from: a */
    private static final boolean f4663a;

    /* renamed from: b */
    private static final Pattern f4664b = Pattern.compile("\\{\\}");

    /* renamed from: c */
    private static boolean f4665c = false;

    static {
        boolean z;
        if (TrustDefenderVersion.numeric.intValue() == -1) {
            z = true;
        } else {
            z = false;
        }
        f4663a = z;
    }

    /* renamed from: a */
    public static void m2894a(String tag, String message) {
        Log.e(tag, message);
    }

    /* renamed from: a */
    public static void m2895a(String tag, String message, Throwable throwable) {
        Log.e(tag, message, throwable);
    }

    /* renamed from: b */
    public static void m2899b(String tag, String message) {
        Log.w(tag, message);
    }

    /* renamed from: a */
    public static void m2896a(String tag, String message, String... values) {
        Log.w(tag, m2893a(message, (Object[]) values));
    }

    /* renamed from: c */
    public static void m2901c(String tag, String message) {
        if (f4663a || f4665c) {
            Log.i(tag, message);
        }
    }

    /* renamed from: b */
    public static void m2900b(String tag, String message, String... values) {
        if (f4663a || f4665c) {
            Log.i(tag, m2893a(message, (Object[]) values));
        }
    }

    /* renamed from: c */
    public static void m2902c(String tag, String message, Throwable throwable) {
        if (f4663a || f4665c) {
            Log.i(tag, message, throwable);
        }
    }

    /* renamed from: a */
    static String m2892a(Class c) {
        String tag = "c.t.tdm." + c.getSimpleName();
        if (tag.length() > 23) {
            return tag.substring(0, 23);
        }
        return tag;
    }

    /* renamed from: a */
    static boolean m2897a() {
        return f4663a;
    }

    /* renamed from: a */
    static String m2893a(String message, Object... values) {
        if (message == null || values == null) {
            return "";
        }
        Matcher matcher = f4664b.matcher(message);
        StringBuffer result = new StringBuffer();
        Object[] arr$ = values;
        int len$ = values.length;
        int i$ = 0;
        while (i$ < len$) {
            Object value = arr$[i$];
            if (matcher.find()) {
                if (value != null) {
                    matcher.appendReplacement(result, String.valueOf(value));
                } else {
                    matcher.appendReplacement(result, "null");
                }
                i$++;
            } else {
                throw new InvalidParameterException("Incorrect number of arguments for this format string");
            }
        }
        if (!matcher.find()) {
            return matcher.appendTail(result).toString();
        }
        throw new InvalidParameterException("Incorrect number of arguments for this format string");
    }

    /* renamed from: b */
    static int m2898b() {
        if (Log.isLoggable("TrustDefender", 2)) {
            f4665c = true;
            NativeGatherer.m2512a().mo45863b(1);
            return 1;
        }
        f4665c = false;
        NativeGatherer.m2512a().mo45863b(0);
        return 0;
    }
}
