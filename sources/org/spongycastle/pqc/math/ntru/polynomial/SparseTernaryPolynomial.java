package org.spongycastle.pqc.math.ntru.polynomial;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.pqc.math.ntru.util.ArrayEncoder;
import org.spongycastle.pqc.math.ntru.util.Util;
import org.spongycastle.util.Arrays;

public class SparseTernaryPolynomial implements TernaryPolynomial {
    private static final int BITS_PER_INDEX = 11;

    /* renamed from: N */
    private int f7241N;
    private int[] negOnes;
    private int[] ones;

    SparseTernaryPolynomial(int N, int[] ones2, int[] negOnes2) {
        this.f7241N = N;
        this.ones = ones2;
        this.negOnes = negOnes2;
    }

    public SparseTernaryPolynomial(IntegerPolynomial intPoly) {
        this(intPoly.coeffs);
    }

    public SparseTernaryPolynomial(int[] coeffs) {
        this.f7241N = coeffs.length;
        this.ones = new int[this.f7241N];
        this.negOnes = new int[this.f7241N];
        int onesIdx = 0;
        int negOnesIdx = 0;
        for (int i = 0; i < this.f7241N; i++) {
            int c = coeffs[i];
            switch (c) {
                case -1:
                    int negOnesIdx2 = negOnesIdx + 1;
                    this.negOnes[negOnesIdx] = i;
                    negOnesIdx = negOnesIdx2;
                    break;
                case 0:
                    break;
                case 1:
                    int onesIdx2 = onesIdx + 1;
                    this.ones[onesIdx] = i;
                    onesIdx = onesIdx2;
                    break;
                default:
                    throw new IllegalArgumentException("Illegal value: " + c + ", must be one of {-1, 0, 1}");
            }
        }
        this.ones = Arrays.copyOf(this.ones, onesIdx);
        this.negOnes = Arrays.copyOf(this.negOnes, negOnesIdx);
    }

    public static SparseTernaryPolynomial fromBinary(InputStream is, int N, int numOnes, int numNegOnes) throws IOException {
        int bitsPerIndex = 32 - Integer.numberOfLeadingZeros(2047);
        return new SparseTernaryPolynomial(N, ArrayEncoder.decodeModQ(Util.readFullLength(is, ((numOnes * bitsPerIndex) + 7) / 8), numOnes, 2048), ArrayEncoder.decodeModQ(Util.readFullLength(is, ((numNegOnes * bitsPerIndex) + 7) / 8), numNegOnes, 2048));
    }

    public static SparseTernaryPolynomial generateRandom(int N, int numOnes, int numNegOnes, SecureRandom random) {
        return new SparseTernaryPolynomial(Util.generateRandomTernary(N, numOnes, numNegOnes, random));
    }

    public IntegerPolynomial mult(IntegerPolynomial poly2) {
        int[] b = poly2.coeffs;
        if (b.length != this.f7241N) {
            throw new IllegalArgumentException("Number of coefficients must be the same");
        }
        int[] c = new int[this.f7241N];
        for (int idx = 0; idx != this.ones.length; idx++) {
            int j = (this.f7241N - 1) - this.ones[idx];
            for (int k = this.f7241N - 1; k >= 0; k--) {
                c[k] = c[k] + b[j];
                j--;
                if (j < 0) {
                    j = this.f7241N - 1;
                }
            }
        }
        for (int idx2 = 0; idx2 != this.negOnes.length; idx2++) {
            int j2 = (this.f7241N - 1) - this.negOnes[idx2];
            for (int k2 = this.f7241N - 1; k2 >= 0; k2--) {
                c[k2] = c[k2] - b[j2];
                j2--;
                if (j2 < 0) {
                    j2 = this.f7241N - 1;
                }
            }
        }
        return new IntegerPolynomial(c);
    }

    public IntegerPolynomial mult(IntegerPolynomial poly2, int modulus) {
        IntegerPolynomial c = mult(poly2);
        c.mod(modulus);
        return c;
    }

    public BigIntPolynomial mult(BigIntPolynomial poly2) {
        BigInteger[] b = poly2.coeffs;
        if (b.length != this.f7241N) {
            throw new IllegalArgumentException("Number of coefficients must be the same");
        }
        BigInteger[] c = new BigInteger[this.f7241N];
        for (int i = 0; i < this.f7241N; i++) {
            c[i] = BigInteger.ZERO;
        }
        for (int idx = 0; idx != this.ones.length; idx++) {
            int j = (this.f7241N - 1) - this.ones[idx];
            for (int k = this.f7241N - 1; k >= 0; k--) {
                c[k] = c[k].add(b[j]);
                j--;
                if (j < 0) {
                    j = this.f7241N - 1;
                }
            }
        }
        for (int idx2 = 0; idx2 != this.negOnes.length; idx2++) {
            int j2 = (this.f7241N - 1) - this.negOnes[idx2];
            for (int k2 = this.f7241N - 1; k2 >= 0; k2--) {
                c[k2] = c[k2].subtract(b[j2]);
                j2--;
                if (j2 < 0) {
                    j2 = this.f7241N - 1;
                }
            }
        }
        return new BigIntPolynomial(c);
    }

    public int[] getOnes() {
        return this.ones;
    }

    public int[] getNegOnes() {
        return this.negOnes;
    }

    public byte[] toBinary() {
        byte[] bin1 = ArrayEncoder.encodeModQ(this.ones, 2048);
        byte[] bin2 = ArrayEncoder.encodeModQ(this.negOnes, 2048);
        byte[] bin = Arrays.copyOf(bin1, bin1.length + bin2.length);
        System.arraycopy(bin2, 0, bin, bin1.length, bin2.length);
        return bin;
    }

    public IntegerPolynomial toIntegerPolynomial() {
        int[] coeffs = new int[this.f7241N];
        for (int idx = 0; idx != this.ones.length; idx++) {
            coeffs[this.ones[idx]] = 1;
        }
        for (int idx2 = 0; idx2 != this.negOnes.length; idx2++) {
            coeffs[this.negOnes[idx2]] = -1;
        }
        return new IntegerPolynomial(coeffs);
    }

    public int size() {
        return this.f7241N;
    }

    public void clear() {
        for (int i = 0; i < this.ones.length; i++) {
            this.ones[i] = 0;
        }
        for (int i2 = 0; i2 < this.negOnes.length; i2++) {
            this.negOnes[i2] = 0;
        }
    }

    public int hashCode() {
        return ((((this.f7241N + 31) * 31) + Arrays.hashCode(this.negOnes)) * 31) + Arrays.hashCode(this.ones);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SparseTernaryPolynomial other = (SparseTernaryPolynomial) obj;
        if (this.f7241N != other.f7241N) {
            return false;
        }
        if (!Arrays.areEqual(this.negOnes, other.negOnes)) {
            return false;
        }
        if (!Arrays.areEqual(this.ones, other.ones)) {
            return false;
        }
        return true;
    }
}
