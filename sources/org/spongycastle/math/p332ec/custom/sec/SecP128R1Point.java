package org.spongycastle.math.p332ec.custom.sec;

import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.p332ec.ECPoint.AbstractFp;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat128;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP128R1Point */
public class SecP128R1Point extends AbstractFp {
    public SecP128R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y) {
        this(curve, x, y, false);
    }

    public SecP128R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
        boolean z;
        boolean z2 = true;
        super(curve, x, y);
        if (x == null) {
            z = true;
        } else {
            z = false;
        }
        if (y != null) {
            z2 = false;
        }
        if (z != z2) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = withCompression;
    }

    SecP128R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        super(curve, x, y, zs);
        this.withCompression = withCompression;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecP128R1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    public ECPoint add(ECPoint b) {
        int[] S2;
        int[] U2;
        int[] S1;
        int[] U1;
        if (isInfinity()) {
            return b;
        }
        if (b.isInfinity()) {
            return this;
        }
        if (this == b) {
            return twice();
        }
        ECCurve curve = getCurve();
        SecP128R1FieldElement X1 = (SecP128R1FieldElement) this.f6973x;
        SecP128R1FieldElement Y1 = (SecP128R1FieldElement) this.f6974y;
        SecP128R1FieldElement X2 = (SecP128R1FieldElement) b.getXCoord();
        SecP128R1FieldElement Y2 = (SecP128R1FieldElement) b.getYCoord();
        SecP128R1FieldElement Z1 = (SecP128R1FieldElement) this.f6975zs[0];
        SecP128R1FieldElement Z2 = (SecP128R1FieldElement) b.getZCoord(0);
        int[] tt1 = Nat128.createExt();
        int[] t2 = Nat128.create();
        int[] t3 = Nat128.create();
        int[] t4 = Nat128.create();
        boolean Z1IsOne = Z1.isOne();
        if (Z1IsOne) {
            U2 = X2.f6989x;
            S2 = Y2.f6989x;
        } else {
            S2 = t3;
            SecP128R1Field.square(Z1.f6989x, S2);
            U2 = t2;
            SecP128R1Field.multiply(S2, X2.f6989x, U2);
            SecP128R1Field.multiply(S2, Z1.f6989x, S2);
            SecP128R1Field.multiply(S2, Y2.f6989x, S2);
        }
        boolean Z2IsOne = Z2.isOne();
        if (Z2IsOne) {
            U1 = X1.f6989x;
            S1 = Y1.f6989x;
        } else {
            S1 = t4;
            SecP128R1Field.square(Z2.f6989x, S1);
            U1 = tt1;
            SecP128R1Field.multiply(S1, X1.f6989x, U1);
            SecP128R1Field.multiply(S1, Z2.f6989x, S1);
            SecP128R1Field.multiply(S1, Y1.f6989x, S1);
        }
        int[] H = Nat128.create();
        SecP128R1Field.subtract(U1, U2, H);
        int[] R = t2;
        SecP128R1Field.subtract(S1, S2, R);
        if (!Nat128.isZero(H)) {
            int[] HSquared = t3;
            SecP128R1Field.square(H, HSquared);
            int[] G = Nat128.create();
            SecP128R1Field.multiply(HSquared, H, G);
            int[] V = t3;
            SecP128R1Field.multiply(HSquared, U1, V);
            SecP128R1Field.negate(G, G);
            Nat128.mul(S1, G, tt1);
            SecP128R1Field.reduce32(Nat128.addBothTo(V, V, G), G);
            SecP128R1FieldElement X3 = new SecP128R1FieldElement(t4);
            SecP128R1Field.square(R, X3.f6989x);
            SecP128R1Field.subtract(X3.f6989x, G, X3.f6989x);
            SecP128R1FieldElement Y3 = new SecP128R1FieldElement(G);
            SecP128R1Field.subtract(V, X3.f6989x, Y3.f6989x);
            SecP128R1Field.multiplyAddToExt(Y3.f6989x, R, tt1);
            SecP128R1Field.reduce(tt1, Y3.f6989x);
            SecP128R1FieldElement secP128R1FieldElement = new SecP128R1FieldElement(H);
            if (!Z1IsOne) {
                SecP128R1Field.multiply(secP128R1FieldElement.f6989x, Z1.f6989x, secP128R1FieldElement.f6989x);
            }
            if (!Z2IsOne) {
                SecP128R1Field.multiply(secP128R1FieldElement.f6989x, Z2.f6989x, secP128R1FieldElement.f6989x);
            }
            return new SecP128R1Point(curve, X3, Y3, new ECFieldElement[]{secP128R1FieldElement}, this.withCompression);
        } else if (Nat128.isZero(R)) {
            return twice();
        } else {
            return curve.getInfinity();
        }
    }

    /* Debug info: failed to restart local var, previous not found, register: 20 */
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SecP128R1FieldElement Y1 = (SecP128R1FieldElement) this.f6974y;
        if (Y1.isZero()) {
            return curve.getInfinity();
        }
        SecP128R1FieldElement X1 = (SecP128R1FieldElement) this.f6973x;
        SecP128R1FieldElement Z1 = (SecP128R1FieldElement) this.f6975zs[0];
        int[] t1 = Nat128.create();
        int[] t2 = Nat128.create();
        int[] Y1Squared = Nat128.create();
        SecP128R1Field.square(Y1.f6989x, Y1Squared);
        int[] T = Nat128.create();
        SecP128R1Field.square(Y1Squared, T);
        boolean Z1IsOne = Z1.isOne();
        int[] Z1Squared = Z1.f6989x;
        if (!Z1IsOne) {
            Z1Squared = t2;
            SecP128R1Field.square(Z1.f6989x, Z1Squared);
        }
        SecP128R1Field.subtract(X1.f6989x, Z1Squared, t1);
        int[] M = t2;
        SecP128R1Field.add(X1.f6989x, Z1Squared, M);
        SecP128R1Field.multiply(M, t1, M);
        SecP128R1Field.reduce32(Nat128.addBothTo(M, M, M), M);
        int[] S = Y1Squared;
        SecP128R1Field.multiply(Y1Squared, X1.f6989x, S);
        SecP128R1Field.reduce32(Nat.shiftUpBits(4, S, 2, 0), S);
        SecP128R1Field.reduce32(Nat.shiftUpBits(4, T, 3, 0, t1), t1);
        SecP128R1FieldElement X3 = new SecP128R1FieldElement(T);
        SecP128R1Field.square(M, X3.f6989x);
        SecP128R1Field.subtract(X3.f6989x, S, X3.f6989x);
        SecP128R1Field.subtract(X3.f6989x, S, X3.f6989x);
        SecP128R1FieldElement Y3 = new SecP128R1FieldElement(S);
        SecP128R1Field.subtract(S, X3.f6989x, Y3.f6989x);
        SecP128R1Field.multiply(Y3.f6989x, M, Y3.f6989x);
        SecP128R1Field.subtract(Y3.f6989x, t1, Y3.f6989x);
        SecP128R1FieldElement secP128R1FieldElement = new SecP128R1FieldElement(M);
        SecP128R1Field.twice(Y1.f6989x, secP128R1FieldElement.f6989x);
        if (!Z1IsOne) {
            SecP128R1Field.multiply(secP128R1FieldElement.f6989x, Z1.f6989x, secP128R1FieldElement.f6989x);
        }
        return new SecP128R1Point(curve, X3, Y3, new ECFieldElement[]{secP128R1FieldElement}, this.withCompression);
    }

    public ECPoint twicePlus(ECPoint b) {
        if (this == b) {
            return threeTimes();
        }
        if (isInfinity()) {
            return b;
        }
        if (b.isInfinity()) {
            return twice();
        }
        return !this.f6974y.isZero() ? twice().add(b) : b;
    }

    /* Debug info: failed to restart local var, previous not found, register: 1 */
    public ECPoint threeTimes() {
        return (isInfinity() || this.f6974y.isZero()) ? this : twice().add(this);
    }

    /* Debug info: failed to restart local var, previous not found, register: 6 */
    public ECPoint negate() {
        return isInfinity() ? this : new SecP128R1Point(this.curve, this.f6973x, this.f6974y.negate(), this.f6975zs, this.withCompression);
    }
}
