package org.spongycastle.math.p332ec;

import java.math.BigInteger;

/* renamed from: org.spongycastle.math.ec.ZSignedDigitR2LMultiplier */
public class ZSignedDigitR2LMultiplier extends AbstractECMultiplier {
    /* access modifiers changed from: protected */
    public ECPoint multiplyPositive(ECPoint p, BigInteger k) {
        ECPoint R0 = p.getCurve().getInfinity();
        ECPoint R1 = p;
        int n = k.bitLength();
        int s = k.getLowestSetBit();
        ECPoint R12 = R1.timesPow2(s);
        int i = s;
        while (true) {
            i++;
            if (i >= n) {
                return R0.add(R12);
            }
            R0 = R0.add(k.testBit(i) ? R12 : R12.negate());
            R12 = R12.twice();
        }
    }
}
