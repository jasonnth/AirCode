package com.threatmetrix.TrustDefender;

import android.content.Context;
import android.telephony.TelephonyManager;
import java.util.Locale;
import java.util.UUID;

/* renamed from: com.threatmetrix.TrustDefender.an */
class C4778an {

    /* renamed from: a */
    private static final String f4245a = C4834w.m2892a(C4778an.class);

    C4778an() {
    }

    /* renamed from: a */
    static String m2664a(Context context) {
        boolean z = false;
        C4811l preferences = new C4811l(context, "ThreatMetrixMobileSDK", 0);
        if (!(C4804e.f4393b == null || C4804e.f4392a == null)) {
            z = true;
        }
        if (!z) {
            C4834w.m2901c(f4245a, "SharedPreferences wasn't found, generating GUID");
            return UUID.randomUUID().toString().replace("-", "").toLowerCase(Locale.US);
        }
        String genID = null;
        try {
            genID = preferences.mo46051a("ThreatMetrixMobileSDK", (String) null);
        } catch (ClassCastException e) {
            String str = f4245a;
        }
        if (genID != null) {
            return genID;
        }
        C4834w.m2901c(f4245a, "Found nothing in shared prefs, generating GUID");
        String genID2 = UUID.randomUUID().toString().replace("-", "").toLowerCase(Locale.US);
        preferences.mo46055b("ThreatMetrixMobileSDK", genID2);
        preferences.mo46052a();
        return genID2;
    }

    /* renamed from: b */
    static String m2669b(Context context) {
        String id = C4810k.m2800a(context.getContentResolver(), "ANDROID_ID");
        if (id != null && !id.equals("9774d56d682e549c") && id.length() >= 15) {
            return id;
        }
        C4834w.m2901c(f4245a, "ANDROID_ID contains nothing useful");
        return null;
    }

    /* renamed from: c */
    static String m2671c(Context context) {
        String imei = null;
        if (new C4808i(context).mo46048a("android.permission.READ_PHONE_STATE", context.getPackageName())) {
            try {
                Object telephonyService = context.getSystemService("phone");
                if (telephonyService == null || !(telephonyService instanceof TelephonyManager)) {
                    return null;
                }
                imei = ((TelephonyManager) telephonyService).getDeviceId();
                if (imei == null || imei.equals("000000000000000")) {
                    imei = "";
                }
                if (imei.isEmpty()) {
                    C4834w.m2901c(f4245a, "Failed to get useful imei");
                }
                String str = f4245a;
                new StringBuilder("imei: ").append(imei);
            } catch (SecurityException e) {
                String str2 = f4245a;
                imei = "";
            } catch (Exception e2) {
                C4834w.m2901c(f4245a, e2.getMessage());
                imei = "";
            }
        }
        return imei;
    }

    /* renamed from: a */
    static boolean m2668a() {
        String serial = C4799b.f4354h;
        if (serial == null) {
            return false;
        }
        if (serial.equals("unknown") || serial.equals("1234567890ABCDEF")) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private static String m2665a(String in) throws InterruptedException {
        String out;
        String out2 = in;
        if (in == null || in.length() == 0) {
            return null;
        }
        if (in.length() == 32) {
            return out2;
        }
        if (in.length() < 32) {
            String md5 = C4770ai.m2628b(in);
            if (md5 == null) {
                return null;
            }
            int len = 32 - in.length();
            if (len > md5.length()) {
                len = md5.length();
            }
            out = in + md5.substring(0, len);
        } else {
            out = C4770ai.m2628b(in);
        }
        return out;
    }

    /* renamed from: a */
    static String m2667a(String genID, boolean useAltIDScheme) throws InterruptedException {
        String HTML5Cookie = genID;
        if (useAltIDScheme) {
            HTML5Cookie = C4770ai.m2628b(genID);
        }
        C4834w.m2901c(f4245a, "using generated ID for LSC:" + HTML5Cookie);
        return m2665a(HTML5Cookie);
    }

    /* renamed from: b */
    static String m2670b(String androidID, boolean useAltIDScheme) throws InterruptedException {
        String cookie = androidID;
        if (androidID != null) {
            if (useAltIDScheme) {
                cookie = C4770ai.m2628b(androidID);
            }
            C4834w.m2901c(f4245a, "using ANDROID_ID for TPC:" + cookie);
        }
        return m2665a(cookie);
    }

    /* renamed from: a */
    static String m2666a(String androidID, String genID, String imei, boolean useAltIDScheme) throws InterruptedException {
        String str = C4799b.f4354h == null ? "" : C4799b.f4354h;
        flashCookie = C4770ai.m2633f(imei) ? str + imei : C4770ai.m2633f(androidID) ? str + androidID : C4770ai.m2633f(genID) ? str + genID : str;
        if (useAltIDScheme) {
            flashCookie = C4770ai.m2628b(flashCookie);
        }
        return m2665a(C4770ai.m2628b(flashCookie));
    }
}
