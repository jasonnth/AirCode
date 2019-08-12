package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class NaccacheSternKeyParameters extends AsymmetricKeyParameter {

    /* renamed from: g */
    private BigInteger f6858g;
    int lowerSigmaBound;

    /* renamed from: n */
    private BigInteger f6859n;

    public NaccacheSternKeyParameters(boolean privateKey, BigInteger g, BigInteger n, int lowerSigmaBound2) {
        super(privateKey);
        this.f6858g = g;
        this.f6859n = n;
        this.lowerSigmaBound = lowerSigmaBound2;
    }

    public BigInteger getG() {
        return this.f6858g;
    }

    public int getLowerSigmaBound() {
        return this.lowerSigmaBound;
    }

    public BigInteger getModulus() {
        return this.f6859n;
    }
}
