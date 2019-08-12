package org.spongycastle.math.p332ec.custom.djb;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECCurve.AbstractFp;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.raw.Nat256;
import org.spongycastle.util.encoders.Hex;

/* renamed from: org.spongycastle.math.ec.custom.djb.Curve25519 */
public class Curve25519 extends AbstractFp {
    private static final int Curve25519_DEFAULT_COORDS = 4;

    /* renamed from: q */
    public static final BigInteger f6978q = Nat256.toBigInteger(Curve25519Field.f6980P);
    protected Curve25519Point infinity;

    public Curve25519() {
        super(f6978q);
        this.infinity = new Curve25519Point(this, null, null);
        this.f6958a = fromBigInteger(new BigInteger(1, Hex.decode("2AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA984914A144")));
        this.f6959b = fromBigInteger(new BigInteger(1, Hex.decode("7B425ED097B425ED097B425ED097B425ED097B425ED097B4260B5E9C7710C864")));
        this.order = new BigInteger(1, Hex.decode("1000000000000000000000000000000014DEF9DEA2F79CD65812631A5CF5D3ED"));
        this.cofactor = BigInteger.valueOf(8);
        this.coord = 4;
    }

    /* access modifiers changed from: protected */
    public ECCurve cloneCurve() {
        return new Curve25519();
    }

    public boolean supportsCoordinateSystem(int coord) {
        switch (coord) {
            case 4:
                return true;
            default:
                return false;
        }
    }

    public BigInteger getQ() {
        return f6978q;
    }

    public int getFieldSize() {
        return f6978q.bitLength();
    }

    public ECFieldElement fromBigInteger(BigInteger x) {
        return new Curve25519FieldElement(x);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, boolean withCompression) {
        return new Curve25519Point(this, x, y, withCompression);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        return new Curve25519Point(this, x, y, zs, withCompression);
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }
}
