package org.spongycastle.jce.provider;

import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.util.encoders.Base64;

public class PEMUtil {
    private final String _footer1;
    private final String _footer2;
    private final String _header1;
    private final String _header2;

    PEMUtil(String type) {
        this._header1 = "-----BEGIN " + type + "-----";
        this._header2 = "-----BEGIN X509 " + type + "-----";
        this._footer1 = "-----END " + type + "-----";
        this._footer2 = "-----END X509 " + type + "-----";
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0024  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String readLine(java.io.InputStream r5) throws java.io.IOException {
        /*
            r4 = this;
            r3 = 13
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
        L_0x0007:
            int r0 = r5.read()
            if (r0 == r3) goto L_0x001a
            r2 = 10
            if (r0 == r2) goto L_0x001a
            if (r0 < 0) goto L_0x001a
            if (r0 == r3) goto L_0x0007
            char r2 = (char) r0
            r1.append(r2)
            goto L_0x0007
        L_0x001a:
            if (r0 < 0) goto L_0x0022
            int r2 = r1.length()
            if (r2 == 0) goto L_0x0007
        L_0x0022:
            if (r0 >= 0) goto L_0x0026
            r2 = 0
        L_0x0025:
            return r2
        L_0x0026:
            java.lang.String r2 = r1.toString()
            goto L_0x0025
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.PEMUtil.readLine(java.io.InputStream):java.lang.String");
    }

    /* access modifiers changed from: 0000 */
    public ASN1Sequence readPEMObject(InputStream in) throws IOException {
        String line;
        StringBuffer pemBuf = new StringBuffer();
        do {
            line = readLine(in);
            if (line == null || line.startsWith(this._header1)) {
            }
        } while (!line.startsWith(this._header2));
        while (true) {
            String line2 = readLine(in);
            if (line2 != null && !line2.startsWith(this._footer1) && !line2.startsWith(this._footer2)) {
                pemBuf.append(line2);
            }
        }
        if (pemBuf.length() == 0) {
            return null;
        }
        ASN1Primitive o = new ASN1InputStream(Base64.decode(pemBuf.toString())).readObject();
        if (o instanceof ASN1Sequence) {
            return (ASN1Sequence) o;
        }
        throw new IOException("malformed PEM data encountered");
    }
}
