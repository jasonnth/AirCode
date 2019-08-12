package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class GOST3410PublicKeyParameters extends GOST3410KeyParameters {

    /* renamed from: y */
    private BigInteger f6846y;

    public GOST3410PublicKeyParameters(BigInteger y, GOST3410Parameters params) {
        super(false, params);
        this.f6846y = y;
    }

    public BigInteger getY() {
        return this.f6846y;
    }
}
