package p005cn.jpush.android.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.security.auth.x500.X500Principal;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;
import p005cn.jpush.android.JPushConstants.PushActivity;
import p005cn.jpush.android.api.InstrumentedActivity;
import p005cn.jpush.android.api.InstrumentedListActivity;
import p005cn.jpush.android.api.JPushInterface;
import p005cn.jpush.android.api.NotificationHelper;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.helpers.ReportHelper;
import p005cn.jpush.android.p006ui.PopWinActivity;
import p005cn.jpush.android.service.AlarmReceiver;
import p005cn.jpush.android.service.DaemonService;
import p005cn.jpush.android.service.DownloadService;
import p005cn.jpush.android.service.PushReceiver;
import p005cn.jpush.android.service.PushService;
import p005cn.jpush.android.service.StatusCode;
import p005cn.jpush.android.service.SystemPropertiesProxy;
import p005cn.jpush.android.service.WakelockManager;
import p005cn.jpush.proto.common.imcommands.ImResponseHelper;

/* renamed from: cn.jpush.android.util.AndroidUtil */
public class AndroidUtil {
    private static final X500Principal DEBUG_DN = new X500Principal("CN=Android Debug,O=Android,C=US");
    private static final String INVALID_ANDROIDID = "9774d56d682e549c";
    private static final ArrayList<String> OPTIONAL_PERMISSIONS = new ArrayList<>();
    public static final String PATH_DATA_INSTALL = "/data/app/";
    public static final String PATH_SYSTEM_INSTALL = "/system/app/";
    public static final String PREFS_NAME = "PrefsFile";
    private static final ArrayList<String> REQUIRED_PERMISSIONS = new ArrayList<>();
    private static final String TAG = "AndroidUtil";
    private static long WAKELOCK_START_TIME = 0;
    public static int deviceID_Status = 1;
    private static List<String> invalidImeis = new ArrayList();
    private static PushReceiver receiver;

    /* renamed from: cn.jpush.android.util.AndroidUtil$DeviceID_Status */
    private enum DeviceID_Status {
        DEVICEID_FROM_NEW,
        DEVICEID_FROM_SETTING,
        DEVICEID_FROM_EXTERNALSTORAGE,
        DEVICEID_FROM_SHAREPREFS,
        DEVICEID_FROM_SYS
    }

    static {
        invalidImeis.add("358673013795895");
        invalidImeis.add("004999010640000");
        invalidImeis.add("00000000000000");
        invalidImeis.add("000000000000000");
        REQUIRED_PERMISSIONS.add("android.permission.INTERNET");
        REQUIRED_PERMISSIONS.add("android.permission.WAKE_LOCK");
        REQUIRED_PERMISSIONS.add("android.permission.ACCESS_NETWORK_STATE");
        OPTIONAL_PERMISSIONS.add("android.permission.VIBRATE");
        OPTIONAL_PERMISSIONS.add("android.permission.CHANGE_WIFI_STATE");
    }

    public static String getClientInfo(Context context, String sdkVersion) {
        String androidSdkVersion = VERSION.RELEASE + "," + Integer.toString(VERSION.SDK_INT);
        String model = Build.MODEL;
        String baseband = SystemPropertiesProxy.get(context, "gsm.version.baseband", "baseband");
        String device = Build.DEVICE;
        String channel = Configs.getChannel();
        if (StringUtils.isEmpty(channel)) {
            channel = " ";
        }
        return androidSdkVersion + "$$" + model + "$$" + baseband + "$$" + device + "$$" + channel + "$$" + sdkVersion + "$$" + (isSystemInstall(context) ? 1 : 0) + "$$" + getWidthHeight(context);
    }

    public static String getWidthHeight(Context context) {
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        if (mDisplayMetrics == null) {
            return "0*0";
        }
        int width = mDisplayMetrics.widthPixels;
        return width + "*" + mDisplayMetrics.heightPixels;
    }

    public static String getDownloadFailedClientInfo(Context context, String failedUrl) {
        String androidSdkVersion = VERSION.RELEASE + "," + Integer.toString(VERSION.SDK_INT);
        String model = Build.MODEL;
        String baseband = SystemPropertiesProxy.get(context, "gsm.version.baseband", "baseband");
        String device = Build.DEVICE;
        String channel = Configs.getChannel();
        if (StringUtils.isEmpty(channel)) {
            channel = " ";
        }
        String network = getNetworkType(context);
        JSONObject json = new JSONObject();
        try {
            json.put("androidSdkVersion", androidSdkVersion);
            json.put("model", model);
            json.put("baseband", baseband);
            json.put("device", device);
            json.put("channel", channel);
            json.put("network", network);
            json.put("url", failedUrl);
        } catch (JSONException e) {
        }
        return json.toString();
    }

