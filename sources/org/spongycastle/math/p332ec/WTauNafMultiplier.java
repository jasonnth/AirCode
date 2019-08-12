package org.spongycastle.math.p332ec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECPoint.AbstractF2m;

/* renamed from: org.spongycastle.math.ec.WTauNafMultiplier */
public class WTauNafMultiplier extends AbstractECMultiplier {
    static final String PRECOMP_NAME = "bc_wtnaf";

    /* access modifiers changed from: protected */
    public ECPoint multiplyPositive(ECPoint point, BigInteger k) {
        if (!(point instanceof AbstractF2m)) {
            throw new IllegalArgumentException("Only ECPoint.AbstractF2m can be used in WTauNafMultiplier");
        }
        AbstractF2m p = (AbstractF2m) point;
        ECCurve.AbstractF2m curve = (ECCurve.AbstractF2m) p.getCurve();
        int m = curve.getFieldSize();
        byte a = curve.getA().toBigInteger().byteValue();
        byte mu = Tnaf.getMu((int) a);
        return multiplyWTnaf(p, Tnaf.partModReduction(k, m, a, curve.getSi(), mu, 10), curve.getPreCompInfo(p, PRECOMP_NAME), a, mu);
    }

    private AbstractF2m multiplyWTnaf(AbstractF2m p, ZTauElement lambda, PreCompInfo preCompInfo, byte a, byte mu) {
        return multiplyFromWTnaf(p, Tnaf.tauAdicWNaf(mu, lambda, 4, BigInteger.valueOf(16), Tnaf.getTw(mu, 4), a == 0 ? Tnaf.alpha0 : Tnaf.alpha1), preCompInfo);
    }

    private static AbstractF2m multiplyFromWTnaf(AbstractF2m p, byte[] u, PreCompInfo preCompInfo) {
        AbstractF2m[] pu;
        ECCurve.AbstractF2m curve = (ECCurve.AbstractF2m) p.getCurve();
        byte a = curve.getA().toBigInteger().byteValue();
        if (preCompInfo == null || !(preCompInfo instanceof WTauNafPreCompInfo)) {
            pu = Tnaf.getPreComp(p, a);
            WTauNafPreCompInfo pre = new WTauNafPreCompInfo();
            pre.setPreComp(pu);
            curve.setPreCompInfo(p, PRECOMP_NAME, pre);
        } else {
            pu = ((WTauNafPreCompInfo) preCompInfo).getPreComp();
        }
        AbstractF2m[] puNeg = new AbstractF2m[pu.length];
        for (int i = 0; i < pu.length; i++) {
            puNeg[i] = (AbstractF2m) pu[i].negate();
        }
        AbstractF2m q = (AbstractF2m) p.getCurve().getInfinity();
        int tauCount = 0;
        for (int i2 = u.length - 1; i2 >= 0; i2--) {
            tauCount++;
            byte ui = u[i2];
            if (ui != 0) {
                AbstractF2m q2 = q.tauPow(tauCount);
                tauCount = 0;
                q = (AbstractF2m) q2.add(ui > 0 ? pu[ui >>> 1] : puNeg[(-ui) >>> 1]);
            }
        }
        if (tauCount > 0) {
            return q.tauPow(tauCount);
        }
        return q;
    }
}
