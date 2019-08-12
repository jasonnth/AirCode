package com.paypal.android.sdk.onetouch.core.metadata;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import org.json.JSONObject;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.h */
public class C4682h implements LocationListener {

    /* renamed from: O */
    private static C4680e f3988O = new C4680e();

    /* renamed from: P */
    private static final Object f3989P = new Object();

    /* renamed from: Q */
    private static C4682h f3990Q;

    /* renamed from: b */
    public static C4693u f3991b = null;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public static final String f3992h = C4682h.class.getSimpleName();

    /* renamed from: A */
    private C4681g f3993A;

    /* renamed from: B */
    private C4681g f3994B;

    /* renamed from: C */
    private String f3995C;

    /* renamed from: D */
    private Map<String, Object> f3996D;

    /* renamed from: E */
    private Location f3997E;
    /* access modifiers changed from: private */

    /* renamed from: F */
    public Timer f3998F;
    /* access modifiers changed from: private */

    /* renamed from: G */
    public Handler f3999G;

    /* renamed from: H */
    private C4687m f4000H;

    /* renamed from: I */
    private String f4001I;

    /* renamed from: J */
    private String f4002J;

    /* renamed from: K */
    private boolean f4003K;

    /* renamed from: L */
    private String f4004L;
    /* access modifiers changed from: private */

    /* renamed from: r */
    public Context f4005r;

    /* renamed from: s */
    private String f4006s;

    /* renamed from: t */
    private long f4007t;
    /* access modifiers changed from: private */

    /* renamed from: u */
    public long f4008u;
    /* access modifiers changed from: private */

    /* renamed from: v */
    public int f4009v;
    /* access modifiers changed from: private */

    /* renamed from: w */
    public int f4010w;

    /* renamed from: x */
    private long f4011x;
    /* access modifiers changed from: private */

    /* renamed from: y */
    public String f4012y;

    /* renamed from: z */
    private C4678c f4013z;

    private C4682h() {
    }

