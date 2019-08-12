package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;

public class ShortenedDigest implements ExtendedDigest {
    private ExtendedDigest baseDigest;
    private int length;

    public ShortenedDigest(ExtendedDigest baseDigest2, int length2) {
        if (baseDigest2 == null) {
            throw new IllegalArgumentException("baseDigest must not be null");
        } else if (length2 > baseDigest2.getDigestSize()) {
            throw new IllegalArgumentException("baseDigest output not large enough to support length");
        } else {
            this.baseDigest = baseDigest2;
            this.length = length2;
        }
    }

    public String getAlgorithmName() {
        return this.baseDigest.getAlgorithmName() + "(" + (this.length * 8) + ")";
    }

    public int getDigestSize() {
        return this.length;
    }

    public void update(byte in) {
        this.baseDigest.update(in);
    }

    public void update(byte[] in, int inOff, int len) {
        this.baseDigest.update(in, inOff, len);
    }

    public int doFinal(byte[] out, int outOff) {
        byte[] tmp = new byte[this.baseDigest.getDigestSize()];
        this.baseDigest.doFinal(tmp, 0);
        System.arraycopy(tmp, 0, out, outOff, this.length);
        return this.length;
    }

    public void reset() {
        this.baseDigest.reset();
    }

    public int getByteLength() {
        return this.baseDigest.getByteLength();
    }
}
