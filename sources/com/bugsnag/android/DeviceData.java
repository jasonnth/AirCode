package com.bugsnag.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import com.bugsnag.android.JsonStream.Streamable;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

class DeviceData implements Streamable {
    private static final String[] ROOT_INDICATORS = {"/system/xbin/su", "/system/bin/su", "/system/app/Superuser.apk", "/system/app/SuperSU.apk", "/system/app/Superuser", "/system/app/SuperSU", "/system/xbin/daemonsu", "/su/bin"};
    protected final String[] cpuAbi;
    protected final Integer dpi;

    /* renamed from: id */
    protected final String f2923id;
    protected final String locale = getLocale();
    protected final Boolean rooted = isRooted();
    protected final Float screenDensity;
    protected final String screenResolution;
    protected final Long totalMemory = getTotalMemory();

    private static class Abi2Wrapper {
        @TargetApi(8)
        public static String[] getAbi1andAbi2() {
            return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        }
    }

    private static class SupportedAbiWrapper {
        @TargetApi(21)
        public static String[] getSupportedAbis() {
            return Build.SUPPORTED_ABIS;
        }
    }

    DeviceData(Context appContext) {
        this.screenDensity = getScreenDensity(appContext);
        this.dpi = getScreenDensityDpi(appContext);
        this.screenResolution = getScreenResolution(appContext);
        this.f2923id = getAndroidId(appContext);
        this.cpuAbi = getCpuAbi();
    }

    public void toStream(JsonStream writer) throws IOException {
        writer.beginObject();
        writer.name("osName").value("android");
        writer.name("manufacturer").value(Build.MANUFACTURER);
        writer.name("brand").value(Build.BRAND);
        writer.name("model").value(Build.MODEL);
        writer.name("id").value(this.f2923id);
        writer.name("apiLevel").value((long) VERSION.SDK_INT);
        writer.name("osVersion").value(VERSION.RELEASE);
        writer.name("osBuild").value(Build.DISPLAY);
        writer.name(AccountKitGraphConstants.PARAMETER_LOCALE).value(this.locale);
        writer.name("totalMemory").value((Number) this.totalMemory);
        writer.name("jailbroken").value(this.rooted);
        writer.name("screenDensity").value((Number) this.screenDensity);
        writer.name("dpi").value((Number) this.dpi);
        writer.name("screenResolution").value(this.screenResolution);
        writer.name("cpuAbi").beginArray();
        for (String s : this.cpuAbi) {
            writer.value(s);
        }
        writer.endArray();
        writer.endObject();
    }

    public String getUserId() {
        return this.f2923id;
    }

    private static Float getScreenDensity(Context appContext) {
        Resources resources = appContext.getResources();
        if (resources == null) {
            return null;
        }
        return Float.valueOf(resources.getDisplayMetrics().density);
    }

    private static Integer getScreenDensityDpi(Context appContext) {
        Resources resources = appContext.getResources();
        if (resources == null) {
            return null;
        }
        return Integer.valueOf(resources.getDisplayMetrics().densityDpi);
    }

    private static String getScreenResolution(Context appContext) {
        Resources resources = appContext.getResources();
        if (resources == null) {
            return null;
        }
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return String.format(Locale.US, "%dx%d", new Object[]{Integer.valueOf(Math.max(metrics.widthPixels, metrics.heightPixels)), Integer.valueOf(Math.min(metrics.widthPixels, metrics.heightPixels))});
    }

    private static Long getTotalMemory() {
        if (Runtime.getRuntime().maxMemory() != Long.MAX_VALUE) {
            return Long.valueOf(Runtime.getRuntime().maxMemory());
        }
        return Long.valueOf(Runtime.getRuntime().totalMemory());
    }

    private static Boolean isRooted() {
        if (Build.TAGS != null && Build.TAGS.contains("test-keys")) {
            return Boolean.valueOf(true);
        }
        try {
            for (String candidate : ROOT_INDICATORS) {
                if (new File(candidate).exists()) {
                    return Boolean.valueOf(true);
                }
            }
            return Boolean.valueOf(false);
        } catch (Exception e) {
            return null;
        }
    }

    private static String getLocale() {
        return Locale.getDefault().toString();
    }

    private static String getAndroidId(Context appContext) {
        return Secure.getString(appContext.getContentResolver(), JPushReportInterface.ANDROID_ID);
    }

    private static String[] getCpuAbi() {
        if (VERSION.SDK_INT >= 21) {
            return SupportedAbiWrapper.getSupportedAbis();
        }
        if (VERSION.SDK_INT >= 8) {
            return Abi2Wrapper.getAbi1andAbi2();
        }
        return new String[]{Build.CPU_ABI};
    }
}
