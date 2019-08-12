package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat160;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP160R1FieldElement */
public class SecP160R1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f6995Q = SecP160R1Curve.f6991q;

    /* renamed from: x */
    protected int[] f6996x;

    public SecP160R1FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.compareTo(f6995Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP160R1FieldElement");
        }
        this.f6996x = SecP160R1Field.fromBigInteger(x);
    }

    public SecP160R1FieldElement() {
        this.f6996x = Nat160.create();
    }

    protected SecP160R1FieldElement(int[] x) {
        this.f6996x = x;
    }

    public boolean isZero() {
        return Nat160.isZero(this.f6996x);
    }

    public boolean isOne() {
        return Nat160.isOne(this.f6996x);
    }

    public boolean testBitZero() {
        return Nat160.getBit(this.f6996x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat160.toBigInteger(this.f6996x);
    }

    public String getFieldName() {
        return "SecP160R1Field";
    }

    public int getFieldSize() {
        return f6995Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement b) {
        int[] z = Nat160.create();
        SecP160R1Field.add(this.f6996x, ((SecP160R1FieldElement) b).f6996x, z);
        return new SecP160R1FieldElement(z);
    }

    public ECFieldElement addOne() {
        int[] z = Nat160.create();
        SecP160R1Field.addOne(this.f6996x, z);
        return new SecP160R1FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        int[] z = Nat160.create();
        SecP160R1Field.subtract(this.f6996x, ((SecP160R1FieldElement) b).f6996x, z);
        return new SecP160R1FieldElement(z);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        int[] z = Nat160.create();
        SecP160R1Field.multiply(this.f6996x, ((SecP160R1FieldElement) b).f6996x, z);
        return new SecP160R1FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        int[] z = Nat160.create();
        Mod.invert(SecP160R1Field.f6993P, ((SecP160R1FieldElement) b).f6996x, z);
        SecP160R1Field.multiply(z, this.f6996x, z);
        return new SecP160R1FieldElement(z);
    }

    public ECFieldElement negate() {
        int[] z = Nat160.create();
        SecP160R1Field.negate(this.f6996x, z);
        return new SecP160R1FieldElement(z);
    }

    public ECFieldElement square() {
        int[] z = Nat160.create();
        SecP160R1Field.square(this.f6996x, z);
        return new SecP160R1FieldElement(z);
    }

    public ECFieldElement invert() {
        int[] z = Nat160.create();
        Mod.invert(SecP160R1Field.f6993P, this.f6996x, z);
        return new SecP160R1FieldElement(z);
    }

    public ECFieldElement sqrt() {
        int[] x1 = this.f6996x;
        if (Nat160.isZero(x1) || Nat160.isOne(x1)) {
            return this;
        }
        int[] x2 = Nat160.create();
        SecP160R1Field.square(x1, x2);
        SecP160R1Field.multiply(x2, x1, x2);
        int[] x4 = Nat160.create();
        SecP160R1Field.squareN(x2, 2, x4);
        SecP160R1Field.multiply(x4, x2, x4);
        int[] x8 = x2;
        SecP160R1Field.squareN(x4, 4, x8);
        SecP160R1Field.multiply(x8, x4, x8);
        int[] x16 = x4;
        SecP160R1Field.squareN(x8, 8, x16);
        SecP160R1Field.multiply(x16, x8, x16);
        int[] x32 = x8;
        SecP160R1Field.squareN(x16, 16, x32);
        SecP160R1Field.multiply(x32, x16, x32);
        int[] x64 = x16;
        SecP160R1Field.squareN(x32, 32, x64);
        SecP160R1Field.multiply(x64, x32, x64);
        int[] x128 = x32;
        SecP160R1Field.squareN(x64, 64, x128);
        SecP160R1Field.multiply(x128, x64, x128);
        int[] x129 = x64;
        SecP160R1Field.square(x128, x129);
        SecP160R1Field.multiply(x129, x1, x129);
        int[] t1 = x129;
        SecP160R1Field.squareN(t1, 29, t1);
        int[] t2 = x128;
        SecP160R1Field.square(t1, t2);
        if (Nat160.m4043eq(x1, t2)) {
            return new SecP160R1FieldElement(t1);
        }
        return null;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecP160R1FieldElement)) {
            return false;
        }
        return Nat160.m4043eq(this.f6996x, ((SecP160R1FieldElement) other).f6996x);
    }

    public int hashCode() {
        return f6995Q.hashCode() ^ Arrays.hashCode(this.f6996x, 0, 5);
    }
}
