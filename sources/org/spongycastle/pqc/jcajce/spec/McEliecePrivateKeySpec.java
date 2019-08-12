package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.KeySpec;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public class McEliecePrivateKeySpec implements KeySpec {
    private GF2mField field;
    private PolynomialGF2mSmallM goppaPoly;

    /* renamed from: h */
    private GF2Matrix f7213h;

    /* renamed from: k */
    private int f7214k;

    /* renamed from: n */
    private int f7215n;
    private String oid;

    /* renamed from: p1 */
    private Permutation f7216p1;

    /* renamed from: p2 */
    private Permutation f7217p2;
    private PolynomialGF2mSmallM[] qInv;
    private GF2Matrix sInv;

    public McEliecePrivateKeySpec(String oid2, int n, int k, GF2mField field2, PolynomialGF2mSmallM goppaPoly2, GF2Matrix sInv2, Permutation p1, Permutation p2, GF2Matrix h, PolynomialGF2mSmallM[] qInv2) {
        this.oid = oid2;
        this.f7214k = k;
        this.f7215n = n;
        this.field = field2;
        this.goppaPoly = goppaPoly2;
        this.sInv = sInv2;
        this.f7216p1 = p1;
        this.f7217p2 = p2;
        this.f7213h = h;
        this.qInv = qInv2;
    }

    public McEliecePrivateKeySpec(String oid2, int n, int k, byte[] encField, byte[] encGoppaPoly, byte[] encSInv, byte[] encP1, byte[] encP2, byte[] encH, byte[][] encQInv) {
        this.oid = oid2;
        this.f7215n = n;
        this.f7214k = k;
        this.field = new GF2mField(encField);
        this.goppaPoly = new PolynomialGF2mSmallM(this.field, encGoppaPoly);
        this.sInv = new GF2Matrix(encSInv);
        this.f7216p1 = new Permutation(encP1);
        this.f7217p2 = new Permutation(encP2);
        this.f7213h = new GF2Matrix(encH);
        this.qInv = new PolynomialGF2mSmallM[encQInv.length];
        for (int i = 0; i < encQInv.length; i++) {
            this.qInv[i] = new PolynomialGF2mSmallM(this.field, encQInv[i]);
        }
    }

    public int getN() {
        return this.f7215n;
    }

    public int getK() {
        return this.f7214k;
    }

    public GF2mField getField() {
        return this.field;
    }

    public PolynomialGF2mSmallM getGoppaPoly() {
        return this.goppaPoly;
    }

    public GF2Matrix getSInv() {
        return this.sInv;
    }

    public Permutation getP1() {
        return this.f7216p1;
    }

    public Permutation getP2() {
        return this.f7217p2;
    }

    public GF2Matrix getH() {
        return this.f7213h;
    }

    public PolynomialGF2mSmallM[] getQInv() {
        return this.qInv;
    }

    public String getOIDString() {
        return this.oid;
    }
}
