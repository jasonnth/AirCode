package org.spongycastle.math.p332ec.custom.sec;

import org.spongycastle.math.p332ec.ECConstants;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.p332ec.ECPoint.AbstractF2m;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecT113R2Point */
public class SecT113R2Point extends AbstractF2m {
    public SecT113R2Point(ECCurve curve, ECFieldElement x, ECFieldElement y) {
        this(curve, x, y, false);
    }

    public SecT113R2Point(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
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

    SecT113R2Point(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        super(curve, x, y, zs);
        this.withCompression = withCompression;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecT113R2Point(null, getAffineXCoord(), getAffineYCoord());
    }

    public ECFieldElement getYCoord() {
        ECFieldElement X = this.f6973x;
        ECFieldElement L = this.f6974y;
        if (isInfinity() || X.isZero()) {
            return L;
        }
        ECFieldElement Y = L.add(X).multiply(X);
        ECFieldElement Z = this.f6975zs[0];
        if (!Z.isOne()) {
            return Y.divide(Z);
        }
        return Y;
    }

    /* access modifiers changed from: protected */
    public boolean getCompressionYTilde() {
        ECFieldElement X = getRawXCoord();
        if (!X.isZero() && getRawYCoord().testBitZero() != X.testBitZero()) {
            return true;
        }
        return false;
    }

    public ECPoint add(ECPoint b) {
        ECFieldElement X3;
        ECFieldElement L3;
        ECFieldElement Z3;
        if (isInfinity()) {
            return b;
        }
        if (b.isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        ECFieldElement X1 = this.f6973x;
        ECFieldElement X2 = b.getRawXCoord();
        if (!X1.isZero()) {
            ECFieldElement L1 = this.f6974y;
            ECFieldElement Z1 = this.f6975zs[0];
            ECFieldElement L2 = b.getRawYCoord();
            ECFieldElement Z2 = b.getZCoord(0);
            boolean Z1IsOne = Z1.isOne();
            ECFieldElement U2 = X2;
            ECFieldElement S2 = L2;
            if (!Z1IsOne) {
                U2 = U2.multiply(Z1);
                S2 = S2.multiply(Z1);
            }
            boolean Z2IsOne = Z2.isOne();
            ECFieldElement U1 = X1;
            ECFieldElement S1 = L1;
            if (!Z2IsOne) {
                U1 = U1.multiply(Z2);
                S1 = S1.multiply(Z2);
            }
            ECFieldElement A = S1.add(S2);
            ECFieldElement B = U1.add(U2);
            if (!B.isZero()) {
                if (X2.isZero()) {
                    ECPoint p = normalize();
                    ECFieldElement X12 = p.getXCoord();
                    ECFieldElement Y1 = p.getYCoord();
                    ECFieldElement L = Y1.add(L2).divide(X12);
                    X3 = L.square().add(L).add(X12).add(curve.getA());
                    if (X3.isZero()) {
                        SecT113R2Point secT113R2Point = new SecT113R2Point(curve, X3, curve.getB().sqrt(), this.withCompression);
                        return secT113R2Point;
                    }
                    L3 = L.multiply(X12.add(X3)).add(X3).add(Y1).divide(X3).add(X3);
                    Z3 = curve.fromBigInteger(ECConstants.ONE);
                } else {
                    ECFieldElement B2 = B.square();
                    ECFieldElement AU1 = A.multiply(U1);
                    ECFieldElement AU2 = A.multiply(U2);
                    X3 = AU1.multiply(AU2);
                    if (X3.isZero()) {
                        SecT113R2Point secT113R2Point2 = new SecT113R2Point(curve, X3, curve.getB().sqrt(), this.withCompression);
                        return secT113R2Point2;
                    }
                    ECFieldElement ABZ2 = A.multiply(B2);
                    if (!Z2IsOne) {
                        ABZ2 = ABZ2.multiply(Z2);
                    }
                    L3 = AU2.add(B2).squarePlusProduct(ABZ2, L1.add(Z1));
                    Z3 = ABZ2;
                    if (!Z1IsOne) {
                        Z3 = Z3.multiply(Z1);
                    }
                }
                return new SecT113R2Point(curve, X3, L3, new ECFieldElement[]{Z3}, this.withCompression);
            } else if (A.isZero()) {
                return twice();
            } else {
                return curve.getInfinity();
            }
        } else if (X2.isZero()) {
            return curve.getInfinity();
        } else {
            return b.add(this);
        }
    }

    /* Debug info: failed to restart local var, previous not found, register: 18 */
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        ECFieldElement X1 = this.f6973x;
        if (X1.isZero()) {
            return curve.getInfinity();
        }
        ECFieldElement L1 = this.f6974y;
        ECFieldElement Z1 = this.f6975zs[0];
        boolean Z1IsOne = Z1.isOne();
        ECFieldElement L1Z1 = Z1IsOne ? L1 : L1.multiply(Z1);
        ECFieldElement Z1Sq = Z1IsOne ? Z1 : Z1.square();
        ECFieldElement a = curve.getA();
        ECFieldElement T = L1.square().add(L1Z1).add(Z1IsOne ? a : a.multiply(Z1Sq));
        if (T.isZero()) {
            return new SecT113R2Point(curve, T, curve.getB().sqrt(), this.withCompression);
        }
        ECFieldElement X3 = T.square();
        ECFieldElement Z3 = Z1IsOne ? T : T.multiply(Z1Sq);
        return new SecT113R2Point(curve, X3, (Z1IsOne ? X1 : X1.multiply(Z1)).squarePlusProduct(T, L1Z1).add(X3).add(Z3), new ECFieldElement[]{Z3}, this.withCompression);
    }

    public ECPoint twicePlus(ECPoint b) {
        if (isInfinity()) {
            return b;
        }
        if (b.isInfinity()) {
            return twice();
        }
        ECCurve curve = getCurve();
        ECFieldElement X1 = this.f6973x;
        if (X1.isZero()) {
            return b;
        }
        ECFieldElement X2 = b.getRawXCoord();
        ECFieldElement Z2 = b.getZCoord(0);
        if (X2.isZero() || !Z2.isOne()) {
            return twice().add(b);
        }
        ECFieldElement L1 = this.f6974y;
        ECFieldElement Z1 = this.f6975zs[0];
        ECFieldElement L2 = b.getRawYCoord();
        ECFieldElement X1Sq = X1.square();
        ECFieldElement L1Sq = L1.square();
        ECFieldElement Z1Sq = Z1.square();
        ECFieldElement T = curve.getA().multiply(Z1Sq).add(L1Sq).add(L1.multiply(Z1));
        ECFieldElement L2plus1 = L2.addOne();
        ECFieldElement A = curve.getA().add(L2plus1).multiply(Z1Sq).add(L1Sq).multiplyPlusProduct(T, X1Sq, Z1Sq);
        ECFieldElement X2Z1Sq = X2.multiply(Z1Sq);
        ECFieldElement B = X2Z1Sq.add(T).square();
        if (B.isZero()) {
            if (A.isZero()) {
                return b.twice();
            }
            return curve.getInfinity();
        } else if (A.isZero()) {
            SecT113R2Point secT113R2Point = new SecT113R2Point(curve, A, curve.getB().sqrt(), this.withCompression);
            return secT113R2Point;
        } else {
            ECFieldElement X3 = A.square().multiply(X2Z1Sq);
            ECFieldElement Z3 = A.multiply(B).multiply(Z1Sq);
            return new SecT113R2Point(curve, X3, A.add(B).square().multiplyPlusProduct(T, L2plus1, Z3), new ECFieldElement[]{Z3}, this.withCompression);
        }
    }

    /* Debug info: failed to restart local var, previous not found, register: 8 */
    public ECPoint negate() {
        if (isInfinity()) {
            return this;
        }
        ECFieldElement X = this.f6973x;
        if (X.isZero()) {
            return this;
        }
        ECFieldElement L = this.f6974y;
        ECFieldElement Z = this.f6975zs[0];
        return new SecT113R2Point(this.curve, X, L.add(Z), new ECFieldElement[]{Z}, this.withCompression);
    }
}
