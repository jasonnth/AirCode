package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

public class RIPEMD320Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 40;

    /* renamed from: H0 */
    private int f6548H0;

    /* renamed from: H1 */
    private int f6549H1;

    /* renamed from: H2 */
    private int f6550H2;

    /* renamed from: H3 */
    private int f6551H3;

    /* renamed from: H4 */
    private int f6552H4;

    /* renamed from: H5 */
    private int f6553H5;

    /* renamed from: H6 */
    private int f6554H6;

    /* renamed from: H7 */
    private int f6555H7;

    /* renamed from: H8 */
    private int f6556H8;

    /* renamed from: H9 */
    private int f6557H9;

    /* renamed from: X */
    private int[] f6558X = new int[16];
    private int xOff;

    public RIPEMD320Digest() {
        reset();
    }

    public RIPEMD320Digest(RIPEMD320Digest t) {
        super((GeneralDigest) t);
        doCopy(t);
    }

    private void doCopy(RIPEMD320Digest t) {
        super.copyIn(t);
        this.f6548H0 = t.f6548H0;
        this.f6549H1 = t.f6549H1;
        this.f6550H2 = t.f6550H2;
        this.f6551H3 = t.f6551H3;
        this.f6552H4 = t.f6552H4;
        this.f6553H5 = t.f6553H5;
        this.f6554H6 = t.f6554H6;
        this.f6555H7 = t.f6555H7;
        this.f6556H8 = t.f6556H8;
        this.f6557H9 = t.f6557H9;
        System.arraycopy(t.f6558X, 0, this.f6558X, 0, t.f6558X.length);
        this.xOff = t.xOff;
    }

    public String getAlgorithmName() {
        return "RIPEMD320";
    }

    public int getDigestSize() {
        return 40;
    }

    /* access modifiers changed from: protected */
    public void processWord(byte[] in, int inOff) {
        int[] iArr = this.f6558X;
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
        this.f6558X[14] = (int) (-1 & bitLength);
        this.f6558X[15] = (int) (bitLength >>> 32);
    }

    private void unpackWord(int word, byte[] out, int outOff) {
        out[outOff] = (byte) word;
        out[outOff + 1] = (byte) (word >>> 8);
        out[outOff + 2] = (byte) (word >>> 16);
        out[outOff + 3] = (byte) (word >>> 24);
    }

    public int doFinal(byte[] out, int outOff) {
        finish();
        unpackWord(this.f6548H0, out, outOff);
        unpackWord(this.f6549H1, out, outOff + 4);
        unpackWord(this.f6550H2, out, outOff + 8);
        unpackWord(this.f6551H3, out, outOff + 12);
        unpackWord(this.f6552H4, out, outOff + 16);
        unpackWord(this.f6553H5, out, outOff + 20);
        unpackWord(this.f6554H6, out, outOff + 24);
        unpackWord(this.f6555H7, out, outOff + 28);
        unpackWord(this.f6556H8, out, outOff + 32);
        unpackWord(this.f6557H9, out, outOff + 36);
        reset();
        return 40;
    }

    public void reset() {
        super.reset();
        this.f6548H0 = 1732584193;
        this.f6549H1 = -271733879;
        this.f6550H2 = -1732584194;
        this.f6551H3 = 271733878;
        this.f6552H4 = -1009589776;
        this.f6553H5 = 1985229328;
        this.f6554H6 = -19088744;
        this.f6555H7 = -1985229329;
        this.f6556H8 = 19088743;
        this.f6557H9 = 1009589775;
        this.xOff = 0;
        for (int i = 0; i != this.f6558X.length; i++) {
            this.f6558X[i] = 0;
        }
    }

    /* renamed from: RL */
    private int m3996RL(int x, int n) {
        return (x << n) | (x >>> (32 - n));
    }

    /* renamed from: f1 */
    private int m3997f1(int x, int y, int z) {
        return (x ^ y) ^ z;
    }

    /* renamed from: f2 */
    private int m3998f2(int x, int y, int z) {
        return (x & y) | ((x ^ -1) & z);
    }

    /* renamed from: f3 */
    private int m3999f3(int x, int y, int z) {
        return ((y ^ -1) | x) ^ z;
    }

    /* renamed from: f4 */
    private int m4000f4(int x, int y, int z) {
        return (x & z) | ((z ^ -1) & y);
    }

    /* renamed from: f5 */
    private int m4001f5(int x, int y, int z) {
        return ((z ^ -1) | y) ^ x;
    }

    /* JADX WARNING: type inference failed for: r15v0, types: [org.spongycastle.crypto.digests.RIPEMD320Digest] */
    /* access modifiers changed from: protected */
    public void processBlock() {
        int a = this.f6548H0;
        int b = this.f6549H1;
        int c = this.f6550H2;
        int d = this.f6551H3;
        int e = this.f6552H4;
        int aa = this.f6553H5;
        int bb = this.f6554H6;
        int cc = this.f6555H7;
        int dd = this.f6556H8;
        int ee = this.f6557H9;
        int a2 = m3996RL(m3997f1(b, c, d) + a + this.f6558X[0], 11) + e;
        int c2 = m3996RL(c, 10);
        int e2 = m3996RL(m3997f1(a2, b, c2) + e + this.f6558X[1], 14) + d;
        int b2 = m3996RL(b, 10);
        int d2 = m3996RL(m3997f1(e2, a2, b2) + d + this.f6558X[2], 15) + c2;
        int a3 = m3996RL(a2, 10);
        int c3 = m3996RL(m3997f1(d2, e2, a3) + c2 + this.f6558X[3], 12) + b2;
        int e3 = m3996RL(e2, 10);
        int b3 = m3996RL(m3997f1(c3, d2, e3) + b2 + this.f6558X[4], 5) + a3;
        int d3 = m3996RL(d2, 10);
        int a4 = m3996RL(m3997f1(b3, c3, d3) + a3 + this.f6558X[5], 8) + e3;
        int c4 = m3996RL(c3, 10);
        int e4 = m3996RL(m3997f1(a4, b3, c4) + e3 + this.f6558X[6], 7) + d3;
        int b4 = m3996RL(b3, 10);
        int d4 = m3996RL(m3997f1(e4, a4, b4) + d3 + this.f6558X[7], 9) + c4;
        int a5 = m3996RL(a4, 10);
        int c5 = m3996RL(m3997f1(d4, e4, a5) + c4 + this.f6558X[8], 11) + b4;
        int e5 = m3996RL(e4, 10);
        int b5 = m3996RL(m3997f1(c5, d4, e5) + b4 + this.f6558X[9], 13) + a5;
        int d5 = m3996RL(d4, 10);
        int a6 = m3996RL(m3997f1(b5, c5, d5) + a5 + this.f6558X[10], 14) + e5;
        int c6 = m3996RL(c5, 10);
        int e6 = m3996RL(m3997f1(a6, b5, c6) + e5 + this.f6558X[11], 15) + d5;
        int b6 = m3996RL(b5, 10);
        int d6 = m3996RL(m3997f1(e6, a6, b6) + d5 + this.f6558X[12], 6) + c6;
        int a7 = m3996RL(a6, 10);
        int c7 = m3996RL(m3997f1(d6, e6, a7) + c6 + this.f6558X[13], 7) + b6;
        int e7 = m3996RL(e6, 10);
        int b7 = m3996RL(m3997f1(c7, d6, e7) + b6 + this.f6558X[14], 9) + a7;
        int d7 = m3996RL(d6, 10);
        int a8 = m3996RL(m3997f1(b7, c7, d7) + a7 + this.f6558X[15], 8) + e7;
        int c8 = m3996RL(c7, 10);
        int aa2 = m3996RL(m4001f5(bb, cc, dd) + aa + this.f6558X[5] + 1352829926, 8) + ee;
        int cc2 = m3996RL(cc, 10);
        int ee2 = m3996RL(m4001f5(aa2, bb, cc2) + ee + this.f6558X[14] + 1352829926, 9) + dd;
        int bb2 = m3996RL(bb, 10);
        int dd2 = m3996RL(m4001f5(ee2, aa2, bb2) + dd + this.f6558X[7] + 1352829926, 9) + cc2;
        int aa3 = m3996RL(aa2, 10);
        int cc3 = m3996RL(m4001f5(dd2, ee2, aa3) + cc2 + this.f6558X[0] + 1352829926, 11) + bb2;
        int ee3 = m3996RL(ee2, 10);
        int bb3 = m3996RL(m4001f5(cc3, dd2, ee3) + bb2 + this.f6558X[9] + 1352829926, 13) + aa3;
        int dd3 = m3996RL(dd2, 10);
        int aa4 = m3996RL(m4001f5(bb3, cc3, dd3) + aa3 + this.f6558X[2] + 1352829926, 15) + ee3;
        int cc4 = m3996RL(cc3, 10);
        int ee4 = m3996RL(m4001f5(aa4, bb3, cc4) + ee3 + this.f6558X[11] + 1352829926, 15) + dd3;
        int bb4 = m3996RL(bb3, 10);
        int dd4 = m3996RL(m4001f5(ee4, aa4, bb4) + dd3 + this.f6558X[4] + 1352829926, 5) + cc4;
        int aa5 = m3996RL(aa4, 10);
        int cc5 = m3996RL(m4001f5(dd4, ee4, aa5) + cc4 + this.f6558X[13] + 1352829926, 7) + bb4;
        int ee5 = m3996RL(ee4, 10);
        int bb5 = m3996RL(m4001f5(cc5, dd4, ee5) + bb4 + this.f6558X[6] + 1352829926, 7) + aa5;
        int dd5 = m3996RL(dd4, 10);
        int aa6 = m3996RL(m4001f5(bb5, cc5, dd5) + aa5 + this.f6558X[15] + 1352829926, 8) + ee5;
        int cc6 = m3996RL(cc5, 10);
        int ee6 = m3996RL(m4001f5(aa6, bb5, cc6) + ee5 + this.f6558X[8] + 1352829926, 11) + dd5;
        int bb6 = m3996RL(bb5, 10);
        int dd6 = m3996RL(m4001f5(ee6, aa6, bb6) + dd5 + this.f6558X[1] + 1352829926, 14) + cc6;
        int aa7 = m3996RL(aa6, 10);
        int cc7 = m3996RL(m4001f5(dd6, ee6, aa7) + cc6 + this.f6558X[10] + 1352829926, 14) + bb6;
        int ee7 = m3996RL(ee6, 10);
        int bb7 = m3996RL(m4001f5(cc7, dd6, ee7) + bb6 + this.f6558X[3] + 1352829926, 12) + aa7;
        int dd7 = m3996RL(dd6, 10);
        int aa8 = m3996RL(m4001f5(bb7, cc7, dd7) + aa7 + this.f6558X[12] + 1352829926, 6) + ee7;
        int cc8 = m3996RL(cc7, 10);
        int t = a8;
        int a9 = aa8;
        int aa9 = t;
        int e8 = m3996RL(m3998f2(a9, b7, c8) + e7 + this.f6558X[7] + 1518500249, 7) + d7;
        int b8 = m3996RL(b7, 10);
        int d8 = m3996RL(m3998f2(e8, a9, b8) + d7 + this.f6558X[4] + 1518500249, 6) + c8;
        int a10 = m3996RL(a9, 10);
        int c9 = m3996RL(m3998f2(d8, e8, a10) + c8 + this.f6558X[13] + 1518500249, 8) + b8;
        int e9 = m3996RL(e8, 10);
        int b9 = m3996RL(m3998f2(c9, d8, e9) + b8 + this.f6558X[1] + 1518500249, 13) + a10;
        int d9 = m3996RL(d8, 10);
        int a11 = m3996RL(m3998f2(b9, c9, d9) + a10 + this.f6558X[10] + 1518500249, 11) + e9;
        int c10 = m3996RL(c9, 10);
        int e10 = m3996RL(m3998f2(a11, b9, c10) + e9 + this.f6558X[6] + 1518500249, 9) + d9;
        int b10 = m3996RL(b9, 10);
        int d10 = m3996RL(m3998f2(e10, a11, b10) + d9 + this.f6558X[15] + 1518500249, 7) + c10;
        int a12 = m3996RL(a11, 10);
        int c11 = m3996RL(m3998f2(d10, e10, a12) + c10 + this.f6558X[3] + 1518500249, 15) + b10;
        int e11 = m3996RL(e10, 10);
        int b11 = m3996RL(m3998f2(c11, d10, e11) + b10 + this.f6558X[12] + 1518500249, 7) + a12;
        int d11 = m3996RL(d10, 10);
        int a13 = m3996RL(m3998f2(b11, c11, d11) + a12 + this.f6558X[0] + 1518500249, 12) + e11;
        int c12 = m3996RL(c11, 10);
        int e12 = m3996RL(m3998f2(a13, b11, c12) + e11 + this.f6558X[9] + 1518500249, 15) + d11;
        int b12 = m3996RL(b11, 10);
        int d12 = m3996RL(m3998f2(e12, a13, b12) + d11 + this.f6558X[5] + 1518500249, 9) + c12;
        int a14 = m3996RL(a13, 10);
        int c13 = m3996RL(m3998f2(d12, e12, a14) + c12 + this.f6558X[2] + 1518500249, 11) + b12;
        int e13 = m3996RL(e12, 10);
        int b13 = m3996RL(m3998f2(c13, d12, e13) + b12 + this.f6558X[14] + 1518500249, 7) + a14;
        int d13 = m3996RL(d12, 10);
        int a15 = m3996RL(m3998f2(b13, c13, d13) + a14 + this.f6558X[11] + 1518500249, 13) + e13;
        int c14 = m3996RL(c13, 10);
        int e14 = m3996RL(m3998f2(a15, b13, c14) + e13 + this.f6558X[8] + 1518500249, 12) + d13;
        int b14 = m3996RL(b13, 10);
        int ee8 = m3996RL(m4000f4(aa9, bb7, cc8) + ee7 + this.f6558X[6] + 1548603684, 9) + dd7;
        int bb8 = m3996RL(bb7, 10);
        int dd8 = m3996RL(m4000f4(ee8, aa9, bb8) + dd7 + this.f6558X[11] + 1548603684, 13) + cc8;
        int aa10 = m3996RL(aa9, 10);
        int cc9 = m3996RL(m4000f4(dd8, ee8, aa10) + cc8 + this.f6558X[3] + 1548603684, 15) + bb8;
        int ee9 = m3996RL(ee8, 10);
        int bb9 = m3996RL(m4000f4(cc9, dd8, ee9) + bb8 + this.f6558X[7] + 1548603684, 7) + aa10;
        int dd9 = m3996RL(dd8, 10);
        int aa11 = m3996RL(m4000f4(bb9, cc9, dd9) + aa10 + this.f6558X[0] + 1548603684, 12) + ee9;
        int cc10 = m3996RL(cc9, 10);
        int ee10 = m3996RL(m4000f4(aa11, bb9, cc10) + ee9 + this.f6558X[13] + 1548603684, 8) + dd9;
        int bb10 = m3996RL(bb9, 10);
        int dd10 = m3996RL(m4000f4(ee10, aa11, bb10) + dd9 + this.f6558X[5] + 1548603684, 9) + cc10;
        int aa12 = m3996RL(aa11, 10);
        int cc11 = m3996RL(m4000f4(dd10, ee10, aa12) + cc10 + this.f6558X[10] + 1548603684, 11) + bb10;
        int ee11 = m3996RL(ee10, 10);
        int bb11 = m3996RL(m4000f4(cc11, dd10, ee11) + bb10 + this.f6558X[14] + 1548603684, 7) + aa12;
        int dd11 = m3996RL(dd10, 10);
        int aa13 = m3996RL(m4000f4(bb11, cc11, dd11) + aa12 + this.f6558X[15] + 1548603684, 7) + ee11;
        int cc12 = m3996RL(cc11, 10);
        int ee12 = m3996RL(m4000f4(aa13, bb11, cc12) + ee11 + this.f6558X[8] + 1548603684, 12) + dd11;
        int bb12 = m3996RL(bb11, 10);
        int dd12 = m3996RL(m4000f4(ee12, aa13, bb12) + dd11 + this.f6558X[12] + 1548603684, 7) + cc12;
        int aa14 = m3996RL(aa13, 10);
        int cc13 = m3996RL(m4000f4(dd12, ee12, aa14) + cc12 + this.f6558X[4] + 1548603684, 6) + bb12;
        int ee13 = m3996RL(ee12, 10);
        int bb13 = m3996RL(m4000f4(cc13, dd12, ee13) + bb12 + this.f6558X[9] + 1548603684, 15) + aa14;
        int dd13 = m3996RL(dd12, 10);
        int aa15 = m3996RL(m4000f4(bb13, cc13, dd13) + aa14 + this.f6558X[1] + 1548603684, 13) + ee13;
        int cc14 = m3996RL(cc13, 10);
        int ee14 = m3996RL(m4000f4(aa15, bb13, cc14) + ee13 + this.f6558X[2] + 1548603684, 11) + dd13;
        int t2 = b14;
        int b15 = m3996RL(bb13, 10);
        int bb14 = t2;
        int d14 = m3996RL(m3999f3(e14, a15, b15) + d13 + this.f6558X[3] + 1859775393, 11) + c14;
        int a16 = m3996RL(a15, 10);
        int c15 = m3996RL(m3999f3(d14, e14, a16) + c14 + this.f6558X[10] + 1859775393, 13) + b15;
        int e15 = m3996RL(e14, 10);
        int b16 = m3996RL(m3999f3(c15, d14, e15) + b15 + this.f6558X[14] + 1859775393, 6) + a16;
        int d15 = m3996RL(d14, 10);
        int a17 = m3996RL(m3999f3(b16, c15, d15) + a16 + this.f6558X[4] + 1859775393, 7) + e15;
        int c16 = m3996RL(c15, 10);
        int e16 = m3996RL(m3999f3(a17, b16, c16) + e15 + this.f6558X[9] + 1859775393, 14) + d15;
        int b17 = m3996RL(b16, 10);
        int d16 = m3996RL(m3999f3(e16, a17, b17) + d15 + this.f6558X[15] + 1859775393, 9) + c16;
        int a18 = m3996RL(a17, 10);
        int c17 = m3996RL(m3999f3(d16, e16, a18) + c16 + this.f6558X[8] + 1859775393, 13) + b17;
        int e17 = m3996RL(e16, 10);
        int b18 = m3996RL(m3999f3(c17, d16, e17) + b17 + this.f6558X[1] + 1859775393, 15) + a18;
        int d17 = m3996RL(d16, 10);
        int a19 = m3996RL(m3999f3(b18, c17, d17) + a18 + this.f6558X[2] + 1859775393, 14) + e17;
        int c18 = m3996RL(c17, 10);
        int e18 = m3996RL(m3999f3(a19, b18, c18) + e17 + this.f6558X[7] + 1859775393, 8) + d17;
        int b19 = m3996RL(b18, 10);
        int d18 = m3996RL(m3999f3(e18, a19, b19) + d17 + this.f6558X[0] + 1859775393, 13) + c18;
        int a20 = m3996RL(a19, 10);
        int c19 = m3996RL(m3999f3(d18, e18, a20) + c18 + this.f6558X[6] + 1859775393, 6) + b19;
        int e19 = m3996RL(e18, 10);
        int b20 = m3996RL(m3999f3(c19, d18, e19) + b19 + this.f6558X[13] + 1859775393, 5) + a20;
        int d19 = m3996RL(d18, 10);
        int a21 = m3996RL(m3999f3(b20, c19, d19) + a20 + this.f6558X[11] + 1859775393, 12) + e19;
        int c20 = m3996RL(c19, 10);
        int e20 = m3996RL(m3999f3(a21, b20, c20) + e19 + this.f6558X[5] + 1859775393, 7) + d19;
        int b21 = m3996RL(b20, 10);
        int d20 = m3996RL(m3999f3(e20, a21, b21) + d19 + this.f6558X[12] + 1859775393, 5) + c20;
        int a22 = m3996RL(a21, 10);
        int dd14 = m3996RL(m3999f3(ee14, aa15, bb14) + dd13 + this.f6558X[15] + 1836072691, 9) + cc14;
        int aa16 = m3996RL(aa15, 10);
        int cc15 = m3996RL(m3999f3(dd14, ee14, aa16) + cc14 + this.f6558X[5] + 1836072691, 7) + bb14;
        int ee15 = m3996RL(ee14, 10);
        int bb15 = m3996RL(m3999f3(cc15, dd14, ee15) + bb14 + this.f6558X[1] + 1836072691, 15) + aa16;
        int dd15 = m3996RL(dd14, 10);
        int aa17 = m3996RL(m3999f3(bb15, cc15, dd15) + aa16 + this.f6558X[3] + 1836072691, 11) + ee15;
        int cc16 = m3996RL(cc15, 10);
        int ee16 = m3996RL(m3999f3(aa17, bb15, cc16) + ee15 + this.f6558X[7] + 1836072691, 8) + dd15;
        int bb16 = m3996RL(bb15, 10);
        int dd16 = m3996RL(m3999f3(ee16, aa17, bb16) + dd15 + this.f6558X[14] + 1836072691, 6) + cc16;
        int aa18 = m3996RL(aa17, 10);
        int cc17 = m3996RL(m3999f3(dd16, ee16, aa18) + cc16 + this.f6558X[6] + 1836072691, 6) + bb16;
        int ee17 = m3996RL(ee16, 10);
        int bb17 = m3996RL(m3999f3(cc17, dd16, ee17) + bb16 + this.f6558X[9] + 1836072691, 14) + aa18;
        int dd17 = m3996RL(dd16, 10);
        int aa19 = m3996RL(m3999f3(bb17, cc17, dd17) + aa18 + this.f6558X[11] + 1836072691, 12) + ee17;
        int cc18 = m3996RL(cc17, 10);
        int ee18 = m3996RL(m3999f3(aa19, bb17, cc18) + ee17 + this.f6558X[8] + 1836072691, 13) + dd17;
        int bb18 = m3996RL(bb17, 10);
        int dd18 = m3996RL(m3999f3(ee18, aa19, bb18) + dd17 + this.f6558X[12] + 1836072691, 5) + cc18;
        int aa20 = m3996RL(aa19, 10);
        int cc19 = m3996RL(m3999f3(dd18, ee18, aa20) + cc18 + this.f6558X[2] + 1836072691, 14) + bb18;
        int ee19 = m3996RL(ee18, 10);
        int bb19 = m3996RL(m3999f3(cc19, dd18, ee19) + bb18 + this.f6558X[10] + 1836072691, 13) + aa20;
        int dd19 = m3996RL(dd18, 10);
        int aa21 = m3996RL(m3999f3(bb19, cc19, dd19) + aa20 + this.f6558X[0] + 1836072691, 13) + ee19;
        int cc20 = m3996RL(cc19, 10);
        int ee20 = m3996RL(m3999f3(aa21, bb19, cc20) + ee19 + this.f6558X[4] + 1836072691, 7) + dd19;
        int bb20 = m3996RL(bb19, 10);
        int dd20 = m3996RL(m3999f3(ee20, aa21, bb20) + dd19 + this.f6558X[13] + 1836072691, 5) + cc20;
        int aa22 = m3996RL(aa21, 10);
        int t3 = c20;
        int c21 = cc20;
        int cc21 = t3;
        int c22 = m3996RL(((m4000f4(d20, e20, a22) + c21) + this.f6558X[1]) - 1894007588, 11) + b21;
        int e21 = m3996RL(e20, 10);
        int b22 = m3996RL(((m4000f4(c22, d20, e21) + b21) + this.f6558X[9]) - 1894007588, 12) + a22;
        int d21 = m3996RL(d20, 10);
        int a23 = m3996RL(((m4000f4(b22, c22, d21) + a22) + this.f6558X[11]) - 1894007588, 14) + e21;
        int c23 = m3996RL(c22, 10);
        int e22 = m3996RL(((m4000f4(a23, b22, c23) + e21) + this.f6558X[10]) - 1894007588, 15) + d21;
        int b23 = m3996RL(b22, 10);
        int d22 = m3996RL(((m4000f4(e22, a23, b23) + d21) + this.f6558X[0]) - 1894007588, 14) + c23;
        int a24 = m3996RL(a23, 10);
        int c24 = m3996RL(((m4000f4(d22, e22, a24) + c23) + this.f6558X[8]) - 1894007588, 15) + b23;
        int e23 = m3996RL(e22, 10);
        int b24 = m3996RL(((m4000f4(c24, d22, e23) + b23) + this.f6558X[12]) - 1894007588, 9) + a24;
        int d23 = m3996RL(d22, 10);
        int a25 = m3996RL(((m4000f4(b24, c24, d23) + a24) + this.f6558X[4]) - 1894007588, 8) + e23;
        int c25 = m3996RL(c24, 10);
        int e24 = m3996RL(((m4000f4(a25, b24, c25) + e23) + this.f6558X[13]) - 1894007588, 9) + d23;
        int b25 = m3996RL(b24, 10);
        int d24 = m3996RL(((m4000f4(e24, a25, b25) + d23) + this.f6558X[3]) - 1894007588, 14) + c25;
        int a26 = m3996RL(a25, 10);
        int c26 = m3996RL(((m4000f4(d24, e24, a26) + c25) + this.f6558X[7]) - 1894007588, 5) + b25;
        int e25 = m3996RL(e24, 10);
        int b26 = m3996RL(((m4000f4(c26, d24, e25) + b25) + this.f6558X[15]) - 1894007588, 6) + a26;
        int d25 = m3996RL(d24, 10);
        int a27 = m3996RL(((m4000f4(b26, c26, d25) + a26) + this.f6558X[14]) - 1894007588, 8) + e25;
        int c27 = m3996RL(c26, 10);
        int e26 = m3996RL(((m4000f4(a27, b26, c27) + e25) + this.f6558X[5]) - 1894007588, 6) + d25;
        int b27 = m3996RL(b26, 10);
        int d26 = m3996RL(((m4000f4(e26, a27, b27) + d25) + this.f6558X[6]) - 1894007588, 5) + c27;
        int a28 = m3996RL(a27, 10);
        int c28 = m3996RL(((m4000f4(d26, e26, a28) + c27) + this.f6558X[2]) - 1894007588, 12) + b27;
        int e27 = m3996RL(e26, 10);
        int cc22 = m3996RL(m3998f2(dd20, ee20, aa22) + cc21 + this.f6558X[8] + 2053994217, 15) + bb20;
        int ee21 = m3996RL(ee20, 10);
        int bb21 = m3996RL(m3998f2(cc22, dd20, ee21) + bb20 + this.f6558X[6] + 2053994217, 5) + aa22;
        int dd21 = m3996RL(dd20, 10);
        int aa23 = m3996RL(m3998f2(bb21, cc22, dd21) + aa22 + this.f6558X[4] + 2053994217, 8) + ee21;
        int cc23 = m3996RL(cc22, 10);
        int ee22 = m3996RL(m3998f2(aa23, bb21, cc23) + ee21 + this.f6558X[1] + 2053994217, 11) + dd21;
        int bb22 = m3996RL(bb21, 10);
        int dd22 = m3996RL(m3998f2(ee22, aa23, bb22) + dd21 + this.f6558X[3] + 2053994217, 14) + cc23;
        int aa24 = m3996RL(aa23, 10);
        int cc24 = m3996RL(m3998f2(dd22, ee22, aa24) + cc23 + this.f6558X[11] + 2053994217, 14) + bb22;
        int ee23 = m3996RL(ee22, 10);
        int bb23 = m3996RL(m3998f2(cc24, dd22, ee23) + bb22 + this.f6558X[15] + 2053994217, 6) + aa24;
        int dd23 = m3996RL(dd22, 10);
        int aa25 = m3996RL(m3998f2(bb23, cc24, dd23) + aa24 + this.f6558X[0] + 2053994217, 14) + ee23;
        int cc25 = m3996RL(cc24, 10);
        int ee24 = m3996RL(m3998f2(aa25, bb23, cc25) + ee23 + this.f6558X[5] + 2053994217, 6) + dd23;
        int bb24 = m3996RL(bb23, 10);
        int dd24 = m3996RL(m3998f2(ee24, aa25, bb24) + dd23 + this.f6558X[12] + 2053994217, 9) + cc25;
        int aa26 = m3996RL(aa25, 10);
        int cc26 = m3996RL(m3998f2(dd24, ee24, aa26) + cc25 + this.f6558X[2] + 2053994217, 12) + bb24;
        int ee25 = m3996RL(ee24, 10);
        int bb25 = m3996RL(m3998f2(cc26, dd24, ee25) + bb24 + this.f6558X[13] + 2053994217, 9) + aa26;
        int dd25 = m3996RL(dd24, 10);
        int aa27 = m3996RL(m3998f2(bb25, cc26, dd25) + aa26 + this.f6558X[9] + 2053994217, 12) + ee25;
        int cc27 = m3996RL(cc26, 10);
        int ee26 = m3996RL(m3998f2(aa27, bb25, cc27) + ee25 + this.f6558X[7] + 2053994217, 5) + dd25;
        int bb26 = m3996RL(bb25, 10);
        int dd26 = m3996RL(m3998f2(ee26, aa27, bb26) + dd25 + this.f6558X[10] + 2053994217, 15) + cc27;
        int aa28 = m3996RL(aa27, 10);
        int cc28 = m3996RL(m3998f2(dd26, ee26, aa28) + cc27 + this.f6558X[14] + 2053994217, 8) + bb26;
        int ee27 = m3996RL(ee26, 10);
        int t4 = d26;
        int d27 = dd26;
        int dd27 = t4;
        int b28 = m3996RL(((m4001f5(c28, d27, e27) + b27) + this.f6558X[4]) - 1454113458, 9) + a28;
        int d28 = m3996RL(d27, 10);
        int a29 = m3996RL(((m4001f5(b28, c28, d28) + a28) + this.f6558X[0]) - 1454113458, 15) + e27;
        int c29 = m3996RL(c28, 10);
        int e28 = m3996RL(((m4001f5(a29, b28, c29) + e27) + this.f6558X[5]) - 1454113458, 5) + d28;
        int b29 = m3996RL(b28, 10);
        int d29 = m3996RL(((m4001f5(e28, a29, b29) + d28) + this.f6558X[9]) - 1454113458, 11) + c29;
        int a30 = m3996RL(a29, 10);
        int c30 = m3996RL(((m4001f5(d29, e28, a30) + c29) + this.f6558X[7]) - 1454113458, 6) + b29;
        int e29 = m3996RL(e28, 10);
        int b30 = m3996RL(((m4001f5(c30, d29, e29) + b29) + this.f6558X[12]) - 1454113458, 8) + a30;
        int d30 = m3996RL(d29, 10);
        int a31 = m3996RL(((m4001f5(b30, c30, d30) + a30) + this.f6558X[2]) - 1454113458, 13) + e29;
        int c31 = m3996RL(c30, 10);
        int e30 = m3996RL(((m4001f5(a31, b30, c31) + e29) + this.f6558X[10]) - 1454113458, 12) + d30;
        int b31 = m3996RL(b30, 10);
        int d31 = m3996RL(((m4001f5(e30, a31, b31) + d30) + this.f6558X[14]) - 1454113458, 5) + c31;
        int a32 = m3996RL(a31, 10);
        int c32 = m3996RL(((m4001f5(d31, e30, a32) + c31) + this.f6558X[1]) - 1454113458, 12) + b31;
        int e31 = m3996RL(e30, 10);
        int b32 = m3996RL(((m4001f5(c32, d31, e31) + b31) + this.f6558X[3]) - 1454113458, 13) + a32;
        int d32 = m3996RL(d31, 10);
        int a33 = m3996RL(((m4001f5(b32, c32, d32) + a32) + this.f6558X[8]) - 1454113458, 14) + e31;
        int c33 = m3996RL(c32, 10);
        int e32 = m3996RL(((m4001f5(a33, b32, c33) + e31) + this.f6558X[11]) - 1454113458, 11) + d32;
        int b33 = m3996RL(b32, 10);
        int d33 = m3996RL(((m4001f5(e32, a33, b33) + d32) + this.f6558X[6]) - 1454113458, 8) + c33;
        int a34 = m3996RL(a33, 10);
        int c34 = m3996RL(((m4001f5(d33, e32, a34) + c33) + this.f6558X[15]) - 1454113458, 5) + b33;
        int e33 = m3996RL(e32, 10);
        int b34 = m3996RL(((m4001f5(c34, d33, e33) + b33) + this.f6558X[13]) - 1454113458, 6) + a34;
        int d34 = m3996RL(d33, 10);
        int bb27 = m3996RL(m3997f1(cc28, dd27, ee27) + bb26 + this.f6558X[12], 8) + aa28;
        int dd28 = m3996RL(dd27, 10);
        int aa29 = m3996RL(m3997f1(bb27, cc28, dd28) + aa28 + this.f6558X[15], 5) + ee27;
        int cc29 = m3996RL(cc28, 10);
        int ee28 = m3996RL(m3997f1(aa29, bb27, cc29) + ee27 + this.f6558X[10], 12) + dd28;
        int bb28 = m3996RL(bb27, 10);
        int dd29 = m3996RL(m3997f1(ee28, aa29, bb28) + dd28 + this.f6558X[4], 9) + cc29;
        int aa30 = m3996RL(aa29, 10);
        int cc30 = m3996RL(m3997f1(dd29, ee28, aa30) + cc29 + this.f6558X[1], 12) + bb28;
        int ee29 = m3996RL(ee28, 10);
        int bb29 = m3996RL(m3997f1(cc30, dd29, ee29) + bb28 + this.f6558X[5], 5) + aa30;
        int dd30 = m3996RL(dd29, 10);
        int aa31 = m3996RL(m3997f1(bb29, cc30, dd30) + aa30 + this.f6558X[8], 14) + ee29;
        int cc31 = m3996RL(cc30, 10);
        int ee30 = m3996RL(m3997f1(aa31, bb29, cc31) + ee29 + this.f6558X[7], 6) + dd30;
        int bb30 = m3996RL(bb29, 10);
        int dd31 = m3996RL(m3997f1(ee30, aa31, bb30) + dd30 + this.f6558X[6], 8) + cc31;
        int aa32 = m3996RL(aa31, 10);
        int cc32 = m3996RL(m3997f1(dd31, ee30, aa32) + cc31 + this.f6558X[2], 13) + bb30;
        int ee31 = m3996RL(ee30, 10);
        int bb31 = m3996RL(m3997f1(cc32, dd31, ee31) + bb30 + this.f6558X[13], 6) + aa32;
        int dd32 = m3996RL(dd31, 10);
        int aa33 = m3996RL(m3997f1(bb31, cc32, dd32) + aa32 + this.f6558X[14], 5) + ee31;
        int cc33 = m3996RL(cc32, 10);
        int ee32 = m3996RL(m3997f1(aa33, bb31, cc33) + ee31 + this.f6558X[0], 15) + dd32;
        int bb32 = m3996RL(bb31, 10);
        int dd33 = m3996RL(m3997f1(ee32, aa33, bb32) + dd32 + this.f6558X[3], 13) + cc33;
        int aa34 = m3996RL(aa33, 10);
        int cc34 = m3996RL(m3997f1(dd33, ee32, aa34) + cc33 + this.f6558X[9], 11) + bb32;
        int ee33 = m3996RL(ee32, 10);
        int bb33 = m3996RL(m3997f1(cc34, dd33, ee33) + bb32 + this.f6558X[11], 11) + aa34;
        int dd34 = m3996RL(dd33, 10);
        this.f6548H0 += a34;
        this.f6549H1 += b34;
        this.f6550H2 += c34;
        this.f6551H3 += d34;
        this.f6552H4 += ee33;
        this.f6553H5 += aa34;
        this.f6554H6 += bb33;
        this.f6555H7 += cc34;
        this.f6556H8 += dd34;
        this.f6557H9 += e33;
        this.xOff = 0;
        for (int i = 0; i != this.f6558X.length; i++) {
            this.f6558X[i] = 0;
        }
    }

    public Memoable copy() {
        return new RIPEMD320Digest(this);
    }

    public void reset(Memoable other) {
        doCopy((RIPEMD320Digest) other);
    }
}
