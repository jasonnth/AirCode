package com.paypal.android.sdk.onetouch.core.metadata;

import android.os.Handler;
import android.os.Message;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.s */
public class C4691s extends C4670aa {

    /* renamed from: a */
    private static final String f4027a = C4691s.class.getSimpleName();

    /* renamed from: b */
    private Handler f4028b;

    /* renamed from: c */
    private String f4029c;

    /* renamed from: d */
    private String f4030d;

    /* renamed from: e */
    private String f4031e;

    /* renamed from: f */
    private C4669a f4032f;

    public C4691s(String str, String str2, String str3, C4669a aVar, Handler handler) {
        this.f4028b = handler;
        this.f4029c = str;
        this.f4030d = str2;
        this.f4031e = str3;
        this.f4032f = aVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m2479a(java.lang.String r11) {
        /*
            r10 = this;
            r2 = 0
            java.lang.String r4 = ""
            java.net.URL r1 = new java.net.URL     // Catch:{ Exception -> 0x00be, all -> 0x00d7 }
            r1.<init>(r11)     // Catch:{ Exception -> 0x00be, all -> 0x00d7 }
            java.net.URLConnection r0 = r1.openConnection()     // Catch:{ Exception -> 0x00be, all -> 0x00d7 }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Exception -> 0x00be, all -> 0x00d7 }
            r3 = 60000(0xea60, float:8.4078E-41)
            r0.setReadTimeout(r3)     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            r3 = 60000(0xea60, float:8.4078E-41)
            r0.setConnectTimeout(r3)     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.String r3 = "GET"
            r0.setRequestMethod(r3)     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.String r3 = "User-Agent"
            java.lang.String r5 = "%s/%s/%s/%s/Android"
            r6 = 4
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            r7 = 0
            com.paypal.android.sdk.onetouch.core.metadata.a r8 = r10.f4032f     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.String r8 = r8.mo45389a()     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            r6[r7] = r8     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            r7 = 1
            com.paypal.android.sdk.onetouch.core.metadata.a r8 = r10.f4032f     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.String r8 = r8.mo45391b()     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            r6[r7] = r8     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            r7 = 2
            java.lang.String r8 = r10.f4031e     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            r6[r7] = r8     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            r7 = 3
            java.lang.String r8 = r10.f4030d     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            r6[r7] = r8     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.String r5 = java.lang.String.format(r5, r6)     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            r0.setRequestProperty(r3, r5)     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.String r3 = "Accept-Language"
            java.lang.String r5 = "en-us"
            r0.setRequestProperty(r3, r5)     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            int r3 = r0.getResponseCode()     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.String r5 = f4027a     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.String r7 = "\nSending 'GET' request to URL : "
            r6.<init>(r7)     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.StringBuilder r1 = r6.append(r1)     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2390a(r5, r1)     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.String r1 = f4027a     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.String r6 = "Response Code : "
            r5.<init>(r6)     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.StringBuilder r3 = r5.append(r3)     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2390a(r1, r3)     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            java.io.InputStream r5 = r0.getInputStream()     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            r3.<init>(r1)     // Catch:{ Exception -> 0x00ed, all -> 0x00e2 }
            r1 = r4
        L_0x0093:
            java.lang.String r2 = r3.readLine()     // Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
            if (r2 == 0) goto L_0x00ab
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
            r4.<init>()     // Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
            java.lang.StringBuilder r4 = r4.append(r1)     // Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
            java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
            goto L_0x0093
        L_0x00ab:
            java.lang.String r2 = f4027a     // Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
            java.lang.String r4 = r1.toString()     // Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
            com.paypal.android.sdk.onetouch.core.metadata.C4677ai.m2390a(r2, r4)     // Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
            com.paypal.android.sdk.onetouch.core.metadata.C4674af.m2376a(r3)
            if (r0 == 0) goto L_0x00f8
            r0.disconnect()
            r0 = r1
        L_0x00bd:
            return r0
        L_0x00be:
            r0 = move-exception
            r1 = r0
            r3 = r2
            r0 = r4
        L_0x00c2:
            android.os.Handler r4 = r10.f4028b     // Catch:{ all -> 0x00eb }
            android.os.Handler r5 = r10.f4028b     // Catch:{ all -> 0x00eb }
            r6 = 1
            android.os.Message r1 = android.os.Message.obtain(r5, r6, r1)     // Catch:{ all -> 0x00eb }
            r4.sendMessage(r1)     // Catch:{ all -> 0x00eb }
            com.paypal.android.sdk.onetouch.core.metadata.C4674af.m2376a(r3)
            if (r2 == 0) goto L_0x00bd
            r2.disconnect()
            goto L_0x00bd
        L_0x00d7:
            r0 = move-exception
            r3 = r2
        L_0x00d9:
            com.paypal.android.sdk.onetouch.core.metadata.C4674af.m2376a(r3)
            if (r2 == 0) goto L_0x00e1
            r2.disconnect()
        L_0x00e1:
            throw r0
        L_0x00e2:
            r1 = move-exception
            r3 = r2
            r2 = r0
            r0 = r1
            goto L_0x00d9
        L_0x00e7:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L_0x00d9
        L_0x00eb:
            r0 = move-exception
            goto L_0x00d9
        L_0x00ed:
            r1 = move-exception
            r3 = r2
            r2 = r0
            r0 = r4
            goto L_0x00c2
        L_0x00f2:
            r2 = move-exception
            r9 = r2
            r2 = r0
            r0 = r1
            r1 = r9
            goto L_0x00c2
        L_0x00f8:
            r0 = r1
            goto L_0x00bd
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paypal.android.sdk.onetouch.core.metadata.C4691s.m2479a(java.lang.String):java.lang.String");
    }

    public void run() {
        if (this.f4028b != null) {
            try {
                this.f4028b.sendMessage(Message.obtain(this.f4028b, 20, this.f4029c));
                String a = m2479a(this.f4029c);
                C4677ai.m2390a(f4027a, String.format("%s/%s/%s/%s/Android", new Object[]{this.f4032f.mo45389a(), this.f4032f.mo45391b(), this.f4031e, this.f4030d}));
                this.f4028b.sendMessage(Message.obtain(this.f4028b, 22, a.toString()));
            } catch (Exception e) {
                this.f4028b.sendMessage(Message.obtain(this.f4028b, 21, e));
            } finally {
                C4671ab.m2370a().mo45395b(this);
            }
        }
    }
}
