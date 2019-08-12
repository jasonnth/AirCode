package com.threatmetrix.TrustDefender;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.SparseArray;
import com.airbnb.android.core.utils.NetworkUtil;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.threatmetrix.TrustDefender.x */
class C4835x {

    /* renamed from: a */
    private static final String f4666a = C4834w.m2892a(C4835x.class);

    /* renamed from: f */
    private static SparseArray<String> f4667f = new SparseArray<>(30);

    /* renamed from: b */
    private String f4668b = null;

    /* renamed from: c */
    private String f4669c = null;

    /* renamed from: d */
    private String f4670d = null;

    /* renamed from: e */
    private String f4671e = null;

    /* renamed from: com.threatmetrix.TrustDefender.x$a */
    enum C4836a {
        BLUETOOTH("bluetooth tethering"),
        CELLULAR(NetworkUtil.NETWORK_TYPE_CELLULAR),
        MOBILE("mobile"),
        WIFI("wifi"),
        ETHERNET("ethernet");
        

        /* renamed from: f */
        String f4678f;

        private C4836a(String value) {
            this.f4678f = value;
        }

        /* renamed from: a */
        public final String mo46101a() {
            return this.f4678f;
        }
    }

    static {
        String trim;
        List<String> currentTypes = new ArrayList<>();
        Class<?> connectivityManager = C4787at.m2745b("android.net.ConnectivityManager");
        List<String> fieldNames = C4787at.m2744a(connectivityManager);
        if (fieldNames != null) {
            for (String fieldName : fieldNames) {
                if (C4770ai.m2633f(fieldName) && fieldName.startsWith("TYPE_")) {
                    currentTypes.add(fieldName);
                }
            }
            for (String type : currentTypes) {
                Object value = C4787at.m2737a((Class) connectivityManager, type, (Object) null);
                if (value != null) {
                    SparseArray<String> sparseArray = f4667f;
                    int intValue = ((Integer) value).intValue();
                    if (C4770ai.m2632e(type)) {
                        trim = null;
                    } else {
                        String trim2 = type.trim();
                        if (C4770ai.m2632e(trim2)) {
                            trim = null;
                        } else {
                            String lowerCase = trim2.toLowerCase();
                            if (lowerCase.startsWith("type")) {
                                lowerCase = lowerCase.replaceFirst("type", "");
                            }
                            trim = lowerCase.replaceAll("_", " ").trim();
                        }
                    }
                    sparseArray.put(intValue, trim);
                }
            }
        }
    }

