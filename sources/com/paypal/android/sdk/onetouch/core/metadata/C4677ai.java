package com.paypal.android.sdk.onetouch.core.metadata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.ai */
public final class C4677ai {

    /* renamed from: a */
    private static final boolean f3916a = Boolean.valueOf(System.getProperty("dyson.debug.mode", Boolean.FALSE.toString())).booleanValue();

    /* renamed from: b */
    private static final boolean f3917b = Boolean.valueOf(System.getProperty("prd.debug.mode", Boolean.FALSE.toString())).booleanValue();

    /* renamed from: f */
    private static final String f3918f = (C4682h.class.getSimpleName() + "." + C4677ai.class.getSimpleName());

    /* renamed from: g */
    private static final Uri f3919g;

    static {
        Uri uri;
        try {
            uri = Uri.parse("content://com.google.android.gsf.gservices");
        } catch (Exception e) {
            uri = null;
        }
        f3919g = uri;
    }

    private C4677ai() {
    }

    /* renamed from: a */
    public static Location m2379a(LocationManager locationManager) {
        Location location = null;
        try {
            List providers = locationManager.getProviders(true);
            int size = providers.size() - 1;
            while (size >= 0) {
                try {
                    Location lastKnownLocation = locationManager.getLastKnownLocation((String) providers.get(size));
                    if (lastKnownLocation != null) {
                        return lastKnownLocation;
                    }
                    size--;
                    location = lastKnownLocation;
                } catch (RuntimeException e) {
                    return location;
                }
            }
            return location;
        } catch (RuntimeException e2) {
            return null;
        }
    }

    /* renamed from: a */
    public static C4669a m2380a(Context context) {
        C4669a aVar = new C4669a();
        aVar.mo45390a(context.getPackageName());
        try {
            aVar.mo45392b(context.getPackageManager().getPackageInfo(aVar.mo45389a(), 0).versionName);
        } catch (NameNotFoundException e) {
        }
        return aVar;
    }

    /* renamed from: a */
    public static <T> T m2381a(Object obj, Class<T> cls) {
        if (obj == null || !cls.isAssignableFrom(obj.getClass())) {
            return null;
        }
        return cls.cast(obj);
    }

    /* renamed from: a */
    public static <T> T m2382a(Map<String, Object> map, Class<T> cls, String str, T t) {
        if (map == null) {
            return t;
        }
        Object obj = map.get(str);
        if (obj == null) {
            return t;
        }
        if (cls.isAssignableFrom(obj.getClass())) {
            return cls.cast(obj);
        }
        m2389a(6, "PRD", "cannot parse data for " + str, (Throwable) new Exception("cannot parse data for " + str));
        return t;
    }

