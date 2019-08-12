package org.spongycastle.crypto.modes.gcm;

import org.spongycastle.util.Arrays;

public class BasicGCMExponentiator implements GCMExponentiator {

    /* renamed from: x */
    private int[] f6797x;

    public void init(byte[] x) {
        this.f6797x = GCMUtil.asInts(x);
    }

    public void exponentiateX(long pow, byte[] output) {
        int[] y = GCMUtil.oneAsInts();
        if (pow > 0) {
            int[] powX = Arrays.clone(this.f6797x);
            do {
                if ((1 & pow) != 0) {
                    GCMUtil.multiply(y, powX);
                }
                GCMUtil.multiply(powX, powX);
                pow >>>= 1;
            } while (pow > 0);
        }
        GCMUtil.asBytes(y, output);
    }
}
