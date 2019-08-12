package com.mparticle.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.mparticle.MParticle;
import com.mparticle.MParticle.LogLevel;
import com.mparticle.internal.MPUtility.AndroidAdIdInfo;
import com.mparticle.internal.PushRegistrationHelper.PushRegistration;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;

/* renamed from: com.mparticle.internal.b */
class C4604b {

    /* renamed from: a */
    private JSONObject f3747a;

    /* renamed from: b */
    private JSONObject f3748b;

    /* renamed from: c */
    private boolean f3749c = true;

    C4604b() {
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public JSONObject mo44859a(Context context) {
        int i;
        String num;
        JSONObject jSONObject = new JSONObject();
        SharedPreferences sharedPreferences = context.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0);
        Editor edit = sharedPreferences.edit();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            jSONObject.put("apn", packageName);
            String str = "unknown";
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
                num = Integer.toString(packageInfo.versionCode);
                jSONObject.put("av", packageInfo.versionName);
            } catch (NameNotFoundException e) {
            }
            jSONObject.put("abn", num);
            String installerPackageName = packageManager.getInstallerPackageName(packageName);
            if (installerPackageName != null) {
                jSONObject.put("ain", installerPackageName);
            }
            try {
                jSONObject.put("an", packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, 0)));
            } catch (NameNotFoundException e2) {
            }
            jSONObject.put("bid", MPUtility.getBuildUUID(num));
            jSONObject.put("dbg", MPUtility.isAppDebuggable(context));
            jSONObject.put("pir", sharedPreferences.getBoolean("mp::pirated", false));
            jSONObject.put("ict", sharedPreferences.getLong("mp::ict", currentTimeMillis));
            if (!sharedPreferences.contains("mp::ict")) {
                edit.putLong("mp::ict", currentTimeMillis);
            }
            int i2 = sharedPreferences.getInt("mp::totalruns", 0) + 1;
            edit.putInt("mp::totalruns", i2);
            jSONObject.put("lc", i2);
            jSONObject.put("lud", sharedPreferences.getLong("mp::lastusedate", 0));
            edit.putLong("mp::lastusedate", currentTimeMillis);
            try {
                PackageInfo packageInfo2 = packageManager.getPackageInfo(packageName, 0);
                int i3 = sharedPreferences.getInt("mp::version::counter", -1);
                int i4 = sharedPreferences.getInt("mp::launch_since_upgrade", 0);
                long j = sharedPreferences.getLong("mp::upgrade_date", currentTimeMillis);
                if (i3 < 0 || i3 != packageInfo2.versionCode) {
                    edit.putInt("mp::version::counter", packageInfo2.versionCode);
                    edit.putLong("mp::upgrade_date", currentTimeMillis);
                    j = currentTimeMillis;
                    i = 0;
                } else {
                    i = i4;
                }
                int i5 = i + 1;
                edit.putInt("mp::launch_since_upgrade", i5);
                jSONObject.put("lcu", i5);
                jSONObject.put("ud", j);
            } catch (NameNotFoundException e3) {
            }
            String str2 = "env";
            MParticle.getInstance().getConfigManager();
            jSONObject.put(str2, ConfigManager.getEnvironment().getValue());
            jSONObject.put("ir", sharedPreferences.getString("mp::install_referrer", null));
            jSONObject.put("fi", sharedPreferences.getBoolean("mp::firstrun::install", true));
            edit.putBoolean("mp::firstrun::install", false);
        } catch (Exception e4) {
        } finally {
            edit.apply();
        }
        return jSONObject;
    }

    /* renamed from: a */
    static void m2225a(JSONObject jSONObject, Context context) throws JSONException {
        if (!MParticle.isAndroidIdDisabled()) {
            String androidID = MPUtility.getAndroidID(context);
            jSONObject.put("duid", androidID);
            jSONObject.put("anid", androidID);
            jSONObject.put("ouid", MPUtility.getOpenUDID(context));
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public JSONObject mo44861b(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bid", Build.ID);
            jSONObject.put("b", Build.BRAND);
            jSONObject.put("p", Build.PRODUCT);
            jSONObject.put("dn", Build.DEVICE);
            jSONObject.put("dma", Build.MANUFACTURER);
            jSONObject.put("dp", InternalLogger.EVENT_PARAM_SDK_ANDROID);
            jSONObject.put("dosv", VERSION.SDK);
            jSONObject.put("dosvi", VERSION.SDK_INT);
            jSONObject.put("dmdl", Build.MODEL);
            jSONObject.put("vr", VERSION.RELEASE);
            m2225a(jSONObject, context);
            jSONObject.put("dbe", MPUtility.isBluetoothEnabled(context));
            jSONObject.put("dbv", MPUtility.getBluetoothVersion(context));
            jSONObject.put("dsnfc", MPUtility.hasNfc(context));
            jSONObject.put("dst", MPUtility.hasTelephony(context));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("cydia", MPUtility.isPhoneRooted());
            jSONObject.put("jb", jSONObject2);
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            jSONObject.put("dsh", displayMetrics.heightPixels);
            jSONObject.put("dsw", displayMetrics.widthPixels);
            jSONObject.put("dpi", displayMetrics.densityDpi);
            Locale locale = Locale.getDefault();
            jSONObject.put("dc", locale.getDisplayCountry());
            jSONObject.put("dlc", locale.getCountry());
            jSONObject.put("dll", locale.getLanguage());
            jSONObject.put("tzn", MPUtility.getTimeZone());
            jSONObject.put("tz", TimeZone.getDefault().getRawOffset() / JPushConstants.ONE_HOUR);
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager.getPhoneType() != 0) {
                String networkOperatorName = telephonyManager.getNetworkOperatorName();
                if (networkOperatorName.length() != 0) {
                    jSONObject.put("nca", networkOperatorName);
                }
                String networkCountryIso = telephonyManager.getNetworkCountryIso();
                if (networkCountryIso.length() != 0) {
                    jSONObject.put("nc", networkCountryIso);
                }
                String networkOperator = telephonyManager.getNetworkOperator();
                if (6 == networkOperator.length()) {
                    jSONObject.put("mcc", networkOperator.substring(0, 3));
                    jSONObject.put("mnc", networkOperator.substring(3));
                }
            }
            jSONObject.put("it", MPUtility.isTablet(context));
            jSONObject.put("idst", MPUtility.isInDaylightSavings());
        } catch (Exception e) {
        }
        return jSONObject;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo44860a(Context context, JSONObject jSONObject) {
        jSONObject.remove("lat");
        jSONObject.remove("gaid");
        AndroidAdIdInfo googleAdIdInfo = MPUtility.getGoogleAdIdInfo(context);
        String str = "Failed to collect Google Play Advertising ID, be sure to add Google Play services or com.google.android.gms:play-services-ads to your app's dependencies.";
        if (googleAdIdInfo != null) {
            try {
                jSONObject.put("lat", googleAdIdInfo.isLimitAdTrackingEnabled);
                if (!googleAdIdInfo.isLimitAdTrackingEnabled || !MParticle.getInstance().getConfigManager().getRestrictAAIDBasedOnLAT()) {
                    jSONObject.put("gaid", googleAdIdInfo.f3696id);
                    str = "Successfully collected Google Play Advertising ID.";
                } else {
                    str = "Google Play Advertising ID available but ad tracking is disabled on this device.";
                }
            } catch (JSONException e) {
                ConfigManager.log(LogLevel.DEBUG, "Failed while building device-info object: ", e.toString());
            }
        }
        if (this.f3749c) {
            ConfigManager.log(LogLevel.DEBUG, str);
            this.f3749c = false;
        }
        try {
            PushRegistration latestPushRegistration = PushRegistrationHelper.getLatestPushRegistration(context);
            if (latestPushRegistration != null && !MPUtility.isEmpty(latestPushRegistration.instanceId)) {
                jSONObject.put("to", latestPushRegistration.instanceId);
                jSONObject.put("tot", "google");
            }
            jSONObject.put("se", MParticle.getInstance().getConfigManager().isPushSoundEnabled());
            jSONObject.put("ve", MParticle.getInstance().getConfigManager().isPushVibrationEnabled());
        } catch (JSONException e2) {
            ConfigManager.log(LogLevel.DEBUG, "Failed while building device-info object: ", e2.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public JSONObject mo44862c(Context context) {
        if (this.f3747a == null) {
            this.f3747a = mo44861b(context);
        }
        mo44860a(context, this.f3747a);
        return this.f3747a;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public JSONObject mo44863d(Context context) {
        if (this.f3748b == null) {
            this.f3748b = mo44859a(context);
        }
        return this.f3748b;
    }
}