    /* renamed from: a */
    private static long m2437a(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            if (VERSION.SDK_INT > 8) {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
            }
            String str = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
            if (str != null) {
                return new File(str).lastModified();
            }
            return 0;
        } catch (NameNotFoundException e) {
            return 0;
        }
    }

    /* renamed from: a */
    private static String m2438a(TelephonyManager telephonyManager) {
        try {
            return telephonyManager.getSimOperatorName();
        } catch (SecurityException e) {
            C4677ai.m2391a(f3992h, "Known SecurityException on some devices", (Throwable) e);
            return null;
        }
    }

    /* renamed from: a */
    private static ArrayList<String> m2439a(WifiManager wifiManager) {
        ArrayList arrayList = new ArrayList();
        List scanResults = wifiManager.getScanResults();
        if (scanResults == null || scanResults.size() == 0) {
            return null;
        }
        String bssid = wifiManager.getConnectionInfo().getBSSID();
        if (bssid == null || bssid.equals("00:00:00:00:00:00")) {
            return null;
        }
        int i = 0;
        int i2 = -1;
        int i3 = Integer.MIN_VALUE;
        while (true) {
            int i4 = i;
            if (i4 >= scanResults.size()) {
                break;
            }
            if (!bssid.equals(((ScanResult) scanResults.get(i4)).BSSID)) {
                int i5 = ((ScanResult) scanResults.get(i4)).level;
                if (i3 < i5) {
                    i2 = i4;
                    i3 = i5;
                }
            }
            i = i4 + 1;
        }
        arrayList.add(bssid);
        if (i2 != -1) {
            arrayList.add(((ScanResult) scanResults.get(i2)).BSSID);
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m2440a(C4678c cVar) {
        this.f4013z = cVar;
        C4677ai.m2390a(f3992h, "Configuration loaded");
        C4677ai.m2390a(f3992h, "URL:     " + this.f4013z.mo45405a());
        C4677ai.m2390a(f3992h, "Version: " + this.f4013z.mo45406b());
        m2458n();
        this.f3998F = new Timer();
        long c = this.f4013z.mo45407c();
        long d = this.f4013z.mo45408d();
        long e = this.f4013z.mo45409e();
        C4677ai.m2390a(f3992h, "Sending logRiskMetadata every " + c + " seconds.");
        C4677ai.m2390a(f3992h, "sessionTimeout set to " + d + " seconds.");
        C4677ai.m2390a(f3992h, "compTimeout set to    " + e + " seconds.");
        this.f4007t = c * 1000;
        this.f4008u = e * 1000;
        C4686l.m2468a(d * 1000);
        m2457m();
    }

    /* renamed from: a */
    private void m2441a(C4681g gVar, C4681g gVar2) {
        boolean z = true;
        if (gVar != null) {
            gVar.f3961ag = this.f3996D;
            JSONObject a = gVar2 != null ? gVar.mo45418a(gVar2) : gVar.mo45417a();
            HashMap hashMap = new HashMap();
            hashMap.put("appGuid", this.f4006s);
            hashMap.put("libraryVersion", mo45424d());
            hashMap.put("additionalData", a.toString());
            C4677ai.m2390a(f3992h, "Dyson Risk Data " + a.toString());
            if (this.f4013z != null) {
                String g = this.f4013z.mo45411g();
                boolean h = this.f4013z.mo45412h();
                C4677ai.m2390a(f3992h, "new LogRiskMetadataRequest to: " + g);
                C4677ai.m2390a(f3992h, "endpointIsStage: " + h + " (using SSL: " + (!h) + ")");
                Handler handler = this.f3999G;
                if (h) {
                    z = false;
                }
                C4671ab.m2370a().mo45394a(new C4695w(g, hashMap, handler, z));
            }
        }
    }

    /* renamed from: b */
    private static long m2443b(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            if (VERSION.SDK_INT > 8) {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
            }
            String str = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
            if (str != null) {
                return new File(str).lastModified();
            }
            return 0;
        } catch (NameNotFoundException e) {
            return 0;
        }
    }

    /* renamed from: c */
    static /* synthetic */ boolean m2444c(C4682h hVar) {
        return System.currentTimeMillis() - hVar.f4011x > hVar.f4008u;
    }

    /* renamed from: f */
    static /* synthetic */ void m2447f(C4682h hVar) {
        if (hVar.f3994B != null) {
            C4677ai.m2390a(f3992h, hVar.f3995C + " update not sent correctly, retrying...");
            if ("full".equals(hVar.f3995C)) {
                hVar.m2441a(hVar.f3994B, (C4681g) null);
                return;
            }
            hVar.m2441a(hVar.f3994B, hVar.m2459w());
        } else if (!C4686l.m2470c() || hVar.f3993A == null) {
            C4686l.m2467a();
            hVar.f3995C = "full";
            C4681g w = hVar.m2459w();
            hVar.m2441a(w, (C4681g) null);
            hVar.f3994B = w;
        } else {
            hVar.f3995C = "incremental";
            C4681g w2 = hVar.m2459w();
            hVar.m2441a(hVar.f3993A, w2);
            hVar.f3994B = w2;
        }
    }

    /* renamed from: h */
    public static C4682h m2450h() {
        C4682h hVar;
        synchronized (f3989P) {
            if (f3990Q == null) {
                f3990Q = new C4682h();
            }
            hVar = f3990Q;
        }
        return hVar;
    }

    /* renamed from: k */
    private static String m2455k() {
        return C4677ai.m2400b(Boolean.FALSE.booleanValue());
    }

    /* renamed from: l */
    private String m2456l() {
        StringBuilder sb = new StringBuilder("https://b.stats.paypal.com/counter.cgi?p=");
        if (this.f4000H == null || this.f4000H == C4687m.UNKNOWN) {
            return "Beacon not recognize host app";
        }
        int a = this.f4000H.mo45433a();
        if (this.f4002J == null) {
            return "Beacon pairing id empty";
        }
        sb.append(this.f4002J).append("&i=");
        String b = C4677ai.m2396b();
        if (b.equals("")) {
            try {
                sb.append(C4680e.m2431a("emptyIp")).append("&t=");
            } catch (IOException e) {
                C4677ai.m2391a(f3992h, "error reading property file", (Throwable) e);
            }
        } else {
            sb.append(b).append("&t=");
        }
        sb.append(String.valueOf(System.currentTimeMillis() / 1000)).append("&a=").append(a);
        C4677ai.m2390a(f3992h, "Beacon Request URL " + sb.toString());
        C4671ab.m2370a().mo45394a(new C4691s(sb.toString(), this.f4006s, this.f4001I, C4677ai.m2380a(this.f4005r), this.f3999G));
        return sb.toString();
    }

    /* renamed from: m */
    private void m2457m() {
        if (this.f4013z != null && this.f4003K) {
            m2458n();
            this.f3998F = new Timer();
            C4677ai.m2390a(f3992h, "Starting LogRiskMetadataTask");
            this.f3998F.scheduleAtFixedRate(new C4683i(this), 0, this.f4007t);
        }
    }

    /* renamed from: n */
    private void m2458n() {
        if (this.f3998F != null) {
            this.f3998F.cancel();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:321:0x052c, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:322:0x052d, code lost:
        com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(f3992h, "Exception Thrown in " + com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataAppGuid, (java.lang.Throwable) r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* renamed from: w */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.paypal.android.sdk.onetouch.core.metadata.C4681g m2459w() {
        /*
            r18 = this;
            r0 = r18
            android.content.Context r2 = r0.f4005r
            if (r2 != 0) goto L_0x0008
            r2 = 0
        L_0x0007:
            return r2
        L_0x0008:
            com.paypal.android.sdk.onetouch.core.metadata.g r5 = new com.paypal.android.sdk.onetouch.core.metadata.g
            r5.<init>()
            r0 = r18
            com.paypal.android.sdk.onetouch.core.metadata.c r2 = r0.f4013z     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ag r11 = r2.mo45413i()     // Catch:{ Exception -> 0x04b8 }
            r0 = r18
            android.content.Context r2 = r0.f4005r     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r3 = "phone"
            java.lang.Object r2 = r2.getSystemService(r3)     // Catch:{ Exception -> 0x04b8 }
            android.telephony.TelephonyManager r2 = (android.telephony.TelephonyManager) r2     // Catch:{ Exception -> 0x04b8 }
            r0 = r18
            android.content.Context r3 = r0.f4005r     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = "wifi"
            java.lang.Object r3 = r3.getSystemService(r4)     // Catch:{ Exception -> 0x04b8 }
            android.net.wifi.WifiManager r3 = (android.net.wifi.WifiManager) r3     // Catch:{ Exception -> 0x04b8 }
            r0 = r18
            android.content.Context r4 = r0.f4005r     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "connectivity"
            java.lang.Object r4 = r4.getSystemService(r6)     // Catch:{ Exception -> 0x04b8 }
            android.net.ConnectivityManager r4 = (android.net.ConnectivityManager) r4     // Catch:{ Exception -> 0x04b8 }
            r7 = 0
            r6 = 0
            r0 = r18
            android.content.Context r8 = r0.f4005r     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r9 = "android.permission.ACCESS_WIFI_STATE"
            boolean r8 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2393a(r8, r9)     // Catch:{ Exception -> 0x04b8 }
            if (r8 == 0) goto L_0x04a5
            android.net.wifi.WifiInfo r8 = r3.getConnectionInfo()     // Catch:{ Exception -> 0x04b8 }
            r10 = r8
        L_0x0050:
            r0 = r18
            android.content.Context r8 = r0.f4005r     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r9 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r8 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2393a(r8, r9)     // Catch:{ Exception -> 0x04b8 }
            if (r8 == 0) goto L_0x04a9
            android.net.NetworkInfo r4 = r4.getActiveNetworkInfo()     // Catch:{ Exception -> 0x04b8 }
            r9 = r4
        L_0x0062:
            r0 = r18
            android.content.Context r4 = r0.f4005r     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r4 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2393a(r4, r8)     // Catch:{ Exception -> 0x04b8 }
            if (r4 != 0) goto L_0x007c
            r0 = r18
            android.content.Context r4 = r0.f4005r     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r4 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2393a(r4, r8)     // Catch:{ Exception -> 0x04b8 }
            if (r4 == 0) goto L_0x04ad
        L_0x007c:
            r4 = 1
            r8 = r4
        L_0x007e:
            r0 = r18
            android.content.Context r4 = r0.f4005r     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r12 = "android.permission.READ_PHONE_STATE"
            boolean r12 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2393a(r4, r12)     // Catch:{ Exception -> 0x04b8 }
            java.util.Date r13 = new java.util.Date     // Catch:{ Exception -> 0x04b8 }
            r13.<init>()     // Catch:{ Exception -> 0x04b8 }
            int r4 = r2.getPhoneType()     // Catch:{ Exception -> 0x04b8 }
            switch(r4) {
                case 0: goto L_0x04b1;
                case 1: goto L_0x04c2;
                case 2: goto L_0x04e5;
                default: goto L_0x0095;
            }     // Catch:{ Exception -> 0x04b8 }
        L_0x0095:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r14 = "unknown ("
            r4.<init>(r14)     // Catch:{ Exception -> 0x04b8 }
            int r14 = r2.getPhoneType()     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r14 = ")"
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            r5.f3987z = r4     // Catch:{ Exception -> 0x04b8 }
        L_0x00b2:
            java.lang.String r4 = "3.5.7.release"
            r5.f3968g = r4     // Catch:{ Exception -> 0x04b8 }
            r0 = r18
            java.lang.String r4 = r0.f4012y     // Catch:{ Exception -> 0x04b8 }
            r5.f3969h = r4     // Catch:{ Exception -> 0x04b8 }
            r0 = r18
            com.paypal.android.sdk.onetouch.core.metadata.c r4 = r0.f4013z     // Catch:{ Exception -> 0x04b8 }
            if (r4 != 0) goto L_0x0508
            r4 = 0
        L_0x00c4:
            r5.f3970i = r4     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataOsType     // Catch:{ Exception -> 0x0512 }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x0512 }
            if (r4 != 0) goto L_0x00d1
            r4 = 0
            r5.f3985x = r4     // Catch:{ Exception -> 0x0512 }
        L_0x00d1:
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataAppGuid     // Catch:{ Exception -> 0x052c }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x052c }
            if (r4 == 0) goto L_0x00df
            r0 = r18
            java.lang.String r4 = r0.f4006s     // Catch:{ Exception -> 0x052c }
            r5.f3954a = r4     // Catch:{ Exception -> 0x052c }
        L_0x00df:
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataTimestamp     // Catch:{ Exception -> 0x0546 }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x0546 }
            if (r4 == 0) goto L_0x00ed
            long r14 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0546 }
            r5.f3935H = r14     // Catch:{ Exception -> 0x0546 }
        L_0x00ed:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            r4.<init>()     // Catch:{ Exception -> 0x04b8 }
            r0 = r18
            java.lang.String r14 = r0.f4006s     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ Exception -> 0x04b8 }
            long r14 = r5.f3935H     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2385a(r4)     // Catch:{ Exception -> 0x04b8 }
            r5.f3958ad = r4     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataPairingId     // Catch:{ Exception -> 0x0560 }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x0560 }
            if (r4 == 0) goto L_0x0118
            r0 = r18
            java.lang.String r4 = r0.f4002J     // Catch:{ Exception -> 0x0560 }
            r5.f3947T = r4     // Catch:{ Exception -> 0x0560 }
        L_0x0118:
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataPhoneType     // Catch:{ Exception -> 0x057a }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x057a }
            if (r4 != 0) goto L_0x0123
            r4 = 0
            r5.f3987z = r4     // Catch:{ Exception -> 0x057a }
        L_0x0123:
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataSourceApp     // Catch:{ Exception -> 0x05a0 }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x05a0 }
            if (r4 == 0) goto L_0x0139
            r0 = r18
            com.paypal.android.sdk.onetouch.core.metadata.m r4 = r0.f4000H     // Catch:{ Exception -> 0x05a0 }
            if (r4 != 0) goto L_0x0594
            com.paypal.android.sdk.onetouch.core.metadata.m r4 = com.paypal.android.sdk.onetouch.core.metadata.C4687m.UNKNOWN     // Catch:{ Exception -> 0x05a0 }
            int r4 = r4.mo45433a()     // Catch:{ Exception -> 0x05a0 }
            r5.f3943P = r4     // Catch:{ Exception -> 0x05a0 }
        L_0x0139:
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataSourceAppVersion     // Catch:{ Exception -> 0x05ba }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x05ba }
            if (r4 == 0) goto L_0x0147
            r0 = r18
            java.lang.String r4 = r0.f4001I     // Catch:{ Exception -> 0x05ba }
            r5.f3944Q = r4     // Catch:{ Exception -> 0x05ba }
        L_0x0147:
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataNotifToken     // Catch:{ Exception -> 0x05d4 }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x05d4 }
            if (r4 == 0) goto L_0x0155
            r0 = r18
            java.lang.String r4 = r0.f4004L     // Catch:{ Exception -> 0x05d4 }
            r5.f3951X = r4     // Catch:{ Exception -> 0x05d4 }
        L_0x0155:
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataAndroidId     // Catch:{ Exception -> 0x05ee }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x05ee }
            if (r4 == 0) goto L_0x016e
            r0 = r18
            android.content.Context r4 = r0.f4005r     // Catch:{ Exception -> 0x05ee }
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch:{ Exception -> 0x05ee }
            java.lang.String r14 = "android_id"
            java.lang.String r4 = android.provider.Settings.Secure.getString(r4, r14)     // Catch:{ Exception -> 0x05ee }
            r5.f3950W = r4     // Catch:{ Exception -> 0x05ee }
        L_0x016e:
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataDeviceUptime     // Catch:{ Exception -> 0x0608 }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x0608 }
            if (r4 == 0) goto L_0x017c
            long r14 = android.os.SystemClock.uptimeMillis()     // Catch:{ Exception -> 0x0608 }
            r5.f3975n = r14     // Catch:{ Exception -> 0x0608 }
        L_0x017c:
            r0 = r18
            android.content.Context r4 = r0.f4005r     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.a r14 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2380a(r4)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataAppId     // Catch:{ Exception -> 0x0622 }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x0622 }
            if (r4 == 0) goto L_0x0192
            java.lang.String r4 = r14.mo45389a()     // Catch:{ Exception -> 0x0622 }
            r5.f3963b = r4     // Catch:{ Exception -> 0x0622 }
        L_0x0192:
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataAppVersion     // Catch:{ Exception -> 0x063e }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x063e }
            if (r4 == 0) goto L_0x01a0
            java.lang.String r4 = r14.mo45391b()     // Catch:{ Exception -> 0x063e }
            r5.f3964c = r4     // Catch:{ Exception -> 0x063e }
        L_0x01a0:
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataBaseStationId     // Catch:{ Exception -> 0x065e }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x065e }
            if (r4 == 0) goto L_0x01ad
            if (r6 != 0) goto L_0x0658
            r4 = -1
        L_0x01ab:
            r5.f3965d = r4     // Catch:{ Exception -> 0x065e }
        L_0x01ad:
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataCdmaNetworkId     // Catch:{ Exception -> 0x067e }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x067e }
            if (r4 == 0) goto L_0x01ba
            if (r6 != 0) goto L_0x0678
            r4 = -1
        L_0x01b8:
            r5.f3941N = r4     // Catch:{ Exception -> 0x067e }
        L_0x01ba:
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataCdmaSystemId     // Catch:{ Exception -> 0x069e }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x069e }
            if (r4 == 0) goto L_0x01c7
            if (r6 != 0) goto L_0x0698
            r4 = -1
        L_0x01c5:
            r5.f3940M = r4     // Catch:{ Exception -> 0x069e }
        L_0x01c7:
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataBssid     // Catch:{ Exception -> 0x06be }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x06be }
            if (r4 == 0) goto L_0x01d4
            if (r10 != 0) goto L_0x06b8
            r4 = 0
        L_0x01d2:
            r5.f3966e = r4     // Catch:{ Exception -> 0x06be }
        L_0x01d4:
            com.paypal.android.sdk.onetouch.core.metadata.ah r4 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataBssidArray     // Catch:{ Exception -> 0x06db }
            boolean r4 = r11.mo45403a(r4)     // Catch:{ Exception -> 0x06db }
            if (r4 == 0) goto L_0x01e4
            if (r8 == 0) goto L_0x06d8
            java.util.ArrayList r3 = m2439a(r3)     // Catch:{ Exception -> 0x06db }
        L_0x01e2:
            r5.f3960af = r3     // Catch:{ Exception -> 0x06db }
        L_0x01e4:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataCellId     // Catch:{ Exception -> 0x06fb }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x06fb }
            if (r3 == 0) goto L_0x01f1
            if (r7 != 0) goto L_0x06f5
            r3 = -1
        L_0x01ef:
            r5.f3967f = r3     // Catch:{ Exception -> 0x06fb }
        L_0x01f1:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataNetworkOperator     // Catch:{ Exception -> 0x0715 }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x0715 }
            if (r3 == 0) goto L_0x01ff
            java.lang.String r3 = r2.getNetworkOperator()     // Catch:{ Exception -> 0x0715 }
            r5.f3942O = r3     // Catch:{ Exception -> 0x0715 }
        L_0x01ff:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataConnType     // Catch:{ Exception -> 0x0735 }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x0735 }
            if (r3 == 0) goto L_0x020c
            if (r9 != 0) goto L_0x072f
            r3 = 0
        L_0x020a:
            r5.f3971j = r3     // Catch:{ Exception -> 0x0735 }
        L_0x020c:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataDeviceId     // Catch:{ Exception -> 0x0752 }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x0752 }
            if (r3 == 0) goto L_0x021c
            if (r12 == 0) goto L_0x074f
            java.lang.String r3 = r2.getDeviceId()     // Catch:{ Exception -> 0x0752 }
        L_0x021a:
            r5.f3972k = r3     // Catch:{ Exception -> 0x0752 }
        L_0x021c:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataDeviceModel     // Catch:{ Exception -> 0x076c }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x076c }
            if (r3 == 0) goto L_0x0228
            java.lang.String r3 = android.os.Build.MODEL     // Catch:{ Exception -> 0x076c }
            r5.f3973l = r3     // Catch:{ Exception -> 0x076c }
        L_0x0228:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataDeviceName     // Catch:{ Exception -> 0x0786 }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x0786 }
            if (r3 == 0) goto L_0x0234
            java.lang.String r3 = android.os.Build.DEVICE     // Catch:{ Exception -> 0x0786 }
            r5.f3974m = r3     // Catch:{ Exception -> 0x0786 }
        L_0x0234:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataIpAddrs     // Catch:{ Exception -> 0x07a0 }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x07a0 }
            if (r3 == 0) goto L_0x0242
            java.lang.String r3 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2396b()     // Catch:{ Exception -> 0x07a0 }
            r5.f3976o = r3     // Catch:{ Exception -> 0x07a0 }
        L_0x0242:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataIpAddresses     // Catch:{ Exception -> 0x07ba }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x07ba }
            if (r3 == 0) goto L_0x0251
            r3 = 1
            java.util.List r3 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2387a(r3)     // Catch:{ Exception -> 0x07ba }
            r5.f3977p = r3     // Catch:{ Exception -> 0x07ba }
        L_0x0251:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataLinkerId     // Catch:{ Exception -> 0x07d4 }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x07d4 }
            if (r3 == 0) goto L_0x025f
            java.lang.String r3 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2383a()     // Catch:{ Exception -> 0x07d4 }
            r5.f3979r = r3     // Catch:{ Exception -> 0x07d4 }
        L_0x025f:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataLocaleCountry     // Catch:{ Exception -> 0x07ee }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x07ee }
            if (r3 == 0) goto L_0x0271
            java.util.Locale r3 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x07ee }
            java.lang.String r3 = r3.getCountry()     // Catch:{ Exception -> 0x07ee }
            r5.f3980s = r3     // Catch:{ Exception -> 0x07ee }
        L_0x0271:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataLocaleLang     // Catch:{ Exception -> 0x0808 }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x0808 }
            if (r3 == 0) goto L_0x0283
            java.util.Locale r3 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x0808 }
            java.lang.String r3 = r3.getLanguage()     // Catch:{ Exception -> 0x0808 }
            r5.f3981t = r3     // Catch:{ Exception -> 0x0808 }
        L_0x0283:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataLocation     // Catch:{ Exception -> 0x082d }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x082d }
            if (r3 == 0) goto L_0x0294
            r0 = r18
            android.location.Location r3 = r0.f3997E     // Catch:{ Exception -> 0x082d }
            if (r3 != 0) goto L_0x0822
            r3 = 0
        L_0x0292:
            r5.f3982u = r3     // Catch:{ Exception -> 0x082d }
        L_0x0294:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataLocationAreaCode     // Catch:{ Exception -> 0x084d }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x084d }
            if (r3 == 0) goto L_0x02a1
            if (r7 != 0) goto L_0x0847
            r3 = -1
        L_0x029f:
            r5.f3983v = r3     // Catch:{ Exception -> 0x084d }
        L_0x02a1:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataMacAddrs     // Catch:{ Exception -> 0x086d }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x086d }
            if (r3 == 0) goto L_0x02ae
            if (r10 != 0) goto L_0x0867
            r3 = 0
        L_0x02ac:
            r5.f3984w = r3     // Catch:{ Exception -> 0x086d }
        L_0x02ae:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataOsType     // Catch:{ Exception -> 0x0887 }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x0887 }
            if (r3 == 0) goto L_0x02ba
            java.lang.String r3 = android.os.Build.VERSION.RELEASE     // Catch:{ Exception -> 0x0887 }
            r5.f3986y = r3     // Catch:{ Exception -> 0x0887 }
        L_0x02ba:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataRiskCompSessionId     // Catch:{ Exception -> 0x08a1 }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x08a1 }
            if (r3 == 0) goto L_0x02c8
            java.lang.String r3 = com.paypal.android.sdk.onetouch.core.metadata.C4686l.m2469b()     // Catch:{ Exception -> 0x08a1 }
            r5.f3928A = r3     // Catch:{ Exception -> 0x08a1 }
        L_0x02c8:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataRoaming     // Catch:{ Exception -> 0x08bb }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x08bb }
            if (r3 == 0) goto L_0x02df
            android.telephony.ServiceState r3 = new android.telephony.ServiceState     // Catch:{ Exception -> 0x08bb }
            r3.<init>()     // Catch:{ Exception -> 0x08bb }
            boolean r3 = r3.getRoaming()     // Catch:{ Exception -> 0x08bb }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x08bb }
            r5.f3929B = r3     // Catch:{ Exception -> 0x08bb }
        L_0x02df:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataSimOperatorName     // Catch:{ Exception -> 0x08d5 }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x08d5 }
            if (r3 == 0) goto L_0x02ed
            java.lang.String r3 = m2438a(r2)     // Catch:{ Exception -> 0x08d5 }
            r5.f3930C = r3     // Catch:{ Exception -> 0x08d5 }
        L_0x02ed:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataSerialNumber     // Catch:{ Exception -> 0x08f2 }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x08f2 }
            if (r3 == 0) goto L_0x02fd
            if (r12 == 0) goto L_0x08ef
            java.lang.String r3 = r2.getSimSerialNumber()     // Catch:{ Exception -> 0x08f2 }
        L_0x02fb:
            r5.f3931D = r3     // Catch:{ Exception -> 0x08f2 }
        L_0x02fd:
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x08f2 }
            r4 = 9
            if (r3 < r4) goto L_0x0307
            java.lang.String r3 = android.os.Build.SERIAL     // Catch:{ Exception -> 0x08f2 }
            r5.f3952Y = r3     // Catch:{ Exception -> 0x08f2 }
        L_0x0307:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataSmsEnabled     // Catch:{ Exception -> 0x090c }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x090c }
            if (r3 == 0) goto L_0x0324
            r0 = r18
            android.content.Context r3 = r0.f4005r     // Catch:{ Exception -> 0x090c }
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch:{ Exception -> 0x090c }
            java.lang.String r4 = "android.hardware.telephony"
            boolean r3 = r3.hasSystemFeature(r4)     // Catch:{ Exception -> 0x090c }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x090c }
            r5.f3932E = r3     // Catch:{ Exception -> 0x090c }
        L_0x0324:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataSsid     // Catch:{ Exception -> 0x092c }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x092c }
            if (r3 == 0) goto L_0x0331
            if (r10 != 0) goto L_0x0926
            r3 = 0
        L_0x032f:
            r5.f3933F = r3     // Catch:{ Exception -> 0x092c }
        L_0x0331:
            com.paypal.android.sdk.onetouch.core.metadata.ah r3 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataSubscriberId     // Catch:{ Exception -> 0x0949 }
            boolean r3 = r11.mo45403a(r3)     // Catch:{ Exception -> 0x0949 }
            if (r3 == 0) goto L_0x0341
            if (r12 == 0) goto L_0x0946
            java.lang.String r2 = r2.getSubscriberId()     // Catch:{ Exception -> 0x0949 }
        L_0x033f:
            r5.f3934G = r2     // Catch:{ Exception -> 0x0949 }
        L_0x0341:
            com.paypal.android.sdk.onetouch.core.metadata.ah r2 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataTotalStorageSpace     // Catch:{ Exception -> 0x0963 }
            boolean r2 = r11.mo45403a(r2)     // Catch:{ Exception -> 0x0963 }
            if (r2 == 0) goto L_0x034f
            long r2 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2402c()     // Catch:{ Exception -> 0x0963 }
            r5.f3936I = r2     // Catch:{ Exception -> 0x0963 }
        L_0x034f:
            com.paypal.android.sdk.onetouch.core.metadata.ah r2 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataTzName     // Catch:{ Exception -> 0x097d }
            boolean r2 = r11.mo45403a(r2)     // Catch:{ Exception -> 0x097d }
            if (r2 == 0) goto L_0x036c
            java.util.TimeZone r2 = java.util.TimeZone.getDefault()     // Catch:{ Exception -> 0x097d }
            java.util.TimeZone r3 = java.util.TimeZone.getDefault()     // Catch:{ Exception -> 0x097d }
            boolean r3 = r3.inDaylightTime(r13)     // Catch:{ Exception -> 0x097d }
            r4 = 1
            java.util.Locale r6 = java.util.Locale.ENGLISH     // Catch:{ Exception -> 0x097d }
            java.lang.String r2 = r2.getDisplayName(r3, r4, r6)     // Catch:{ Exception -> 0x097d }
            r5.f3937J = r2     // Catch:{ Exception -> 0x097d }
        L_0x036c:
            com.paypal.android.sdk.onetouch.core.metadata.ah r2 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataIsDaylightSaving     // Catch:{ Exception -> 0x0997 }
            boolean r2 = r11.mo45403a(r2)     // Catch:{ Exception -> 0x0997 }
            if (r2 == 0) goto L_0x0382
            java.util.TimeZone r2 = java.util.TimeZone.getDefault()     // Catch:{ Exception -> 0x0997 }
            boolean r2 = r2.inDaylightTime(r13)     // Catch:{ Exception -> 0x0997 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x0997 }
            r5.f3938K = r2     // Catch:{ Exception -> 0x0997 }
        L_0x0382:
            com.paypal.android.sdk.onetouch.core.metadata.ah r2 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataTimeZoneOffset     // Catch:{ Exception -> 0x09b1 }
            boolean r2 = r11.mo45403a(r2)     // Catch:{ Exception -> 0x09b1 }
            if (r2 == 0) goto L_0x039c
            java.util.TimeZone r2 = java.util.TimeZone.getDefault()     // Catch:{ Exception -> 0x09b1 }
            long r6 = r13.getTime()     // Catch:{ Exception -> 0x09b1 }
            int r2 = r2.getOffset(r6)     // Catch:{ Exception -> 0x09b1 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x09b1 }
            r5.f3939L = r2     // Catch:{ Exception -> 0x09b1 }
        L_0x039c:
            com.paypal.android.sdk.onetouch.core.metadata.ah r2 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataIsEmulator     // Catch:{ Exception -> 0x09cb }
            boolean r2 = r11.mo45403a(r2)     // Catch:{ Exception -> 0x09cb }
            if (r2 == 0) goto L_0x03ae
            boolean r2 = com.paypal.android.sdk.onetouch.core.metadata.C4689o.m2475a()     // Catch:{ Exception -> 0x09cb }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x09cb }
            r5.f3945R = r2     // Catch:{ Exception -> 0x09cb }
        L_0x03ae:
            com.paypal.android.sdk.onetouch.core.metadata.ah r2 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataIsRooted     // Catch:{ Exception -> 0x09e5 }
            boolean r2 = r11.mo45403a(r2)     // Catch:{ Exception -> 0x09e5 }
            if (r2 == 0) goto L_0x03c0
            boolean r2 = com.paypal.android.sdk.onetouch.core.metadata.C4690p.m2476a()     // Catch:{ Exception -> 0x09e5 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x09e5 }
            r5.f3946S = r2     // Catch:{ Exception -> 0x09e5 }
        L_0x03c0:
            com.paypal.android.sdk.onetouch.core.metadata.ah r2 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataKnownApps     // Catch:{ Exception -> 0x0a02 }
            boolean r2 = r11.mo45403a(r2)     // Catch:{ Exception -> 0x0a02 }
            if (r2 == 0) goto L_0x041d
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x0a02 }
            r3.<init>()     // Catch:{ Exception -> 0x0a02 }
            r0 = r18
            com.paypal.android.sdk.onetouch.core.metadata.c r2 = r0.f4013z     // Catch:{ Exception -> 0x0a02 }
            if (r2 == 0) goto L_0x0414
            r0 = r18
            com.paypal.android.sdk.onetouch.core.metadata.c r2 = r0.f4013z     // Catch:{ Exception -> 0x0a02 }
            java.util.List r2 = r2.mo45410f()     // Catch:{ Exception -> 0x0a02 }
            java.util.Iterator r4 = r2.iterator()     // Catch:{ Exception -> 0x040a }
        L_0x03df:
            boolean r2 = r4.hasNext()     // Catch:{ Exception -> 0x040a }
            if (r2 == 0) goto L_0x0414
            java.lang.Object r2 = r4.next()     // Catch:{ Exception -> 0x040a }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x040a }
            r0 = r18
            android.content.Context r6 = r0.f4005r     // Catch:{ Exception -> 0x040a }
            android.content.pm.PackageManager r6 = r6.getPackageManager()     // Catch:{ Exception -> 0x040a }
            android.content.Intent r7 = new android.content.Intent     // Catch:{ Exception -> 0x040a }
            r7.<init>()     // Catch:{ Exception -> 0x040a }
            android.content.ComponentName r8 = android.content.ComponentName.unflattenFromString(r2)     // Catch:{ Exception -> 0x040a }
            android.content.Intent r7 = r7.setComponent(r8)     // Catch:{ Exception -> 0x040a }
            boolean r6 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2394a(r6, r7)     // Catch:{ Exception -> 0x040a }
            if (r6 == 0) goto L_0x03df
            r3.add(r2)     // Catch:{ Exception -> 0x040a }
            goto L_0x03df
        L_0x040a:
            r2 = move-exception
            java.lang.String r2 = f3992h     // Catch:{ Exception -> 0x0a02 }
            java.lang.String r4 = "knownApps error"
            r6 = 0
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r2, r4, r6)     // Catch:{ Exception -> 0x0a02 }
        L_0x0414:
            int r2 = r3.size()     // Catch:{ Exception -> 0x0a02 }
            if (r2 != 0) goto L_0x09ff
            r2 = 0
        L_0x041b:
            r5.f3978q = r2     // Catch:{ Exception -> 0x0a02 }
        L_0x041d:
            com.paypal.android.sdk.onetouch.core.metadata.ah r2 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataAppFirstInstallTime     // Catch:{ Exception -> 0x0a1c }
            boolean r2 = r11.mo45403a(r2)     // Catch:{ Exception -> 0x0a1c }
            if (r2 == 0) goto L_0x042f
            r0 = r18
            android.content.Context r2 = r0.f4005r     // Catch:{ Exception -> 0x0a1c }
            long r2 = m2437a(r2)     // Catch:{ Exception -> 0x0a1c }
            r5.f3948U = r2     // Catch:{ Exception -> 0x0a1c }
        L_0x042f:
            com.paypal.android.sdk.onetouch.core.metadata.ah r2 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataAppLastUpdateTime     // Catch:{ Exception -> 0x0a36 }
            boolean r2 = r11.mo45403a(r2)     // Catch:{ Exception -> 0x0a36 }
            if (r2 == 0) goto L_0x0441
            r0 = r18
            android.content.Context r2 = r0.f4005r     // Catch:{ Exception -> 0x0a36 }
            long r2 = m2443b(r2)     // Catch:{ Exception -> 0x0a36 }
            r5.f3949V = r2     // Catch:{ Exception -> 0x0a36 }
        L_0x0441:
            com.paypal.android.sdk.onetouch.core.metadata.ah r2 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataGsfId     // Catch:{ Exception -> 0x0a50 }
            boolean r2 = r11.mo45403a(r2)     // Catch:{ Exception -> 0x0a50 }
            if (r2 == 0) goto L_0x0453
            r0 = r18
            android.content.Context r2 = r0.f4005r     // Catch:{ Exception -> 0x0a50 }
            java.lang.String r2 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2397b(r2)     // Catch:{ Exception -> 0x0a50 }
            r5.f3953Z = r2     // Catch:{ Exception -> 0x0a50 }
        L_0x0453:
            com.paypal.android.sdk.onetouch.core.metadata.ah r2 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataVPNSetting     // Catch:{ Exception -> 0x0a6a }
            boolean r2 = r11.mo45403a(r2)     // Catch:{ Exception -> 0x0a6a }
            if (r2 == 0) goto L_0x0461
            java.lang.String r2 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2407e()     // Catch:{ Exception -> 0x0a6a }
            r5.f3956ab = r2     // Catch:{ Exception -> 0x0a6a }
        L_0x0461:
            com.paypal.android.sdk.onetouch.core.metadata.ah r2 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataProxySetting     // Catch:{ Exception -> 0x0a84 }
            boolean r2 = r11.mo45403a(r2)     // Catch:{ Exception -> 0x0a84 }
            if (r2 == 0) goto L_0x046f
            java.lang.String r2 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2405d()     // Catch:{ Exception -> 0x0a84 }
            r5.f3955aa = r2     // Catch:{ Exception -> 0x0a84 }
        L_0x046f:
            com.paypal.android.sdk.onetouch.core.metadata.ah r2 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataCounter     // Catch:{ Exception -> 0x0a9e }
            boolean r2 = r11.mo45403a(r2)     // Catch:{ Exception -> 0x0a9e }
            if (r2 == 0) goto L_0x0492
            int r2 = r5.f3943P     // Catch:{ Exception -> 0x0a9e }
            com.paypal.android.sdk.onetouch.core.metadata.m r3 = com.paypal.android.sdk.onetouch.core.metadata.C4687m.PAYPAL     // Catch:{ Exception -> 0x0a9e }
            int r3 = r3.mo45433a()     // Catch:{ Exception -> 0x0a9e }
            if (r2 != r3) goto L_0x0492
            r0 = r18
            android.content.Context r2 = r0.f4005r     // Catch:{ Exception -> 0x0a9e }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2404c(r2)     // Catch:{ Exception -> 0x0a9e }
            r0 = r18
            android.content.Context r2 = r0.f4005r     // Catch:{ Exception -> 0x0a9e }
            java.lang.String r2 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2406d(r2)     // Catch:{ Exception -> 0x0a9e }
            r5.f3957ac = r2     // Catch:{ Exception -> 0x0a9e }
        L_0x0492:
            r0 = r18
            java.util.Map<java.lang.String, java.lang.Object> r2 = r0.f3996D     // Catch:{ Exception -> 0x04b8 }
            r5.f3961ag = r2     // Catch:{ Exception -> 0x04b8 }
            r0 = r18
            java.lang.String r2 = r0.f4002J     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r2 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2399b(r2)     // Catch:{ Exception -> 0x04b8 }
            r5.f3959ae = r2     // Catch:{ Exception -> 0x04b8 }
        L_0x04a2:
            r2 = r5
            goto L_0x0007
        L_0x04a5:
            r8 = 0
            r10 = r8
            goto L_0x0050
        L_0x04a9:
            r4 = 0
            r9 = r4
            goto L_0x0062
        L_0x04ad:
            r4 = 0
            r8 = r4
            goto L_0x007e
        L_0x04b1:
            java.lang.String r4 = "none"
            r5.f3987z = r4     // Catch:{ Exception -> 0x04b8 }
            goto L_0x00b2
        L_0x04b8:
            r2 = move-exception
            java.lang.String r3 = f3992h
            java.lang.String r4 = "Exception Thrown in During Collection"
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)
            goto L_0x04a2
        L_0x04c2:
            java.lang.String r4 = "gsm"
            r5.f3987z = r4     // Catch:{ Exception -> 0x04b8 }
            if (r8 == 0) goto L_0x04d8
            android.telephony.CellLocation r4 = r2.getCellLocation()     // Catch:{ SecurityException -> 0x04da }
            java.lang.Class<android.telephony.gsm.GsmCellLocation> r14 = android.telephony.gsm.GsmCellLocation.class
            java.lang.Object r4 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2381a(r4, r14)     // Catch:{ SecurityException -> 0x04da }
            android.telephony.gsm.GsmCellLocation r4 = (android.telephony.gsm.GsmCellLocation) r4     // Catch:{ SecurityException -> 0x04da }
        L_0x04d5:
            r7 = r4
            goto L_0x00b2
        L_0x04d8:
            r4 = 0
            goto L_0x04d5
        L_0x04da:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = "Known SecurityException on some devices: "
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x00b2
        L_0x04e5:
            java.lang.String r4 = "cdma"
            r5.f3987z = r4     // Catch:{ Exception -> 0x04b8 }
            if (r8 == 0) goto L_0x04fb
            android.telephony.CellLocation r4 = r2.getCellLocation()     // Catch:{ SecurityException -> 0x04fd }
            java.lang.Class<android.telephony.cdma.CdmaCellLocation> r14 = android.telephony.cdma.CdmaCellLocation.class
            java.lang.Object r4 = com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2381a(r4, r14)     // Catch:{ SecurityException -> 0x04fd }
            android.telephony.cdma.CdmaCellLocation r4 = (android.telephony.cdma.CdmaCellLocation) r4     // Catch:{ SecurityException -> 0x04fd }
        L_0x04f8:
            r6 = r4
            goto L_0x00b2
        L_0x04fb:
            r4 = 0
            goto L_0x04f8
        L_0x04fd:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = "Known SecurityException on some devices: "
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x00b2
        L_0x0508:
            r0 = r18
            com.paypal.android.sdk.onetouch.core.metadata.c r4 = r0.f4013z     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.mo45406b()     // Catch:{ Exception -> 0x04b8 }
            goto L_0x00c4
        L_0x0512:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r16 = "Exception Thrown in "
            r15.<init>(r16)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r16 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataOsType     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x00d1
        L_0x052c:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r16 = "Exception Thrown in "
            r15.<init>(r16)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r16 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataAppGuid     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x00df
        L_0x0546:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r16 = "Exception Thrown in "
            r15.<init>(r16)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r16 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataTimestamp     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x00ed
        L_0x0560:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r16 = "Exception Thrown in "
            r15.<init>(r16)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r16 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataPairingId     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0118
        L_0x057a:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r16 = "Exception Thrown in "
            r15.<init>(r16)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r16 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataPhoneType     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0123
        L_0x0594:
            r0 = r18
            com.paypal.android.sdk.onetouch.core.metadata.m r4 = r0.f4000H     // Catch:{ Exception -> 0x05a0 }
            int r4 = r4.mo45433a()     // Catch:{ Exception -> 0x05a0 }
            r5.f3943P = r4     // Catch:{ Exception -> 0x05a0 }
            goto L_0x0139
        L_0x05a0:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r16 = "Exception Thrown in "
            r15.<init>(r16)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r16 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataSourceApp     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0139
        L_0x05ba:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r16 = "Exception Thrown in "
            r15.<init>(r16)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r16 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataSourceAppVersion     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0147
        L_0x05d4:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r16 = "Exception Thrown in "
            r15.<init>(r16)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r16 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataNotifToken     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0155
        L_0x05ee:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r16 = "Exception Thrown in "
            r15.<init>(r16)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r16 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataAndroidId     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x016e
        L_0x0608:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r16 = "Exception Thrown in "
            r15.<init>(r16)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r16 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataDeviceUptime     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x017c
        L_0x0622:
            r4 = move-exception
            java.lang.String r15 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r16 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r17 = "Exception Thrown in "
            r16.<init>(r17)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r17 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataAppId     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r16 = r16.append(r17)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r16 = r16.toString()     // Catch:{ Exception -> 0x04b8 }
            r0 = r16
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r15, r0, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0192
        L_0x063e:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r16 = "Exception Thrown in "
            r15.<init>(r16)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r16 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataAppVersion     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x01a0
        L_0x0658:
            int r4 = r6.getBaseStationId()     // Catch:{ Exception -> 0x065e }
            goto L_0x01ab
        L_0x065e:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r16 = "Exception Thrown in "
            r15.<init>(r16)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r16 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataBaseStationId     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x01ad
        L_0x0678:
            int r4 = r6.getNetworkId()     // Catch:{ Exception -> 0x067e }
            goto L_0x01b8
        L_0x067e:
            r4 = move-exception
            java.lang.String r14 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r16 = "Exception Thrown in "
            r15.<init>(r16)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r16 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataCdmaNetworkId     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r14, r15, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x01ba
        L_0x0698:
            int r4 = r6.getSystemId()     // Catch:{ Exception -> 0x069e }
            goto L_0x01c5
        L_0x069e:
            r4 = move-exception
            java.lang.String r6 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = "Exception Thrown in "
            r14.<init>(r15)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r15 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataCdmaSystemId     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r6, r14, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x01c7
        L_0x06b8:
            java.lang.String r4 = r10.getBSSID()     // Catch:{ Exception -> 0x06be }
            goto L_0x01d2
        L_0x06be:
            r4 = move-exception
            java.lang.String r6 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r15 = "Exception Thrown in "
            r14.<init>(r15)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r15 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataBssid     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r6, r14, r4)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x01d4
        L_0x06d8:
            r3 = 0
            goto L_0x01e2
        L_0x06db:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "Exception Thrown in "
            r6.<init>(r8)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataBssidArray     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x01e4
        L_0x06f5:
            int r3 = r7.getCid()     // Catch:{ Exception -> 0x06fb }
            goto L_0x01ef
        L_0x06fb:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "Exception Thrown in "
            r6.<init>(r8)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataCellId     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x01f1
        L_0x0715:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "Exception Thrown in "
            r6.<init>(r8)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataNetworkOperator     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x01ff
        L_0x072f:
            java.lang.String r3 = r9.getTypeName()     // Catch:{ Exception -> 0x0735 }
            goto L_0x020a
        L_0x0735:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "Exception Thrown in "
            r6.<init>(r8)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataConnType     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x020c
        L_0x074f:
            r3 = 0
            goto L_0x021a
        L_0x0752:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "Exception Thrown in "
            r6.<init>(r8)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataDeviceId     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x021c
        L_0x076c:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "Exception Thrown in "
            r6.<init>(r8)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataDeviceModel     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0228
        L_0x0786:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "Exception Thrown in "
            r6.<init>(r8)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataDeviceName     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0234
        L_0x07a0:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "Exception Thrown in "
            r6.<init>(r8)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataIpAddrs     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0242
        L_0x07ba:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "Exception Thrown in "
            r6.<init>(r8)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataIpAddresses     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0251
        L_0x07d4:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "Exception Thrown in "
            r6.<init>(r8)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataLinkerId     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x025f
        L_0x07ee:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "Exception Thrown in "
            r6.<init>(r8)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataLocaleCountry     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0271
        L_0x0808:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "Exception Thrown in "
            r6.<init>(r8)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataLocaleLang     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0283
        L_0x0822:
            android.location.Location r3 = new android.location.Location     // Catch:{ Exception -> 0x082d }
            r0 = r18
            android.location.Location r4 = r0.f3997E     // Catch:{ Exception -> 0x082d }
            r3.<init>(r4)     // Catch:{ Exception -> 0x082d }
            goto L_0x0292
        L_0x082d:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r8 = "Exception Thrown in "
            r6.<init>(r8)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r8 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataLocation     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0294
        L_0x0847:
            int r3 = r7.getLac()     // Catch:{ Exception -> 0x084d }
            goto L_0x029f
        L_0x084d:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataLocationAreaCode     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x02a1
        L_0x0867:
            java.lang.String r3 = r10.getMacAddress()     // Catch:{ Exception -> 0x086d }
            goto L_0x02ac
        L_0x086d:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataMacAddrs     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x02ae
        L_0x0887:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataOsType     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x02ba
        L_0x08a1:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataRiskCompSessionId     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x02c8
        L_0x08bb:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataRoaming     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x02df
        L_0x08d5:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataSimOperatorName     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x02ed
        L_0x08ef:
            r3 = 0
            goto L_0x02fb
        L_0x08f2:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataSerialNumber     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0307
        L_0x090c:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataSmsEnabled     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0324
        L_0x0926:
            java.lang.String r3 = r10.getSSID()     // Catch:{ Exception -> 0x092c }
            goto L_0x032f
        L_0x092c:
            r3 = move-exception
            java.lang.String r4 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r7 = "Exception Thrown in "
            r6.<init>(r7)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r7 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataSsid     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r4, r6, r3)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0331
        L_0x0946:
            r2 = 0
            goto L_0x033f
        L_0x0949:
            r2 = move-exception
            java.lang.String r3 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "Exception Thrown in "
            r4.<init>(r6)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataSubscriberId     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0341
        L_0x0963:
            r2 = move-exception
            java.lang.String r3 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "Exception Thrown in "
            r4.<init>(r6)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataTotalStorageSpace     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x034f
        L_0x097d:
            r2 = move-exception
            java.lang.String r3 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "Exception Thrown in "
            r4.<init>(r6)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataTzName     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x036c
        L_0x0997:
            r2 = move-exception
            java.lang.String r3 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "Exception Thrown in "
            r4.<init>(r6)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataIsDaylightSaving     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0382
        L_0x09b1:
            r2 = move-exception
            java.lang.String r3 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "Exception Thrown in "
            r4.<init>(r6)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataTimeZoneOffset     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x039c
        L_0x09cb:
            r2 = move-exception
            java.lang.String r3 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "Exception Thrown in "
            r4.<init>(r6)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataIsEmulator     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x03ae
        L_0x09e5:
            r2 = move-exception
            java.lang.String r3 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "Exception Thrown in "
            r4.<init>(r6)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataIsRooted     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x03c0
        L_0x09ff:
            r2 = r3
            goto L_0x041b
        L_0x0a02:
            r2 = move-exception
            java.lang.String r3 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "Exception Thrown in "
            r4.<init>(r6)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataKnownApps     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x041d
        L_0x0a1c:
            r2 = move-exception
            java.lang.String r3 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "Exception Thrown in "
            r4.<init>(r6)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataAppFirstInstallTime     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x042f
        L_0x0a36:
            r2 = move-exception
            java.lang.String r3 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "Exception Thrown in "
            r4.<init>(r6)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataAppLastUpdateTime     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0441
        L_0x0a50:
            r2 = move-exception
            java.lang.String r3 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "Exception Thrown in "
            r4.<init>(r6)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataGsfId     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0453
        L_0x0a6a:
            r2 = move-exception
            java.lang.String r3 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "Exception Thrown in "
            r4.<init>(r6)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataVPNSetting     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0461
        L_0x0a84:
            r2 = move-exception
            java.lang.String r3 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "Exception Thrown in "
            r4.<init>(r6)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataProxySetting     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x046f
        L_0x0a9e:
            r2 = move-exception
            java.lang.String r3 = f3992h     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r6 = "Exception Thrown in "
            r4.<init>(r6)     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.ah r6 = com.paypal.android.sdk.onetouch.core.metadata.C4676ah.PPRiskDataCounter     // Catch:{ Exception -> 0x04b8 }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ Exception -> 0x04b8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x04b8 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2391a(r3, r4, r2)     // Catch:{ Exception -> 0x04b8 }
            goto L_0x0492
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paypal.android.sdk.onetouch.core.metadata.C4682h.m2459w():com.paypal.android.sdk.onetouch.core.metadata.g");
    }

    /* renamed from: a */
    public final String mo45419a(Context context, String str, C4687m mVar, String str2, Map<String, Object> map) {
        String a = C4677ai.m2386a(map, "RISK_MANAGER_CONF_URL", (String) null);
        String a2 = C4677ai.m2386a(map, "RISK_MANAGER_PAIRING_ID", (String) null);
        this.f4004L = C4677ai.m2386a(map, "RISK_MANAGER_NOTIF_TOKEN", (String) null);
        f3991b = (C4693u) C4677ai.m2382a(map, C4693u.class, "RISK_MANAGER_NETWORK_ADAPTER", new C4697y());
        boolean a3 = C4677ai.m2395a(map, "RISK_MANAGER_IS_DISABLE_REMOTE_CONFIG", Boolean.valueOf(false));
        this.f4003K = false;
        this.f4005r = context;
        this.f4006s = C4677ai.m2403c(context, str);
        if (mVar == null) {
            this.f4000H = C4687m.UNKNOWN;
        } else {
            this.f4000H = mVar;
        }
        this.f4001I = str2;
        this.f3993A = null;
        this.f3994B = null;
        this.f4010w = 0;
        this.f4009v = 0;
        if (a2 == null || a2.trim().length() == 0) {
            this.f4002J = m2455k();
        } else {
            C4677ai.m2388a(3, "PRD", "Using custom pairing id");
            this.f4002J = a2.trim();
        }
        try {
            this.f4012y = a == null ? "https://www.paypalobjects.com/webstatic/risk/dyson_config_android_v3.json" : a;
            C4677ai.m2390a(f3992h, "Host activity detected");
            this.f4011x = System.currentTimeMillis();
            if (this.f3999G == null) {
                this.f3999G = new C4685k(this);
                LocationManager locationManager = (LocationManager) this.f4005r.getSystemService("location");
                if (locationManager != null) {
                    onLocationChanged(C4677ai.m2379a(locationManager));
                    if (locationManager.isProviderEnabled("network")) {
                        locationManager.requestLocationUpdates("network", 3600000, 10.0f, this);
                    }
                }
            }
            m2458n();
        } catch (Exception e) {
            C4677ai.m2391a(f3992h, (String) null, (Throwable) e);
        }
        m2456l();
        m2440a(new C4678c(this.f4005r, !a3));
        return this.f4002J;
    }

    /* renamed from: a */
    public final String mo45420a(String str, Map<String, Object> map) {
        String str2;
        this.f3996D = null;
        if (str != null && this.f4002J != null && str.equals(this.f4002J)) {
            return str;
        }
        if (str == null || str.trim().length() == 0) {
            str2 = m2455k();
        } else {
            str2 = str.trim();
            C4677ai.m2388a(3, "PRD", "Using custom pairing id");
        }
        this.f4002J = str2;
        mo45423b();
        m2456l();
        return str2;
    }

    /* renamed from: a */
    public final void mo45421a(Message message) {
        String str;
        try {
            switch (message.what) {
                case 0:
                    C4677ai.m2390a(f3992h, "Dyson Async URL: " + message.obj);
                    return;
                case 1:
                    C4677ai.m2390a(f3992h, "LogRiskMetadataRequest failed." + ((Exception) message.obj).getMessage());
                    return;
                case 2:
                    String str2 = (String) message.obj;
                    C4677ai.m2390a(f3992h, "LogRiskMetadataRequest Server returned: " + str2);
                    try {
                        str = Uri.parse("?" + str2).getQueryParameter("responseEnvelope.ack");
                    } catch (UnsupportedOperationException e) {
                        str = null;
                    }
                    if ("Success".equals(str)) {
                        C4677ai.m2390a(f3992h, "LogRiskMetadataRequest Success");
                        return;
                    }
                    return;
                case 10:
                    C4677ai.m2390a(f3992h, "Load Configuration URL: " + message.obj);
                    return;
                case 11:
                    C4677ai.m2390a(f3992h, "LoadConfigurationRequest failed.");
                    return;
                case 12:
                    C4678c cVar = (C4678c) message.obj;
                    if (cVar != null) {
                        m2440a(cVar);
                        return;
                    }
                    return;
                case 20:
                    C4677ai.m2390a(f3992h, "Beacon URL: " + message.obj);
                    return;
                case 21:
                    C4677ai.m2390a(f3992h, "BeaconRequest failed " + ((Exception) message.obj).getMessage());
                    return;
                case 22:
                    C4677ai.m2390a(f3992h, "Beacon returned: " + message.obj);
                    return;
                default:
                    return;
            }
        } catch (Exception e2) {
            C4677ai.m2391a(f3992h, (String) null, (Throwable) e2);
        }
        C4677ai.m2391a(f3992h, (String) null, (Throwable) e2);
    }

    /* renamed from: b */
    public final String mo45422b(String str) {
        return mo45420a(str, null);
    }

    /* renamed from: b */
    public final void mo45423b() {
        C4686l.m2467a();
        this.f3993A = m2459w();
        m2441a(this.f3993A, (C4681g) null);
    }

    /* renamed from: d */
    public final String mo45424d() {
        return String.format(Locale.US, "Dyson/%S (%S %S)", new Object[]{"3.5.7.release", InternalLogger.EVENT_PARAM_SDK_ANDROID, VERSION.RELEASE});
    }

    /* renamed from: i */
    public final void mo45425i() {
        new Timer().schedule(new C4684j(this), 0);
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            this.f3997E = new Location(location);
            C4677ai.m2390a(f3992h, "Location Update: " + location.toString());
        }
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
