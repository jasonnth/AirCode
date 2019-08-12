package org.spongycastle.math.p332ec.custom.sec;

import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.p332ec.ECPoint.AbstractFp;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat160;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP160K1Point */
public class SecP160K1Point extends AbstractFp {
    public SecP160K1Point(ECCurve curve, ECFieldElement x, ECFieldElement y) {
        this(curve, x, y, false);
    }

    public SecP160K1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
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

    SecP160K1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        super(curve, x, y, zs);
        this.withCompression = withCompression;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecP160K1Point(null, getAffineXCoord(), getAffineYCoord());
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
        SecP160R2FieldElement X1 = (SecP160R2FieldElement) this.f6973x;
        SecP160R2FieldElement Y1 = (SecP160R2FieldElement) this.f6974y;
        SecP160R2FieldElement X2 = (SecP160R2FieldElement) b.getXCoord();
        SecP160R2FieldElement Y2 = (SecP160R2FieldElement) b.getYCoord();
        SecP160R2FieldElement Z1 = (SecP160R2FieldElement) this.f6975zs[0];
        SecP160R2FieldElement Z2 = (SecP160R2FieldElement) b.getZCoord(0);
        int[] tt1 = Nat160.createExt();
        int[] t2 = Nat160.create();
        int[] t3 = Nat160.create();
        int[] t4 = Nat160.create();
        boolean Z1IsOne = Z1.isOne();
        if (Z1IsOne) {
            U2 = X2.f7001x;
            S2 = Y2.f7001x;
        } else {
            S2 = t3;
            SecP160R2Field.square(Z1.f7001x, S2);
            U2 = t2;
            SecP160R2Field.multiply(S2, X2.f7001x, U2);
            SecP160R2Field.multiply(S2, Z1.f7001x, S2);
            SecP160R2Field.multiply(S2, Y2.f7001x, S2);
        }
        boolean Z2IsOne = Z2.isOne();
        if (Z2IsOne) {
            U1 = X1.f7001x;
            S1 = Y1.f7001x;
        } else {
            S1 = t4;
            SecP160R2Field.square(Z2.f7001x, S1);
            U1 = tt1;
            SecP160R2Field.multiply(S1, X1.f7001x, U1);
            SecP160R2Field.multiply(S1, Z2.f7001x, S1);
            SecP160R2Field.multiply(S1, Y1.f7001x, S1);
        }
        int[] H = Nat160.create();
        SecP160R2Field.subtract(U1, U2, H);
        int[] R = t2;
        SecP160R2Field.subtract(S1, S2, R);
        if (!Nat160.isZero(H)) {
            int[] HSquared = t3;
            SecP160R2Field.square(H, HSquared);
            int[] G = Nat160.create();
            SecP160R2Field.multiply(HSquared, H, G);
            int[] V = t3;
            SecP160R2Field.multiply(HSquared, U1, V);
            SecP160R2Field.negate(G, G);
            Nat160.mul(S1, G, tt1);
            SecP160R2Field.reduce32(Nat160.addBothTo(V, V, G), G);
            SecP160R2FieldElement X3 = new SecP160R2FieldElement(t4);
            SecP160R2Field.square(R, X3.f7001x);
            SecP160R2Field.subtract(X3.f7001x, G, X3.f7001x);
            SecP160R2FieldElement Y3 = new SecP160R2FieldElement(G);
            SecP160R2Field.subtract(V, X3.f7001x, Y3.f7001x);
            SecP160R2Field.multiplyAddToExt(Y3.f7001x, R, tt1);
            SecP160R2Field.reduce(tt1, Y3.f7001x);
            SecP160R2FieldElement secP160R2FieldElement = new SecP160R2FieldElement(H);
            if (!Z1IsOne) {
                SecP160R2Field.multiply(secP160R2FieldElement.f7001x, Z1.f7001x, secP160R2FieldElement.f7001x);
            }
            if (!Z2IsOne) {
                SecP160R2Field.multiply(secP160R2FieldElement.f7001x, Z2.f7001x, secP160R2FieldElement.f7001x);
            }
            return new SecP160K1Point(curve, X3, Y3, new ECFieldElement[]{secP160R2FieldElement}, this.withCompression);
        } else if (Nat160.isZero(R)) {
            return twice();
        } else {
            return curve.getInfinity();
        }
    }

    /* Debug info: failed to restart local var, previous not found, register: 17 */
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SecP160R2FieldElement Y1 = (SecP160R2FieldElement) this.f6974y;
        if (Y1.isZero()) {
            return curve.getInfinity();
        }
        SecP160R2FieldElement X1 = (SecP160R2FieldElement) this.f6973x;
        SecP160R2FieldElement Z1 = (SecP160R2FieldElement) this.f6975zs[0];
        int[] Y1Squared = Nat160.create();
        SecP160R2Field.square(Y1.f7001x, Y1Squared);
        int[] T = Nat160.create();
        SecP160R2Field.square(Y1Squared, T);
        int[] M = Nat160.create();
        SecP160R2Field.square(X1.f7001x, M);
        SecP160R2Field.reduce32(Nat160.addBothTo(M, M, M), M);
        int[] S = Y1Squared;
        SecP160R2Field.multiply(Y1Squared, X1.f7001x, S);
        SecP160R2Field.reduce32(Nat.shiftUpBits(5, S, 2, 0), S);
        int[] t1 = Nat160.create();
        SecP160R2Field.reduce32(Nat.shiftUpBits(5, T, 3, 0, t1), t1);
        SecP160R2FieldElement X3 = new SecP160R2FieldElement(T);
        SecP160R2Field.square(M, X3.f7001x);
        SecP160R2Field.subtract(X3.f7001x, S, X3.f7001x);
        SecP160R2Field.subtract(X3.f7001x, S, X3.f7001x);
        SecP160R2FieldElement Y3 = new SecP160R2FieldElement(S);
        SecP160R2Field.subtract(S, X3.f7001x, Y3.f7001x);
        SecP160R2Field.multiply(Y3.f7001x, M, Y3.f7001x);
        SecP160R2Field.subtract(Y3.f7001x, t1, Y3.f7001x);
        SecP160R2FieldElement Z3 = new SecP160R2FieldElement(M);
        SecP160R2Field.twice(Y1.f7001x, Z3.f7001x);
        if (!Z1.isOne()) {
            SecP160R2Field.multiply(Z3.f7001x, Z1.f7001x, Z3.f7001x);
        }
        return new SecP160K1Point(curve, X3, Y3, new ECFieldElement[]{Z3}, this.withCompression);
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
        return isInfinity() ? this : new SecP160K1Point(this.curve, this.f6973x, this.f6974y.negate(), this.f6975zs, this.withCompression);
    }
}
