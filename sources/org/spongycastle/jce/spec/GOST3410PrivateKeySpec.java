package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

public class GOST3410PrivateKeySpec implements KeySpec {

    /* renamed from: a */
    private BigInteger f6947a;

    /* renamed from: p */
    private BigInteger f6948p;

    /* renamed from: q */
    private BigInteger f6949q;

    /* renamed from: x */
    private BigInteger f6950x;

    public GOST3410PrivateKeySpec(BigInteger x, BigInteger p, BigInteger q, BigInteger a) {
        this.f6950x = x;
        this.f6948p = p;
        this.f6949q = q;
        this.f6947a = a;
    }

    public BigInteger getX() {
        return this.f6950x;
    }

    public BigInteger getP() {
        return this.f6948p;
    }

    public BigInteger getQ() {
        return this.f6949q;
    }

    public BigInteger getA() {
        return this.f6947a;
    }
}
