package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class DSAParameters implements CipherParameters {

    /* renamed from: g */
    private BigInteger f6827g;

    /* renamed from: p */
    private BigInteger f6828p;

    /* renamed from: q */
    private BigInteger f6829q;
    private DSAValidationParameters validation;

    public DSAParameters(BigInteger p, BigInteger q, BigInteger g) {
        this.f6827g = g;
        this.f6828p = p;
        this.f6829q = q;
    }

    public DSAParameters(BigInteger p, BigInteger q, BigInteger g, DSAValidationParameters params) {
        this.f6827g = g;
        this.f6828p = p;
        this.f6829q = q;
        this.validation = params;
    }

    public BigInteger getP() {
        return this.f6828p;
    }

    public BigInteger getQ() {
        return this.f6829q;
    }

    public BigInteger getG() {
        return this.f6827g;
    }

    public DSAValidationParameters getValidationParameters() {
        return this.validation;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAParameters)) {
            return false;
        }
        DSAParameters pm = (DSAParameters) obj;
        if (!pm.getP().equals(this.f6828p) || !pm.getQ().equals(this.f6829q) || !pm.getG().equals(this.f6827g)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getQ().hashCode()) ^ getG().hashCode();
    }
}
