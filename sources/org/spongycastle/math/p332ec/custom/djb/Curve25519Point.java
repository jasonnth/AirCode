package org.spongycastle.math.p332ec.custom.djb;

import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.p332ec.ECPoint.AbstractFp;
import org.spongycastle.math.raw.Nat256;

/* renamed from: org.spongycastle.math.ec.custom.djb.Curve25519Point */
public class Curve25519Point extends AbstractFp {
    public Curve25519Point(ECCurve curve, ECFieldElement x, ECFieldElement y) {
        this(curve, x, y, false);
    }

    public Curve25519Point(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
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

    Curve25519Point(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        super(curve, x, y, zs);
        this.withCompression = withCompression;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new Curve25519Point(null, getAffineXCoord(), getAffineYCoord());
    }

    public ECFieldElement getZCoord(int index) {
        if (index == 1) {
            return getJacobianModifiedW();
        }
        return super.getZCoord(index);
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
        Curve25519FieldElement X1 = (Curve25519FieldElement) this.f6973x;
        Curve25519FieldElement Y1 = (Curve25519FieldElement) this.f6974y;
        Curve25519FieldElement Z1 = (Curve25519FieldElement) this.f6975zs[0];
        Curve25519FieldElement X2 = (Curve25519FieldElement) b.getXCoord();
        Curve25519FieldElement Y2 = (Curve25519FieldElement) b.getYCoord();
        Curve25519FieldElement Z2 = (Curve25519FieldElement) b.getZCoord(0);
        int[] tt1 = Nat256.createExt();
        int[] t2 = Nat256.create();
        int[] t3 = Nat256.create();
        int[] t4 = Nat256.create();
        boolean Z1IsOne = Z1.isOne();
        if (Z1IsOne) {
            U2 = X2.f6983x;
            S2 = Y2.f6983x;
        } else {
            S2 = t3;
            Curve25519Field.square(Z1.f6983x, S2);
            U2 = t2;
            Curve25519Field.multiply(S2, X2.f6983x, U2);
            Curve25519Field.multiply(S2, Z1.f6983x, S2);
            Curve25519Field.multiply(S2, Y2.f6983x, S2);
        }
        boolean Z2IsOne = Z2.isOne();
        if (Z2IsOne) {
            U1 = X1.f6983x;
            S1 = Y1.f6983x;
        } else {
            S1 = t4;
            Curve25519Field.square(Z2.f6983x, S1);
            U1 = tt1;
            Curve25519Field.multiply(S1, X1.f6983x, U1);
            Curve25519Field.multiply(S1, Z2.f6983x, S1);
            Curve25519Field.multiply(S1, Y1.f6983x, S1);
        }
        int[] H = Nat256.create();
        Curve25519Field.subtract(U1, U2, H);
        int[] R = t2;
        Curve25519Field.subtract(S1, S2, R);
        if (!Nat256.isZero(H)) {
            int[] HSquared = Nat256.create();
            Curve25519Field.square(H, HSquared);
            int[] G = Nat256.create();
            Curve25519Field.multiply(HSquared, H, G);
            int[] V = t3;
            Curve25519Field.multiply(HSquared, U1, V);
            Curve25519Field.negate(G, G);
            Nat256.mul(S1, G, tt1);
            Curve25519Field.reduce27(Nat256.addBothTo(V, V, G), G);
            Curve25519FieldElement X3 = new Curve25519FieldElement(t4);
            Curve25519Field.square(R, X3.f6983x);
            Curve25519Field.subtract(X3.f6983x, G, X3.f6983x);
            Curve25519FieldElement Y3 = new Curve25519FieldElement(G);
            Curve25519Field.subtract(V, X3.f6983x, Y3.f6983x);
            Curve25519Field.multiplyAddToExt(Y3.f6983x, R, tt1);
            Curve25519Field.reduce(tt1, Y3.f6983x);
            Curve25519FieldElement curve25519FieldElement = new Curve25519FieldElement(H);
            if (!Z1IsOne) {
                Curve25519Field.multiply(curve25519FieldElement.f6983x, Z1.f6983x, curve25519FieldElement.f6983x);
            }
            if (!Z2IsOne) {
                Curve25519Field.multiply(curve25519FieldElement.f6983x, Z2.f6983x, curve25519FieldElement.f6983x);
            }
            return new Curve25519Point(curve, X3, Y3, new ECFieldElement[]{curve25519FieldElement, calculateJacobianModifiedW(curve25519FieldElement, (!Z1IsOne || !Z2IsOne) ? null : HSquared)}, this.withCompression);
        } else if (Nat256.isZero(R)) {
            return twice();
        } else {
            return curve.getInfinity();
        }
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        if (this.f6974y.isZero()) {
            return curve.getInfinity();
        }
        return twiceJacobianModified(true);
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
        return !this.f6974y.isZero() ? twiceJacobianModified(false).add(b) : b;
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public ECPoint threeTimes() {
        return (!isInfinity() && !this.f6974y.isZero()) ? twiceJacobianModified(false).add(this) : this;
    }

    /* Debug info: failed to restart local var, previous not found, register: 6 */
    public ECPoint negate() {
        return isInfinity() ? this : new Curve25519Point(getCurve(), this.f6973x, this.f6974y.negate(), this.f6975zs, this.withCompression);
    }

    /* access modifiers changed from: protected */
    public Curve25519FieldElement calculateJacobianModifiedW(Curve25519FieldElement Z, int[] ZSquared) {
        Curve25519FieldElement a4 = (Curve25519FieldElement) getCurve().getA();
        if (Z.isOne()) {
            return a4;
        }
        Curve25519FieldElement W = new Curve25519FieldElement();
        if (ZSquared == null) {
            ZSquared = W.f6983x;
            Curve25519Field.square(Z.f6983x, ZSquared);
        }
        Curve25519Field.square(ZSquared, W.f6983x);
        Curve25519Field.multiply(W.f6983x, a4.f6983x, W.f6983x);
        return W;
    }

    /* access modifiers changed from: protected */
    public Curve25519FieldElement getJacobianModifiedW() {
        Curve25519FieldElement W = (Curve25519FieldElement) this.f6975zs[1];
        if (W != null) {
            return W;
        }
        ECFieldElement[] eCFieldElementArr = this.f6975zs;
        Curve25519FieldElement W2 = calculateJacobianModifiedW((Curve25519FieldElement) this.f6975zs[0], null);
        eCFieldElementArr[1] = W2;
        return W2;
    }

    /* access modifiers changed from: protected */
    public Curve25519Point twiceJacobianModified(boolean calculateW) {
        Curve25519FieldElement X1 = (Curve25519FieldElement) this.f6973x;
        Curve25519FieldElement Y1 = (Curve25519FieldElement) this.f6974y;
        Curve25519FieldElement Z1 = (Curve25519FieldElement) this.f6975zs[0];
        Curve25519FieldElement W1 = getJacobianModifiedW();
        int[] M = Nat256.create();
        Curve25519Field.square(X1.f6983x, M);
        Curve25519Field.reduce27(Nat256.addBothTo(M, M, M) + Nat256.addTo(W1.f6983x, M), M);
        int[] _2Y1 = Nat256.create();
        Curve25519Field.twice(Y1.f6983x, _2Y1);
        int[] _2Y1Squared = Nat256.create();
        Curve25519Field.multiply(_2Y1, Y1.f6983x, _2Y1Squared);
        int[] S = Nat256.create();
        Curve25519Field.multiply(_2Y1Squared, X1.f6983x, S);
        Curve25519Field.twice(S, S);
        int[] _8T = Nat256.create();
        Curve25519Field.square(_2Y1Squared, _8T);
        Curve25519Field.twice(_8T, _8T);
        Curve25519FieldElement X3 = new Curve25519FieldElement(_2Y1Squared);
        Curve25519Field.square(M, X3.f6983x);
        Curve25519Field.subtract(X3.f6983x, S, X3.f6983x);
        Curve25519Field.subtract(X3.f6983x, S, X3.f6983x);
        Curve25519FieldElement Y3 = new Curve25519FieldElement(S);
        Curve25519Field.subtract(S, X3.f6983x, Y3.f6983x);
        Curve25519Field.multiply(Y3.f6983x, M, Y3.f6983x);
        Curve25519Field.subtract(Y3.f6983x, _8T, Y3.f6983x);
        Curve25519FieldElement Z3 = new Curve25519FieldElement(_2Y1);
        if (!Nat256.isOne(Z1.f6983x)) {
            Curve25519Field.multiply(Z3.f6983x, Z1.f6983x, Z3.f6983x);
        }
        Curve25519FieldElement W3 = null;
        if (calculateW) {
            W3 = new Curve25519FieldElement(_8T);
            Curve25519Field.multiply(W3.f6983x, W1.f6983x, W3.f6983x);
            Curve25519Field.twice(W3.f6983x, W3.f6983x);
        }
        return new Curve25519Point(getCurve(), X3, Y3, new ECFieldElement[]{Z3, W3}, this.withCompression);
    }
}
