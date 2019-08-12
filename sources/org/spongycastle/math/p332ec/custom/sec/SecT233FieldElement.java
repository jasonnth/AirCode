package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Nat256;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecT233FieldElement */
public class SecT233FieldElement extends ECFieldElement {

    /* renamed from: x */
    protected long[] f7048x;

    public SecT233FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.bitLength() > 233) {
            throw new IllegalArgumentException("x value invalid for SecT233FieldElement");
        }
        this.f7048x = SecT233Field.fromBigInteger(x);
    }

    public SecT233FieldElement() {
        this.f7048x = Nat256.create64();
    }

    protected SecT233FieldElement(long[] x) {
        this.f7048x = x;
    }

    public boolean isOne() {
        return Nat256.isOne64(this.f7048x);
    }

    public boolean isZero() {
        return Nat256.isZero64(this.f7048x);
    }

    public boolean testBitZero() {
        return (this.f7048x[0] & 1) != 0;
    }

    public BigInteger toBigInteger() {
        return Nat256.toBigInteger64(this.f7048x);
    }

    public String getFieldName() {
        return "SecT233Field";
    }

    public int getFieldSize() {
        return 233;
    }

    public ECFieldElement add(ECFieldElement b) {
        long[] z = Nat256.create64();
        SecT233Field.add(this.f7048x, ((SecT233FieldElement) b).f7048x, z);
        return new SecT233FieldElement(z);
    }

    public ECFieldElement addOne() {
        long[] z = Nat256.create64();
        SecT233Field.addOne(this.f7048x, z);
        return new SecT233FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        return add(b);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        long[] z = Nat256.create64();
        SecT233Field.multiply(this.f7048x, ((SecT233FieldElement) b).f7048x, z);
        return new SecT233FieldElement(z);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        return multiplyPlusProduct(b, x, y);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7048x;
        long[] bx = ((SecT233FieldElement) b).f7048x;
        long[] xx = ((SecT233FieldElement) x).f7048x;
        long[] yx = ((SecT233FieldElement) y).f7048x;
        long[] tt = Nat256.createExt64();
        SecT233Field.multiplyAddToExt(ax, bx, tt);
        SecT233Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat256.create64();
        SecT233Field.reduce(tt, z);
        return new SecT233FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        return multiply(b.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] z = Nat256.create64();
        SecT233Field.square(this.f7048x, z);
        return new SecT233FieldElement(z);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement x, ECFieldElement y) {
        return squarePlusProduct(x, y);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7048x;
        long[] xx = ((SecT233FieldElement) x).f7048x;
        long[] yx = ((SecT233FieldElement) y).f7048x;
        long[] tt = Nat256.createExt64();
        SecT233Field.squareAddToExt(ax, tt);
        SecT233Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat256.create64();
        SecT233Field.reduce(tt, z);
        return new SecT233FieldElement(z);
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public ECFieldElement squarePow(int pow) {
        if (pow < 1) {
            return this;
        }
        long[] z = Nat256.create64();
        SecT233Field.squareN(this.f7048x, pow, z);
        return new SecT233FieldElement(z);
    }

    public ECFieldElement invert() {
        long[] z = Nat256.create64();
        SecT233Field.invert(this.f7048x, z);
        return new SecT233FieldElement(z);
    }

    public ECFieldElement sqrt() {
        long[] z = Nat256.create64();
        SecT233Field.sqrt(this.f7048x, z);
        return new SecT233FieldElement(z);
    }

    public int getRepresentation() {
        return 2;
    }

    public int getM() {
        return 233;
    }

    public int getK1() {
        return 74;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecT233FieldElement)) {
            return false;
        }
        return Nat256.eq64(this.f7048x, ((SecT233FieldElement) other).f7048x);
    }

    public int hashCode() {
        return 2330074 ^ Arrays.hashCode(this.f7048x, 0, 4);
    }
}
