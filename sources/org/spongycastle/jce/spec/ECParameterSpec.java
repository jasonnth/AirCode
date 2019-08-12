package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECPoint;

public class ECParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: G */
    private ECPoint f6938G;
    private ECCurve curve;

    /* renamed from: h */
    private BigInteger f6939h;

    /* renamed from: n */
    private BigInteger f6940n;
    private byte[] seed;

    public ECParameterSpec(ECCurve curve2, ECPoint G, BigInteger n) {
        this.curve = curve2;
        this.f6938G = G.normalize();
        this.f6940n = n;
        this.f6939h = BigInteger.valueOf(1);
        this.seed = null;
    }

    public ECParameterSpec(ECCurve curve2, ECPoint G, BigInteger n, BigInteger h) {
        this.curve = curve2;
        this.f6938G = G.normalize();
        this.f6940n = n;
        this.f6939h = h;
        this.seed = null;
    }

    public ECParameterSpec(ECCurve curve2, ECPoint G, BigInteger n, BigInteger h, byte[] seed2) {
        this.curve = curve2;
        this.f6938G = G.normalize();
        this.f6940n = n;
        this.f6939h = h;
        this.seed = seed2;
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.f6938G;
    }

    public BigInteger getN() {
        return this.f6940n;
    }

    public BigInteger getH() {
        return this.f6939h;
    }

    public byte[] getSeed() {
        return this.seed;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ECParameterSpec)) {
            return false;
        }
        ECParameterSpec other = (ECParameterSpec) o;
        if (!getCurve().equals(other.getCurve()) || !getG().equals(other.getG())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return getCurve().hashCode() ^ getG().hashCode();
    }
}
