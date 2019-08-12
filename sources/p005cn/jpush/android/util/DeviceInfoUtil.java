package p005cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants;

/* renamed from: cn.jpush.android.util.DeviceInfoUtil */
public class DeviceInfoUtil {
    private static final String APPKEY = "app_key";
    private static final String APPVERSIONCODE = "app_versioncode";
    private static final String APPVERSIONNAME = "app_versionname";
    private static final String CHANNEL = "channel";
    private static final String CPU = "cpu_info";
    private static final String DEVICE_INFO = "device_info";
    private static final String DEVICE_INFO_SPF = "jpush_device_info";
    private static final String LANGUAGE = "language";
    private static final String MODEL = "model";
    private static final String OSVERSION = "os_version";
    private static final String RESOLUTION = "resolution";
    private static final String SDKVERSION = "sdk_version";
    private static final String TAG = DeviceInfoUtil.class.getSimpleName();
    private static final String TIMEZONE = "timezone";
    private static Map<String, String> old_info = null;

    private static Map<String, String> getDeviceInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        Map<String, String> info = new HashMap<>();
        String cpu = null;
        String resolution = null;
        String channel = null;
        String appkey = null;
        String versionCode = null;
        String versionName = null;
        String osVersion = null;
        String language = null;
        String timezone = null;
        String sdkVersion = null;
        String model = null;
        try {
            cpu = AndroidUtil.getCpuInfo();
            resolution = AndroidUtil.getWidthHeight(context);
            channel = Configs.getChannel();
            appkey = Configs.getAppKey();
            versionCode = JPush.VERSION_NUM + "";
            versionName = JPush.VERSION_NAME.replaceAll("[^a-zA-Z0-9_.]", "_");
            osVersion = VERSION.RELEASE + "";
            sdkVersion = JPushConstants.SDK_VERSION;
            model = Build.MODEL;
            language = context.getResources().getConfiguration().locale.toString();
            long timezoneValue = ((long) TimeZone.getDefault().getRawOffset()) / 3600000;
            if (timezoneValue > 0) {
                timezone = "+" + timezoneValue;
            } else if (timezoneValue < 0) {
                timezone = "-" + timezoneValue;
            } else {
                timezone = "" + timezoneValue;
            }
        } catch (Exception e) {
            Logger.m1420e(TAG, e.getMessage());
        }
        if (!StringUtils.isEmpty(cpu)) {
            info.put(CPU, cpu);
        }
        if (!StringUtils.isEmpty(resolution)) {
            info.put(RESOLUTION, resolution);
        }
        if (!StringUtils.isEmpty(channel)) {
            info.put(CHANNEL, channel);
        }
        if (!StringUtils.isEmpty(appkey)) {
            info.put(APPKEY, appkey);
        }
        if (!StringUtils.isEmpty(versionCode)) {
            info.put(APPVERSIONCODE, versionCode);
        }
        if (!StringUtils.isEmpty(versionName)) {
            info.put(APPVERSIONNAME, versionName);
        }
        if (!StringUtils.isEmpty(osVersion)) {
            info.put(OSVERSION, osVersion);
        }
        if (!StringUtils.isEmpty(language)) {
            info.put(LANGUAGE, language);
        }
        if (!StringUtils.isEmpty(timezone)) {
            info.put(TIMEZONE, timezone);
        }
        if (!StringUtils.isEmpty(sdkVersion)) {
            info.put(SDKVERSION, sdkVersion);
        }
        if (!StringUtils.isEmpty(model)) {
            info.put(MODEL, model);
        }
        return info;
    }

    private static void saveInfoToSpf(Context context, Map<String, String> info) {
        if (info != null) {
            Set<String> keys = info.keySet();
            if (keys != null && keys.size() > 0) {
                Editor editor = context.getSharedPreferences(DEVICE_INFO_SPF, 0).edit();
                for (String key : keys) {
                    editor.putString(key, (String) info.get(key));
                }
                editor.commit();
            }
        }
    }

    public static String getVersion(Context context) {
        return context.getSharedPreferences(DEVICE_INFO_SPF, 0).getString(SDKVERSION, null);
    }

    public static void setVersion(Context context, String sdkversion) {
        Editor editor = context.getSharedPreferences(DEVICE_INFO_SPF, 0).edit();
        editor.putString(SDKVERSION, sdkversion);
        editor.commit();
    }

    private static Map<String, String> getInfoFromSpf(Context context) {
        Map<String, String> info = new HashMap<>();
        SharedPreferences spf = context.getSharedPreferences(DEVICE_INFO_SPF, 0);
        String cpu = spf.getString(CPU, null);
        String resolution = spf.getString(RESOLUTION, null);
        String channel = spf.getString(CHANNEL, null);
        String appkey = spf.getString(APPKEY, null);
        String versionCode = spf.getString(APPVERSIONCODE, null);
        String versionName = spf.getString(APPVERSIONNAME, null);
        String osVersion = spf.getString(LANGUAGE, null);
        String language = osVersion;
        String timezone = spf.getString(TIMEZONE, null);
        String sdkVersion = spf.getString(SDKVERSION, null);
        String model = spf.getString(MODEL, null);
        if (!StringUtils.isEmpty(cpu)) {
            info.put(CPU, cpu);
        }
        if (!StringUtils.isEmpty(resolution)) {
            info.put(RESOLUTION, resolution);
        }
        if (!StringUtils.isEmpty(channel)) {
            info.put(CHANNEL, channel);
        }
        if (!StringUtils.isEmpty(appkey)) {
            info.put(APPKEY, appkey);
        }
        if (!StringUtils.isEmpty(versionCode)) {
            info.put(APPVERSIONCODE, versionCode);
        }
        if (!StringUtils.isEmpty(versionName)) {
            info.put(APPVERSIONNAME, versionName);
        }
        if (!StringUtils.isEmpty(osVersion)) {
            info.put(OSVERSION, osVersion);
        }
        if (!StringUtils.isEmpty(language)) {
            info.put(LANGUAGE, language);
        }
        if (!StringUtils.isEmpty(timezone)) {
            info.put(TIMEZONE, timezone);
        }
        if (!StringUtils.isEmpty(sdkVersion)) {
            info.put(SDKVERSION, sdkVersion);
        }
        if (!StringUtils.isEmpty(model)) {
            info.put(MODEL, model);
        }
        return info;
    }

    private static boolean isDeviceInfoChange(Map<String, String> src, Map<String, String> oldInfo) {
        if (oldInfo == null || oldInfo.isEmpty() || !src.equals(oldInfo)) {
            return true;
        }
        return false;
    }

    public static void reportDeviceInfo(Context context) {
        Logger.m1416d(TAG, "action:reportDeviceInfo");
        if (Configs.isValidRegistered()) {
            Map<String, String> new_info = getDeviceInfo(context);
            if (new_info != null && !new_info.isEmpty()) {
                if (old_info == null) {
                    old_info = getInfoFromSpf(context);
                }
                if (isDeviceInfoChange(new_info, old_info)) {
                    old_info = new_info;
                    saveInfoToSpf(context, new_info);
                }
            }
        }
    }
}
