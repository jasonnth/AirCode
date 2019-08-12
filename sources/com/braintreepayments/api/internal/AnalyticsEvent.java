package com.braintreepayments.api.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.braintreepayments.api.Venmo;
import com.facebook.internal.AnalyticsEvents;
import com.paypal.android.sdk.onetouch.core.PayPalOneTouchCore;
import icepick.Icepick;
import org.json.JSONException;
import org.json.JSONObject;

public class AnalyticsEvent {
    String event;

    /* renamed from: id */
    int f2921id;
    JSONObject metadata;
    long timestamp;

    public AnalyticsEvent(Context context, String sessionId, String integration, String event2) {
        this.event = Icepick.ANDROID_PREFIX + integration + "." + event2;
        this.timestamp = System.currentTimeMillis() / 1000;
        this.metadata = new JSONObject();
        try {
            this.metadata.put("sessionId", sessionId).put("deviceNetworkType", getNetworkType(context)).put("userInterfaceOrientation", getUserOrientation(context)).put("merchantAppVersion", getAppVersion(context)).put("paypalInstalled", isPayPalInstalled(context)).put("venmoInstalled", Venmo.isVenmoInstalled(context));
        } catch (JSONException e) {
        }
    }

    public AnalyticsEvent() {
        this.metadata = new JSONObject();
    }

    public String getIntegrationType() {
        String[] eventSegments = this.event.split("\\.");
        if (eventSegments.length > 1) {
            return eventSegments[1];
        }
        return "";
    }

    private String getNetworkType(Context context) {
        String networkType = null;
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (networkInfo != null) {
            networkType = networkInfo.getTypeName();
        }
        if (networkType == null) {
            return "none";
        }
        return networkType;
    }

    private String getUserOrientation(Context context) {
        switch (context.getResources().getConfiguration().orientation) {
            case 1:
                return "Portrait";
            case 2:
                return "Landscape";
            default:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }

    private String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return "VersionUnknown";
        }
    }

    private boolean isPayPalInstalled(Context context) {
        try {
            Class.forName(PayPalOneTouchCore.class.getName());
            return PayPalOneTouchCore.isWalletAppInstalled(context);
        } catch (ClassNotFoundException | NoClassDefFoundError e) {
            return false;
        }
    }
}
