package org.spongycastle.math.p332ec;

import com.facebook.soloader.MinElf;
import java.math.BigInteger;

/* renamed from: org.spongycastle.math.ec.WNafL2RMultiplier */
public class WNafL2RMultiplier extends AbstractECMultiplier {
    /* access modifiers changed from: protected */
    public ECPoint multiplyPositive(ECPoint p, BigInteger k) {
        ECPoint[] table;
        ECPoint[] table2;
        ECPoint R;
        int width = Math.max(2, Math.min(16, getWindowSize(k.bitLength())));
        WNafPreCompInfo wnafPreCompInfo = WNafUtil.precompute(p, width, true);
        ECPoint[] preComp = wnafPreCompInfo.getPreComp();
        ECPoint[] preCompNeg = wnafPreCompInfo.getPreCompNeg();
        int[] wnaf = WNafUtil.generateCompactWindowNaf(width, k);
        ECPoint R2 = p.getCurve().getInfinity();
        int i = wnaf.length;
        if (i > 1) {
            i--;
            int wi = wnaf[i];
            int digit = wi >> 16;
            int zeroes = wi & MinElf.PN_XNUM;
            int n = Math.abs(digit);
            if (digit < 0) {
                table2 = preCompNeg;
            } else {
                table2 = preComp;
            }
            if ((n << 2) < (1 << width)) {
                byte highest = LongArray.bitLengths[n];
                int scale = width - highest;
                R = table2[((1 << (width - 1)) - 1) >>> 1].add(table2[(((n ^ (1 << (highest - 1))) << scale) + 1) >>> 1]);
                zeroes -= scale;
            } else {
                R = table2[n >>> 1];
            }
            R2 = R.timesPow2(zeroes);
        }
        while (i > 0) {
            i--;
            int wi2 = wnaf[i];
            int digit2 = wi2 >> 16;
            int zeroes2 = wi2 & MinElf.PN_XNUM;
            int n2 = Math.abs(digit2);
            if (digit2 < 0) {
                table = preCompNeg;
            } else {
                table = preComp;
            }
            R2 = R2.twicePlus(table[n2 >>> 1]).timesPow2(zeroes2);
        }
        return R2;
    }

    /* access modifiers changed from: protected */
    public int getWindowSize(int bits) {
        return WNafUtil.getWindowSize(bits);
    }
}
