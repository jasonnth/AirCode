package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class DHParameters implements CipherParameters {
    private static final int DEFAULT_MINIMUM_LENGTH = 160;

    /* renamed from: g */
    private BigInteger f6817g;

    /* renamed from: j */
    private BigInteger f6818j;

    /* renamed from: l */
    private int f6819l;

    /* renamed from: m */
    private int f6820m;

    /* renamed from: p */
    private BigInteger f6821p;

    /* renamed from: q */
    private BigInteger f6822q;
    private DHValidationParameters validation;

    private static int getDefaultMParam(int lParam) {
        if (lParam == 0) {
            return 160;
        }
        if (lParam >= 160) {
            lParam = 160;
        }
        return lParam;
    }

    public DHParameters(BigInteger p, BigInteger g) {
        this(p, g, null, 0);
    }

    public DHParameters(BigInteger p, BigInteger g, BigInteger q) {
        this(p, g, q, 0);
    }

    public DHParameters(BigInteger p, BigInteger g, BigInteger q, int l) {
        this(p, g, q, getDefaultMParam(l), l, null, null);
    }

    public DHParameters(BigInteger p, BigInteger g, BigInteger q, int m, int l) {
        this(p, g, q, m, l, null, null);
    }

    public DHParameters(BigInteger p, BigInteger g, BigInteger q, BigInteger j, DHValidationParameters validation2) {
        this(p, g, q, 160, 0, j, validation2);
    }

    public DHParameters(BigInteger p, BigInteger g, BigInteger q, int m, int l, BigInteger j, DHValidationParameters validation2) {
        if (l != 0) {
            if (l > p.bitLength()) {
                throw new IllegalArgumentException("when l value specified, it must satisfy 2^(l-1) <= p");
            } else if (l < m) {
                throw new IllegalArgumentException("when l value specified, it may not be less than m value");
            }
        }
        this.f6817g = g;
        this.f6821p = p;
        this.f6822q = q;
        this.f6820m = m;
        this.f6819l = l;
        this.f6818j = j;
        this.validation = validation2;
    }

    public BigInteger getP() {
        return this.f6821p;
    }

    public BigInteger getG() {
        return this.f6817g;
    }

    public BigInteger getQ() {
        return this.f6822q;
    }

    public BigInteger getJ() {
        return this.f6818j;
    }

    public int getM() {
        return this.f6820m;
    }

    public int getL() {
        return this.f6819l;
    }

    public DHValidationParameters getValidationParameters() {
        return this.validation;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHParameters)) {
            return false;
        }
        DHParameters pm = (DHParameters) obj;
        if (getQ() != null) {
            if (!getQ().equals(pm.getQ())) {
                return false;
            }
        } else if (pm.getQ() != null) {
            return false;
        }
        if (!pm.getP().equals(this.f6821p) || !pm.getG().equals(this.f6817g)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (getQ() != null ? getQ().hashCode() : 0) ^ (getG().hashCode() ^ getP().hashCode());
    }
}
