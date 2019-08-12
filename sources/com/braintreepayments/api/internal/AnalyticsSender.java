package com.braintreepayments.api.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.models.Authorization;
import com.braintreepayments.api.models.ClientToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;
import p005cn.jpush.android.JPushConstants.PushService;

public class AnalyticsSender {
    public static void send(Context context, Authorization authorization, BraintreeHttpClient httpClient, String analyticsUrl, boolean synchronous) {
        final AnalyticsDatabase db = AnalyticsDatabase.getInstance(context);
        try {
            for (final List<AnalyticsEvent> innerEvents : db.getPendingRequests()) {
                JSONObject analyticsRequest = serializeEvents(context, authorization, innerEvents);
                if (synchronous) {
                    try {
                        httpClient.post(analyticsUrl, analyticsRequest.toString());
                        db.removeEvents(innerEvents);
                    } catch (Exception e) {
                    }
                } else {
                    httpClient.post(analyticsUrl, analyticsRequest.toString(), new HttpResponseCallback() {
                        public void success(String responseBody) {
                            db.removeEvents(innerEvents);
                        }

                        public void failure(Exception exception) {
                        }
                    });
                }
            }
        } catch (JSONException e2) {
        }
    }

    private static JSONObject serializeEvents(Context context, Authorization authorization, List<AnalyticsEvent> events) throws JSONException {
        AnalyticsEvent primeEvent = (AnalyticsEvent) events.get(0);
        JSONObject requestObject = new JSONObject();
        if (authorization instanceof ClientToken) {
            requestObject.put("authorization_fingerprint", ((ClientToken) authorization).getAuthorizationFingerprint());
        } else {
            requestObject.put("tokenization_key", authorization.toString());
        }
        requestObject.put("_meta", primeEvent.metadata.put(PushService.PARAM_PLATFORM, InternalLogger.EVENT_PARAM_SDK_ANDROID).put("integrationType", primeEvent.getIntegrationType()).put("platformVersion", Integer.toString(VERSION.SDK_INT)).put("sdkVersion", "2.5.2").put("merchantAppId", context.getPackageName()).put("merchantAppName", getAppName(context)).put("deviceRooted", isDeviceRooted()).put("deviceManufacturer", Build.MANUFACTURER).put("deviceModel", Build.MODEL).put("androidId", getAndroidId(context)).put("deviceAppGeneratedPersistentUuid", UUIDHelper.getPersistentUUID(context)).put("isSimulator", detectEmulator()));
        JSONArray eventObjects = new JSONArray();
        for (AnalyticsEvent analyticsEvent : events) {
            eventObjects.put(new JSONObject().put("kind", analyticsEvent.event).put(ErfExperimentsModel.TIMESTAMP, analyticsEvent.timestamp));
        }
        requestObject.put("analytics", eventObjects);
        return requestObject;
    }

    private static String detectEmulator() {
        if ("google_sdk".equalsIgnoreCase(Build.PRODUCT) || "sdk".equalsIgnoreCase(Build.PRODUCT) || "Genymotion".equalsIgnoreCase(Build.MANUFACTURER) || Build.FINGERPRINT.contains("generic")) {
            return "true";
        }
        return InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
    }

    private static String getAppName(Context context) {
        ApplicationInfo applicationInfo;
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            applicationInfo = null;
        }
        String appName = null;
        if (applicationInfo != null) {
            appName = (String) packageManager.getApplicationLabel(applicationInfo);
        }
        if (appName == null) {
            return "ApplicationNameUnknown";
        }
        return appName;
    }

    private static String isDeviceRooted() {
        boolean check1;
        boolean check2;
        boolean check3;
        boolean z = false;
        String buildTags = Build.TAGS;
        if (buildTags == null || !buildTags.contains("test-keys")) {
            check1 = false;
        } else {
            check1 = true;
        }
        try {
            check2 = new File("/system/app/Superuser.apk").exists();
        } catch (Exception e) {
            check2 = false;
        }
        try {
            if (new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"}).getInputStream())).readLine() != null) {
                check3 = true;
            } else {
                check3 = false;
            }
        } catch (Exception e2) {
            check3 = false;
        }
        if (check1 || check2 || check3) {
            z = true;
        }
        return Boolean.toString(z);
    }

    private static String getAndroidId(Context context) {
        String id = Secure.getString(context.getContentResolver(), JPushReportInterface.ANDROID_ID);
        if (id == null) {
            return "AndroidIdUnknown";
        }
        return id;
    }
}
