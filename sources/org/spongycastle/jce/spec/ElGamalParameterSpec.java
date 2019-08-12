package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

public class ElGamalParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: g */
    private BigInteger f6943g;

    /* renamed from: p */
    private BigInteger f6944p;

    public ElGamalParameterSpec(BigInteger p, BigInteger g) {
        this.f6944p = p;
        this.f6943g = g;
    }

    public BigInteger getP() {
        return this.f6944p;
    }

    public BigInteger getG() {
        return this.f6943g;
    }
}
