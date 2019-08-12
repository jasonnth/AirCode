package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.KeySpec;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

public class McElieceCCA2PublicKeySpec implements KeySpec {
    private GF2Matrix matrixG;

    /* renamed from: n */
    private int f7211n;
    private String oid;

    /* renamed from: t */
    private int f7212t;

    public McElieceCCA2PublicKeySpec(String oid2, int n, int t, GF2Matrix matrix) {
        this.oid = oid2;
        this.f7211n = n;
        this.f7212t = t;
        this.matrixG = new GF2Matrix(matrix);
    }

    public McElieceCCA2PublicKeySpec(String oid2, int n, int t, byte[] encMatrix) {
        this.oid = oid2;
        this.f7211n = n;
        this.f7212t = t;
        this.matrixG = new GF2Matrix(encMatrix);
    }

    public int getN() {
        return this.f7211n;
    }

    public int getT() {
        return this.f7212t;
    }

    public GF2Matrix getMatrixG() {
        return this.matrixG;
    }

    public String getOIDString() {
        return this.oid;
    }
}
