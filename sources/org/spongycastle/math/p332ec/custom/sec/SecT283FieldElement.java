package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat320;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecT283FieldElement */
public class SecT283FieldElement extends ECFieldElement {

    /* renamed from: x */
    protected long[] f7050x;

    public SecT283FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.bitLength() > 283) {
            throw new IllegalArgumentException("x value invalid for SecT283FieldElement");
        }
        this.f7050x = SecT283Field.fromBigInteger(x);
    }

    public SecT283FieldElement() {
        this.f7050x = Nat320.create64();
    }

    protected SecT283FieldElement(long[] x) {
        this.f7050x = x;
    }

    public boolean isOne() {
        return Nat320.isOne64(this.f7050x);
    }

    public boolean isZero() {
        return Nat320.isZero64(this.f7050x);
    }

    public boolean testBitZero() {
        return (this.f7050x[0] & 1) != 0;
    }

    public BigInteger toBigInteger() {
        return Nat320.toBigInteger64(this.f7050x);
    }

    public String getFieldName() {
        return "SecT283Field";
    }

    public int getFieldSize() {
        return 283;
    }

    public ECFieldElement add(ECFieldElement b) {
        long[] z = Nat320.create64();
        SecT283Field.add(this.f7050x, ((SecT283FieldElement) b).f7050x, z);
        return new SecT283FieldElement(z);
    }

    public ECFieldElement addOne() {
        long[] z = Nat320.create64();
        SecT283Field.addOne(this.f7050x, z);
        return new SecT283FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        return add(b);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        long[] z = Nat320.create64();
        SecT283Field.multiply(this.f7050x, ((SecT283FieldElement) b).f7050x, z);
        return new SecT283FieldElement(z);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        return multiplyPlusProduct(b, x, y);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7050x;
        long[] bx = ((SecT283FieldElement) b).f7050x;
        long[] xx = ((SecT283FieldElement) x).f7050x;
        long[] yx = ((SecT283FieldElement) y).f7050x;
        long[] tt = Nat.create64(9);
        SecT283Field.multiplyAddToExt(ax, bx, tt);
        SecT283Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat320.create64();
        SecT283Field.reduce(tt, z);
        return new SecT283FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        return multiply(b.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] z = Nat320.create64();
        SecT283Field.square(this.f7050x, z);
        return new SecT283FieldElement(z);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement x, ECFieldElement y) {
        return squarePlusProduct(x, y);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7050x;
        long[] xx = ((SecT283FieldElement) x).f7050x;
        long[] yx = ((SecT283FieldElement) y).f7050x;
        long[] tt = Nat.create64(9);
        SecT283Field.squareAddToExt(ax, tt);
        SecT283Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat320.create64();
        SecT283Field.reduce(tt, z);
        return new SecT283FieldElement(z);
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public ECFieldElement squarePow(int pow) {
        if (pow < 1) {
            return this;
        }
        long[] z = Nat320.create64();
        SecT283Field.squareN(this.f7050x, pow, z);
        return new SecT283FieldElement(z);
    }

    public ECFieldElement invert() {
        long[] z = Nat320.create64();
        SecT283Field.invert(this.f7050x, z);
        return new SecT283FieldElement(z);
    }

    public ECFieldElement sqrt() {
        long[] z = Nat320.create64();
        SecT283Field.sqrt(this.f7050x, z);
        return new SecT283FieldElement(z);
    }

    public int getRepresentation() {
        return 3;
    }

    public int getM() {
        return 283;
    }

    public int getK1() {
        return 5;
    }

    public int getK2() {
        return 7;
    }

    public int getK3() {
        return 12;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecT283FieldElement)) {
            return false;
        }
        return Nat320.eq64(this.f7050x, ((SecT283FieldElement) other).f7050x);
    }

    public int hashCode() {
        return 2831275 ^ Arrays.hashCode(this.f7050x, 0, 5);
    }
}
