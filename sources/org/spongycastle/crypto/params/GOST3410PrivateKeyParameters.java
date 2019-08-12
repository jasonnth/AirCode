package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class GOST3410PrivateKeyParameters extends GOST3410KeyParameters {

    /* renamed from: x */
    private BigInteger f6845x;

    public GOST3410PrivateKeyParameters(BigInteger x, GOST3410Parameters params) {
        super(true, params);
        this.f6845x = x;
    }

    public BigInteger getX() {
        return this.f6845x;
    }
}
