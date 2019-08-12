package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Nat576;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecT571FieldElement */
public class SecT571FieldElement extends ECFieldElement {

    /* renamed from: x */
    protected long[] f7053x;

    public SecT571FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.bitLength() > 571) {
            throw new IllegalArgumentException("x value invalid for SecT571FieldElement");
        }
        this.f7053x = SecT571Field.fromBigInteger(x);
    }

    public SecT571FieldElement() {
        this.f7053x = Nat576.create64();
    }

    protected SecT571FieldElement(long[] x) {
        this.f7053x = x;
    }

    public boolean isOne() {
        return Nat576.isOne64(this.f7053x);
    }

    public boolean isZero() {
        return Nat576.isZero64(this.f7053x);
    }

    public boolean testBitZero() {
        return (this.f7053x[0] & 1) != 0;
    }

    public BigInteger toBigInteger() {
        return Nat576.toBigInteger64(this.f7053x);
    }

    public String getFieldName() {
        return "SecT571Field";
    }

    public int getFieldSize() {
        return 571;
    }

    public ECFieldElement add(ECFieldElement b) {
        long[] z = Nat576.create64();
        SecT571Field.add(this.f7053x, ((SecT571FieldElement) b).f7053x, z);
        return new SecT571FieldElement(z);
    }

    public ECFieldElement addOne() {
        long[] z = Nat576.create64();
        SecT571Field.addOne(this.f7053x, z);
        return new SecT571FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        return add(b);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        long[] z = Nat576.create64();
        SecT571Field.multiply(this.f7053x, ((SecT571FieldElement) b).f7053x, z);
        return new SecT571FieldElement(z);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        return multiplyPlusProduct(b, x, y);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7053x;
        long[] bx = ((SecT571FieldElement) b).f7053x;
        long[] xx = ((SecT571FieldElement) x).f7053x;
        long[] yx = ((SecT571FieldElement) y).f7053x;
        long[] tt = Nat576.createExt64();
        SecT571Field.multiplyAddToExt(ax, bx, tt);
        SecT571Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat576.create64();
        SecT571Field.reduce(tt, z);
        return new SecT571FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        return multiply(b.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] z = Nat576.create64();
        SecT571Field.square(this.f7053x, z);
        return new SecT571FieldElement(z);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement x, ECFieldElement y) {
        return squarePlusProduct(x, y);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7053x;
        long[] xx = ((SecT571FieldElement) x).f7053x;
        long[] yx = ((SecT571FieldElement) y).f7053x;
        long[] tt = Nat576.createExt64();
        SecT571Field.squareAddToExt(ax, tt);
        SecT571Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat576.create64();
        SecT571Field.reduce(tt, z);
        return new SecT571FieldElement(z);
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public ECFieldElement squarePow(int pow) {
        if (pow < 1) {
            return this;
        }
        long[] z = Nat576.create64();
        SecT571Field.squareN(this.f7053x, pow, z);
        return new SecT571FieldElement(z);
    }

    public ECFieldElement invert() {
        long[] z = Nat576.create64();
        SecT571Field.invert(this.f7053x, z);
        return new SecT571FieldElement(z);
    }

    public ECFieldElement sqrt() {
        long[] z = Nat576.create64();
        SecT571Field.sqrt(this.f7053x, z);
        return new SecT571FieldElement(z);
    }

    public int getRepresentation() {
        return 3;
    }

    public int getM() {
        return 571;
    }

    public int getK1() {
        return 2;
    }

    public int getK2() {
        return 5;
    }

    public int getK3() {
        return 10;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecT571FieldElement)) {
            return false;
        }
        return Nat576.eq64(this.f7053x, ((SecT571FieldElement) other).f7053x);
    }

    public int hashCode() {
        return 5711052 ^ Arrays.hashCode(this.f7053x, 0, 9);
    }
}
