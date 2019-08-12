package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.asn1.eac.EACTags;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP384R1FieldElement */
public class SecP384R1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f7038Q = SecP384R1Curve.f7035q;

    /* renamed from: x */
    protected int[] f7039x;

    public SecP384R1FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.compareTo(f7038Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP384R1FieldElement");
        }
        this.f7039x = SecP384R1Field.fromBigInteger(x);
    }

    public SecP384R1FieldElement() {
        this.f7039x = Nat.create(12);
    }

    protected SecP384R1FieldElement(int[] x) {
        this.f7039x = x;
    }

    public boolean isZero() {
        return Nat.isZero(12, this.f7039x);
    }

    public boolean isOne() {
        return Nat.isOne(12, this.f7039x);
    }

    public boolean testBitZero() {
        return Nat.getBit(this.f7039x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat.toBigInteger(12, this.f7039x);
    }

    public String getFieldName() {
        return "SecP384R1Field";
    }

    public int getFieldSize() {
        return f7038Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement b) {
        int[] z = Nat.create(12);
        SecP384R1Field.add(this.f7039x, ((SecP384R1FieldElement) b).f7039x, z);
        return new SecP384R1FieldElement(z);
    }

    public ECFieldElement addOne() {
        int[] z = Nat.create(12);
        SecP384R1Field.addOne(this.f7039x, z);
        return new SecP384R1FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        int[] z = Nat.create(12);
        SecP384R1Field.subtract(this.f7039x, ((SecP384R1FieldElement) b).f7039x, z);
        return new SecP384R1FieldElement(z);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        int[] z = Nat.create(12);
        SecP384R1Field.multiply(this.f7039x, ((SecP384R1FieldElement) b).f7039x, z);
        return new SecP384R1FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        int[] z = Nat.create(12);
        Mod.invert(SecP384R1Field.f7037P, ((SecP384R1FieldElement) b).f7039x, z);
        SecP384R1Field.multiply(z, this.f7039x, z);
        return new SecP384R1FieldElement(z);
    }

    public ECFieldElement negate() {
        int[] z = Nat.create(12);
        SecP384R1Field.negate(this.f7039x, z);
        return new SecP384R1FieldElement(z);
    }

    public ECFieldElement square() {
        int[] z = Nat.create(12);
        SecP384R1Field.square(this.f7039x, z);
        return new SecP384R1FieldElement(z);
    }

    public ECFieldElement invert() {
        int[] z = Nat.create(12);
        Mod.invert(SecP384R1Field.f7037P, this.f7039x, z);
        return new SecP384R1FieldElement(z);
    }

    public ECFieldElement sqrt() {
        int[] x1 = this.f7039x;
        if (Nat.isZero(12, x1) || Nat.isOne(12, x1)) {
            return this;
        }
        int[] t1 = Nat.create(12);
        int[] t2 = Nat.create(12);
        int[] t3 = Nat.create(12);
        int[] t4 = Nat.create(12);
        SecP384R1Field.square(x1, t1);
        SecP384R1Field.multiply(t1, x1, t1);
        SecP384R1Field.squareN(t1, 2, t2);
        SecP384R1Field.multiply(t2, t1, t2);
        SecP384R1Field.square(t2, t2);
        SecP384R1Field.multiply(t2, x1, t2);
        SecP384R1Field.squareN(t2, 5, t3);
        SecP384R1Field.multiply(t3, t2, t3);
        SecP384R1Field.squareN(t3, 5, t4);
        SecP384R1Field.multiply(t4, t2, t4);
        SecP384R1Field.squareN(t4, 15, t2);
        SecP384R1Field.multiply(t2, t4, t2);
        SecP384R1Field.squareN(t2, 2, t3);
        SecP384R1Field.multiply(t1, t3, t1);
        SecP384R1Field.squareN(t3, 28, t3);
        SecP384R1Field.multiply(t2, t3, t2);
        SecP384R1Field.squareN(t2, 60, t3);
        SecP384R1Field.multiply(t3, t2, t3);
        int[] r = t2;
        SecP384R1Field.squareN(t3, EACTags.COMPATIBLE_TAG_ALLOCATION_AUTHORITY, r);
        SecP384R1Field.multiply(r, t3, r);
        SecP384R1Field.squareN(r, 15, r);
        SecP384R1Field.multiply(r, t4, r);
        SecP384R1Field.squareN(r, 33, r);
        SecP384R1Field.multiply(r, t1, r);
        SecP384R1Field.squareN(r, 64, r);
        SecP384R1Field.multiply(r, x1, r);
        SecP384R1Field.squareN(r, 30, t1);
        SecP384R1Field.square(t1, t2);
        if (Nat.m4041eq(12, x1, t2)) {
            return new SecP384R1FieldElement(t1);
        }
        return null;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecP384R1FieldElement)) {
            return false;
        }
        return Nat.m4041eq(12, this.f7039x, ((SecP384R1FieldElement) other).f7039x);
    }

    public int hashCode() {
        return f7038Q.hashCode() ^ Arrays.hashCode(this.f7039x, 0, 12);
    }
}
