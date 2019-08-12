package com.bugsnag.android;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import com.airbnb.android.core.utils.NetworkUtil;
import com.bugsnag.android.JsonStream.Streamable;
import java.io.IOException;
import java.util.Date;

class DeviceState implements Streamable {
    private final Float batteryLevel;
    private final Boolean charging;
    private final Long freeDisk;
    private final Long freeMemory = getFreeMemory();
    private final String locationStatus;
    private final String networkAccess;
    private final String orientation;
    private final String time;

    DeviceState(Context appContext) {
        this.orientation = getOrientation(appContext);
        this.batteryLevel = getBatteryLevel(appContext);
        this.freeDisk = getFreeDisk();
        this.charging = isCharging(appContext);
        this.locationStatus = getLocationStatus(appContext);
        this.networkAccess = getNetworkAccess(appContext);
        this.time = getTime();
    }

    public void toStream(JsonStream writer) throws IOException {
        writer.beginObject();
        writer.name("freeMemory").value((Number) this.freeMemory);
        writer.name("orientation").value(this.orientation);
        writer.name("batteryLevel").value((Number) this.batteryLevel);
        writer.name("freeDisk").value((Number) this.freeDisk);
        writer.name("charging").value(this.charging);
        writer.name("locationStatus").value(this.locationStatus);
        writer.name("networkAccess").value(this.networkAccess);
        writer.name("time").value(this.time);
        writer.endObject();
    }

    private static Long getFreeMemory() {
        if (Runtime.getRuntime().maxMemory() != Long.MAX_VALUE) {
            return Long.valueOf((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory()) + Runtime.getRuntime().freeMemory());
        }
        return Long.valueOf(Runtime.getRuntime().freeMemory());
    }

    private static String getOrientation(Context appContext) {
        switch (appContext.getResources().getConfiguration().orientation) {
            case 1:
                return "portrait";
            case 2:
                return "landscape";
            default:
                return null;
        }
    }

    private static Float getBatteryLevel(Context appContext) {
        boolean z = false;
        try {
            Intent batteryStatus = appContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            return Float.valueOf(((float) batteryStatus.getIntExtra("level", -1)) / ((float) batteryStatus.getIntExtra("scale", -1)));
        } catch (Exception e) {
            Logger.warn("Could not get batteryLevel");
            return z;
        }
    }

    private static Long getFreeDisk() {
        try {
            StatFs externalStat = new StatFs(Environment.getExternalStorageDirectory().getPath());
            long externalBytesAvailable = ((long) externalStat.getBlockSize()) * ((long) externalStat.getBlockCount());
            StatFs internalStat = new StatFs(Environment.getDataDirectory().getPath());
            return Long.valueOf(Math.min(((long) internalStat.getBlockSize()) * ((long) internalStat.getBlockCount()), externalBytesAvailable));
        } catch (Exception e) {
            Logger.warn("Could not get freeDisk");
            return null;
        }
    }

    private static Boolean isCharging(Context appContext) {
        try {
            int status = appContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("status", -1);
            return Boolean.valueOf(status == 2 || status == 5);
        } catch (Exception e) {
            Logger.warn("Could not get charging status");
            return null;
        }
    }

    private static String getLocationStatus(Context appContext) {
        try {
            String providersAllowed = Secure.getString(appContext.getContentResolver(), "location_providers_allowed");
            if (providersAllowed == null || providersAllowed.length() <= 0) {
                return "disallowed";
            }
            return "allowed";
        } catch (Exception e) {
            Logger.warn("Could not get locationStatus");
            return null;
        }
    }

    private static String getNetworkAccess(Context appContext) {
        try {
            NetworkInfo activeNetwork = ((ConnectivityManager) appContext.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting()) {
                return "none";
            }
            if (activeNetwork.getType() == 1) {
                return "wifi";
            }
            if (activeNetwork.getType() == 9) {
                return "ethernet";
            }
            return NetworkUtil.NETWORK_TYPE_CELLULAR;
        } catch (Exception e) {
            Logger.warn("Could not get network access information, we recommend granting the 'android.permission.ACCESS_NETWORK_STATE' permission");
            return null;
        }
    }

    private String getTime() {
        return DateUtils.toISO8601(new Date());
    }
}
