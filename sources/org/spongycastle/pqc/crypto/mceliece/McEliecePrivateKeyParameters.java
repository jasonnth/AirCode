package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public class McEliecePrivateKeyParameters extends McElieceKeyParameters {
    private GF2mField field;
    private PolynomialGF2mSmallM goppaPoly;

    /* renamed from: h */
    private GF2Matrix f7123h;

    /* renamed from: k */
    private int f7124k;

    /* renamed from: n */
    private int f7125n;
    private String oid;

    /* renamed from: p1 */
    private Permutation f7126p1;

    /* renamed from: p2 */
    private Permutation f7127p2;
    private PolynomialGF2mSmallM[] qInv;
    private GF2Matrix sInv;

    public McEliecePrivateKeyParameters(String oid2, int n, int k, GF2mField field2, PolynomialGF2mSmallM goppaPoly2, GF2Matrix sInv2, Permutation p1, Permutation p2, GF2Matrix h, PolynomialGF2mSmallM[] qInv2, McElieceParameters params) {
        super(true, params);
        this.oid = oid2;
        this.f7124k = k;
        this.f7125n = n;
        this.field = field2;
        this.goppaPoly = goppaPoly2;
        this.sInv = sInv2;
        this.f7126p1 = p1;
        this.f7127p2 = p2;
        this.f7123h = h;
        this.qInv = qInv2;
    }

    public McEliecePrivateKeyParameters(String oid2, int n, int k, byte[] encField, byte[] encGoppaPoly, byte[] encSInv, byte[] encP1, byte[] encP2, byte[] encH, byte[][] encQInv, McElieceParameters params) {
        super(true, params);
        this.oid = oid2;
        this.f7125n = n;
        this.f7124k = k;
        this.field = new GF2mField(encField);
        this.goppaPoly = new PolynomialGF2mSmallM(this.field, encGoppaPoly);
        this.sInv = new GF2Matrix(encSInv);
        this.f7126p1 = new Permutation(encP1);
        this.f7127p2 = new Permutation(encP2);
        this.f7123h = new GF2Matrix(encH);
        this.qInv = new PolynomialGF2mSmallM[encQInv.length];
        for (int i = 0; i < encQInv.length; i++) {
            this.qInv[i] = new PolynomialGF2mSmallM(this.field, encQInv[i]);
        }
    }

    public int getN() {
        return this.f7125n;
    }

    public int getK() {
        return this.f7124k;
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
        return this.f7126p1;
    }

    public Permutation getP2() {
        return this.f7127p2;
    }

    public GF2Matrix getH() {
        return this.f7123h;
    }

    public PolynomialGF2mSmallM[] getQInv() {
        return this.qInv;
    }

    public String getOIDString() {
        return this.oid;
    }
}
