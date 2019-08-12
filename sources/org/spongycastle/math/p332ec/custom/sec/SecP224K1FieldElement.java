package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat224;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP224K1FieldElement */
public class SecP224K1FieldElement extends ECFieldElement {
    private static final int[] PRECOMP_POW2 = {868209154, -587542221, 579297866, -1014948952, -1470801668, 514782679, -1897982644};

    /* renamed from: Q */
    public static final BigInteger f7016Q = SecP224K1Curve.f7013q;

    /* renamed from: x */
    protected int[] f7017x;

    public SecP224K1FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.compareTo(f7016Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224K1FieldElement");
        }
        this.f7017x = SecP224K1Field.fromBigInteger(x);
    }

    public SecP224K1FieldElement() {
        this.f7017x = Nat224.create();
    }

    protected SecP224K1FieldElement(int[] x) {
        this.f7017x = x;
    }

    public boolean isZero() {
        return Nat224.isZero(this.f7017x);
    }

    public boolean isOne() {
        return Nat224.isOne(this.f7017x);
    }

    public boolean testBitZero() {
        return Nat224.getBit(this.f7017x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat224.toBigInteger(this.f7017x);
    }

    public String getFieldName() {
        return "SecP224K1Field";
    }

    public int getFieldSize() {
        return f7016Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement b) {
        int[] z = Nat224.create();
        SecP224K1Field.add(this.f7017x, ((SecP224K1FieldElement) b).f7017x, z);
        return new SecP224K1FieldElement(z);
    }

    public ECFieldElement addOne() {
        int[] z = Nat224.create();
        SecP224K1Field.addOne(this.f7017x, z);
        return new SecP224K1FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        int[] z = Nat224.create();
        SecP224K1Field.subtract(this.f7017x, ((SecP224K1FieldElement) b).f7017x, z);
        return new SecP224K1FieldElement(z);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        int[] z = Nat224.create();
        SecP224K1Field.multiply(this.f7017x, ((SecP224K1FieldElement) b).f7017x, z);
        return new SecP224K1FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        int[] z = Nat224.create();
        Mod.invert(SecP224K1Field.f7014P, ((SecP224K1FieldElement) b).f7017x, z);
        SecP224K1Field.multiply(z, this.f7017x, z);
        return new SecP224K1FieldElement(z);
    }

    public ECFieldElement negate() {
        int[] z = Nat224.create();
        SecP224K1Field.negate(this.f7017x, z);
        return new SecP224K1FieldElement(z);
    }

    public ECFieldElement square() {
        int[] z = Nat224.create();
        SecP224K1Field.square(this.f7017x, z);
        return new SecP224K1FieldElement(z);
    }

    public ECFieldElement invert() {
        int[] z = Nat224.create();
        Mod.invert(SecP224K1Field.f7014P, this.f7017x, z);
        return new SecP224K1FieldElement(z);
    }

    /* Debug info: failed to restart local var, previous not found, register: 15 */
    public ECFieldElement sqrt() {
        int[] x1 = this.f7017x;
        if (Nat224.isZero(x1) || Nat224.isOne(x1)) {
            return this;
        }
        int[] x2 = Nat224.create();
        SecP224K1Field.square(x1, x2);
        SecP224K1Field.multiply(x2, x1, x2);
        int[] x3 = x2;
        SecP224K1Field.square(x2, x3);
        SecP224K1Field.multiply(x3, x1, x3);
        int[] x4 = Nat224.create();
        SecP224K1Field.square(x3, x4);
        SecP224K1Field.multiply(x4, x1, x4);
        int[] x8 = Nat224.create();
        SecP224K1Field.squareN(x4, 4, x8);
        SecP224K1Field.multiply(x8, x4, x8);
        int[] x11 = Nat224.create();
        SecP224K1Field.squareN(x8, 3, x11);
        SecP224K1Field.multiply(x11, x3, x11);
        int[] x19 = x11;
        SecP224K1Field.squareN(x11, 8, x19);
        SecP224K1Field.multiply(x19, x8, x19);
        int[] x23 = x8;
        SecP224K1Field.squareN(x19, 4, x23);
        SecP224K1Field.multiply(x23, x4, x23);
        int[] x42 = x4;
        SecP224K1Field.squareN(x23, 19, x42);
        SecP224K1Field.multiply(x42, x19, x42);
        int[] x84 = Nat224.create();
        SecP224K1Field.squareN(x42, 42, x84);
        SecP224K1Field.multiply(x84, x42, x84);
        int[] x107 = x42;
        SecP224K1Field.squareN(x84, 23, x107);
        SecP224K1Field.multiply(x107, x23, x107);
        int[] x191 = x23;
        SecP224K1Field.squareN(x107, 84, x191);
        SecP224K1Field.multiply(x191, x84, x191);
        int[] t1 = x191;
        SecP224K1Field.squareN(t1, 20, t1);
        SecP224K1Field.multiply(t1, x19, t1);
        SecP224K1Field.squareN(t1, 3, t1);
        SecP224K1Field.multiply(t1, x1, t1);
        SecP224K1Field.squareN(t1, 2, t1);
        SecP224K1Field.multiply(t1, x1, t1);
        SecP224K1Field.squareN(t1, 4, t1);
        SecP224K1Field.multiply(t1, x3, t1);
        SecP224K1Field.square(t1, t1);
        int[] t2 = x84;
        SecP224K1Field.square(t1, t2);
        if (Nat224.m4045eq(x1, t2)) {
            return new SecP224K1FieldElement(t1);
        }
        SecP224K1Field.multiply(t1, PRECOMP_POW2, t1);
        SecP224K1Field.square(t1, t2);
        if (Nat224.m4045eq(x1, t2)) {
            return new SecP224K1FieldElement(t1);
        }
        return null;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecP224K1FieldElement)) {
            return false;
        }
        return Nat224.m4045eq(this.f7017x, ((SecP224K1FieldElement) other).f7017x);
    }

    public int hashCode() {
        return f7016Q.hashCode() ^ Arrays.hashCode(this.f7017x, 0, 7);
    }
}
