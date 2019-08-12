package org.spongycastle.math.p332ec;

import java.math.BigInteger;

/* renamed from: org.spongycastle.math.ec.MixedNafR2LMultiplier */
public class MixedNafR2LMultiplier extends AbstractECMultiplier {
    protected int additionCoord;
    protected int doublingCoord;

    public MixedNafR2LMultiplier() {
        this(2, 4);
    }

    public MixedNafR2LMultiplier(int additionCoord2, int doublingCoord2) {
        this.additionCoord = additionCoord2;
        this.doublingCoord = doublingCoord2;
    }

    /* access modifiers changed from: protected */
    public ECPoint multiplyPositive(ECPoint p, BigInteger k) {
        ECCurve curveOrig = p.getCurve();
        ECCurve curveAdd = configureCurve(curveOrig, this.additionCoord);
        ECCurve curveDouble = configureCurve(curveOrig, this.doublingCoord);
        int[] naf = WNafUtil.generateCompactNaf(k);
        ECPoint Ra = curveAdd.getInfinity();
        ECPoint Td = curveDouble.importPoint(p);
        int zeroes = 0;
        for (int ni : naf) {
            int digit = ni >> 16;
            Td = Td.timesPow2(zeroes + (65535 & ni));
            ECPoint Tj = curveAdd.importPoint(Td);
            if (digit < 0) {
                Tj = Tj.negate();
            }
            Ra = Ra.add(Tj);
            zeroes = 1;
        }
        return curveOrig.importPoint(Ra);
    }

    /* access modifiers changed from: protected */
    public ECCurve configureCurve(ECCurve c, int coord) {
        if (c.getCoordinateSystem() == coord) {
            return c;
        }
        if (c.supportsCoordinateSystem(coord)) {
            return c.configure().setCoordinateSystem(coord).create();
        }
        throw new IllegalArgumentException("Coordinate system " + coord + " not supported by this curve");
    }
}
