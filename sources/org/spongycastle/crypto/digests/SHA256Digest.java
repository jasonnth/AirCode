package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

public class SHA256Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 32;

    /* renamed from: K */
    static final int[] f6579K = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};

    /* renamed from: H1 */
    private int f6580H1;

    /* renamed from: H2 */
    private int f6581H2;

    /* renamed from: H3 */
    private int f6582H3;

    /* renamed from: H4 */
    private int f6583H4;

    /* renamed from: H5 */
    private int f6584H5;

    /* renamed from: H6 */
    private int f6585H6;

    /* renamed from: H7 */
    private int f6586H7;

    /* renamed from: H8 */
    private int f6587H8;

    /* renamed from: X */
    private int[] f6588X = new int[64];
    private int xOff;

    public SHA256Digest() {
        reset();
    }

    public SHA256Digest(SHA256Digest t) {
        super((GeneralDigest) t);
        copyIn(t);
    }

    private void copyIn(SHA256Digest t) {
        super.copyIn(t);
        this.f6580H1 = t.f6580H1;
        this.f6581H2 = t.f6581H2;
        this.f6582H3 = t.f6582H3;
        this.f6583H4 = t.f6583H4;
        this.f6584H5 = t.f6584H5;
        this.f6585H6 = t.f6585H6;
        this.f6586H7 = t.f6586H7;
        this.f6587H8 = t.f6587H8;
        System.arraycopy(t.f6588X, 0, this.f6588X, 0, t.f6588X.length);
        this.xOff = t.xOff;
    }

    public SHA256Digest(byte[] encodedState) {
        super(encodedState);
        this.f6580H1 = Pack.bigEndianToInt(encodedState, 16);
        this.f6581H2 = Pack.bigEndianToInt(encodedState, 20);
        this.f6582H3 = Pack.bigEndianToInt(encodedState, 24);
        this.f6583H4 = Pack.bigEndianToInt(encodedState, 28);
        this.f6584H5 = Pack.bigEndianToInt(encodedState, 32);
        this.f6585H6 = Pack.bigEndianToInt(encodedState, 36);
        this.f6586H7 = Pack.bigEndianToInt(encodedState, 40);
        this.f6587H8 = Pack.bigEndianToInt(encodedState, 44);
        this.xOff = Pack.bigEndianToInt(encodedState, 48);
        for (int i = 0; i != this.xOff; i++) {
            this.f6588X[i] = Pack.bigEndianToInt(encodedState, (i * 4) + 52);
        }
    }

    public String getAlgorithmName() {
        return "SHA-256";
    }

    public int getDigestSize() {
        return 32;
    }

    /* access modifiers changed from: protected */
    public void processWord(byte[] in, int inOff) {
        int inOff2 = inOff + 1;
        int inOff3 = inOff2 + 1;
        this.f6588X[this.xOff] = (in[inOff] << 24) | ((in[inOff2] & 255) << 16) | ((in[inOff3] & 255) << 8) | (in[inOff3 + 1] & 255);
        int i = this.xOff + 1;
        this.xOff = i;
        if (i == 16) {
            processBlock();
        }
    }

    /* access modifiers changed from: protected */
    public void processLength(long bitLength) {
        if (this.xOff > 14) {
            processBlock();
        }
        this.f6588X[14] = (int) (bitLength >>> 32);
        this.f6588X[15] = (int) (-1 & bitLength);
    }

    public int doFinal(byte[] out, int outOff) {
        finish();
        Pack.intToBigEndian(this.f6580H1, out, outOff);
        Pack.intToBigEndian(this.f6581H2, out, outOff + 4);
        Pack.intToBigEndian(this.f6582H3, out, outOff + 8);
        Pack.intToBigEndian(this.f6583H4, out, outOff + 12);
        Pack.intToBigEndian(this.f6584H5, out, outOff + 16);
        Pack.intToBigEndian(this.f6585H6, out, outOff + 20);
        Pack.intToBigEndian(this.f6586H7, out, outOff + 24);
        Pack.intToBigEndian(this.f6587H8, out, outOff + 28);
        reset();
        return 32;
    }

    public void reset() {
        super.reset();
        this.f6580H1 = 1779033703;
        this.f6581H2 = -1150833019;
        this.f6582H3 = 1013904242;
        this.f6583H4 = -1521486534;
        this.f6584H5 = 1359893119;
        this.f6585H6 = -1694144372;
        this.f6586H7 = 528734635;
        this.f6587H8 = 1541459225;
        this.xOff = 0;
        for (int i = 0; i != this.f6588X.length; i++) {
            this.f6588X[i] = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void processBlock() {
        for (int t = 16; t <= 63; t++) {
            this.f6588X[t] = Theta1(this.f6588X[t - 2]) + this.f6588X[t - 7] + Theta0(this.f6588X[t - 15]) + this.f6588X[t - 16];
        }
        int a = this.f6580H1;
        int b = this.f6581H2;
        int c = this.f6582H3;
        int d = this.f6583H4;
        int e = this.f6584H5;
        int f = this.f6585H6;
        int g = this.f6586H7;
        int h = this.f6587H8;
        int t2 = 0;
        for (int i = 0; i < 8; i++) {
            int h2 = h + Sum1(e) + m4006Ch(e, f, g) + f6579K[t2] + this.f6588X[t2];
            int d2 = d + h2;
            int h3 = h2 + Sum0(a) + Maj(a, b, c);
            int t3 = t2 + 1;
            int g2 = g + Sum1(d2) + m4006Ch(d2, e, f) + f6579K[t3] + this.f6588X[t3];
            int c2 = c + g2;
            int g3 = g2 + Sum0(h3) + Maj(h3, a, b);
            int t4 = t3 + 1;
            int f2 = f + Sum1(c2) + m4006Ch(c2, d2, e) + f6579K[t4] + this.f6588X[t4];
            int b2 = b + f2;
            int f3 = f2 + Sum0(g3) + Maj(g3, h3, a);
            int t5 = t4 + 1;
            int e2 = e + Sum1(b2) + m4006Ch(b2, c2, d2) + f6579K[t5] + this.f6588X[t5];
            int a2 = a + e2;
            int e3 = e2 + Sum0(f3) + Maj(f3, g3, h3);
            int t6 = t5 + 1;
            int d3 = d2 + Sum1(a2) + m4006Ch(a2, b2, c2) + f6579K[t6] + this.f6588X[t6];
            h = h3 + d3;
            d = d3 + Sum0(e3) + Maj(e3, f3, g3);
            int t7 = t6 + 1;
            int c3 = c2 + Sum1(h) + m4006Ch(h, a2, b2) + f6579K[t7] + this.f6588X[t7];
            g = g3 + c3;
            c = c3 + Sum0(d) + Maj(d, e3, f3);
            int t8 = t7 + 1;
            int b3 = b2 + Sum1(g) + m4006Ch(g, h, a2) + f6579K[t8] + this.f6588X[t8];
            f = f3 + b3;
            b = b3 + Sum0(c) + Maj(c, d, e3);
            int t9 = t8 + 1;
            int a3 = a2 + Sum1(f) + m4006Ch(f, g, h) + f6579K[t9] + this.f6588X[t9];
            e = e3 + a3;
            a = a3 + Sum0(b) + Maj(b, c, d);
            t2 = t9 + 1;
        }
        this.f6580H1 += a;
        this.f6581H2 += b;
        this.f6582H3 += c;
        this.f6583H4 += d;
        this.f6584H5 += e;
        this.f6585H6 += f;
        this.f6586H7 += g;
        this.f6587H8 += h;
        this.xOff = 0;
        for (int i2 = 0; i2 < 16; i2++) {
            this.f6588X[i2] = 0;
        }
    }

    /* renamed from: Ch */
    private int m4006Ch(int x, int y, int z) {
        return (x & y) ^ ((x ^ -1) & z);
    }

    private int Maj(int x, int y, int z) {
        return ((x & y) ^ (x & z)) ^ (y & z);
    }

    private int Sum0(int x) {
        return (((x >>> 2) | (x << 30)) ^ ((x >>> 13) | (x << 19))) ^ ((x >>> 22) | (x << 10));
    }

    private int Sum1(int x) {
        return (((x >>> 6) | (x << 26)) ^ ((x >>> 11) | (x << 21))) ^ ((x >>> 25) | (x << 7));
    }

    private int Theta0(int x) {
        return (((x >>> 7) | (x << 25)) ^ ((x >>> 18) | (x << 14))) ^ (x >>> 3);
    }

    private int Theta1(int x) {
        return (((x >>> 17) | (x << 15)) ^ ((x >>> 19) | (x << 13))) ^ (x >>> 10);
    }

    public Memoable copy() {
        return new SHA256Digest(this);
    }

    public void reset(Memoable other) {
        copyIn((SHA256Digest) other);
    }

    public byte[] getEncodedState() {
        byte[] state = new byte[((this.xOff * 4) + 52)];
        super.populateState(state);
        Pack.intToBigEndian(this.f6580H1, state, 16);
        Pack.intToBigEndian(this.f6581H2, state, 20);
        Pack.intToBigEndian(this.f6582H3, state, 24);
        Pack.intToBigEndian(this.f6583H4, state, 28);
        Pack.intToBigEndian(this.f6584H5, state, 32);
        Pack.intToBigEndian(this.f6585H6, state, 36);
        Pack.intToBigEndian(this.f6586H7, state, 40);
        Pack.intToBigEndian(this.f6587H8, state, 44);
        Pack.intToBigEndian(this.xOff, state, 48);
        for (int i = 0; i != this.xOff; i++) {
            Pack.intToBigEndian(this.f6588X[i], state, (i * 4) + 52);
        }
        return state;
    }
}
