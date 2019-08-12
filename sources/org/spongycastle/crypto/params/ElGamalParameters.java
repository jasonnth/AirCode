package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class ElGamalParameters implements CipherParameters {

    /* renamed from: g */
    private BigInteger f6837g;

    /* renamed from: l */
    private int f6838l;

    /* renamed from: p */
    private BigInteger f6839p;

    public ElGamalParameters(BigInteger p, BigInteger g) {
        this(p, g, 0);
    }

    public ElGamalParameters(BigInteger p, BigInteger g, int l) {
        this.f6837g = g;
        this.f6839p = p;
        this.f6838l = l;
    }

    public BigInteger getP() {
        return this.f6839p;
    }

    public BigInteger getG() {
        return this.f6837g;
    }

    public int getL() {
        return this.f6838l;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ElGamalParameters)) {
            return false;
        }
        ElGamalParameters pm = (ElGamalParameters) obj;
        if (!pm.getP().equals(this.f6839p) || !pm.getG().equals(this.f6837g) || pm.getL() != this.f6838l) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) + this.f6838l;
    }
}
