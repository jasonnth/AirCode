package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class SRP6GroupParameters {

    /* renamed from: N */
    private BigInteger f6866N;

    /* renamed from: g */
    private BigInteger f6867g;

    public SRP6GroupParameters(BigInteger N, BigInteger g) {
        this.f6866N = N;
        this.f6867g = g;
    }

    public BigInteger getG() {
        return this.f6867g;
    }

    public BigInteger getN() {
        return this.f6866N;
    }
}
