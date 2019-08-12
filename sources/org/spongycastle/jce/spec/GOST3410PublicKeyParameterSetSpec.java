package org.spongycastle.jce.spec;

import java.math.BigInteger;

public class GOST3410PublicKeyParameterSetSpec {

    /* renamed from: a */
    private BigInteger f6951a;

    /* renamed from: p */
    private BigInteger f6952p;

    /* renamed from: q */
    private BigInteger f6953q;

    public GOST3410PublicKeyParameterSetSpec(BigInteger p, BigInteger q, BigInteger a) {
        this.f6952p = p;
        this.f6953q = q;
        this.f6951a = a;
    }

    public BigInteger getP() {
        return this.f6952p;
    }

    public BigInteger getQ() {
        return this.f6953q;
    }

    public BigInteger getA() {
        return this.f6951a;
    }

    public boolean equals(Object o) {
        if (!(o instanceof GOST3410PublicKeyParameterSetSpec)) {
            return false;
        }
        GOST3410PublicKeyParameterSetSpec other = (GOST3410PublicKeyParameterSetSpec) o;
        if (!this.f6951a.equals(other.f6951a) || !this.f6952p.equals(other.f6952p) || !this.f6953q.equals(other.f6953q)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.f6951a.hashCode() ^ this.f6952p.hashCode()) ^ this.f6953q.hashCode();
    }
}
