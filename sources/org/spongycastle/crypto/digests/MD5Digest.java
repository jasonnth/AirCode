package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

public class MD5Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 16;
    private static final int S11 = 7;
    private static final int S12 = 12;
    private static final int S13 = 17;
    private static final int S14 = 22;
    private static final int S21 = 5;
    private static final int S22 = 9;
    private static final int S23 = 14;
    private static final int S24 = 20;
    private static final int S31 = 4;
    private static final int S32 = 11;
    private static final int S33 = 16;
    private static final int S34 = 23;
    private static final int S41 = 6;
    private static final int S42 = 10;
    private static final int S43 = 15;
    private static final int S44 = 21;

    /* renamed from: H1 */
    private int f6523H1;

    /* renamed from: H2 */
    private int f6524H2;

    /* renamed from: H3 */
    private int f6525H3;

    /* renamed from: H4 */
    private int f6526H4;

    /* renamed from: X */
    private int[] f6527X = new int[16];
    private int xOff;

    public MD5Digest() {
        reset();
    }

    public MD5Digest(MD5Digest t) {
        super((GeneralDigest) t);
        copyIn(t);
    }

    private void copyIn(MD5Digest t) {
        super.copyIn(t);
        this.f6523H1 = t.f6523H1;
        this.f6524H2 = t.f6524H2;
        this.f6525H3 = t.f6525H3;
        this.f6526H4 = t.f6526H4;
        System.arraycopy(t.f6527X, 0, this.f6527X, 0, t.f6527X.length);
        this.xOff = t.xOff;
    }

    public String getAlgorithmName() {
        return "MD5";
    }

    public int getDigestSize() {
        return 16;
    }

    /* access modifiers changed from: protected */
    public void processWord(byte[] in, int inOff) {
        int[] iArr = this.f6527X;
        int i = this.xOff;
        this.xOff = i + 1;
        iArr[i] = (in[inOff] & 255) | ((in[inOff + 1] & 255) << 8) | ((in[inOff + 2] & 255) << 16) | ((in[inOff + 3] & 255) << 24);
        if (this.xOff == 16) {
            processBlock();
        }
    }

    /* access modifiers changed from: protected */
    public void processLength(long bitLength) {
        if (this.xOff > 14) {
            processBlock();
        }
        this.f6527X[14] = (int) (-1 & bitLength);
        this.f6527X[15] = (int) (bitLength >>> 32);
    }

    private void unpackWord(int word, byte[] out, int outOff) {
        out[outOff] = (byte) word;
        out[outOff + 1] = (byte) (word >>> 8);
        out[outOff + 2] = (byte) (word >>> 16);
        out[outOff + 3] = (byte) (word >>> 24);
    }

    public int doFinal(byte[] out, int outOff) {
        finish();
        unpackWord(this.f6523H1, out, outOff);
        unpackWord(this.f6524H2, out, outOff + 4);
        unpackWord(this.f6525H3, out, outOff + 8);
        unpackWord(this.f6526H4, out, outOff + 12);
        reset();
        return 16;
    }

    public void reset() {
        super.reset();
        this.f6523H1 = 1732584193;
        this.f6524H2 = -271733879;
        this.f6525H3 = -1732584194;
        this.f6526H4 = 271733878;
        this.xOff = 0;
        for (int i = 0; i != this.f6527X.length; i++) {
            this.f6527X[i] = 0;
        }
    }

    private int rotateLeft(int x, int n) {
        return (x << n) | (x >>> (32 - n));
    }

    /* renamed from: F */
    private int m3968F(int u, int v, int w) {
        return (u & v) | ((u ^ -1) & w);
    }

    /* renamed from: G */
    private int m3969G(int u, int v, int w) {
        return (u & w) | ((w ^ -1) & v);
    }

    /* renamed from: H */
    private int m3970H(int u, int v, int w) {
        return (u ^ v) ^ w;
    }

    /* renamed from: K */
    private int m3971K(int u, int v, int w) {
        return ((w ^ -1) | u) ^ v;
    }

    /* access modifiers changed from: protected */
    public void processBlock() {
        int a = this.f6523H1;
        int b = this.f6524H2;
        int c = this.f6525H3;
        int d = this.f6526H4;
        int a2 = rotateLeft(((m3968F(b, c, d) + a) + this.f6527X[0]) - 680876936, 7) + b;
        int d2 = rotateLeft(((m3968F(a2, b, c) + d) + this.f6527X[1]) - 389564586, 12) + a2;
        int c2 = rotateLeft(m3968F(d2, a2, b) + c + this.f6527X[2] + 606105819, 17) + d2;
        int b2 = rotateLeft(((m3968F(c2, d2, a2) + b) + this.f6527X[3]) - 1044525330, 22) + c2;
        int a3 = rotateLeft(((m3968F(b2, c2, d2) + a2) + this.f6527X[4]) - 176418897, 7) + b2;
        int d3 = rotateLeft(m3968F(a3, b2, c2) + d2 + this.f6527X[5] + 1200080426, 12) + a3;
        int c3 = rotateLeft(((m3968F(d3, a3, b2) + c2) + this.f6527X[6]) - 1473231341, 17) + d3;
        int b3 = rotateLeft(((m3968F(c3, d3, a3) + b2) + this.f6527X[7]) - 45705983, 22) + c3;
        int a4 = rotateLeft(m3968F(b3, c3, d3) + a3 + this.f6527X[8] + 1770035416, 7) + b3;
        int d4 = rotateLeft(((m3968F(a4, b3, c3) + d3) + this.f6527X[9]) - 1958414417, 12) + a4;
        int c4 = rotateLeft(((m3968F(d4, a4, b3) + c3) + this.f6527X[10]) - 42063, 17) + d4;
        int b4 = rotateLeft(((m3968F(c4, d4, a4) + b3) + this.f6527X[11]) - 1990404162, 22) + c4;
        int a5 = rotateLeft(m3968F(b4, c4, d4) + a4 + this.f6527X[12] + 1804603682, 7) + b4;
        int d5 = rotateLeft(((m3968F(a5, b4, c4) + d4) + this.f6527X[13]) - 40341101, 12) + a5;
        int c5 = rotateLeft(((m3968F(d5, a5, b4) + c4) + this.f6527X[14]) - 1502002290, 17) + d5;
        int b5 = rotateLeft(m3968F(c5, d5, a5) + b4 + this.f6527X[15] + 1236535329, 22) + c5;
        int a6 = rotateLeft(((m3969G(b5, c5, d5) + a5) + this.f6527X[1]) - 165796510, 5) + b5;
        int d6 = rotateLeft(((m3969G(a6, b5, c5) + d5) + this.f6527X[6]) - 1069501632, 9) + a6;
        int c6 = rotateLeft(m3969G(d6, a6, b5) + c5 + this.f6527X[11] + 643717713, 14) + d6;
        int b6 = rotateLeft(((m3969G(c6, d6, a6) + b5) + this.f6527X[0]) - 373897302, 20) + c6;
        int a7 = rotateLeft(((m3969G(b6, c6, d6) + a6) + this.f6527X[5]) - 701558691, 5) + b6;
        int d7 = rotateLeft(m3969G(a7, b6, c6) + d6 + this.f6527X[10] + 38016083, 9) + a7;
        int c7 = rotateLeft(((m3969G(d7, a7, b6) + c6) + this.f6527X[15]) - 660478335, 14) + d7;
        int b7 = rotateLeft(((m3969G(c7, d7, a7) + b6) + this.f6527X[4]) - 405537848, 20) + c7;
        int a8 = rotateLeft(m3969G(b7, c7, d7) + a7 + this.f6527X[9] + 568446438, 5) + b7;
        int d8 = rotateLeft(((m3969G(a8, b7, c7) + d7) + this.f6527X[14]) - 1019803690, 9) + a8;
        int c8 = rotateLeft(((m3969G(d8, a8, b7) + c7) + this.f6527X[3]) - 187363961, 14) + d8;
        int b8 = rotateLeft(m3969G(c8, d8, a8) + b7 + this.f6527X[8] + 1163531501, 20) + c8;
        int a9 = rotateLeft(((m3969G(b8, c8, d8) + a8) + this.f6527X[13]) - 1444681467, 5) + b8;
        int d9 = rotateLeft(((m3969G(a9, b8, c8) + d8) + this.f6527X[2]) - 51403784, 9) + a9;
        int c9 = rotateLeft(m3969G(d9, a9, b8) + c8 + this.f6527X[7] + 1735328473, 14) + d9;
        int b9 = rotateLeft(((m3969G(c9, d9, a9) + b8) + this.f6527X[12]) - 1926607734, 20) + c9;
        int a10 = rotateLeft(((m3970H(b9, c9, d9) + a9) + this.f6527X[5]) - 378558, 4) + b9;
        int d10 = rotateLeft(((m3970H(a10, b9, c9) + d9) + this.f6527X[8]) - 2022574463, 11) + a10;
        int c10 = rotateLeft(m3970H(d10, a10, b9) + c9 + this.f6527X[11] + 1839030562, 16) + d10;
        int b10 = rotateLeft(((m3970H(c10, d10, a10) + b9) + this.f6527X[14]) - 35309556, 23) + c10;
        int a11 = rotateLeft(((m3970H(b10, c10, d10) + a10) + this.f6527X[1]) - 1530992060, 4) + b10;
        int d11 = rotateLeft(m3970H(a11, b10, c10) + d10 + this.f6527X[4] + 1272893353, 11) + a11;
        int c11 = rotateLeft(((m3970H(d11, a11, b10) + c10) + this.f6527X[7]) - 155497632, 16) + d11;
        int b11 = rotateLeft(((m3970H(c11, d11, a11) + b10) + this.f6527X[10]) - 1094730640, 23) + c11;
        int a12 = rotateLeft(m3970H(b11, c11, d11) + a11 + this.f6527X[13] + 681279174, 4) + b11;
        int d12 = rotateLeft(((m3970H(a12, b11, c11) + d11) + this.f6527X[0]) - 358537222, 11) + a12;
        int c12 = rotateLeft(((m3970H(d12, a12, b11) + c11) + this.f6527X[3]) - 722521979, 16) + d12;
        int b12 = rotateLeft(m3970H(c12, d12, a12) + b11 + this.f6527X[6] + 76029189, 23) + c12;
        int a13 = rotateLeft(((m3970H(b12, c12, d12) + a12) + this.f6527X[9]) - 640364487, 4) + b12;
        int d13 = rotateLeft(((m3970H(a13, b12, c12) + d12) + this.f6527X[12]) - 421815835, 11) + a13;
        int c13 = rotateLeft(m3970H(d13, a13, b12) + c12 + this.f6527X[15] + 530742520, 16) + d13;
        int b13 = rotateLeft(((m3970H(c13, d13, a13) + b12) + this.f6527X[2]) - 995338651, 23) + c13;
        int a14 = rotateLeft(((m3971K(b13, c13, d13) + a13) + this.f6527X[0]) - 198630844, 6) + b13;
        int d14 = rotateLeft(m3971K(a14, b13, c13) + d13 + this.f6527X[7] + 1126891415, 10) + a14;
        int c14 = rotateLeft(((m3971K(d14, a14, b13) + c13) + this.f6527X[14]) - 1416354905, 15) + d14;
        int b14 = rotateLeft(((m3971K(c14, d14, a14) + b13) + this.f6527X[5]) - 57434055, 21) + c14;
        int a15 = rotateLeft(m3971K(b14, c14, d14) + a14 + this.f6527X[12] + 1700485571, 6) + b14;
        int d15 = rotateLeft(((m3971K(a15, b14, c14) + d14) + this.f6527X[3]) - 1894986606, 10) + a15;
        int c15 = rotateLeft(((m3971K(d15, a15, b14) + c14) + this.f6527X[10]) - 1051523, 15) + d15;
        int b15 = rotateLeft(((m3971K(c15, d15, a15) + b14) + this.f6527X[1]) - 2054922799, 21) + c15;
        int a16 = rotateLeft(m3971K(b15, c15, d15) + a15 + this.f6527X[8] + 1873313359, 6) + b15;
        int d16 = rotateLeft(((m3971K(a16, b15, c15) + d15) + this.f6527X[15]) - 30611744, 10) + a16;
        int c16 = rotateLeft(((m3971K(d16, a16, b15) + c15) + this.f6527X[6]) - 1560198380, 15) + d16;
        int b16 = rotateLeft(m3971K(c16, d16, a16) + b15 + this.f6527X[13] + 1309151649, 21) + c16;
        int a17 = rotateLeft(((m3971K(b16, c16, d16) + a16) + this.f6527X[4]) - 145523070, 6) + b16;
        int d17 = rotateLeft(((m3971K(a17, b16, c16) + d16) + this.f6527X[11]) - 1120210379, 10) + a17;
        int c17 = rotateLeft(m3971K(d17, a17, b16) + c16 + this.f6527X[2] + 718787259, 15) + d17;
        int b17 = rotateLeft(((m3971K(c17, d17, a17) + b16) + this.f6527X[9]) - 343485551, 21) + c17;
        this.f6523H1 += a17;
        this.f6524H2 += b17;
        this.f6525H3 += c17;
        this.f6526H4 += d17;
        this.xOff = 0;
        for (int i = 0; i != this.f6527X.length; i++) {
            this.f6527X[i] = 0;
        }
    }

    public Memoable copy() {
        return new MD5Digest(this);
    }

    public void reset(Memoable other) {
        copyIn((MD5Digest) other);
    }
}
