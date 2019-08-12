package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class RSAPrivateCrtKeyParameters extends RSAKeyParameters {

    /* renamed from: dP */
    private BigInteger f6861dP;

    /* renamed from: dQ */
    private BigInteger f6862dQ;

    /* renamed from: e */
    private BigInteger f6863e;

    /* renamed from: p */
    private BigInteger f6864p;

    /* renamed from: q */
    private BigInteger f6865q;
    private BigInteger qInv;

    public RSAPrivateCrtKeyParameters(BigInteger modulus, BigInteger publicExponent, BigInteger privateExponent, BigInteger p, BigInteger q, BigInteger dP, BigInteger dQ, BigInteger qInv2) {
        super(true, modulus, privateExponent);
        this.f6863e = publicExponent;
        this.f6864p = p;
        this.f6865q = q;
        this.f6861dP = dP;
        this.f6862dQ = dQ;
        this.qInv = qInv2;
    }

    public BigInteger getPublicExponent() {
        return this.f6863e;
    }

    public BigInteger getP() {
        return this.f6864p;
    }

    public BigInteger getQ() {
        return this.f6865q;
    }

    public BigInteger getDP() {
        return this.f6861dP;
    }

    public BigInteger getDQ() {
        return this.f6862dQ;
    }

    public BigInteger getQInv() {
        return this.qInv;
    }
}
