package org.spongycastle.math.p332ec.custom.djb;

import java.math.BigInteger;
import org.spongycastle.asn1.eac.EACTags;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat256;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.djb.Curve25519FieldElement */
public class Curve25519FieldElement extends ECFieldElement {
    private static final int[] PRECOMP_POW2 = {1242472624, -991028441, -1389370248, 792926214, 1039914919, 726466713, 1338105611, 730014848};

    /* renamed from: Q */
    public static final BigInteger f6982Q = Curve25519.f6978q;

    /* renamed from: x */
    protected int[] f6983x;

    public Curve25519FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.compareTo(f6982Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for Curve25519FieldElement");
        }
        this.f6983x = Curve25519Field.fromBigInteger(x);
    }

    public Curve25519FieldElement() {
        this.f6983x = Nat256.create();
    }

    protected Curve25519FieldElement(int[] x) {
        this.f6983x = x;
    }

    public boolean isZero() {
        return Nat256.isZero(this.f6983x);
    }

    public boolean isOne() {
        return Nat256.isOne(this.f6983x);
    }

    public boolean testBitZero() {
        return Nat256.getBit(this.f6983x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat256.toBigInteger(this.f6983x);
    }

    public String getFieldName() {
        return "Curve25519Field";
    }

    public int getFieldSize() {
        return f6982Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement b) {
        int[] z = Nat256.create();
        Curve25519Field.add(this.f6983x, ((Curve25519FieldElement) b).f6983x, z);
        return new Curve25519FieldElement(z);
    }

    public ECFieldElement addOne() {
        int[] z = Nat256.create();
        Curve25519Field.addOne(this.f6983x, z);
        return new Curve25519FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        int[] z = Nat256.create();
        Curve25519Field.subtract(this.f6983x, ((Curve25519FieldElement) b).f6983x, z);
        return new Curve25519FieldElement(z);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        int[] z = Nat256.create();
        Curve25519Field.multiply(this.f6983x, ((Curve25519FieldElement) b).f6983x, z);
        return new Curve25519FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        int[] z = Nat256.create();
        Mod.invert(Curve25519Field.f6980P, ((Curve25519FieldElement) b).f6983x, z);
        Curve25519Field.multiply(z, this.f6983x, z);
        return new Curve25519FieldElement(z);
    }

    public ECFieldElement negate() {
        int[] z = Nat256.create();
        Curve25519Field.negate(this.f6983x, z);
        return new Curve25519FieldElement(z);
    }

    public ECFieldElement square() {
        int[] z = Nat256.create();
        Curve25519Field.square(this.f6983x, z);
        return new Curve25519FieldElement(z);
    }

    public ECFieldElement invert() {
        int[] z = Nat256.create();
        Mod.invert(Curve25519Field.f6980P, this.f6983x, z);
        return new Curve25519FieldElement(z);
    }

    /* Debug info: failed to restart local var, previous not found, register: 15 */
    public ECFieldElement sqrt() {
        int[] x1 = this.f6983x;
        if (Nat256.isZero(x1) || Nat256.isOne(x1)) {
            return this;
        }
        int[] x2 = Nat256.create();
        Curve25519Field.square(x1, x2);
        Curve25519Field.multiply(x2, x1, x2);
        int[] x3 = x2;
        Curve25519Field.square(x2, x3);
        Curve25519Field.multiply(x3, x1, x3);
        int[] x4 = Nat256.create();
        Curve25519Field.square(x3, x4);
        Curve25519Field.multiply(x4, x1, x4);
        int[] x7 = Nat256.create();
        Curve25519Field.squareN(x4, 3, x7);
        Curve25519Field.multiply(x7, x3, x7);
        int[] x11 = x3;
        Curve25519Field.squareN(x7, 4, x11);
        Curve25519Field.multiply(x11, x4, x11);
        int[] x15 = x7;
        Curve25519Field.squareN(x11, 4, x15);
        Curve25519Field.multiply(x15, x4, x15);
        int[] x30 = x4;
        Curve25519Field.squareN(x15, 15, x30);
        Curve25519Field.multiply(x30, x15, x30);
        int[] x60 = x15;
        Curve25519Field.squareN(x30, 30, x60);
        Curve25519Field.multiply(x60, x30, x60);
        int[] x120 = x30;
        Curve25519Field.squareN(x60, 60, x120);
        Curve25519Field.multiply(x120, x60, x120);
        int[] x131 = x60;
        Curve25519Field.squareN(x120, 11, x131);
        Curve25519Field.multiply(x131, x11, x131);
        int[] x251 = x11;
        Curve25519Field.squareN(x131, EACTags.COMPATIBLE_TAG_ALLOCATION_AUTHORITY, x251);
        Curve25519Field.multiply(x251, x120, x251);
        int[] t1 = x251;
        Curve25519Field.square(t1, t1);
        int[] t2 = x120;
        Curve25519Field.square(t1, t2);
        if (Nat256.m4046eq(x1, t2)) {
            return new Curve25519FieldElement(t1);
        }
        Curve25519Field.multiply(t1, PRECOMP_POW2, t1);
        Curve25519Field.square(t1, t2);
        if (Nat256.m4046eq(x1, t2)) {
            return new Curve25519FieldElement(t1);
        }
        return null;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Curve25519FieldElement)) {
            return false;
        }
        return Nat256.m4046eq(this.f6983x, ((Curve25519FieldElement) other).f6983x);
    }

    public int hashCode() {
        return f6982Q.hashCode() ^ Arrays.hashCode(this.f6983x, 0, 8);
    }
}
