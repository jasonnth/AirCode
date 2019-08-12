package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.KeySpec;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public class McElieceCCA2PrivateKeySpec implements KeySpec {
    private GF2mField field;
    private PolynomialGF2mSmallM goppaPoly;

    /* renamed from: h */
    private GF2Matrix f7207h;

    /* renamed from: k */
    private int f7208k;

    /* renamed from: n */
    private int f7209n;
    private String oid;

    /* renamed from: p */
    private Permutation f7210p;
    private PolynomialGF2mSmallM[] qInv;

    public McElieceCCA2PrivateKeySpec(String oid2, int n, int k, GF2mField field2, PolynomialGF2mSmallM gp, Permutation p, GF2Matrix h, PolynomialGF2mSmallM[] qInv2) {
        this.oid = oid2;
        this.f7209n = n;
        this.f7208k = k;
        this.field = field2;
        this.goppaPoly = gp;
        this.f7210p = p;
        this.f7207h = h;
        this.qInv = qInv2;
    }

    public McElieceCCA2PrivateKeySpec(String oid2, int n, int k, byte[] encFieldPoly, byte[] encGoppaPoly, byte[] encP, byte[] encH, byte[][] encQInv) {
        this.oid = oid2;
        this.f7209n = n;
        this.f7208k = k;
        this.field = new GF2mField(encFieldPoly);
        this.goppaPoly = new PolynomialGF2mSmallM(this.field, encGoppaPoly);
        this.f7210p = new Permutation(encP);
        this.f7207h = new GF2Matrix(encH);
        this.qInv = new PolynomialGF2mSmallM[encQInv.length];
        for (int i = 0; i < encQInv.length; i++) {
            this.qInv[i] = new PolynomialGF2mSmallM(this.field, encQInv[i]);
        }
    }

    public int getN() {
        return this.f7209n;
    }

    public int getK() {
        return this.f7208k;
    }

    public GF2mField getField() {
        return this.field;
    }

    public PolynomialGF2mSmallM getGoppaPoly() {
        return this.goppaPoly;
    }

    public Permutation getP() {
        return this.f7210p;
    }

    public GF2Matrix getH() {
        return this.f7207h;
    }

    public PolynomialGF2mSmallM[] getQInv() {
        return this.qInv;
    }

    public String getOIDString() {
        return this.oid;
    }
}
