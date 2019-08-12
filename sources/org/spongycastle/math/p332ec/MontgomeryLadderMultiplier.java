package org.spongycastle.math.p332ec;

import java.math.BigInteger;

/* renamed from: org.spongycastle.math.ec.MontgomeryLadderMultiplier */
public class MontgomeryLadderMultiplier extends AbstractECMultiplier {
    /* access modifiers changed from: protected */
    public ECPoint multiplyPositive(ECPoint p, BigInteger k) {
        int b;
        ECPoint[] R = {p.getCurve().getInfinity(), p};
        int i = k.bitLength();
        while (true) {
            i--;
            if (i < 0) {
                return R[0];
            }
            if (k.testBit(i)) {
                b = 1;
            } else {
                b = 0;
            }
            int bp = 1 - b;
            R[bp] = R[bp].add(R[b]);
            R[b] = R[b].twice();
        }
    }
}