    public C4835x(Context context) {
        String[] strArr;
        Object telephonyService;
        m2904a(NativeGatherer.m2512a().mo45875i());
        if (this.f4669c == null || this.f4668b == null || this.f4671e == null) {
            m2904a(m2907b(context));
            if (this.f4669c == null || this.f4668b == null || this.f4671e == null) {
                if (C4806g.m2792c()) {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.net.wifi.STATE_CHANGE");
                    Intent registerReceiver = context.registerReceiver(null, intentFilter);
                    if (registerReceiver != null) {
                        String[] strArr2 = new String[4];
                        WifiInfo wifiInfo = (WifiInfo) registerReceiver.getParcelableExtra("wifiInfo");
                        if (wifiInfo != null) {
                            String b = m2906b(wifiInfo.getBSSID());
                            String a = m2903a(wifiInfo.getSSID());
                            int rssi = wifiInfo.getRssi();
                            if (b != null) {
                                strArr2[0] = b;
                            }
                            if (a != null) {
                                strArr2[1] = a;
                            }
                            strArr2[2] = String.valueOf(rssi);
                            strArr2[3] = C4836a.WIFI.mo46101a();
                            if (!(strArr2[0] == null || strArr2[1] == null || strArr2[2] == null)) {
                                strArr = strArr2;
                                m2904a(strArr);
                                if (this.f4668b == null || this.f4669c == null || this.f4671e == null) {
                                    m2904a(m2905a(context));
                                }
                                if (this.f4670d == null && C4813n.m2820k()) {
                                    telephonyService = context.getSystemService("phone");
                                    if (telephonyService != null && (telephonyService instanceof TelephonyManager)) {
                                        int dataState = ((TelephonyManager) telephonyService).getDataState();
                                        if (dataState == 2 || dataState == 1 || dataState == 3) {
                                            this.f4670d = C4836a.CELLULAR.mo46101a();
                                        }
                                    }
                                }
                            }
                        } else {
                            Bundle extras = registerReceiver.getExtras();
                            NetworkInfo networkInfo = (NetworkInfo) extras.get("networkInfo");
                            if (networkInfo != null && networkInfo.getState() == State.CONNECTED) {
                                String a2 = m2903a(networkInfo.getExtraInfo());
                                String b2 = m2906b((String) extras.get("bssid"));
                                if (b2 != null) {
                                    strArr2[0] = b2;
                                }
                                if (a2 != null) {
                                    strArr2[1] = a2;
                                }
                                strArr2[3] = C4836a.WIFI.mo46101a();
                            }
                        }
                        strArr = strArr2;
                        m2904a(strArr);
                        m2904a(m2905a(context));
                        telephonyService = context.getSystemService("phone");
                        int dataState2 = ((TelephonyManager) telephonyService).getDataState();
                        this.f4670d = C4836a.CELLULAR.mo46101a();
                    }
                }
                strArr = null;
                m2904a(strArr);
                m2904a(m2905a(context));
                try {
                    telephonyService = context.getSystemService("phone");
                    int dataState22 = ((TelephonyManager) telephonyService).getDataState();
                    this.f4670d = C4836a.CELLULAR.mo46101a();
                } catch (SecurityException e) {
                    String str = f4666a;
                } catch (Exception e2) {
                    C4834w.m2901c(f4666a, e2.getMessage());
                } finally {
                    this.f4669c = null;
                    this.f4668b = null;
                    this.f4671e = null;
                }
            }
        }
        String str2 = f4666a;
        new StringBuilder("Network Info (Final values) BSSID: ").append(this.f4669c).append(" SSID: ").append(this.f4668b).append(" RSSI: ").append(this.f4671e).append(" Type: ").append(this.f4670d);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final String mo46096a() {
        return this.f4668b;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final String mo46097b() {
        return this.f4669c;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public final String mo46098c() {
        return this.f4670d;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public final String mo46099d() {
        return this.f4671e;
    }

    /* renamed from: a */
    private String[] m2905a(Context context) {
        if (!C4806g.m2791b()) {
            return null;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        Intent intent = context.registerReceiver(null, intentFilter);
        if (intent == null) {
            return null;
        }
        String[] networkInfo = new String[4];
        int typeCode = intent.getIntExtra("networkType", -99);
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return networkInfo;
        }
        NetworkInfo info = (NetworkInfo) extras.get("networkInfo");
        if (info != null) {
            if (info.getState() != State.CONNECTED) {
                return null;
            }
            if (typeCode == -99) {
                try {
                    typeCode = info.getType();
                } catch (SecurityException e) {
                    String str = f4666a;
                    return null;
                } catch (Exception e2) {
                    C4834w.m2901c(f4666a, e2.getMessage());
                    return null;
                }
            }
        }
        String typeName = (String) f4667f.get(typeCode);
        if (typeName == null) {
            C4834w.m2900b(f4666a, "Unexpected connection type code {}", String.valueOf(typeCode));
            return networkInfo;
        } else if (typeCode == 0 || typeName.contains(C4836a.MOBILE.mo46101a())) {
            networkInfo[3] = C4836a.CELLULAR.mo46101a();
            return networkInfo;
        } else if (typeCode == 1 || typeName.contains(C4836a.WIFI.mo46101a())) {
            networkInfo[3] = C4836a.WIFI.mo46101a();
            String ssid = info == null ? null : m2903a(info.getExtraInfo());
            String bssid = m2906b((String) extras.get("bssid"));
            if (bssid != null) {
                networkInfo[0] = bssid;
            }
            if (ssid == null) {
                return networkInfo;
            }
            networkInfo[1] = ssid;
            return networkInfo;
        } else if (typeCode == 7 || typeName.contains(C4836a.BLUETOOTH.mo46101a())) {
            networkInfo[3] = C4836a.BLUETOOTH.mo46101a();
            return networkInfo;
        } else if (typeCode == 9 || typeName.contains(C4836a.ETHERNET.mo46101a())) {
            networkInfo[3] = C4836a.ETHERNET.mo46101a();
            return networkInfo;
        } else {
            C4834w.m2900b(f4666a, "Unexpected connection type {}", typeName);
            networkInfo[3] = typeName;
            return networkInfo;
        }
    }

    /* renamed from: b */
    private String[] m2907b(Context context) {
        if (!C4806g.m2793d()) {
            return null;
        }
        if (!new C4808i(context).mo46048a("android.permission.ACCESS_WIFI_STATE", context.getPackageName())) {
            return null;
        }
        try {
            Object wifiService = context.getSystemService("wifi");
            if (wifiService == null || !(wifiService instanceof WifiManager)) {
                return null;
            }
            WifiInfo wifiInfo = ((WifiManager) wifiService).getConnectionInfo();
            String bssid = m2906b(wifiInfo.getBSSID());
            if (bssid == null) {
                return null;
            }
            String ssid = m2903a(wifiInfo.getSSID());
            if (ssid == null) {
                return null;
            }
            return new String[]{bssid, ssid, String.valueOf(wifiInfo.getRssi()), C4836a.WIFI.mo46101a()};
        } catch (SecurityException e) {
            String str = f4666a;
            return null;
        } catch (Exception e2) {
            C4834w.m2901c(f4666a, e2.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    private static String m2903a(String ssid) {
        if (ssid == null || ssid.contains("unknown ssid") || ssid.length() <= 0) {
            return null;
        }
        if (ssid.charAt(0) == '\"') {
            ssid = ssid.substring(1);
        }
        if (ssid.length() > 0 && ssid.charAt(ssid.length() - 1) == '\"') {
            ssid = ssid.substring(0, ssid.length() - 1);
        }
        if (!ssid.isEmpty()) {
            return C4770ai.m2635h(ssid);
        }
        return null;
    }

    /* renamed from: b */
    private static String m2906b(String bssid) {
        if (bssid == null || bssid.length() < 17 || "00:00:00:00:00:00".equals(bssid)) {
            return null;
        }
        return bssid.toUpperCase();
    }

    /* renamed from: a */
    private void m2904a(String[] networkInfo) {
        if (networkInfo != null && networkInfo.length >= 4) {
            if (this.f4669c == null && networkInfo[0] != null) {
                this.f4669c = networkInfo[0];
            }
            if (this.f4668b == null && networkInfo[1] != null) {
                this.f4668b = networkInfo[1];
            }
            if (this.f4671e == null && networkInfo[2] != null) {
                this.f4671e = networkInfo[2];
            }
            if (this.f4670d == null && networkInfo[3] != null) {
                this.f4670d = networkInfo[3];
            }
        }
    }

    public final boolean equals(Object o) {
        if (o == null || !(o instanceof C4835x)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        boolean isEqual = true;
        C4835x other = (C4835x) o;
        if ((this.f4668b != null && !this.f4668b.equals(other.f4668b)) || (this.f4668b == null && other.f4668b != null)) {
            isEqual = false;
        }
        if ((this.f4671e != null && !this.f4671e.equals(other.f4671e)) || (this.f4671e == null && other.f4671e != null)) {
            isEqual = false;
        }
        if ((this.f4670d != null && !this.f4670d.equals(other.f4670d)) || (this.f4670d == null && other.f4670d != null)) {
            isEqual = false;
        }
        if ((this.f4669c == null || this.f4669c.equals(other.f4669c)) && (this.f4669c != null || other.f4669c == null)) {
            return isEqual;
        }
        return false;
    }
}
