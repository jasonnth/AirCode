package org.spongycastle.crypto.p326ec;

import org.spongycastle.math.p332ec.ECPoint;

/* renamed from: org.spongycastle.crypto.ec.ECPair */
public class ECPair {

    /* renamed from: x */
    private final ECPoint f6612x;

    /* renamed from: y */
    private final ECPoint f6613y;

    public ECPair(ECPoint x, ECPoint y) {
        this.f6612x = x;
        this.f6613y = y;
    }

    public ECPoint getX() {
        return this.f6612x;
    }

    public ECPoint getY() {
        return this.f6613y;
    }

    public boolean equals(ECPair other) {
        return other.getX().equals(getX()) && other.getY().equals(getY());
    }

    public boolean equals(Object other) {
        if (other instanceof ECPair) {
            return equals((ECPair) other);
        }
        return false;
    }

    public int hashCode() {
        return this.f6612x.hashCode() + (this.f6613y.hashCode() * 37);
    }
}
