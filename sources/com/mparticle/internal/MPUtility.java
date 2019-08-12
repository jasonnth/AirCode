package com.mparticle.internal;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.view.Display;
import android.view.WindowManager;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.facebook.appevents.AppEventsConstants;
import com.mparticle.MParticle.LogLevel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

public class MPUtility {
    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
    static final String NO_BLUETOOTH = "none";
    private static String sOpenUDID;

    public static class AndroidAdIdInfo {

        /* renamed from: id */
        public final String f3696id;
        public final boolean isLimitAdTrackingEnabled;

        public AndroidAdIdInfo(String id, boolean isLimitAdTrackingEnabled2) {
            this.f3696id = id;
            this.isLimitAdTrackingEnabled = isLimitAdTrackingEnabled2;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r1v5, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: type inference failed for: r2v17 */
    /* JADX WARNING: type inference failed for: r2v19 */
    /* JADX WARNING: type inference failed for: r1v23 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d8 A[SYNTHETIC, Splitter:B:49:0x00d8] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00dd A[SYNTHETIC, Splitter:B:52:0x00dd] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00f8 A[SYNTHETIC, Splitter:B:62:0x00f8] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00fd A[SYNTHETIC, Splitter:B:65:0x00fd] */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getCpuUsage() {
        /*
            r2 = 0
            r9 = 1
            r4 = 0
            java.lang.String r0 = "unknown"
            int r1 = android.os.Process.myPid()
            java.lang.String r5 = java.lang.String.valueOf(r1)
            r1 = 5
            java.lang.String[] r1 = new java.lang.String[r1]     // Catch:{ IOException -> 0x00c6, all -> 0x00f4 }
            r3 = 0
            java.lang.String r6 = "top"
            r1[r3] = r6     // Catch:{ IOException -> 0x00c6, all -> 0x00f4 }
            r3 = 1
            java.lang.String r6 = "-d"
            r1[r3] = r6     // Catch:{ IOException -> 0x00c6, all -> 0x00f4 }
            r3 = 2
            java.lang.String r6 = "1"
            r1[r3] = r6     // Catch:{ IOException -> 0x00c6, all -> 0x00f4 }
            r3 = 3
            java.lang.String r6 = "-n"
            r1[r3] = r6     // Catch:{ IOException -> 0x00c6, all -> 0x00f4 }
            r3 = 4
            java.lang.String r6 = "1"
            r1[r3] = r6     // Catch:{ IOException -> 0x00c6, all -> 0x00f4 }
            java.lang.ProcessBuilder r3 = new java.lang.ProcessBuilder     // Catch:{ IOException -> 0x00c6, all -> 0x00f4 }
            r6 = 0
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ IOException -> 0x00c6, all -> 0x00f4 }
            r3.<init>(r6)     // Catch:{ IOException -> 0x00c6, all -> 0x00f4 }
            java.lang.ProcessBuilder r1 = r3.command(r1)     // Catch:{ IOException -> 0x00c6, all -> 0x00f4 }
            r3 = 1
            java.lang.ProcessBuilder r1 = r1.redirectErrorStream(r3)     // Catch:{ IOException -> 0x00c6, all -> 0x00f4 }
            java.lang.Process r3 = r1.start()     // Catch:{ IOException -> 0x00c6, all -> 0x00f4 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ IOException -> 0x011d, all -> 0x0114 }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x011d, all -> 0x0114 }
            java.io.InputStream r7 = r3.getInputStream()     // Catch:{ IOException -> 0x011d, all -> 0x0114 }
            r6.<init>(r7)     // Catch:{ IOException -> 0x011d, all -> 0x0114 }
            r1.<init>(r6)     // Catch:{ IOException -> 0x011d, all -> 0x0114 }
        L_0x0052:
            java.lang.String r2 = r1.readLine()     // Catch:{ IOException -> 0x0121, all -> 0x0116 }
            if (r2 == 0) goto L_0x00a8
            boolean r6 = r2.contains(r5)     // Catch:{ IOException -> 0x0121, all -> 0x0116 }
            if (r6 == 0) goto L_0x0052
            java.lang.String r6 = " "
            java.lang.String[] r6 = r2.split(r6)     // Catch:{ IOException -> 0x0121, all -> 0x0116 }
            if (r6 == 0) goto L_0x0052
            r2 = r4
        L_0x0068:
            int r7 = r6.length     // Catch:{ IOException -> 0x0121, all -> 0x0116 }
            if (r2 >= r7) goto L_0x0052
            r7 = r6[r2]     // Catch:{ IOException -> 0x0121, all -> 0x0116 }
            if (r7 == 0) goto L_0x00a5
            r7 = r6[r2]     // Catch:{ IOException -> 0x0121, all -> 0x0116 }
            java.lang.String r8 = "%"
            boolean r7 = r7.contains(r8)     // Catch:{ IOException -> 0x0121, all -> 0x0116 }
            if (r7 == 0) goto L_0x00a5
            r0 = r6[r2]     // Catch:{ IOException -> 0x0121, all -> 0x0116 }
            r2 = 0
            int r5 = r0.length()     // Catch:{ IOException -> 0x0121, all -> 0x0116 }
            int r5 = r5 + -1
            java.lang.String r0 = r0.substring(r2, r5)     // Catch:{ IOException -> 0x0121, all -> 0x0116 }
            if (r1 == 0) goto L_0x008c
            r1.close()     // Catch:{ IOException -> 0x0097 }
        L_0x008c:
            if (r3 == 0) goto L_0x0091
            r3.exitValue()     // Catch:{ IllegalThreadStateException -> 0x0092 }
        L_0x0091:
            return r0
        L_0x0092:
            r1 = move-exception
            r3.destroy()     // Catch:{ IOException -> 0x0097 }
            goto L_0x0091
        L_0x0097:
            r1 = move-exception
            com.mparticle.MParticle$LogLevel r1 = com.mparticle.MParticle.LogLevel.WARNING
            java.lang.String[] r2 = new java.lang.String[r9]
            java.lang.String r3 = "Error computing CPU usage"
            r2[r4] = r3
            com.mparticle.internal.ConfigManager.log(r1, r2)
            goto L_0x0091
        L_0x00a5:
            int r2 = r2 + 1
            goto L_0x0068
        L_0x00a8:
            if (r1 == 0) goto L_0x00ad
            r1.close()     // Catch:{ IOException -> 0x00b8 }
        L_0x00ad:
            if (r3 == 0) goto L_0x0091
            r3.exitValue()     // Catch:{ IllegalThreadStateException -> 0x00b3 }
            goto L_0x0091
        L_0x00b3:
            r1 = move-exception
            r3.destroy()     // Catch:{ IOException -> 0x00b8 }
            goto L_0x0091
        L_0x00b8:
            r1 = move-exception
            com.mparticle.MParticle$LogLevel r1 = com.mparticle.MParticle.LogLevel.WARNING
            java.lang.String[] r2 = new java.lang.String[r9]
            java.lang.String r3 = "Error computing CPU usage"
            r2[r4] = r3
            com.mparticle.internal.ConfigManager.log(r1, r2)
            goto L_0x0091
        L_0x00c6:
            r1 = move-exception
            r1 = r2
        L_0x00c8:
            com.mparticle.MParticle$LogLevel r3 = com.mparticle.MParticle.LogLevel.WARNING     // Catch:{ all -> 0x0119 }
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ all -> 0x0119 }
            r6 = 0
            java.lang.String r7 = "Error computing CPU usage"
            r5[r6] = r7     // Catch:{ all -> 0x0119 }
            com.mparticle.internal.ConfigManager.log(r3, r5)     // Catch:{ all -> 0x0119 }
            if (r1 == 0) goto L_0x00db
            r1.close()     // Catch:{ IOException -> 0x00e6 }
        L_0x00db:
            if (r2 == 0) goto L_0x0091
            r2.exitValue()     // Catch:{ IllegalThreadStateException -> 0x00e1 }
            goto L_0x0091
        L_0x00e1:
            r1 = move-exception
            r2.destroy()     // Catch:{ IOException -> 0x00e6 }
            goto L_0x0091
        L_0x00e6:
            r1 = move-exception
            com.mparticle.MParticle$LogLevel r1 = com.mparticle.MParticle.LogLevel.WARNING
            java.lang.String[] r2 = new java.lang.String[r9]
            java.lang.String r3 = "Error computing CPU usage"
            r2[r4] = r3
            com.mparticle.internal.ConfigManager.log(r1, r2)
            goto L_0x0091
        L_0x00f4:
            r0 = move-exception
            r3 = r2
        L_0x00f6:
            if (r2 == 0) goto L_0x00fb
            r2.close()     // Catch:{ IOException -> 0x0106 }
        L_0x00fb:
            if (r3 == 0) goto L_0x0100
            r3.exitValue()     // Catch:{ IllegalThreadStateException -> 0x0101 }
        L_0x0100:
            throw r0
        L_0x0101:
            r1 = move-exception
            r3.destroy()     // Catch:{ IOException -> 0x0106 }
            goto L_0x0100
        L_0x0106:
            r1 = move-exception
            com.mparticle.MParticle$LogLevel r1 = com.mparticle.MParticle.LogLevel.WARNING
            java.lang.String[] r2 = new java.lang.String[r9]
            java.lang.String r3 = "Error computing CPU usage"
            r2[r4] = r3
            com.mparticle.internal.ConfigManager.log(r1, r2)
            goto L_0x0100
        L_0x0114:
            r0 = move-exception
            goto L_0x00f6
        L_0x0116:
            r0 = move-exception
            r2 = r1
            goto L_0x00f6
        L_0x0119:
            r0 = move-exception
            r3 = r2
            r2 = r1
            goto L_0x00f6
        L_0x011d:
            r1 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x00c8
        L_0x0121:
            r2 = move-exception
            r2 = r3
            goto L_0x00c8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mparticle.internal.MPUtility.getCpuUsage():java.lang.String");
    }

    public static long getAvailableMemory(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static boolean isSystemMemoryLow(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.lowMemory;
    }

    public static long getSystemMemoryThreshold(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.threshold;
    }

    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    public static AndroidAdIdInfo getGoogleAdIdInfo(Context context) {
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{context});
            return new AndroidAdIdInfo((String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]), ((Boolean) invoke.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(invoke, new Object[0])).booleanValue());
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isInDaylightSavings() {
        return Boolean.valueOf(TimeZone.getDefault().inDaylightTime(new Date())).booleanValue();
    }

    public static String getGpsEnabled(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
            return Boolean.toString(((LocationManager) context.getSystemService("location")).isProviderEnabled("gps"));
        }
        return null;
    }

    public static long getAvailableInternalDisk() {
        return getDiskSpace(Environment.getDataDirectory());
    }

    public static long getAvailableExternalDisk() {
        return getDiskSpace(Environment.getExternalStorageDirectory());
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            return "unknown";
        }
    }

    public static String hmacSha256Encode(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        Mac instance = Mac.getInstance("HmacSHA256");
        instance.init(new SecretKeySpec(key.getBytes("utf-8"), "HmacSHA256"));
        return asHex(instance.doFinal(data.getBytes("utf-8")));
    }

    private static String asHex(byte[] buf) {
        char[] cArr = new char[(buf.length * 2)];
        for (int i = 0; i < buf.length; i++) {
            cArr[i * 2] = HEX_CHARS[(buf[i] & 240) >>> 4];
            cArr[(i * 2) + 1] = HEX_CHARS[buf[i] & 15];
        }
        return new String(cArr);
    }

    public static JSONObject getJsonResponse(HttpURLConnection connection) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine + 10);
                } else {
                    bufferedReader.close();
                    return new JSONObject(sb.toString());
                }
            }
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public static long getDiskSpace(File path) {
        long j = -1;
        StatFs statFs = new StatFs(path.getPath());
        if (VERSION.SDK_INT > 17) {
            j = C4605c.m2231a(statFs);
        }
        if (j == 0) {
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        }
        return j;
    }

    public static long millitime() {
        return TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    public static String getAndroidID(Context context) {
        return Secure.getString(context.getContentResolver(), JPushReportInterface.ANDROID_ID);
    }

    public static String getTimeZone() {
        return TimeZone.getDefault().getDisplayName(false, 0);
    }

    public static int getOrientation(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay.getWidth() == defaultDisplay.getHeight()) {
            return 3;
        }
        if (defaultDisplay.getWidth() < defaultDisplay.getHeight()) {
            return 1;
        }
        return 2;
    }

    public static long getTotalMemory(Context context) {
        if (VERSION.SDK_INT > 15) {
            return getTotalMemoryJB(context);
        }
        return getTotalMemoryPreJB();
    }

    @TargetApi(16)
    public static long getTotalMemoryJB(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem;
    }

    public static long getTotalMemoryPreJB() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            long intValue = (long) (Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue() * 1024);
            bufferedReader.close();
            return intValue;
        } catch (IOException e) {
            return -1;
        }
    }

    public static String getOpenUDID(Context context) {
        if (sOpenUDID == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0);
            sOpenUDID = sharedPreferences.getString("mp::openudid", null);
            if (sOpenUDID == null) {
                sOpenUDID = getAndroidID(context);
                if (sOpenUDID == null) {
                    sOpenUDID = getGeneratedUdid();
                }
                Editor edit = sharedPreferences.edit();
                edit.putString("mp::openudid", sOpenUDID);
                edit.apply();
            }
        }
        return sOpenUDID;
    }

    public static String getRampUdid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0);
        String string = sharedPreferences.getString("mp::rampudid", null);
        if (string != null) {
            return string;
        }
        String generatedUdid = getGeneratedUdid();
        Editor edit = sharedPreferences.edit();
        edit.putString("mp::rampudid", generatedUdid);
        edit.apply();
        return generatedUdid;
    }

    static String getGeneratedUdid() {
        return new BigInteger(64, new SecureRandom()).toString(16);
    }

    static String getBuildUUID(String versionCode) {
        if (versionCode == null) {
            versionCode = "unknown";
        }
        return UUID.nameUUIDFromBytes(versionCode.getBytes()).toString();
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static boolean hasNfc(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.nfc");
    }

    public static String getBluetoothVersion(Context context) {
        String str = NO_BLUETOOTH;
        if (VERSION.SDK_INT >= 18 && context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            return "ble";
        }
        if (context.getPackageManager().hasSystemFeature("android.hardware.bluetooth")) {
            return "classic";
        }
        return str;
    }

    public static boolean isPhoneRooted() {
        String str = Build.TAGS;
        if (str != null && str.contains("test-keys")) {
            return true;
        }
        String[] strArr = {"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (new File(strArr[i] + "su").exists()) {
                return true;
            }
        }
        return false;
    }

    public static int mpHash(String input) {
        int i = 0;
        if (!(input == null || input.length() == 0)) {
            char[] charArray = input.toLowerCase().toCharArray();
            int i2 = 0;
            while (i2 < charArray.length) {
                int i3 = charArray[i2] + ((i << 5) - i);
                i2++;
                i = i3;
            }
        }
        return i;
    }

    public static boolean hasTelephony(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    public static boolean isBluetoothEnabled(Context context) {
        if (checkPermission(context, "android.permission.BLUETOOTH")) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null) {
                return defaultAdapter.isEnabled();
            }
        }
        return false;
    }

    public static boolean checkPermission(Context context, String permission) {
        return context.checkCallingOrSelfPermission(permission) == 0;
    }

    public static boolean isGmsAdIdAvailable() {
        if (VERSION.SDK_INT <= 8) {
            return false;
        }
        try {
            Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isSupportLibAvailable() {
        try {
            Class.forName("android.support.v4.app.FragmentActivity");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isInstanceIdAvailable() {
        try {
            Class.forName("com.google.android.gms.iid.InstanceID");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static BigInteger hashFnv1A(byte[] data) {
        BigInteger bigInteger = new BigInteger("cbf29ce484222325", 16);
        BigInteger bigInteger2 = new BigInteger("100000001b3", 16);
        BigInteger pow = new BigInteger("2").pow(64);
        for (byte b : data) {
            bigInteger = bigInteger.xor(BigInteger.valueOf((long) (b & 255))).multiply(bigInteger2).mod(pow);
        }
        return bigInteger;
    }

    public static boolean isServiceAvailable(Context context, Class<?> service) {
        if (context.getPackageManager().queryIntentServices(new Intent(context, service), 65536).size() > 0) {
            return true;
        }
        return false;
    }

    public static JSONObject wrapExtras(Bundle extras) {
        if (extras == null || extras.isEmpty()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : extras.keySet()) {
            Bundle bundle = extras.getBundle(str);
            if (bundle != null) {
                try {
                    jSONObject.put(str, wrapExtras(bundle));
                } catch (JSONException e) {
                }
            } else {
                Object obj = extras.get(str);
                if (obj != null) {
                    String obj2 = obj.toString();
                    if (obj2.length() < 500) {
                        try {
                            jSONObject.put(str, obj2);
                        } catch (JSONException e2) {
                        }
                    }
                }
            }
        }
        return jSONObject;
    }

    public static boolean isAppDebuggable(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static JSONObject enforceAttributeConstraints(Map<String, String> attributes) {
        if (attributes == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : attributes.entrySet()) {
            setCheckedAttribute(jSONObject, (String) entry.getKey(), (String) entry.getValue(), false, false);
        }
        return jSONObject;
    }

    public static Boolean setCheckedAttribute(JSONObject attributes, String key, Object value, boolean increment, boolean userAttribute) {
        return setCheckedAttribute(attributes, key, value, Boolean.valueOf(false), increment, userAttribute);
    }

    public static Boolean setCheckedAttribute(JSONObject attributes, String key, Object value, Boolean caseInsensitive, boolean increment, boolean userAttribute) {
        if (attributes == null || key == null) {
            return Boolean.valueOf(false);
        }
        try {
            if (caseInsensitive.booleanValue()) {
                key = findCaseInsensitiveKey(attributes, key);
            }
            if (100 != attributes.length() || attributes.has(key)) {
                if (value != null) {
                    String obj = value.toString();
                    if ((userAttribute && obj.length() > 4096) || (!userAttribute && obj.length() > 256)) {
                        ConfigManager.log(LogLevel.ERROR, "Attribute value length exceeds limit. Discarding attribute: " + key);
                        return Boolean.valueOf(false);
                    }
                }
                if (key.length() > 256) {
                    ConfigManager.log(LogLevel.ERROR, "Attribute name length exceeds limit. Discarding attribute: " + key);
                    return Boolean.valueOf(false);
                }
                if (value == null) {
                    value = JSONObject.NULL;
                }
                if (increment) {
                    value = Integer.toString(Integer.parseInt(attributes.optString(key, AppEventsConstants.EVENT_PARAM_VALUE_NO)) + ((Integer) value).intValue());
                }
                attributes.put(key, value);
                return Boolean.valueOf(true);
            }
            ConfigManager.log(LogLevel.ERROR, "Attribute count exceeds limit. Discarding attribute: " + key);
            return Boolean.valueOf(false);
        } catch (JSONException e) {
            ConfigManager.log(LogLevel.ERROR, "JSON error processing attributes. Discarding attribute: " + key);
            return Boolean.valueOf(false);
        } catch (NumberFormatException e2) {
            ConfigManager.log(LogLevel.ERROR, "Attempted to increment a key that could not be parsed as an integer: " + key);
            return Boolean.valueOf(false);
        } catch (Exception e3) {
            ConfigManager.log(LogLevel.ERROR, "Failed to add attribute: " + e3.getMessage());
            return Boolean.valueOf(false);
        }
    }

    public static String findCaseInsensitiveKey(JSONObject jsonObject, String key) {
        Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (str.equalsIgnoreCase(key)) {
                return str;
            }
        }
        return key;
    }

    public static long generateMpid() {
        long longValue;
        do {
            longValue = hashFnv1A(UUID.randomUUID().toString().getBytes()).longValue();
        } while (longValue == 0);
        return longValue;
    }
}
