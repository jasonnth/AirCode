package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public class McElieceCCA2PrivateKeyParameters extends McElieceCCA2KeyParameters {
    private GF2mField field;
    private PolynomialGF2mSmallM goppaPoly;

    /* renamed from: h */
    private GF2Matrix f7095h;

    /* renamed from: k */
    private int f7096k;

    /* renamed from: n */
    private int f7097n;
    private String oid;

    /* renamed from: p */
    private Permutation f7098p;
    private PolynomialGF2mSmallM[] qInv;

    public McElieceCCA2PrivateKeyParameters(String oid2, int n, int k, GF2mField field2, PolynomialGF2mSmallM gp, Permutation p, GF2Matrix h, PolynomialGF2mSmallM[] qInv2, McElieceCCA2Parameters params) {
        super(true, params);
        this.oid = oid2;
        this.f7097n = n;
        this.f7096k = k;
        this.field = field2;
        this.goppaPoly = gp;
        this.f7098p = p;
        this.f7095h = h;
        this.qInv = qInv2;
    }

    public McElieceCCA2PrivateKeyParameters(String oid2, int n, int k, byte[] encFieldPoly, byte[] encGoppaPoly, byte[] encP, byte[] encH, byte[][] encQInv, McElieceCCA2Parameters params) {
        super(true, params);
        this.oid = oid2;
        this.f7097n = n;
        this.f7096k = k;
        this.field = new GF2mField(encFieldPoly);
        this.goppaPoly = new PolynomialGF2mSmallM(this.field, encGoppaPoly);
        this.f7098p = new Permutation(encP);
        this.f7095h = new GF2Matrix(encH);
        this.qInv = new PolynomialGF2mSmallM[encQInv.length];
        for (int i = 0; i < encQInv.length; i++) {
            this.qInv[i] = new PolynomialGF2mSmallM(this.field, encQInv[i]);
        }
    }

    public int getN() {
        return this.f7097n;
    }

    public int getK() {
        return this.f7096k;
    }

    public int getT() {
        return this.goppaPoly.getDegree();
    }

    public GF2mField getField() {
        return this.field;
    }

    public PolynomialGF2mSmallM getGoppaPoly() {
        return this.goppaPoly;
    }

    public Permutation getP() {
        return this.f7098p;
    }

    public GF2Matrix getH() {
        return this.f7095h;
    }

    public PolynomialGF2mSmallM[] getQInv() {
        return this.qInv;
    }

    public String getOIDString() {
        return this.oid;
    }
}
