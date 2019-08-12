package org.spongycastle.crypto.modes.gcm;

public class BasicGCMMultiplier implements GCMMultiplier {

    /* renamed from: H */
    private int[] f6798H;

    public void init(byte[] H) {
        this.f6798H = GCMUtil.asInts(H);
    }

    public void multiplyH(byte[] x) {
        int[] t = GCMUtil.asInts(x);
        GCMUtil.multiply(t, this.f6798H);
        GCMUtil.asBytes(t, x);
    }
}