    /* renamed from: a */
    public static String m2383a() {
        try {
            C4679d dVar = new C4679d();
            dVar.mo45414a(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/com.ebay.lid/");
            String str = "fb.bin";
            String b = dVar.mo45416b(str);
            if (!"".equals(b.trim())) {
                return b;
            }
            String b2 = m2400b(Boolean.TRUE.booleanValue());
            dVar.mo45415a(str, b2.getBytes(JPushConstants.ENCODING_UTF_8));
            return b2;
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m2384a(Context context, String str, String str2) {
        try {
            new StringBuilder("entering getMetadata loading name=").append(str);
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                new StringBuilder("leaving getMetadata successfully loading name=").append(str);
                return applicationInfo.metaData.getString(str);
            }
        } catch (NameNotFoundException e) {
            new StringBuilder("load metadata in manifest failed, name=").append(str);
        }
        new StringBuilder("leaving getMetadata with default value,name=").append(str);
        return null;
    }

    /* renamed from: a */
    public static String m2385a(String str) {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.update(str.getBytes());
        byte[] digest = instance.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : digest) {
            stringBuffer.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }
        return stringBuffer.toString().substring(0, 32);
    }

    /* renamed from: a */
    public static String m2386a(Map<String, Object> map, String str, String str2) {
        return (String) m2382a(map, String.class, str, (T) null);
    }

    /* renamed from: a */
    public static List<String> m2387a(boolean z) {
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        if (!(inetAddress instanceof Inet6Address)) {
                            arrayList.add(hostAddress);
                        } else if (z) {
                            arrayList.add(hostAddress);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return arrayList;
    }

    /* renamed from: a */
    public static void m2388a(int i, String str, String str2) {
        if (f3917b) {
            Log.println(i, str, str2);
        }
    }

    /* renamed from: a */
    public static void m2389a(int i, String str, String str2, Throwable th) {
        if (f3917b) {
            Log.println(6, str, str2 + 10 + Log.getStackTraceString(th));
        }
    }

    /* renamed from: a */
    public static void m2390a(String str, String str2) {
    }

    /* renamed from: a */
    public static void m2391a(String str, String str2, Throwable th) {
    }

    /* renamed from: a */
    public static void m2392a(String str, JSONObject jSONObject) {
        if (f3916a && jSONObject != null) {
            jSONObject.toString();
        }
    }

    /* renamed from: a */
    public static boolean m2393a(Context context, String str) {
        try {
            return context.getApplicationContext().checkCallingOrSelfPermission(str) == 0;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m2394a(PackageManager packageManager, Intent intent) {
        List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        return queryIntentActivities != null && queryIntentActivities.size() > 0;
    }

    /* renamed from: a */
    public static boolean m2395a(Map<String, Object> map, String str, Boolean bool) {
        return ((Boolean) m2382a(map, Boolean.class, str, (T) bool)).booleanValue();
    }

    /* renamed from: b */
    public static String m2396b() {
        List a = m2387a(false);
        return a.isEmpty() ? "" : (String) a.get(0);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.String[]] */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r2v3, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0, types: [java.lang.String[]]
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
      uses: [?[OBJECT, ARRAY], java.lang.String[], java.lang.String]
      mth insns count: 31
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m2397b(android.content.Context r6) {
        /*
            r1 = 1
            r2 = 0
            android.net.Uri r0 = f3919g
            if (r0 == 0) goto L_0x0025
            java.lang.String r0 = "com.google.android.providers.gsf.permission.READ_GSERVICES"
            boolean r0 = m2393a(r6, r0)
            if (r0 == 0) goto L_0x0025
            java.lang.String[] r4 = new java.lang.String[r1]
            r0 = 0
            java.lang.String r1 = "android_id"
            r4[r0] = r1
            android.content.ContentResolver r0 = r6.getContentResolver()
            android.net.Uri r1 = f3919g
            r3 = r2
            r5 = r2
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)
            if (r1 != 0) goto L_0x0026
        L_0x0025:
            return r2
        L_0x0026:
            boolean r0 = r1.moveToFirst()     // Catch:{ NumberFormatException -> 0x0048, all -> 0x004d }
            if (r0 == 0) goto L_0x0033
            int r0 = r1.getColumnCount()     // Catch:{ NumberFormatException -> 0x0048, all -> 0x004d }
            r3 = 2
            if (r0 >= r3) goto L_0x0037
        L_0x0033:
            r1.close()
            goto L_0x0025
        L_0x0037:
            r0 = 1
            java.lang.String r0 = r1.getString(r0)     // Catch:{ NumberFormatException -> 0x0048, all -> 0x004d }
            long r4 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x0048, all -> 0x004d }
            java.lang.String r2 = java.lang.Long.toHexString(r4)     // Catch:{ NumberFormatException -> 0x0048, all -> 0x004d }
            r1.close()
            goto L_0x0025
        L_0x0048:
            r0 = move-exception
            r1.close()
            goto L_0x0025
        L_0x004d:
            r0 = move-exception
            r1.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2397b(android.content.Context):java.lang.String");
    }

    /* renamed from: b */
    public static String m2398b(Context context, String str) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                bufferedReader.close();
                return new String(Base64.decode(sb.toString(), 0), JPushConstants.ENCODING_UTF_8);
            }
        }
    }

    /* renamed from: b */
    public static String m2399b(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if ((charAt >= '0' && charAt <= '9') || ((charAt >= 'A' && charAt <= 'F') || (charAt >= 'a' && charAt <= 'f'))) {
                int parseInt = Integer.parseInt(str.charAt(i2), 16);
                i += parseInt;
                arrayList.add(Integer.valueOf(parseInt));
            }
        }
        int i3 = i + 1;
        int size = arrayList.size() % 4;
        for (int i4 = 0; i4 < arrayList.size() - size; i4 += 4) {
            sb.append(Integer.toHexString((((Integer) arrayList.get((((Integer) arrayList.get(i4 + 3)).intValue() % 4) + i4)).intValue() + i3) % 16));
        }
        if (sb.toString().length() == 0) {
            return null;
        }
        return sb.toString().length() >= 4 ? sb.toString().substring(0, 4) : sb.toString();
    }

    /* renamed from: b */
    public static String m2400b(boolean z) {
        return z ? UUID.randomUUID().toString() : UUID.randomUUID().toString().replaceAll("-", "");
    }

    /* renamed from: b */
    public static boolean m2401b(String str, String str2) {
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        new StringBuilder("Cached version is ").append(str);
        new StringBuilder("default version is ").append(str2);
        int i = 0;
        while (i < split.length && i < split2.length && split[i].equals(split2[i])) {
            i++;
        }
        return ((i >= split.length || i >= split2.length) ? Integer.valueOf(Integer.signum(split.length - split2.length)) : Integer.valueOf(Integer.signum(Integer.valueOf(split[i]).compareTo(Integer.valueOf(split2[i]))))).intValue() >= 0;
    }

    /* renamed from: c */
    public static long m2402c() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (IllegalArgumentException e) {
            e.getLocalizedMessage();
            return 0;
        }
    }

    /* renamed from: c */
    public static String m2403c(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("RiskManagerAG", 0);
        String string = sharedPreferences.getString("RiskManagerAG", "");
        Editor edit = sharedPreferences.edit();
        if (str != null && !str.equals(string)) {
            edit.putString("RiskManagerAG", str);
            edit.commit();
            return str;
        } else if (!string.equals("")) {
            return string;
        } else {
            String b = m2400b(Boolean.TRUE.booleanValue());
            edit.putString("RiskManagerAG", b);
            edit.commit();
            return b;
        }
    }

    /* renamed from: c */
    public static void m2404c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("RiskManagerCT", 0);
        int i = sharedPreferences.getInt("RiskManagerCT", 0);
        Editor edit = sharedPreferences.edit();
        edit.putInt("RiskManagerCT", (i <= 0 || i >= Integer.MAX_VALUE) ? 1 : i + 1);
        edit.commit();
    }

    /* renamed from: d */
    public static String m2405d() {
        if (VERSION.SDK_INT >= 14) {
            String property = System.getProperty("http.proxyHost");
            if (property != null) {
                String property2 = System.getProperty("http.proxyPort");
                if (property2 != null) {
                    return "host=" + property + ",port=" + property2;
                }
            }
        }
        return null;
    }

    /* renamed from: d */
    public static String m2406d(Context context) {
        return context.getSharedPreferences("RiskManagerCT", 0).getInt("RiskManagerCT", 0);
    }

    /* renamed from: e */
    public static String m2407e() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                if (networkInterface.isUp() && networkInterface.getInterfaceAddresses().size() != 0) {
                    String name = networkInterface.getName();
                    if (name.startsWith("ppp") || name.startsWith("tun") || name.startsWith("tap")) {
                        return name;
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }
}
