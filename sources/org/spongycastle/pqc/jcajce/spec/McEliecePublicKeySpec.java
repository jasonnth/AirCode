package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.KeySpec;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

public class McEliecePublicKeySpec implements KeySpec {

    /* renamed from: g */
    private GF2Matrix f7218g;

    /* renamed from: n */
    private int f7219n;
    private String oid;

    /* renamed from: t */
    private int f7220t;

    public McEliecePublicKeySpec(String oid2, int n, int t, GF2Matrix g) {
        this.oid = oid2;
        this.f7219n = n;
        this.f7220t = t;
        this.f7218g = new GF2Matrix(g);
    }

    public McEliecePublicKeySpec(String oid2, int t, int n, byte[] encG) {
        this.oid = oid2;
        this.f7219n = n;
        this.f7220t = t;
        this.f7218g = new GF2Matrix(encG);
    }

    public int getN() {
        return this.f7219n;
    }

    public int getT() {
        return this.f7220t;
    }

    public GF2Matrix getG() {
        return this.f7218g;
    }

    public String getOIDString() {
        return this.oid;
    }
}
