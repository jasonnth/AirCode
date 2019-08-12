package p315io.branch.referral;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

/* renamed from: io.branch.referral.SystemObserver */
class SystemObserver {
    String GAIDString_ = null;
    int LATVal_ = 0;
    private Context context_;
    private boolean isRealHardwareId;

    /* renamed from: io.branch.referral.SystemObserver$GAdsParamsFetchEvents */
    interface GAdsParamsFetchEvents {
        void onGAdsFetchFinished();
    }

    /* renamed from: io.branch.referral.SystemObserver$GAdsPrefetchTask */
    private class GAdsPrefetchTask extends BranchAsyncTask<Void, Void, Void> {
        private final GAdsParamsFetchEvents callback_;

        public GAdsPrefetchTask(GAdsParamsFetchEvents callback) {
            this.callback_ = callback;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... params) {
            final CountDownLatch latch = new CountDownLatch(1);
            new Thread(new Runnable() {
                public void run() {
                    Process.setThreadPriority(-19);
                    Object adInfoObj = SystemObserver.this.getAdInfoObject();
                    SystemObserver.this.getAdvertisingId(adInfoObj);
                    SystemObserver.this.getLATValue(adInfoObj);
                    latch.countDown();
                }
            }).start();
            try {
                latch.await(1500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (this.callback_ != null) {
                this.callback_.onGAdsFetchFinished();
            }
        }
    }

    SystemObserver(Context context) {
        this.context_ = context;
        this.isRealHardwareId = true;
    }

    /* access modifiers changed from: 0000 */
    public String getUniqueID(boolean debug) {
        if (this.context_ == null) {
            return "bnc_no_value";
        }
        String androidID = null;
        if (!debug) {
            androidID = Secure.getString(this.context_.getContentResolver(), JPushReportInterface.ANDROID_ID);
        }
        if (androidID != null) {
            return androidID;
        }
        String androidID2 = UUID.randomUUID().toString();
        this.isRealHardwareId = false;
        return androidID2;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasRealHardwareId() {
        return this.isRealHardwareId;
    }

    /* access modifiers changed from: 0000 */
    public String getPackageName() {
        String packageName = "";
        try {
            return this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), 0).packageName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return packageName;
        }
    }

