package org.spongycastle.crypto.prng.drbg;

import org.spongycastle.math.p332ec.ECPoint;

public class DualECPoints {
    private final int cofactor;

    /* renamed from: p */
    private final ECPoint f6876p;

    /* renamed from: q */
    private final ECPoint f6877q;
    private final int securityStrength;

    public DualECPoints(int securityStrength2, ECPoint p, ECPoint q, int cofactor2) {
        if (!p.getCurve().equals(q.getCurve())) {
            throw new IllegalArgumentException("points need to be on the same curve");
        }
        this.securityStrength = securityStrength2;
        this.f6876p = p;
        this.f6877q = q;
        this.cofactor = cofactor2;
    }

    public int getSeedLen() {
        return this.f6876p.getCurve().getFieldSize();
    }

    public int getMaxOutlen() {
        return ((this.f6876p.getCurve().getFieldSize() - (log2(this.cofactor) + 13)) / 8) * 8;
    }

    public ECPoint getP() {
        return this.f6876p;
    }

    public ECPoint getQ() {
        return this.f6877q;
    }

    public int getSecurityStrength() {
        return this.securityStrength;
    }

    public int getCofactor() {
        return this.cofactor;
    }

    private static int log2(int value) {
        int log = 0;
        while (true) {
            value >>= 1;
            if (value == 0) {
                return log;
            }
            log++;
        }
    }
}
