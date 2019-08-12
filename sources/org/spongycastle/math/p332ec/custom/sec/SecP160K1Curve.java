package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECConstants;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECCurve.AbstractFp;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.util.encoders.Hex;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP160K1Curve */
public class SecP160K1Curve extends AbstractFp {
    private static final int SECP160K1_DEFAULT_COORDS = 2;

    /* renamed from: q */
    public static final BigInteger f6990q = SecP160R2Curve.f6997q;
    protected SecP160K1Point infinity;

    public SecP160K1Curve() {
        super(f6990q);
        this.infinity = new SecP160K1Point(this, null, null);
        this.f6958a = fromBigInteger(ECConstants.ZERO);
        this.f6959b = fromBigInteger(BigInteger.valueOf(7));
        this.order = new BigInteger(1, Hex.decode("0100000000000000000001B8FA16DFAB9ACA16B6B3"));
        this.cofactor = BigInteger.valueOf(1);
        this.coord = 2;
    }

    /* access modifiers changed from: protected */
    public ECCurve cloneCurve() {
        return new SecP160K1Curve();
    }

    public boolean supportsCoordinateSystem(int coord) {
        switch (coord) {
            case 2:
                return true;
            default:
                return false;
        }
    }

    public BigInteger getQ() {
        return f6990q;
    }

    public int getFieldSize() {
        return f6990q.bitLength();
    }

    public ECFieldElement fromBigInteger(BigInteger x) {
        return new SecP160R2FieldElement(x);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, boolean withCompression) {
        return new SecP160K1Point(this, x, y, withCompression);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        return new SecP160K1Point(this, x, y, zs, withCompression);
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }
}
