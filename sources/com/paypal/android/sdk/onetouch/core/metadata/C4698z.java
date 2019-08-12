package com.paypal.android.sdk.onetouch.core.metadata;

import android.net.Uri;
import java.util.Map;
import p005cn.jpush.android.JPushConstants;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.z */
public class C4698z implements C4692t {

    /* renamed from: g */
    private static int f4042g = JPushConstants.ONE_MINUTE;

    /* renamed from: h */
    private static int f4043h = JPushConstants.ONE_MINUTE;

    /* renamed from: c */
    private final C4673ae f4044c = new C4673ae(C4696x.m2485a());

    /* renamed from: d */
    private byte[] f4045d;

    /* renamed from: e */
    private Uri f4046e;

    /* renamed from: f */
    private Map<String, String> f4047f;

    static {
        C4698z.class.getSimpleName();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0065  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int mo45434a(byte[] r10) {
        /*
            r9 = this;
            r2 = 0
            java.net.URL r0 = new java.net.URL     // Catch:{ all -> 0x00bd }
            android.net.Uri r1 = r9.f4046e     // Catch:{ all -> 0x00bd }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00bd }
            r0.<init>(r1)     // Catch:{ all -> 0x00bd }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ all -> 0x00bd }
            javax.net.ssl.HttpsURLConnection r0 = (javax.net.ssl.HttpsURLConnection) r0     // Catch:{ all -> 0x00bd }
            int r1 = f4043h     // Catch:{ all -> 0x0059 }
            r0.setReadTimeout(r1)     // Catch:{ all -> 0x0059 }
            int r1 = f4042g     // Catch:{ all -> 0x0059 }
            r0.setConnectTimeout(r1)     // Catch:{ all -> 0x0059 }
            java.lang.String r1 = "POST"
            r0.setRequestMethod(r1)     // Catch:{ all -> 0x0059 }
            r1 = 1
            r0.setDoInput(r1)     // Catch:{ all -> 0x0059 }
            r1 = 1
            r0.setDoOutput(r1)     // Catch:{ all -> 0x0059 }
            com.paypal.android.sdk.onetouch.core.metadata.ae r1 = r9.f4044c     // Catch:{ all -> 0x0059 }
            r0.setSSLSocketFactory(r1)     // Catch:{ all -> 0x0059 }
            java.util.Map<java.lang.String, java.lang.String> r1 = r9.f4047f     // Catch:{ all -> 0x0059 }
            java.util.Set r1 = r1.entrySet()     // Catch:{ all -> 0x0059 }
            java.util.Iterator r3 = r1.iterator()     // Catch:{ all -> 0x0059 }
        L_0x0039:
            boolean r1 = r3.hasNext()     // Catch:{ all -> 0x0059 }
            if (r1 == 0) goto L_0x0069
            java.lang.Object r1 = r3.next()     // Catch:{ all -> 0x0059 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x0059 }
            java.lang.Object r4 = r1.getKey()     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0059 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x0059 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0059 }
            r0.setRequestProperty(r4, r1)     // Catch:{ all -> 0x0059 }
            goto L_0x0039
        L_0x0059:
            r1 = move-exception
            r3 = r0
            r0 = r1
            r1 = r2
        L_0x005d:
            com.paypal.android.sdk.onetouch.core.metadata.C4674af.m2376a(r2)
            com.paypal.android.sdk.onetouch.core.metadata.C4674af.m2376a(r1)
            if (r3 == 0) goto L_0x0068
            r3.disconnect()
        L_0x0068:
            throw r0
        L_0x0069:
            int r1 = r10.length     // Catch:{ all -> 0x0059 }
            r0.setFixedLengthStreamingMode(r1)     // Catch:{ all -> 0x0059 }
            java.io.OutputStream r3 = r0.getOutputStream()     // Catch:{ all -> 0x0059 }
            r3.write(r10)     // Catch:{ all -> 0x00c1 }
            r3.flush()     // Catch:{ all -> 0x00c1 }
            int r4 = r0.getResponseCode()     // Catch:{ all -> 0x00c1 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r4 != r1) goto L_0x00b6
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ all -> 0x00c1 }
            java.io.InputStream r5 = r0.getInputStream()     // Catch:{ all -> 0x00c1 }
            r1.<init>(r5)     // Catch:{ all -> 0x00c1 }
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x009d }
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x009d }
            r5.<init>()     // Catch:{ all -> 0x009d }
        L_0x0091:
            int r6 = r1.read(r2)     // Catch:{ all -> 0x009d }
            r7 = -1
            if (r6 == r7) goto L_0x00a4
            r7 = 0
            r5.write(r2, r7, r6)     // Catch:{ all -> 0x009d }
            goto L_0x0091
        L_0x009d:
            r2 = move-exception
            r8 = r2
            r2 = r1
            r1 = r3
            r3 = r0
            r0 = r8
            goto L_0x005d
        L_0x00a4:
            byte[] r2 = r5.toByteArray()     // Catch:{ all -> 0x009d }
            r9.f4045d = r2     // Catch:{ all -> 0x009d }
        L_0x00aa:
            com.paypal.android.sdk.onetouch.core.metadata.C4674af.m2376a(r1)
            com.paypal.android.sdk.onetouch.core.metadata.C4674af.m2376a(r3)
            if (r0 == 0) goto L_0x00b5
            r0.disconnect()
        L_0x00b5:
            return r4
        L_0x00b6:
            r1 = 0
            byte[] r1 = new byte[r1]     // Catch:{ all -> 0x00c1 }
            r9.f4045d = r1     // Catch:{ all -> 0x00c1 }
            r1 = r2
            goto L_0x00aa
        L_0x00bd:
            r0 = move-exception
            r1 = r2
            r3 = r2
            goto L_0x005d
        L_0x00c1:
            r1 = move-exception
            r8 = r1
            r1 = r3
            r3 = r0
            r0 = r8
            goto L_0x005d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paypal.android.sdk.onetouch.core.metadata.C4698z.mo45434a(byte[]):int");
    }

    /* renamed from: a */
    public final void mo45435a(Uri uri) {
        this.f4046e = uri;
    }

    /* renamed from: a */
    public final void mo45436a(Map<String, String> map) {
        this.f4047f = map;
    }

    /* renamed from: b */
    public final byte[] mo45437b() {
        return this.f4045d;
    }
}
