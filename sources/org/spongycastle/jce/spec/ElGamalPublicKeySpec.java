package org.spongycastle.jce.spec;

import java.math.BigInteger;

public class ElGamalPublicKeySpec extends ElGamalKeySpec {

    /* renamed from: y */
    private BigInteger f6946y;

    public ElGamalPublicKeySpec(BigInteger y, ElGamalParameterSpec spec) {
        super(spec);
        this.f6946y = y;
    }

    public BigInteger getY() {
        return this.f6946y;
    }
}
