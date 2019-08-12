package com.braintreepayments.api.internal;

public class SignatureVerification {
    static boolean sEnableSignatureVerification = true;

    /* JADX WARNING: Removed duplicated region for block: B:42:0x0086 A[SYNTHETIC, Splitter:B:42:0x0086] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0092 A[SYNTHETIC, Splitter:B:48:0x0092] */
    /* JADX WARNING: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isSignatureValid(android.content.Context r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, int r19) {
        /*
            boolean r12 = sEnableSignatureVerification
            if (r12 != 0) goto L_0x0006
            r10 = 1
        L_0x0005:
            return r10
        L_0x0006:
            android.content.pm.PackageManager r6 = r15.getPackageManager()
            r12 = 64
            r0 = r16
            android.content.pm.PackageInfo r12 = r6.getPackageInfo(r0, r12)     // Catch:{ NameNotFoundException -> 0x0070 }
            android.content.pm.Signature[] r8 = r12.signatures     // Catch:{ NameNotFoundException -> 0x0070 }
            r2 = 0
            int r12 = r8.length
            if (r12 == 0) goto L_0x0073
            r10 = 1
        L_0x0019:
            int r14 = r8.length
            r12 = 0
            r13 = r12
            r3 = r2
        L_0x001d:
            if (r13 >= r14) goto L_0x0005
            r7 = r8[r13]
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ CertificateException -> 0x0081, all -> 0x008e }
            byte[] r12 = r7.toByteArray()     // Catch:{ CertificateException -> 0x0081, all -> 0x008e }
            r2.<init>(r12)     // Catch:{ CertificateException -> 0x0081, all -> 0x008e }
            java.lang.String r12 = "X509"
            java.security.cert.CertificateFactory r12 = java.security.cert.CertificateFactory.getInstance(r12)     // Catch:{ CertificateException -> 0x009c, all -> 0x009a }
            java.security.cert.Certificate r11 = r12.generateCertificate(r2)     // Catch:{ CertificateException -> 0x009c, all -> 0x009a }
            java.security.cert.X509Certificate r11 = (java.security.cert.X509Certificate) r11     // Catch:{ CertificateException -> 0x009c, all -> 0x009a }
            javax.security.auth.x500.X500Principal r12 = r11.getSubjectX500Principal()     // Catch:{ CertificateException -> 0x009c, all -> 0x009a }
            java.lang.String r9 = r12.getName()     // Catch:{ CertificateException -> 0x009c, all -> 0x009a }
            javax.security.auth.x500.X500Principal r12 = r11.getIssuerX500Principal()     // Catch:{ CertificateException -> 0x009c, all -> 0x009a }
            java.lang.String r5 = r12.getName()     // Catch:{ CertificateException -> 0x009c, all -> 0x009a }
            java.security.PublicKey r12 = r11.getPublicKey()     // Catch:{ CertificateException -> 0x009c, all -> 0x009a }
            int r1 = r12.hashCode()     // Catch:{ CertificateException -> 0x009c, all -> 0x009a }
            r0 = r17
            boolean r12 = r0.equals(r9)     // Catch:{ CertificateException -> 0x009c, all -> 0x009a }
            if (r12 == 0) goto L_0x0075
            r0 = r18
            boolean r12 = r0.equals(r5)     // Catch:{ CertificateException -> 0x009c, all -> 0x009a }
            if (r12 == 0) goto L_0x0075
            r0 = r19
            if (r0 != r1) goto L_0x0075
            r12 = 1
        L_0x0064:
            r10 = r10 & r12
            if (r10 != 0) goto L_0x0077
            r10 = 0
            if (r2 == 0) goto L_0x0005
            r2.close()     // Catch:{ IOException -> 0x006e }
            goto L_0x0005
        L_0x006e:
            r12 = move-exception
            goto L_0x0005
        L_0x0070:
            r4 = move-exception
            r10 = 0
            goto L_0x0005
        L_0x0073:
            r10 = 0
            goto L_0x0019
        L_0x0075:
            r12 = 0
            goto L_0x0064
        L_0x0077:
            if (r2 == 0) goto L_0x007c
            r2.close()     // Catch:{ IOException -> 0x0096 }
        L_0x007c:
            int r12 = r13 + 1
            r13 = r12
            r3 = r2
            goto L_0x001d
        L_0x0081:
            r4 = move-exception
            r2 = r3
        L_0x0083:
            r10 = 0
            if (r2 == 0) goto L_0x0005
            r2.close()     // Catch:{ IOException -> 0x008b }
            goto L_0x0005
        L_0x008b:
            r12 = move-exception
            goto L_0x0005
        L_0x008e:
            r12 = move-exception
            r2 = r3
        L_0x0090:
            if (r2 == 0) goto L_0x0095
            r2.close()     // Catch:{ IOException -> 0x0098 }
        L_0x0095:
            throw r12
        L_0x0096:
            r12 = move-exception
            goto L_0x007c
        L_0x0098:
            r13 = move-exception
            goto L_0x0095
        L_0x009a:
            r12 = move-exception
            goto L_0x0090
        L_0x009c:
            r4 = move-exception
            goto L_0x0083
        */
        throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.internal.SignatureVerification.isSignatureValid(android.content.Context, java.lang.String, java.lang.String, java.lang.String, int):boolean");
    }
}
