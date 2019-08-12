package org.spongycastle.crypto.agreement.jpake;

import java.math.BigInteger;

public class JPAKEPrimeOrderGroup {

    /* renamed from: g */
    private final BigInteger f6459g;

    /* renamed from: p */
    private final BigInteger f6460p;

    /* renamed from: q */
    private final BigInteger f6461q;

    public JPAKEPrimeOrderGroup(BigInteger p, BigInteger q, BigInteger g) {
        this(p, q, g, false);
    }

    JPAKEPrimeOrderGroup(BigInteger p, BigInteger q, BigInteger g, boolean skipChecks) {
        JPAKEUtil.validateNotNull(p, "p");
        JPAKEUtil.validateNotNull(q, "q");
        JPAKEUtil.validateNotNull(g, "g");
        if (!skipChecks) {
            if (!p.subtract(JPAKEUtil.ONE).mod(q).equals(JPAKEUtil.ZERO)) {
                throw new IllegalArgumentException("p-1 must be evenly divisible by q");
            } else if (g.compareTo(BigInteger.valueOf(2)) == -1 || g.compareTo(p.subtract(JPAKEUtil.ONE)) == 1) {
                throw new IllegalArgumentException("g must be in [2, p-1]");
            } else if (!g.modPow(q, p).equals(JPAKEUtil.ONE)) {
                throw new IllegalArgumentException("g^q mod p must equal 1");
            } else if (!p.isProbablePrime(20)) {
                throw new IllegalArgumentException("p must be prime");
            } else if (!q.isProbablePrime(20)) {
                throw new IllegalArgumentException("q must be prime");
            }
        }
        this.f6460p = p;
        this.f6461q = q;
        this.f6459g = g;
    }

    public BigInteger getP() {
        return this.f6460p;
    }

    public BigInteger getQ() {
        return this.f6461q;
    }

    public BigInteger getG() {
        return this.f6459g;
    }
}
