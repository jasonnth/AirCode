package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat256;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP256K1FieldElement */
public class SecP256K1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f7027Q = SecP256K1Curve.f7024q;

    /* renamed from: x */
    protected int[] f7028x;

    public SecP256K1FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.compareTo(f7027Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP256K1FieldElement");
        }
        this.f7028x = SecP256K1Field.fromBigInteger(x);
    }

    public SecP256K1FieldElement() {
        this.f7028x = Nat256.create();
    }

    protected SecP256K1FieldElement(int[] x) {
        this.f7028x = x;
    }

    public boolean isZero() {
        return Nat256.isZero(this.f7028x);
    }

    public boolean isOne() {
        return Nat256.isOne(this.f7028x);
    }

    public boolean testBitZero() {
        return Nat256.getBit(this.f7028x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat256.toBigInteger(this.f7028x);
    }

    public String getFieldName() {
        return "SecP256K1Field";
    }

    public int getFieldSize() {
        return f7027Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement b) {
        int[] z = Nat256.create();
        SecP256K1Field.add(this.f7028x, ((SecP256K1FieldElement) b).f7028x, z);
        return new SecP256K1FieldElement(z);
    }

    public ECFieldElement addOne() {
        int[] z = Nat256.create();
        SecP256K1Field.addOne(this.f7028x, z);
        return new SecP256K1FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        int[] z = Nat256.create();
        SecP256K1Field.subtract(this.f7028x, ((SecP256K1FieldElement) b).f7028x, z);
        return new SecP256K1FieldElement(z);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        int[] z = Nat256.create();
        SecP256K1Field.multiply(this.f7028x, ((SecP256K1FieldElement) b).f7028x, z);
        return new SecP256K1FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        int[] z = Nat256.create();
        Mod.invert(SecP256K1Field.f7025P, ((SecP256K1FieldElement) b).f7028x, z);
        SecP256K1Field.multiply(z, this.f7028x, z);
        return new SecP256K1FieldElement(z);
    }

    public ECFieldElement negate() {
        int[] z = Nat256.create();
        SecP256K1Field.negate(this.f7028x, z);
        return new SecP256K1FieldElement(z);
    }

    public ECFieldElement square() {
        int[] z = Nat256.create();
        SecP256K1Field.square(this.f7028x, z);
        return new SecP256K1FieldElement(z);
    }

    public ECFieldElement invert() {
        int[] z = Nat256.create();
        Mod.invert(SecP256K1Field.f7025P, this.f7028x, z);
        return new SecP256K1FieldElement(z);
    }

    public ECFieldElement sqrt() {
        int[] x1 = this.f7028x;
        if (Nat256.isZero(x1) || Nat256.isOne(x1)) {
            return this;
        }
        int[] x2 = Nat256.create();
        SecP256K1Field.square(x1, x2);
        SecP256K1Field.multiply(x2, x1, x2);
        int[] x3 = Nat256.create();
        SecP256K1Field.square(x2, x3);
        SecP256K1Field.multiply(x3, x1, x3);
        int[] x6 = Nat256.create();
        SecP256K1Field.squareN(x3, 3, x6);
        SecP256K1Field.multiply(x6, x3, x6);
        int[] x9 = x6;
        SecP256K1Field.squareN(x6, 3, x9);
        SecP256K1Field.multiply(x9, x3, x9);
        int[] x11 = x9;
        SecP256K1Field.squareN(x9, 2, x11);
        SecP256K1Field.multiply(x11, x2, x11);
        int[] x22 = Nat256.create();
        SecP256K1Field.squareN(x11, 11, x22);
        SecP256K1Field.multiply(x22, x11, x22);
        int[] x44 = x11;
        SecP256K1Field.squareN(x22, 22, x44);
        SecP256K1Field.multiply(x44, x22, x44);
        int[] x88 = Nat256.create();
        SecP256K1Field.squareN(x44, 44, x88);
        SecP256K1Field.multiply(x88, x44, x88);
        int[] x176 = Nat256.create();
        SecP256K1Field.squareN(x88, 88, x176);
        SecP256K1Field.multiply(x176, x88, x176);
        int[] x220 = x88;
        SecP256K1Field.squareN(x176, 44, x220);
        SecP256K1Field.multiply(x220, x44, x220);
        int[] x223 = x44;
        SecP256K1Field.squareN(x220, 3, x223);
        SecP256K1Field.multiply(x223, x3, x223);
        int[] t1 = x223;
        SecP256K1Field.squareN(t1, 23, t1);
        SecP256K1Field.multiply(t1, x22, t1);
        SecP256K1Field.squareN(t1, 6, t1);
        SecP256K1Field.multiply(t1, x2, t1);
        SecP256K1Field.squareN(t1, 2, t1);
        int[] t2 = x2;
        SecP256K1Field.square(t1, t2);
        if (Nat256.m4046eq(x1, t2)) {
            return new SecP256K1FieldElement(t1);
        }
        return null;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecP256K1FieldElement)) {
            return false;
        }
        return Nat256.m4046eq(this.f7028x, ((SecP256K1FieldElement) other).f7028x);
    }

    public int hashCode() {
        return f7027Q.hashCode() ^ Arrays.hashCode(this.f7028x, 0, 8);
    }
}
