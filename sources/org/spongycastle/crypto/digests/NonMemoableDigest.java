package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;

public class NonMemoableDigest implements ExtendedDigest {
    private ExtendedDigest baseDigest;

    public NonMemoableDigest(ExtendedDigest baseDigest2) {
        if (baseDigest2 == null) {
            throw new IllegalArgumentException("baseDigest must not be null");
        }
        this.baseDigest = baseDigest2;
    }

    public String getAlgorithmName() {
        return this.baseDigest.getAlgorithmName();
    }

    public int getDigestSize() {
        return this.baseDigest.getDigestSize();
    }

    public void update(byte in) {
        this.baseDigest.update(in);
    }

    public void update(byte[] in, int inOff, int len) {
        this.baseDigest.update(in, inOff, len);
    }

    public int doFinal(byte[] out, int outOff) {
        return this.baseDigest.doFinal(out, outOff);
    }

    public void reset() {
        this.baseDigest.reset();
    }

    public int getByteLength() {
        return this.baseDigest.getByteLength();
    }
}
