package com.threatmetrix.TrustDefender;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.SparseIntArray;
import com.facebook.common.util.UriUtil;
import com.facebook.internal.AnalyticsEvents;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;
import p005cn.jpush.android.JPushConstants.PushService;

/* renamed from: com.threatmetrix.TrustDefender.p */
class C4826p {

    /* renamed from: a */
    private static final String f4633a = C4834w.m2892a(C4826p.class);

    /* renamed from: b */
    private static SparseIntArray f4634b = new SparseIntArray(5);

    /* renamed from: com.threatmetrix.TrustDefender.p$a */
    enum C4827a {
        CDMA("CDMA", 3),
        GSM("GSM", 1),
        LTE("LTE", 0),
        UNKOWN("OTHER", 99),
        WCDMA("UMTS", 2);
        

        /* renamed from: f */
        private String f4641f;

        /* renamed from: g */
        private int f4642g;

        private C4827a(String label, int priority) {
            this.f4641f = label;
            this.f4642g = priority;
        }

        /* renamed from: a */
        public final String mo46089a() {
            return this.f4641f;
        }

        /* renamed from: b */
        public final int mo46090b() {
            return this.f4642g;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.p$b */
    static class C4828b {

        /* renamed from: a */
        int f4643a = 0;

        /* renamed from: b */
        int f4644b = 0;

        C4828b() {
        }
    }

    C4826p() {
    }

    static {
        Class<?> devicePolicyManager = C4787at.m2745b("android.app.admin.DevicePolicyManager");
        Object value = C4787at.m2737a((Class) devicePolicyManager, "ENCRYPTION_STATUS_UNSUPPORTED", (Object) null);
        if (value != null) {
            f4634b.put(((Integer) value).intValue(), 1);
        }
        Object value2 = C4787at.m2737a((Class) devicePolicyManager, "ENCRYPTION_STATUS_INACTIVE", (Object) null);
        if (value2 != null) {
            f4634b.put(((Integer) value2).intValue(), 2);
        }
        Object value3 = C4787at.m2737a((Class) devicePolicyManager, "ENCRYPTION_STATUS_ACTIVATING", (Object) null);
        if (value3 != null) {
            f4634b.put(((Integer) value3).intValue(), 4);
        }
        Object value4 = C4787at.m2737a((Class) devicePolicyManager, "ENCRYPTION_STATUS_ACTIVE", (Object) null);
        if (value4 != null) {
            f4634b.put(((Integer) value4).intValue(), 8);
        }
        Object value5 = C4787at.m2737a((Class) devicePolicyManager, "ENCRYPTION_STATUS_ACTIVE_DEFAULT_KEY", (Object) null);
        if (value5 != null) {
            f4634b.put(((Integer) value5).intValue(), 32);
        }
    }

    /* renamed from: a */
    static List<String> m2864a(Context context, List<String> paths) throws InterruptedException {
        ArrayList<String> foundURLs = new ArrayList<>();
        int not_matched = 0;
        if (paths != null && !paths.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (String p : paths) {
                URI url = null;
                try {
                    URI uri = new URI(p);
                    url = uri;
                } catch (URISyntaxException e) {
                    String str = f4633a;
                }
                if (url != null) {
                    if (url.getScheme() == null) {
                        String str2 = f4633a;
                        new StringBuilder("Failed to get url scheme from: ").append(url);
                    } else {
                        arrayList2.add(url);
                        StringBuilder sb = new StringBuilder(url.getScheme());
                        sb.append("://");
                        if (url.getHost() != null && !url.getHost().isEmpty()) {
                            sb.append(url.getHost());
                        } else if (url.getAuthority() != null && !url.getAuthority().isEmpty()) {
                            sb.append(url.getAuthority());
                        }
                        if (url.getPath() != null && !url.getPath().isEmpty()) {
                            sb.append(url.getPath());
                        }
                        if (url.getQuery() != null && !url.getQuery().isEmpty()) {
                            sb.append("?").append(url.getQuery());
                        }
                        arrayList.add(sb.toString());
                    }
                }
            }
            String[] nativeURLs = NativeGatherer.m2512a().mo45860a((String[]) arrayList.toArray(new String[arrayList.size()]));
            String buildTags = C4799b.f4349c;
            C4808i iVar = new C4808i(context);
            for (int i = 0; i < arrayList2.size(); i++) {
                URI url2 = (URI) arrayList2.get(i);
                String path = (String) arrayList.get(i);
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                boolean foundMatch = false;
                if (url2.getScheme().equals(UriUtil.LOCAL_FILE_SCHEME)) {
                    foundMatch = new File(url2).exists();
                } else if (url2.getScheme().equals(PushService.PARAM_TAGS)) {
                    if (buildTags != null) {
                        String tagToCheck = url2.getHost() == null ? url2.getAuthority() : url2.getHost();
                        if (tagToCheck != null && !tagToCheck.isEmpty()) {
                            foundMatch = buildTags.contains(tagToCheck);
                        }
                    }
                } else if (url2.getScheme().equals(JPushReportInterface.PKG)) {
                    String uri2 = url2.getHost() == null ? url2.getAuthority() : url2.getHost();
                    if (uri2 != null) {
                        foundMatch = iVar.mo46047a(uri2, C4803d.f4383a);
                    }
                } else if (url2.getScheme().equals("prop")) {
                    String property = url2.getHost() == null ? url2.getAuthority() : url2.getHost();
                    if (property != null) {
                        String value = url2.getQuery();
                        if (value != null) {
                            if (property.equals("ro.build.version.codename")) {
                                if (value.equalsIgnoreCase(C4800a.f4362b)) {
                                    foundMatch = true;
                                }
                            } else if (property.equals("ro.build.date.utc")) {
                                if (value.equals(Long.valueOf(C4799b.f4347a))) {
                                    foundMatch = true;
                                }
                            } else if (property.equals("ro.build.type")) {
                                if (value.equalsIgnoreCase(C4799b.f4348b)) {
                                    foundMatch = true;
                                }
                            } else if (property.equals("ro.build.tags")) {
                                if (value.equalsIgnoreCase(C4799b.f4349c)) {
                                    foundMatch = true;
                                }
                            } else if (property.equals("ro.build.host")) {
                                if (value.equalsIgnoreCase(C4799b.f4350d)) {
                                    foundMatch = true;
                                }
                            } else if (property.equals("ro.build.user")) {
                                if (value.equalsIgnoreCase(C4799b.f4352f)) {
                                    foundMatch = true;
                                }
                            } else if (!property.equals("ro.build.id")) {
                                ArrayList<String> keys = new ArrayList<>();
                                Collections.addAll(keys, new String[]{property});
                                String found = m2861a("/system/build.prop", keys, "=", false);
                                if (found == null || !value.equalsIgnoreCase(found)) {
                                    String found2 = m2861a("/default.prop", keys, "=", false);
                                    if (found2 != null && value.equalsIgnoreCase(found2)) {
                                        foundMatch = true;
                                    }
                                } else {
                                    foundMatch = true;
                                }
                            } else if (value.equalsIgnoreCase(C4799b.f4353g)) {
                                foundMatch = true;
                            }
                        }
                    }
                }
                if (foundMatch) {
                    foundURLs.add(path);
                } else {
                    not_matched++;
                }
            }
            C4834w.m2901c(f4633a, "matched " + not_matched + "/" + paths.size());
            if (nativeURLs != null && nativeURLs.length > 0) {
                String[] arr$ = nativeURLs;
                int len$ = nativeURLs.length;
                for (int i$ = 0; i$ < len$; i$++) {
                    foundURLs.add("a" + arr$[i$]);
                }
            }
            Collections.sort(foundURLs);
            if (!foundURLs.isEmpty() && C4834w.m2897a()) {
                String str3 = f4633a;
                new StringBuilder("found ").append(C4770ai.m2624a((List<String>) foundURLs, ";"));
            }
        }
        return foundURLs;
    }

    /* renamed from: a */
    static String m2862a(StringBuilder fontCount) throws InterruptedException {
        if (NativeGatherer.m2512a().mo45864b()) {
            List<String> fonts = NativeGatherer.m2512a().mo45871f("/system/fonts");
            if (fonts == null || fonts.isEmpty() || fonts.size() != 2) {
                return null;
            }
            String fontHash = (String) fonts.get(0);
            fontCount.append((String) fonts.get(1));
            return fontHash;
        }
        List<String> fonts2 = new ArrayList<>();
        String str = ".ttf";
        String[] list = new File("/system/fonts/").list();
        if (list != null) {
            for (String str2 : list) {
                if (str2 != null && str2.endsWith(str)) {
                    StringBuilder sb = new StringBuilder(str2);
                    fonts2.add(sb.substring(0, sb.length() - 4));
                }
            }
        }
        StringBuilder fontString = new StringBuilder();
        for (String f : fonts2) {
            fontString.append(f);
        }
        fontCount.append(fonts2.size());
        return C4770ai.m2628b(fontString.toString());
    }

    /* renamed from: a */
    static String m2858a() {
        Locale locale = Locale.getDefault();
        StringBuffer buffer = new StringBuffer();
        String language = locale.getLanguage();
        if (language != null) {
            buffer.append(language);
            String country = locale.getCountry();
            if (country != null) {
                buffer.append("-");
                buffer.append(country);
            }
        }
        return buffer.toString();
    }

    /* renamed from: b */
    static String m2866b() {
        Locale locale = Locale.getDefault();
        StringBuffer buffer = new StringBuffer();
        String language = locale.getLanguage();
        if (language != null) {
            buffer.append(language);
            String country = locale.getCountry();
            if (country != null) {
                buffer.append("_");
                buffer.append(country);
            }
        }
        return buffer.toString();
    }

    /* renamed from: a */
    static String m2859a(long freeSpace, long bootTime) throws InterruptedException {
        StringBuilder state = new StringBuilder();
        state.append(freeSpace).append("-").append(bootTime);
        String deviceState = C4770ai.m2628b(state.toString());
        C4834w.m2901c(f4633a, "getDeviceState: " + deviceState);
        return deviceState;
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ab A[SYNTHETIC, Splitter:B:51:0x00ab] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00d8 A[SYNTHETIC, Splitter:B:67:0x00d8] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m2861a(java.lang.String r13, java.util.List<java.lang.String> r14, java.lang.String r15, boolean r16) {
        /*
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r0 = 0
            if (r13 == 0) goto L_0x00ae
            if (r14 == 0) goto L_0x00ae
            java.io.File r3 = new java.io.File
            r3.<init>(r13)
            boolean r11 = r3.exists()
            if (r11 == 0) goto L_0x00ae
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ IOException -> 0x00f0 }
            java.io.FileReader r11 = new java.io.FileReader     // Catch:{ IOException -> 0x00f0 }
            r11.<init>(r3)     // Catch:{ IOException -> 0x00f0 }
            r1.<init>(r11)     // Catch:{ IOException -> 0x00f0 }
        L_0x001f:
            java.lang.String r10 = r1.readLine()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            if (r10 == 0) goto L_0x00dc
            java.lang.String r8 = ""
            if (r15 == 0) goto L_0x006e
            boolean r11 = r15.isEmpty()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            if (r11 != 0) goto L_0x006e
            java.util.List r9 = com.threatmetrix.TrustDefender.C4770ai.m2629b(r10, r15)     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            boolean r11 = r9.isEmpty()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            if (r11 != 0) goto L_0x001f
            r11 = 0
            java.lang.Object r11 = r9.get(r11)     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            java.lang.String r7 = r11.trim()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            int r11 = r7.length()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            if (r11 == 0) goto L_0x001f
            boolean r11 = r14.contains(r7)     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            if (r11 == 0) goto L_0x00bd
            int r11 = r9.size()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            r12 = 1
            if (r11 <= r12) goto L_0x0063
            r11 = 1
            java.lang.Object r11 = r9.get(r11)     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            java.lang.String r8 = r11.trim()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
        L_0x0063:
            if (r16 != 0) goto L_0x00bd
            r1.close()     // Catch:{ IOException -> 0x006a }
        L_0x0068:
            r0 = r1
        L_0x0069:
            return r8
        L_0x006a:
            r11 = move-exception
            java.lang.String r11 = f4633a
            goto L_0x0068
        L_0x006e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            r2.<init>()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            java.util.Iterator r4 = r14.iterator()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
        L_0x0077:
            boolean r11 = r4.hasNext()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            if (r11 == 0) goto L_0x00b3
            java.lang.Object r6 = r4.next()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            boolean r11 = r10.contains(r6)     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            if (r11 == 0) goto L_0x0077
            if (r16 != 0) goto L_0x0095
            r1.close()     // Catch:{ IOException -> 0x0091 }
        L_0x008e:
            r0 = r1
            r8 = r10
            goto L_0x0069
        L_0x0091:
            r11 = move-exception
            java.lang.String r11 = f4633a
            goto L_0x008e
        L_0x0095:
            int r11 = r2.length()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            if (r11 <= 0) goto L_0x00a1
            java.lang.String r11 = ","
            r2.append(r11)     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
        L_0x00a1:
            r2.append(r10)     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            goto L_0x0077
        L_0x00a5:
            r11 = move-exception
            r0 = r1
        L_0x00a7:
            java.lang.String r11 = f4633a     // Catch:{ all -> 0x00ee }
            if (r0 == 0) goto L_0x00ae
            r0.close()     // Catch:{ IOException -> 0x00e6 }
        L_0x00ae:
            java.lang.String r8 = r5.toString()
            goto L_0x0069
        L_0x00b3:
            int r11 = r2.length()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            if (r11 == 0) goto L_0x00bd
            java.lang.String r8 = r2.toString()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
        L_0x00bd:
            boolean r11 = r8.isEmpty()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            if (r11 != 0) goto L_0x001f
            int r11 = r5.length()     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            if (r11 <= 0) goto L_0x00cf
            java.lang.String r11 = ","
            r5.append(r11)     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
        L_0x00cf:
            r5.append(r8)     // Catch:{ IOException -> 0x00a5, all -> 0x00d4 }
            goto L_0x001f
        L_0x00d4:
            r11 = move-exception
            r0 = r1
        L_0x00d6:
            if (r0 == 0) goto L_0x00db
            r0.close()     // Catch:{ IOException -> 0x00ea }
        L_0x00db:
            throw r11
        L_0x00dc:
            r1.close()     // Catch:{ IOException -> 0x00e1 }
            r0 = r1
            goto L_0x00ae
        L_0x00e1:
            r11 = move-exception
            java.lang.String r11 = f4633a
            r0 = r1
            goto L_0x00ae
        L_0x00e6:
            r11 = move-exception
            java.lang.String r11 = f4633a
            goto L_0x00ae
        L_0x00ea:
            r12 = move-exception
            java.lang.String r12 = f4633a
            goto L_0x00db
        L_0x00ee:
            r11 = move-exception
            goto L_0x00d6
        L_0x00f0:
            r11 = move-exception
            goto L_0x00a7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4826p.m2861a(java.lang.String, java.util.List, java.lang.String, boolean):java.lang.String");
    }

    /* renamed from: a */
    static String m2860a(Context m_context, Context context) throws InterruptedException {
        String str = f4633a;
        StringBuilder fingerprint = new StringBuilder();
        if (Thread.currentThread().isInterrupted()) {
            return "";
        }
        try {
            Object telephonyService = context.getApplicationContext().getSystemService("phone");
            if (telephonyService == null || !(telephonyService instanceof TelephonyManager)) {
                return "";
            }
            TelephonyManager manager = (TelephonyManager) telephonyService;
            String carrierName = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            if (manager.getPhoneType() == 1) {
                carrierName = manager.getNetworkOperatorName();
            }
            fingerprint.append(carrierName);
            fingerprint.append(manager.getSimCountryIso());
            fingerprint.append(((((float) m2872e()) / 1024.0f) / 1024.0f) / 1024.0f);
            C4817j display = new C4817j(m_context);
            int x = display.mo46057a();
            int y = display.mo46058b();
            if (x >= y) {
                fingerprint.append(x).append("x").append(y);
            } else {
                fingerprint.append(y).append("x").append(x);
            }
            String str2 = f4633a;
            ArrayList arrayList = new ArrayList();
            Collections.addAll(arrayList, new String[]{"Processor", "Hardware", "Serial"});
            String a = m2861a("/proc/cpuinfo", arrayList, ":", true);
            C4834w.m2901c(f4633a, "getCPUInfo returned: " + a);
            fingerprint.append(a);
            String str3 = f4633a;
            ArrayList arrayList2 = new ArrayList();
            Collections.addAll(arrayList2, new String[]{"MemTotal"});
            String a2 = m2861a("/proc/meminfo", arrayList2, ":", true);
            C4834w.m2901c(f4633a, "getMemInfo returned: " + a2);
            fingerprint.append(a2);
            fingerprint.append(C4799b.f4355i).append(" ").append(C4799b.f4356j).append(" ").append(C4799b.f4358l).append(" ").append(C4799b.f4359m).append(" ").append(C4800a.f4361a);
            String str4 = f4633a;
            new StringBuilder("getDeviceFingerprint returned: hash(").append(fingerprint.toString()).append(")");
            return C4770ai.m2628b(fingerprint.toString());
        } catch (SecurityException e) {
            String str5 = f4633a;
            return "";
        } catch (Exception e2) {
            C4834w.m2901c(f4633a, e2.getMessage());
            return "";
        }
    }

    /* renamed from: a */
    static String m2863a(List<URI> paths) throws InterruptedException {
        String keys;
        StringBuilder found = new StringBuilder();
        if (paths == null || paths.isEmpty()) {
            return null;
        }
        for (URI uri : paths) {
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            } else if (uri != null) {
                if (uri.getScheme().equals(UriUtil.LOCAL_FILE_SCHEME)) {
                    File file = new File(uri.getPath());
                    String query = uri.getQuery();
                    if (query == null || query.isEmpty()) {
                        if (found.length() > 0) {
                            found.append(";");
                        }
                        found.append(uri.getPath()).append("=").append(file.exists() ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
                    } else if (file.exists()) {
                        Map<String, String> kvs = C4770ai.m2631d(query);
                        boolean isGrep = kvs.containsKey("grep");
                        if (isGrep || kvs.containsKey("keys")) {
                            String sep = (String) kvs.get("sep");
                            if (!isGrep && (sep == null || sep.isEmpty())) {
                                sep = ":";
                            }
                            if (isGrep) {
                                keys = (String) kvs.get("grep");
                            } else {
                                keys = (String) kvs.get("keys");
                            }
                            if (keys == null || keys.isEmpty()) {
                                break;
                            }
                            String info = m2861a(file.getAbsolutePath(), C4770ai.m2629b(keys, ","), sep, true);
                            if (info != null && !info.isEmpty()) {
                                if (found.length() > 0) {
                                    found.append(";");
                                }
                                found.append(uri.getPath()).append("=").append(info);
                            }
                        }
                    } else {
                        continue;
                    }
                } else if (uri.getScheme().equals("intro")) {
                    try {
                        String className = uri.getHost();
                        String methodOrFieldName = uri.getPath();
                        if (className == null || className.isEmpty()) {
                            C4834w.m2901c(f4633a, "getURLs: empty className");
                        } else if (methodOrFieldName == null || methodOrFieldName.isEmpty()) {
                            String str = f4633a;
                        } else {
                            if (methodOrFieldName.startsWith("/")) {
                                methodOrFieldName = methodOrFieldName.substring(1);
                            }
                            Class c = C4787at.m2745b(className);
                            if (c == null) {
                                String str2 = f4633a;
                                new StringBuilder("getURLs: failed to find class: ").append(className);
                            } else {
                                Method m = C4787at.m2743a(c, methodOrFieldName, new Class[0]);
                                if (m != null) {
                                    Object o = C4787at.m2741a((Object) c, m, new Object[0]);
                                    if (o != null) {
                                        String v = o.toString();
                                        if (v != null) {
                                            if (found.length() > 0) {
                                                found.append(";");
                                            }
                                            found.append(uri.getHost()).append(uri.getPath()).append("=").append(v);
                                        }
                                    }
                                } else {
                                    Field f = C4787at.m2742a(c, methodOrFieldName);
                                    if (f != null) {
                                        Object o2 = C4787at.m2740a((Object) c, f);
                                        if (o2 != null && (o2 instanceof String)) {
                                            if (found.length() > 0) {
                                                found.append(";");
                                            }
                                            found.append(uri.getHost()).append(uri.getPath()).append("=").append((String) o2);
                                        }
                                    } else {
                                        String str3 = f4633a;
                                        new StringBuilder("getURLs: failed to find method or field: ").append(methodOrFieldName);
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        if (found.length() > 0) {
            String str4 = f4633a;
            new StringBuilder("found ").append(found.toString());
        }
        return found.toString();
    }

    /* renamed from: a */
    static boolean m2865a(C4828b tzInfo) {
        if (tzInfo == null) {
            throw new IllegalArgumentException("tzInfo cannot be null");
        }
        TimeZone tz = TimeZone.getDefault();
        if (tz != null) {
            tzInfo.f4643a = tz.getRawOffset() / JPushConstants.ONE_MINUTE;
            tzInfo.f4644b = tz.getDSTSavings() / JPushConstants.ONE_MINUTE;
            C4834w.m2901c(f4633a, "getTimeZoneInfo: dstDiff=" + tzInfo.f4644b + " gmfOffset=" + tzInfo.f4643a);
            return true;
        }
        String str = f4633a;
        return false;
    }

    /* renamed from: a */
    static C4835x m2857a(Context context) throws InterruptedException {
        return new C4835x(context);
    }

    /* renamed from: c */
    static long m2868c() {
        long bootTime = (System.currentTimeMillis() - C4812m.m2809b()) / 1000;
        C4834w.m2901c(f4633a, " getBootTime: " + bootTime);
        return bootTime;
    }

    /* renamed from: b */
    static String m2867b(Context context) {
        String unknown = "-";
        String packageName = new C4798a(context).mo46041a();
        C4807h packageInfo = new C4807h(context, packageName, C4803d.f4384b);
        int versionCode = packageInfo.mo46044a();
        String versionName = packageInfo.mo46045b();
        StringBuilder builder = new StringBuilder();
        StringBuilder append = builder.append(packageName).append(":");
        if (versionName == 0) {
            versionName = unknown;
        }
        append.append(versionName).append(":").append(versionCode == -1 ? unknown : Integer.valueOf(versionCode));
        String binaryArch = NativeGatherer.m2512a().mo45874h();
        StringBuilder append2 = builder.append(":");
        if (binaryArch != 0) {
            unknown = binaryArch;
        }
        append2.append(unknown);
        C4834w.m2901c(f4633a, "Application Info " + builder.toString());
        return builder.toString();
    }

    /* renamed from: d */
    static long m2871d() {
        C4769ah wrapper = new C4769ah(Environment.getDataDirectory().getPath());
        long availableBlocks = wrapper.mo45968c();
        long blockSizeInBytes = wrapper.mo45967b();
        long freeSpaceInMeg = 0;
        if (!(availableBlocks == 0 || blockSizeInBytes == 0)) {
            freeSpaceInMeg = ((((availableBlocks * blockSizeInBytes) >> 20) << 20) / 10) * 10;
        }
        C4834w.m2901c(f4633a, "Free space on the phone " + freeSpaceInMeg);
        return freeSpaceInMeg;
    }

    /* renamed from: e */
    static long m2872e() {
        C4769ah wrapper = new C4769ah(Environment.getDataDirectory().getPath());
        return wrapper.mo45966a() * wrapper.mo45967b();
    }

    /* renamed from: c */
    static boolean m2869c(Context context) {
        String mockLocation = C4810k.m2800a(context.getContentResolver(), "ALLOW_MOCK_LOCATION");
        return mockLocation != null && mockLocation.equals("1");
    }

    /* renamed from: f */
    static String m2874f() {
        return "android";
    }

    /* renamed from: g */
    static String m2876g() {
        return InternalLogger.EVENT_PARAM_SDK_ANDROID;
    }

    @TargetApi(11)
    /* renamed from: d */
    static int m2870d(Context context) {
        if (C4800a.f4363c < C4801b.f4368d) {
            return 1;
        }
        int value = f4634b.get(C4829q.m2881a(context));
        if (value != 0) {
            return value;
        }
        return 16;
    }

    /* renamed from: e */
    static String m2873e(Context context) {
        if (!C4813n.m2811b()) {
            return null;
        }
        try {
            Object telephonyService = context.getApplicationContext().getSystemService("phone");
            if (telephonyService == null || !(telephonyService instanceof TelephonyManager)) {
                return null;
            }
            TelephonyManager telephonyManager = (TelephonyManager) telephonyService;
            Map<String, String> cellTowerInformation = new HashMap<>();
            try {
                String no = telephonyManager.getNetworkOperator();
                String ncIso = telephonyManager.getNetworkCountryIso();
                String non = telephonyManager.getNetworkOperatorName();
                if (no != null && !"".equals(no.trim())) {
                    cellTowerInformation.put("no", no);
                }
                if (non != null && !"".equals(non.trim())) {
                    cellTowerInformation.put("non", non);
                }
                if (ncIso != null && !"".equals(ncIso.trim())) {
                    cellTowerInformation.put("nc_iso", ncIso);
                }
            } catch (SecurityException e) {
                String str = f4633a;
            } catch (Exception e2) {
                C4834w.m2901c(f4633a, e2.getMessage());
            }
            C4808i iVar = new C4808i(context);
            boolean finePermission = iVar.mo46048a("android.permission.ACCESS_FINE_LOCATION", context.getPackageName());
            boolean coarsePermission = iVar.mo46048a("android.permission.ACCESS_COARSE_LOCATION", context.getPackageName());
            if (finePermission || coarsePermission) {
                if (C4800a.f4363c >= C4801b.f4375k) {
                    Map<String, String> api18Info = C4795f.m2764a(context);
                    if (api18Info != null) {
                        cellTowerInformation.putAll(api18Info);
                    }
                }
                if (C4813n.m2812c() || C4813n.m2813d()) {
                    CellLocation cellLocation = null;
                    try {
                        cellLocation = telephonyManager.getCellLocation();
                    } catch (SecurityException e3) {
                        String str2 = f4633a;
                    } catch (Exception e4) {
                        C4834w.m2901c(f4633a, e4.getMessage());
                    }
                    if (cellLocation != null) {
                        if (cellLocation instanceof GsmCellLocation) {
                            GsmCellLocation loc = (GsmCellLocation) telephonyManager.getCellLocation();
                            int temp = loc.getLac();
                            if (temp != -1) {
                                cellTowerInformation.put("lac", String.valueOf(temp));
                            }
                            int temp2 = loc.getCid();
                            if (temp2 != -1) {
                                cellTowerInformation.put("cid", String.valueOf(temp2));
                            }
                            int temp3 = loc.getPsc();
                            if (temp3 != -1) {
                                cellTowerInformation.put("psc", String.valueOf(temp3));
                            }
                        } else if (cellLocation instanceof CdmaCellLocation) {
                            CdmaCellLocation loc2 = (CdmaCellLocation) telephonyManager.getCellLocation();
                            int temp4 = loc2.getSystemId();
                            if (temp4 != -1) {
                                cellTowerInformation.put("sid", String.valueOf(temp4));
                            }
                            int temp5 = loc2.getBaseStationId();
                            if (temp5 != -1) {
                                cellTowerInformation.put("bsid", String.valueOf(temp5));
                            }
                            int temp6 = loc2.getBaseStationLatitude();
                            if (temp6 != Integer.MAX_VALUE) {
                                cellTowerInformation.put("bs_lat", String.valueOf(temp6));
                            }
                            int temp7 = loc2.getBaseStationLongitude();
                            if (temp7 != Integer.MAX_VALUE) {
                                cellTowerInformation.put("bs_lng", String.valueOf(temp7));
                            }
                        }
                    }
                }
                if (C4813n.m2814e()) {
                    List<NeighboringCellInfo> list = null;
                    try {
                        Method getNeighboringCellInfo = C4787at.m2746b(TelephonyManager.class, "getNeighboringCellInfo", new Class[0]);
                        if (getNeighboringCellInfo != null) {
                            Object list2 = C4787at.m2741a((Object) telephonyManager, getNeighboringCellInfo, new Object[0]);
                            if (list2 != null && (list2 instanceof List)) {
                                list = (List) list2;
                            }
                        }
                    } catch (SecurityException e5) {
                        String str3 = f4633a;
                    } catch (Exception e6) {
                        C4834w.m2901c(f4633a, e6.getMessage());
                    }
                    if (list != null && list.size() > 0) {
                        for (NeighboringCellInfo info : list) {
                            if (!(info.getCid() == -1 || info.getRssi() == 99)) {
                                if (!String.valueOf(info.getCid()).equalsIgnoreCase((String) cellTowerInformation.get("cid"))) {
                                    if (!String.valueOf(info.getCid()).equalsIgnoreCase((String) cellTowerInformation.get("bsid"))) {
                                    }
                                }
                                cellTowerInformation.put("sl_ASU", String.valueOf(info.getRssi()));
                            }
                        }
                    }
                }
            }
            if (cellTowerInformation.size() >= 3) {
                return cellTowerInformation.toString();
            }
            return null;
        } catch (SecurityException e7) {
            String str4 = f4633a;
            return null;
        } catch (Exception e8) {
            C4834w.m2901c(f4633a, e8.getMessage());
            return null;
        }
    }

    /* renamed from: f */
    static String m2875f(Context context) {
        return new C4820l().mo46071b(context);
    }

    /* renamed from: g */
    static String m2877g(Context context) {
        if (new C4808i(context).mo46048a("android.permission.READ_PHONE_STATE", context.getPackageName())) {
            try {
                Object telephonyService = context.getApplicationContext().getSystemService("phone");
                if (telephonyService != null && (telephonyService instanceof TelephonyManager)) {
                    TelephonyManager telephonyManager = (TelephonyManager) telephonyService;
                    Map<String, String> result = new HashMap<>();
                    C4770ai.m2627a(telephonyManager.getDeviceId(), true, "di", result);
                    C4770ai.m2627a(telephonyManager.getLine1Number(), true, "ln", result);
                    C4770ai.m2627a(telephonyManager.getSimSerialNumber(), true, "ss", result);
                    C4770ai.m2627a(telephonyManager.getSubscriberId(), true, "si", result);
                    C4770ai.m2627a(telephonyManager.getVoiceMailNumber(), true, "vn", result);
                    C4770ai.m2627a(telephonyManager.getDeviceSoftwareVersion(), false, "sv", result);
                    String data = telephonyManager.getVoiceMailAlphaTag();
                    if (C4770ai.m2633f(data) && !data.equalsIgnoreCase("VoiceMail")) {
                        result.put("VMAlphaTag", data);
                    }
                    if (C4800a.f4363c >= 22) {
                        result.putAll(C4831s.m2883a(context));
                    }
                    JSONObject json = new JSONObject();
                    for (String key : result.keySet()) {
                        try {
                            json.put(key, result.get(key));
                        } catch (JSONException exception) {
                            String str = f4633a;
                            new StringBuilder("Can't create JSON: ").append(exception.getMessage());
                            return "";
                        }
                    }
                    return json.toString();
                }
            } catch (SecurityException e) {
                String str2 = f4633a;
                return "";
            } catch (Exception e2) {
                C4834w.m2901c(f4633a, e2.getMessage());
                return "";
            }
        }
        return "";
    }

    /* renamed from: h */
    static String m2878h(Context context) {
        if (!C4813n.m2811b()) {
            return null;
        }
        String cellId = null;
        C4808i packageManager = new C4808i(context);
        boolean finePermission = packageManager.mo46048a("android.permission.ACCESS_FINE_LOCATION", context.getPackageName());
        boolean coarsePermission = packageManager.mo46048a("android.permission.ACCESS_COARSE_LOCATION", context.getPackageName());
        if (!finePermission && !coarsePermission) {
            return null;
        }
        if (C4800a.f4363c >= C4801b.f4375k) {
            cellId = C4795f.m2766b(context);
        }
        if (!C4770ai.m2632e(cellId)) {
            return cellId;
        }
        if (!C4813n.m2812c() && !C4813n.m2813d()) {
            return cellId;
        }
        try {
            Object telephonyService = context.getApplicationContext().getSystemService("phone");
            if (telephonyService == null || !(telephonyService instanceof TelephonyManager)) {
                return null;
            }
            TelephonyManager telephonyManager = (TelephonyManager) telephonyService;
            CellLocation cellLocation = null;
            try {
                cellLocation = telephonyManager.getCellLocation();
            } catch (SecurityException e) {
                String str = f4633a;
            } catch (Exception e2) {
                C4834w.m2901c(f4633a, e2.getMessage());
            }
            if (cellLocation == null) {
                return cellId;
            }
            StringBuilder cellInfo = new StringBuilder();
            if (cellLocation instanceof GsmCellLocation) {
                GsmCellLocation loc = (GsmCellLocation) telephonyManager.getCellLocation();
                int cid = loc.getCid();
                int lac = loc.getLac();
                if (cid == -1 && lac == -1) {
                    return null;
                }
                cellInfo.append("GSM:");
                if (cid != -1) {
                    cellInfo.append(cid);
                }
                cellInfo.append(":::");
                if (lac != -1) {
                    cellInfo.append(lac);
                }
                return cellInfo.toString();
            } else if (!(cellLocation instanceof CdmaCellLocation)) {
                return cellId;
            } else {
                CdmaCellLocation loc2 = (CdmaCellLocation) telephonyManager.getCellLocation();
                int bid = loc2.getBaseStationId();
                int sid = loc2.getSystemId();
                int nid = loc2.getNetworkId();
                if (bid == -1 && sid == -1 && nid == -1) {
                    return null;
                }
                cellInfo.append("CDMA:");
                if (bid != -1) {
                    cellInfo.append(bid);
                }
                cellInfo.append(":");
                if (sid != -1) {
                    cellInfo.append(sid);
                }
                cellInfo.append(":");
                if (nid != -1) {
                    cellInfo.append(nid);
                }
                return cellInfo.toString();
            }
        } catch (SecurityException e3) {
            String str2 = f4633a;
            return cellId;
        } catch (Exception e4) {
            C4834w.m2901c(f4633a, e4.getMessage());
            return cellId;
        }
    }
}
