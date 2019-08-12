package org.spongycastle.math.p332ec.custom.sec;

import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.p332ec.ECPoint.AbstractFp;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat384;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP384R1Point */
public class SecP384R1Point extends AbstractFp {
    public SecP384R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y) {
        this(curve, x, y, false);
    }

    public SecP384R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
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

    SecP384R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        super(curve, x, y, zs);
        this.withCompression = withCompression;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecP384R1Point(null, getAffineXCoord(), getAffineYCoord());
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
        SecP384R1FieldElement X1 = (SecP384R1FieldElement) this.f6973x;
        SecP384R1FieldElement Y1 = (SecP384R1FieldElement) this.f6974y;
        SecP384R1FieldElement X2 = (SecP384R1FieldElement) b.getXCoord();
        SecP384R1FieldElement Y2 = (SecP384R1FieldElement) b.getYCoord();
        SecP384R1FieldElement Z1 = (SecP384R1FieldElement) this.f6975zs[0];
        SecP384R1FieldElement Z2 = (SecP384R1FieldElement) b.getZCoord(0);
        int[] tt1 = Nat.create(24);
        int[] tt2 = Nat.create(24);
        int[] t3 = Nat.create(12);
        int[] t4 = Nat.create(12);
        boolean Z1IsOne = Z1.isOne();
        if (Z1IsOne) {
            U2 = X2.f7039x;
            S2 = Y2.f7039x;
        } else {
            S2 = t3;
            SecP384R1Field.square(Z1.f7039x, S2);
            U2 = tt2;
            SecP384R1Field.multiply(S2, X2.f7039x, U2);
            SecP384R1Field.multiply(S2, Z1.f7039x, S2);
            SecP384R1Field.multiply(S2, Y2.f7039x, S2);
        }
        boolean Z2IsOne = Z2.isOne();
        if (Z2IsOne) {
            U1 = X1.f7039x;
            S1 = Y1.f7039x;
        } else {
            S1 = t4;
            SecP384R1Field.square(Z2.f7039x, S1);
            U1 = tt1;
            SecP384R1Field.multiply(S1, X1.f7039x, U1);
            SecP384R1Field.multiply(S1, Z2.f7039x, S1);
            SecP384R1Field.multiply(S1, Y1.f7039x, S1);
        }
        int[] H = Nat.create(12);
        SecP384R1Field.subtract(U1, U2, H);
        int[] R = Nat.create(12);
        SecP384R1Field.subtract(S1, S2, R);
        if (!Nat.isZero(12, H)) {
            int[] HSquared = t3;
            SecP384R1Field.square(H, HSquared);
            int[] G = Nat.create(12);
            SecP384R1Field.multiply(HSquared, H, G);
            int[] V = t3;
            SecP384R1Field.multiply(HSquared, U1, V);
            SecP384R1Field.negate(G, G);
            Nat384.mul(S1, G, tt1);
            SecP384R1Field.reduce32(Nat.addBothTo(12, V, V, G), G);
            SecP384R1FieldElement X3 = new SecP384R1FieldElement(t4);
            SecP384R1Field.square(R, X3.f7039x);
            SecP384R1Field.subtract(X3.f7039x, G, X3.f7039x);
            SecP384R1FieldElement Y3 = new SecP384R1FieldElement(G);
            SecP384R1Field.subtract(V, X3.f7039x, Y3.f7039x);
            Nat384.mul(Y3.f7039x, R, tt2);
            SecP384R1Field.addExt(tt1, tt2, tt1);
            SecP384R1Field.reduce(tt1, Y3.f7039x);
            SecP384R1FieldElement secP384R1FieldElement = new SecP384R1FieldElement(H);
            if (!Z1IsOne) {
                SecP384R1Field.multiply(secP384R1FieldElement.f7039x, Z1.f7039x, secP384R1FieldElement.f7039x);
            }
            if (!Z2IsOne) {
                SecP384R1Field.multiply(secP384R1FieldElement.f7039x, Z2.f7039x, secP384R1FieldElement.f7039x);
            }
            return new SecP384R1Point(curve, X3, Y3, new ECFieldElement[]{secP384R1FieldElement}, this.withCompression);
        } else if (Nat.isZero(12, R)) {
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
        SecP384R1FieldElement Y1 = (SecP384R1FieldElement) this.f6974y;
        if (Y1.isZero()) {
            return curve.getInfinity();
        }
        SecP384R1FieldElement X1 = (SecP384R1FieldElement) this.f6973x;
        SecP384R1FieldElement Z1 = (SecP384R1FieldElement) this.f6975zs[0];
        int[] t1 = Nat.create(12);
        int[] t2 = Nat.create(12);
        int[] Y1Squared = Nat.create(12);
        SecP384R1Field.square(Y1.f7039x, Y1Squared);
        int[] T = Nat.create(12);
        SecP384R1Field.square(Y1Squared, T);
        boolean Z1IsOne = Z1.isOne();
        int[] Z1Squared = Z1.f7039x;
        if (!Z1IsOne) {
            Z1Squared = t2;
            SecP384R1Field.square(Z1.f7039x, Z1Squared);
        }
        SecP384R1Field.subtract(X1.f7039x, Z1Squared, t1);
        int[] M = t2;
        SecP384R1Field.add(X1.f7039x, Z1Squared, M);
        SecP384R1Field.multiply(M, t1, M);
        SecP384R1Field.reduce32(Nat.addBothTo(12, M, M, M), M);
        int[] S = Y1Squared;
        SecP384R1Field.multiply(Y1Squared, X1.f7039x, S);
        SecP384R1Field.reduce32(Nat.shiftUpBits(12, S, 2, 0), S);
        SecP384R1Field.reduce32(Nat.shiftUpBits(12, T, 3, 0, t1), t1);
        SecP384R1FieldElement X3 = new SecP384R1FieldElement(T);
        SecP384R1Field.square(M, X3.f7039x);
        SecP384R1Field.subtract(X3.f7039x, S, X3.f7039x);
        SecP384R1Field.subtract(X3.f7039x, S, X3.f7039x);
        SecP384R1FieldElement Y3 = new SecP384R1FieldElement(S);
        SecP384R1Field.subtract(S, X3.f7039x, Y3.f7039x);
        SecP384R1Field.multiply(Y3.f7039x, M, Y3.f7039x);
        SecP384R1Field.subtract(Y3.f7039x, t1, Y3.f7039x);
        SecP384R1FieldElement secP384R1FieldElement = new SecP384R1FieldElement(M);
        SecP384R1Field.twice(Y1.f7039x, secP384R1FieldElement.f7039x);
        if (!Z1IsOne) {
            SecP384R1Field.multiply(secP384R1FieldElement.f7039x, Z1.f7039x, secP384R1FieldElement.f7039x);
        }
        return new SecP384R1Point(curve, X3, Y3, new ECFieldElement[]{secP384R1FieldElement}, this.withCompression);
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
        return isInfinity() ? this : new SecP384R1Point(this.curve, this.f6973x, this.f6974y.negate(), this.f6975zs, this.withCompression);
    }
}
