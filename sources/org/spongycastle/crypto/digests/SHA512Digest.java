package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

public class SHA512Digest extends LongDigest {
    private static final int DIGEST_LENGTH = 64;

    public SHA512Digest() {
    }

    public SHA512Digest(SHA512Digest t) {
        super(t);
    }

    public SHA512Digest(byte[] encodedState) {
        restoreState(encodedState);
    }

    public String getAlgorithmName() {
        return "SHA-512";
    }

    public int getDigestSize() {
        return 64;
    }

    public int doFinal(byte[] out, int outOff) {
        finish();
        Pack.longToBigEndian(this.f6505H1, out, outOff);
        Pack.longToBigEndian(this.f6506H2, out, outOff + 8);
        Pack.longToBigEndian(this.f6507H3, out, outOff + 16);
        Pack.longToBigEndian(this.f6508H4, out, outOff + 24);
        Pack.longToBigEndian(this.f6509H5, out, outOff + 32);
        Pack.longToBigEndian(this.f6510H6, out, outOff + 40);
        Pack.longToBigEndian(this.f6511H7, out, outOff + 48);
        Pack.longToBigEndian(this.f6512H8, out, outOff + 56);
        reset();
        return 64;
    }

    public void reset() {
        super.reset();
        this.f6505H1 = 7640891576956012808L;
        this.f6506H2 = -4942790177534073029L;
        this.f6507H3 = 4354685564936845355L;
        this.f6508H4 = -6534734903238641935L;
        this.f6509H5 = 5840696475078001361L;
        this.f6510H6 = -7276294671716946913L;
        this.f6511H7 = 2270897969802886507L;
        this.f6512H8 = 6620516959819538809L;
    }

    public Memoable copy() {
        return new SHA512Digest(this);
    }

    public void reset(Memoable other) {
        copyIn((SHA512Digest) other);
    }

    public byte[] getEncodedState() {
        byte[] encoded = new byte[getEncodedStateSize()];
        super.populateState(encoded);
        return encoded;
    }
}
