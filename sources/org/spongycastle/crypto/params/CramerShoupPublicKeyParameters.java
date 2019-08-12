package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class CramerShoupPublicKeyParameters extends CramerShoupKeyParameters {

    /* renamed from: c */
    private BigInteger f6814c;

    /* renamed from: d */
    private BigInteger f6815d;

    /* renamed from: h */
    private BigInteger f6816h;

    public CramerShoupPublicKeyParameters(CramerShoupParameters params, BigInteger c, BigInteger d, BigInteger h) {
        super(false, params);
        this.f6814c = c;
        this.f6815d = d;
        this.f6816h = h;
    }

    public BigInteger getC() {
        return this.f6814c;
    }

    public BigInteger getD() {
        return this.f6815d;
    }

    public BigInteger getH() {
        return this.f6816h;
    }

    public int hashCode() {
        return ((this.f6814c.hashCode() ^ this.f6815d.hashCode()) ^ this.f6816h.hashCode()) ^ super.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupPublicKeyParameters)) {
            return false;
        }
        CramerShoupPublicKeyParameters other = (CramerShoupPublicKeyParameters) obj;
        if (!other.getC().equals(this.f6814c) || !other.getD().equals(this.f6815d) || !other.getH().equals(this.f6816h) || !super.equals(obj)) {
            return false;
        }
        return true;
    }
}
