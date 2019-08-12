package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Nat256;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecT193FieldElement */
public class SecT193FieldElement extends ECFieldElement {

    /* renamed from: x */
    protected long[] f7047x;

    public SecT193FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.bitLength() > 193) {
            throw new IllegalArgumentException("x value invalid for SecT193FieldElement");
        }
        this.f7047x = SecT193Field.fromBigInteger(x);
    }

    public SecT193FieldElement() {
        this.f7047x = Nat256.create64();
    }

    protected SecT193FieldElement(long[] x) {
        this.f7047x = x;
    }

    public boolean isOne() {
        return Nat256.isOne64(this.f7047x);
    }

    public boolean isZero() {
        return Nat256.isZero64(this.f7047x);
    }

    public boolean testBitZero() {
        return (this.f7047x[0] & 1) != 0;
    }

    public BigInteger toBigInteger() {
        return Nat256.toBigInteger64(this.f7047x);
    }

    public String getFieldName() {
        return "SecT193Field";
    }

    public int getFieldSize() {
        return CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256;
    }

    public ECFieldElement add(ECFieldElement b) {
        long[] z = Nat256.create64();
        SecT193Field.add(this.f7047x, ((SecT193FieldElement) b).f7047x, z);
        return new SecT193FieldElement(z);
    }

    public ECFieldElement addOne() {
        long[] z = Nat256.create64();
        SecT193Field.addOne(this.f7047x, z);
        return new SecT193FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        return add(b);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        long[] z = Nat256.create64();
        SecT193Field.multiply(this.f7047x, ((SecT193FieldElement) b).f7047x, z);
        return new SecT193FieldElement(z);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        return multiplyPlusProduct(b, x, y);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7047x;
        long[] bx = ((SecT193FieldElement) b).f7047x;
        long[] xx = ((SecT193FieldElement) x).f7047x;
        long[] yx = ((SecT193FieldElement) y).f7047x;
        long[] tt = Nat256.createExt64();
        SecT193Field.multiplyAddToExt(ax, bx, tt);
        SecT193Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat256.create64();
        SecT193Field.reduce(tt, z);
        return new SecT193FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        return multiply(b.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] z = Nat256.create64();
        SecT193Field.square(this.f7047x, z);
        return new SecT193FieldElement(z);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement x, ECFieldElement y) {
        return squarePlusProduct(x, y);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7047x;
        long[] xx = ((SecT193FieldElement) x).f7047x;
        long[] yx = ((SecT193FieldElement) y).f7047x;
        long[] tt = Nat256.createExt64();
        SecT193Field.squareAddToExt(ax, tt);
        SecT193Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat256.create64();
        SecT193Field.reduce(tt, z);
        return new SecT193FieldElement(z);
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public ECFieldElement squarePow(int pow) {
        if (pow < 1) {
            return this;
        }
        long[] z = Nat256.create64();
        SecT193Field.squareN(this.f7047x, pow, z);
        return new SecT193FieldElement(z);
    }

    public ECFieldElement invert() {
        long[] z = Nat256.create64();
        SecT193Field.invert(this.f7047x, z);
        return new SecT193FieldElement(z);
    }

    public ECFieldElement sqrt() {
        long[] z = Nat256.create64();
        SecT193Field.sqrt(this.f7047x, z);
        return new SecT193FieldElement(z);
    }

    public int getRepresentation() {
        return 2;
    }

    public int getM() {
        return CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256;
    }

    public int getK1() {
        return 15;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecT193FieldElement)) {
            return false;
        }
        return Nat256.eq64(this.f7047x, ((SecT193FieldElement) other).f7047x);
    }

    public int hashCode() {
        return 1930015 ^ Arrays.hashCode(this.f7047x, 0, 4);
    }
}
