package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

public class McElieceCCA2PublicKeyParameters extends McElieceCCA2KeyParameters {
    private GF2Matrix matrixG;

    /* renamed from: n */
    private int f7099n;
    private String oid;

    /* renamed from: t */
    private int f7100t;

    public McElieceCCA2PublicKeyParameters(String oid2, int n, int t, GF2Matrix matrix, McElieceCCA2Parameters params) {
        super(false, params);
        this.oid = oid2;
        this.f7099n = n;
        this.f7100t = t;
        this.matrixG = new GF2Matrix(matrix);
    }

    public McElieceCCA2PublicKeyParameters(String oid2, int n, int t, byte[] encMatrix, McElieceCCA2Parameters params) {
        super(false, params);
        this.oid = oid2;
        this.f7099n = n;
        this.f7100t = t;
        this.matrixG = new GF2Matrix(encMatrix);
    }

    public int getN() {
        return this.f7099n;
    }

    public int getT() {
        return this.f7100t;
    }

    public GF2Matrix getMatrixG() {
        return this.matrixG;
    }

    public int getK() {
        return this.matrixG.getNumRows();
    }

    public String getOIDString() {
        return this.oid;
    }
}
