package org.spongycastle.math.p332ec;

import java.math.BigInteger;

/* renamed from: org.spongycastle.math.ec.FixedPointCombMultiplier */
public class FixedPointCombMultiplier extends AbstractECMultiplier {
    /* access modifiers changed from: protected */
    public ECPoint multiplyPositive(ECPoint p, BigInteger k) {
        ECCurve c = p.getCurve();
        int size = FixedPointUtil.getCombSize(c);
        if (k.bitLength() > size) {
            throw new IllegalStateException("fixed-point comb doesn't support scalars larger than the curve order");
        }
        FixedPointPreCompInfo info = FixedPointUtil.precompute(p, getWidthForCombSize(size));
        ECPoint[] lookupTable = info.getPreComp();
        int width = info.getWidth();
        int d = ((size + width) - 1) / width;
        ECPoint R = c.getInfinity();
        int top = (d * width) - 1;
        for (int i = 0; i < d; i++) {
            int index = 0;
            for (int j = top - i; j >= 0; j -= d) {
                index <<= 1;
                if (k.testBit(j)) {
                    index |= 1;
                }
            }
            R = R.twicePlus(lookupTable[index]);
        }
        return R;
    }

    /* access modifiers changed from: protected */
    public int getWidthForCombSize(int combSize) {
        return combSize > 257 ? 6 : 5;
    }
}
