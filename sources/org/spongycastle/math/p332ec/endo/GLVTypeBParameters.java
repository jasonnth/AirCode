package org.spongycastle.math.p332ec.endo;

import java.math.BigInteger;

/* renamed from: org.spongycastle.math.ec.endo.GLVTypeBParameters */
public class GLVTypeBParameters {
    protected final BigInteger beta;
    protected final int bits;

    /* renamed from: g1 */
    protected final BigInteger f7054g1;

    /* renamed from: g2 */
    protected final BigInteger f7055g2;
    protected final BigInteger lambda;
    protected final BigInteger v1A;
    protected final BigInteger v1B;
    protected final BigInteger v2A;
    protected final BigInteger v2B;

    private static void checkVector(BigInteger[] v, String name) {
        if (v == null || v.length != 2 || v[0] == null || v[1] == null) {
            throw new IllegalArgumentException("'" + name + "' must consist of exactly 2 (non-null) values");
        }
    }

    public GLVTypeBParameters(BigInteger beta2, BigInteger lambda2, BigInteger[] v1, BigInteger[] v2, BigInteger g1, BigInteger g2, int bits2) {
        checkVector(v1, "v1");
        checkVector(v2, "v2");
        this.beta = beta2;
        this.lambda = lambda2;
        this.v1A = v1[0];
        this.v1B = v1[1];
        this.v2A = v2[0];
        this.v2B = v2[1];
        this.f7054g1 = g1;
        this.f7055g2 = g2;
        this.bits = bits2;
    }

    public BigInteger getBeta() {
        return this.beta;
    }

    public BigInteger getLambda() {
        return this.lambda;
    }

    public BigInteger[] getV1() {
        return new BigInteger[]{this.v1A, this.v1B};
    }

    public BigInteger getV1A() {
        return this.v1A;
    }

    public BigInteger getV1B() {
        return this.v1B;
    }

    public BigInteger[] getV2() {
        return new BigInteger[]{this.v2A, this.v2B};
    }

    public BigInteger getV2A() {
        return this.v2A;
    }

    public BigInteger getV2B() {
        return this.v2B;
    }

    public BigInteger getG1() {
        return this.f7054g1;
    }

    public BigInteger getG2() {
        return this.f7055g2;
    }

    public int getBits() {
        return this.bits;
    }
}
