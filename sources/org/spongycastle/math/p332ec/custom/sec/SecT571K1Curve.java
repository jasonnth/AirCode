package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECCurve.AbstractF2m;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECMultiplier;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.p332ec.WTauNafMultiplier;
import org.spongycastle.util.encoders.Hex;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecT571K1Curve */
public class SecT571K1Curve extends AbstractF2m {
    private static final int SecT571K1_DEFAULT_COORDS = 6;
    protected SecT571K1Point infinity;

    public SecT571K1Curve() {
        super(571, 2, 5, 10);
        this.infinity = new SecT571K1Point(this, null, null);
        this.f6958a = fromBigInteger(BigInteger.valueOf(0));
        this.f6959b = fromBigInteger(BigInteger.valueOf(1));
        this.order = new BigInteger(1, Hex.decode("020000000000000000000000000000000000000000000000000000000000000000000000131850E1F19A63E4B391A8DB917F4138B630D84BE5D639381E91DEB45CFE778F637C1001"));
        this.cofactor = BigInteger.valueOf(4);
        this.coord = 6;
    }

    /* access modifiers changed from: protected */
    public ECCurve cloneCurve() {
        return new SecT571K1Curve();
    }

    public boolean supportsCoordinateSystem(int coord) {
        switch (coord) {
            case 6:
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    public ECMultiplier createDefaultMultiplier() {
        return new WTauNafMultiplier();
    }

    public int getFieldSize() {
        return 571;
    }

    public ECFieldElement fromBigInteger(BigInteger x) {
        return new SecT571FieldElement(x);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, boolean withCompression) {
        return new SecT571K1Point(this, x, y, withCompression);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        return new SecT571K1Point(this, x, y, zs, withCompression);
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }

    public boolean isKoblitz() {
        return true;
    }

    public int getM() {
        return 571;
    }

    public boolean isTrinomial() {
        return false;
    }

    public int getK1() {
        return 2;
    }

    public int getK2() {
        return 5;
    }

    public int getK3() {
        return 10;
    }
}
