package org.spongycastle.pqc.crypto.rainbow.util;

import java.lang.reflect.Array;

public class ComputeInField {

    /* renamed from: A */
    private short[][] f7184A;

    /* renamed from: x */
    short[] f7185x;

    public short[] solveEquation(short[][] B, short[] b) {
        try {
            if (B.length != b.length) {
                throw new RuntimeException("The equation system is not solvable");
            }
            this.f7184A = (short[][]) Array.newInstance(Short.TYPE, new int[]{B.length, B.length + 1});
            this.f7185x = new short[B.length];
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    this.f7184A[i][j] = B[i][j];
                }
            }
            for (int i2 = 0; i2 < b.length; i2++) {
                this.f7184A[i2][b.length] = GF2Field.addElem(b[i2], this.f7184A[i2][b.length]);
            }
            computeZerosUnder(false);
            substitute();
            return this.f7185x;
        } catch (RuntimeException e) {
            return null;
        }
    }

    public short[][] inverse(short[][] coef) {
        try {
            this.f7184A = (short[][]) Array.newInstance(Short.TYPE, new int[]{coef.length, coef.length * 2});
            if (coef.length != coef[0].length) {
                throw new RuntimeException("The matrix is not invertible. Please choose another one!");
            }
            for (int i = 0; i < coef.length; i++) {
                for (int j = 0; j < coef.length; j++) {
                    this.f7184A[i][j] = coef[i][j];
                }
                for (int j2 = coef.length; j2 < coef.length * 2; j2++) {
                    this.f7184A[i][j2] = 0;
                }
                this.f7184A[i][this.f7184A.length + i] = 1;
            }
            computeZerosUnder(true);
            for (int i2 = 0; i2 < this.f7184A.length; i2++) {
                short factor = GF2Field.invElem(this.f7184A[i2][i2]);
                for (int j3 = i2; j3 < this.f7184A.length * 2; j3++) {
                    this.f7184A[i2][j3] = GF2Field.multElem(this.f7184A[i2][j3], factor);
                }
            }
            computeZerosAbove();
            short[][] inverse = (short[][]) Array.newInstance(Short.TYPE, new int[]{this.f7184A.length, this.f7184A.length});
            for (int i3 = 0; i3 < this.f7184A.length; i3++) {
                for (int j4 = this.f7184A.length; j4 < this.f7184A.length * 2; j4++) {
                    inverse[i3][j4 - this.f7184A.length] = this.f7184A[i3][j4];
                }
            }
            return inverse;
        } catch (RuntimeException e) {
            return null;
        }
    }

    private void computeZerosUnder(boolean usedForInverse) throws RuntimeException {
        int length;
        if (usedForInverse) {
            length = this.f7184A.length * 2;
        } else {
            length = this.f7184A.length + 1;
        }
        for (int k = 0; k < this.f7184A.length - 1; k++) {
            for (int i = k + 1; i < this.f7184A.length; i++) {
                short factor1 = this.f7184A[i][k];
                short factor2 = GF2Field.invElem(this.f7184A[k][k]);
                if (factor2 == 0) {
                    throw new RuntimeException("Matrix not invertible! We have to choose another one!");
                }
                for (int j = k; j < length; j++) {
                    this.f7184A[i][j] = GF2Field.addElem(this.f7184A[i][j], GF2Field.multElem(factor1, GF2Field.multElem(this.f7184A[k][j], factor2)));
                }
            }
        }
    }

    private void computeZerosAbove() throws RuntimeException {
        for (int k = this.f7184A.length - 1; k > 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                short factor1 = this.f7184A[i][k];
                short factor2 = GF2Field.invElem(this.f7184A[k][k]);
                if (factor2 == 0) {
                    throw new RuntimeException("The matrix is not invertible");
                }
                for (int j = k; j < this.f7184A.length * 2; j++) {
                    this.f7184A[i][j] = GF2Field.addElem(this.f7184A[i][j], GF2Field.multElem(factor1, GF2Field.multElem(this.f7184A[k][j], factor2)));
                }
            }
        }
    }

    private void substitute() throws RuntimeException {
        short temp = GF2Field.invElem(this.f7184A[this.f7184A.length - 1][this.f7184A.length - 1]);
        if (temp == 0) {
            throw new RuntimeException("The equation system is not solvable");
        }
        this.f7185x[this.f7184A.length - 1] = GF2Field.multElem(this.f7184A[this.f7184A.length - 1][this.f7184A.length], temp);
        for (int i = this.f7184A.length - 2; i >= 0; i--) {
            short tmp = this.f7184A[i][this.f7184A.length];
            for (int j = this.f7184A.length - 1; j > i; j--) {
                tmp = GF2Field.addElem(tmp, GF2Field.multElem(this.f7184A[i][j], this.f7185x[j]));
            }
            short temp2 = GF2Field.invElem(this.f7184A[i][i]);
            if (temp2 == 0) {
                throw new RuntimeException("Not solvable equation system");
            }
            this.f7185x[i] = GF2Field.multElem(tmp, temp2);
        }
    }

    public short[][] multiplyMatrix(short[][] M1, short[][] M2) throws RuntimeException {
        if (M1[0].length != M2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        this.f7184A = (short[][]) Array.newInstance(Short.TYPE, new int[]{M1.length, M2[0].length});
        for (int i = 0; i < M1.length; i++) {
            for (int j = 0; j < M2.length; j++) {
                for (int k = 0; k < M2[0].length; k++) {
                    this.f7184A[i][k] = GF2Field.addElem(this.f7184A[i][k], GF2Field.multElem(M1[i][j], M2[j][k]));
                }
            }
        }
        return this.f7184A;
    }

    public short[] multiplyMatrix(short[][] M1, short[] m) throws RuntimeException {
        if (M1[0].length != m.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short[] B = new short[M1.length];
        for (int i = 0; i < M1.length; i++) {
            for (int j = 0; j < m.length; j++) {
                B[i] = GF2Field.addElem(B[i], GF2Field.multElem(M1[i][j], m[j]));
            }
        }
        return B;
    }

    public short[] addVect(short[] vector1, short[] vector2) {
        if (vector1.length != vector2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short[] rslt = new short[vector1.length];
        for (int n = 0; n < rslt.length; n++) {
            rslt[n] = GF2Field.addElem(vector1[n], vector2[n]);
        }
        return rslt;
    }

    public short[][] multVects(short[] vector1, short[] vector2) {
        if (vector1.length != vector2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short[][] rslt = (short[][]) Array.newInstance(Short.TYPE, new int[]{vector1.length, vector2.length});
        for (int i = 0; i < vector1.length; i++) {
            for (int j = 0; j < vector2.length; j++) {
                rslt[i][j] = GF2Field.multElem(vector1[i], vector2[j]);
            }
        }
        return rslt;
    }

    public short[] multVect(short scalar, short[] vector) {
        short[] rslt = new short[vector.length];
        for (int n = 0; n < rslt.length; n++) {
            rslt[n] = GF2Field.multElem(scalar, vector[n]);
        }
        return rslt;
    }

    public short[][] multMatrix(short scalar, short[][] matrix) {
        short[][] rslt = (short[][]) Array.newInstance(Short.TYPE, new int[]{matrix.length, matrix[0].length});
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                rslt[i][j] = GF2Field.multElem(scalar, matrix[i][j]);
            }
        }
        return rslt;
    }

    public short[][] addSquareMatrix(short[][] matrix1, short[][] matrix2) {
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
            short[][] rslt = (short[][]) Array.newInstance(Short.TYPE, new int[]{matrix1.length, matrix1.length});
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix2.length; j++) {
                    rslt[i][j] = GF2Field.addElem(matrix1[i][j], matrix2[i][j]);
                }
            }
            return rslt;
        }
        throw new RuntimeException("Addition is not possible!");
    }
}
