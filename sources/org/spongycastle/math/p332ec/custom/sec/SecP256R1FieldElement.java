package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat256;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP256R1FieldElement */
public class SecP256R1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f7033Q = SecP256R1Curve.f7029q;

    /* renamed from: x */
    protected int[] f7034x;

    public SecP256R1FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.compareTo(f7033Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP256R1FieldElement");
        }
        this.f7034x = SecP256R1Field.fromBigInteger(x);
    }

    public SecP256R1FieldElement() {
        this.f7034x = Nat256.create();
    }

    protected SecP256R1FieldElement(int[] x) {
        this.f7034x = x;
    }

    public boolean isZero() {
        return Nat256.isZero(this.f7034x);
    }

    public boolean isOne() {
        return Nat256.isOne(this.f7034x);
    }

    public boolean testBitZero() {
        return Nat256.getBit(this.f7034x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat256.toBigInteger(this.f7034x);
    }

    public String getFieldName() {
        return "SecP256R1Field";
    }

    public int getFieldSize() {
        return f7033Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement b) {
        int[] z = Nat256.create();
        SecP256R1Field.add(this.f7034x, ((SecP256R1FieldElement) b).f7034x, z);
        return new SecP256R1FieldElement(z);
    }

    public ECFieldElement addOne() {
        int[] z = Nat256.create();
        SecP256R1Field.addOne(this.f7034x, z);
        return new SecP256R1FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        int[] z = Nat256.create();
        SecP256R1Field.subtract(this.f7034x, ((SecP256R1FieldElement) b).f7034x, z);
        return new SecP256R1FieldElement(z);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        int[] z = Nat256.create();
        SecP256R1Field.multiply(this.f7034x, ((SecP256R1FieldElement) b).f7034x, z);
        return new SecP256R1FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        int[] z = Nat256.create();
        Mod.invert(SecP256R1Field.f7031P, ((SecP256R1FieldElement) b).f7034x, z);
        SecP256R1Field.multiply(z, this.f7034x, z);
        return new SecP256R1FieldElement(z);
    }

    public ECFieldElement negate() {
        int[] z = Nat256.create();
        SecP256R1Field.negate(this.f7034x, z);
        return new SecP256R1FieldElement(z);
    }

    public ECFieldElement square() {
        int[] z = Nat256.create();
        SecP256R1Field.square(this.f7034x, z);
        return new SecP256R1FieldElement(z);
    }

    public ECFieldElement invert() {
        int[] z = Nat256.create();
        Mod.invert(SecP256R1Field.f7031P, this.f7034x, z);
        return new SecP256R1FieldElement(z);
    }

    public ECFieldElement sqrt() {
        int[] x1 = this.f7034x;
        if (Nat256.isZero(x1) || Nat256.isOne(x1)) {
            return this;
        }
        int[] t1 = Nat256.create();
        int[] t2 = Nat256.create();
        SecP256R1Field.square(x1, t1);
        SecP256R1Field.multiply(t1, x1, t1);
        SecP256R1Field.squareN(t1, 2, t2);
        SecP256R1Field.multiply(t2, t1, t2);
        SecP256R1Field.squareN(t2, 4, t1);
        SecP256R1Field.multiply(t1, t2, t1);
        SecP256R1Field.squareN(t1, 8, t2);
        SecP256R1Field.multiply(t2, t1, t2);
        SecP256R1Field.squareN(t2, 16, t1);
        SecP256R1Field.multiply(t1, t2, t1);
        SecP256R1Field.squareN(t1, 32, t1);
        SecP256R1Field.multiply(t1, x1, t1);
        SecP256R1Field.squareN(t1, 96, t1);
        SecP256R1Field.multiply(t1, x1, t1);
        SecP256R1Field.squareN(t1, 94, t1);
        SecP256R1Field.square(t1, t2);
        if (Nat256.m4046eq(x1, t2)) {
            return new SecP256R1FieldElement(t1);
        }
        return null;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecP256R1FieldElement)) {
            return false;
        }
        return Nat256.m4046eq(this.f7034x, ((SecP256R1FieldElement) other).f7034x);
    }

    public int hashCode() {
        return f7033Q.hashCode() ^ Arrays.hashCode(this.f7034x, 0, 8);
    }
}
