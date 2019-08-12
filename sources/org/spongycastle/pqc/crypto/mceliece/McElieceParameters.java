package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2;

public class McElieceParameters implements CipherParameters {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private int fieldPoly;

    /* renamed from: m */
    private int f7116m;

    /* renamed from: n */
    private int f7117n;

    /* renamed from: t */
    private int f7118t;

    public McElieceParameters() {
        this(11, 50);
    }

    public McElieceParameters(int keysize) throws IllegalArgumentException {
        if (keysize < 1) {
            throw new IllegalArgumentException("key size must be positive");
        }
        this.f7116m = 0;
        this.f7117n = 1;
        while (this.f7117n < keysize) {
            this.f7117n <<= 1;
            this.f7116m++;
        }
        this.f7118t = this.f7117n >>> 1;
        this.f7118t /= this.f7116m;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(this.f7116m);
    }

    public McElieceParameters(int m, int t) throws IllegalArgumentException {
        if (m < 1) {
            throw new IllegalArgumentException("m must be positive");
        } else if (m > 32) {
            throw new IllegalArgumentException("m is too large");
        } else {
            this.f7116m = m;
            this.f7117n = 1 << m;
            if (t < 0) {
                throw new IllegalArgumentException("t must be positive");
            } else if (t > this.f7117n) {
                throw new IllegalArgumentException("t must be less than n = 2^m");
            } else {
                this.f7118t = t;
                this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(m);
            }
        }
    }

    public McElieceParameters(int m, int t, int poly) throws IllegalArgumentException {
        this.f7116m = m;
        if (m < 1) {
            throw new IllegalArgumentException("m must be positive");
        } else if (m > 32) {
            throw new IllegalArgumentException(" m is too large");
        } else {
            this.f7117n = 1 << m;
            this.f7118t = t;
            if (t < 0) {
                throw new IllegalArgumentException("t must be positive");
            } else if (t > this.f7117n) {
                throw new IllegalArgumentException("t must be less than n = 2^m");
            } else if (PolynomialRingGF2.degree(poly) != m || !PolynomialRingGF2.isIrreducible(poly)) {
                throw new IllegalArgumentException("polynomial is not a field polynomial for GF(2^m)");
            } else {
                this.fieldPoly = poly;
            }
        }
    }

    public int getM() {
        return this.f7116m;
    }

    public int getN() {
        return this.f7117n;
    }

    public int getT() {
        return this.f7118t;
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }
}
