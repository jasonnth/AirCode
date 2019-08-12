package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Nat256;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecT239FieldElement */
public class SecT239FieldElement extends ECFieldElement {

    /* renamed from: x */
    protected long[] f7049x;

    public SecT239FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.bitLength() > 239) {
            throw new IllegalArgumentException("x value invalid for SecT239FieldElement");
        }
        this.f7049x = SecT239Field.fromBigInteger(x);
    }

    public SecT239FieldElement() {
        this.f7049x = Nat256.create64();
    }

    protected SecT239FieldElement(long[] x) {
        this.f7049x = x;
    }

    public boolean isOne() {
        return Nat256.isOne64(this.f7049x);
    }

    public boolean isZero() {
        return Nat256.isZero64(this.f7049x);
    }

    public boolean testBitZero() {
        return (this.f7049x[0] & 1) != 0;
    }

    public BigInteger toBigInteger() {
        return Nat256.toBigInteger64(this.f7049x);
    }

    public String getFieldName() {
        return "SecT239Field";
    }

    public int getFieldSize() {
        return 239;
    }

    public ECFieldElement add(ECFieldElement b) {
        long[] z = Nat256.create64();
        SecT239Field.add(this.f7049x, ((SecT239FieldElement) b).f7049x, z);
        return new SecT239FieldElement(z);
    }

    public ECFieldElement addOne() {
        long[] z = Nat256.create64();
        SecT239Field.addOne(this.f7049x, z);
        return new SecT239FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        return add(b);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        long[] z = Nat256.create64();
        SecT239Field.multiply(this.f7049x, ((SecT239FieldElement) b).f7049x, z);
        return new SecT239FieldElement(z);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        return multiplyPlusProduct(b, x, y);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7049x;
        long[] bx = ((SecT239FieldElement) b).f7049x;
        long[] xx = ((SecT239FieldElement) x).f7049x;
        long[] yx = ((SecT239FieldElement) y).f7049x;
        long[] tt = Nat256.createExt64();
        SecT239Field.multiplyAddToExt(ax, bx, tt);
        SecT239Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat256.create64();
        SecT239Field.reduce(tt, z);
        return new SecT239FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        return multiply(b.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] z = Nat256.create64();
        SecT239Field.square(this.f7049x, z);
        return new SecT239FieldElement(z);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement x, ECFieldElement y) {
        return squarePlusProduct(x, y);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7049x;
        long[] xx = ((SecT239FieldElement) x).f7049x;
        long[] yx = ((SecT239FieldElement) y).f7049x;
        long[] tt = Nat256.createExt64();
        SecT239Field.squareAddToExt(ax, tt);
        SecT239Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat256.create64();
        SecT239Field.reduce(tt, z);
        return new SecT239FieldElement(z);
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public ECFieldElement squarePow(int pow) {
        if (pow < 1) {
            return this;
        }
        long[] z = Nat256.create64();
        SecT239Field.squareN(this.f7049x, pow, z);
        return new SecT239FieldElement(z);
    }

    public ECFieldElement invert() {
        long[] z = Nat256.create64();
        SecT239Field.invert(this.f7049x, z);
        return new SecT239FieldElement(z);
    }

    public ECFieldElement sqrt() {
        long[] z = Nat256.create64();
        SecT239Field.sqrt(this.f7049x, z);
        return new SecT239FieldElement(z);
    }

    public int getRepresentation() {
        return 2;
    }

    public int getM() {
        return 239;
    }

    public int getK1() {
        return 158;
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
        if (!(other instanceof SecT239FieldElement)) {
            return false;
        }
        return Nat256.eq64(this.f7049x, ((SecT239FieldElement) other).f7049x);
    }

    public int hashCode() {
        return 23900158 ^ Arrays.hashCode(this.f7049x, 0, 4);
    }
}
