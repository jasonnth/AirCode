package org.spongycastle.crypto.engines;

import java.math.BigInteger;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

public class CramerShoupCiphertext {

    /* renamed from: e */
    BigInteger f6667e;

    /* renamed from: u1 */
    BigInteger f6668u1;

    /* renamed from: u2 */
    BigInteger f6669u2;

    /* renamed from: v */
    BigInteger f6670v;

    public CramerShoupCiphertext() {
    }

    public CramerShoupCiphertext(BigInteger u1, BigInteger u2, BigInteger e, BigInteger v) {
        this.f6668u1 = u1;
        this.f6669u2 = u2;
        this.f6667e = e;
        this.f6670v = v;
    }

    public CramerShoupCiphertext(byte[] c) {
        int s = Pack.bigEndianToInt(c, 0);
        int off = s + 4;
        this.f6668u1 = new BigInteger(Arrays.copyOfRange(c, 0 + 4, s + 4));
        int s2 = Pack.bigEndianToInt(c, off);
        int off2 = off + 4;
        byte[] tmp = Arrays.copyOfRange(c, off2, off2 + s2);
        int off3 = off2 + s2;
        this.f6669u2 = new BigInteger(tmp);
        int s3 = Pack.bigEndianToInt(c, off3);
        int off4 = off3 + 4;
        byte[] tmp2 = Arrays.copyOfRange(c, off4, off4 + s3);
        int off5 = off4 + s3;
        this.f6667e = new BigInteger(tmp2);
        int s4 = Pack.bigEndianToInt(c, off5);
        int off6 = off5 + 4;
        byte[] tmp3 = Arrays.copyOfRange(c, off6, off6 + s4);
        int off7 = off6 + s4;
        this.f6670v = new BigInteger(tmp3);
    }

    public BigInteger getU1() {
        return this.f6668u1;
    }

    public void setU1(BigInteger u1) {
        this.f6668u1 = u1;
    }

    public BigInteger getU2() {
        return this.f6669u2;
    }

    public void setU2(BigInteger u2) {
        this.f6669u2 = u2;
    }

    public BigInteger getE() {
        return this.f6667e;
    }

    public void setE(BigInteger e) {
        this.f6667e = e;
    }

    public BigInteger getV() {
        return this.f6670v;
    }

    public void setV(BigInteger v) {
        this.f6670v = v;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("u1: " + this.f6668u1.toString());
        result.append("\nu2: " + this.f6669u2.toString());
        result.append("\ne: " + this.f6667e.toString());
        result.append("\nv: " + this.f6670v.toString());
        return result.toString();
    }

    public byte[] toByteArray() {
        byte[] u1Bytes = this.f6668u1.toByteArray();
        int u1Length = u1Bytes.length;
        byte[] u2Bytes = this.f6669u2.toByteArray();
        int u2Length = u2Bytes.length;
        byte[] eBytes = this.f6667e.toByteArray();
        int eLength = eBytes.length;
        byte[] vBytes = this.f6670v.toByteArray();
        int vLength = vBytes.length;
        byte[] result = new byte[(u1Length + u2Length + eLength + vLength + 16)];
        Pack.intToBigEndian(u1Length, result, 0);
        System.arraycopy(u1Bytes, 0, result, 0 + 4, u1Length);
        int off = u1Length + 4;
        Pack.intToBigEndian(u2Length, result, off);
        int off2 = off + 4;
        System.arraycopy(u2Bytes, 0, result, off2, u2Length);
        int off3 = off2 + u2Length;
        Pack.intToBigEndian(eLength, result, off3);
        int off4 = off3 + 4;
        System.arraycopy(eBytes, 0, result, off4, eLength);
        int off5 = off4 + eLength;
        Pack.intToBigEndian(vLength, result, off5);
        int off6 = off5 + 4;
        System.arraycopy(vBytes, 0, result, off6, vLength);
        int off7 = off6 + vLength;
        return result;
    }
}
