package org.spongycastle.math.p332ec.custom.sec;

import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.p332ec.ECPoint.AbstractFp;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat224;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP224K1Point */
public class SecP224K1Point extends AbstractFp {
    public SecP224K1Point(ECCurve curve, ECFieldElement x, ECFieldElement y) {
        this(curve, x, y, false);
    }

    public SecP224K1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
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

    SecP224K1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        super(curve, x, y, zs);
        this.withCompression = withCompression;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecP224K1Point(null, getAffineXCoord(), getAffineYCoord());
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
        SecP224K1FieldElement X1 = (SecP224K1FieldElement) this.f6973x;
        SecP224K1FieldElement Y1 = (SecP224K1FieldElement) this.f6974y;
        SecP224K1FieldElement X2 = (SecP224K1FieldElement) b.getXCoord();
        SecP224K1FieldElement Y2 = (SecP224K1FieldElement) b.getYCoord();
        SecP224K1FieldElement Z1 = (SecP224K1FieldElement) this.f6975zs[0];
        SecP224K1FieldElement Z2 = (SecP224K1FieldElement) b.getZCoord(0);
        int[] tt1 = Nat224.createExt();
        int[] t2 = Nat224.create();
        int[] t3 = Nat224.create();
        int[] t4 = Nat224.create();
        boolean Z1IsOne = Z1.isOne();
        if (Z1IsOne) {
            U2 = X2.f7017x;
            S2 = Y2.f7017x;
        } else {
            S2 = t3;
            SecP224K1Field.square(Z1.f7017x, S2);
            U2 = t2;
            SecP224K1Field.multiply(S2, X2.f7017x, U2);
            SecP224K1Field.multiply(S2, Z1.f7017x, S2);
            SecP224K1Field.multiply(S2, Y2.f7017x, S2);
        }
        boolean Z2IsOne = Z2.isOne();
        if (Z2IsOne) {
            U1 = X1.f7017x;
            S1 = Y1.f7017x;
        } else {
            S1 = t4;
            SecP224K1Field.square(Z2.f7017x, S1);
            U1 = tt1;
            SecP224K1Field.multiply(S1, X1.f7017x, U1);
            SecP224K1Field.multiply(S1, Z2.f7017x, S1);
            SecP224K1Field.multiply(S1, Y1.f7017x, S1);
        }
        int[] H = Nat224.create();
        SecP224K1Field.subtract(U1, U2, H);
        int[] R = t2;
        SecP224K1Field.subtract(S1, S2, R);
        if (!Nat224.isZero(H)) {
            int[] HSquared = t3;
            SecP224K1Field.square(H, HSquared);
            int[] G = Nat224.create();
            SecP224K1Field.multiply(HSquared, H, G);
            int[] V = t3;
            SecP224K1Field.multiply(HSquared, U1, V);
            SecP224K1Field.negate(G, G);
            Nat224.mul(S1, G, tt1);
            SecP224K1Field.reduce32(Nat224.addBothTo(V, V, G), G);
            SecP224K1FieldElement X3 = new SecP224K1FieldElement(t4);
            SecP224K1Field.square(R, X3.f7017x);
            SecP224K1Field.subtract(X3.f7017x, G, X3.f7017x);
            SecP224K1FieldElement Y3 = new SecP224K1FieldElement(G);
            SecP224K1Field.subtract(V, X3.f7017x, Y3.f7017x);
            SecP224K1Field.multiplyAddToExt(Y3.f7017x, R, tt1);
            SecP224K1Field.reduce(tt1, Y3.f7017x);
            SecP224K1FieldElement secP224K1FieldElement = new SecP224K1FieldElement(H);
            if (!Z1IsOne) {
                SecP224K1Field.multiply(secP224K1FieldElement.f7017x, Z1.f7017x, secP224K1FieldElement.f7017x);
            }
            if (!Z2IsOne) {
                SecP224K1Field.multiply(secP224K1FieldElement.f7017x, Z2.f7017x, secP224K1FieldElement.f7017x);
            }
            return new SecP224K1Point(curve, X3, Y3, new ECFieldElement[]{secP224K1FieldElement}, this.withCompression);
        } else if (Nat224.isZero(R)) {
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
        SecP224K1FieldElement Y1 = (SecP224K1FieldElement) this.f6974y;
        if (Y1.isZero()) {
            return curve.getInfinity();
        }
        SecP224K1FieldElement X1 = (SecP224K1FieldElement) this.f6973x;
        SecP224K1FieldElement Z1 = (SecP224K1FieldElement) this.f6975zs[0];
        int[] Y1Squared = Nat224.create();
        SecP224K1Field.square(Y1.f7017x, Y1Squared);
        int[] T = Nat224.create();
        SecP224K1Field.square(Y1Squared, T);
        int[] M = Nat224.create();
        SecP224K1Field.square(X1.f7017x, M);
        SecP224K1Field.reduce32(Nat224.addBothTo(M, M, M), M);
        int[] S = Y1Squared;
        SecP224K1Field.multiply(Y1Squared, X1.f7017x, S);
        SecP224K1Field.reduce32(Nat.shiftUpBits(7, S, 2, 0), S);
        int[] t1 = Nat224.create();
        SecP224K1Field.reduce32(Nat.shiftUpBits(7, T, 3, 0, t1), t1);
        SecP224K1FieldElement X3 = new SecP224K1FieldElement(T);
        SecP224K1Field.square(M, X3.f7017x);
        SecP224K1Field.subtract(X3.f7017x, S, X3.f7017x);
        SecP224K1Field.subtract(X3.f7017x, S, X3.f7017x);
        SecP224K1FieldElement Y3 = new SecP224K1FieldElement(S);
        SecP224K1Field.subtract(S, X3.f7017x, Y3.f7017x);
        SecP224K1Field.multiply(Y3.f7017x, M, Y3.f7017x);
        SecP224K1Field.subtract(Y3.f7017x, t1, Y3.f7017x);
        SecP224K1FieldElement Z3 = new SecP224K1FieldElement(M);
        SecP224K1Field.twice(Y1.f7017x, Z3.f7017x);
        if (!Z1.isOne()) {
            SecP224K1Field.multiply(Z3.f7017x, Z1.f7017x, Z3.f7017x);
        }
        return new SecP224K1Point(curve, X3, Y3, new ECFieldElement[]{Z3}, this.withCompression);
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
        return isInfinity() ? this : new SecP224K1Point(this.curve, this.f6973x, this.f6974y.negate(), this.f6975zs, this.withCompression);
    }
}
