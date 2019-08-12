package org.spongycastle.crypto.params;

import org.spongycastle.math.p332ec.ECPoint;

public class ECPublicKeyParameters extends ECKeyParameters {

    /* renamed from: Q */
    ECPoint f6836Q;

    public ECPublicKeyParameters(ECPoint Q, ECDomainParameters params) {
        super(false, params);
        this.f6836Q = Q.normalize();
    }

    public ECPoint getQ() {
        return this.f6836Q;
    }
}
