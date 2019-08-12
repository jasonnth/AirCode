package org.spongycastle.math.p332ec.custom.sec;

import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.p332ec.ECPoint.AbstractFp;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat224;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP224R1Point */
public class SecP224R1Point extends AbstractFp {
    public SecP224R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y) {
        this(curve, x, y, false);
    }

    public SecP224R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
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

    SecP224R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        super(curve, x, y, zs);
        this.withCompression = withCompression;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecP224R1Point(null, getAffineXCoord(), getAffineYCoord());
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
        SecP224R1FieldElement X1 = (SecP224R1FieldElement) this.f6973x;
        SecP224R1FieldElement Y1 = (SecP224R1FieldElement) this.f6974y;
        SecP224R1FieldElement X2 = (SecP224R1FieldElement) b.getXCoord();
        SecP224R1FieldElement Y2 = (SecP224R1FieldElement) b.getYCoord();
        SecP224R1FieldElement Z1 = (SecP224R1FieldElement) this.f6975zs[0];
        SecP224R1FieldElement Z2 = (SecP224R1FieldElement) b.getZCoord(0);
        int[] tt1 = Nat224.createExt();
        int[] t2 = Nat224.create();
        int[] t3 = Nat224.create();
        int[] t4 = Nat224.create();
        boolean Z1IsOne = Z1.isOne();
        if (Z1IsOne) {
            U2 = X2.f7023x;
            S2 = Y2.f7023x;
        } else {
            S2 = t3;
            SecP224R1Field.square(Z1.f7023x, S2);
            U2 = t2;
            SecP224R1Field.multiply(S2, X2.f7023x, U2);
            SecP224R1Field.multiply(S2, Z1.f7023x, S2);
            SecP224R1Field.multiply(S2, Y2.f7023x, S2);
        }
        boolean Z2IsOne = Z2.isOne();
        if (Z2IsOne) {
            U1 = X1.f7023x;
            S1 = Y1.f7023x;
        } else {
            S1 = t4;
            SecP224R1Field.square(Z2.f7023x, S1);
            U1 = tt1;
            SecP224R1Field.multiply(S1, X1.f7023x, U1);
            SecP224R1Field.multiply(S1, Z2.f7023x, S1);
            SecP224R1Field.multiply(S1, Y1.f7023x, S1);
        }
        int[] H = Nat224.create();
        SecP224R1Field.subtract(U1, U2, H);
        int[] R = t2;
        SecP224R1Field.subtract(S1, S2, R);
        if (!Nat224.isZero(H)) {
            int[] HSquared = t3;
            SecP224R1Field.square(H, HSquared);
            int[] G = Nat224.create();
            SecP224R1Field.multiply(HSquared, H, G);
            int[] V = t3;
            SecP224R1Field.multiply(HSquared, U1, V);
            SecP224R1Field.negate(G, G);
            Nat224.mul(S1, G, tt1);
            SecP224R1Field.reduce32(Nat224.addBothTo(V, V, G), G);
            SecP224R1FieldElement X3 = new SecP224R1FieldElement(t4);
            SecP224R1Field.square(R, X3.f7023x);
            SecP224R1Field.subtract(X3.f7023x, G, X3.f7023x);
            SecP224R1FieldElement Y3 = new SecP224R1FieldElement(G);
            SecP224R1Field.subtract(V, X3.f7023x, Y3.f7023x);
            SecP224R1Field.multiplyAddToExt(Y3.f7023x, R, tt1);
            SecP224R1Field.reduce(tt1, Y3.f7023x);
            SecP224R1FieldElement secP224R1FieldElement = new SecP224R1FieldElement(H);
            if (!Z1IsOne) {
                SecP224R1Field.multiply(secP224R1FieldElement.f7023x, Z1.f7023x, secP224R1FieldElement.f7023x);
            }
            if (!Z2IsOne) {
                SecP224R1Field.multiply(secP224R1FieldElement.f7023x, Z2.f7023x, secP224R1FieldElement.f7023x);
            }
            return new SecP224R1Point(curve, X3, Y3, new ECFieldElement[]{secP224R1FieldElement}, this.withCompression);
        } else if (Nat224.isZero(R)) {
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
        SecP224R1FieldElement Y1 = (SecP224R1FieldElement) this.f6974y;
        if (Y1.isZero()) {
            return curve.getInfinity();
        }
        SecP224R1FieldElement X1 = (SecP224R1FieldElement) this.f6973x;
        SecP224R1FieldElement Z1 = (SecP224R1FieldElement) this.f6975zs[0];
        int[] t1 = Nat224.create();
        int[] t2 = Nat224.create();
        int[] Y1Squared = Nat224.create();
        SecP224R1Field.square(Y1.f7023x, Y1Squared);
        int[] T = Nat224.create();
        SecP224R1Field.square(Y1Squared, T);
        boolean Z1IsOne = Z1.isOne();
        int[] Z1Squared = Z1.f7023x;
        if (!Z1IsOne) {
            Z1Squared = t2;
            SecP224R1Field.square(Z1.f7023x, Z1Squared);
        }
        SecP224R1Field.subtract(X1.f7023x, Z1Squared, t1);
        int[] M = t2;
        SecP224R1Field.add(X1.f7023x, Z1Squared, M);
        SecP224R1Field.multiply(M, t1, M);
        SecP224R1Field.reduce32(Nat224.addBothTo(M, M, M), M);
        int[] S = Y1Squared;
        SecP224R1Field.multiply(Y1Squared, X1.f7023x, S);
        SecP224R1Field.reduce32(Nat.shiftUpBits(7, S, 2, 0), S);
        SecP224R1Field.reduce32(Nat.shiftUpBits(7, T, 3, 0, t1), t1);
        SecP224R1FieldElement X3 = new SecP224R1FieldElement(T);
        SecP224R1Field.square(M, X3.f7023x);
        SecP224R1Field.subtract(X3.f7023x, S, X3.f7023x);
        SecP224R1Field.subtract(X3.f7023x, S, X3.f7023x);
        SecP224R1FieldElement Y3 = new SecP224R1FieldElement(S);
        SecP224R1Field.subtract(S, X3.f7023x, Y3.f7023x);
        SecP224R1Field.multiply(Y3.f7023x, M, Y3.f7023x);
        SecP224R1Field.subtract(Y3.f7023x, t1, Y3.f7023x);
        SecP224R1FieldElement secP224R1FieldElement = new SecP224R1FieldElement(M);
        SecP224R1Field.twice(Y1.f7023x, secP224R1FieldElement.f7023x);
        if (!Z1IsOne) {
            SecP224R1Field.multiply(secP224R1FieldElement.f7023x, Z1.f7023x, secP224R1FieldElement.f7023x);
        }
        return new SecP224R1Point(curve, X3, Y3, new ECFieldElement[]{secP224R1FieldElement}, this.withCompression);
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
        return isInfinity() ? this : new SecP224R1Point(this.curve, this.f6973x, this.f6974y.negate(), this.f6975zs, this.withCompression);
    }
}
