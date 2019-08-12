package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

public class RIPEMD128Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 16;

    /* renamed from: H0 */
    private int f6528H0;

    /* renamed from: H1 */
    private int f6529H1;

    /* renamed from: H2 */
    private int f6530H2;

    /* renamed from: H3 */
    private int f6531H3;

    /* renamed from: X */
    private int[] f6532X = new int[16];
    private int xOff;

    public RIPEMD128Digest() {
        reset();
    }

    public RIPEMD128Digest(RIPEMD128Digest t) {
        super((GeneralDigest) t);
        copyIn(t);
    }

    private void copyIn(RIPEMD128Digest t) {
        super.copyIn(t);
        this.f6528H0 = t.f6528H0;
        this.f6529H1 = t.f6529H1;
        this.f6530H2 = t.f6530H2;
        this.f6531H3 = t.f6531H3;
        System.arraycopy(t.f6532X, 0, this.f6532X, 0, t.f6532X.length);
        this.xOff = t.xOff;
    }

    public String getAlgorithmName() {
        return "RIPEMD128";
    }

    public int getDigestSize() {
        return 16;
    }

    /* access modifiers changed from: protected */
    public void processWord(byte[] in, int inOff) {
        int[] iArr = this.f6532X;
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
        this.f6532X[14] = (int) (-1 & bitLength);
        this.f6532X[15] = (int) (bitLength >>> 32);
    }

    private void unpackWord(int word, byte[] out, int outOff) {
        out[outOff] = (byte) word;
        out[outOff + 1] = (byte) (word >>> 8);
        out[outOff + 2] = (byte) (word >>> 16);
        out[outOff + 3] = (byte) (word >>> 24);
    }

    public int doFinal(byte[] out, int outOff) {
        finish();
        unpackWord(this.f6528H0, out, outOff);
        unpackWord(this.f6529H1, out, outOff + 4);
        unpackWord(this.f6530H2, out, outOff + 8);
        unpackWord(this.f6531H3, out, outOff + 12);
        reset();
        return 16;
    }

    public void reset() {
        super.reset();
        this.f6528H0 = 1732584193;
        this.f6529H1 = -271733879;
        this.f6530H2 = -1732584194;
        this.f6531H3 = 271733878;
        this.xOff = 0;
        for (int i = 0; i != this.f6532X.length; i++) {
            this.f6532X[i] = 0;
        }
    }

    /* renamed from: RL */
    private int m3976RL(int x, int n) {
        return (x << n) | (x >>> (32 - n));
    }

    /* renamed from: f1 */
    private int m3977f1(int x, int y, int z) {
        return (x ^ y) ^ z;
    }

    /* renamed from: f2 */
    private int m3978f2(int x, int y, int z) {
        return (x & y) | ((x ^ -1) & z);
    }

    /* renamed from: f3 */
    private int m3979f3(int x, int y, int z) {
        return ((y ^ -1) | x) ^ z;
    }

    /* renamed from: f4 */
    private int m3980f4(int x, int y, int z) {
        return (x & z) | ((z ^ -1) & y);
    }

    /* renamed from: F1 */
    private int m3972F1(int a, int b, int c, int d, int x, int s) {
        return m3976RL(m3977f1(b, c, d) + a + x, s);
    }

    /* renamed from: F2 */
    private int m3973F2(int a, int b, int c, int d, int x, int s) {
        return m3976RL(m3978f2(b, c, d) + a + x + 1518500249, s);
    }

    /* renamed from: F3 */
    private int m3974F3(int a, int b, int c, int d, int x, int s) {
        return m3976RL(m3979f3(b, c, d) + a + x + 1859775393, s);
    }

    /* renamed from: F4 */
    private int m3975F4(int a, int b, int c, int d, int x, int s) {
        return m3976RL(((m3980f4(b, c, d) + a) + x) - 1894007588, s);
    }

    private int FF1(int a, int b, int c, int d, int x, int s) {
        return m3976RL(m3977f1(b, c, d) + a + x, s);
    }

    private int FF2(int a, int b, int c, int d, int x, int s) {
        return m3976RL(m3978f2(b, c, d) + a + x + 1836072691, s);
    }

    private int FF3(int a, int b, int c, int d, int x, int s) {
        return m3976RL(m3979f3(b, c, d) + a + x + 1548603684, s);
    }

    private int FF4(int a, int b, int c, int d, int x, int s) {
        return m3976RL(m3980f4(b, c, d) + a + x + 1352829926, s);
    }

    /* access modifiers changed from: protected */
    public void processBlock() {
        int aa = this.f6528H0;
        int a = aa;
        int bb = this.f6529H1;
        int b = bb;
        int cc = this.f6530H2;
        int c = cc;
        int dd = this.f6531H3;
        int d = dd;
        int a2 = m3972F1(a, b, c, d, this.f6532X[0], 11);
        int d2 = m3972F1(d, a2, b, c, this.f6532X[1], 14);
        int c2 = m3972F1(c, d2, a2, b, this.f6532X[2], 15);
        int b2 = m3972F1(b, c2, d2, a2, this.f6532X[3], 12);
        int a3 = m3972F1(a2, b2, c2, d2, this.f6532X[4], 5);
        int d3 = m3972F1(d2, a3, b2, c2, this.f6532X[5], 8);
        int c3 = m3972F1(c2, d3, a3, b2, this.f6532X[6], 7);
        int b3 = m3972F1(b2, c3, d3, a3, this.f6532X[7], 9);
        int a4 = m3972F1(a3, b3, c3, d3, this.f6532X[8], 11);
        int d4 = m3972F1(d3, a4, b3, c3, this.f6532X[9], 13);
        int c4 = m3972F1(c3, d4, a4, b3, this.f6532X[10], 14);
        int b4 = m3972F1(b3, c4, d4, a4, this.f6532X[11], 15);
        int a5 = m3972F1(a4, b4, c4, d4, this.f6532X[12], 6);
        int d5 = m3972F1(d4, a5, b4, c4, this.f6532X[13], 7);
        int c5 = m3972F1(c4, d5, a5, b4, this.f6532X[14], 9);
        int b5 = m3972F1(b4, c5, d5, a5, this.f6532X[15], 8);
        int a6 = m3973F2(a5, b5, c5, d5, this.f6532X[7], 7);
        int d6 = m3973F2(d5, a6, b5, c5, this.f6532X[4], 6);
        int c6 = m3973F2(c5, d6, a6, b5, this.f6532X[13], 8);
        int b6 = m3973F2(b5, c6, d6, a6, this.f6532X[1], 13);
        int a7 = m3973F2(a6, b6, c6, d6, this.f6532X[10], 11);
        int d7 = m3973F2(d6, a7, b6, c6, this.f6532X[6], 9);
        int c7 = m3973F2(c6, d7, a7, b6, this.f6532X[15], 7);
        int b7 = m3973F2(b6, c7, d7, a7, this.f6532X[3], 15);
        int a8 = m3973F2(a7, b7, c7, d7, this.f6532X[12], 7);
        int d8 = m3973F2(d7, a8, b7, c7, this.f6532X[0], 12);
        int c8 = m3973F2(c7, d8, a8, b7, this.f6532X[9], 15);
        int b8 = m3973F2(b7, c8, d8, a8, this.f6532X[5], 9);
        int a9 = m3973F2(a8, b8, c8, d8, this.f6532X[2], 11);
        int d9 = m3973F2(d8, a9, b8, c8, this.f6532X[14], 7);
        int c9 = m3973F2(c8, d9, a9, b8, this.f6532X[11], 13);
        int b9 = m3973F2(b8, c9, d9, a9, this.f6532X[8], 12);
        int a10 = m3974F3(a9, b9, c9, d9, this.f6532X[3], 11);
        int d10 = m3974F3(d9, a10, b9, c9, this.f6532X[10], 13);
        int c10 = m3974F3(c9, d10, a10, b9, this.f6532X[14], 6);
        int b10 = m3974F3(b9, c10, d10, a10, this.f6532X[4], 7);
        int a11 = m3974F3(a10, b10, c10, d10, this.f6532X[9], 14);
        int d11 = m3974F3(d10, a11, b10, c10, this.f6532X[15], 9);
        int c11 = m3974F3(c10, d11, a11, b10, this.f6532X[8], 13);
        int b11 = m3974F3(b10, c11, d11, a11, this.f6532X[1], 15);
        int a12 = m3974F3(a11, b11, c11, d11, this.f6532X[2], 14);
        int d12 = m3974F3(d11, a12, b11, c11, this.f6532X[7], 8);
        int c12 = m3974F3(c11, d12, a12, b11, this.f6532X[0], 13);
        int b12 = m3974F3(b11, c12, d12, a12, this.f6532X[6], 6);
        int a13 = m3974F3(a12, b12, c12, d12, this.f6532X[13], 5);
        int d13 = m3974F3(d12, a13, b12, c12, this.f6532X[11], 12);
        int c13 = m3974F3(c12, d13, a13, b12, this.f6532X[5], 7);
        int b13 = m3974F3(b12, c13, d13, a13, this.f6532X[12], 5);
        int a14 = m3975F4(a13, b13, c13, d13, this.f6532X[1], 11);
        int d14 = m3975F4(d13, a14, b13, c13, this.f6532X[9], 12);
        int c14 = m3975F4(c13, d14, a14, b13, this.f6532X[11], 14);
        int b14 = m3975F4(b13, c14, d14, a14, this.f6532X[10], 15);
        int a15 = m3975F4(a14, b14, c14, d14, this.f6532X[0], 14);
        int d15 = m3975F4(d14, a15, b14, c14, this.f6532X[8], 15);
        int c15 = m3975F4(c14, d15, a15, b14, this.f6532X[12], 9);
        int b15 = m3975F4(b14, c15, d15, a15, this.f6532X[4], 8);
        int a16 = m3975F4(a15, b15, c15, d15, this.f6532X[13], 9);
        int d16 = m3975F4(d15, a16, b15, c15, this.f6532X[3], 14);
        int c16 = m3975F4(c15, d16, a16, b15, this.f6532X[7], 5);
        int b16 = m3975F4(b15, c16, d16, a16, this.f6532X[15], 6);
        int a17 = m3975F4(a16, b16, c16, d16, this.f6532X[14], 8);
        int d17 = m3975F4(d16, a17, b16, c16, this.f6532X[5], 6);
        int c17 = m3975F4(c16, d17, a17, b16, this.f6532X[6], 5);
        int b17 = m3975F4(b16, c17, d17, a17, this.f6532X[2], 12);
        int aa2 = FF4(aa, bb, cc, dd, this.f6532X[5], 8);
        int dd2 = FF4(dd, aa2, bb, cc, this.f6532X[14], 9);
        int cc2 = FF4(cc, dd2, aa2, bb, this.f6532X[7], 9);
        int bb2 = FF4(bb, cc2, dd2, aa2, this.f6532X[0], 11);
        int aa3 = FF4(aa2, bb2, cc2, dd2, this.f6532X[9], 13);
        int dd3 = FF4(dd2, aa3, bb2, cc2, this.f6532X[2], 15);
        int cc3 = FF4(cc2, dd3, aa3, bb2, this.f6532X[11], 15);
        int bb3 = FF4(bb2, cc3, dd3, aa3, this.f6532X[4], 5);
        int aa4 = FF4(aa3, bb3, cc3, dd3, this.f6532X[13], 7);
        int dd4 = FF4(dd3, aa4, bb3, cc3, this.f6532X[6], 7);
        int cc4 = FF4(cc3, dd4, aa4, bb3, this.f6532X[15], 8);
        int bb4 = FF4(bb3, cc4, dd4, aa4, this.f6532X[8], 11);
        int aa5 = FF4(aa4, bb4, cc4, dd4, this.f6532X[1], 14);
        int dd5 = FF4(dd4, aa5, bb4, cc4, this.f6532X[10], 14);
        int cc5 = FF4(cc4, dd5, aa5, bb4, this.f6532X[3], 12);
        int bb5 = FF4(bb4, cc5, dd5, aa5, this.f6532X[12], 6);
        int aa6 = FF3(aa5, bb5, cc5, dd5, this.f6532X[6], 9);
        int dd6 = FF3(dd5, aa6, bb5, cc5, this.f6532X[11], 13);
        int cc6 = FF3(cc5, dd6, aa6, bb5, this.f6532X[3], 15);
        int bb6 = FF3(bb5, cc6, dd6, aa6, this.f6532X[7], 7);
        int aa7 = FF3(aa6, bb6, cc6, dd6, this.f6532X[0], 12);
        int dd7 = FF3(dd6, aa7, bb6, cc6, this.f6532X[13], 8);
        int cc7 = FF3(cc6, dd7, aa7, bb6, this.f6532X[5], 9);
        int bb7 = FF3(bb6, cc7, dd7, aa7, this.f6532X[10], 11);
        int aa8 = FF3(aa7, bb7, cc7, dd7, this.f6532X[14], 7);
        int dd8 = FF3(dd7, aa8, bb7, cc7, this.f6532X[15], 7);
        int cc8 = FF3(cc7, dd8, aa8, bb7, this.f6532X[8], 12);
        int bb8 = FF3(bb7, cc8, dd8, aa8, this.f6532X[12], 7);
        int aa9 = FF3(aa8, bb8, cc8, dd8, this.f6532X[4], 6);
        int dd9 = FF3(dd8, aa9, bb8, cc8, this.f6532X[9], 15);
        int cc9 = FF3(cc8, dd9, aa9, bb8, this.f6532X[1], 13);
        int bb9 = FF3(bb8, cc9, dd9, aa9, this.f6532X[2], 11);
        int aa10 = FF2(aa9, bb9, cc9, dd9, this.f6532X[15], 9);
        int dd10 = FF2(dd9, aa10, bb9, cc9, this.f6532X[5], 7);
        int cc10 = FF2(cc9, dd10, aa10, bb9, this.f6532X[1], 15);
        int bb10 = FF2(bb9, cc10, dd10, aa10, this.f6532X[3], 11);
        int aa11 = FF2(aa10, bb10, cc10, dd10, this.f6532X[7], 8);
        int dd11 = FF2(dd10, aa11, bb10, cc10, this.f6532X[14], 6);
        int cc11 = FF2(cc10, dd11, aa11, bb10, this.f6532X[6], 6);
        int bb11 = FF2(bb10, cc11, dd11, aa11, this.f6532X[9], 14);
        int aa12 = FF2(aa11, bb11, cc11, dd11, this.f6532X[11], 12);
        int dd12 = FF2(dd11, aa12, bb11, cc11, this.f6532X[8], 13);
        int cc12 = FF2(cc11, dd12, aa12, bb11, this.f6532X[12], 5);
        int bb12 = FF2(bb11, cc12, dd12, aa12, this.f6532X[2], 14);
        int aa13 = FF2(aa12, bb12, cc12, dd12, this.f6532X[10], 13);
        int dd13 = FF2(dd12, aa13, bb12, cc12, this.f6532X[0], 13);
        int cc13 = FF2(cc12, dd13, aa13, bb12, this.f6532X[4], 7);
        int bb13 = FF2(bb12, cc13, dd13, aa13, this.f6532X[13], 5);
        int aa14 = FF1(aa13, bb13, cc13, dd13, this.f6532X[8], 15);
        int dd14 = FF1(dd13, aa14, bb13, cc13, this.f6532X[6], 5);
        int cc14 = FF1(cc13, dd14, aa14, bb13, this.f6532X[4], 8);
        int bb14 = FF1(bb13, cc14, dd14, aa14, this.f6532X[1], 11);
        int aa15 = FF1(aa14, bb14, cc14, dd14, this.f6532X[3], 14);
        int dd15 = FF1(dd14, aa15, bb14, cc14, this.f6532X[11], 14);
        int cc15 = FF1(cc14, dd15, aa15, bb14, this.f6532X[15], 6);
        int bb15 = FF1(bb14, cc15, dd15, aa15, this.f6532X[0], 14);
        int aa16 = FF1(aa15, bb15, cc15, dd15, this.f6532X[5], 6);
        int dd16 = FF1(dd15, aa16, bb15, cc15, this.f6532X[12], 9);
        int cc16 = FF1(cc15, dd16, aa16, bb15, this.f6532X[2], 12);
        int bb16 = FF1(bb15, cc16, dd16, aa16, this.f6532X[13], 9);
        int aa17 = FF1(aa16, bb16, cc16, dd16, this.f6532X[9], 12);
        int dd17 = FF1(dd16, aa17, bb16, cc16, this.f6532X[7], 5);
        int cc17 = FF1(cc16, dd17, aa17, bb16, this.f6532X[10], 15);
        int bb17 = FF1(bb16, cc17, dd17, aa17, this.f6532X[14], 8);
        int dd18 = dd17 + this.f6529H1 + c17;
        this.f6529H1 = this.f6530H2 + d17 + aa17;
        this.f6530H2 = this.f6531H3 + a17 + bb17;
        this.f6531H3 = this.f6528H0 + b17 + cc17;
        this.f6528H0 = dd18;
        this.xOff = 0;
        for (int i = 0; i != this.f6532X.length; i++) {
            this.f6532X[i] = 0;
        }
    }

    public Memoable copy() {
        return new RIPEMD128Digest(this);
    }

    public void reset(Memoable other) {
        copyIn((RIPEMD128Digest) other);
    }
}