    /* access modifiers changed from: 0000 */
    public String getURIScheme() {
        return getURIScheme(this.context_.getPackageName());
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x005c A[SYNTHETIC, Splitter:B:29:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0062 A[Catch:{ IOException -> 0x0068 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getURIScheme(java.lang.String r11) {
        /*
            r10 = this;
            java.lang.String r5 = "bnc_no_value"
            boolean r8 = r10.isLowOnMemory()
            if (r8 != 0) goto L_0x0046
            android.content.Context r8 = r10.context_
            android.content.pm.PackageManager r4 = r8.getPackageManager()
            r8 = 0
            android.content.pm.ApplicationInfo r0 = r4.getApplicationInfo(r11, r8)     // Catch:{ NameNotFoundException -> 0x0066 }
            java.lang.String r6 = r0.publicSourceDir     // Catch:{ NameNotFoundException -> 0x0066 }
            r2 = 0
            r1 = 0
            java.util.jar.JarFile r3 = new java.util.jar.JarFile     // Catch:{ Exception -> 0x004a, all -> 0x0059 }
            r3.<init>(r6)     // Catch:{ Exception -> 0x004a, all -> 0x0059 }
            java.lang.String r8 = "AndroidManifest.xml"
            java.util.zip.ZipEntry r8 = r3.getEntry(r8)     // Catch:{ Exception -> 0x006d, all -> 0x006a }
            java.io.InputStream r1 = r3.getInputStream(r8)     // Catch:{ Exception -> 0x006d, all -> 0x006a }
            int r8 = r1.available()     // Catch:{ Exception -> 0x006d, all -> 0x006a }
            byte[] r7 = new byte[r8]     // Catch:{ Exception -> 0x006d, all -> 0x006a }
            r1.read(r7)     // Catch:{ Exception -> 0x006d, all -> 0x006a }
            io.branch.referral.ApkParser r8 = new io.branch.referral.ApkParser     // Catch:{ Exception -> 0x006d, all -> 0x006a }
            r8.<init>()     // Catch:{ Exception -> 0x006d, all -> 0x006a }
            java.lang.String r5 = r8.decompressXML(r7)     // Catch:{ Exception -> 0x006d, all -> 0x006a }
            if (r1 == 0) goto L_0x0040
            r1.close()     // Catch:{ IOException -> 0x0047 }
            r1 = 0
        L_0x0040:
            if (r3 == 0) goto L_0x0045
            r3.close()     // Catch:{ IOException -> 0x0047 }
        L_0x0045:
            r2 = r3
        L_0x0046:
            return r5
        L_0x0047:
            r8 = move-exception
            r2 = r3
            goto L_0x0046
        L_0x004a:
            r8 = move-exception
        L_0x004b:
            if (r1 == 0) goto L_0x0051
            r1.close()     // Catch:{ IOException -> 0x0057 }
            r1 = 0
        L_0x0051:
            if (r2 == 0) goto L_0x0046
            r2.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x0046
        L_0x0057:
            r8 = move-exception
            goto L_0x0046
        L_0x0059:
            r8 = move-exception
        L_0x005a:
            if (r1 == 0) goto L_0x0060
            r1.close()     // Catch:{ IOException -> 0x0068 }
            r1 = 0
        L_0x0060:
            if (r2 == 0) goto L_0x0065
            r2.close()     // Catch:{ IOException -> 0x0068 }
        L_0x0065:
            throw r8     // Catch:{ NameNotFoundException -> 0x0066 }
        L_0x0066:
            r8 = move-exception
            goto L_0x0046
        L_0x0068:
            r9 = move-exception
            goto L_0x0065
        L_0x006a:
            r8 = move-exception
            r2 = r3
            goto L_0x005a
        L_0x006d:
            r8 = move-exception
            r2 = r3
            goto L_0x004b
        */
        throw new UnsupportedOperationException("Method not decompiled: p315io.branch.referral.SystemObserver.getURIScheme(java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: 0000 */
    public boolean isLowOnMemory() {
        ActivityManager activityManager = (ActivityManager) this.context_.getSystemService("activity");
        MemoryInfo mi = new MemoryInfo();
        activityManager.getMemoryInfo(mi);
        return mi.lowMemory;
    }

    /* access modifiers changed from: 0000 */
    public String getAppVersion() {
        try {
            PackageInfo packageInfo = this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), 0);
            if (packageInfo.versionName != null) {
                return packageInfo.versionName;
            }
            return "bnc_no_value";
        } catch (NameNotFoundException e) {
            return "bnc_no_value";
        }
    }

    /* access modifiers changed from: 0000 */
    public String getPhoneBrand() {
        return Build.MANUFACTURER;
    }

    /* access modifiers changed from: 0000 */
    public String getPhoneModel() {
        return Build.MODEL;
    }

    /* access modifiers changed from: 0000 */
    public String getISO2CountryCode() {
        if (Locale.getDefault() != null) {
            return Locale.getDefault().getCountry();
        }
        return "";
    }

    /* access modifiers changed from: 0000 */
    public String getISO2LanguageCode() {
        if (Locale.getDefault() != null) {
            return Locale.getDefault().getLanguage();
        }
        return "";
    }

    /* access modifiers changed from: 0000 */
    public String getOS() {
        return InternalLogger.EVENT_PARAM_SDK_ANDROID;
    }

    /* access modifiers changed from: 0000 */
    public int getOSVersion() {
        return VERSION.SDK_INT;
    }

    /* access modifiers changed from: 0000 */
    @SuppressLint({"NewApi"})
    public int getUpdateState() {
        PrefHelper pHelper = PrefHelper.getInstance(this.context_);
        String currAppVersion = getAppVersion();
        if ("bnc_no_value".equals(pHelper.getAppVersion())) {
            if (VERSION.SDK_INT >= 9) {
                try {
                    PackageInfo packageInfo = this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), 0);
                    if (packageInfo.lastUpdateTime != packageInfo.firstInstallTime) {
                        return 2;
                    }
                    return 0;
                } catch (NameNotFoundException e) {
                }
            }
            return 0;
        } else if (pHelper.getAppVersion().equals(currAppVersion)) {
            return 1;
        } else {
            return 2;
        }
    }

    /* access modifiers changed from: 0000 */
    public DisplayMetrics getScreenDisplay() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.context_.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public boolean getWifiConnected() {
        if (this.context_.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return false;
        }
        NetworkInfo wifiInfo = ((ConnectivityManager) this.context_.getSystemService("connectivity")).getNetworkInfo(1);
        if (wifiInfo == null || !wifiInfo.isConnected()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public Object getAdInfoObject() {
        Object adInfoObj = null;
        try {
            return Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.context_});
        } catch (Throwable th) {
            return adInfoObj;
        }
    }

    /* access modifiers changed from: private */
    public String getAdvertisingId(Object adInfoObj) {
        try {
            this.GAIDString_ = (String) adInfoObj.getClass().getMethod("getId", new Class[0]).invoke(adInfoObj, new Object[0]);
        } catch (Exception e) {
        }
        return this.GAIDString_;
    }

    /* access modifiers changed from: private */
    public int getLATValue(Object adInfoObj) {
        int i;
        try {
            if (((Boolean) adInfoObj.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(adInfoObj, new Object[0])).booleanValue()) {
                i = 1;
            } else {
                i = 0;
            }
            this.LATVal_ = i;
        } catch (Exception e) {
        }
        return this.LATVal_;
    }

    /* access modifiers changed from: 0000 */
    public boolean prefetchGAdsParams(GAdsParamsFetchEvents callback) {
        if (!TextUtils.isEmpty(this.GAIDString_)) {
            return false;
        }
        new GAdsPrefetchTask(callback).executeTask(new Void[0]);
        return true;
    }

    static String getLocalIPAddress() {
        String ipAddress = "";
        try {
            for (NetworkInterface netInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                Iterator it = Collections.list(netInterface.getInetAddresses()).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    InetAddress address = (InetAddress) it.next();
                    if (!address.isLoopbackAddress()) {
                        String ip = address.getHostAddress();
                        if (ip.indexOf(58) < 0) {
                            ipAddress = ip;
                            break;
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
        return ipAddress;
    }
}
