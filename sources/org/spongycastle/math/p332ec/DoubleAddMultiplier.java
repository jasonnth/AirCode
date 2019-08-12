package org.spongycastle.math.p332ec;

import java.math.BigInteger;

/* renamed from: org.spongycastle.math.ec.DoubleAddMultiplier */
public class DoubleAddMultiplier extends AbstractECMultiplier {
    /* access modifiers changed from: protected */
    public ECPoint multiplyPositive(ECPoint p, BigInteger k) {
        int b;
        ECPoint[] R = {p.getCurve().getInfinity(), p};
        int n = k.bitLength();
        for (int i = 0; i < n; i++) {
            if (k.testBit(i)) {
                b = 1;
            } else {
                b = 0;
            }
            int bp = 1 - b;
            R[bp] = R[bp].twicePlus(R[b]);
        }
        return R[0];
    }
}
