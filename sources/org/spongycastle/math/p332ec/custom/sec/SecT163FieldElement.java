package org.spongycastle.math.p332ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.raw.Nat192;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecT163FieldElement */
public class SecT163FieldElement extends ECFieldElement {

    /* renamed from: x */
    protected long[] f7046x;

    public SecT163FieldElement(BigInteger x) {
        if (x == null || x.signum() < 0 || x.bitLength() > 163) {
            throw new IllegalArgumentException("x value invalid for SecT163FieldElement");
        }
        this.f7046x = SecT163Field.fromBigInteger(x);
    }

    public SecT163FieldElement() {
        this.f7046x = Nat192.create64();
    }

    protected SecT163FieldElement(long[] x) {
        this.f7046x = x;
    }

    public boolean isOne() {
        return Nat192.isOne64(this.f7046x);
    }

    public boolean isZero() {
        return Nat192.isZero64(this.f7046x);
    }

    public boolean testBitZero() {
        return (this.f7046x[0] & 1) != 0;
    }

    public BigInteger toBigInteger() {
        return Nat192.toBigInteger64(this.f7046x);
    }

    public String getFieldName() {
        return "SecT163Field";
    }

    public int getFieldSize() {
        return CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384;
    }

    public ECFieldElement add(ECFieldElement b) {
        long[] z = Nat192.create64();
        SecT163Field.add(this.f7046x, ((SecT163FieldElement) b).f7046x, z);
        return new SecT163FieldElement(z);
    }

    public ECFieldElement addOne() {
        long[] z = Nat192.create64();
        SecT163Field.addOne(this.f7046x, z);
        return new SecT163FieldElement(z);
    }

    public ECFieldElement subtract(ECFieldElement b) {
        return add(b);
    }

    public ECFieldElement multiply(ECFieldElement b) {
        long[] z = Nat192.create64();
        SecT163Field.multiply(this.f7046x, ((SecT163FieldElement) b).f7046x, z);
        return new SecT163FieldElement(z);
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        return multiplyPlusProduct(b, x, y);
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement b, ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7046x;
        long[] bx = ((SecT163FieldElement) b).f7046x;
        long[] xx = ((SecT163FieldElement) x).f7046x;
        long[] yx = ((SecT163FieldElement) y).f7046x;
        long[] tt = Nat192.createExt64();
        SecT163Field.multiplyAddToExt(ax, bx, tt);
        SecT163Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat192.create64();
        SecT163Field.reduce(tt, z);
        return new SecT163FieldElement(z);
    }

    public ECFieldElement divide(ECFieldElement b) {
        return multiply(b.invert());
    }

    public ECFieldElement negate() {
        return this;
    }

    public ECFieldElement square() {
        long[] z = Nat192.create64();
        SecT163Field.square(this.f7046x, z);
        return new SecT163FieldElement(z);
    }

    public ECFieldElement squareMinusProduct(ECFieldElement x, ECFieldElement y) {
        return squarePlusProduct(x, y);
    }

    public ECFieldElement squarePlusProduct(ECFieldElement x, ECFieldElement y) {
        long[] ax = this.f7046x;
        long[] xx = ((SecT163FieldElement) x).f7046x;
        long[] yx = ((SecT163FieldElement) y).f7046x;
        long[] tt = Nat192.createExt64();
        SecT163Field.squareAddToExt(ax, tt);
        SecT163Field.multiplyAddToExt(xx, yx, tt);
        long[] z = Nat192.create64();
        SecT163Field.reduce(tt, z);
        return new SecT163FieldElement(z);
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public ECFieldElement squarePow(int pow) {
        if (pow < 1) {
            return this;
        }
        long[] z = Nat192.create64();
        SecT163Field.squareN(this.f7046x, pow, z);
        return new SecT163FieldElement(z);
    }

    public ECFieldElement invert() {
        long[] z = Nat192.create64();
        SecT163Field.invert(this.f7046x, z);
        return new SecT163FieldElement(z);
    }

    public ECFieldElement sqrt() {
        long[] z = Nat192.create64();
        SecT163Field.sqrt(this.f7046x, z);
        return new SecT163FieldElement(z);
    }

    public int getRepresentation() {
        return 3;
    }

    public int getM() {
        return CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384;
    }

    public int getK1() {
        return 3;
    }

    public int getK2() {
        return 6;
    }

    public int getK3() {
        return 7;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecT163FieldElement)) {
            return false;
        }
        return Nat192.eq64(this.f7046x, ((SecT163FieldElement) other).f7046x);
    }

    public int hashCode() {
        return 163763 ^ Arrays.hashCode(this.f7046x, 0, 3);
    }
}
