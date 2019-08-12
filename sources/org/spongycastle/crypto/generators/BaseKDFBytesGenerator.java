package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.DigestDerivationFunction;
import org.spongycastle.crypto.params.ISO18033KDFParameters;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.util.Pack;

public class BaseKDFBytesGenerator implements DigestDerivationFunction {
    private int counterStart;
    private Digest digest;

    /* renamed from: iv */
    private byte[] f6727iv;
    private byte[] shared;

    protected BaseKDFBytesGenerator(int counterStart2, Digest digest2) {
        this.counterStart = counterStart2;
        this.digest = digest2;
    }

    public void init(DerivationParameters param) {
        if (param instanceof KDFParameters) {
            KDFParameters p = (KDFParameters) param;
            this.shared = p.getSharedSecret();
            this.f6727iv = p.getIV();
        } else if (param instanceof ISO18033KDFParameters) {
            this.shared = ((ISO18033KDFParameters) param).getSeed();
            this.f6727iv = null;
        } else {
            throw new IllegalArgumentException("KDF parameters required for KDF2Generator");
        }
    }

    public Digest getDigest() {
        return this.digest;
    }

    public int generateBytes(byte[] out, int outOff, int len) throws DataLengthException, IllegalArgumentException {
        if (out.length - len < outOff) {
            throw new DataLengthException("output buffer too small");
        }
        long oBytes = (long) len;
        int outLen = this.digest.getDigestSize();
        if (oBytes > 8589934591L) {
            throw new IllegalArgumentException("Output length too large");
        }
        int cThreshold = (int) (((((long) outLen) + oBytes) - 1) / ((long) outLen));
        byte[] dig = new byte[this.digest.getDigestSize()];
        byte[] C = new byte[4];
        Pack.intToBigEndian(this.counterStart, C, 0);
        int counterBase = this.counterStart & -256;
        for (int i = 0; i < cThreshold; i++) {
            this.digest.update(this.shared, 0, this.shared.length);
            this.digest.update(C, 0, C.length);
            if (this.f6727iv != null) {
                this.digest.update(this.f6727iv, 0, this.f6727iv.length);
            }
            this.digest.doFinal(dig, 0);
            if (len > outLen) {
                System.arraycopy(dig, 0, out, outOff, outLen);
                outOff += outLen;
                len -= outLen;
            } else {
                System.arraycopy(dig, 0, out, outOff, len);
            }
            byte b = (byte) (C[3] + 1);
            C[3] = b;
            if (b == 0) {
                counterBase += 256;
                Pack.intToBigEndian(counterBase, C, 0);
            }
        }
        this.digest.reset();
        return (int) oBytes;
    }
}
