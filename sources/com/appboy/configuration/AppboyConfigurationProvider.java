package com.appboy.configuration;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PackageUtils;
import java.util.Locale;
import p004bo.app.C0396by;

public class AppboyConfigurationProvider extends CachedConfigurationProvider {

    /* renamed from: a */
    private static final String f2756a = AppboyLogger.getAppboyLogTag(AppboyConfigurationProvider.class);

    /* renamed from: b */
    private final Context f2757b;

    /* renamed from: com.appboy.configuration.AppboyConfigurationProvider$a */
    enum C3141a {
        SMALL,
        LARGE
    }

    public AppboyConfigurationProvider(Context context) {
        super(context);
        this.f2757b = context;
    }

    public String getBaseUrlForRequests() {
        if ("STAGING".equals(m1718a().toUpperCase(Locale.US))) {
            return "https://sondheim.appboy.com/api/v2/";
        }
        return "https://dev.appboy.com/api/v2/";
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getAppboyApiKeyStringFromLocaleMapping(java.util.Locale r11) {
        /*
            r10 = this;
            r9 = 1
            r3 = 0
            r1 = 0
            if (r11 != 0) goto L_0x000f
            java.lang.String r0 = f2756a
            java.lang.String r2 = "Passed in a null locale to match."
            com.appboy.support.AppboyLogger.m1733d(r0, r2)
            r0 = r1
        L_0x000e:
            return r0
        L_0x000f:
            bo.app.i r0 = r10.mRuntimeAppConfigurationProvider
            java.lang.String r2 = "com_appboy_locale_api_key_map"
            boolean r0 = r0.mo7301a(r2)
            if (r0 == 0) goto L_0x0056
            bo.app.i r0 = r10.mRuntimeAppConfigurationProvider
            java.lang.String r2 = "com_appboy_locale_api_key_map"
            java.lang.String r0 = r0.mo7300a(r2, r1)
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ JSONException -> 0x003e }
            r4.<init>(r0)     // Catch:{ JSONException -> 0x003e }
            int r0 = r4.length()     // Catch:{ JSONException -> 0x003e }
            java.lang.String[] r0 = new java.lang.String[r0]     // Catch:{ JSONException -> 0x003e }
            r2 = r3
        L_0x002f:
            int r5 = r4.length()     // Catch:{ JSONException -> 0x00ad }
            if (r2 >= r5) goto L_0x0049
            java.lang.String r5 = r4.getString(r2)     // Catch:{ JSONException -> 0x00ad }
            r0[r2] = r5     // Catch:{ JSONException -> 0x00ad }
            int r2 = r2 + 1
            goto L_0x002f
        L_0x003e:
            r0 = move-exception
            r2 = r0
            r0 = r1
        L_0x0041:
            java.lang.String r4 = f2756a
            java.lang.String r5 = "Caught exception creating locale to api key mapping from override cache"
            com.appboy.support.AppboyLogger.m1736e(r4, r5, r2)
        L_0x0049:
            r2 = r0
        L_0x004a:
            if (r2 != 0) goto L_0x005f
            java.lang.String r0 = f2756a
            java.lang.String r2 = "Locale to api key mappings not present in XML."
            com.appboy.support.AppboyLogger.m1733d(r0, r2)
            r0 = r1
            goto L_0x000e
        L_0x0056:
            java.lang.String r0 = "com_appboy_locale_api_key_map"
            java.lang.String[] r0 = r10.readStringArrayResourceValue(r0, r1)
            r2 = r0
            goto L_0x004a
        L_0x005f:
            int r4 = r2.length
            r0 = r3
        L_0x0061:
            if (r0 >= r4) goto L_0x00aa
            r5 = r2[r0]
            java.lang.String r6 = ","
            int r6 = com.appboy.support.StringUtils.countOccurrences(r5, r6)
            if (r6 == r9) goto L_0x0071
        L_0x006e:
            int r0 = r0 + 1
            goto L_0x0061
        L_0x0071:
            java.lang.String r6 = ","
            java.lang.String[] r5 = r5.split(r6)
            int r6 = r5.length
            r7 = 2
            if (r6 != r7) goto L_0x006e
            r6 = r5[r3]
            java.lang.String r6 = r6.trim()
            java.lang.String r6 = r6.toLowerCase()
            java.lang.String r7 = r11.toString()
            java.lang.String r7 = r7.toLowerCase()
            boolean r7 = r6.equals(r7)
            java.lang.String r8 = r11.getCountry()
            java.lang.String r8 = r8.toLowerCase()
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x00a2
            if (r7 == 0) goto L_0x006e
        L_0x00a2:
            r0 = r5[r9]
            java.lang.String r0 = r0.trim()
            goto L_0x000e
        L_0x00aa:
            r0 = r1
            goto L_0x000e
        L_0x00ad:
            r2 = move-exception
            goto L_0x0041
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.configuration.AppboyConfigurationProvider.getAppboyApiKeyStringFromLocaleMapping(java.util.Locale):java.lang.String");
    }

    public C0396by getAppboyApiKey() {
        C0396by byVar = (C0396by) this.mConfigurationCache.get("com_appboy_api_key");
        if (byVar == null) {
            String a = this.mRuntimeAppConfigurationProvider.mo7300a("com_appboy_api_key", (String) null);
            if (a != null) {
                AppboyLogger.m1737i(f2756a, "Found an override api key. Using it to configure Appboy.");
            } else {
                a = getAppboyApiKeyStringFromLocaleMapping(Locale.getDefault());
                if (a != null) {
                    AppboyLogger.m1737i(f2756a, "Found a locale that matches the current locale in appboy.xml. Using the associated api key");
                } else {
                    a = readStringResourceValue("com_appboy_api_key", null);
                }
            }
            if (a != null) {
                byVar = new C0396by(a);
                this.mConfigurationCache.put("com_appboy_api_key", byVar);
            }
        }
        if (byVar != null) {
            return byVar;
        }
        AppboyLogger.m1735e(f2756a, "****************************************************");
        AppboyLogger.m1735e(f2756a, "**                                                **");
        AppboyLogger.m1735e(f2756a, "**                 !! WARNING !!                  **");
        AppboyLogger.m1735e(f2756a, "**                                                **");
        AppboyLogger.m1735e(f2756a, "**     No API key set in res/values/appboy.xml    **");
        AppboyLogger.m1735e(f2756a, "** No cached API Key found from Appboy.configure  **");
        AppboyLogger.m1735e(f2756a, "**         Appboy functionality disabled          **");
        AppboyLogger.m1735e(f2756a, "**                                                **");
        AppboyLogger.m1735e(f2756a, "****************************************************");
        throw new RuntimeException("Unable to read the Appboy API key from the res/values/appboy.xml file. See log for more details.");
    }

    public boolean isGcmMessagingRegistrationEnabled() {
        return getBooleanValue("com_appboy_push_gcm_messaging_registration_enabled", false);
    }

    public boolean isAdmMessagingRegistrationEnabled() {
        return getBooleanValue("com_appboy_push_adm_messaging_registration_enabled", false);
    }

    public boolean isLocationCollectionEnabled() {
        return !getBooleanValue("com_appboy_disable_location_collection", false);
    }

    public boolean isBackgroundLocationCollectionEnabled() {
        return getBooleanValue("com_appboy_enable_background_location_collection", false);
    }

    public long getLocationUpdateTimeIntervalInMillis() {
        return 1000 * ((long) getIntValue("com_appboy_location_update_time_interval", -1));
    }

    public float getLocationUpdateDistanceInMeters() {
        return (float) getIntValue("com_appboy_location_update_distance", -1);
    }

    public int getSmallNotificationIconResourceId() {
        return m1717a(C3141a.SMALL);
    }

    public int getLargeNotificationIconResourceId() {
        return m1717a(C3141a.LARGE);
    }

    public long getTriggerActionMinimumTimeIntervalInSeconds() {
        return (long) getIntValue("com_appboy_trigger_action_minimum_time_interval_seconds", 30);
    }

    public int getSessionTimeoutSeconds() {
        return getIntValue("com_appboy_session_timeout", 10);
    }

    public int getVersionCode() {
        int i;
        if (this.mConfigurationCache.containsKey("version_code")) {
            return ((Integer) this.mConfigurationCache.get("version_code")).intValue();
        }
        try {
            i = this.f2757b.getPackageManager().getPackageInfo(PackageUtils.getResourcePackageName(this.f2757b), 0).versionCode;
        } catch (Exception e) {
            AppboyLogger.m1736e(f2756a, "Unable to read the version code.", e);
            i = -1;
        }
        this.mConfigurationCache.put("version_code", Integer.valueOf(i));
        return i;
    }

    public String getGcmSenderId() {
        return getStringValue("com_appboy_push_gcm_sender_id", null);
    }

    public boolean getHandlePushDeepLinksAutomatically() {
        return getBooleanValue("com_appboy_handle_push_deep_links_automatically", false);
    }

    public boolean getIsUilImageCacheDisabled() {
        return getBooleanValue("com_appboy_disable_uil_image_cache", false);
    }

    public boolean getIsNewsfeedVisualIndicatorOn() {
        return getBooleanValue("com_appboy_newsfeed_unread_visual_indicator_on", true);
    }

    public boolean getIsFrescoLibraryUseEnabled() {
        return getBooleanValue("com_appboy_enable_fresco_library_use", false);
    }

    public int getApplicationIconResourceId() {
        int i = 0;
        if (this.mConfigurationCache.containsKey("application_icon")) {
            return ((Integer) this.mConfigurationCache.get("application_icon")).intValue();
        }
        String packageName = this.f2757b.getPackageName();
        try {
            i = this.f2757b.getPackageManager().getApplicationInfo(packageName, 0).icon;
        } catch (NameNotFoundException e) {
            AppboyLogger.m1735e(f2756a, String.format("Cannot find package named %s", new Object[]{packageName}));
            try {
                i = this.f2757b.getPackageManager().getApplicationInfo(this.f2757b.getPackageName(), 0).icon;
            } catch (NameNotFoundException e2) {
                AppboyLogger.m1735e(f2756a, String.format("Cannot find package named %s", new Object[]{packageName}));
            }
        }
        this.mConfigurationCache.put("application_icon", Integer.valueOf(i));
        return i;
    }

    @TargetApi(21)
    public int getDefaultNotificationAccentColor() {
        return getIntValue("com_appboy_default_notification_accent_color", 0);
    }

    /* renamed from: a */
    private int m1717a(C3141a aVar) {
        String str = aVar.equals(C3141a.LARGE) ? "com_appboy_push_large_notification_icon" : "com_appboy_push_small_notification_icon";
        if (this.mConfigurationCache.containsKey(str)) {
            return ((Integer) this.mConfigurationCache.get(str)).intValue();
        }
        if (this.mRuntimeAppConfigurationProvider.mo7301a(str)) {
            int identifier = this.f2757b.getResources().getIdentifier(this.mRuntimeAppConfigurationProvider.mo7300a(str, ""), "drawable", PackageUtils.getResourcePackageName(this.f2757b));
            this.mConfigurationCache.put(str, Integer.valueOf(identifier));
            return identifier;
        }
        int identifier2 = this.f2757b.getResources().getIdentifier(str, "drawable", PackageUtils.getResourcePackageName(this.f2757b));
        this.mConfigurationCache.put(str, Integer.valueOf(identifier2));
        return identifier2;
    }

    /* renamed from: a */
    private String m1718a() {
        return getStringValue("com_appboy_server_target", "PROD");
    }
}
