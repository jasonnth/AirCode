package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat448;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecT409FieldElement */
public class SecT409FieldElement extends ECFieldElement {

    /* renamed from: x */
    protected long[] f7051x;

    public SecT409FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.bitLength() > 409) {
            throw new IllegalArgumentException("x value invalid for SecT409FieldElement");
        }
        this.f7051x = SecT409Field.fromBigInteger(x);
    }

    public SecT409FieldElement() {
        this.f7051x = Nat448.create64();
    }

    protected SecT409FieldElement(long[] x) {
        this.f7051x = x;
    }

    public boolean isOne() {
        return Nat448.isOne64(this.f7051x);
    }

    public boolean isZero() {
        return Nat448.isZero64(this.f7051x);
    }

    public boolean testBitZero() {
        return (this.f7051x[0] & 1) != 0;
    }

    public BigInteger toBigInteger() {
        return Nat448.toBigInteger64(this.f7051x);
    }

    public String getFieldName() {
        return "SecT409Field";
    }

    public int getFieldSize() {
        return 409;
    }

    public ECFieldElement add(ECFieldElement b) {
        long[] z = Nat448.create64();
        SecT409Field.add(this.f7051x, ((SecT409FieldElement) b).f7051x, z);
        return new SecT409FieldElement(z);
    }

    public ECFieldElement addOne() {
        long[] z = Nat448.create64();
        SecT409Field.addOne(this.f7051x, z);
        return new SecT409FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        return add(b);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        long[] z = Nat448.create64();
        SecT409Field.multiply(this.f7051x, ((SecT409FieldElement) b).f7051x, z);
        return new SecT409FieldElement(z);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        return multiplyPlusProduct(b, x, y);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7051x;
        long[] bx = ((SecT409FieldElement) b).f7051x;
        long[] xx = ((SecT409FieldElement) x).f7051x;
        long[] yx = ((SecT409FieldElement) y).f7051x;
        long[] tt = Nat.create64(13);
        SecT409Field.multiplyAddToExt(ax, bx, tt);
        SecT409Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat448.create64();
        SecT409Field.reduce(tt, z);
        return new SecT409FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        return multiply(b.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] z = Nat448.create64();
        SecT409Field.square(this.f7051x, z);
        return new SecT409FieldElement(z);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement x, ECFieldElement y) {
        return squarePlusProduct(x, y);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7051x;
        long[] xx = ((SecT409FieldElement) x).f7051x;
        long[] yx = ((SecT409FieldElement) y).f7051x;
        long[] tt = Nat.create64(13);
        SecT409Field.squareAddToExt(ax, tt);
        SecT409Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat448.create64();
        SecT409Field.reduce(tt, z);
        return new SecT409FieldElement(z);
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public ECFieldElement squarePow(int pow) {
        if (pow < 1) {
            return this;
        }
        long[] z = Nat448.create64();
        SecT409Field.squareN(this.f7051x, pow, z);
        return new SecT409FieldElement(z);
    }

    public ECFieldElement invert() {
        long[] z = Nat448.create64();
        SecT409Field.invert(this.f7051x, z);
        return new SecT409FieldElement(z);
    }

    public ECFieldElement sqrt() {
        long[] z = Nat448.create64();
        SecT409Field.sqrt(this.f7051x, z);
        return new SecT409FieldElement(z);
    }

    public int getRepresentation() {
        return 2;
    }

    public int getM() {
        return 409;
    }

    public int getK1() {
        return 87;
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
        if (!(other instanceof SecT409FieldElement)) {
            return false;
        }
        return Nat448.eq64(this.f7051x, ((SecT409FieldElement) other).f7051x);
    }

    public int hashCode() {
        return 4090087 ^ Arrays.hashCode(this.f7051x, 0, 7);
    }
}
