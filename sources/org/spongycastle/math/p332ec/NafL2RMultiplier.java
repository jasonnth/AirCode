package org.spongycastle.math.p332ec;

import com.facebook.soloader.MinElf;
import java.math.BigInteger;

/* renamed from: org.spongycastle.math.ec.NafL2RMultiplier */
public class NafL2RMultiplier extends AbstractECMultiplier {
    /* access modifiers changed from: protected */
    public ECPoint multiplyPositive(ECPoint p, BigInteger k) {
        ECPoint eCPoint;
        int[] naf = WNafUtil.generateCompactNaf(k);
        ECPoint addP = p.normalize();
        ECPoint subP = addP.negate();
        ECPoint R = p.getCurve().getInfinity();
        int i = naf.length;
        while (true) {
            i--;
            if (i < 0) {
                return R;
            }
            int ni = naf[i];
            int zeroes = ni & MinElf.PN_XNUM;
            if ((ni >> 16) < 0) {
                eCPoint = subP;
            } else {
                eCPoint = addP;
            }
            R = R.twicePlus(eCPoint).timesPow2(zeroes);
        }
    }
}
