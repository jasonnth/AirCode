package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.jmrtd.cbeff.ISO781611;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat192;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecT131FieldElement */
public class SecT131FieldElement extends ECFieldElement {

    /* renamed from: x */
    protected long[] f7045x;

    public SecT131FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.bitLength() > 131) {
            throw new IllegalArgumentException("x value invalid for SecT131FieldElement");
        }
        this.f7045x = SecT131Field.fromBigInteger(x);
    }

    public SecT131FieldElement() {
        this.f7045x = Nat192.create64();
    }

    protected SecT131FieldElement(long[] x) {
        this.f7045x = x;
    }

    public boolean isOne() {
        return Nat192.isOne64(this.f7045x);
    }

    public boolean isZero() {
        return Nat192.isZero64(this.f7045x);
    }

    public boolean testBitZero() {
        return (this.f7045x[0] & 1) != 0;
    }

    public BigInteger toBigInteger() {
        return Nat192.toBigInteger64(this.f7045x);
    }

    public String getFieldName() {
        return "SecT131Field";
    }

    public int getFieldSize() {
        return ISO781611.CREATION_DATE_AND_TIME_TAG;
    }

    public ECFieldElement add(ECFieldElement b) {
        long[] z = Nat192.create64();
        SecT131Field.add(this.f7045x, ((SecT131FieldElement) b).f7045x, z);
        return new SecT131FieldElement(z);
    }

    public ECFieldElement addOne() {
        long[] z = Nat192.create64();
        SecT131Field.addOne(this.f7045x, z);
        return new SecT131FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        return add(b);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        long[] z = Nat192.create64();
        SecT131Field.multiply(this.f7045x, ((SecT131FieldElement) b).f7045x, z);
        return new SecT131FieldElement(z);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        return multiplyPlusProduct(b, x, y);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7045x;
        long[] bx = ((SecT131FieldElement) b).f7045x;
        long[] xx = ((SecT131FieldElement) x).f7045x;
        long[] yx = ((SecT131FieldElement) y).f7045x;
        long[] tt = Nat.create64(5);
        SecT131Field.multiplyAddToExt(ax, bx, tt);
        SecT131Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat192.create64();
        SecT131Field.reduce(tt, z);
        return new SecT131FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        return multiply(b.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] z = Nat192.create64();
        SecT131Field.square(this.f7045x, z);
        return new SecT131FieldElement(z);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement x, ECFieldElement y) {
        return squarePlusProduct(x, y);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7045x;
        long[] xx = ((SecT131FieldElement) x).f7045x;
        long[] yx = ((SecT131FieldElement) y).f7045x;
        long[] tt = Nat.create64(5);
        SecT131Field.squareAddToExt(ax, tt);
        SecT131Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat192.create64();
        SecT131Field.reduce(tt, z);
        return new SecT131FieldElement(z);
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public ECFieldElement squarePow(int pow) {
        if (pow < 1) {
            return this;
        }
        long[] z = Nat192.create64();
        SecT131Field.squareN(this.f7045x, pow, z);
        return new SecT131FieldElement(z);
    }

    public ECFieldElement invert() {
        long[] z = Nat192.create64();
        SecT131Field.invert(this.f7045x, z);
        return new SecT131FieldElement(z);
    }

    public ECFieldElement sqrt() {
        long[] z = Nat192.create64();
        SecT131Field.sqrt(this.f7045x, z);
        return new SecT131FieldElement(z);
    }

    public int getRepresentation() {
        return 3;
    }

    public int getM() {
        return ISO781611.CREATION_DATE_AND_TIME_TAG;
    }

    public int getK1() {
        return 2;
    }

    public int getK2() {
        return 3;
    }

    public int getK3() {
        return 8;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecT131FieldElement)) {
            return false;
        }
        return Nat192.eq64(this.f7045x, ((SecT131FieldElement) other).f7045x);
    }

    public int hashCode() {
        return 131832 ^ Arrays.hashCode(this.f7045x, 0, 3);
    }
}
