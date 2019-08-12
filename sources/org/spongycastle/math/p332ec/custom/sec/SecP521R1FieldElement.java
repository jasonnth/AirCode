package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP521R1FieldElement */
public class SecP521R1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f7042Q = SecP521R1Curve.f7040q;

    /* renamed from: x */
    protected int[] f7043x;

    public SecP521R1FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.compareTo(f7042Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP521R1FieldElement");
        }
        this.f7043x = SecP521R1Field.fromBigInteger(x);
    }

    public SecP521R1FieldElement() {
        this.f7043x = Nat.create(17);
    }

    protected SecP521R1FieldElement(int[] x) {
        this.f7043x = x;
    }

    public boolean isZero() {
        return Nat.isZero(17, this.f7043x);
    }

    public boolean isOne() {
        return Nat.isOne(17, this.f7043x);
    }

    public boolean testBitZero() {
        return Nat.getBit(this.f7043x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat.toBigInteger(17, this.f7043x);
    }

    public String getFieldName() {
        return "SecP521R1Field";
    }

    public int getFieldSize() {
        return f7042Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement b) {
        int[] z = Nat.create(17);
        SecP521R1Field.add(this.f7043x, ((SecP521R1FieldElement) b).f7043x, z);
        return new SecP521R1FieldElement(z);
    }

    public ECFieldElement addOne() {
        int[] z = Nat.create(17);
        SecP521R1Field.addOne(this.f7043x, z);
        return new SecP521R1FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        int[] z = Nat.create(17);
        SecP521R1Field.subtract(this.f7043x, ((SecP521R1FieldElement) b).f7043x, z);
        return new SecP521R1FieldElement(z);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        int[] z = Nat.create(17);
        SecP521R1Field.multiply(this.f7043x, ((SecP521R1FieldElement) b).f7043x, z);
        return new SecP521R1FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        int[] z = Nat.create(17);
        Mod.invert(SecP521R1Field.f7041P, ((SecP521R1FieldElement) b).f7043x, z);
        SecP521R1Field.multiply(z, this.f7043x, z);
        return new SecP521R1FieldElement(z);
    }

    public ECFieldElement negate() {
        int[] z = Nat.create(17);
        SecP521R1Field.negate(this.f7043x, z);
        return new SecP521R1FieldElement(z);
    }

    public ECFieldElement square() {
        int[] z = Nat.create(17);
        SecP521R1Field.square(this.f7043x, z);
        return new SecP521R1FieldElement(z);
    }

    public ECFieldElement invert() {
        int[] z = Nat.create(17);
        Mod.invert(SecP521R1Field.f7041P, this.f7043x, z);
        return new SecP521R1FieldElement(z);
    }

    public ECFieldElement sqrt() {
        int[] x1 = this.f7043x;
        if (Nat.isZero(17, x1) || Nat.isOne(17, x1)) {
            return this;
        }
        int[] t1 = Nat.create(17);
        int[] t2 = Nat.create(17);
        SecP521R1Field.squareN(x1, 519, t1);
        SecP521R1Field.square(t1, t2);
        if (Nat.m4041eq(17, x1, t2)) {
            return new SecP521R1FieldElement(t1);
        }
        return null;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecP521R1FieldElement)) {
            return false;
        }
        return Nat.m4041eq(17, this.f7043x, ((SecP521R1FieldElement) other).f7043x);
    }

    public int hashCode() {
        return f7042Q.hashCode() ^ Arrays.hashCode(this.f7043x, 0, 17);
    }
}
