package org.jmrtd.cbeff;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Logger;
import net.p318sf.scuba.tlv.TLVInputStream;
import net.p318sf.scuba.tlv.TLVUtil;
import org.spongycastle.crypto.tls.CipherSuite;

public class ISO781611Decoder implements ISO781611 {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private BiometricDataBlockDecoder<?> bdbDecoder;

    public ISO781611Decoder(BiometricDataBlockDecoder<?> biometricDataBlockDecoder) {
        this.bdbDecoder = biometricDataBlockDecoder;
    }

    public ComplexCBEFFInfo decode(InputStream inputStream) throws IOException {
        return readBITGroup(inputStream);
    }

    private ComplexCBEFFInfo readBITGroup(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int readTag = tLVInputStream.readTag();
        switch (readTag) {
            case 32609:
                return readBITGroup(readTag, tLVInputStream.readLength(), inputStream);
            default:
                throw new IllegalArgumentException("Expected tag " + Integer.toHexString(32609) + ", found " + Integer.toHexString(readTag));
        }
    }

    private ComplexCBEFFInfo readBITGroup(int i, int i2, InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        ComplexCBEFFInfo complexCBEFFInfo = new ComplexCBEFFInfo();
        if (i != 32609) {
            throw new IllegalArgumentException("Expected tag " + Integer.toHexString(32609) + ", found " + Integer.toHexString(i));
        }
        int readTag = tLVInputStream.readTag();
        if (readTag != 2) {
            throw new IllegalArgumentException("Expected tag BIOMETRIC_INFO_COUNT_TAG (" + Integer.toHexString(2) + ") in CBEFF structure, found " + Integer.toHexString(readTag));
        }
        int readLength = tLVInputStream.readLength();
        if (readLength != 1) {
            throw new IllegalArgumentException("BIOMETRIC_INFO_COUNT should have length 1, found length " + readLength);
        }
        byte b = tLVInputStream.readValue()[0] & 255;
        for (int i3 = 0; i3 < b; i3++) {
            complexCBEFFInfo.add(readBIT(inputStream, i3));
        }
        return complexCBEFFInfo;
    }

    private CBEFFInfo readBIT(InputStream inputStream, int i) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        return readBIT(tLVInputStream.readTag(), tLVInputStream.readLength(), inputStream, i);
    }

    private CBEFFInfo readBIT(int i, int i2, InputStream inputStream, int i3) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        if (i != 32608) {
            throw new IllegalArgumentException("Expected tag BIOMETRIC_INFORMATION_TEMPLATE_TAG (" + Integer.toHexString(32608) + "), found " + Integer.toHexString(i) + ", index is " + i3);
        }
        int readTag = tLVInputStream.readTag();
        int readLength = tLVInputStream.readLength();
        if (readTag == 125) {
            readStaticallyProtectedBIT(inputStream, readTag, readLength, i3);
            return null;
        } else if ((readTag & 160) == 160) {
            return new SimpleCBEFFInfo(readBiometricDataBlock(inputStream, readBHT(inputStream, readTag, readLength, i3), i3));
        } else {
            throw new IllegalArgumentException("Unsupported template tag: " + Integer.toHexString(readTag));
        }
    }

    private StandardBiometricHeader readBHT(InputStream inputStream, int i, int i2, int i3) throws IOException {
        TLVInputStream tLVInputStream;
        if (inputStream instanceof TLVInputStream) {
            tLVInputStream = (TLVInputStream) inputStream;
        } else {
            tLVInputStream = new TLVInputStream(inputStream);
        }
        if (i != 161) {
            String str = "Expected tag " + Integer.toHexString(CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384) + ", found " + Integer.toHexString(i);
            if (LOGGER != null) {
                LOGGER.warning(str);
            }
        }
        HashMap hashMap = new HashMap();
        int i4 = 0;
        while (i4 < i2) {
            int readTag = tLVInputStream.readTag();
            int tagLength = i4 + TLVUtil.getTagLength(readTag) + TLVUtil.getLengthLength(tLVInputStream.readLength());
            byte[] readValue = tLVInputStream.readValue();
            i4 = tagLength + readValue.length;
            hashMap.put(Integer.valueOf(readTag), readValue);
        }
        return new StandardBiometricHeader(hashMap);
    }

    private void readStaticallyProtectedBIT(InputStream inputStream, int i, int i2, int i3) throws IOException {
        TLVInputStream tLVInputStream = new TLVInputStream(new ByteArrayInputStream(decodeSMTValue(inputStream)));
        readBiometricDataBlock(new ByteArrayInputStream(decodeSMTValue(inputStream)), readBHT(tLVInputStream, tLVInputStream.readTag(), tLVInputStream.readLength(), i3), i3);
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] decodeSMTValue(java.io.InputStream r6) throws java.io.IOException {
        /*
            r5 = this;
            r2 = 0
            boolean r0 = r6 instanceof net.p318sf.scuba.tlv.TLVInputStream
            if (r0 == 0) goto L_0x0015
            net.sf.scuba.tlv.TLVInputStream r6 = (net.p318sf.scuba.tlv.TLVInputStream) r6
        L_0x0008:
            int r0 = r6.readTag()
            int r4 = r6.readLength()
            switch(r0) {
                case 129: goto L_0x001c;
                case 133: goto L_0x0021;
                case 142: goto L_0x002a;
                case 158: goto L_0x0036;
                default: goto L_0x0013;
            }
        L_0x0013:
            r0 = 0
        L_0x0014:
            return r0
        L_0x0015:
            net.sf.scuba.tlv.TLVInputStream r0 = new net.sf.scuba.tlv.TLVInputStream
            r0.<init>(r6)
            r6 = r0
            goto L_0x0008
        L_0x001c:
            byte[] r0 = r6.readValue()
            goto L_0x0014
        L_0x0021:
            java.security.AccessControlException r0 = new java.security.AccessControlException
            java.lang.String r1 = "Access denied. Biometric Information Template is statically protected."
            r0.<init>(r1)
            throw r0
        L_0x002a:
            long r0 = (long) r4
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x0013
            long r0 = (long) r4
            long r0 = r6.skip(r0)
            long r2 = r2 + r0
            goto L_0x002a
        L_0x0036:
            r0 = r2
        L_0x0037:
            long r2 = (long) r4
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x0013
            long r2 = (long) r4
            long r2 = r6.skip(r2)
            long r0 = r0 + r2
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jmrtd.cbeff.ISO781611Decoder.decodeSMTValue(java.io.InputStream):byte[]");
    }

    private BiometricDataBlock readBiometricDataBlock(InputStream inputStream, StandardBiometricHeader standardBiometricHeader, int i) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int readTag = tLVInputStream.readTag();
        if (readTag == 24366 || readTag == 32558) {
            return this.bdbDecoder.decode(inputStream, standardBiometricHeader, i, tLVInputStream.readLength());
        }
        throw new IllegalArgumentException("Expected tag BIOMETRIC_DATA_BLOCK_TAG (" + Integer.toHexString(24366) + ") or BIOMETRIC_DATA_BLOCK_TAG_ALT (" + Integer.toHexString(32558) + "), found " + Integer.toHexString(readTag));
    }
}
