package org.spongycastle.math.p332ec.custom.sec;

import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.p332ec.ECPoint.AbstractFp;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat256;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP256K1Point */
public class SecP256K1Point extends AbstractFp {
    public SecP256K1Point(ECCurve curve, ECFieldElement x, ECFieldElement y) {
        this(curve, x, y, false);
    }

    public SecP256K1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
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

    SecP256K1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        super(curve, x, y, zs);
        this.withCompression = withCompression;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecP256K1Point(null, getAffineXCoord(), getAffineYCoord());
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
        SecP256K1FieldElement X1 = (SecP256K1FieldElement) this.f6973x;
        SecP256K1FieldElement Y1 = (SecP256K1FieldElement) this.f6974y;
        SecP256K1FieldElement X2 = (SecP256K1FieldElement) b.getXCoord();
        SecP256K1FieldElement Y2 = (SecP256K1FieldElement) b.getYCoord();
        SecP256K1FieldElement Z1 = (SecP256K1FieldElement) this.f6975zs[0];
        SecP256K1FieldElement Z2 = (SecP256K1FieldElement) b.getZCoord(0);
        int[] tt1 = Nat256.createExt();
        int[] t2 = Nat256.create();
        int[] t3 = Nat256.create();
        int[] t4 = Nat256.create();
        boolean Z1IsOne = Z1.isOne();
        if (Z1IsOne) {
            U2 = X2.f7028x;
            S2 = Y2.f7028x;
        } else {
            S2 = t3;
            SecP256K1Field.square(Z1.f7028x, S2);
            U2 = t2;
            SecP256K1Field.multiply(S2, X2.f7028x, U2);
            SecP256K1Field.multiply(S2, Z1.f7028x, S2);
            SecP256K1Field.multiply(S2, Y2.f7028x, S2);
        }
        boolean Z2IsOne = Z2.isOne();
        if (Z2IsOne) {
            U1 = X1.f7028x;
            S1 = Y1.f7028x;
        } else {
            S1 = t4;
            SecP256K1Field.square(Z2.f7028x, S1);
            U1 = tt1;
            SecP256K1Field.multiply(S1, X1.f7028x, U1);
            SecP256K1Field.multiply(S1, Z2.f7028x, S1);
            SecP256K1Field.multiply(S1, Y1.f7028x, S1);
        }
        int[] H = Nat256.create();
        SecP256K1Field.subtract(U1, U2, H);
        int[] R = t2;
        SecP256K1Field.subtract(S1, S2, R);
        if (!Nat256.isZero(H)) {
            int[] HSquared = t3;
            SecP256K1Field.square(H, HSquared);
            int[] G = Nat256.create();
            SecP256K1Field.multiply(HSquared, H, G);
            int[] V = t3;
            SecP256K1Field.multiply(HSquared, U1, V);
            SecP256K1Field.negate(G, G);
            Nat256.mul(S1, G, tt1);
            SecP256K1Field.reduce32(Nat256.addBothTo(V, V, G), G);
            SecP256K1FieldElement X3 = new SecP256K1FieldElement(t4);
            SecP256K1Field.square(R, X3.f7028x);
            SecP256K1Field.subtract(X3.f7028x, G, X3.f7028x);
            SecP256K1FieldElement Y3 = new SecP256K1FieldElement(G);
            SecP256K1Field.subtract(V, X3.f7028x, Y3.f7028x);
            SecP256K1Field.multiplyAddToExt(Y3.f7028x, R, tt1);
            SecP256K1Field.reduce(tt1, Y3.f7028x);
            SecP256K1FieldElement secP256K1FieldElement = new SecP256K1FieldElement(H);
            if (!Z1IsOne) {
                SecP256K1Field.multiply(secP256K1FieldElement.f7028x, Z1.f7028x, secP256K1FieldElement.f7028x);
            }
            if (!Z2IsOne) {
                SecP256K1Field.multiply(secP256K1FieldElement.f7028x, Z2.f7028x, secP256K1FieldElement.f7028x);
            }
            return new SecP256K1Point(curve, X3, Y3, new ECFieldElement[]{secP256K1FieldElement}, this.withCompression);
        } else if (Nat256.isZero(R)) {
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
        SecP256K1FieldElement Y1 = (SecP256K1FieldElement) this.f6974y;
        if (Y1.isZero()) {
            return curve.getInfinity();
        }
        SecP256K1FieldElement X1 = (SecP256K1FieldElement) this.f6973x;
        SecP256K1FieldElement Z1 = (SecP256K1FieldElement) this.f6975zs[0];
        int[] Y1Squared = Nat256.create();
        SecP256K1Field.square(Y1.f7028x, Y1Squared);
        int[] T = Nat256.create();
        SecP256K1Field.square(Y1Squared, T);
        int[] M = Nat256.create();
        SecP256K1Field.square(X1.f7028x, M);
        SecP256K1Field.reduce32(Nat256.addBothTo(M, M, M), M);
        int[] S = Y1Squared;
        SecP256K1Field.multiply(Y1Squared, X1.f7028x, S);
        SecP256K1Field.reduce32(Nat.shiftUpBits(8, S, 2, 0), S);
        int[] t1 = Nat256.create();
        SecP256K1Field.reduce32(Nat.shiftUpBits(8, T, 3, 0, t1), t1);
        SecP256K1FieldElement X3 = new SecP256K1FieldElement(T);
        SecP256K1Field.square(M, X3.f7028x);
        SecP256K1Field.subtract(X3.f7028x, S, X3.f7028x);
        SecP256K1Field.subtract(X3.f7028x, S, X3.f7028x);
        SecP256K1FieldElement Y3 = new SecP256K1FieldElement(S);
        SecP256K1Field.subtract(S, X3.f7028x, Y3.f7028x);
        SecP256K1Field.multiply(Y3.f7028x, M, Y3.f7028x);
        SecP256K1Field.subtract(Y3.f7028x, t1, Y3.f7028x);
        SecP256K1FieldElement Z3 = new SecP256K1FieldElement(M);
        SecP256K1Field.twice(Y1.f7028x, Z3.f7028x);
        if (!Z1.isOne()) {
            SecP256K1Field.multiply(Z3.f7028x, Z1.f7028x, Z3.f7028x);
        }
        return new SecP256K1Point(curve, X3, Y3, new ECFieldElement[]{Z3}, this.withCompression);
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
        return isInfinity() ? this : new SecP256K1Point(this.curve, this.f6973x, this.f6974y.negate(), this.f6975zs, this.withCompression);
    }
}
