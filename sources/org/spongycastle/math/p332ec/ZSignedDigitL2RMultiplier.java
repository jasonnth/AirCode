package org.spongycastle.math.p332ec;

import java.math.BigInteger;

/* renamed from: org.spongycastle.math.ec.ZSignedDigitL2RMultiplier */
public class ZSignedDigitL2RMultiplier extends AbstractECMultiplier {
    /* access modifiers changed from: protected */
    public ECPoint multiplyPositive(ECPoint p, BigInteger k) {
        ECPoint addP = p.normalize();
        ECPoint subP = addP.negate();
        ECPoint R0 = addP;
        int n = k.bitLength();
        int s = k.getLowestSetBit();
        int i = n;
        while (true) {
            i--;
            if (i <= s) {
                return R0.timesPow2(s);
            }
            R0 = R0.twicePlus(k.testBit(i) ? addP : subP);
        }
    }
}
