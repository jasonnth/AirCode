package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class DHPublicKeyParameters extends DHKeyParameters {

    /* renamed from: y */
    private BigInteger f6824y;

    public DHPublicKeyParameters(BigInteger y, DHParameters params) {
        super(false, params);
        this.f6824y = y;
    }

    public BigInteger getY() {
        return this.f6824y;
    }

    public int hashCode() {
        return this.f6824y.hashCode() ^ super.hashCode();
    }

    public boolean equals(Object obj) {
        if ((obj instanceof DHPublicKeyParameters) && ((DHPublicKeyParameters) obj).getY().equals(this.f6824y) && super.equals(obj)) {
            return true;
        }
        return false;
    }
}
