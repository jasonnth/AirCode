package org.spongycastle.math.p332ec;

import java.math.BigInteger;

/* renamed from: org.spongycastle.math.ec.NafR2LMultiplier */
public class NafR2LMultiplier extends AbstractECMultiplier {
    /* access modifiers changed from: protected */
    public ECPoint multiplyPositive(ECPoint p, BigInteger k) {
        ECPoint eCPoint;
        int[] naf = WNafUtil.generateCompactNaf(k);
        ECPoint R0 = p.getCurve().getInfinity();
        ECPoint R1 = p;
        int zeroes = 0;
        for (int ni : naf) {
            int digit = ni >> 16;
            R1 = R1.timesPow2(zeroes + (65535 & ni));
            if (digit < 0) {
                eCPoint = R1.negate();
            } else {
                eCPoint = R1;
            }
            R0 = R0.add(eCPoint);
            zeroes = 1;
        }
        return R0;
    }
}
