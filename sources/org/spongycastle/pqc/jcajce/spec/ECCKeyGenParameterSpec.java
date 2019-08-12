package org.spongycastle.pqc.jcajce.spec;

import java.security.InvalidParameterException;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2;

public class ECCKeyGenParameterSpec implements AlgorithmParameterSpec {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private int fieldPoly;

    /* renamed from: m */
    private int f7204m;

    /* renamed from: n */
    private int f7205n;

    /* renamed from: t */
    private int f7206t;

    public ECCKeyGenParameterSpec() {
        this(11, 50);
    }

    public ECCKeyGenParameterSpec(int keysize) throws InvalidParameterException {
        if (keysize < 1) {
            throw new InvalidParameterException("key size must be positive");
        }
        this.f7204m = 0;
        this.f7205n = 1;
        while (this.f7205n < keysize) {
            this.f7205n <<= 1;
            this.f7204m++;
        }
        this.f7206t = this.f7205n >>> 1;
        this.f7206t /= this.f7204m;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(this.f7204m);
    }

    public ECCKeyGenParameterSpec(int m, int t) throws InvalidParameterException {
        if (m < 1) {
            throw new InvalidParameterException("m must be positive");
        } else if (m > 32) {
            throw new InvalidParameterException("m is too large");
        } else {
            this.f7204m = m;
            this.f7205n = 1 << m;
            if (t < 0) {
                throw new InvalidParameterException("t must be positive");
            } else if (t > this.f7205n) {
                throw new InvalidParameterException("t must be less than n = 2^m");
            } else {
                this.f7206t = t;
                this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(m);
            }
        }
    }

    public ECCKeyGenParameterSpec(int m, int t, int poly) throws InvalidParameterException {
        this.f7204m = m;
        if (m < 1) {
            throw new InvalidParameterException("m must be positive");
        } else if (m > 32) {
            throw new InvalidParameterException(" m is too large");
        } else {
            this.f7205n = 1 << m;
            this.f7206t = t;
            if (t < 0) {
                throw new InvalidParameterException("t must be positive");
            } else if (t > this.f7205n) {
                throw new InvalidParameterException("t must be less than n = 2^m");
            } else if (PolynomialRingGF2.degree(poly) != m || !PolynomialRingGF2.isIrreducible(poly)) {
                throw new InvalidParameterException("polynomial is not a field polynomial for GF(2^m)");
            } else {
                this.fieldPoly = poly;
            }
        }
    }

    public int getM() {
        return this.f7204m;
    }

    public int getN() {
        return this.f7205n;
    }

    public int getT() {
        return this.f7206t;
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }
}
