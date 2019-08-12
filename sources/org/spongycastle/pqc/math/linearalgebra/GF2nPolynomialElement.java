package org.spongycastle.pqc.math.linearalgebra;

import java.math.BigInteger;
import java.util.Random;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public class GF2nPolynomialElement extends GF2nElement {
    private static final int[] bitMask = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, PKIFailureInfo.badSenderNonce, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, PKIFailureInfo.duplicateCertReq, 1073741824, Integer.MIN_VALUE, 0};
    private GF2Polynomial polynomial;

    public GF2nPolynomialElement(GF2nPolynomialField f, Random rand) {
        this.mField = f;
        this.mDegree = this.mField.getDegree();
        this.polynomial = new GF2Polynomial(this.mDegree);
        randomize(rand);
    }

    public GF2nPolynomialElement(GF2nPolynomialField f, GF2Polynomial bs) {
        this.mField = f;
        this.mDegree = this.mField.getDegree();
        this.polynomial = new GF2Polynomial(bs);
        this.polynomial.expandN(this.mDegree);
    }

    public GF2nPolynomialElement(GF2nPolynomialField f, byte[] os) {
        this.mField = f;
        this.mDegree = this.mField.getDegree();
        this.polynomial = new GF2Polynomial(this.mDegree, os);
        this.polynomial.expandN(this.mDegree);
    }

    public GF2nPolynomialElement(GF2nPolynomialField f, int[] is) {
        this.mField = f;
        this.mDegree = this.mField.getDegree();
        this.polynomial = new GF2Polynomial(this.mDegree, is);
        this.polynomial.expandN(f.mDegree);
    }

    public GF2nPolynomialElement(GF2nPolynomialElement other) {
        this.mField = other.mField;
        this.mDegree = other.mDegree;
        this.polynomial = new GF2Polynomial(other.polynomial);
    }

    public Object clone() {
        return new GF2nPolynomialElement(this);
    }

    /* access modifiers changed from: 0000 */
    public void assignZero() {
        this.polynomial.assignZero();
    }

    public static GF2nPolynomialElement ZERO(GF2nPolynomialField f) {
        return new GF2nPolynomialElement(f, new GF2Polynomial(f.getDegree()));
    }

    public static GF2nPolynomialElement ONE(GF2nPolynomialField f) {
        return new GF2nPolynomialElement(f, new GF2Polynomial(f.getDegree(), new int[]{1}));
    }

    /* access modifiers changed from: 0000 */
    public void assignOne() {
        this.polynomial.assignOne();
    }

    private void randomize(Random rand) {
        this.polynomial.expandN(this.mDegree);
        this.polynomial.randomize(rand);
    }

    public boolean isZero() {
        return this.polynomial.isZero();
    }

    public boolean isOne() {
        return this.polynomial.isOne();
    }

    public boolean equals(Object other) {
        if (other == null || !(other instanceof GF2nPolynomialElement)) {
            return false;
        }
        GF2nPolynomialElement otherElem = (GF2nPolynomialElement) other;
        if (this.mField == otherElem.mField || this.mField.getFieldPolynomial().equals(otherElem.mField.getFieldPolynomial())) {
            return this.polynomial.equals(otherElem.polynomial);
        }
        return false;
    }

    public int hashCode() {
        return this.mField.hashCode() + this.polynomial.hashCode();
    }

    private GF2Polynomial getGF2Polynomial() {
        return new GF2Polynomial(this.polynomial);
    }

    /* access modifiers changed from: 0000 */
    public boolean testBit(int index) {
        return this.polynomial.testBit(index);
    }

    public boolean testRightmostBit() {
        return this.polynomial.testBit(0);
    }

    public GFElement add(GFElement addend) throws RuntimeException {
        GF2nPolynomialElement result = new GF2nPolynomialElement(this);
        result.addToThis(addend);
        return result;
    }

    public void addToThis(GFElement addend) throws RuntimeException {
        if (!(addend instanceof GF2nPolynomialElement)) {
            throw new RuntimeException();
        } else if (!this.mField.equals(((GF2nPolynomialElement) addend).mField)) {
            throw new RuntimeException();
        } else {
            this.polynomial.addToThis(((GF2nPolynomialElement) addend).polynomial);
        }
    }

    public GF2nElement increase() {
        GF2nPolynomialElement result = new GF2nPolynomialElement(this);
        result.increaseThis();
        return result;
    }

    public void increaseThis() {
        this.polynomial.increaseThis();
    }

    public GFElement multiply(GFElement factor) throws RuntimeException {
        GF2nPolynomialElement result = new GF2nPolynomialElement(this);
        result.multiplyThisBy(factor);
        return result;
    }

    public void multiplyThisBy(GFElement factor) throws RuntimeException {
        if (!(factor instanceof GF2nPolynomialElement)) {
            throw new RuntimeException();
        } else if (!this.mField.equals(((GF2nPolynomialElement) factor).mField)) {
            throw new RuntimeException();
        } else if (equals(factor)) {
            squareThis();
        } else {
            this.polynomial = this.polynomial.multiply(((GF2nPolynomialElement) factor).polynomial);
            reduceThis();
        }
    }

    public GFElement invert() throws ArithmeticException {
        return invertMAIA();
    }

    public GF2nPolynomialElement invertEEA() throws ArithmeticException {
        if (isZero()) {
            throw new ArithmeticException();
        }
        GF2Polynomial b = new GF2Polynomial(this.mDegree + 32, "ONE");
        b.reduceN();
        GF2Polynomial c = new GF2Polynomial(this.mDegree + 32);
        c.reduceN();
        GF2Polynomial u = getGF2Polynomial();
        GF2Polynomial v = this.mField.getFieldPolynomial();
        u.reduceN();
        while (!u.isOne()) {
            u.reduceN();
            v.reduceN();
            int j = u.getLength() - v.getLength();
            if (j < 0) {
                GF2Polynomial h = u;
                u = v;
                v = h;
                GF2Polynomial h2 = b;
                b = c;
                c = h2;
                j = -j;
                c.reduceN();
            }
            u.shiftLeftAddThis(v, j);
            b.shiftLeftAddThis(c, j);
        }
        b.reduceN();
        return new GF2nPolynomialElement((GF2nPolynomialField) this.mField, b);
    }

    public GF2nPolynomialElement invertSquare() throws ArithmeticException {
        if (isZero()) {
            throw new ArithmeticException();
        }
        int b = this.mField.getDegree() - 1;
        GF2nPolynomialElement n = new GF2nPolynomialElement(this);
        n.polynomial.expandN((this.mDegree << 1) + 32);
        n.polynomial.reduceN();
        int k = 1;
        for (int i = IntegerFunctions.floorLog(b) - 1; i >= 0; i--) {
            GF2nPolynomialElement u = new GF2nPolynomialElement(n);
            for (int j = 1; j <= k; j++) {
                u.squareThisPreCalc();
            }
            n.multiplyThisBy(u);
            k <<= 1;
            if ((bitMask[i] & b) != 0) {
                n.squareThisPreCalc();
                n.multiplyThisBy(this);
                k++;
            }
        }
        n.squareThisPreCalc();
        return n;
    }

    public GF2nPolynomialElement invertMAIA() throws ArithmeticException {
        if (isZero()) {
            throw new ArithmeticException();
        }
        GF2Polynomial b = new GF2Polynomial(this.mDegree, "ONE");
        GF2Polynomial c = new GF2Polynomial(this.mDegree);
        GF2Polynomial u = getGF2Polynomial();
        GF2Polynomial v = this.mField.getFieldPolynomial();
        while (true) {
            if (!u.testBit(0)) {
                u.shiftRightThis();
                if (!b.testBit(0)) {
                    b.shiftRightThis();
                } else {
                    b.addToThis(this.mField.getFieldPolynomial());
                    b.shiftRightThis();
                }
            } else if (u.isOne()) {
                return new GF2nPolynomialElement((GF2nPolynomialField) this.mField, b);
            } else {
                u.reduceN();
                v.reduceN();
                if (u.getLength() < v.getLength()) {
                    GF2Polynomial h = u;
                    u = v;
                    v = h;
                    GF2Polynomial h2 = b;
                    b = c;
                    c = h2;
                }
                u.addToThis(v);
                b.addToThis(c);
            }
        }
    }

    public GF2nElement square() {
        return squarePreCalc();
    }

    public void squareThis() {
        squareThisPreCalc();
    }

    public GF2nPolynomialElement squareMatrix() {
        GF2nPolynomialElement result = new GF2nPolynomialElement(this);
        result.squareThisMatrix();
        result.reduceThis();
        return result;
    }

    public void squareThisMatrix() {
        GF2Polynomial result = new GF2Polynomial(this.mDegree);
        for (int i = 0; i < this.mDegree; i++) {
            if (this.polynomial.vectorMult(((GF2nPolynomialField) this.mField).squaringMatrix[(this.mDegree - i) - 1])) {
                result.setBit(i);
            }
        }
        this.polynomial = result;
    }

    public GF2nPolynomialElement squareBitwise() {
        GF2nPolynomialElement result = new GF2nPolynomialElement(this);
        result.squareThisBitwise();
        result.reduceThis();
        return result;
    }

    public void squareThisBitwise() {
        this.polynomial.squareThisBitwise();
        reduceThis();
    }

    public GF2nPolynomialElement squarePreCalc() {
        GF2nPolynomialElement result = new GF2nPolynomialElement(this);
        result.squareThisPreCalc();
        result.reduceThis();
        return result;
    }

    public void squareThisPreCalc() {
        this.polynomial.squareThisPreCalc();
        reduceThis();
    }

    public GF2nPolynomialElement power(int k) {
        if (k == 1) {
            return new GF2nPolynomialElement(this);
        }
        GF2nPolynomialElement result = ONE((GF2nPolynomialField) this.mField);
        if (k == 0) {
            return result;
        }
        GF2nPolynomialElement x = new GF2nPolynomialElement(this);
        x.polynomial.expandN((x.mDegree << 1) + 32);
        x.polynomial.reduceN();
        for (int i = 0; i < this.mDegree; i++) {
            if (((1 << i) & k) != 0) {
                result.multiplyThisBy(x);
            }
            x.square();
        }
        return result;
    }

    public GF2nElement squareRoot() {
        GF2nPolynomialElement result = new GF2nPolynomialElement(this);
        result.squareRootThis();
        return result;
    }

    public void squareRootThis() {
        this.polynomial.expandN((this.mDegree << 1) + 32);
        this.polynomial.reduceN();
        for (int i = 0; i < this.mField.getDegree() - 1; i++) {
            squareThis();
        }
    }

    public GF2nElement solveQuadraticEquation() throws RuntimeException {
        GF2nPolynomialElement z;
        GF2nPolynomialElement w;
        if (isZero()) {
            return ZERO((GF2nPolynomialField) this.mField);
        }
        if ((this.mDegree & 1) == 1) {
            return halfTrace();
        }
        do {
            GF2nPolynomialElement p = new GF2nPolynomialElement((GF2nPolynomialField) this.mField, new Random());
            z = ZERO((GF2nPolynomialField) this.mField);
            w = (GF2nPolynomialElement) p.clone();
            for (int i = 1; i < this.mDegree; i++) {
                z.squareThis();
                w.squareThis();
                z.addToThis(w.multiply(this));
                w.addToThis(p);
            }
        } while (w.isZero());
        if (equals(z.square().add(z))) {
            return z;
        }
        throw new RuntimeException();
    }

    public int trace() {
        GF2nPolynomialElement t = new GF2nPolynomialElement(this);
        for (int i = 1; i < this.mDegree; i++) {
            t.squareThis();
            t.addToThis(this);
        }
        if (t.isOne()) {
            return 1;
        }
        return 0;
    }

    private GF2nPolynomialElement halfTrace() throws RuntimeException {
        if ((this.mDegree & 1) == 0) {
            throw new RuntimeException();
        }
        GF2nPolynomialElement h = new GF2nPolynomialElement(this);
        for (int i = 1; i <= ((this.mDegree - 1) >> 1); i++) {
            h.squareThis();
            h.squareThis();
            h.addToThis(this);
        }
        return h;
    }

    private void reduceThis() {
        if (this.polynomial.getLength() > this.mDegree) {
            if (((GF2nPolynomialField) this.mField).isTrinomial()) {
                try {
                    int tc = ((GF2nPolynomialField) this.mField).getTc();
                    if (this.mDegree - tc <= 32 || this.polynomial.getLength() > (this.mDegree << 1)) {
                        reduceTrinomialBitwise(tc);
                    } else {
                        this.polynomial.reduceTrinomial(this.mDegree, tc);
                    }
                } catch (RuntimeException e) {
                    throw new RuntimeException("GF2nPolynomialElement.reduce: the field polynomial is not a trinomial");
                }
            } else if (((GF2nPolynomialField) this.mField).isPentanomial()) {
                try {
                    int[] pc = ((GF2nPolynomialField) this.mField).getPc();
                    if (this.mDegree - pc[2] <= 32 || this.polynomial.getLength() > (this.mDegree << 1)) {
                        reducePentanomialBitwise(pc);
                    } else {
                        this.polynomial.reducePentanomial(this.mDegree, pc);
                    }
                } catch (RuntimeException e2) {
                    throw new RuntimeException("GF2nPolynomialElement.reduce: the field polynomial is not a pentanomial");
                }
            } else {
                this.polynomial = this.polynomial.remainder(this.mField.getFieldPolynomial());
                this.polynomial.expandN(this.mDegree);
            }
        } else if (this.polynomial.getLength() < this.mDegree) {
            this.polynomial.expandN(this.mDegree);
        }
    }

    private void reduceTrinomialBitwise(int tc) {
        int k = this.mDegree - tc;
        for (int i = this.polynomial.getLength() - 1; i >= this.mDegree; i--) {
            if (this.polynomial.testBit(i)) {
                this.polynomial.xorBit(i);
                this.polynomial.xorBit(i - k);
                this.polynomial.xorBit(i - this.mDegree);
            }
        }
        this.polynomial.reduceN();
        this.polynomial.expandN(this.mDegree);
    }

    private void reducePentanomialBitwise(int[] pc) {
        int k = this.mDegree - pc[2];
        int l = this.mDegree - pc[1];
        int m = this.mDegree - pc[0];
        for (int i = this.polynomial.getLength() - 1; i >= this.mDegree; i--) {
            if (this.polynomial.testBit(i)) {
                this.polynomial.xorBit(i);
                this.polynomial.xorBit(i - k);
                this.polynomial.xorBit(i - l);
                this.polynomial.xorBit(i - m);
                this.polynomial.xorBit(i - this.mDegree);
            }
        }
        this.polynomial.reduceN();
        this.polynomial.expandN(this.mDegree);
    }

    public String toString() {
        return this.polynomial.toString(16);
    }

    public String toString(int radix) {
        return this.polynomial.toString(radix);
    }

    public byte[] toByteArray() {
        return this.polynomial.toByteArray();
    }

    public BigInteger toFlexiBigInt() {
        return this.polynomial.toFlexiBigInt();
    }
}
