package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;

public class CramerShoupParameters implements CipherParameters {

    /* renamed from: H */
    private Digest f6804H;

    /* renamed from: g1 */
    private BigInteger f6805g1;

    /* renamed from: g2 */
    private BigInteger f6806g2;

    /* renamed from: p */
    private BigInteger f6807p;

    public CramerShoupParameters(BigInteger p, BigInteger g1, BigInteger g2, Digest H) {
        this.f6807p = p;
        this.f6805g1 = g1;
        this.f6806g2 = g2;
        this.f6804H = H;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAParameters)) {
            return false;
        }
        CramerShoupParameters pm = (CramerShoupParameters) obj;
        if (!pm.getP().equals(this.f6807p) || !pm.getG1().equals(this.f6805g1) || !pm.getG2().equals(this.f6806g2)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG1().hashCode()) ^ getG2().hashCode();
    }

    public BigInteger getG1() {
        return this.f6805g1;
    }

    public BigInteger getG2() {
        return this.f6806g2;
    }

    public BigInteger getP() {
        return this.f6807p;
    }

    public Digest getH() {
        this.f6804H.reset();
        return this.f6804H;
    }
}
