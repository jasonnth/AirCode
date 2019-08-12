package com.facebook.accountkit.internal;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.StatFs;
import android.support.p000v4.content.PermissionChecker;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.Patterns;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class Utility {
    private static final ScheduledThreadPoolExecutor BACKGROUND_EXECUTOR = new ScheduledThreadPoolExecutor(1);
    private static final String EXTRA_APP_EVENTS_INFO_FORMAT_VERSION = "a2";
    private static final String HASH_ALGORITHM_SHA1 = "SHA-1";
    private static final String NO_CARRIER = "NoCarrier";
    private static final int REFRESH_TIME_FOR_EXTENDED_DEVICE_INFO_MILLIS = 1800000;
    private static final String TAG = Utility.class.getName();
    private static long availableExternalStorageGB = -1;
    private static String carrierName = NO_CARRIER;
    private static String deviceTimezone = "";
    private static int numCPUCores = 0;
    private static long timestampOfLastCheck = -1;
    private static long totalExternalStorageGB = -1;

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    static ScheduledThreadPoolExecutor getBackgroundExecutor() {
        return BACKGROUND_EXECUTOR;
    }

    public static Executor getThreadPoolExecutor() {
        return AccountKit.getExecutor();
    }

    static boolean isDebuggable(Context context) {
        try {
            return (context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.flags & 2) != 0;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    static Object getStringPropertyAsJSON(JSONObject jsonObject, String key) throws JSONException {
        Object value = jsonObject.opt(key);
        if (value == null || !(value instanceof String)) {
            return value;
        }
        return new JSONTokener((String) value).nextValue();
    }

    public static boolean hasReceiveSmsPermissions(Context context) {
        return hasPermission(context, "android.permission.RECEIVE_SMS");
    }

    static boolean hasReadPhoneStatePermissions(Context context) {
        return hasPermission(context, "android.permission.READ_PHONE_STATE");
    }

    static boolean hasGetAccountsPermissions(Context context) {
        return hasPermission(context, "android.permission.GET_ACCOUNTS");
    }

    private static boolean hasPermission(Context context, String permission) {
        try {
            if (PermissionChecker.checkCallingOrSelfPermission(context, permission) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<String> getDeviceEmailsIfAvailable(Context context) {
        Account[] accounts;
        if (!hasGetAccountsPermissions(context)) {
            return null;
        }
        List<String> emails = new ArrayList<>();
        for (Account account : AccountManager.get(context).getAccounts()) {
            if (!isNullOrEmpty(account.name) && Patterns.EMAIL_ADDRESS.matcher(account.name).matches() && !emails.contains(account.name)) {
                emails.add(account.name);
            }
        }
        return emails;
    }

    @SuppressLint({"HardwareIds"})
    public static String readPhoneNumberIfAvailable(Context context, String countryCode) {
        if (hasReadPhoneStatePermissions(context)) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return cleanUpPhoneNumberIfPossible(telephonyManager.getLine1Number(), countryCode);
            }
        }
        return null;
    }

    public static String cleanPhoneNumberString(String input) {
        if (isNullOrEmpty(input)) {
            return "";
        }
        return input.replaceAll("[^\\d]", "");
    }

    static String cleanUpPhoneNumberIfPossible(String phoneNumber, String countryCode) {
        if (isNullOrEmpty(phoneNumber) || isNullOrEmpty(countryCode)) {
            return phoneNumber;
        }
        return phoneNumber.replace("+", "").replaceFirst("^" + countryCode, "");
    }

    static void putNonNullString(Bundle bundle, String key, String value) {
        if (bundle != null && key != null && value != null) {
            bundle.putString(key, value);
        }
    }

    static String readStreamToString(InputStream inputStream) throws IOException {
        InputStreamReader reader;
        BufferedInputStream bufferedInputStream = null;
        InputStreamReader reader2 = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream);
            try {
                reader = new InputStreamReader(bufferedInputStream2);
            } catch (Throwable th) {
                th = th;
                bufferedInputStream = bufferedInputStream2;
                closeQuietly(bufferedInputStream);
                closeQuietly(reader2);
                throw th;
            }
            try {
                StringBuilder stringBuilder = new StringBuilder();
                char[] buffer = new char[2048];
                while (true) {
                    int n = reader.read(buffer);
                    if (n != -1) {
                        stringBuilder.append(buffer, 0, n);
                    } else {
                        String sb = stringBuilder.toString();
                        closeQuietly(bufferedInputStream2);
                        closeQuietly(reader);
                        return sb;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                reader2 = reader;
                bufferedInputStream = bufferedInputStream2;
                closeQuietly(bufferedInputStream);
                closeQuietly(reader2);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            closeQuietly(bufferedInputStream);
            closeQuietly(reader2);
            throw th;
        }
    }

    static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static String getCurrentCountry(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
            String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) {
                return simCountry.toLowerCase(Locale.US);
            }
            if (tm.getPhoneType() != 2) {
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) {
                    return networkCountry.toLowerCase(Locale.US);
                }
            }
            return null;
        } catch (Exception e) {
        }
    }

    static void disconnectQuietly(URLConnection connection) {
        if (connection instanceof HttpURLConnection) {
            ((HttpURLConnection) connection).disconnect();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int copyAndCloseInputStream(java.io.InputStream r6, java.io.OutputStream r7) throws java.io.IOException {
        /*
            r1 = 0
            r4 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0023 }
            r2.<init>(r6)     // Catch:{ all -> 0x0023 }
            r5 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r5]     // Catch:{ all -> 0x002f }
        L_0x000b:
            int r3 = r2.read(r0)     // Catch:{ all -> 0x002f }
            r5 = -1
            if (r3 == r5) goto L_0x0018
            r5 = 0
            r7.write(r0, r5, r3)     // Catch:{ all -> 0x002f }
            int r4 = r4 + r3
            goto L_0x000b
        L_0x0018:
            if (r2 == 0) goto L_0x001d
            r2.close()
        L_0x001d:
            if (r6 == 0) goto L_0x0022
            r6.close()
        L_0x0022:
            return r4
        L_0x0023:
            r5 = move-exception
        L_0x0024:
            if (r1 == 0) goto L_0x0029
            r1.close()
        L_0x0029:
            if (r6 == 0) goto L_0x002e
            r6.close()
        L_0x002e:
            throw r5
        L_0x002f:
            r5 = move-exception
            r1 = r2
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.Utility.copyAndCloseInputStream(java.io.InputStream, java.io.OutputStream):int");
    }

    static boolean notEquals(Object o1, Object o2) {
        return o1 == null || !o1.equals(o2);
    }

    public static <T> boolean areObjectsEqual(T a, T b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    public static int getHashCode(Object object) {
        if (object == null) {
            return 0;
        }
        return object.hashCode();
    }

    static String sha1hash(byte[] bytes) {
        try {
            return hashBytes(MessageDigest.getInstance(HASH_ALGORITHM_SHA1), bytes);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static String hashBytes(MessageDigest hash, byte[] bytes) {
        hash.update(bytes);
        byte[] digest = hash.digest();
        StringBuilder builder = new StringBuilder();
        for (byte b : digest) {
            builder.append(Integer.toHexString((b >> 4) & 15));
            builder.append(Integer.toHexString(b & 15));
        }
        return builder.toString();
    }

    static String getMetadataApplicationId() {
        return AccountKit.getApplicationId();
    }

    static void logd(String tag, Exception e) {
        if (tag != null && e != null) {
            Log.d(tag, e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    static void logd(String tag, String msg, Throwable t) {
        if (!isNullOrEmpty(tag)) {
            Log.d(tag, msg, t);
        }
    }

    static void assertUIThread() {
        if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            Log.w(TAG, "This method should be called from the UI thread");
        }
    }

    static Pair<AccountKitError, InternalAccountKitError> createErrorFromServerError(AccountKitRequestError graphError) {
        AccountKitError error;
        int errorCode = graphError.getErrorCode();
        if (graphError.getSubErrorCode() == 1550001) {
            errorCode = InternalAccountKitError.INVALID_PHONE_NUMBER;
        }
        InternalAccountKitError internalError = new InternalAccountKitError(errorCode, graphError.getErrorMessage(), graphError.getUserErrorMessage());
        switch (graphError.getErrorCode()) {
            case 100:
                error = new AccountKitError(Type.ARGUMENT_ERROR, internalError);
                break;
            case 101:
                error = new AccountKitError(Type.NETWORK_CONNECTION_ERROR, internalError);
                break;
            case InternalAccountKitError.INVALID_CONFIRMATION_CODE /*15003*/:
                error = new AccountKitError(Type.ARGUMENT_ERROR, internalError);
                break;
            case InternalAccountKitError.LOGIN_REQUEST_EXPIRED /*1948001*/:
                error = new AccountKitError(Type.LOGIN_INVALIDATED, internalError);
                break;
            case InternalAccountKitError.INVALID_CREDENTIALS_OR_LOGIN_REQUEST /*1948002*/:
                error = new AccountKitError(Type.ARGUMENT_ERROR, internalError);
                break;
            case InternalAccountKitError.TOO_MANY_ATTEMPTS /*1948003*/:
                error = new AccountKitError(Type.SERVER_ERROR, internalError);
                break;
            default:
                error = new AccountKitError(Type.SERVER_ERROR, internalError);
                break;
        }
        return new Pair<>(error, internalError);
    }

    static boolean isConfirmationCodeRetryable(InternalAccountKitError internalAccountKitError) {
        return internalAccountKitError != null && internalAccountKitError.getCode() == 15003;
    }

    static void setAppEventAttributionParameters(JSONObject params, String anonymousAppDeviceGUID) throws JSONException {
        params.put("anon_id", anonymousAppDeviceGUID);
    }

    static void setAppEventExtendedDeviceInfoParameters(JSONObject params, Context appContext) throws JSONException {
        Locale locale;
        JSONArray extraInfoArray = new JSONArray();
        extraInfoArray.put(EXTRA_APP_EVENTS_INFO_FORMAT_VERSION);
        refreshPeriodicExtendedDeviceInfo(appContext);
        String pkgName = appContext.getPackageName();
        int versionCode = -1;
        String versionName = "";
        try {
            PackageInfo pi = appContext.getPackageManager().getPackageInfo(pkgName, 0);
            versionCode = pi.versionCode;
            versionName = pi.versionName;
        } catch (NameNotFoundException e) {
        }
        extraInfoArray.put(pkgName);
        extraInfoArray.put(versionCode);
        extraInfoArray.put(versionName);
        extraInfoArray.put(VERSION.RELEASE);
        extraInfoArray.put(Build.MODEL);
        try {
            locale = appContext.getResources().getConfiguration().locale;
        } catch (Exception e2) {
            locale = Locale.getDefault();
        }
        extraInfoArray.put(locale.getLanguage() + "_" + locale.getCountry());
        extraInfoArray.put(deviceTimezone);
        extraInfoArray.put(carrierName);
        int width = 0;
        int height = 0;
        double density = 0.0d;
        try {
            WindowManager wm = (WindowManager) appContext.getSystemService("window");
            if (wm != null) {
                Display display = wm.getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                display.getMetrics(displayMetrics);
                width = displayMetrics.widthPixels;
                height = displayMetrics.heightPixels;
                density = (double) displayMetrics.density;
            }
        } catch (Exception e3) {
        }
        extraInfoArray.put(width);
        extraInfoArray.put(height);
        extraInfoArray.put(String.format(Locale.ENGLISH, "%.2f", new Object[]{Double.valueOf(density)}));
        extraInfoArray.put(refreshBestGuessNumberOfCPUCores());
        extraInfoArray.put(totalExternalStorageGB);
        extraInfoArray.put(availableExternalStorageGB);
        params.put("extinfo", extraInfoArray.toString());
    }

    public static String getRedirectURL() {
        return "ak" + AccountKit.getApplicationId() + "://authorize";
    }

    private static int refreshBestGuessNumberOfCPUCores() {
        if (numCPUCores > 0) {
            return numCPUCores;
        }
        try {
            numCPUCores = new File("/sys/devices/system/cpu/").listFiles(new FilenameFilter() {
                public boolean accept(File dir, String fileName) {
                    return Pattern.matches("cpu[0-9]+", fileName);
                }
            }).length;
        } catch (Exception e) {
        }
        if (numCPUCores <= 0) {
            numCPUCores = Math.max(Runtime.getRuntime().availableProcessors(), 1);
        }
        return numCPUCores;
    }

    private static void refreshPeriodicExtendedDeviceInfo(Context appContext) {
        if (timestampOfLastCheck == -1 || System.currentTimeMillis() - timestampOfLastCheck >= 1800000) {
            timestampOfLastCheck = System.currentTimeMillis();
            refreshTimezone();
            refreshCarrierName(appContext);
            refreshTotalExternalStorage();
            refreshAvailableExternalStorage();
        }
    }

    private static void refreshTimezone() {
        try {
            TimeZone tz = TimeZone.getDefault();
            deviceTimezone = tz.getDisplayName(tz.inDaylightTime(new Date()), 0);
        } catch (Exception e) {
        }
    }

    private static void refreshCarrierName(Context appContext) {
        if (carrierName.equals(NO_CARRIER)) {
            try {
                carrierName = ((TelephonyManager) appContext.getSystemService("phone")).getNetworkOperatorName();
            } catch (Exception e) {
            }
        }
    }

    private static void refreshAvailableExternalStorage() {
        try {
            if (externalStorageExists()) {
                StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
                availableExternalStorageGB = ((long) stat.getAvailableBlocks()) * ((long) stat.getBlockSize());
            }
            availableExternalStorageGB = convertBytesToGB((double) availableExternalStorageGB);
        } catch (Exception e) {
        }
    }

    private static void refreshTotalExternalStorage() {
        try {
            if (externalStorageExists()) {
                StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
                totalExternalStorageGB = ((long) stat.getBlockCount()) * ((long) stat.getBlockSize());
            }
            totalExternalStorageGB = convertBytesToGB((double) totalExternalStorageGB);
        } catch (Exception e) {
        }
    }

    private static boolean externalStorageExists() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    private static long convertBytesToGB(double bytes) {
        return Math.round(bytes / 1.073741824E9d);
    }
}
