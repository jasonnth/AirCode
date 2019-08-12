package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.jmrtd.cbeff.ISO781611;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECCurve.AbstractF2m;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.util.encoders.Hex;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecT131R2Curve */
public class SecT131R2Curve extends AbstractF2m {
    private static final int SecT131R2_DEFAULT_COORDS = 6;
    protected SecT131R2Point infinity;

    public SecT131R2Curve() {
        super(ISO781611.CREATION_DATE_AND_TIME_TAG, 2, 3, 8);
        this.infinity = new SecT131R2Point(this, null, null);
        this.f6958a = fromBigInteger(new BigInteger(1, Hex.decode("03E5A88919D7CAFCBF415F07C2176573B2")));
        this.f6959b = fromBigInteger(new BigInteger(1, Hex.decode("04B8266A46C55657AC734CE38F018F2192")));
        this.order = new BigInteger(1, Hex.decode("0400000000000000016954A233049BA98F"));
        this.cofactor = BigInteger.valueOf(2);
        this.coord = 6;
    }

    /* access modifiers changed from: protected */
    public ECCurve cloneCurve() {
        return new SecT131R2Curve();
    }

    public boolean supportsCoordinateSystem(int coord) {
        switch (coord) {
            case 6:
                return true;
            default:
                return false;
        }
    }

    public int getFieldSize() {
        return ISO781611.CREATION_DATE_AND_TIME_TAG;
    }

    public ECFieldElement fromBigInteger(BigInteger x) {
        return new SecT131FieldElement(x);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, boolean withCompression) {
        return new SecT131R2Point(this, x, y, withCompression);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        return new SecT131R2Point(this, x, y, zs, withCompression);
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }

    public boolean isKoblitz() {
        return false;
    }

    public int getM() {
        return ISO781611.CREATION_DATE_AND_TIME_TAG;
    }

    public boolean isTrinomial() {
        return false;
    }

    public int getK1() {
        return 2;
    }

    public int getK2() {
        return 3;
    }

    public int getK3() {
        return 8;
    }
}
