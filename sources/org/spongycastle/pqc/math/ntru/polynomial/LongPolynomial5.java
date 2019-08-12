package org.spongycastle.pqc.math.ntru.polynomial;

import java.lang.reflect.Array;
import org.spongycastle.util.Arrays;

public class LongPolynomial5 {
    private long[] coeffs;
    private int numCoeffs;

    public LongPolynomial5(IntegerPolynomial p) {
        this.numCoeffs = p.coeffs.length;
        this.coeffs = new long[((this.numCoeffs + 4) / 5)];
        int cIdx = 0;
        int shift = 0;
        for (int i = 0; i < this.numCoeffs; i++) {
            long[] jArr = this.coeffs;
            jArr[cIdx] = jArr[cIdx] | (((long) p.coeffs[i]) << shift);
            shift += 12;
            if (shift >= 60) {
                shift = 0;
                cIdx++;
            }
        }
    }

    private LongPolynomial5(long[] coeffs2, int numCoeffs2) {
        this.coeffs = coeffs2;
        this.numCoeffs = numCoeffs2;
    }

    public LongPolynomial5 mult(TernaryPolynomial poly2) {
        long iCoeff;
        int newIdx;
        long[][] prod = (long[][]) Array.newInstance(Long.TYPE, new int[]{5, (this.coeffs.length + ((poly2.size() + 4) / 5)) - 1});
        int[] ones = poly2.getOnes();
        for (int idx = 0; idx != ones.length; idx++) {
            int pIdx = ones[idx];
            int cIdx = pIdx / 5;
            int m = pIdx - (cIdx * 5);
            for (long j : this.coeffs) {
                prod[m][cIdx] = (prod[m][cIdx] + j) & 576319980446939135L;
                cIdx++;
            }
        }
        int[] negOnes = poly2.getNegOnes();
        for (int idx2 = 0; idx2 != negOnes.length; idx2++) {
            int pIdx2 = negOnes[idx2];
            int cIdx2 = pIdx2 / 5;
            int m2 = pIdx2 - (cIdx2 * 5);
            for (long j2 : this.coeffs) {
                prod[m2][cIdx2] = ((576601524159907840L + prod[m2][cIdx2]) - j2) & 576319980446939135L;
                cIdx2++;
            }
        }
        long[] cCoeffs = Arrays.copyOf(prod[0], prod[0].length + 1);
        for (int m3 = 1; m3 <= 4; m3++) {
            int shift = m3 * 12;
            int shift60 = 60 - shift;
            long mask = (1 << shift60) - 1;
            int pLen = prod[m3].length;
            for (int i = 0; i < pLen; i++) {
                long upper = prod[m3][i] >> shift60;
                cCoeffs[i] = (cCoeffs[i] + ((prod[m3][i] & mask) << shift)) & 576319980446939135L;
                int nextIdx = i + 1;
                cCoeffs[nextIdx] = (cCoeffs[nextIdx] + upper) & 576319980446939135L;
            }
        }
        int shift2 = (this.numCoeffs % 5) * 12;
        for (int cIdx3 = this.coeffs.length - 1; cIdx3 < cCoeffs.length; cIdx3++) {
            if (cIdx3 == this.coeffs.length - 1) {
                iCoeff = this.numCoeffs == 5 ? 0 : cCoeffs[cIdx3] >> shift2;
                newIdx = 0;
            } else {
                iCoeff = cCoeffs[cIdx3];
                newIdx = (cIdx3 * 5) - this.numCoeffs;
            }
            int base = newIdx / 5;
            int m4 = newIdx - (base * 5);
            long upper2 = iCoeff >> ((5 - m4) * 12);
            cCoeffs[base] = (cCoeffs[base] + (iCoeff << (m4 * 12))) & 576319980446939135L;
            int base1 = base + 1;
            if (base1 < this.coeffs.length) {
                cCoeffs[base1] = (cCoeffs[base1] + upper2) & 576319980446939135L;
            }
        }
        LongPolynomial5 longPolynomial5 = new LongPolynomial5(cCoeffs, this.numCoeffs);
        return longPolynomial5;
    }

    public IntegerPolynomial toIntegerPolynomial() {
        int[] intCoeffs = new int[this.numCoeffs];
        int cIdx = 0;
        int shift = 0;
        for (int i = 0; i < this.numCoeffs; i++) {
            intCoeffs[i] = (int) ((this.coeffs[cIdx] >> shift) & 2047);
            shift += 12;
            if (shift >= 60) {
                shift = 0;
                cIdx++;
            }
        }
        return new IntegerPolynomial(intCoeffs);
    }
}
