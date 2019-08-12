package org.spongycastle.math.p332ec.custom.sec;

import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.p332ec.ECPoint.AbstractFp;
import org.spongycastle.math.raw.Nat;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP521R1Point */
public class SecP521R1Point extends AbstractFp {
    public SecP521R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y) {
        this(curve, x, y, false);
    }

    public SecP521R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
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

    SecP521R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        super(curve, x, y, zs);
        this.withCompression = withCompression;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecP521R1Point(null, getAffineXCoord(), getAffineYCoord());
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
        SecP521R1FieldElement X1 = (SecP521R1FieldElement) this.f6973x;
        SecP521R1FieldElement Y1 = (SecP521R1FieldElement) this.f6974y;
        SecP521R1FieldElement X2 = (SecP521R1FieldElement) b.getXCoord();
        SecP521R1FieldElement Y2 = (SecP521R1FieldElement) b.getYCoord();
        SecP521R1FieldElement Z1 = (SecP521R1FieldElement) this.f6975zs[0];
        SecP521R1FieldElement Z2 = (SecP521R1FieldElement) b.getZCoord(0);
        int[] t1 = Nat.create(17);
        int[] t2 = Nat.create(17);
        int[] t3 = Nat.create(17);
        int[] t4 = Nat.create(17);
        boolean Z1IsOne = Z1.isOne();
        if (Z1IsOne) {
            U2 = X2.f7043x;
            S2 = Y2.f7043x;
        } else {
            S2 = t3;
            SecP521R1Field.square(Z1.f7043x, S2);
            U2 = t2;
            SecP521R1Field.multiply(S2, X2.f7043x, U2);
            SecP521R1Field.multiply(S2, Z1.f7043x, S2);
            SecP521R1Field.multiply(S2, Y2.f7043x, S2);
        }
        boolean Z2IsOne = Z2.isOne();
        if (Z2IsOne) {
            U1 = X1.f7043x;
            S1 = Y1.f7043x;
        } else {
            S1 = t4;
            SecP521R1Field.square(Z2.f7043x, S1);
            U1 = t1;
            SecP521R1Field.multiply(S1, X1.f7043x, U1);
            SecP521R1Field.multiply(S1, Z2.f7043x, S1);
            SecP521R1Field.multiply(S1, Y1.f7043x, S1);
        }
        int[] H = Nat.create(17);
        SecP521R1Field.subtract(U1, U2, H);
        int[] R = t2;
        SecP521R1Field.subtract(S1, S2, R);
        if (!Nat.isZero(17, H)) {
            int[] HSquared = t3;
            SecP521R1Field.square(H, HSquared);
            int[] G = Nat.create(17);
            SecP521R1Field.multiply(HSquared, H, G);
            int[] V = t3;
            SecP521R1Field.multiply(HSquared, U1, V);
            SecP521R1Field.multiply(S1, G, t1);
            SecP521R1FieldElement X3 = new SecP521R1FieldElement(t4);
            SecP521R1Field.square(R, X3.f7043x);
            SecP521R1Field.add(X3.f7043x, G, X3.f7043x);
            SecP521R1Field.subtract(X3.f7043x, V, X3.f7043x);
            SecP521R1Field.subtract(X3.f7043x, V, X3.f7043x);
            SecP521R1FieldElement Y3 = new SecP521R1FieldElement(G);
            SecP521R1Field.subtract(V, X3.f7043x, Y3.f7043x);
            SecP521R1Field.multiply(Y3.f7043x, R, t2);
            SecP521R1Field.subtract(t2, t1, Y3.f7043x);
            SecP521R1FieldElement secP521R1FieldElement = new SecP521R1FieldElement(H);
            if (!Z1IsOne) {
                SecP521R1Field.multiply(secP521R1FieldElement.f7043x, Z1.f7043x, secP521R1FieldElement.f7043x);
            }
            if (!Z2IsOne) {
                SecP521R1Field.multiply(secP521R1FieldElement.f7043x, Z2.f7043x, secP521R1FieldElement.f7043x);
            }
            return new SecP521R1Point(curve, X3, Y3, new ECFieldElement[]{secP521R1FieldElement}, this.withCompression);
        } else if (Nat.isZero(17, R)) {
            return twice();
        } else {
            return curve.getInfinity();
        }
    }

    /* Debug info: failed to restart local var, previous not found, register: 19 */
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SecP521R1FieldElement Y1 = (SecP521R1FieldElement) this.f6974y;
        if (Y1.isZero()) {
            return curve.getInfinity();
        }
        SecP521R1FieldElement X1 = (SecP521R1FieldElement) this.f6973x;
        SecP521R1FieldElement Z1 = (SecP521R1FieldElement) this.f6975zs[0];
        int[] t1 = Nat.create(17);
        int[] t2 = Nat.create(17);
        int[] Y1Squared = Nat.create(17);
        SecP521R1Field.square(Y1.f7043x, Y1Squared);
        int[] T = Nat.create(17);
        SecP521R1Field.square(Y1Squared, T);
        boolean Z1IsOne = Z1.isOne();
        int[] Z1Squared = Z1.f7043x;
        if (!Z1IsOne) {
            Z1Squared = t2;
            SecP521R1Field.square(Z1.f7043x, Z1Squared);
        }
        SecP521R1Field.subtract(X1.f7043x, Z1Squared, t1);
        int[] M = t2;
        SecP521R1Field.add(X1.f7043x, Z1Squared, M);
        SecP521R1Field.multiply(M, t1, M);
        Nat.addBothTo(17, M, M, M);
        SecP521R1Field.reduce23(M);
        int[] S = Y1Squared;
        SecP521R1Field.multiply(Y1Squared, X1.f7043x, S);
        Nat.shiftUpBits(17, S, 2, 0);
        SecP521R1Field.reduce23(S);
        Nat.shiftUpBits(17, T, 3, 0, t1);
        SecP521R1Field.reduce23(t1);
        SecP521R1FieldElement X3 = new SecP521R1FieldElement(T);
        SecP521R1Field.square(M, X3.f7043x);
        SecP521R1Field.subtract(X3.f7043x, S, X3.f7043x);
        SecP521R1Field.subtract(X3.f7043x, S, X3.f7043x);
        SecP521R1FieldElement Y3 = new SecP521R1FieldElement(S);
        SecP521R1Field.subtract(S, X3.f7043x, Y3.f7043x);
        SecP521R1Field.multiply(Y3.f7043x, M, Y3.f7043x);
        SecP521R1Field.subtract(Y3.f7043x, t1, Y3.f7043x);
        SecP521R1FieldElement secP521R1FieldElement = new SecP521R1FieldElement(M);
        SecP521R1Field.twice(Y1.f7043x, secP521R1FieldElement.f7043x);
        if (!Z1IsOne) {
            SecP521R1Field.multiply(secP521R1FieldElement.f7043x, Z1.f7043x, secP521R1FieldElement.f7043x);
        }
        return new SecP521R1Point(curve, X3, Y3, new ECFieldElement[]{secP521R1FieldElement}, this.withCompression);
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

    /* access modifiers changed from: protected */
    public ECFieldElement two(ECFieldElement x) {
        return x.add(x);
    }

    /* access modifiers changed from: protected */
    public ECFieldElement three(ECFieldElement x) {
        return two(x).add(x);
    }

    /* access modifiers changed from: protected */
    public ECFieldElement four(ECFieldElement x) {
        return two(two(x));
    }

    /* access modifiers changed from: protected */
    public ECFieldElement eight(ECFieldElement x) {
        return four(two(x));
    }

    /* access modifiers changed from: protected */
    public ECFieldElement doubleProductFromSquares(ECFieldElement a, ECFieldElement b, ECFieldElement aSquared, ECFieldElement bSquared) {
        return a.add(b).square().subtract(aSquared).subtract(bSquared);
    }

    /* Debug info: failed to restart local var, previous not found, register: 6 */
    public ECPoint negate() {
        return isInfinity() ? this : new SecP521R1Point(this.curve, this.f6973x, this.f6974y.negate(), this.f6975zs, this.withCompression);
    }
}