    public static boolean hasPermission(Context context, String thePermission) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(thePermission)) {
                    if (context.getPackageManager().checkPermission(thePermission, context.getPackageName()) == 0) {
                        return true;
                    }
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("empty params");
    }

    public static boolean hasPermissionDefined(Context context, String thePermission) {
        if (context == null || TextUtils.isEmpty(thePermission)) {
            throw new IllegalArgumentException("empty params");
        }
        try {
            context.getPackageManager().getPermissionInfo(thePermission, 128);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean hasReceiver(Context context, String compoentName) {
        try {
            context.getPackageManager().getReceiverInfo(new ComponentName(context.getPackageName(), compoentName), 128);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean hasService(Context context, String compoentName) {
        try {
            context.getPackageManager().getServiceInfo(new ComponentName(context.getPackageName(), compoentName), 128);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean hasActivity(Context context, String compoentName) {
        try {
            context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), compoentName), 128);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean hasReceiverResolves(Context context, Class<?> cls) {
        if (!context.getPackageManager().queryBroadcastReceivers(new Intent(context, cls), 0).isEmpty()) {
            return true;
        }
        Logger.m1422ee(TAG, "No target receiver!");
        return false;
    }

    public static boolean hasServiceResolves(Context context, Class<?> cls) {
        if (!context.getPackageManager().queryIntentServices(new Intent(context, cls), 0).isEmpty()) {
            return true;
        }
        Logger.m1422ee(TAG, "No target services!");
        return false;
    }

    public static boolean hasActivityResolves(Context context, Class<?> cls) {
        if (!context.getPackageManager().queryIntentActivities(new Intent(context, cls), 0).isEmpty()) {
            return true;
        }
        Logger.m1422ee(TAG, "No target activitis!");
        return false;
    }

    public static boolean hasReceiverIntentFilter(Context context, String action, boolean needPackageCategory) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(action);
        if (needPackageCategory) {
            intent.addCategory(context.getPackageName());
        }
        if (pm.queryBroadcastReceivers(intent, 0).isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean hasReceiverIntentFilterPackage(Context context, String action) {
        if (context.getPackageManager().queryBroadcastReceivers(new Intent(action), 0).isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean hasReceiverIntentFilterPackage(Context context, String receiveName, String action) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(action);
        intent.setPackage(context.getPackageName());
        for (ResolveInfo resolveInfo : pm.queryBroadcastReceivers(intent, 0)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null && activityInfo.name.equals(receiveName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasServiceIntentFilter(Context context, String action, boolean needCategory) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(action);
        if (needCategory) {
            intent.addCategory(context.getPackageName());
        }
        if (pm.queryIntentServices(intent, 0).isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean hasActivityIntentFilter(Context context, String action) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(action);
        intent.addCategory(context.getPackageName());
        if (pm.queryIntentActivities(intent, 0).isEmpty()) {
            return false;
        }
        return true;
    }

    public static void installPackage(Context context, String filePath) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(filePath)), JPushConstants.A_MIME);
        context.startActivity(intent);
    }

    public static void createWebUrlShortcut(Context context, String name, String url, int iconResourceId) {
        Uri uri = Uri.parse(url);
        if (uri == null) {
            Logger.m1416d(TAG, "Unexpected: invalid url - " + url);
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setFlags(335544320);
        createShortCut(context, intent, name, iconResourceId);
    }

    public static void createShortCut(Context contex, Intent intent, String name, int iconResourceId) {
        ShortcutIconResource iconRes = ShortcutIconResource.fromContext(contex, iconResourceId);
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcut.putExtra("duplicate", false);
        shortcut.putExtra("android.intent.extra.shortcut.NAME", name);
        shortcut.putExtra("android.intent.extra.shortcut.INTENT", intent);
        shortcut.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", iconRes);
        contex.sendBroadcast(shortcut);
    }

    public static boolean isConnected(Context context) {
        try {
            NetworkInfo info = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (info == null || !info.isConnected()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isSdcardExist() {
        boolean ret = Environment.getExternalStorageState().equals("mounted");
        if (!ret) {
            Logger.m1416d(TAG, "SDCard is not mounted");
        }
        return ret;
    }

    public static boolean isPackageExist(Context context, String aPackageName) {
        try {
            context.getPackageManager().getApplicationInfo(aPackageName, 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static int dip2px(Context context, float dpValue) {
        return (int) (dpValue * context.getResources().getDisplayMetrics().density);
    }

    public static boolean startNewPK(Context context, String packageName, String packageMainClassName) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        } else if (TextUtils.isEmpty(packageName)) {
            Logger.m1432w(TAG, "Give up to start app for invalid params - packageName:" + packageName);
            return false;
        } else {
            Intent intent = findLaunchIntentForActivity(context, packageName);
            if (intent == null) {
                try {
                    if (TextUtils.isEmpty(packageMainClassName)) {
                        Logger.m1416d(TAG, "Empty main activity to run up");
                        return false;
                    }
                    Intent intent2 = new Intent();
                    try {
                        intent2.setClassName(packageName, packageMainClassName);
                        intent2.addCategory("android.intent.category.LAUNCHER");
                        intent = intent2;
                    } catch (Exception e) {
                        e = e;
                        Intent intent3 = intent2;
                        Logger.m1417d(TAG, "May invalid app main activity", e);
                        return false;
                    }
                } catch (Exception e2) {
                    e = e2;
                    Logger.m1417d(TAG, "May invalid app main activity", e);
                    return false;
                }
            }
            intent.setFlags(268435456);
            context.startActivity(intent);
            return true;
        }
    }

    public static Intent findLaunchIntentForActivity(Context context, String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            if (pm.getPackageInfo(packageName, 256) != null) {
                return pm.getLaunchIntentForPackage(packageName);
            }
        } catch (NameNotFoundException e) {
        }
        return null;
    }

    public static Intent getIntentForStartPushActivity(Context context, Entity entity, boolean isUpdateVersion) {
        Intent intent = new Intent();
        intent.putExtra(PushActivity.IS_UPDATE_VERSION, isUpdateVersion);
        intent.putExtra("body", entity);
        intent.setAction(PushActivity.ACTION_JPUSH);
        intent.addCategory(context.getPackageName());
        intent.addFlags(335544320);
        return intent;
    }

    public static Intent getIntentForStartPopWin(Context context, Entity entity) {
        Intent intent = new Intent(context, PopWinActivity.class);
        intent.putExtra("body", entity);
        intent.addFlags(335544320);
        return intent;
    }

    public static String getNetworkType(Context context) {
        try {
            NetworkInfo info = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (info == null) {
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
            String type = info.getTypeName();
            String subtype = info.getSubtypeName();
            if (type == null) {
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
            if (subtype != null) {
                return type + "," + subtype;
            }
            return type;
        } catch (Exception e) {
            e.printStackTrace();
            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }

    public static String getConnectedTypeName(Context context) {
        try {
            NetworkInfo info = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (info == null) {
                return "";
            }
            return info.getTypeName().toUpperCase(Locale.getDefault());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getWifiMac(Context context, String defaultString) {
        if (VERSION.SDK_INT >= 23) {
            String macSerial = "";
            String str = "";
            try {
                String str2 = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address").getInputStream())).readLine();
                if (!TextUtils.isEmpty(str2)) {
                    macSerial = str2.trim();
                    Logger.m1416d(TAG, "android mac address:" + macSerial);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return macSerial;
        } else if (!hasPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
            return defaultString;
        } else {
            try {
                String mac = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
                if (!StringUtils.isEmpty(mac)) {
                    return mac;
                }
                return defaultString;
            } catch (Exception e2) {
                Logger.m1421e(TAG, "", e2);
                return defaultString;
            }
        }
    }

    public static String getWifiMacMD5(Context context) {
        if (!hasPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
            return null;
        }
        try {
            String mac = getWifiMac(context, "");
            if (mac == null || mac.equals("")) {
                return null;
            }
            Logger.m1424i(TAG, "MAC addr info---- " + mac);
            return getMD5String(mac + Build.MODEL);
        } catch (Exception e) {
            Logger.m1421e(TAG, "", e);
            return null;
        }
    }

    public static String getMD5String(String inStr) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            char[] charArray = inStr.toCharArray();
            byte[] byteArray = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) {
                byteArray[i] = (byte) charArray[i];
            }
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (byte b : md5Bytes) {
                int val = b & 255;
                if (val < 16) {
                    hexValue.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            Logger.m1416d(TAG, "Get MD5 error");
            return "";
        }
    }

    public static String getMD5Utf8(String inStr) {
        try {
            byte[] md5Bytes = MessageDigest.getInstance("MD5").digest(inStr.getBytes("utf-8"));
            StringBuffer hexValue = new StringBuffer();
            for (byte b : md5Bytes) {
                int val = b & 255;
                if (val < 16) {
                    hexValue.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            Logger.m1416d(TAG, "Get MD5 error");
            return "";
        }
    }

    public static String getBytesMD5(byte[] byteArray) {
        try {
            byte[] md5Bytes = MessageDigest.getInstance("MD5").digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (byte b : md5Bytes) {
                int val = b & 255;
                if (val < 16) {
                    hexValue.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            Logger.m1416d(TAG, "Get MD5 error");
            return "";
        }
    }

    public static ArrayList<String> executeCommand(String[] shellCmd) {
        ArrayList<String> fullResponse = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(shellCmd).getInputStream()));
            while (true) {
                try {
                    String line = in.readLine();
                    if (line != null) {
                        Logger.m1416d(TAG, "--> Line received: " + line);
                        fullResponse.add(line);
                    } else {
                        Logger.m1416d(TAG, "--> Full response was: " + fullResponse);
                        return fullResponse;
                    }
                } catch (Exception e) {
                    return null;
                }
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public static void getUnsafePackageInfo(Context context) {
        List<PackageInfo> appInfos = context.getPackageManager().getInstalledPackages(8);
        if (appInfos != null) {
            for (PackageInfo packageInfo : appInfos) {
                ProviderInfo[] providerInfos = packageInfo.providers;
                if (providerInfos != null) {
                    for (ProviderInfo providerInfo : providerInfos) {
                        Logger.m1424i(TAG, "read permisson:" + providerInfo.readPermission);
                        Logger.m1424i(TAG, "write permission:" + providerInfo.writePermission);
                        Logger.m1424i(TAG, providerInfo.pathPermissions.toString());
                        Logger.m1424i(TAG, providerInfo.uriPermissionPatterns.toString());
                        Logger.m1424i(TAG, providerInfo.applicationInfo.permission.toString());
                    }
                }
            }
        }
    }

    public static void startMainActivity(Context context) {
        startMainActivity(context, null);
    }

    public static void startMainActivity(Context context, String extra) {
        Intent intent = new Intent("android.intent.action.MAIN");
        String packageName = context.getPackageName();
        intent.setPackage(packageName);
        if (!StringUtils.isEmpty(extra)) {
            intent.putExtra(JPushInterface.EXTRA_EXTRA, extra);
        }
        intent.addCategory("android.intent.category.LAUNCHER");
        ResolveInfo r = context.getPackageManager().resolveActivity(intent, 0);
        Logger.m1424i(TAG, "Main class is " + r.activityInfo.name);
        intent.setClassName(packageName, r.activityInfo.name);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static boolean isScreenOn(Context context) {
        return ((PowerManager) context.getSystemService("power")).isScreenOn();
    }

    public static int getChargedStatus(Context context) {
        if (context == null) {
            return -1;
        }
        Intent batteryStatus = null;
        try {
            batteryStatus = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (batteryStatus == null) {
            return -1;
        }
        int status = batteryStatus.getIntExtra("status", -1);
        if (status == 2 || status == 5) {
            return batteryStatus.getIntExtra("plugged", -1);
        }
        return -1;
    }

    public static boolean isAppOnForeground(Context context) {
        List<RunningAppProcessInfo> appProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName()) && appProcess.importance == 100) {
                return true;
            }
        }
        return false;
    }

    public static void addValidImei(String inValidImei) {
        invalidImeis.add(inValidImei);
    }

    public static boolean isValidImei(String imei) {
        if (StringUtils.isEmpty(imei)) {
            return false;
        }
        if (imei.length() < 10) {
            return false;
        }
        for (int i = 0; i < invalidImeis.size(); i++) {
            if (imei.equals(invalidImeis.get(i)) || imei.startsWith((String) invalidImeis.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDebuggable(Context ctx) {
        boolean debuggable = false;
        try {
            Signature[] signatures = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 64).signatures;
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            for (Signature byteArray : signatures) {
                debuggable = ((X509Certificate) cf.generateCertificate(new ByteArrayInputStream(byteArray.toByteArray()))).getSubjectX500Principal().equals(DEBUG_DN);
                if (debuggable) {
                    break;
                }
            }
        } catch (NameNotFoundException | Exception e) {
        }
        return debuggable;
    }

    public static String getUDID(Context context) {
        String imei = Configs.getImei(context);
        if (!StringUtils.isEmpty(imei) && isValidImei(imei)) {
            return imei;
        }
        String imei2 = getUDIDInternal(context);
        Configs.setImei(context, imei2);
        return imei2;
    }

    public static String getUDIDInternal(Context context) {
        try {
            String imei = getImei(context, "");
            if (isValidImei(imei)) {
                return imei;
            }
            String androidId = getAndroidId(context);
            if (StringUtils.isEmpty(androidId) || !isValidImei(androidId) || INVALID_ANDROIDID.equals(androidId.toLowerCase(Locale.getDefault()))) {
                return getUDIDWithoutImei(context);
            }
            return androidId;
        } catch (Exception e) {
            Logger.m1421e(TAG, "", e);
            return getSavedUuid(context);
        }
    }

    public static String getAndroidId(Context context) {
        return Secure.getString(context.getContentResolver(), JPushReportInterface.ANDROID_ID);
    }

    public static String getImei(Context context, String imei) {
        try {
            if (hasPermission(context, "android.permission.READ_PHONE_STATE")) {
                return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            }
            return imei;
        } catch (Exception e) {
            e.printStackTrace();
            return imei;
        }
    }

    public static String getUDIDWithoutImei(Context context) {
        String mac = getWifiMacMD5(context);
        if (!StringUtils.isEmpty(mac) && isValidImei(mac)) {
            return mac;
        }
        String imei = getSavedUuid(context);
        if (imei == null) {
            imei = " ";
        }
        return imei;
    }

    private static String getExternalSdDataPath() {
        String rootPath = null;
        try {
            rootPath = Environment.getExternalStorageDirectory().getPath();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception e2) {
        }
        if (!StringUtils.isEmpty(rootPath)) {
            return rootPath + "/data/";
        }
        return rootPath;
    }

    private static String getUdidFilePath() {
        String sdpath = getExternalSdDataPath();
        if (sdpath == null) {
            return null;
        }
        return sdpath + ".push_udid";
    }

    private static String getDeviceidFilePath() {
        String sdpath = getExternalSdDataPath();
        if (sdpath == null) {
            return null;
        }
        return sdpath + ".push_deviceid";
    }

    private static String getSavedUuid(Context context) {
        Logger.m1416d(TAG, "Action:getSavedUuid");
        String oldUUid = getOldSavedKey(context);
        if (!StringUtils.isEmpty(oldUUid)) {
            return oldUUid;
        }
        if (!isSdcardExist()) {
            return CheckSavedKey(context);
        }
        String udid = Configs.getPushUdid(context);
        if (udid != null) {
            return udid;
        }
        if (VERSION.SDK_INT < 23) {
            return getStringUdid(context, udid);
        }
        if (!hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") || !hasPermission(context, "android.permission.READ_EXTERNAL_STORAGE")) {
            return CheckSavedKey(context);
        }
        return getStringUdid(context, udid);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ca A[SYNTHETIC, Splitter:B:40:0x00ca] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d3 A[SYNTHETIC, Splitter:B:45:0x00d3] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getStringUdid(android.content.Context r14, java.lang.String r15) {
        /*
            java.lang.String r9 = getUdidFilePath()
            r4 = 0
            boolean r10 = p005cn.jpush.android.util.StringUtils.isEmpty(r9)
            if (r10 != 0) goto L_0x0010
            java.io.File r4 = new java.io.File
            r4.<init>(r9)
        L_0x0010:
            if (r4 == 0) goto L_0x0053
            boolean r10 = r4.exists()     // Catch:{ Exception -> 0x004f }
            if (r10 == 0) goto L_0x0053
            java.io.FileInputStream r10 = new java.io.FileInputStream     // Catch:{ Exception -> 0x004f }
            r10.<init>(r4)     // Catch:{ Exception -> 0x004f }
            java.util.ArrayList r2 = p005cn.jpush.android.util.FileUtil.readLines(r10)     // Catch:{ Exception -> 0x004f }
            int r10 = r2.size()     // Catch:{ Exception -> 0x004f }
            if (r10 <= 0) goto L_0x0053
            r10 = 0
            java.lang.Object r10 = r2.get(r10)     // Catch:{ Exception -> 0x004f }
            r0 = r10
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x004f }
            r15 = r0
            p005cn.jpush.android.Configs.setPushUdid(r14, r15)     // Catch:{ Exception -> 0x004f }
            java.lang.String r10 = "AndroidUtil"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004f }
            r11.<init>()     // Catch:{ Exception -> 0x004f }
            java.lang.String r12 = "Got sdcard file saved udid - "
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ Exception -> 0x004f }
            java.lang.StringBuilder r11 = r11.append(r15)     // Catch:{ Exception -> 0x004f }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x004f }
            p005cn.jpush.android.util.Logger.m1424i(r10, r11)     // Catch:{ Exception -> 0x004f }
            r8 = r15
        L_0x004e:
            return r8
        L_0x004f:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0053:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            long r12 = java.lang.System.currentTimeMillis()
            java.lang.StringBuilder r10 = r10.append(r12)
            java.lang.String r11 = ""
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r7 = r10.toString()
            byte[] r10 = r7.getBytes()
            java.util.UUID r10 = java.util.UUID.nameUUIDFromBytes(r10)
            java.lang.String r15 = r10.toString()
            java.lang.String r15 = p005cn.jpush.android.util.StringUtils.toMD5(r15)
            p005cn.jpush.android.Configs.setPushUdid(r14, r15)
            if (r4 == 0) goto L_0x00a4
            r4.createNewFile()     // Catch:{ IOException -> 0x00af }
            r5 = 0
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00be }
            r6.<init>(r4)     // Catch:{ IOException -> 0x00be }
            byte[] r10 = r15.getBytes()     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r6.write(r10)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            r6.flush()     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            java.lang.String r10 = "AndroidUtil"
            java.lang.String r11 = "Saved udid into file"
            p005cn.jpush.android.util.Logger.m1424i(r10, r11)     // Catch:{ IOException -> 0x00dc, all -> 0x00d9 }
            if (r6 == 0) goto L_0x00a1
            r6.close()     // Catch:{ IOException -> 0x00bb }
        L_0x00a1:
            r5 = r6
        L_0x00a2:
            r8 = r15
            goto L_0x004e
        L_0x00a4:
            java.lang.String r10 = "AndroidUtil"
            java.lang.String r11 = "udid file path is null"
            p005cn.jpush.android.util.Logger.m1420e(r10, r11)     // Catch:{ IOException -> 0x00af }
            r8 = r15
            goto L_0x004e
        L_0x00af:
            r3 = move-exception
            java.lang.String r10 = "AndroidUtil"
            java.lang.String r11 = "Create file in sdcard error"
            p005cn.jpush.android.util.Logger.m1421e(r10, r11, r3)
            r8 = r15
            goto L_0x004e
        L_0x00bb:
            r10 = move-exception
            r5 = r6
            goto L_0x00a2
        L_0x00be:
            r3 = move-exception
        L_0x00bf:
            java.lang.String r10 = "AndroidUtil"
            java.lang.String r11 = "write file error"
            p005cn.jpush.android.util.Logger.m1421e(r10, r11, r3)     // Catch:{ all -> 0x00d0 }
            if (r5 == 0) goto L_0x00a2
            r5.close()     // Catch:{ IOException -> 0x00ce }
            goto L_0x00a2
        L_0x00ce:
            r10 = move-exception
            goto L_0x00a2
        L_0x00d0:
            r10 = move-exception
        L_0x00d1:
            if (r5 == 0) goto L_0x00d6
            r5.close()     // Catch:{ IOException -> 0x00d7 }
        L_0x00d6:
            throw r10
        L_0x00d7:
            r11 = move-exception
            goto L_0x00d6
        L_0x00d9:
            r10 = move-exception
            r5 = r6
            goto L_0x00d1
        L_0x00dc:
            r3 = move-exception
            r5 = r6
            goto L_0x00bf
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.util.AndroidUtil.getStringUdid(android.content.Context, java.lang.String):java.lang.String");
    }

    private static String getDeviceIdFromExternalStorage(Context context) {
        if (!isSdcardExist()) {
            Logger.m1420e(TAG, "Can not use external storege");
            return null;
        } else if (!hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            return null;
        } else {
            if (VERSION.SDK_INT < 23) {
                return getDeviceIdFromSD();
            }
            if (!hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") || !hasPermission(context, "android.permission.READ_EXTERNAL_STORAGE")) {
                return null;
            }
            return getDeviceIdFromSD();
        }
    }

    private static String getDeviceIdFromSD() {
        String deviceidPath = getDeviceidFilePath();
        if (StringUtils.isEmpty(deviceidPath)) {
            Logger.m1422ee(TAG, "get device id  sd card file path fail");
            return null;
        }
        File file = new File(deviceidPath);
        if (file.exists()) {
            try {
                ArrayList<String> content = FileUtil.readLines(new FileInputStream(file));
                if (content.size() > 0) {
                    String udid = (String) content.get(0);
                    Logger.m1424i(TAG, "Got sdcard file saved deviceId - " + udid);
                    return udid;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private static String writeDeviceIdToExternalStorage(Context context, String udid) {
        if (!isSdcardExist() || !hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            return null;
        }
        if (VERSION.SDK_INT < 23) {
            return writeDeviceIdtoSd(udid);
        }
        if (!hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") || !hasPermission(context, "android.permission.READ_EXTERNAL_STORAGE")) {
            return null;
        }
        return writeDeviceIdtoSd(udid);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a6 A[SYNTHETIC, Splitter:B:36:0x00a6] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00af A[SYNTHETIC, Splitter:B:41:0x00af] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String writeDeviceIdtoSd(java.lang.String r10) {
        /*
            r7 = 0
            java.lang.String r6 = getExternalSdDataPath()
            boolean r8 = p005cn.jpush.android.util.StringUtils.isEmpty(r6)
            if (r8 == 0) goto L_0x0016
            java.lang.String r8 = "AndroidUtil"
            java.lang.String r9 = "get sdcard data path fial"
            p005cn.jpush.android.util.Logger.m1420e(r8, r9)
            r10 = r7
        L_0x0015:
            return r10
        L_0x0016:
            java.io.File r5 = new java.io.File
            r5.<init>(r6)
            boolean r8 = r5.exists()
            if (r8 != 0) goto L_0x0024
            r5.mkdir()     // Catch:{ Exception -> 0x0039 }
        L_0x0024:
            java.lang.String r0 = getDeviceidFilePath()
            boolean r8 = p005cn.jpush.android.util.StringUtils.isEmpty(r0)
            if (r8 == 0) goto L_0x0044
            java.lang.String r8 = "AndroidUtil"
            java.lang.String r9 = "get device id  sd card file path fail"
            p005cn.jpush.android.util.Logger.m1422ee(r8, r9)
            r10 = r7
            goto L_0x0015
        L_0x0039:
            r1 = move-exception
            java.lang.String r8 = "AndroidUtil"
            java.lang.String r9 = "mkdir in sdcard error"
            p005cn.jpush.android.util.Logger.m1421e(r8, r9, r1)
            goto L_0x0024
        L_0x0044:
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.StringBuilder r8 = r8.append(r6)
            java.lang.String r9 = ".push_deviceid"
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r2.<init>(r8)
            boolean r8 = r2.exists()
            if (r8 == 0) goto L_0x0066
            r2.delete()     // Catch:{ SecurityException -> 0x008a }
        L_0x0066:
            r2.createNewFile()     // Catch:{ IOException -> 0x008d }
            r3 = 0
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x009a }
            r4.<init>(r2)     // Catch:{ IOException -> 0x009a }
            byte[] r8 = r10.getBytes()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
            r4.write(r8)     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
            r4.flush()     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
            java.lang.String r8 = "AndroidUtil"
            java.lang.String r9 = "Saved deviceid into file"
            p005cn.jpush.android.util.Logger.m1424i(r8, r9)     // Catch:{ IOException -> 0x00ba, all -> 0x00b7 }
            if (r4 == 0) goto L_0x0015
            r4.close()     // Catch:{ IOException -> 0x0088 }
            goto L_0x0015
        L_0x0088:
            r7 = move-exception
            goto L_0x0015
        L_0x008a:
            r1 = move-exception
            r10 = r7
            goto L_0x0015
        L_0x008d:
            r1 = move-exception
            java.lang.String r8 = "AndroidUtil"
            java.lang.String r9 = "Create file in sdcard error"
            p005cn.jpush.android.util.Logger.m1421e(r8, r9, r1)
            r10 = r7
            goto L_0x0015
        L_0x009a:
            r1 = move-exception
        L_0x009b:
            java.lang.String r8 = "AndroidUtil"
            java.lang.String r9 = "write deviceid error"
            p005cn.jpush.android.util.Logger.m1421e(r8, r9, r1)     // Catch:{ all -> 0x00ac }
            if (r3 == 0) goto L_0x00a9
            r3.close()     // Catch:{ IOException -> 0x00b3 }
        L_0x00a9:
            r10 = r7
            goto L_0x0015
        L_0x00ac:
            r7 = move-exception
        L_0x00ad:
            if (r3 == 0) goto L_0x00b2
            r3.close()     // Catch:{ IOException -> 0x00b5 }
        L_0x00b2:
            throw r7
        L_0x00b3:
            r8 = move-exception
            goto L_0x00a9
        L_0x00b5:
            r8 = move-exception
            goto L_0x00b2
        L_0x00b7:
            r7 = move-exception
            r3 = r4
            goto L_0x00ad
        L_0x00ba:
            r1 = move-exception
            r3 = r4
            goto L_0x009b
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.util.AndroidUtil.writeDeviceIdtoSd(java.lang.String):java.lang.String");
    }

    private static String getSerialNum(Context context) {
        if (VERSION.SDK_INT >= 9) {
            return Build.SERIAL;
        }
        return null;
    }

    public static String getDeviceSysId(Context context) {
        String deviceId = getAndroidId(context);
        if (!StringUtils.isEmpty(deviceId) && isValidImei(deviceId)) {
            return deviceId;
        }
        String deviceId2 = getSerialNum(context);
        if (!StringUtils.isEmpty(deviceId2) && isValidImei(deviceId2)) {
            return deviceId2;
        }
        String deviceId3 = getImei(context, " ");
        if (StringUtils.isEmpty(deviceId3) || !isValidImei(deviceId3)) {
            return UUID.randomUUID().toString();
        }
        return deviceId3;
    }

    public static String getDeviceId(Context context) {
        String str = "";
        String deviceId = Configs.getDeviceId();
        if (!StringUtils.isEmpty(deviceId)) {
            deviceID_Status = DeviceID_Status.DEVICEID_FROM_SHAREPREFS.ordinal();
            return deviceId;
        }
        String deviceId2 = getDeviceIDFromSettings(context, deviceId);
        if (!StringUtils.isEmpty(deviceId2)) {
            deviceID_Status = DeviceID_Status.DEVICEID_FROM_SETTING.ordinal();
            writeDeviceIdToExternalStorage(context, deviceId2);
            Configs.setDeviceId(deviceId2);
            return deviceId2;
        }
        String deviceId3 = getDeviceIdFromExternalStorage(context);
        if (!StringUtils.isEmpty(deviceId3)) {
            deviceID_Status = DeviceID_Status.DEVICEID_FROM_EXTERNALSTORAGE.ordinal();
            writeDeviceIDToSettings(context, deviceId3);
            Configs.setDeviceId(deviceId3);
            return deviceId3;
        }
        String imei = "";
        if (VERSION.SDK_INT < 23) {
            imei = getImei(context, deviceId3);
        }
        String android_id = getAndroidId(context);
        String mac_Adress = getWifiMac(context, "");
        String randUUid = UUID.randomUUID().toString();
        String deviceId4 = getMD5String(imei + android_id + mac_Adress + randUUid);
        if (StringUtils.isEmpty(deviceId4)) {
            deviceId4 = randUUid;
        }
        Configs.setDeviceId(deviceId4);
        deviceID_Status = DeviceID_Status.DEVICEID_FROM_NEW.ordinal();
        writeDeviceIDToSettings(context, deviceId4);
        writeDeviceIdToExternalStorage(context, deviceId4);
        return deviceId4;
    }

    public static void saveDeviceIdFromServer(Context context, String deviceId) {
        if (!StringUtils.isEmpty(deviceId)) {
            writeDeviceIDToSettings(context, deviceId);
            writeDeviceIdToExternalStorage(context, deviceId);
            Configs.setDeviceId(deviceId);
        }
    }

    public static void clearDeviceId(Context context) {
        writeDeviceIDToSettings(context, "");
        writeDeviceIdToExternalStorage(context, "");
    }

    public static boolean checkCanStoreDevice(Context context) {
        boolean extrernal;
        boolean perm = hasPermission(context, "android.permission.WRITE_SETTINGS");
        if (!hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") || !isSdcardExist()) {
            extrernal = false;
        } else {
            extrernal = true;
        }
        if (!perm && !extrernal) {
            return false;
        }
        if (extrernal) {
            return true;
        }
        if (perm) {
            return canReadAndWriteSettings(context);
        }
        return true;
    }

    private static String getDeviceIDFromSettings(Context context, String deviceId) {
        if (!hasPermission(context, "android.permission.WRITE_SETTINGS")) {
            return deviceId;
        }
        try {
            return System.getString(context.getContentResolver(), Configs.KEY_DEVICE_ID);
        } catch (Exception e) {
            Logger.m1420e(TAG, "Can not read from settings");
            return deviceId;
        }
    }

    private static String writeDeviceIDToSettings(Context context, String deviceId) {
        if (hasPermission(context, "android.permission.WRITE_SETTINGS")) {
            try {
                if (System.putString(context.getContentResolver(), Configs.KEY_DEVICE_ID, deviceId)) {
                    return deviceId;
                }
            } catch (Exception e) {
                Logger.m1420e(TAG, "Can not write settings");
            }
        }
        return null;
    }

    public static boolean canReadAndWriteSettings(Context paramContext) {
        String str = "";
        try {
            String str2 = System.getString(paramContext.getContentResolver(), "IMEI");
            if (TextUtils.isEmpty(str2)) {
                str2 = getImei(paramContext, "");
            }
            System.putString(paramContext.getContentResolver(), "IMEI", str2);
            Logger.m1424i(TAG, "Can write and read settings");
            return true;
        } catch (Exception e) {
            Logger.m1420e(TAG, "Can not write and read settings");
            return false;
        }
    }

    private static String getOldSavedKey(Context context) {
        return context.getSharedPreferences("PrefsFile", 0).getString("key", null);
    }

    private static String CheckSavedKey(Context context) {
        SharedPreferences settings = context.getSharedPreferences("PrefsFile", 0);
        String key = settings.getString("key", null);
        if (key != null) {
            return key;
        }
        String key2 = UUID.randomUUID().toString();
        Editor editor = settings.edit();
        editor.putString("key", key2);
        editor.commit();
        return key2;
    }

    public static Drawable getDefaultIcon(Context context) {
        Drawable db = null;
        try {
            return context.getPackageManager().getApplicationIcon(context.getPackageName());
        } catch (NameNotFoundException e) {
            Logger.m1421e(TAG, "", e);
            return db;
        }
    }

    public static boolean isSystemInstall(Context contex) {
        String sourceDir = contex.getApplicationInfo().sourceDir;
        if (StringUtils.isEmpty(sourceDir)) {
            Logger.m1420e(TAG, "Unexpected: cannot get pk installed path");
            return false;
        }
        Logger.m1416d(TAG, "Current pk installed path: " + sourceDir);
        if (sourceDir.startsWith(PATH_SYSTEM_INSTALL)) {
            return true;
        }
        if (sourceDir.startsWith(PATH_DATA_INSTALL)) {
            return false;
        }
        Logger.m1424i(TAG, "NOTE: the pk does not installed in system/data. ");
        return false;
    }

    public static boolean isExtendsInstrumentedApp(Context context, String packageName) {
        if (context == null || StringUtils.isEmpty(packageName)) {
            Logger.m1416d(TAG, "Context did not init, return");
            return false;
        }
        ActivityInfo[] list = getActivityList(context, packageName);
        if (list == null) {
            return false;
        }
        int i = 0;
        while (i < list.length) {
            try {
                if (isExtendsInstrumented(Class.forName(list[i].name))) {
                    return true;
                }
                i++;
            } catch (ClassNotFoundException e) {
            }
        }
        return false;
    }

    public static void acquiredWakelock(Context context) {
        try {
            WakeLock wakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, JPush.PKG_NAME + "_JPush");
            wakeLock.setReferenceCounted(false);
            WakelockManager.getInstance().setWakelock(wakeLock);
            if (!WakelockManager.getInstance().getWakelock().isHeld()) {
                WakelockManager.getInstance().getWakelock().acquire();
                WAKELOCK_START_TIME = System.currentTimeMillis();
                Logger.m1428v(TAG, "Acquired Wakelock");
                return;
            }
            Logger.m1428v(TAG, "Wakelock is already held. No need to acquire.");
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Logger.m1416d(TAG, "AndroidUtil acquiredWakelock IllegalStateException errno");
        } catch (Exception e2) {
            e2.printStackTrace();
            Logger.m1416d(TAG, "AndroidUtil acquiredWakelock errno");
        }
    }

    public static void releaseWakelock() {
        try {
            WakeLock wl = WakelockManager.getInstance().getWakelock();
            if (wl == null) {
                return;
            }
            if (wl.isHeld()) {
                try {
                    wl.release();
                    long usage = System.currentTimeMillis() - WAKELOCK_START_TIME;
                    WAKELOCK_START_TIME = 0;
                    Logger.m1428v(TAG, "Released wake lock - milliseconds:" + usage);
                } catch (RuntimeException e) {
                    Logger.m1433w(TAG, "Release wake lock exception", e);
                }
            } else {
                Logger.m1428v(TAG, "Wakelock is not held. No need to release.");
            }
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
            Logger.m1416d(TAG, "AndroidUtil releaseWakelock IllegalStateException errno");
        } catch (Exception e3) {
            e3.printStackTrace();
            Logger.m1416d(TAG, "AndroidUtil releaseWakelock errno");
        }
    }

    public static boolean isMainActivityExtendsInstrumentedActivity(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setPackage(context.getPackageName());
            intent.addCategory("android.intent.category.LAUNCHER");
            ActivityInfo a = context.getPackageManager().resolveActivity(intent, 0).activityInfo;
            if (a != null) {
                return isExtendsInstrumented(Class.forName(a.name));
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isExtendsInstrumented(Class child) {
        if (child == null) {
            return false;
        }
        if (InstrumentedActivity.class.isAssignableFrom(child) || InstrumentedListActivity.class.isAssignableFrom(child)) {
            return true;
        }
        return false;
    }

    private static ActivityInfo[] getActivityList(Context context, String packageName) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, 1).activities;
        } catch (Exception e) {
            Logger.m1420e(TAG, "Get activity list failed");
            return null;
        }
    }

    public static JSONObject bulidReportJson(String type, JSONArray data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        JSONObject js = new JSONObject();
        try {
            js.put("data", data);
            js.put("type", type);
            js.put(JPushReportInterface.ITIME, Configs.getReportTime());
            return js;
        } catch (Exception e) {
            Logger.m1420e(TAG, e.getMessage());
            return null;
        }
    }

    public static JSONObject bulidReportJson(String type, String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        JSONObject js = new JSONObject();
        try {
            js.put("data", data);
            js.put("type", type);
            js.put(JPushReportInterface.ITIME, Configs.getReportTime());
            return js;
        } catch (Exception e) {
            Logger.m1420e(TAG, e.getMessage());
            return null;
        }
    }

    public static boolean isValidCN(String s) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        return Pattern.compile("^[一-龥]{0,}$").matcher(s).matches();
    }

    public static JSONObject Map2Json(Map<String, String> map) {
        JSONObject holder = new JSONObject();
        for (Entry<String, String> pairs : map.entrySet()) {
            try {
                holder.put((String) pairs.getKey(), (String) pairs.getValue());
            } catch (JSONException e) {
                Logger.m1421e(TAG, "There was an error packaging JSON", e);
            }
        }
        return holder;
    }

    public static boolean isInValidPushTime(Context context) {
        try {
            if (!Configs.isNotificationEnabled(context)) {
                Logger.m1426ii(TAG, "Notification was disabled by JPushInterface.setPushTime !");
                return false;
            }
            String pushTime = Configs.getPushTime(context);
            if (StringUtils.isEmpty(pushTime)) {
                Logger.m1424i(TAG, "no time limited");
                return true;
            }
            Logger.m1424i(TAG, "push time is ：" + pushTime);
            String[] times = pushTime.split("_");
            String time0 = times[0];
            String time1 = times[1];
            char[] setDayOfWeeks = time0.toCharArray();
            String[] setTimeOfDays = time1.split("\\^");
            Calendar calendar = Calendar.getInstance();
            int nowDayOfWeek = calendar.get(7);
            int nowTimeOfDay = calendar.get(11);
            int length = setDayOfWeeks.length;
            for (int i = 0; i < length; i++) {
                if (nowDayOfWeek == Integer.valueOf(String.valueOf(setDayOfWeeks[i])).intValue() + 1) {
                    int beginHour = Integer.valueOf(setTimeOfDays[0]).intValue();
                    int endHour = Integer.valueOf(setTimeOfDays[1]).intValue();
                    if (nowTimeOfDay >= beginHour && nowTimeOfDay <= endHour) {
                        return true;
                    }
                }
            }
            Logger.m1426ii(TAG, "Current time is out of the push time - " + pushTime);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isInSilencePushTime(Context context) {
        String pushTime = Configs.getSilencePushTime(context);
        if (TextUtils.isEmpty(pushTime)) {
            return false;
        }
        try {
            JSONObject js = new JSONObject(pushTime);
            int startHour = js.optInt(JPushConstants.START_HOUR, -1);
            int startMins = js.optInt(JPushConstants.START_MINS, -1);
            int endHour = js.optInt(JPushConstants.END_HOUR, -1);
            int endMins = js.optInt(JPushConstants.END_MINS, -1);
            if (startHour < 0 || startMins < 0 || endHour < 0 || endMins < 0 || startMins > 59 || endMins > 59 || endHour > 23 || startHour > 23) {
                return false;
            }
            Calendar calendar = Calendar.getInstance();
            int nowHour = calendar.get(11);
            int nowMins = calendar.get(12);
            if (startHour < endHour) {
                if ((nowHour <= startHour || nowHour >= endHour) && ((nowHour != startHour || nowMins < startMins) && (nowHour != endHour || nowMins > endMins))) {
                    return false;
                }
            } else if (startHour == endHour) {
                if (nowHour != startHour || nowMins < startMins || nowMins > endMins) {
                    return false;
                }
            } else if (startHour <= endHour) {
                return false;
            } else {
                if ((nowHour <= startHour || nowHour > 23) && ((nowHour < 0 || nowHour >= endHour) && ((nowHour != startHour || nowMins < startMins) && (nowHour != endHour || nowMins > endMins)))) {
                    return false;
                }
            }
            Logger.m1426ii(TAG, "Current time is in the range of silence time - " + startHour + ":" + startMins + " ~ " + endHour + ":" + endMins);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static int byteToInt(byte x) {
        return x & 255;
    }

    public static int parseCommandFromPkgHead(byte[] mRecvBuffer) {
        if (mRecvBuffer == null || mRecvBuffer.length < 20) {
            return -1;
        }
        int packageLen = 0;
        for (int i = 0; i < 2; i++) {
            packageLen = (packageLen << 8) + (mRecvBuffer[i] & 255);
        }
        int byteToInt = byteToInt(mRecvBuffer[3]);
        int wRID = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            wRID = (wRID << 8) + (mRecvBuffer[i2 + 4] & 255);
        }
        return byteToInt;
    }

    public static int getPkgLen(byte[] mRecvBuffer) {
        if (mRecvBuffer == null || mRecvBuffer.length < 6) {
            return 0;
        }
        int packageLen = 0;
        for (int i = 0; i < 2; i++) {
            packageLen = (packageLen << 8) + (mRecvBuffer[i] & 255);
        }
        return packageLen;
    }

    public static void sendNetworkChangedToIM(Context context, boolean isConnected) {
        Logger.m1418dd(TAG, "Action - sendNetworkChangedToIM");
        try {
            Bundle bundle = new Bundle();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(ImResponseHelper.EXTRA_NETWORK_CONNECTED, isConnected);
            bundle.putString(ImResponseHelper.EXTRA_PUSH2IM_DATA, jsonObject.toString());
            sendBroadcast(context, ImResponseHelper.ACTION_IM_RESPONSE, bundle);
        } catch (JSONException e) {
            Logger.m1432w(TAG, "jsonException - " + e.getMessage());
        }
    }

    public static void sendBroadcast(Context context, String action, String extrasValue) {
        sendBroadcast(context, action, JPushInterface.EXTRA_EXTRA, extrasValue);
    }

    public static void sendBroadcast(Context context, String action, String key1, String value1) {
        sendBroadcast(context, action, key1, value1, null, null);
    }

    public static void sendBroadcast(Context context, String action, String key1, String value1, String key2, String value2) {
        Bundle bundle = new Bundle();
        if (key1 != null) {
            bundle.putString(key1, value1);
        }
        if (key2 != null) {
            bundle.putString(key2, value2);
        }
        sendBroadcast(context, action, bundle);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [byte[], java.io.Serializable] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r4v0, types: [byte[], java.io.Serializable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void sendBroadcast(android.content.Context r1, java.lang.String r2, java.lang.String r3, byte[] r4) {
        /*
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            if (r3 == 0) goto L_0x000c
            if (r4 == 0) goto L_0x000c
            r0.putSerializable(r3, r4)
        L_0x000c:
            sendBroadcast(r1, r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.util.AndroidUtil.sendBroadcast(android.content.Context, java.lang.String, java.lang.String, byte[]):void");
    }

    public static void sendBroadcast(Context context, String action, Bundle bundle) {
        if (bundle == null) {
            Logger.m1422ee(TAG, "Bundle should not be null for sendBroadcast.");
            return;
        }
        Intent intent = new Intent(action);
        bundle.putString(JPushInterface.EXTRA_APP_KEY, Configs.getAppKey());
        intent.putExtras(bundle);
        String pkgName = context.getPackageName();
        intent.addCategory(pkgName);
        context.sendBroadcast(intent, String.format("%s.permission.JPUSH_MESSAGE", new Object[]{pkgName}));
    }

    public static void sendBroadcastToApp(Context context, Entity entity) {
        try {
            Intent msgIntent = new Intent(JPushInterface.ACTION_MESSAGE_RECEIVED);
            msgIntent.putExtra(JPushInterface.EXTRA_APP_KEY, entity.senderId);
            msgIntent.putExtra(JPushInterface.EXTRA_MESSAGE, entity.message);
            msgIntent.putExtra(JPushInterface.EXTRA_CONTENT_TYPE, entity.contentType);
            msgIntent.putExtra(JPushInterface.EXTRA_TITLE, entity.title);
            msgIntent.putExtra(JPushInterface.EXTRA_EXTRA, entity.extras);
            msgIntent.putExtra(JPushInterface.EXTRA_MSG_ID, entity.messageId);
            if (entity.isRichPush()) {
                msgIntent.putExtra(JPushInterface.EXTRA_RICHPUSH_FILE_PATH, entity.richPushSavedPath);
            }
            msgIntent.addCategory(entity.appId);
            context.sendBroadcast(msgIntent, String.format("%s.permission.JPUSH_MESSAGE", new Object[]{entity.appId}));
            Logger.m1424i(TAG, "Send broadcast to app: " + String.format("%s.permission.JPUSH_MESSAGE", new Object[]{entity.appId}));
            ReportHelper.reportMsgResult(entity.messageId, StatusCode.RESULT_TYPE_NOTIFACTION_SHOW, context);
        } catch (Exception e) {
            Logger.m1420e(TAG, e.getMessage());
        }
    }

    public static boolean checkValidRunning(Context context) {
        return hasValidAppKey() && checkValidManifest(context);
    }

    private static boolean hasValidAppKey() {
        if (!StringUtils.isEmpty(Configs.getAppKey())) {
            return true;
        }
        if (!StringUtils.isEmpty(JPush.APP_KEY)) {
            Configs.setAppKey(JPush.APP_KEY);
            return true;
        }
        Logger.m1434ww(TAG, "Developer should set AppKey in AndroidManifest.xml");
        return false;
    }

    private static boolean checkValidManifest(Context context) {
        Logger.m1418dd(TAG, "action:checkValidManifest");
        if (!hasServiceResolves(context, PushService.class)) {
            Logger.m1422ee(TAG, "AndroidManifest.xml missing required service: " + PushService.class.getCanonicalName());
            return false;
        }
        if (isMultiProcess(context, PushService.class.getCanonicalName())) {
            Logger.m1428v(TAG, "PushService in other process");
            JPush.isMultiProcess = true;
        } else {
            Logger.m1428v(TAG, "PushService in main process");
            JPush.isMultiProcess = false;
        }
        if (!hasServiceIntentFilter(context, JPushConstants.PushService.ACTION_REGISTER, false)) {
            Logger.m1422ee(TAG, "AndroidManifest.xml missing required intent filter for PushService: cn.jpush.android.intent.REGISTER");
            return false;
        } else if (!hasServiceIntentFilter(context, JPushConstants.PushService.ACTION_REPORT, false)) {
            Logger.m1422ee(TAG, "AndroidManifest.xml missing required intent filter : cn.jpush.android.intent.REPORT");
            return false;
        } else {
            if (!hasServiceResolves(context, DaemonService.class)) {
                Logger.m1434ww(TAG, "AndroidManifest.xml missing required service: " + DaemonService.class.getCanonicalName());
                JPush.canLaunchedStoppedService = false;
            } else if (!hasServiceIntentFilter(context, JPushConstants.DaemonService.DAEMON_ACTION, true)) {
                Logger.m1434ww(TAG, "AndroidManifest.xml missing intent filter for DaemonService: cn.jpush.android.intent.DaemonService");
                JPush.canLaunchedStoppedService = false;
            } else {
                JPush.canLaunchedStoppedService = true;
            }
            if (!hasServiceResolves(context, DownloadService.class)) {
                Logger.m1422ee(TAG, "AndroidManifest.xml missing required service: " + DownloadService.class.getCanonicalName());
                return false;
            } else if (!hasReceiverResolves(context, PushReceiver.class)) {
                Logger.m1422ee(TAG, "AndroidManifest.xml missing required receiver: " + PushReceiver.class.getCanonicalName());
                registerPushReceiver(context);
                return true;
            } else {
                if (hasReceiverIntentFilterPackage(context, PushReceiver.class.getCanonicalName(), "android.intent.action.BOOT_COMPLETED")) {
                    Logger.m1434ww(TAG, "PushReceiver should not have intent filter -- android.intent.action.BOOT_COMPLETED, Please remove the intent filter in AndroidManifest.xml");
                }
                if (!hasReceiverIntentFilter(context, JPushInterface.ACTION_NOTIFICATION_RECEIVED_PROXY, true)) {
                    Logger.m1422ee(TAG, "AndroidManifest.xml missing required intent filter for PushReceiver: cn.jpush.android.intent.NOTIFICATION_RECEIVED_PROXY");
                    return false;
                } else if (!hasReceiverResolves(context, AlarmReceiver.class)) {
                    Logger.m1422ee(TAG, "AndroidManifest.xml missing required receiver: " + AlarmReceiver.class.getCanonicalName());
                    return false;
                } else if (!hasActivityResolves(context, p005cn.jpush.android.p006ui.PushActivity.class)) {
                    Logger.m1422ee(TAG, "AndroidManifest.xml missing required activity: " + p005cn.jpush.android.p006ui.PushActivity.class.getCanonicalName());
                    return false;
                } else if (!hasActivityIntentFilter(context, PushActivity.ACTION_JPUSH)) {
                    Logger.m1422ee(TAG, "AndroidManifest.xml missing required intent filter for PushActivity: cn.jpush.android.ui.PushActivity");
                    return false;
                } else {
                    String uaMessagePermission = context.getPackageName() + JPushConstants.PUSH_MESSAGE_PERMISSION_POSTFIX;
                    if (!hasPermissionDefined(context, uaMessagePermission)) {
                        Logger.m1422ee(TAG, "The permission should be defined - " + uaMessagePermission);
                        return false;
                    }
                    REQUIRED_PERMISSIONS.add(uaMessagePermission);
                    Iterator it = REQUIRED_PERMISSIONS.iterator();
                    while (it.hasNext()) {
                        String thePermission = (String) it.next();
                        if (!hasPermission(context.getApplicationContext(), thePermission)) {
                            Logger.m1422ee(TAG, "The permissoin is required - " + thePermission);
                            return false;
                        }
                    }
                    Iterator it2 = OPTIONAL_PERMISSIONS.iterator();
                    while (it2.hasNext()) {
                        String thePermission2 = (String) it2.next();
                        if (!hasPermission(context.getApplicationContext(), thePermission2)) {
                            Logger.m1432w(TAG, "We recommend you add the permission - " + thePermission2);
                        }
                    }
                    return true;
                }
            }
        }
    }

    private static void registerPushReceiver(Context context) {
        Logger.m1424i(TAG, "Do not have PushReceiver, Register it in code");
        if (receiver == null) {
            receiver = new PushReceiver();
        }
        context.registerReceiver(receiver, new IntentFilter("android.intent.action.USER_PRESENT"));
        context.registerReceiver(receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        try {
            IntentFilter intentAdd = new IntentFilter("android.intent.action.PACKAGE_ADDED");
            intentAdd.addDataScheme("package");
            IntentFilter intentRemove = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
            intentRemove.addDataScheme("package");
            IntentFilter notificationIntent = new IntentFilter(JPushInterface.ACTION_NOTIFICATION_RECEIVED_PROXY);
            notificationIntent.setPriority(1000);
            notificationIntent.addCategory(context.getPackageName());
            context.registerReceiver(receiver, intentAdd);
            context.registerReceiver(receiver, intentRemove);
            context.registerReceiver(receiver, notificationIntent);
        } catch (Exception e) {
        }
    }

    public static void unregisterPushReceiver(Context context) {
        if (receiver != null && !hasReceiver(context, PushReceiver.class.getCanonicalName())) {
            try {
                context.unregisterReceiver(receiver);
            } catch (Exception e) {
                Logger.m1420e(TAG, e.getMessage());
            }
        }
    }

    public static String getCpuInfo() {
        StringBuffer sb = new StringBuffer();
        if (new File("/proc/cpuinfo").exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(new File("/proc/cpuinfo")));
                while (true) {
                    try {
                        String line = br.readLine();
                        if (line == null) {
                            break;
                        } else if (line.contains("Processor")) {
                            int position = line.indexOf(":");
                            if (position >= 0 && position < line.length() - 1) {
                                sb.append(line.substring(position + 1).trim());
                            }
                        }
                    } catch (IOException e) {
                        BufferedReader bufferedReader = br;
                    }
                }
                br.close();
                BufferedReader bufferedReader2 = br;
            } catch (IOException e2) {
            }
        }
        return sb.toString();
    }

    public static int getVersionFromString(String version) {
        String[] versions = version.split("\\.");
        return (Integer.parseInt(versions[0]) << 16) + (Integer.parseInt(versions[1]) << 8) + Integer.parseInt(versions[2]);
    }

    public static String getAppKey(Context context) {
        String app_key = JPush.APP_KEY;
        if (!StringUtils.isEmpty(app_key)) {
            return app_key;
        }
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (ai == null || ai.metaData == null) {
                return app_key;
            }
            return ai.metaData.getString(JPush.KEY_APP_KEY);
        } catch (NameNotFoundException e) {
            return app_key;
        }
    }

    public static void showPermanentNotification(Context context, String tickerText, String toastText) {
        if (isDebuggable(context)) {
            Logger.m1416d(TAG, "action:showPermanentNotification");
            Logger.m1432w(TAG, "JPush提示：包名和AppKey不匹配" + "请到 Portal 上获取您的包名和AppKey并更新AndroidManifest相应字段");
        }
    }

    public static void showPermanentNotification(Context context, String tickerText, String activity, int type) {
        Notification notification;
        Intent broadCast = new Intent(context, PushReceiver.class);
        broadCast.setAction(JPushConstants.PushReceiver.INTENT_NOTIFICATION_OPENED_PROXY);
        broadCast.putExtra("debug_notification", true);
        broadCast.putExtra("activity", activity);
        broadCast.putExtra("type", type);
        PendingIntent pending = PendingIntent.getBroadcast(context, 0, broadCast, 134217728);
        NotificationManager nm = (NotificationManager) context.getSystemService("notification");
        int appIconId = -1;
        try {
            appIconId = context.getPackageManager().getPackageInfo(context.getPackageName(), 256).applicationInfo.icon;
        } catch (NameNotFoundException e) {
        }
        if (appIconId < 0) {
            appIconId = 17301586;
        }
        String title = "JPush提示：缺少统计代码";
        String content = "检测到集成 SDK 未添加统计代码。点击查看详情";
        long when = System.currentTimeMillis();
        if (VERSION.SDK_INT >= 11) {
            notification = new Builder(context.getApplicationContext()).setContentTitle(title).setContentText(content).setContentIntent(pending).setSmallIcon(appIconId).setTicker(tickerText).setWhen(when).getNotification();
            notification.flags = 34;
        } else {
            notification = new Notification(appIconId, tickerText, when);
            notification.flags = 34;
            NotificationHelper.methodInvokeNoti(notification, context, title, content, pending);
        }
        if (notification != null) {
            nm.notify(tickerText.hashCode(), notification);
        }
    }

    public static String getCurrentNetType(Context context) {
        String type = "";
        try {
            NetworkInfo info = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (info == null) {
                return "unknown";
            }
            if (info.getType() == 1) {
                return "wifi";
            }
            if (info.getType() != 0) {
                return type;
            }
            int subType = info.getSubtype();
            if (subType == 4 || subType == 1 || subType == 2) {
                return "2g";
            }
            if (subType == 3 || subType == 8 || subType == 6 || subType == 5 || subType == 12) {
                return "3g";
            }
            if (subType == 13) {
                return "4g";
            }
            return type;
        } catch (Exception e) {
            e.printStackTrace();
            return type;
        }
    }

    public static String getPhoneIp() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                Enumeration<InetAddress> enumIpAddr = ((NetworkInterface) en.nextElement()).getInetAddresses();
                while (true) {
                    if (enumIpAddr.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                            return inetAddress.getHostAddress().toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
            Logger.m1432w(TAG, "getPhoneIp:");
            e.printStackTrace();
        }
        return "";
    }

    public static String getRadioType(int networkType) {
        String radioType = "unknown";
        if (networkType == 1 || networkType == 2 || networkType == 8) {
            return "gsm";
        }
        if (networkType == 4 || networkType == 7 || networkType == 5 || networkType == 6) {
            return "cdma";
        }
        if (networkType == 13) {
            return "lte";
        }
        return radioType;
    }

    private static int getImeiType(String imei) {
        if (StringUtils.isEmpty(imei)) {
            Logger.m1432w(TAG, "The imei is empty!");
            return 0;
        } else if (Pattern.matches("[0]*", imei)) {
            Logger.m1424i(TAG, "Get imei is all 0 !");
            return 0;
        } else if (Pattern.matches("[0-9]{15}", imei)) {
            Logger.m1424i(TAG, "Get imei ok !");
            return 1;
        } else if (!Pattern.matches("[a-f0-9A-F]{14}", imei)) {
            return 0;
        } else {
            Logger.m1424i(TAG, "Get meid as a imei !");
            return 2;
        }
    }

    public static void checkDeviceInfo(Context context) {
        if (!Configs.isFirstInstallNewVersion()) {
            String imei = getImei(context, "");
            String old_imei = Configs.getMainImei();
            String androidId = getAndroidId(context);
            if (StringUtils.isEmpty(androidId)) {
                androidId = " ";
            }
            String mac = getWifiMac(context, "");
            if (StringUtils.isEmpty(mac)) {
                mac = " ";
            }
            int oldImeiType = getImeiType(old_imei);
            int imeiType = getImeiType(imei);
            if (oldImeiType == 0 || imeiType == 0) {
                if (Configs.isSameDeviceJudgeTwo(androidId, mac)) {
                    return;
                }
            } else if (1 != oldImeiType || 2 != imeiType) {
                if (2 != oldImeiType || 1 != imeiType) {
                    if (oldImeiType == imeiType) {
                        if (imei.equals(old_imei)) {
                            if (Configs.isSameDeviceJudgeOne(imei, androidId)) {
                                return;
                            }
                        } else if (Configs.isSameDeviceJudgeTwo(androidId, mac)) {
                            return;
                        }
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
            Logger.m1416d(TAG, "Clear registered data and device id ");
            Configs.clearRegistered();
            clearDeviceId(context);
        }
    }

    public static List<ComponentName> getAllPushService(Context context) {
        Logger.m1416d(TAG, "action - getAllPushService");
        List<ComponentName> serviceComponent = new ArrayList<>();
        PackageManager pkgManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction(JPushConstants.DaemonService.DAEMON_ACTION);
        List<ResolveInfo> rs = pkgManager.queryIntentServices(intent, 0);
        if (rs == null || rs.size() == 0) {
            return null;
        }
        for (int i = 0; i < rs.size(); i++) {
            ServiceInfo serviceInfo = ((ResolveInfo) rs.get(i)).serviceInfo;
            String name = serviceInfo.name;
            String pkgname = serviceInfo.packageName;
            if (serviceInfo.exported && serviceInfo.enabled && !JPush.PKG_NAME.equals(pkgname)) {
                Logger.m1416d(TAG, "ComponentInfo{" + pkgname + "/" + name + "}");
                serviceComponent.add(new ComponentName(pkgname, name));
            }
        }
        return serviceComponent;
    }

    public static void webSettings(WebSettings webSettings) {
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName(JPushConstants.ENCODING_UTF_8);
        webSettings.setSupportZoom(true);
        webSettings.setCacheMode(2);
        webSettings.setSaveFormData(false);
        webSettings.setSavePassword(false);
    }

    public static boolean isMultiProcess(Context context, String compoentName) {
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context.getPackageName(), compoentName), 128);
            Logger.m1428v(TAG, "process name:" + serviceInfo.processName);
            if (serviceInfo.processName.contains(context.getPackageName() + ":")) {
                return true;
            }
        } catch (NameNotFoundException e) {
        } catch (NullPointerException e2) {
            Logger.m1428v(TAG, "can not find " + compoentName);
        }
        return false;
    }
}
