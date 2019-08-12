package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class RSAKeyParameters extends AsymmetricKeyParameter {
    private BigInteger exponent;
    private BigInteger modulus;

    public RSAKeyParameters(boolean isPrivate, BigInteger modulus2, BigInteger exponent2) {
        super(isPrivate);
        this.modulus = modulus2;
        this.exponent = exponent2;
    }

    public BigInteger getModulus() {
        return this.modulus;
    }

    public BigInteger getExponent() {
        return this.exponent;
    }
}
