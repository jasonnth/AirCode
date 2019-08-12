package org.spongycastle.jce.spec;

import java.math.BigInteger;

public class ElGamalPrivateKeySpec extends ElGamalKeySpec {

    /* renamed from: x */
    private BigInteger f6945x;

    public ElGamalPrivateKeySpec(BigInteger x, ElGamalParameterSpec spec) {
        super(spec);
        this.f6945x = x;
    }

    public BigInteger getX() {
        return this.f6945x;
    }
}
