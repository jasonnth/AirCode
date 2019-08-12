package org.spongycastle.jce.spec;

import org.spongycastle.math.p332ec.ECPoint;

public class ECPublicKeySpec extends ECKeySpec {

    /* renamed from: q */
    private ECPoint f6942q;

    public ECPublicKeySpec(ECPoint q, ECParameterSpec spec) {
        super(spec);
        if (q.getCurve() != null) {
            this.f6942q = q.normalize();
        } else {
            this.f6942q = q;
        }
    }

    public ECPoint getQ() {
        return this.f6942q;
    }
}
