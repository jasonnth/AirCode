package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class DHPrivateKeyParameters extends DHKeyParameters {

    /* renamed from: x */
    private BigInteger f6823x;

    public DHPrivateKeyParameters(BigInteger x, DHParameters params) {
        super(true, params);
        this.f6823x = x;
    }

    public BigInteger getX() {
        return this.f6823x;
    }

    public int hashCode() {
        return this.f6823x.hashCode() ^ super.hashCode();
    }

    public boolean equals(Object obj) {
        if ((obj instanceof DHPrivateKeyParameters) && ((DHPrivateKeyParameters) obj).getX().equals(this.f6823x) && super.equals(obj)) {
            return true;
        }
        return false;
    }
}
