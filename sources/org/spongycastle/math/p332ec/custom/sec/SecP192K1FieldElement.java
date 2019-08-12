package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat192;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP192K1FieldElement */
public class SecP192K1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f7005Q = SecP192K1Curve.f7002q;

    /* renamed from: x */
    protected int[] f7006x;

    public SecP192K1FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.compareTo(f7005Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192K1FieldElement");
        }
        this.f7006x = SecP192K1Field.fromBigInteger(x);
    }

    public SecP192K1FieldElement() {
        this.f7006x = Nat192.create();
    }

    protected SecP192K1FieldElement(int[] x) {
        this.f7006x = x;
    }

    public boolean isZero() {
        return Nat192.isZero(this.f7006x);
    }

    public boolean isOne() {
        return Nat192.isOne(this.f7006x);
    }

    public boolean testBitZero() {
        return Nat192.getBit(this.f7006x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat192.toBigInteger(this.f7006x);
    }

    public String getFieldName() {
        return "SecP192K1Field";
    }

    public int getFieldSize() {
        return f7005Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement b) {
        int[] z = Nat192.create();
        SecP192K1Field.add(this.f7006x, ((SecP192K1FieldElement) b).f7006x, z);
        return new SecP192K1FieldElement(z);
    }

    public ECFieldElement addOne() {
        int[] z = Nat192.create();
        SecP192K1Field.addOne(this.f7006x, z);
        return new SecP192K1FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        int[] z = Nat192.create();
        SecP192K1Field.subtract(this.f7006x, ((SecP192K1FieldElement) b).f7006x, z);
        return new SecP192K1FieldElement(z);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        int[] z = Nat192.create();
        SecP192K1Field.multiply(this.f7006x, ((SecP192K1FieldElement) b).f7006x, z);
        return new SecP192K1FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        int[] z = Nat192.create();
        Mod.invert(SecP192K1Field.f7003P, ((SecP192K1FieldElement) b).f7006x, z);
        SecP192K1Field.multiply(z, this.f7006x, z);
        return new SecP192K1FieldElement(z);
    }

    public ECFieldElement negate() {
        int[] z = Nat192.create();
        SecP192K1Field.negate(this.f7006x, z);
        return new SecP192K1FieldElement(z);
    }

    public ECFieldElement square() {
        int[] z = Nat192.create();
        SecP192K1Field.square(this.f7006x, z);
        return new SecP192K1FieldElement(z);
    }

    public ECFieldElement invert() {
        int[] z = Nat192.create();
        Mod.invert(SecP192K1Field.f7003P, this.f7006x, z);
        return new SecP192K1FieldElement(z);
    }

    public ECFieldElement sqrt() {
        int[] x1 = this.f7006x;
        if (Nat192.isZero(x1) || Nat192.isOne(x1)) {
            return this;
        }
        int[] x2 = Nat192.create();
        SecP192K1Field.square(x1, x2);
        SecP192K1Field.multiply(x2, x1, x2);
        int[] x3 = Nat192.create();
        SecP192K1Field.square(x2, x3);
        SecP192K1Field.multiply(x3, x1, x3);
        int[] x6 = Nat192.create();
        SecP192K1Field.squareN(x3, 3, x6);
        SecP192K1Field.multiply(x6, x3, x6);
        int[] x8 = x6;
        SecP192K1Field.squareN(x6, 2, x8);
        SecP192K1Field.multiply(x8, x2, x8);
        int[] x16 = x2;
        SecP192K1Field.squareN(x8, 8, x16);
        SecP192K1Field.multiply(x16, x8, x16);
        int[] x19 = x8;
        SecP192K1Field.squareN(x16, 3, x19);
        SecP192K1Field.multiply(x19, x3, x19);
        int[] x35 = Nat192.create();
        SecP192K1Field.squareN(x19, 16, x35);
        SecP192K1Field.multiply(x35, x16, x35);
        int[] x70 = x16;
        SecP192K1Field.squareN(x35, 35, x70);
        SecP192K1Field.multiply(x70, x35, x70);
        int[] x140 = x35;
        SecP192K1Field.squareN(x70, 70, x140);
        SecP192K1Field.multiply(x140, x70, x140);
        int[] x159 = x70;
        SecP192K1Field.squareN(x140, 19, x159);
        SecP192K1Field.multiply(x159, x19, x159);
        int[] t1 = x159;
        SecP192K1Field.squareN(t1, 20, t1);
        SecP192K1Field.multiply(t1, x19, t1);
        SecP192K1Field.squareN(t1, 4, t1);
        SecP192K1Field.multiply(t1, x3, t1);
        SecP192K1Field.squareN(t1, 6, t1);
        SecP192K1Field.multiply(t1, x3, t1);
        SecP192K1Field.square(t1, t1);
        int[] t2 = x3;
        SecP192K1Field.square(t1, t2);
        if (Nat192.m4044eq(x1, t2)) {
            return new SecP192K1FieldElement(t1);
        }
        return null;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecP192K1FieldElement)) {
            return false;
        }
        return Nat192.m4044eq(this.f7006x, ((SecP192K1FieldElement) other).f7006x);
    }

    public int hashCode() {
        return f7005Q.hashCode() ^ Arrays.hashCode(this.f7006x, 0, 6);
    }
}
