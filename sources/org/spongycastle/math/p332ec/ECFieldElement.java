package org.spongycastle.math.p332ec;

import java.math.BigInteger;
import java.util.Random;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

/* renamed from: org.spongycastle.math.ec.ECFieldElement */
public abstract class ECFieldElement implements ECConstants {

    /* renamed from: org.spongycastle.math.ec.ECFieldElement$F2m */
    public static class F2m extends ECFieldElement {
        public static final int GNB = 1;
        public static final int PPB = 3;
        public static final int TPB = 2;

        /* renamed from: ks */
        private int[] f6967ks;

        /* renamed from: m */
        private int f6968m;
        private int representation;

        /* renamed from: x */
        private LongArray f6969x;

        public F2m(int m, int k1, int k2, int k3, BigInteger x) {
            if (x == null || x.signum() < 0 || x.bitLength() > m) {
                throw new IllegalArgumentException("x value invalid in F2m field element");
            }
            if (k2 == 0 && k3 == 0) {
                this.representation = 2;
                this.f6967ks = new int[]{k1};
            } else if (k2 >= k3) {
                throw new IllegalArgumentException("k2 must be smaller than k3");
            } else if (k2 <= 0) {
                throw new IllegalArgumentException("k2 must be larger than 0");
            } else {
                this.representation = 3;
                this.f6967ks = new int[]{k1, k2, k3};
            }
            this.f6968m = m;
            this.f6969x = new LongArray(x);
        }

        public F2m(int m, int k, BigInteger x) {
            this(m, k, 0, 0, x);
        }

        private F2m(int m, int[] ks, LongArray x) {
            this.f6968m = m;
            this.representation = ks.length == 1 ? 2 : 3;
            this.f6967ks = ks;
            this.f6969x = x;
        }

        public int bitLength() {
            return this.f6969x.degree();
        }

        public boolean isOne() {
            return this.f6969x.isOne();
        }

        public boolean isZero() {
            return this.f6969x.isZero();
        }

        public boolean testBitZero() {
            return this.f6969x.testBitZero();
        }

        public BigInteger toBigInteger() {
            return this.f6969x.toBigInteger();
        }

        public String getFieldName() {
            return "F2m";
        }

        public int getFieldSize() {
            return this.f6968m;
        }

        public static void checkFieldElements(ECFieldElement a, ECFieldElement b) {
            if (!(a instanceof F2m) || !(b instanceof F2m)) {
                throw new IllegalArgumentException("Field elements are not both instances of ECFieldElement.F2m");
            }
            F2m aF2m = (F2m) a;
            F2m bF2m = (F2m) b;
            if (aF2m.representation != bF2m.representation) {
                throw new IllegalArgumentException("One of the F2m field elements has incorrect representation");
            } else if (aF2m.f6968m != bF2m.f6968m || !Arrays.areEqual(aF2m.f6967ks, bF2m.f6967ks)) {
                throw new IllegalArgumentException("Field elements are not elements of the same field F2m");
            }
        }

        public ECFieldElement add(ECFieldElement b) {
            LongArray iarrClone = (LongArray) this.f6969x.clone();
            iarrClone.addShiftedByWords(((F2m) b).f6969x, 0);
            return new F2m(this.f6968m, this.f6967ks, iarrClone);
        }

        public ECFieldElement addOne() {
            return new F2m(this.f6968m, this.f6967ks, this.f6969x.addOne());
        }

        public ECFieldElement subtract(ECFieldElement b) {
            return add(b);
        }

        public ECFieldElement multiply(ECFieldElement b) {
            return new F2m(this.f6968m, this.f6967ks, this.f6969x.modMultiply(((F2m) b).f6969x, this.f6968m, this.f6967ks));
        }

        public ECFieldElement multiplyMinusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
            return multiplyPlusProduct(b, x, y);
        }

