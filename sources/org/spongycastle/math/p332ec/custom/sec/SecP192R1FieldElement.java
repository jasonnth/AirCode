package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat192;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP192R1FieldElement */
public class SecP192R1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f7011Q = SecP192R1Curve.f7007q;

    /* renamed from: x */
    protected int[] f7012x;

    public SecP192R1FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.compareTo(f7011Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192R1FieldElement");
        }
        this.f7012x = SecP192R1Field.fromBigInteger(x);
    }

    public SecP192R1FieldElement() {
        this.f7012x = Nat192.create();
    }

    protected SecP192R1FieldElement(int[] x) {
        this.f7012x = x;
    }

    public boolean isZero() {
        return Nat192.isZero(this.f7012x);
    }

    public boolean isOne() {
        return Nat192.isOne(this.f7012x);
    }

    public boolean testBitZero() {
        return Nat192.getBit(this.f7012x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat192.toBigInteger(this.f7012x);
    }

    public String getFieldName() {
        return "SecP192R1Field";
    }

    public int getFieldSize() {
        return f7011Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement b) {
        int[] z = Nat192.create();
        SecP192R1Field.add(this.f7012x, ((SecP192R1FieldElement) b).f7012x, z);
        return new SecP192R1FieldElement(z);
    }

    public ECFieldElement addOne() {
        int[] z = Nat192.create();
        SecP192R1Field.addOne(this.f7012x, z);
        return new SecP192R1FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        int[] z = Nat192.create();
        SecP192R1Field.subtract(this.f7012x, ((SecP192R1FieldElement) b).f7012x, z);
        return new SecP192R1FieldElement(z);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        int[] z = Nat192.create();
        SecP192R1Field.multiply(this.f7012x, ((SecP192R1FieldElement) b).f7012x, z);
        return new SecP192R1FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        int[] z = Nat192.create();
        Mod.invert(SecP192R1Field.f7009P, ((SecP192R1FieldElement) b).f7012x, z);
        SecP192R1Field.multiply(z, this.f7012x, z);
        return new SecP192R1FieldElement(z);
    }

    public ECFieldElement negate() {
        int[] z = Nat192.create();
        SecP192R1Field.negate(this.f7012x, z);
        return new SecP192R1FieldElement(z);
    }

    public ECFieldElement square() {
        int[] z = Nat192.create();
        SecP192R1Field.square(this.f7012x, z);
        return new SecP192R1FieldElement(z);
    }

    public ECFieldElement invert() {
        int[] z = Nat192.create();
        Mod.invert(SecP192R1Field.f7009P, this.f7012x, z);
        return new SecP192R1FieldElement(z);
    }

    public ECFieldElement sqrt() {
        int[] x1 = this.f7012x;
        if (Nat192.isZero(x1) || Nat192.isOne(x1)) {
            return this;
        }
        int[] t1 = Nat192.create();
        int[] t2 = Nat192.create();
        SecP192R1Field.square(x1, t1);
        SecP192R1Field.multiply(t1, x1, t1);
        SecP192R1Field.squareN(t1, 2, t2);
        SecP192R1Field.multiply(t2, t1, t2);
        SecP192R1Field.squareN(t2, 4, t1);
        SecP192R1Field.multiply(t1, t2, t1);
        SecP192R1Field.squareN(t1, 8, t2);
        SecP192R1Field.multiply(t2, t1, t2);
        SecP192R1Field.squareN(t2, 16, t1);
        SecP192R1Field.multiply(t1, t2, t1);
        SecP192R1Field.squareN(t1, 32, t2);
        SecP192R1Field.multiply(t2, t1, t2);
        SecP192R1Field.squareN(t2, 64, t1);
        SecP192R1Field.multiply(t1, t2, t1);
        SecP192R1Field.squareN(t1, 62, t1);
        SecP192R1Field.square(t1, t2);
        if (Nat192.m4044eq(x1, t2)) {
            return new SecP192R1FieldElement(t1);
        }
        return null;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecP192R1FieldElement)) {
            return false;
        }
        return Nat192.m4044eq(this.f7012x, ((SecP192R1FieldElement) other).f7012x);
    }

    public int hashCode() {
        return f7011Q.hashCode() ^ Arrays.hashCode(this.f7012x, 0, 6);
    }
}
