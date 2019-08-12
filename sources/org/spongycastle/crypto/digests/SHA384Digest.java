package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

public class SHA384Digest extends LongDigest {
    private static final int DIGEST_LENGTH = 48;

    public SHA384Digest() {
    }

    public SHA384Digest(SHA384Digest t) {
        super(t);
    }

    public SHA384Digest(byte[] encodedState) {
        restoreState(encodedState);
    }

    public String getAlgorithmName() {
        return "SHA-384";
    }

    public int getDigestSize() {
        return 48;
    }

    public int doFinal(byte[] out, int outOff) {
        finish();
        Pack.longToBigEndian(this.f6505H1, out, outOff);
        Pack.longToBigEndian(this.f6506H2, out, outOff + 8);
        Pack.longToBigEndian(this.f6507H3, out, outOff + 16);
        Pack.longToBigEndian(this.f6508H4, out, outOff + 24);
        Pack.longToBigEndian(this.f6509H5, out, outOff + 32);
        Pack.longToBigEndian(this.f6510H6, out, outOff + 40);
        reset();
        return 48;
    }

    public void reset() {
        super.reset();
        this.f6505H1 = -3766243637369397544L;
        this.f6506H2 = 7105036623409894663L;
        this.f6507H3 = -7973340178411365097L;
        this.f6508H4 = 1526699215303891257L;
        this.f6509H5 = 7436329637833083697L;
        this.f6510H6 = -8163818279084223215L;
        this.f6511H7 = -2662702644619276377L;
        this.f6512H8 = 5167115440072839076L;
    }

    public Memoable copy() {
        return new SHA384Digest(this);
    }

    public void reset(Memoable other) {
        super.copyIn((SHA384Digest) other);
    }

    public byte[] getEncodedState() {
        byte[] encoded = new byte[getEncodedStateSize()];
        super.populateState(encoded);
        return encoded;
    }
}
