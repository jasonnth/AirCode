package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

public class GOST3410PublicKeySpec implements KeySpec {

    /* renamed from: a */
    private BigInteger f6954a;

    /* renamed from: p */
    private BigInteger f6955p;

    /* renamed from: q */
    private BigInteger f6956q;

    /* renamed from: y */
    private BigInteger f6957y;

    public GOST3410PublicKeySpec(BigInteger y, BigInteger p, BigInteger q, BigInteger a) {
        this.f6957y = y;
        this.f6955p = p;
        this.f6956q = q;
        this.f6954a = a;
    }

    public BigInteger getY() {
        return this.f6957y;
    }

    public BigInteger getP() {
        return this.f6955p;
    }

    public BigInteger getQ() {
        return this.f6956q;
    }

    public BigInteger getA() {
        return this.f6954a;
    }
}
