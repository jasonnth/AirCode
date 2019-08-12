package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

public class McEliecePublicKeyParameters extends McElieceKeyParameters {

    /* renamed from: g */
    private GF2Matrix f7128g;

    /* renamed from: n */
    private int f7129n;
    private String oid;

    /* renamed from: t */
    private int f7130t;

    public McEliecePublicKeyParameters(String oid2, int n, int t, GF2Matrix g, McElieceParameters params) {
        super(false, params);
        this.oid = oid2;
        this.f7129n = n;
        this.f7130t = t;
        this.f7128g = new GF2Matrix(g);
    }

    public McEliecePublicKeyParameters(String oid2, int t, int n, byte[] encG, McElieceParameters params) {
        super(false, params);
        this.oid = oid2;
        this.f7129n = n;
        this.f7130t = t;
        this.f7128g = new GF2Matrix(encG);
    }

    public int getN() {
        return this.f7129n;
    }

    public int getT() {
        return this.f7130t;
    }

    public GF2Matrix getG() {
        return this.f7128g;
    }

    public String getOIDString() {
        return this.oid;
    }

    public int getK() {
        return this.f7128g.getNumRows();
    }
}
