package org.spongycastle.math.p332ec;

import java.math.BigInteger;
import java.util.Hashtable;

/* renamed from: org.spongycastle.math.ec.ECPoint */
public abstract class ECPoint {
    protected static ECFieldElement[] EMPTY_ZS = new ECFieldElement[0];
    protected ECCurve curve;
    protected Hashtable preCompTable;
    protected boolean withCompression;

    /* renamed from: x */
    protected ECFieldElement f6973x;

    /* renamed from: y */
    protected ECFieldElement f6974y;

    /* renamed from: zs */
    protected ECFieldElement[] f6975zs;

    /* renamed from: org.spongycastle.math.ec.ECPoint$AbstractF2m */
    public static abstract class AbstractF2m extends ECPoint {
        protected AbstractF2m(ECCurve curve, ECFieldElement x, ECFieldElement y) {
            super(curve, x, y);
        }

        protected AbstractF2m(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs) {
            super(curve, x, y, zs);
        }

        /* access modifiers changed from: protected */
        public boolean satisfiesCurveEquation() {
            ECFieldElement lhs;
            ECFieldElement rhs;
            ECCurve curve = getCurve();
            ECFieldElement X = this.f6973x;
            ECFieldElement A = curve.getA();
            ECFieldElement B = curve.getB();
            int coord = curve.getCoordinateSystem();
            if (coord == 6) {
                ECFieldElement Z = this.f6975zs[0];
                boolean ZIsOne = Z.isOne();
                if (X.isZero()) {
                    ECFieldElement lhs2 = this.f6974y.square();
                    ECFieldElement rhs2 = B;
                    if (!ZIsOne) {
                        rhs2 = rhs2.multiply(Z.square());
                    }
                    return lhs2.equals(rhs2);
                }
                ECFieldElement L = this.f6974y;
                ECFieldElement X2 = X.square();
                if (ZIsOne) {
                    lhs = L.square().add(L).add(A);
                    rhs = X2.square().add(B);
                } else {
                    ECFieldElement Z2 = Z.square();
                    ECFieldElement Z4 = Z2.square();
                    lhs = L.add(Z).multiplyPlusProduct(L, A, Z2);
                    rhs = X2.squarePlusProduct(B, Z4);
                }
                return lhs.multiply(X2).equals(rhs);
            }
            ECFieldElement Y = this.f6974y;
            ECFieldElement lhs3 = Y.add(X).multiply(Y);
            switch (coord) {
                case 0:
                    break;
                case 1:
                    ECFieldElement Z3 = this.f6975zs[0];
                    if (!Z3.isOne()) {
                        ECFieldElement Z32 = Z3.multiply(Z3.square());
                        lhs3 = lhs3.multiply(Z3);
                        A = A.multiply(Z3);
                        B = B.multiply(Z32);
                        break;
                    }
                    break;
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
            return lhs3.equals(X.add(A).multiply(X.square()).add(B));
        }

        /* Debug info: failed to restart local var, previous not found, register: 10 */
        public ECPoint scaleX(ECFieldElement scale) {
            if (isInfinity()) {
                return this;
            }
            switch (getCurveCoordinateSystem()) {
                case 5:
                    ECFieldElement X = getRawXCoord();
                    ECFieldElement L = getRawYCoord();
                    return getCurve().createRawPoint(X, L.add(X).divide(scale).add(X.multiply(scale)), getRawZCoords(), this.withCompression);
                case 6:
                    ECFieldElement X2 = getRawXCoord();
                    ECFieldElement L2 = getRawYCoord();
                    ECFieldElement Z = getRawZCoords()[0];
                    ECFieldElement X22 = X2.multiply(scale.square());
                    ECFieldElement L22 = L2.add(X2).add(X22);
                    ECFieldElement Z2 = Z.multiply(scale);
                    return getCurve().createRawPoint(X22, L22, new ECFieldElement[]{Z2}, this.withCompression);
                default:
                    return ECPoint.super.scaleX(scale);
            }
        }

        /* Debug info: failed to restart local var, previous not found, register: 7 */
        public ECPoint scaleY(ECFieldElement scale) {
            if (isInfinity()) {
                return this;
            }
            switch (getCurveCoordinateSystem()) {
                case 5:
                case 6:
                    ECFieldElement X = getRawXCoord();
                    return getCurve().createRawPoint(X, getRawYCoord().add(X).multiply(scale).add(X), getRawZCoords(), this.withCompression);
                default:
                    return ECPoint.super.scaleY(scale);
            }
        }

        /* Debug info: failed to restart local var, previous not found, register: 1 */
        public ECPoint subtract(ECPoint b) {
            return b.isInfinity() ? this : add(b.negate());
        }

        public AbstractF2m tau() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coord = curve.getCoordinateSystem();
            ECFieldElement X1 = this.f6973x;
            switch (coord) {
                case 0:
                case 5:
                    return (AbstractF2m) curve.createRawPoint(X1.square(), this.f6974y.square(), this.withCompression);
                case 1:
                case 6:
                    ECFieldElement Y1 = this.f6974y;
                    ECFieldElement Z1 = this.f6975zs[0];
                    return (AbstractF2m) curve.createRawPoint(X1.square(), Y1.square(), new ECFieldElement[]{Z1.square()}, this.withCompression);
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
        }

        public AbstractF2m tauPow(int pow) {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coord = curve.getCoordinateSystem();
            ECFieldElement X1 = this.f6973x;
            switch (coord) {
                case 0:
                case 5:
                    return (AbstractF2m) curve.createRawPoint(X1.squarePow(pow), this.f6974y.squarePow(pow), this.withCompression);
                case 1:
                case 6:
                    ECFieldElement Y1 = this.f6974y;
                    ECFieldElement Z1 = this.f6975zs[0];
                    return (AbstractF2m) curve.createRawPoint(X1.squarePow(pow), Y1.squarePow(pow), new ECFieldElement[]{Z1.squarePow(pow)}, this.withCompression);
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
        }
    }

    /* renamed from: org.spongycastle.math.ec.ECPoint$AbstractFp */
    public static abstract class AbstractFp extends ECPoint {
        protected AbstractFp(ECCurve curve, ECFieldElement x, ECFieldElement y) {
            super(curve, x, y);
        }

        protected AbstractFp(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs) {
            super(curve, x, y, zs);
        }

        /* access modifiers changed from: protected */
        public boolean getCompressionYTilde() {
            return getAffineYCoord().testBitZero();
        }

        /* access modifiers changed from: protected */
        public boolean satisfiesCurveEquation() {
            ECFieldElement X = this.f6973x;
            ECFieldElement Y = this.f6974y;
            ECFieldElement A = this.curve.getA();
            ECFieldElement B = this.curve.getB();
            ECFieldElement lhs = Y.square();
            switch (getCurveCoordinateSystem()) {
                case 0:
                    break;
                case 1:
                    ECFieldElement Z = this.f6975zs[0];
                    if (!Z.isOne()) {
                        ECFieldElement Z2 = Z.square();
                        ECFieldElement Z3 = Z.multiply(Z2);
                        lhs = lhs.multiply(Z);
                        A = A.multiply(Z2);
                        B = B.multiply(Z3);
                        break;
                    }
                    break;
                case 2:
                case 3:
                case 4:
                    ECFieldElement Z4 = this.f6975zs[0];
                    if (!Z4.isOne()) {
                        ECFieldElement Z22 = Z4.square();
                        ECFieldElement Z42 = Z22.square();
                        ECFieldElement Z6 = Z22.multiply(Z42);
                        A = A.multiply(Z42);
                        B = B.multiply(Z6);
                        break;
                    }
                    break;
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
            return lhs.equals(X.square().add(A).multiply(X).add(B));
        }

        /* Debug info: failed to restart local var, previous not found, register: 1 */
        public ECPoint subtract(ECPoint b) {
            return b.isInfinity() ? this : add(b.negate());
        }
    }

    /* renamed from: org.spongycastle.math.ec.ECPoint$F2m */
    public static class F2m extends AbstractF2m {
        public F2m(ECCurve curve, ECFieldElement x, ECFieldElement y) {
            this(curve, x, y, false);
        }

        public F2m(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
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
            if (x != null) {
                org.spongycastle.math.p332ec.ECFieldElement.F2m.checkFieldElements(this.f6973x, this.f6974y);
                if (curve != null) {
                    org.spongycastle.math.p332ec.ECFieldElement.F2m.checkFieldElements(this.f6973x, this.curve.getA());
                }
            }
            this.withCompression = withCompression;
        }

        F2m(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
            super(curve, x, y, zs);
            this.withCompression = withCompression;
        }

        /* access modifiers changed from: protected */
        public ECPoint detach() {
            return new F2m(null, getAffineXCoord(), getAffineYCoord());
        }

        public ECFieldElement getYCoord() {
            int coord = getCurveCoordinateSystem();
            switch (coord) {
                case 5:
                case 6:
                    ECFieldElement X = this.f6973x;
                    ECFieldElement L = this.f6974y;
                    if (isInfinity() || X.isZero()) {
                        return L;
                    }
                    ECFieldElement Y = L.add(X).multiply(X);
                    if (6 != coord) {
                        return Y;
                    }
                    ECFieldElement Z = this.f6975zs[0];
                    if (!Z.isOne()) {
                        return Y.divide(Z);
                    }
                    return Y;
                default:
                    return this.f6974y;
            }
        }

        /* access modifiers changed from: protected */
        public boolean getCompressionYTilde() {
            ECFieldElement X = getRawXCoord();
            if (X.isZero()) {
                return false;
            }
            ECFieldElement Y = getRawYCoord();
            switch (getCurveCoordinateSystem()) {
                case 5:
                case 6:
                    if (Y.testBitZero() != X.testBitZero()) {
                        return true;
                    }
                    return false;
                default:
                    return Y.divide(X).testBitZero();
            }
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
            int coord = curve.getCoordinateSystem();
            ECFieldElement X1 = this.f6973x;
            ECFieldElement X2 = b.f6973x;
            switch (coord) {
                case 0:
                    ECFieldElement Y1 = this.f6974y;
                    ECFieldElement Y2 = b.f6974y;
                    ECFieldElement dx = X1.add(X2);
                    ECFieldElement dy = Y1.add(Y2);
                    if (!dx.isZero()) {
                        ECFieldElement L = dy.divide(dx);
                        ECFieldElement X32 = L.square().add(L).add(dx).add(curve.getA());
                        F2m f2m = new F2m(curve, X32, L.multiply(X1.add(X32)).add(X32).add(Y1), this.withCompression);
                        return f2m;
                    } else if (dy.isZero()) {
                        return twice();
                    } else {
                        return curve.getInfinity();
                    }
                case 1:
                    ECFieldElement Y12 = this.f6974y;
                    ECFieldElement Z1 = this.f6975zs[0];
                    ECFieldElement Y22 = b.f6974y;
                    ECFieldElement Z2 = b.f6975zs[0];
                    boolean Z2IsOne = Z2.isOne();
                    ECFieldElement U = Z1.multiply(Y22).add(Z2IsOne ? Y12 : Y12.multiply(Z2));
                    ECFieldElement V = Z1.multiply(X2).add(Z2IsOne ? X1 : X1.multiply(Z2));
                    if (!V.isZero()) {
                        ECFieldElement VSq = V.square();
                        ECFieldElement VCu = VSq.multiply(V);
                        ECFieldElement W = Z2IsOne ? Z1 : Z1.multiply(Z2);
                        ECFieldElement uv = U.add(V);
                        ECFieldElement A = uv.multiplyPlusProduct(U, VSq, curve.getA()).multiply(W).add(VCu);
                        return new F2m(curve, V.multiply(A), U.multiplyPlusProduct(X1, V, Y12).multiplyPlusProduct(Z2IsOne ? VSq : VSq.multiply(Z2), uv, A), new ECFieldElement[]{VCu.multiply(W)}, this.withCompression);
                    } else if (U.isZero()) {
                        return twice();
                    } else {
                        return curve.getInfinity();
                    }
                case 6:
                    if (!X1.isZero()) {
                        ECFieldElement L1 = this.f6974y;
                        ECFieldElement Z12 = this.f6975zs[0];
                        ECFieldElement L2 = b.f6974y;
                        ECFieldElement Z22 = b.f6975zs[0];
                        boolean Z1IsOne = Z12.isOne();
                        ECFieldElement U2 = X2;
                        ECFieldElement S2 = L2;
                        if (!Z1IsOne) {
                            U2 = U2.multiply(Z12);
                            S2 = S2.multiply(Z12);
                        }
                        boolean Z2IsOne2 = Z22.isOne();
                        ECFieldElement U1 = X1;
                        ECFieldElement S1 = L1;
                        if (!Z2IsOne2) {
                            U1 = U1.multiply(Z22);
                            S1 = S1.multiply(Z22);
                        }
                        ECFieldElement A2 = S1.add(S2);
                        ECFieldElement B = U1.add(U2);
                        if (!B.isZero()) {
                            if (X2.isZero()) {
                                ECPoint p = normalize();
                                ECFieldElement X12 = p.getXCoord();
                                ECFieldElement Y13 = p.getYCoord();
                                ECFieldElement L4 = Y13.add(L2).divide(X12);
                                X3 = L4.square().add(L4).add(X12).add(curve.getA());
                                if (X3.isZero()) {
                                    F2m f2m2 = new F2m(curve, X3, curve.getB().sqrt(), this.withCompression);
                                    return f2m2;
                                }
                                L3 = L4.multiply(X12.add(X3)).add(X3).add(Y13).divide(X3).add(X3);
                                Z3 = curve.fromBigInteger(ECConstants.ONE);
                            } else {
                                ECFieldElement B2 = B.square();
                                ECFieldElement AU1 = A2.multiply(U1);
                                ECFieldElement AU2 = A2.multiply(U2);
                                X3 = AU1.multiply(AU2);
                                if (X3.isZero()) {
                                    F2m f2m3 = new F2m(curve, X3, curve.getB().sqrt(), this.withCompression);
                                    return f2m3;
                                }
                                ECFieldElement ABZ2 = A2.multiply(B2);
                                if (!Z2IsOne2) {
                                    ABZ2 = ABZ2.multiply(Z22);
                                }
                                L3 = AU2.add(B2).squarePlusProduct(ABZ2, L1.add(Z12));
                                Z3 = ABZ2;
                                if (!Z1IsOne) {
                                    Z3 = Z3.multiply(Z12);
                                }
                            }
                            return new F2m(curve, X3, L3, new ECFieldElement[]{Z3}, this.withCompression);
                        } else if (A2.isZero()) {
                            return twice();
                        } else {
                            return curve.getInfinity();
                        }
                    } else if (X2.isZero()) {
                        return curve.getInfinity();
                    } else {
                        return b.add(this);
                    }
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
        }

        /* Debug info: failed to restart local var, previous not found, register: 36 */
        public ECPoint twice() {
            ECFieldElement Z3;
            ECFieldElement X1Z1;
            ECFieldElement L3;
            ECFieldElement t2;
            ECFieldElement Y1Z1;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement X1 = this.f6973x;
            if (X1.isZero()) {
                return curve.getInfinity();
            }
            switch (curve.getCoordinateSystem()) {
                case 0:
                    ECFieldElement L1 = this.f6974y.divide(X1).add(X1);
                    ECFieldElement X3 = L1.square().add(L1).add(curve.getA());
                    return new F2m(curve, X3, X1.squarePlusProduct(X3, L1.addOne()), this.withCompression);
                case 1:
                    ECFieldElement Y1 = this.f6974y;
                    ECFieldElement Z1 = this.f6975zs[0];
                    boolean Z1IsOne = Z1.isOne();
                    ECFieldElement X1Z12 = Z1IsOne ? X1 : X1.multiply(Z1);
                    if (Z1IsOne) {
                        Y1Z1 = Y1;
                    } else {
                        Y1Z1 = Y1.multiply(Z1);
                    }
                    ECFieldElement X1Sq = X1.square();
                    ECFieldElement S = X1Sq.add(Y1Z1);
                    ECFieldElement V = X1Z12;
                    ECFieldElement vSquared = V.square();
                    ECFieldElement sv = S.add(V);
                    ECFieldElement h = sv.multiplyPlusProduct(S, vSquared, curve.getA());
                    return new F2m(curve, V.multiply(h), X1Sq.square().multiplyPlusProduct(V, h, sv), new ECFieldElement[]{V.multiply(vSquared)}, this.withCompression);
                case 6:
                    ECFieldElement L12 = this.f6974y;
                    ECFieldElement Z12 = this.f6975zs[0];
                    boolean Z1IsOne2 = Z12.isOne();
                    ECFieldElement L1Z1 = Z1IsOne2 ? L12 : L12.multiply(Z12);
                    ECFieldElement Z1Sq = Z1IsOne2 ? Z12 : Z12.square();
                    ECFieldElement a = curve.getA();
                    ECFieldElement aZ1Sq = Z1IsOne2 ? a : a.multiply(Z1Sq);
                    ECFieldElement T = L12.square().add(L1Z1).add(aZ1Sq);
                    if (T.isZero()) {
                        return new F2m(curve, T, curve.getB().sqrt(), this.withCompression);
                    }
                    ECFieldElement X32 = T.square();
                    if (Z1IsOne2) {
                        Z3 = T;
                    } else {
                        Z3 = T.multiply(Z1Sq);
                    }
                    ECFieldElement b = curve.getB();
                    if (b.bitLength() < (curve.getFieldSize() >> 1)) {
                        ECFieldElement t1 = L12.add(X1).square();
                        if (b.isOne()) {
                            t2 = aZ1Sq.add(Z1Sq).square();
                        } else {
                            t2 = aZ1Sq.squarePlusProduct(b, Z1Sq.square());
                        }
                        L3 = t1.add(T).add(Z1Sq).multiply(t1).add(t2).add(X32);
                        if (a.isZero()) {
                            L3 = L3.add(Z3);
                        } else if (!a.isOne()) {
                            L3 = L3.add(a.addOne().multiply(Z3));
                        }
                    } else {
                        if (Z1IsOne2) {
                            X1Z1 = X1;
                        } else {
                            X1Z1 = X1.multiply(Z12);
                        }
                        L3 = X1Z1.squarePlusProduct(T, L1Z1).add(X32).add(Z3);
                    }
                    return new F2m(curve, X32, L3, new ECFieldElement[]{Z3}, this.withCompression);
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
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
            switch (curve.getCoordinateSystem()) {
                case 6:
                    ECFieldElement X2 = b.f6973x;
                    ECFieldElement Z2 = b.f6975zs[0];
                    if (X2.isZero() || !Z2.isOne()) {
                        return twice().add(b);
                    }
                    ECFieldElement L1 = this.f6974y;
                    ECFieldElement Z1 = this.f6975zs[0];
                    ECFieldElement L2 = b.f6974y;
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
                        F2m f2m = new F2m(curve, A, curve.getB().sqrt(), this.withCompression);
                        return f2m;
                    } else {
                        ECFieldElement X3 = A.square().multiply(X2Z1Sq);
                        ECFieldElement Z3 = A.multiply(B).multiply(Z1Sq);
                        return new F2m(curve, X3, A.add(B).square().multiplyPlusProduct(T, L2plus1, Z3), new ECFieldElement[]{Z3}, this.withCompression);
                    }
                default:
                    return twice().add(b);
            }
        }

        /* Debug info: failed to restart local var, previous not found, register: 9 */
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement X = this.f6973x;
            if (X.isZero()) {
                return this;
            }
            switch (getCurveCoordinateSystem()) {
                case 0:
                    return new F2m(this.curve, X, this.f6974y.add(X), this.withCompression);
                case 1:
                    ECFieldElement Y = this.f6974y;
                    ECFieldElement Z = this.f6975zs[0];
                    return new F2m(this.curve, X, Y.add(X), new ECFieldElement[]{Z}, this.withCompression);
                case 5:
                    return new F2m(this.curve, X, this.f6974y.addOne(), this.withCompression);
                case 6:
                    ECFieldElement L = this.f6974y;
                    ECFieldElement Z2 = this.f6975zs[0];
                    return new F2m(this.curve, X, L.add(Z2), new ECFieldElement[]{Z2}, this.withCompression);
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
        }
    }

    /* renamed from: org.spongycastle.math.ec.ECPoint$Fp */
    public static class C5399Fp extends AbstractFp {
        public C5399Fp(ECCurve curve, ECFieldElement x, ECFieldElement y) {
            this(curve, x, y, false);
        }

        public C5399Fp(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
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

        C5399Fp(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
            super(curve, x, y, zs);
            this.withCompression = withCompression;
        }

        /* access modifiers changed from: protected */
        public ECPoint detach() {
            return new C5399Fp(null, getAffineXCoord(), getAffineYCoord());
        }

        public ECFieldElement getZCoord(int index) {
            if (index == 1 && 4 == getCurveCoordinateSystem()) {
                return getJacobianModifiedW();
            }
            return super.getZCoord(index);
        }

        public ECPoint add(ECPoint b) {
            ECFieldElement U2;
            ECFieldElement S2;
            ECFieldElement U1;
            ECFieldElement S1;
            ECFieldElement X3;
            ECFieldElement Y3;
            ECFieldElement Z3;
            ECFieldElement v2;
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
            int coord = curve.getCoordinateSystem();
            ECFieldElement X1 = this.f6973x;
            ECFieldElement Y1 = this.f6974y;
            ECFieldElement X2 = b.f6973x;
            ECFieldElement Y2 = b.f6974y;
            switch (coord) {
                case 0:
                    ECFieldElement dx = X2.subtract(X1);
                    ECFieldElement dy = Y2.subtract(Y1);
                    if (!dx.isZero()) {
                        ECFieldElement gamma = dy.divide(dx);
                        ECFieldElement X32 = gamma.square().subtract(X1).subtract(X2);
                        return new C5399Fp(curve, X32, gamma.multiply(X1.subtract(X32)).subtract(Y1), this.withCompression);
                    } else if (dy.isZero()) {
                        return twice();
                    } else {
                        return curve.getInfinity();
                    }
                case 1:
                    ECFieldElement Z1 = this.f6975zs[0];
                    ECFieldElement Z2 = b.f6975zs[0];
                    boolean Z1IsOne = Z1.isOne();
                    boolean Z2IsOne = Z2.isOne();
                    ECFieldElement u1 = Z1IsOne ? Y2 : Y2.multiply(Z1);
                    ECFieldElement u2 = Z2IsOne ? Y1 : Y1.multiply(Z2);
                    ECFieldElement u = u1.subtract(u2);
                    ECFieldElement v1 = Z1IsOne ? X2 : X2.multiply(Z1);
                    if (Z2IsOne) {
                        v2 = X1;
                    } else {
                        v2 = X1.multiply(Z2);
                    }
                    ECFieldElement v = v1.subtract(v2);
                    if (!v.isZero()) {
                        ECFieldElement w = Z1IsOne ? Z2 : Z2IsOne ? Z1 : Z1.multiply(Z2);
                        ECFieldElement vSquared = v.square();
                        ECFieldElement vCubed = vSquared.multiply(v);
                        ECFieldElement vSquaredV2 = vSquared.multiply(v2);
                        ECFieldElement A = u.square().multiply(w).subtract(vCubed).subtract(two(vSquaredV2));
                        return new C5399Fp(curve, v.multiply(A), vSquaredV2.subtract(A).multiplyMinusProduct(u, u2, vCubed), new ECFieldElement[]{vCubed.multiply(w)}, this.withCompression);
                    } else if (u.isZero()) {
                        return twice();
                    } else {
                        return curve.getInfinity();
                    }
                case 2:
                case 4:
                    ECFieldElement Z12 = this.f6975zs[0];
                    ECFieldElement Z22 = b.f6975zs[0];
                    boolean Z1IsOne2 = Z12.isOne();
                    ECFieldElement Z3Squared = null;
                    if (Z1IsOne2 || !Z12.equals(Z22)) {
                        if (Z1IsOne2) {
                            ECFieldElement eCFieldElement = Z12;
                            U2 = X2;
                            S2 = Y2;
                        } else {
                            ECFieldElement Z1Squared = Z12.square();
                            U2 = Z1Squared.multiply(X2);
                            S2 = Z1Squared.multiply(Z12).multiply(Y2);
                        }
                        boolean Z2IsOne2 = Z22.isOne();
                        if (Z2IsOne2) {
                            ECFieldElement eCFieldElement2 = Z22;
                            U1 = X1;
                            S1 = Y1;
                        } else {
                            ECFieldElement Z2Squared = Z22.square();
                            U1 = Z2Squared.multiply(X1);
                            S1 = Z2Squared.multiply(Z22).multiply(Y1);
                        }
                        ECFieldElement H = U1.subtract(U2);
                        ECFieldElement R = S1.subtract(S2);
                        if (!H.isZero()) {
                            ECFieldElement HSquared = H.square();
                            ECFieldElement G = HSquared.multiply(H);
                            ECFieldElement V = HSquared.multiply(U1);
                            X3 = R.square().add(G).subtract(two(V));
                            Y3 = V.subtract(X3).multiplyMinusProduct(R, G, S1);
                            Z3 = H;
                            if (!Z1IsOne2) {
                                Z3 = Z3.multiply(Z12);
                            }
                            if (!Z2IsOne2) {
                                Z3 = Z3.multiply(Z22);
                            }
                            if (Z3 == H) {
                                Z3Squared = HSquared;
                            }
                        } else if (R.isZero()) {
                            return twice();
                        } else {
                            return curve.getInfinity();
                        }
                    } else {
                        ECFieldElement dx2 = X1.subtract(X2);
                        ECFieldElement dy2 = Y1.subtract(Y2);
                        if (!dx2.isZero()) {
                            ECFieldElement C = dx2.square();
                            ECFieldElement W1 = X1.multiply(C);
                            ECFieldElement W2 = X2.multiply(C);
                            ECFieldElement A1 = W1.subtract(W2).multiply(Y1);
                            X3 = dy2.square().subtract(W1).subtract(W2);
                            Y3 = W1.subtract(X3).multiply(dy2).subtract(A1);
                            Z3 = dx2.multiply(Z12);
                        } else if (dy2.isZero()) {
                            return twice();
                        } else {
                            return curve.getInfinity();
                        }
                    }
                    return new C5399Fp(curve, X3, Y3, coord == 4 ? new ECFieldElement[]{Z3, calculateJacobianModifiedW(Z3, Z3Squared)} : new ECFieldElement[]{Z3}, this.withCompression);
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
        }

        public ECPoint twice() {
            ECFieldElement M;
            ECFieldElement S;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement Y1 = this.f6974y;
            if (Y1.isZero()) {
                return curve.getInfinity();
            }
            int coord = curve.getCoordinateSystem();
            ECFieldElement X1 = this.f6973x;
            switch (coord) {
                case 0:
                    ECFieldElement gamma = three(X1.square()).add(getCurve().getA()).divide(two(Y1));
                    ECFieldElement X3 = gamma.square().subtract(two(X1));
                    return new C5399Fp(curve, X3, gamma.multiply(X1.subtract(X3)).subtract(Y1), this.withCompression);
                case 1:
                    ECFieldElement Z1 = this.f6975zs[0];
                    boolean Z1IsOne = Z1.isOne();
                    ECFieldElement w = curve.getA();
                    if (!w.isZero() && !Z1IsOne) {
                        w = w.multiply(Z1.square());
                    }
                    ECFieldElement w2 = w.add(three(X1.square()));
                    ECFieldElement s = Z1IsOne ? Y1 : Y1.multiply(Z1);
                    ECFieldElement t = Z1IsOne ? Y1.square() : s.multiply(Y1);
                    ECFieldElement _4B = four(X1.multiply(t));
                    ECFieldElement h = w2.square().subtract(two(_4B));
                    ECFieldElement _2s = two(s);
                    ECFieldElement X32 = h.multiply(_2s);
                    ECFieldElement _2t = two(t);
                    return new C5399Fp(curve, X32, _4B.subtract(h).multiply(w2).subtract(two(_2t.square())), new ECFieldElement[]{two(Z1IsOne ? two(_2t) : _2s.square()).multiply(s)}, this.withCompression);
                case 2:
                    ECFieldElement Z12 = this.f6975zs[0];
                    boolean Z1IsOne2 = Z12.isOne();
                    ECFieldElement Y1Squared = Y1.square();
                    ECFieldElement T = Y1Squared.square();
                    ECFieldElement a4 = curve.getA();
                    ECFieldElement a4Neg = a4.negate();
                    if (a4Neg.toBigInteger().equals(BigInteger.valueOf(3))) {
                        ECFieldElement Z1Squared = Z1IsOne2 ? Z12 : Z12.square();
                        M = three(X1.add(Z1Squared).multiply(X1.subtract(Z1Squared)));
                        S = four(Y1Squared.multiply(X1));
                    } else {
                        M = three(X1.square());
                        if (Z1IsOne2) {
                            M = M.add(a4);
                        } else if (!a4.isZero()) {
                            ECFieldElement Z1Pow4 = Z12.square().square();
                            if (a4Neg.bitLength() < a4.bitLength()) {
                                M = M.subtract(Z1Pow4.multiply(a4Neg));
                            } else {
                                M = M.add(Z1Pow4.multiply(a4));
                            }
                        }
                        S = four(X1.multiply(Y1Squared));
                    }
                    ECFieldElement X33 = M.square().subtract(two(S));
                    ECFieldElement Y3 = S.subtract(X33).multiply(M).subtract(eight(T));
                    ECFieldElement Z3 = two(Y1);
                    if (!Z1IsOne2) {
                        Z3 = Z3.multiply(Z12);
                    }
                    return new C5399Fp(curve, X33, Y3, new ECFieldElement[]{Z3}, this.withCompression);
                case 4:
                    return twiceJacobianModified(true);
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
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
            ECFieldElement Y1 = this.f6974y;
            if (Y1.isZero()) {
                return b;
            }
            ECCurve curve = getCurve();
            switch (curve.getCoordinateSystem()) {
                case 0:
                    ECFieldElement X1 = this.f6973x;
                    ECFieldElement X2 = b.f6973x;
                    ECFieldElement Y2 = b.f6974y;
                    ECFieldElement dx = X2.subtract(X1);
                    ECFieldElement dy = Y2.subtract(Y1);
                    if (dx.isZero()) {
                        return dy.isZero() ? threeTimes() : this;
                    }
                    ECFieldElement X = dx.square();
                    ECFieldElement d = X.multiply(two(X1).add(X2)).subtract(dy.square());
                    if (d.isZero()) {
                        return curve.getInfinity();
                    }
                    ECFieldElement I = d.multiply(dx).invert();
                    ECFieldElement L1 = d.multiply(I).multiply(dy);
                    ECFieldElement L2 = two(Y1).multiply(X).multiply(dx).multiply(I).subtract(L1);
                    ECFieldElement X4 = L2.subtract(L1).multiply(L1.add(L2)).add(X2);
                    C5399Fp fp = new C5399Fp(curve, X4, X1.subtract(X4).multiply(L2).subtract(Y1), this.withCompression);
                    return fp;
                case 4:
                    return twiceJacobianModified(false).add(b);
                default:
                    return twice().add(b);
            }
        }

        /* Debug info: failed to restart local var, previous not found, register: 19 */
        public ECPoint threeTimes() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement Y1 = this.f6974y;
            if (Y1.isZero()) {
                return this;
            }
            ECCurve curve = getCurve();
            switch (curve.getCoordinateSystem()) {
                case 0:
                    ECFieldElement X1 = this.f6973x;
                    ECFieldElement _2Y1 = two(Y1);
                    ECFieldElement X = _2Y1.square();
                    ECFieldElement Z = three(X1.square()).add(getCurve().getA());
                    ECFieldElement d = three(X1).multiply(X).subtract(Z.square());
                    if (d.isZero()) {
                        return getCurve().getInfinity();
                    }
                    ECFieldElement I = d.multiply(_2Y1).invert();
                    ECFieldElement L1 = d.multiply(I).multiply(Z);
                    ECFieldElement L2 = X.square().multiply(I).subtract(L1);
                    ECFieldElement X4 = L2.subtract(L1).multiply(L1.add(L2)).add(X1);
                    C5399Fp fp = new C5399Fp(curve, X4, X1.subtract(X4).multiply(L2).subtract(Y1), this.withCompression);
                    return fp;
                case 4:
                    return twiceJacobianModified(false).add(this);
                default:
                    return twice().add(this);
            }
        }

        public ECPoint timesPow2(int e) {
            if (e < 0) {
                throw new IllegalArgumentException("'e' cannot be negative");
            } else if (e == 0 || isInfinity()) {
                return this;
            } else {
                if (e == 1) {
                    return twice();
                }
                ECCurve curve = getCurve();
                ECFieldElement Y1 = this.f6974y;
                if (Y1.isZero()) {
                    return curve.getInfinity();
                }
                int coord = curve.getCoordinateSystem();
                ECFieldElement W1 = curve.getA();
                ECFieldElement X1 = this.f6973x;
                ECFieldElement Z1 = this.f6975zs.length < 1 ? curve.fromBigInteger(ECConstants.ONE) : this.f6975zs[0];
                if (!Z1.isOne()) {
                    switch (coord) {
                        case 0:
                            break;
                        case 1:
                            ECFieldElement Z1Sq = Z1.square();
                            X1 = X1.multiply(Z1);
                            Y1 = Y1.multiply(Z1Sq);
                            W1 = calculateJacobianModifiedW(Z1, Z1Sq);
                            break;
                        case 2:
                            W1 = calculateJacobianModifiedW(Z1, null);
                            break;
                        case 4:
                            W1 = getJacobianModifiedW();
                            break;
                        default:
                            throw new IllegalStateException("unsupported coordinate system");
                    }
                }
                for (int i = 0; i < e; i++) {
                    if (Y1.isZero()) {
                        return curve.getInfinity();
                    }
                    ECFieldElement M = three(X1.square());
                    ECFieldElement _2Y1 = two(Y1);
                    ECFieldElement _2Y1Squared = _2Y1.multiply(Y1);
                    ECFieldElement S = two(X1.multiply(_2Y1Squared));
                    ECFieldElement _8T = two(_2Y1Squared.square());
                    if (!W1.isZero()) {
                        M = M.add(W1);
                        W1 = two(_8T.multiply(W1));
                    }
                    X1 = M.square().subtract(two(S));
                    Y1 = M.multiply(S.subtract(X1)).subtract(_8T);
                    if (Z1.isOne()) {
                        Z1 = _2Y1;
                    } else {
                        Z1 = _2Y1.multiply(Z1);
                    }
                }
                switch (coord) {
                    case 0:
                        ECFieldElement zInv = Z1.invert();
                        ECFieldElement zInv2 = zInv.square();
                        return new C5399Fp(curve, X1.multiply(zInv2), Y1.multiply(zInv2.multiply(zInv)), this.withCompression);
                    case 1:
                        return new C5399Fp(curve, X1.multiply(Z1), Y1, new ECFieldElement[]{Z1.multiply(Z1.square())}, this.withCompression);
                    case 2:
                        return new C5399Fp(curve, X1, Y1, new ECFieldElement[]{Z1}, this.withCompression);
                    case 4:
                        return new C5399Fp(curve, X1, Y1, new ECFieldElement[]{Z1, W1}, this.withCompression);
                    default:
                        throw new IllegalStateException("unsupported coordinate system");
                }
            }
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

        /* Debug info: failed to restart local var, previous not found, register: 7 */
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            if (curve.getCoordinateSystem() != 0) {
                return new C5399Fp(curve, this.f6973x, this.f6974y.negate(), this.f6975zs, this.withCompression);
            }
            return new C5399Fp(curve, this.f6973x, this.f6974y.negate(), this.withCompression);
        }

        /* access modifiers changed from: protected */
        public ECFieldElement calculateJacobianModifiedW(ECFieldElement Z, ECFieldElement ZSquared) {
            ECFieldElement a4 = getCurve().getA();
            if (a4.isZero() || Z.isOne()) {
                return a4;
            }
            if (ZSquared == null) {
                ZSquared = Z.square();
            }
            ECFieldElement W = ZSquared.square();
            ECFieldElement a4Neg = a4.negate();
            if (a4Neg.bitLength() < a4.bitLength()) {
                return W.multiply(a4Neg).negate();
            }
            return W.multiply(a4);
        }

        /* access modifiers changed from: protected */
        public ECFieldElement getJacobianModifiedW() {
            ECFieldElement W = this.f6975zs[1];
            if (W != null) {
                return W;
            }
            ECFieldElement[] eCFieldElementArr = this.f6975zs;
            ECFieldElement W2 = calculateJacobianModifiedW(this.f6975zs[0], null);
            eCFieldElementArr[1] = W2;
            return W2;
        }

        /* access modifiers changed from: protected */
        public C5399Fp twiceJacobianModified(boolean calculateW) {
            ECFieldElement X1 = this.f6973x;
            ECFieldElement Y1 = this.f6974y;
            ECFieldElement Z1 = this.f6975zs[0];
            ECFieldElement W1 = getJacobianModifiedW();
            ECFieldElement M = three(X1.square()).add(W1);
            ECFieldElement _2Y1 = two(Y1);
            ECFieldElement _2Y1Squared = _2Y1.multiply(Y1);
            ECFieldElement S = two(X1.multiply(_2Y1Squared));
            ECFieldElement X3 = M.square().subtract(two(S));
            ECFieldElement _8T = two(_2Y1Squared.square());
            return new C5399Fp(getCurve(), X3, M.multiply(S.subtract(X3)).subtract(_8T), new ECFieldElement[]{Z1.isOne() ? _2Y1 : _2Y1.multiply(Z1), calculateW ? two(_8T.multiply(W1)) : null}, this.withCompression);
        }
    }

    public abstract ECPoint add(ECPoint eCPoint);

    /* access modifiers changed from: protected */
    public abstract ECPoint detach();

    /* access modifiers changed from: protected */
    public abstract boolean getCompressionYTilde();

    public abstract ECPoint negate();

    /* access modifiers changed from: protected */
    public abstract boolean satisfiesCurveEquation();

    public abstract ECPoint subtract(ECPoint eCPoint);

    public abstract ECPoint twice();

    protected static ECFieldElement[] getInitialZCoords(ECCurve curve2) {
        int coord = curve2 == null ? 0 : curve2.getCoordinateSystem();
        switch (coord) {
            case 0:
            case 5:
                return EMPTY_ZS;
            default:
                ECFieldElement one = curve2.fromBigInteger(ECConstants.ONE);
                switch (coord) {
                    case 1:
                    case 2:
                    case 6:
                        return new ECFieldElement[]{one};
                    case 3:
                        return new ECFieldElement[]{one, one, one};
                    case 4:
                        return new ECFieldElement[]{one, curve2.getA()};
                    default:
                        throw new IllegalArgumentException("unknown coordinate system");
                }
        }
    }

    protected ECPoint(ECCurve curve2, ECFieldElement x, ECFieldElement y) {
        this(curve2, x, y, getInitialZCoords(curve2));
    }

    protected ECPoint(ECCurve curve2, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs) {
        this.preCompTable = null;
        this.curve = curve2;
        this.f6973x = x;
        this.f6974y = y;
        this.f6975zs = zs;
    }

    /* access modifiers changed from: protected */
    public boolean satisfiesCofactor() {
        BigInteger h = this.curve.getCofactor();
        return h == null || h.equals(ECConstants.ONE) || !ECAlgorithms.referenceMultiply(this, h).isInfinity();
    }

    public final ECPoint getDetachedPoint() {
        return normalize().detach();
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    /* access modifiers changed from: protected */
    public int getCurveCoordinateSystem() {
        if (this.curve == null) {
            return 0;
        }
        return this.curve.getCoordinateSystem();
    }

    public ECFieldElement getX() {
        return normalize().getXCoord();
    }

    public ECFieldElement getY() {
        return normalize().getYCoord();
    }

    public ECFieldElement getAffineXCoord() {
        checkNormalized();
        return getXCoord();
    }

    public ECFieldElement getAffineYCoord() {
        checkNormalized();
        return getYCoord();
    }

    public ECFieldElement getXCoord() {
        return this.f6973x;
    }

    public ECFieldElement getYCoord() {
        return this.f6974y;
    }

    public ECFieldElement getZCoord(int index) {
        if (index < 0 || index >= this.f6975zs.length) {
            return null;
        }
        return this.f6975zs[index];
    }

    public ECFieldElement[] getZCoords() {
        int zsLen = this.f6975zs.length;
        if (zsLen == 0) {
            return EMPTY_ZS;
        }
        ECFieldElement[] copy = new ECFieldElement[zsLen];
        System.arraycopy(this.f6975zs, 0, copy, 0, zsLen);
        return copy;
    }

    public final ECFieldElement getRawXCoord() {
        return this.f6973x;
    }

    public final ECFieldElement getRawYCoord() {
        return this.f6974y;
    }

    /* access modifiers changed from: protected */
    public final ECFieldElement[] getRawZCoords() {
        return this.f6975zs;
    }

    /* access modifiers changed from: protected */
    public void checkNormalized() {
        if (!isNormalized()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    public boolean isNormalized() {
        int coord = getCurveCoordinateSystem();
        if (coord == 0 || coord == 5 || isInfinity() || this.f6975zs[0].isOne()) {
            return true;
        }
        return false;
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public ECPoint normalize() {
        if (isInfinity()) {
            return this;
        }
        switch (getCurveCoordinateSystem()) {
            case 0:
            case 5:
                return this;
            default:
                ECFieldElement Z1 = getZCoord(0);
                if (!Z1.isOne()) {
                    return normalize(Z1.invert());
                }
                return this;
        }
    }

    /* access modifiers changed from: 0000 */
    public ECPoint normalize(ECFieldElement zInv) {
        switch (getCurveCoordinateSystem()) {
            case 1:
            case 6:
                return createScaledPoint(zInv, zInv);
            case 2:
            case 3:
            case 4:
                ECFieldElement zInv2 = zInv.square();
                return createScaledPoint(zInv2, zInv2.multiply(zInv));
            default:
                throw new IllegalStateException("not a projective coordinate system");
        }
    }

    /* access modifiers changed from: protected */
    public ECPoint createScaledPoint(ECFieldElement sx, ECFieldElement sy) {
        return getCurve().createRawPoint(getRawXCoord().multiply(sx), getRawYCoord().multiply(sy), this.withCompression);
    }

    public boolean isInfinity() {
        return this.f6973x == null || this.f6974y == null || (this.f6975zs.length > 0 && this.f6975zs[0].isZero());
    }

    public boolean isCompressed() {
        return this.withCompression;
    }

    public boolean isValid() {
        if (isInfinity() || getCurve() == null) {
            return true;
        }
        if (!satisfiesCurveEquation()) {
            return false;
        }
        if (!satisfiesCofactor()) {
            return false;
        }
        return true;
    }

    /* Debug info: failed to restart local var, previous not found, register: 5 */
    public ECPoint scaleX(ECFieldElement scale) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord().multiply(scale), getRawYCoord(), getRawZCoords(), this.withCompression);
    }

    /* Debug info: failed to restart local var, previous not found, register: 5 */
    public ECPoint scaleY(ECFieldElement scale) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord(), getRawYCoord().multiply(scale), getRawZCoords(), this.withCompression);
    }

    public boolean equals(ECPoint other) {
        boolean n1;
        boolean z = true;
        if (other == null) {
            return false;
        }
        ECCurve c1 = getCurve();
        ECCurve c2 = other.getCurve();
        if (c1 == null) {
            n1 = true;
        } else {
            n1 = false;
        }
        boolean n2 = c2 == null;
        boolean i1 = isInfinity();
        boolean i2 = other.isInfinity();
        if (i1 || i2) {
            if (!i1 || !i2 || (!n1 && !n2 && !c1.equals(c2))) {
                z = false;
            }
            return z;
        }
        ECPoint p1 = this;
        ECPoint p2 = other;
        if (!n1 || !n2) {
            if (n1) {
                p2 = p2.normalize();
            } else if (n2) {
                p1 = p1.normalize();
            } else if (!c1.equals(c2)) {
                return false;
            } else {
                ECPoint[] points = {this, c1.importPoint(p2)};
                c1.normalizeAll(points);
                p1 = points[0];
                p2 = points[1];
            }
        }
        if (!p1.getXCoord().equals(p2.getXCoord()) || !p1.getYCoord().equals(p2.getYCoord())) {
            z = false;
        }
        return z;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ECPoint)) {
            return false;
        }
        return equals((ECPoint) other);
    }

    public int hashCode() {
        ECCurve c = getCurve();
        int hc = c == null ? 0 : c.hashCode() ^ -1;
        if (isInfinity()) {
            return hc;
        }
        ECPoint p = normalize();
        return (hc ^ (p.getXCoord().hashCode() * 17)) ^ (p.getYCoord().hashCode() * 257);
    }

    public String toString() {
        if (isInfinity()) {
            return "INF";
        }
        StringBuffer sb = new StringBuffer();
        sb.append('(');
        sb.append(getRawXCoord());
        sb.append(',');
        sb.append(getRawYCoord());
        for (ECFieldElement append : this.f6975zs) {
            sb.append(',');
            sb.append(append);
        }
        sb.append(')');
        return sb.toString();
    }

    public byte[] getEncoded() {
        return getEncoded(this.withCompression);
    }

    public byte[] getEncoded(boolean compressed) {
        if (isInfinity()) {
            return new byte[1];
        }
        ECPoint normed = normalize();
        byte[] X = normed.getXCoord().getEncoded();
        if (compressed) {
            byte[] PO = new byte[(X.length + 1)];
            PO[0] = (byte) (normed.getCompressionYTilde() ? 3 : 2);
            System.arraycopy(X, 0, PO, 1, X.length);
            return PO;
        }
        byte[] Y = normed.getYCoord().getEncoded();
        byte[] PO2 = new byte[(X.length + Y.length + 1)];
        PO2[0] = 4;
        System.arraycopy(X, 0, PO2, 1, X.length);
        System.arraycopy(Y, 0, PO2, X.length + 1, Y.length);
        return PO2;
    }

    public ECPoint timesPow2(int e) {
        if (e < 0) {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
        ECPoint p = this;
        while (true) {
            e--;
            if (e < 0) {
                return p;
            }
            p = p.twice();
        }
    }

    public ECPoint twicePlus(ECPoint b) {
        return twice().add(b);
    }

    public ECPoint threeTimes() {
        return twicePlus(this);
    }

    public ECPoint multiply(BigInteger k) {
        return getCurve().getMultiplier().multiply(this, k);
    }
}
