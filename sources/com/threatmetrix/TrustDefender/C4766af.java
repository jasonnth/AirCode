package com.threatmetrix.TrustDefender;

/* renamed from: com.threatmetrix.TrustDefender.af */
class C4766af implements Runnable {

    /* renamed from: f */
    private static final String f4195f = C4834w.m2892a(C4766af.class);

    /* renamed from: a */
    private String f4196a = null;

    /* renamed from: b */
    private String f4197b = null;

    /* renamed from: c */
    private String f4198c = null;

    /* renamed from: d */
    private String f4199d = null;

    /* renamed from: e */
    private int f4200e = 10000;

    public C4766af(String fp_server, String org_id, String session_id, String w, int timeout) {
        this.f4196a = fp_server;
        this.f4197b = org_id;
        this.f4198c = session_id;
        this.f4199d = w;
        this.f4200e = timeout;
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:108:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ba A[SYNTHETIC, Splitter:B:34:0x00ba] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00bf A[SYNTHETIC, Splitter:B:37:0x00bf] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c4 A[SYNTHETIC, Splitter:B:40:0x00c4] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00e7 A[SYNTHETIC, Splitter:B:53:0x00e7] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ec A[SYNTHETIC, Splitter:B:56:0x00ec] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00f1 A[SYNTHETIC, Splitter:B:59:0x00f1] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x010c A[SYNTHETIC, Splitter:B:70:0x010c] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0111 A[SYNTHETIC, Splitter:B:73:0x0111] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0116 A[SYNTHETIC, Splitter:B:76:0x0116] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:49:0x00d5=Splitter:B:49:0x00d5, B:30:0x00a8=Splitter:B:30:0x00a8} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r13 = this;
            r8 = 0
            r5 = 0
            r6 = 0
            r3 = 0
            java.net.Socket r9 = new java.net.Socket     // Catch:{ UnknownHostException -> 0x00a7, IOException -> 0x00d4 }
            java.lang.String r10 = r13.f4196a     // Catch:{ UnknownHostException -> 0x00a7, IOException -> 0x00d4 }
            r11 = 8080(0x1f90, float:1.1322E-41)
            r9.<init>(r10, r11)     // Catch:{ UnknownHostException -> 0x00a7, IOException -> 0x00d4 }
            int r10 = r13.f4200e     // Catch:{ UnknownHostException -> 0x013e, IOException -> 0x0132, all -> 0x0126 }
            r9.setSoTimeout(r10)     // Catch:{ UnknownHostException -> 0x013e, IOException -> 0x0132, all -> 0x0126 }
            java.io.OutputStream r5 = r9.getOutputStream()     // Catch:{ UnknownHostException -> 0x013e, IOException -> 0x0132, all -> 0x0126 }
            java.io.BufferedWriter r7 = new java.io.BufferedWriter     // Catch:{ UnknownHostException -> 0x013e, IOException -> 0x0132, all -> 0x0126 }
            java.io.PrintWriter r10 = new java.io.PrintWriter     // Catch:{ UnknownHostException -> 0x013e, IOException -> 0x0132, all -> 0x0126 }
            r10.<init>(r5)     // Catch:{ UnknownHostException -> 0x013e, IOException -> 0x0132, all -> 0x0126 }
            r7.<init>(r10)     // Catch:{ UnknownHostException -> 0x013e, IOException -> 0x0132, all -> 0x0126 }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ UnknownHostException -> 0x0142, IOException -> 0x0135, all -> 0x0129 }
            java.io.InputStreamReader r10 = new java.io.InputStreamReader     // Catch:{ UnknownHostException -> 0x0142, IOException -> 0x0135, all -> 0x0129 }
            java.io.InputStream r11 = r9.getInputStream()     // Catch:{ UnknownHostException -> 0x0142, IOException -> 0x0135, all -> 0x0129 }
            r10.<init>(r11)     // Catch:{ UnknownHostException -> 0x0142, IOException -> 0x0135, all -> 0x0129 }
            r4.<init>(r10)     // Catch:{ UnknownHostException -> 0x0142, IOException -> 0x0135, all -> 0x0129 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            java.lang.String r11 = "<handle sig=FF44EE55 session_id="
            r10.<init>(r11)     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            java.lang.String r11 = r13.f4198c     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            java.lang.String r11 = " org_id="
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            java.lang.String r11 = r13.f4197b     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            java.lang.String r11 = " w="
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            java.lang.String r11 = r13.f4199d     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            java.lang.String r11 = " />"
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            java.lang.String r10 = r10.toString()     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            r7.write(r10)     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            r7.flush()     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            int r10 = r4.read()     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            r11 = -1
            if (r10 == r11) goto L_0x0083
            r10 = 1
            char[] r1 = new char[r10]     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            r10 = 0
            r11 = 0
            r1[r10] = r11     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            r2 = 0
        L_0x0076:
            r10 = 15
            if (r2 >= r10) goto L_0x0080
            r7.write(r1)     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
            int r2 = r2 + 1
            goto L_0x0076
        L_0x0080:
            r4.read()     // Catch:{ UnknownHostException -> 0x0147, IOException -> 0x0139, all -> 0x012d }
        L_0x0083:
            java.lang.String r10 = f4195f
            java.lang.String r11 = "Tidying up"
            com.threatmetrix.TrustDefender.C4834w.m2901c(r10, r11)
            r9.close()     // Catch:{ IOException -> 0x0098 }
        L_0x008e:
            r7.close()     // Catch:{ IOException -> 0x009c }
        L_0x0091:
            r4.close()     // Catch:{ IOException -> 0x00a0 }
            r3 = r4
            r6 = r7
            r8 = r9
        L_0x0097:
            return
        L_0x0098:
            r0 = move-exception
            java.lang.String r10 = f4195f
            goto L_0x008e
        L_0x009c:
            r0 = move-exception
            java.lang.String r10 = f4195f
            goto L_0x0091
        L_0x00a0:
            r0 = move-exception
            java.lang.String r10 = f4195f
            r3 = r4
            r6 = r7
            r8 = r9
            goto L_0x0097
        L_0x00a7:
            r0 = move-exception
        L_0x00a8:
            java.lang.String r10 = f4195f     // Catch:{ all -> 0x0101 }
            java.lang.String r11 = "Failed to connect to the fp server"
            com.threatmetrix.TrustDefender.C4834w.m2902c(r10, r11, r0)     // Catch:{ all -> 0x0101 }
            java.lang.String r10 = f4195f
            java.lang.String r11 = "Tidying up"
            com.threatmetrix.TrustDefender.C4834w.m2901c(r10, r11)
            if (r8 == 0) goto L_0x00bd
            r8.close()     // Catch:{ IOException -> 0x00cc }
        L_0x00bd:
            if (r6 == 0) goto L_0x00c2
            r6.close()     // Catch:{ IOException -> 0x00d0 }
        L_0x00c2:
            if (r3 == 0) goto L_0x0097
            r3.close()     // Catch:{ IOException -> 0x00c8 }
            goto L_0x0097
        L_0x00c8:
            r10 = move-exception
            java.lang.String r10 = f4195f
            goto L_0x0097
        L_0x00cc:
            r10 = move-exception
            java.lang.String r10 = f4195f
            goto L_0x00bd
        L_0x00d0:
            r10 = move-exception
            java.lang.String r10 = f4195f
            goto L_0x00c2
        L_0x00d4:
            r0 = move-exception
        L_0x00d5:
            java.lang.String r10 = f4195f     // Catch:{ all -> 0x0101 }
            java.lang.String r11 = "Failed to read/write to the fp server"
            com.threatmetrix.TrustDefender.C4834w.m2902c(r10, r11, r0)     // Catch:{ all -> 0x0101 }
            java.lang.String r10 = f4195f
            java.lang.String r11 = "Tidying up"
            com.threatmetrix.TrustDefender.C4834w.m2901c(r10, r11)
            if (r8 == 0) goto L_0x00ea
            r8.close()     // Catch:{ IOException -> 0x00f9 }
        L_0x00ea:
            if (r6 == 0) goto L_0x00ef
            r6.close()     // Catch:{ IOException -> 0x00fd }
        L_0x00ef:
            if (r3 == 0) goto L_0x0097
            r3.close()     // Catch:{ IOException -> 0x00f5 }
            goto L_0x0097
        L_0x00f5:
            r10 = move-exception
            java.lang.String r10 = f4195f
            goto L_0x0097
        L_0x00f9:
            r10 = move-exception
            java.lang.String r10 = f4195f
            goto L_0x00ea
        L_0x00fd:
            r10 = move-exception
            java.lang.String r10 = f4195f
            goto L_0x00ef
        L_0x0101:
            r10 = move-exception
        L_0x0102:
            java.lang.String r11 = f4195f
            java.lang.String r12 = "Tidying up"
            com.threatmetrix.TrustDefender.C4834w.m2901c(r11, r12)
            if (r8 == 0) goto L_0x010f
            r8.close()     // Catch:{ IOException -> 0x011a }
        L_0x010f:
            if (r6 == 0) goto L_0x0114
            r6.close()     // Catch:{ IOException -> 0x011e }
        L_0x0114:
            if (r3 == 0) goto L_0x0119
            r3.close()     // Catch:{ IOException -> 0x0122 }
        L_0x0119:
            throw r10
        L_0x011a:
            r11 = move-exception
            java.lang.String r11 = f4195f
            goto L_0x010f
        L_0x011e:
            r11 = move-exception
            java.lang.String r11 = f4195f
            goto L_0x0114
        L_0x0122:
            r11 = move-exception
            java.lang.String r11 = f4195f
            goto L_0x0119
        L_0x0126:
            r10 = move-exception
            r8 = r9
            goto L_0x0102
        L_0x0129:
            r10 = move-exception
            r6 = r7
            r8 = r9
            goto L_0x0102
        L_0x012d:
            r10 = move-exception
            r3 = r4
            r6 = r7
            r8 = r9
            goto L_0x0102
        L_0x0132:
            r0 = move-exception
            r8 = r9
            goto L_0x00d5
        L_0x0135:
            r0 = move-exception
            r6 = r7
            r8 = r9
            goto L_0x00d5
        L_0x0139:
            r0 = move-exception
            r3 = r4
            r6 = r7
            r8 = r9
            goto L_0x00d5
        L_0x013e:
            r0 = move-exception
            r8 = r9
            goto L_0x00a8
        L_0x0142:
            r0 = move-exception
            r6 = r7
            r8 = r9
            goto L_0x00a8
        L_0x0147:
            r0 = move-exception
            r3 = r4
            r6 = r7
            r8 = r9
            goto L_0x00a8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4766af.run():void");
    }
}
