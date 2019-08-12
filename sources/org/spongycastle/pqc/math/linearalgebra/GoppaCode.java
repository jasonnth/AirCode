package org.spongycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;
import java.security.SecureRandom;

public final class GoppaCode {

    public static class MaMaPe {

        /* renamed from: h */
        private GF2Matrix f7228h;

        /* renamed from: p */
        private Permutation f7229p;

        /* renamed from: s */
        private GF2Matrix f7230s;

        public MaMaPe(GF2Matrix s, GF2Matrix h, Permutation p) {
            this.f7230s = s;
            this.f7228h = h;
            this.f7229p = p;
        }

        public GF2Matrix getFirstMatrix() {
            return this.f7230s;
        }

        public GF2Matrix getSecondMatrix() {
            return this.f7228h;
        }

        public Permutation getPermutation() {
            return this.f7229p;
        }
    }

    public static class MatrixSet {

        /* renamed from: g */
        private GF2Matrix f7231g;
        private int[] setJ;

        public MatrixSet(GF2Matrix g, int[] setJ2) {
            this.f7231g = g;
            this.setJ = setJ2;
        }

        public GF2Matrix getG() {
            return this.f7231g;
        }

        public int[] getSetJ() {
            return this.setJ;
        }
    }

    private GoppaCode() {
    }

    public static GF2Matrix createCanonicalCheckMatrix(GF2mField field, PolynomialGF2mSmallM gp) {
        int m = field.getDegree();
        int n = 1 << m;
        int t = gp.getDegree();
        int[][] hArray = (int[][]) Array.newInstance(Integer.TYPE, new int[]{t, n});
        int[][] yz = (int[][]) Array.newInstance(Integer.TYPE, new int[]{t, n});
        for (int j = 0; j < n; j++) {
            yz[0][j] = field.inverse(gp.evaluateAt(j));
        }
        for (int i = 1; i < t; i++) {
            for (int j2 = 0; j2 < n; j2++) {
                yz[i][j2] = field.mult(yz[i - 1][j2], j2);
            }
        }
        for (int i2 = 0; i2 < t; i2++) {
            for (int j3 = 0; j3 < n; j3++) {
                for (int k = 0; k <= i2; k++) {
                    hArray[i2][j3] = field.add(hArray[i2][j3], field.mult(yz[k][j3], gp.getCoefficient((t + k) - i2)));
                }
            }
        }
        int[][] result = (int[][]) Array.newInstance(Integer.TYPE, new int[]{t * m, (n + 31) >>> 5});
        for (int j4 = 0; j4 < n; j4++) {
            int q = j4 >>> 5;
            int r = 1 << (j4 & 31);
            for (int i3 = 0; i3 < t; i3++) {
                int e = hArray[i3][j4];
                for (int u = 0; u < m; u++) {
                    if (((e >>> u) & 1) != 0) {
                        int[] iArr = result[(((i3 + 1) * m) - u) - 1];
                        iArr[q] = iArr[q] ^ r;
                    }
                }
            }
        }
        GF2Matrix gF2Matrix = new GF2Matrix(n, result);
        return gF2Matrix;
    }

    public static MaMaPe computeSystematicForm(GF2Matrix h, SecureRandom sr) {
        Permutation p;
        GF2Matrix hp;
        GF2Matrix sInv;
        boolean found;
        int n = h.getNumColumns();
        GF2Matrix s = null;
        do {
            p = new Permutation(n, sr);
            hp = (GF2Matrix) h.rightMultiply(p);
            sInv = hp.getLeftSubMatrix();
            found = true;
            try {
                s = (GF2Matrix) sInv.computeInverse();
                continue;
            } catch (ArithmeticException e) {
                found = false;
                continue;
            }
        } while (!found);
        return new MaMaPe(sInv, ((GF2Matrix) s.rightMultiply((Matrix) hp)).getRightSubMatrix(), p);
    }

    public static GF2Vector syndromeDecode(GF2Vector syndVec, GF2mField field, PolynomialGF2mSmallM gp, PolynomialGF2mSmallM[] sqRootMatrix) {
        int n = 1 << field.getDegree();
        GF2Vector errors = new GF2Vector(n);
        if (!syndVec.isZero()) {
            PolynomialGF2mSmallM[] ab = new PolynomialGF2mSmallM(syndVec.toExtensionFieldVector(field)).modInverse(gp).addMonomial(1).modSquareRootMatrix(sqRootMatrix).modPolynomialToFracton(gp);
            PolynomialGF2mSmallM a2plusXb2 = ab[0].multiply(ab[0]).add(ab[1].multiply(ab[1]).multWithMonomial(1));
            PolynomialGF2mSmallM elp = a2plusXb2.multWithElement(field.inverse(a2plusXb2.getHeadCoefficient()));
            for (int i = 0; i < n; i++) {
                if (elp.evaluateAt(i) == 0) {
                    errors.setBit(i);
                }
            }
        }
        return errors;
    }
}