        public ECFieldElement multiplyPlusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
            LongArray ax = this.f6969x;
            LongArray bx = ((F2m) b).f6969x;
            LongArray xx = ((F2m) x).f6969x;
            LongArray yx = ((F2m) y).f6969x;
            LongArray ab = ax.multiply(bx, this.f6968m, this.f6967ks);
            LongArray xy = xx.multiply(yx, this.f6968m, this.f6967ks);
            if (ab == ax || ab == bx) {
                ab = (LongArray) ab.clone();
            }
            ab.addShiftedByWords(xy, 0);
            ab.reduce(this.f6968m, this.f6967ks);
            return new F2m(this.f6968m, this.f6967ks, ab);
        }

        public ECFieldElement divide(ECFieldElement b) {
            return multiply(b.invert());
        }

        public ECFieldElement negate() {
            return this;
        }

        public ECFieldElement square() {
            return new F2m(this.f6968m, this.f6967ks, this.f6969x.modSquare(this.f6968m, this.f6967ks));
        }

        public ECFieldElement squareMinusProduct(ECFieldElement x, ECFieldElement y) {
            return squarePlusProduct(x, y);
        }

        public ECFieldElement squarePlusProduct(ECFieldElement x, ECFieldElement y) {
            LongArray ax = this.f6969x;
            LongArray xx = ((F2m) x).f6969x;
            LongArray yx = ((F2m) y).f6969x;
            LongArray aa = ax.square(this.f6968m, this.f6967ks);
            LongArray xy = xx.multiply(yx, this.f6968m, this.f6967ks);
            if (aa == ax) {
                aa = (LongArray) aa.clone();
            }
            aa.addShiftedByWords(xy, 0);
            aa.reduce(this.f6968m, this.f6967ks);
            return new F2m(this.f6968m, this.f6967ks, aa);
        }

        /* Debug info: failed to restart local var, previous not found, register: 6 */
        public ECFieldElement squarePow(int pow) {
            return pow < 1 ? this : new F2m(this.f6968m, this.f6967ks, this.f6969x.modSquareN(pow, this.f6968m, this.f6967ks));
        }

        public ECFieldElement invert() {
            return new F2m(this.f6968m, this.f6967ks, this.f6969x.modInverse(this.f6968m, this.f6967ks));
        }

        /* Debug info: failed to restart local var, previous not found, register: 1 */
        public ECFieldElement sqrt() {
            return (this.f6969x.isZero() || this.f6969x.isOne()) ? this : squarePow(this.f6968m - 1);
        }

        public int getRepresentation() {
            return this.representation;
        }

        public int getM() {
            return this.f6968m;
        }

        public int getK1() {
            return this.f6967ks[0];
        }

        public int getK2() {
            if (this.f6967ks.length >= 2) {
                return this.f6967ks[1];
            }
            return 0;
        }

        public int getK3() {
            if (this.f6967ks.length >= 3) {
                return this.f6967ks[2];
            }
            return 0;
        }

        public boolean equals(Object anObject) {
            if (anObject == this) {
                return true;
            }
            if (!(anObject instanceof F2m)) {
                return false;
            }
            F2m b = (F2m) anObject;
            if (this.f6968m != b.f6968m || this.representation != b.representation || !Arrays.areEqual(this.f6967ks, b.f6967ks) || !this.f6969x.equals(b.f6969x)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.f6969x.hashCode() ^ this.f6968m) ^ Arrays.hashCode(this.f6967ks);
        }
    }

    /* renamed from: org.spongycastle.math.ec.ECFieldElement$Fp */
    public static class C5398Fp extends ECFieldElement {

        /* renamed from: q */
        BigInteger f6970q;

        /* renamed from: r */
        BigInteger f6971r;

        /* renamed from: x */
        BigInteger f6972x;

        static BigInteger calculateResidue(BigInteger p) {
            int bitLength = p.bitLength();
            if (bitLength < 96 || p.shiftRight(bitLength - 64).longValue() != -1) {
                return null;
            }
            return ONE.shiftLeft(bitLength).subtract(p);
        }

        public C5398Fp(BigInteger q, BigInteger x) {
            this(q, calculateResidue(q), x);
        }

        C5398Fp(BigInteger q, BigInteger r, BigInteger x) {
            if (x == null || x.signum() < 0 || x.compareTo(q) >= 0) {
                throw new IllegalArgumentException("x value invalid in Fp field element");
            }
            this.f6970q = q;
            this.f6971r = r;
            this.f6972x = x;
        }

        public BigInteger toBigInteger() {
            return this.f6972x;
        }

        public String getFieldName() {
            return "Fp";
        }

        public int getFieldSize() {
            return this.f6970q.bitLength();
        }

        public BigInteger getQ() {
            return this.f6970q;
        }

        public ECFieldElement add(ECFieldElement b) {
            return new C5398Fp(this.f6970q, this.f6971r, modAdd(this.f6972x, b.toBigInteger()));
        }

        public ECFieldElement addOne() {
            BigInteger x2 = this.f6972x.add(ECConstants.ONE);
            if (x2.compareTo(this.f6970q) == 0) {
                x2 = ECConstants.ZERO;
            }
            return new C5398Fp(this.f6970q, this.f6971r, x2);
        }

        public ECFieldElement subtract(ECFieldElement b) {
            return new C5398Fp(this.f6970q, this.f6971r, modSubtract(this.f6972x, b.toBigInteger()));
        }

        public ECFieldElement multiply(ECFieldElement b) {
            return new C5398Fp(this.f6970q, this.f6971r, modMult(this.f6972x, b.toBigInteger()));
        }

        public ECFieldElement multiplyMinusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
            BigInteger ax = this.f6972x;
            BigInteger bx = b.toBigInteger();
            BigInteger xx = x.toBigInteger();
            BigInteger yx = y.toBigInteger();
            return new C5398Fp(this.f6970q, this.f6971r, modReduce(ax.multiply(bx).subtract(xx.multiply(yx))));
        }

        public ECFieldElement multiplyPlusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
            BigInteger ax = this.f6972x;
            BigInteger bx = b.toBigInteger();
            BigInteger xx = x.toBigInteger();
            BigInteger yx = y.toBigInteger();
            return new C5398Fp(this.f6970q, this.f6971r, modReduce(ax.multiply(bx).add(xx.multiply(yx))));
        }

        public ECFieldElement divide(ECFieldElement b) {
            return new C5398Fp(this.f6970q, this.f6971r, modMult(this.f6972x, modInverse(b.toBigInteger())));
        }

        /* Debug info: failed to restart local var, previous not found, register: 5 */
        public ECFieldElement negate() {
            return this.f6972x.signum() == 0 ? this : new C5398Fp(this.f6970q, this.f6971r, this.f6970q.subtract(this.f6972x));
        }

        public ECFieldElement square() {
            return new C5398Fp(this.f6970q, this.f6971r, modMult(this.f6972x, this.f6972x));
        }

        public ECFieldElement squareMinusProduct(ECFieldElement x, ECFieldElement y) {
            BigInteger ax = this.f6972x;
            BigInteger xx = x.toBigInteger();
            BigInteger yx = y.toBigInteger();
            return new C5398Fp(this.f6970q, this.f6971r, modReduce(ax.multiply(ax).subtract(xx.multiply(yx))));
        }

        public ECFieldElement squarePlusProduct(ECFieldElement x, ECFieldElement y) {
            BigInteger ax = this.f6972x;
            BigInteger xx = x.toBigInteger();
            BigInteger yx = y.toBigInteger();
            return new C5398Fp(this.f6970q, this.f6971r, modReduce(ax.multiply(ax).add(xx.multiply(yx))));
        }

        public ECFieldElement invert() {
            return new C5398Fp(this.f6970q, this.f6971r, modInverse(this.f6972x));
        }

        /* Debug info: failed to restart local var, previous not found, register: 25 */
        public ECFieldElement sqrt() {
            if (isZero() || isOne()) {
                return this;
            }
            if (!this.f6970q.testBit(0)) {
                throw new RuntimeException("not done yet");
            } else if (this.f6970q.testBit(1)) {
                BigInteger e = this.f6970q.shiftRight(2).add(ECConstants.ONE);
                return checkSqrt(new C5398Fp(this.f6970q, this.f6971r, this.f6972x.modPow(e, this.f6970q)));
            } else if (this.f6970q.testBit(2)) {
                BigInteger t1 = this.f6972x.modPow(this.f6970q.shiftRight(3), this.f6970q);
                BigInteger t2 = modMult(t1, this.f6972x);
                if (modMult(t2, t1).equals(ECConstants.ONE)) {
                    C5398Fp fp = new C5398Fp(this.f6970q, this.f6971r, t2);
                    return checkSqrt(fp);
                }
                BigInteger y = modMult(t2, ECConstants.TWO.modPow(this.f6970q.shiftRight(2), this.f6970q));
                C5398Fp fp2 = new C5398Fp(this.f6970q, this.f6971r, y);
                return checkSqrt(fp2);
            } else {
                BigInteger legendreExponent = this.f6970q.shiftRight(1);
                if (!this.f6972x.modPow(legendreExponent, this.f6970q).equals(ECConstants.ONE)) {
                    return null;
                }
                BigInteger X = this.f6972x;
                BigInteger fourX = modDouble(modDouble(X));
                BigInteger k = legendreExponent.add(ECConstants.ONE);
                BigInteger qMinusOne = this.f6970q.subtract(ECConstants.ONE);
                Random rand = new Random();
                while (true) {
                    BigInteger P = new BigInteger(this.f6970q.bitLength(), rand);
                    if (P.compareTo(this.f6970q) < 0) {
                        if (modReduce(P.multiply(P).subtract(fourX)).modPow(legendreExponent, this.f6970q).equals(qMinusOne)) {
                            BigInteger[] result = lucasSequence(P, X, k);
                            BigInteger U = result[0];
                            BigInteger V = result[1];
                            if (modMult(V, V).equals(fourX)) {
                                return new C5398Fp(this.f6970q, this.f6971r, modHalfAbs(V));
                            }
                            if (!U.equals(ECConstants.ONE) && !U.equals(qMinusOne)) {
                                return null;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }

        private ECFieldElement checkSqrt(ECFieldElement z) {
            if (z.square().equals(this)) {
                return z;
            }
            return null;
        }

        private BigInteger[] lucasSequence(BigInteger P, BigInteger Q, BigInteger k) {
            int n = k.bitLength();
            int s = k.getLowestSetBit();
            BigInteger Uh = ECConstants.ONE;
            BigInteger Vl = ECConstants.TWO;
            BigInteger Vh = P;
            BigInteger Ql = ECConstants.ONE;
            BigInteger Qh = ECConstants.ONE;
            for (int j = n - 1; j >= s + 1; j--) {
                Ql = modMult(Ql, Qh);
                if (k.testBit(j)) {
                    Qh = modMult(Ql, Q);
                    Uh = modMult(Uh, Vh);
                    Vl = modReduce(Vh.multiply(Vl).subtract(P.multiply(Ql)));
                    Vh = modReduce(Vh.multiply(Vh).subtract(Qh.shiftLeft(1)));
                } else {
                    Qh = Ql;
                    Uh = modReduce(Uh.multiply(Vl).subtract(Ql));
                    Vh = modReduce(Vh.multiply(Vl).subtract(P.multiply(Ql)));
                    Vl = modReduce(Vl.multiply(Vl).subtract(Ql.shiftLeft(1)));
                }
            }
            BigInteger Ql2 = modMult(Ql, Qh);
            BigInteger Qh2 = modMult(Ql2, Q);
            BigInteger Uh2 = modReduce(Uh.multiply(Vl).subtract(Ql2));
            BigInteger Vl2 = modReduce(Vh.multiply(Vl).subtract(P.multiply(Ql2)));
            BigInteger Ql3 = modMult(Ql2, Qh2);
            for (int j2 = 1; j2 <= s; j2++) {
                Uh2 = modMult(Uh2, Vl2);
                Vl2 = modReduce(Vl2.multiply(Vl2).subtract(Ql3.shiftLeft(1)));
                Ql3 = modMult(Ql3, Ql3);
            }
            return new BigInteger[]{Uh2, Vl2};
        }

        /* access modifiers changed from: protected */
        public BigInteger modAdd(BigInteger x1, BigInteger x2) {
            BigInteger x3 = x1.add(x2);
            if (x3.compareTo(this.f6970q) >= 0) {
                return x3.subtract(this.f6970q);
            }
            return x3;
        }

        /* access modifiers changed from: protected */
        public BigInteger modDouble(BigInteger x) {
            BigInteger _2x = x.shiftLeft(1);
            if (_2x.compareTo(this.f6970q) >= 0) {
                return _2x.subtract(this.f6970q);
            }
            return _2x;
        }

        /* access modifiers changed from: protected */
        public BigInteger modHalf(BigInteger x) {
            if (x.testBit(0)) {
                x = this.f6970q.add(x);
            }
            return x.shiftRight(1);
        }

        /* access modifiers changed from: protected */
        public BigInteger modHalfAbs(BigInteger x) {
            if (x.testBit(0)) {
                x = this.f6970q.subtract(x);
            }
            return x.shiftRight(1);
        }

        /* access modifiers changed from: protected */
        public BigInteger modInverse(BigInteger x) {
            int bits = getFieldSize();
            int len = (bits + 31) >> 5;
            int[] p = Nat.fromBigInteger(bits, this.f6970q);
            int[] n = Nat.fromBigInteger(bits, x);
            int[] z = Nat.create(len);
            Mod.invert(p, n, z);
            return Nat.toBigInteger(len, z);
        }

        /* access modifiers changed from: protected */
        public BigInteger modMult(BigInteger x1, BigInteger x2) {
            return modReduce(x1.multiply(x2));
        }

        /* access modifiers changed from: protected */
        public BigInteger modReduce(BigInteger x) {
            if (this.f6971r == null) {
                return x.mod(this.f6970q);
            }
            boolean negative = x.signum() < 0;
            if (negative) {
                x = x.abs();
            }
            int qLen = this.f6970q.bitLength();
            boolean rIsOne = this.f6971r.equals(ECConstants.ONE);
            while (x.bitLength() > qLen + 1) {
                BigInteger u = x.shiftRight(qLen);
                BigInteger v = x.subtract(u.shiftLeft(qLen));
                if (!rIsOne) {
                    u = u.multiply(this.f6971r);
                }
                x = u.add(v);
            }
            while (x.compareTo(this.f6970q) >= 0) {
                x = x.subtract(this.f6970q);
            }
            if (!negative || x.signum() == 0) {
                return x;
            }
            return this.f6970q.subtract(x);
        }

        /* access modifiers changed from: protected */
        public BigInteger modSubtract(BigInteger x1, BigInteger x2) {
            BigInteger x3 = x1.subtract(x2);
            if (x3.signum() < 0) {
                return x3.add(this.f6970q);
            }
            return x3;
        }

        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof C5398Fp)) {
                return false;
            }
            C5398Fp o = (C5398Fp) other;
            if (!this.f6970q.equals(o.f6970q) || !this.f6972x.equals(o.f6972x)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.f6970q.hashCode() ^ this.f6972x.hashCode();
        }
    }

    public abstract ECFieldElement add(ECFieldElement eCFieldElement);

    public abstract ECFieldElement addOne();

    public abstract ECFieldElement divide(ECFieldElement eCFieldElement);

    public abstract String getFieldName();

    public abstract int getFieldSize();

    public abstract ECFieldElement invert();

    public abstract ECFieldElement multiply(ECFieldElement eCFieldElement);

    public abstract ECFieldElement negate();

    public abstract ECFieldElement sqrt();

    public abstract ECFieldElement square();

    public abstract ECFieldElement subtract(ECFieldElement eCFieldElement);

    public abstract BigInteger toBigInteger();

    public int bitLength() {
        return toBigInteger().bitLength();
    }

    public boolean isOne() {
        return bitLength() == 1;
    }

    public boolean isZero() {
        return toBigInteger().signum() == 0;
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        return multiply(b).subtract(x.multiply(y));
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        return multiply(b).add(x.multiply(y));
    }

    public ECFieldElement squareMinusProduct(ECFieldElement x, ECFieldElement y) {
        return square().subtract(x.multiply(y));
    }

    public ECFieldElement squarePlusProduct(ECFieldElement x, ECFieldElement y) {
        return square().add(x.multiply(y));
    }

    public ECFieldElement squarePow(int pow) {
        ECFieldElement r = this;
        for (int i = 0; i < pow; i++) {
            r = r.square();
        }
        return r;
    }

    public boolean testBitZero() {
        return toBigInteger().testBit(0);
    }

    public String toString() {
        return toBigInteger().toString(16);
    }

    public byte[] getEncoded() {
        return BigIntegers.asUnsignedByteArray((getFieldSize() + 7) / 8, toBigInteger());
    }
}
