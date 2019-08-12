package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat128;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP128R1FieldElement */
public class SecP128R1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f6988Q = SecP128R1Curve.f6984q;

    /* renamed from: x */
    protected int[] f6989x;

    public SecP128R1FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.compareTo(f6988Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP128R1FieldElement");
        }
        this.f6989x = SecP128R1Field.fromBigInteger(x);
    }

    public SecP128R1FieldElement() {
        this.f6989x = Nat128.create();
    }

    protected SecP128R1FieldElement(int[] x) {
        this.f6989x = x;
    }

    public boolean isZero() {
        return Nat128.isZero(this.f6989x);
    }

    public boolean isOne() {
        return Nat128.isOne(this.f6989x);
    }

    public boolean testBitZero() {
        return Nat128.getBit(this.f6989x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat128.toBigInteger(this.f6989x);
    }

    public String getFieldName() {
        return "SecP128R1Field";
    }

    public int getFieldSize() {
        return f6988Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement b) {
        int[] z = Nat128.create();
        SecP128R1Field.add(this.f6989x, ((SecP128R1FieldElement) b).f6989x, z);
        return new SecP128R1FieldElement(z);
    }

    public ECFieldElement addOne() {
        int[] z = Nat128.create();
        SecP128R1Field.addOne(this.f6989x, z);
        return new SecP128R1FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        int[] z = Nat128.create();
        SecP128R1Field.subtract(this.f6989x, ((SecP128R1FieldElement) b).f6989x, z);
        return new SecP128R1FieldElement(z);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        int[] z = Nat128.create();
        SecP128R1Field.multiply(this.f6989x, ((SecP128R1FieldElement) b).f6989x, z);
        return new SecP128R1FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        int[] z = Nat128.create();
        Mod.invert(SecP128R1Field.f6986P, ((SecP128R1FieldElement) b).f6989x, z);
        SecP128R1Field.multiply(z, this.f6989x, z);
        return new SecP128R1FieldElement(z);
    }

    public ECFieldElement negate() {
        int[] z = Nat128.create();
        SecP128R1Field.negate(this.f6989x, z);
        return new SecP128R1FieldElement(z);
    }

    public ECFieldElement square() {
        int[] z = Nat128.create();
        SecP128R1Field.square(this.f6989x, z);
        return new SecP128R1FieldElement(z);
    }

    public ECFieldElement invert() {
        int[] z = Nat128.create();
        Mod.invert(SecP128R1Field.f6986P, this.f6989x, z);
        return new SecP128R1FieldElement(z);
    }

    public ECFieldElement sqrt() {
        int[] x1 = this.f6989x;
        if (Nat128.isZero(x1) || Nat128.isOne(x1)) {
            return this;
        }
        int[] x2 = Nat128.create();
        SecP128R1Field.square(x1, x2);
        SecP128R1Field.multiply(x2, x1, x2);
        int[] x4 = Nat128.create();
        SecP128R1Field.squareN(x2, 2, x4);
        SecP128R1Field.multiply(x4, x2, x4);
        int[] x8 = Nat128.create();
        SecP128R1Field.squareN(x4, 4, x8);
        SecP128R1Field.multiply(x8, x4, x8);
        int[] x10 = x4;
        SecP128R1Field.squareN(x8, 2, x10);
        SecP128R1Field.multiply(x10, x2, x10);
        int[] x20 = x2;
        SecP128R1Field.squareN(x10, 10, x20);
        SecP128R1Field.multiply(x20, x10, x20);
        int[] x30 = x8;
        SecP128R1Field.squareN(x20, 10, x30);
        SecP128R1Field.multiply(x30, x10, x30);
        int[] x31 = x10;
        SecP128R1Field.square(x30, x31);
        SecP128R1Field.multiply(x31, x1, x31);
        int[] t1 = x31;
        SecP128R1Field.squareN(t1, 95, t1);
        int[] t2 = x30;
        SecP128R1Field.square(t1, t2);
        if (Nat128.m4042eq(x1, t2)) {
            return new SecP128R1FieldElement(t1);
        }
        return null;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecP128R1FieldElement)) {
            return false;
        }
        return Nat128.m4042eq(this.f6989x, ((SecP128R1FieldElement) other).f6989x);
    }

    public int hashCode() {
        return f6988Q.hashCode() ^ Arrays.hashCode(this.f6989x, 0, 4);
    }
}
