package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

public class RIPEMD160Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 20;

    /* renamed from: H0 */
    private int f6533H0;

    /* renamed from: H1 */
    private int f6534H1;

    /* renamed from: H2 */
    private int f6535H2;

    /* renamed from: H3 */
    private int f6536H3;

    /* renamed from: H4 */
    private int f6537H4;

    /* renamed from: X */
    private int[] f6538X = new int[16];
    private int xOff;

    public RIPEMD160Digest() {
        reset();
    }

    public RIPEMD160Digest(RIPEMD160Digest t) {
        super((GeneralDigest) t);
        copyIn(t);
    }

    private void copyIn(RIPEMD160Digest t) {
        super.copyIn(t);
        this.f6533H0 = t.f6533H0;
        this.f6534H1 = t.f6534H1;
        this.f6535H2 = t.f6535H2;
        this.f6536H3 = t.f6536H3;
        this.f6537H4 = t.f6537H4;
        System.arraycopy(t.f6538X, 0, this.f6538X, 0, t.f6538X.length);
        this.xOff = t.xOff;
    }

    public String getAlgorithmName() {
        return "RIPEMD160";
    }

    public int getDigestSize() {
        return 20;
    }

    /* access modifiers changed from: protected */
    public void processWord(byte[] in, int inOff) {
        int[] iArr = this.f6538X;
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
        this.f6538X[14] = (int) (-1 & bitLength);
        this.f6538X[15] = (int) (bitLength >>> 32);
    }

    private void unpackWord(int word, byte[] out, int outOff) {
        out[outOff] = (byte) word;
        out[outOff + 1] = (byte) (word >>> 8);
        out[outOff + 2] = (byte) (word >>> 16);
        out[outOff + 3] = (byte) (word >>> 24);
    }

    public int doFinal(byte[] out, int outOff) {
        finish();
        unpackWord(this.f6533H0, out, outOff);
        unpackWord(this.f6534H1, out, outOff + 4);
        unpackWord(this.f6535H2, out, outOff + 8);
        unpackWord(this.f6536H3, out, outOff + 12);
        unpackWord(this.f6537H4, out, outOff + 16);
        reset();
        return 20;
    }

    public void reset() {
        super.reset();
        this.f6533H0 = 1732584193;
        this.f6534H1 = -271733879;
        this.f6535H2 = -1732584194;
        this.f6536H3 = 271733878;
        this.f6537H4 = -1009589776;
        this.xOff = 0;
        for (int i = 0; i != this.f6538X.length; i++) {
            this.f6538X[i] = 0;
        }
    }

    /* renamed from: RL */
    private int m3981RL(int x, int n) {
        return (x << n) | (x >>> (32 - n));
    }

    /* renamed from: f1 */
    private int m3982f1(int x, int y, int z) {
        return (x ^ y) ^ z;
    }

    /* renamed from: f2 */
    private int m3983f2(int x, int y, int z) {
        return (x & y) | ((x ^ -1) & z);
    }

    /* renamed from: f3 */
    private int m3984f3(int x, int y, int z) {
        return ((y ^ -1) | x) ^ z;
    }

    /* renamed from: f4 */
    private int m3985f4(int x, int y, int z) {
        return (x & z) | ((z ^ -1) & y);
    }

    /* renamed from: f5 */
    private int m3986f5(int x, int y, int z) {
        return ((z ^ -1) | y) ^ x;
    }

    /* JADX WARNING: type inference failed for: r14v0, types: [org.spongycastle.crypto.digests.RIPEMD160Digest] */
    /* access modifiers changed from: protected */
    public void processBlock() {
        int aa = this.f6533H0;
        int a = aa;
        int bb = this.f6534H1;
        int b = bb;
        int cc = this.f6535H2;
        int c = cc;
        int dd = this.f6536H3;
        int d = dd;
        int ee = this.f6537H4;
        int e = ee;
        int a2 = m3981RL(m3982f1(b, c, d) + a + this.f6538X[0], 11) + e;
        int c2 = m3981RL(c, 10);
        int e2 = m3981RL(m3982f1(a2, b, c2) + e + this.f6538X[1], 14) + d;
        int b2 = m3981RL(b, 10);
        int d2 = m3981RL(m3982f1(e2, a2, b2) + d + this.f6538X[2], 15) + c2;
        int a3 = m3981RL(a2, 10);
        int c3 = m3981RL(m3982f1(d2, e2, a3) + c2 + this.f6538X[3], 12) + b2;
        int e3 = m3981RL(e2, 10);
        int b3 = m3981RL(m3982f1(c3, d2, e3) + b2 + this.f6538X[4], 5) + a3;
        int d3 = m3981RL(d2, 10);
        int a4 = m3981RL(m3982f1(b3, c3, d3) + a3 + this.f6538X[5], 8) + e3;
        int c4 = m3981RL(c3, 10);
        int e4 = m3981RL(m3982f1(a4, b3, c4) + e3 + this.f6538X[6], 7) + d3;
        int b4 = m3981RL(b3, 10);
        int d4 = m3981RL(m3982f1(e4, a4, b4) + d3 + this.f6538X[7], 9) + c4;
        int a5 = m3981RL(a4, 10);
        int c5 = m3981RL(m3982f1(d4, e4, a5) + c4 + this.f6538X[8], 11) + b4;
        int e5 = m3981RL(e4, 10);
        int b5 = m3981RL(m3982f1(c5, d4, e5) + b4 + this.f6538X[9], 13) + a5;
        int d5 = m3981RL(d4, 10);
        int a6 = m3981RL(m3982f1(b5, c5, d5) + a5 + this.f6538X[10], 14) + e5;
        int c6 = m3981RL(c5, 10);
        int e6 = m3981RL(m3982f1(a6, b5, c6) + e5 + this.f6538X[11], 15) + d5;
        int b6 = m3981RL(b5, 10);
        int d6 = m3981RL(m3982f1(e6, a6, b6) + d5 + this.f6538X[12], 6) + c6;
        int a7 = m3981RL(a6, 10);
        int c7 = m3981RL(m3982f1(d6, e6, a7) + c6 + this.f6538X[13], 7) + b6;
        int e7 = m3981RL(e6, 10);
        int b7 = m3981RL(m3982f1(c7, d6, e7) + b6 + this.f6538X[14], 9) + a7;
        int d7 = m3981RL(d6, 10);
        int a8 = m3981RL(m3982f1(b7, c7, d7) + a7 + this.f6538X[15], 8) + e7;
        int c8 = m3981RL(c7, 10);
        int aa2 = m3981RL(m3986f5(bb, cc, dd) + aa + this.f6538X[5] + 1352829926, 8) + ee;
        int cc2 = m3981RL(cc, 10);
        int ee2 = m3981RL(m3986f5(aa2, bb, cc2) + ee + this.f6538X[14] + 1352829926, 9) + dd;
        int bb2 = m3981RL(bb, 10);
        int dd2 = m3981RL(m3986f5(ee2, aa2, bb2) + dd + this.f6538X[7] + 1352829926, 9) + cc2;
        int aa3 = m3981RL(aa2, 10);
        int cc3 = m3981RL(m3986f5(dd2, ee2, aa3) + cc2 + this.f6538X[0] + 1352829926, 11) + bb2;
        int ee3 = m3981RL(ee2, 10);
        int bb3 = m3981RL(m3986f5(cc3, dd2, ee3) + bb2 + this.f6538X[9] + 1352829926, 13) + aa3;
        int dd3 = m3981RL(dd2, 10);
        int aa4 = m3981RL(m3986f5(bb3, cc3, dd3) + aa3 + this.f6538X[2] + 1352829926, 15) + ee3;
        int cc4 = m3981RL(cc3, 10);
        int ee4 = m3981RL(m3986f5(aa4, bb3, cc4) + ee3 + this.f6538X[11] + 1352829926, 15) + dd3;
        int bb4 = m3981RL(bb3, 10);
        int dd4 = m3981RL(m3986f5(ee4, aa4, bb4) + dd3 + this.f6538X[4] + 1352829926, 5) + cc4;
        int aa5 = m3981RL(aa4, 10);
        int cc5 = m3981RL(m3986f5(dd4, ee4, aa5) + cc4 + this.f6538X[13] + 1352829926, 7) + bb4;
        int ee5 = m3981RL(ee4, 10);
        int bb5 = m3981RL(m3986f5(cc5, dd4, ee5) + bb4 + this.f6538X[6] + 1352829926, 7) + aa5;
        int dd5 = m3981RL(dd4, 10);
        int aa6 = m3981RL(m3986f5(bb5, cc5, dd5) + aa5 + this.f6538X[15] + 1352829926, 8) + ee5;
        int cc6 = m3981RL(cc5, 10);
        int ee6 = m3981RL(m3986f5(aa6, bb5, cc6) + ee5 + this.f6538X[8] + 1352829926, 11) + dd5;
        int bb6 = m3981RL(bb5, 10);
        int dd6 = m3981RL(m3986f5(ee6, aa6, bb6) + dd5 + this.f6538X[1] + 1352829926, 14) + cc6;
        int aa7 = m3981RL(aa6, 10);
        int cc7 = m3981RL(m3986f5(dd6, ee6, aa7) + cc6 + this.f6538X[10] + 1352829926, 14) + bb6;
        int ee7 = m3981RL(ee6, 10);
        int bb7 = m3981RL(m3986f5(cc7, dd6, ee7) + bb6 + this.f6538X[3] + 1352829926, 12) + aa7;
        int dd7 = m3981RL(dd6, 10);
        int aa8 = m3981RL(m3986f5(bb7, cc7, dd7) + aa7 + this.f6538X[12] + 1352829926, 6) + ee7;
        int cc8 = m3981RL(cc7, 10);
        int e8 = m3981RL(m3983f2(a8, b7, c8) + e7 + this.f6538X[7] + 1518500249, 7) + d7;
        int b8 = m3981RL(b7, 10);
        int d8 = m3981RL(m3983f2(e8, a8, b8) + d7 + this.f6538X[4] + 1518500249, 6) + c8;
        int a9 = m3981RL(a8, 10);
        int c9 = m3981RL(m3983f2(d8, e8, a9) + c8 + this.f6538X[13] + 1518500249, 8) + b8;
        int e9 = m3981RL(e8, 10);
        int b9 = m3981RL(m3983f2(c9, d8, e9) + b8 + this.f6538X[1] + 1518500249, 13) + a9;
        int d9 = m3981RL(d8, 10);
        int a10 = m3981RL(m3983f2(b9, c9, d9) + a9 + this.f6538X[10] + 1518500249, 11) + e9;
        int c10 = m3981RL(c9, 10);
        int e10 = m3981RL(m3983f2(a10, b9, c10) + e9 + this.f6538X[6] + 1518500249, 9) + d9;
        int b10 = m3981RL(b9, 10);
        int d10 = m3981RL(m3983f2(e10, a10, b10) + d9 + this.f6538X[15] + 1518500249, 7) + c10;
        int a11 = m3981RL(a10, 10);
        int c11 = m3981RL(m3983f2(d10, e10, a11) + c10 + this.f6538X[3] + 1518500249, 15) + b10;
        int e11 = m3981RL(e10, 10);
        int b11 = m3981RL(m3983f2(c11, d10, e11) + b10 + this.f6538X[12] + 1518500249, 7) + a11;
        int d11 = m3981RL(d10, 10);
        int a12 = m3981RL(m3983f2(b11, c11, d11) + a11 + this.f6538X[0] + 1518500249, 12) + e11;
        int c12 = m3981RL(c11, 10);
        int e12 = m3981RL(m3983f2(a12, b11, c12) + e11 + this.f6538X[9] + 1518500249, 15) + d11;
        int b12 = m3981RL(b11, 10);
        int d12 = m3981RL(m3983f2(e12, a12, b12) + d11 + this.f6538X[5] + 1518500249, 9) + c12;
        int a13 = m3981RL(a12, 10);
        int c13 = m3981RL(m3983f2(d12, e12, a13) + c12 + this.f6538X[2] + 1518500249, 11) + b12;
        int e13 = m3981RL(e12, 10);
        int b13 = m3981RL(m3983f2(c13, d12, e13) + b12 + this.f6538X[14] + 1518500249, 7) + a13;
        int d13 = m3981RL(d12, 10);
        int a14 = m3981RL(m3983f2(b13, c13, d13) + a13 + this.f6538X[11] + 1518500249, 13) + e13;
        int c14 = m3981RL(c13, 10);
        int e14 = m3981RL(m3983f2(a14, b13, c14) + e13 + this.f6538X[8] + 1518500249, 12) + d13;
        int b14 = m3981RL(b13, 10);
        int ee8 = m3981RL(m3985f4(aa8, bb7, cc8) + ee7 + this.f6538X[6] + 1548603684, 9) + dd7;
        int bb8 = m3981RL(bb7, 10);
        int dd8 = m3981RL(m3985f4(ee8, aa8, bb8) + dd7 + this.f6538X[11] + 1548603684, 13) + cc8;
        int aa9 = m3981RL(aa8, 10);
        int cc9 = m3981RL(m3985f4(dd8, ee8, aa9) + cc8 + this.f6538X[3] + 1548603684, 15) + bb8;
        int ee9 = m3981RL(ee8, 10);
        int bb9 = m3981RL(m3985f4(cc9, dd8, ee9) + bb8 + this.f6538X[7] + 1548603684, 7) + aa9;
        int dd9 = m3981RL(dd8, 10);
        int aa10 = m3981RL(m3985f4(bb9, cc9, dd9) + aa9 + this.f6538X[0] + 1548603684, 12) + ee9;
        int cc10 = m3981RL(cc9, 10);
        int ee10 = m3981RL(m3985f4(aa10, bb9, cc10) + ee9 + this.f6538X[13] + 1548603684, 8) + dd9;
        int bb10 = m3981RL(bb9, 10);
        int dd10 = m3981RL(m3985f4(ee10, aa10, bb10) + dd9 + this.f6538X[5] + 1548603684, 9) + cc10;
        int aa11 = m3981RL(aa10, 10);
        int cc11 = m3981RL(m3985f4(dd10, ee10, aa11) + cc10 + this.f6538X[10] + 1548603684, 11) + bb10;
        int ee11 = m3981RL(ee10, 10);
        int bb11 = m3981RL(m3985f4(cc11, dd10, ee11) + bb10 + this.f6538X[14] + 1548603684, 7) + aa11;
        int dd11 = m3981RL(dd10, 10);
        int aa12 = m3981RL(m3985f4(bb11, cc11, dd11) + aa11 + this.f6538X[15] + 1548603684, 7) + ee11;
        int cc12 = m3981RL(cc11, 10);
        int ee12 = m3981RL(m3985f4(aa12, bb11, cc12) + ee11 + this.f6538X[8] + 1548603684, 12) + dd11;
        int bb12 = m3981RL(bb11, 10);
        int dd12 = m3981RL(m3985f4(ee12, aa12, bb12) + dd11 + this.f6538X[12] + 1548603684, 7) + cc12;
        int aa13 = m3981RL(aa12, 10);
        int cc13 = m3981RL(m3985f4(dd12, ee12, aa13) + cc12 + this.f6538X[4] + 1548603684, 6) + bb12;
        int ee13 = m3981RL(ee12, 10);
        int bb13 = m3981RL(m3985f4(cc13, dd12, ee13) + bb12 + this.f6538X[9] + 1548603684, 15) + aa13;
        int dd13 = m3981RL(dd12, 10);
        int aa14 = m3981RL(m3985f4(bb13, cc13, dd13) + aa13 + this.f6538X[1] + 1548603684, 13) + ee13;
        int cc14 = m3981RL(cc13, 10);
        int ee14 = m3981RL(m3985f4(aa14, bb13, cc14) + ee13 + this.f6538X[2] + 1548603684, 11) + dd13;
        int bb14 = m3981RL(bb13, 10);
        int d14 = m3981RL(m3984f3(e14, a14, b14) + d13 + this.f6538X[3] + 1859775393, 11) + c14;
        int a15 = m3981RL(a14, 10);
        int c15 = m3981RL(m3984f3(d14, e14, a15) + c14 + this.f6538X[10] + 1859775393, 13) + b14;
        int e15 = m3981RL(e14, 10);
        int b15 = m3981RL(m3984f3(c15, d14, e15) + b14 + this.f6538X[14] + 1859775393, 6) + a15;
        int d15 = m3981RL(d14, 10);
        int a16 = m3981RL(m3984f3(b15, c15, d15) + a15 + this.f6538X[4] + 1859775393, 7) + e15;
        int c16 = m3981RL(c15, 10);
        int e16 = m3981RL(m3984f3(a16, b15, c16) + e15 + this.f6538X[9] + 1859775393, 14) + d15;
        int b16 = m3981RL(b15, 10);
        int d16 = m3981RL(m3984f3(e16, a16, b16) + d15 + this.f6538X[15] + 1859775393, 9) + c16;
        int a17 = m3981RL(a16, 10);
        int c17 = m3981RL(m3984f3(d16, e16, a17) + c16 + this.f6538X[8] + 1859775393, 13) + b16;
        int e17 = m3981RL(e16, 10);
        int b17 = m3981RL(m3984f3(c17, d16, e17) + b16 + this.f6538X[1] + 1859775393, 15) + a17;
        int d17 = m3981RL(d16, 10);
        int a18 = m3981RL(m3984f3(b17, c17, d17) + a17 + this.f6538X[2] + 1859775393, 14) + e17;
        int c18 = m3981RL(c17, 10);
        int e18 = m3981RL(m3984f3(a18, b17, c18) + e17 + this.f6538X[7] + 1859775393, 8) + d17;
        int b18 = m3981RL(b17, 10);
        int d18 = m3981RL(m3984f3(e18, a18, b18) + d17 + this.f6538X[0] + 1859775393, 13) + c18;
        int a19 = m3981RL(a18, 10);
        int c19 = m3981RL(m3984f3(d18, e18, a19) + c18 + this.f6538X[6] + 1859775393, 6) + b18;
        int e19 = m3981RL(e18, 10);
        int b19 = m3981RL(m3984f3(c19, d18, e19) + b18 + this.f6538X[13] + 1859775393, 5) + a19;
        int d19 = m3981RL(d18, 10);
        int a20 = m3981RL(m3984f3(b19, c19, d19) + a19 + this.f6538X[11] + 1859775393, 12) + e19;
        int c20 = m3981RL(c19, 10);
        int e20 = m3981RL(m3984f3(a20, b19, c20) + e19 + this.f6538X[5] + 1859775393, 7) + d19;
        int b20 = m3981RL(b19, 10);
        int d20 = m3981RL(m3984f3(e20, a20, b20) + d19 + this.f6538X[12] + 1859775393, 5) + c20;
        int a21 = m3981RL(a20, 10);
        int dd14 = m3981RL(m3984f3(ee14, aa14, bb14) + dd13 + this.f6538X[15] + 1836072691, 9) + cc14;
        int aa15 = m3981RL(aa14, 10);
        int cc15 = m3981RL(m3984f3(dd14, ee14, aa15) + cc14 + this.f6538X[5] + 1836072691, 7) + bb14;
        int ee15 = m3981RL(ee14, 10);
        int bb15 = m3981RL(m3984f3(cc15, dd14, ee15) + bb14 + this.f6538X[1] + 1836072691, 15) + aa15;
        int dd15 = m3981RL(dd14, 10);
        int aa16 = m3981RL(m3984f3(bb15, cc15, dd15) + aa15 + this.f6538X[3] + 1836072691, 11) + ee15;
        int cc16 = m3981RL(cc15, 10);
        int ee16 = m3981RL(m3984f3(aa16, bb15, cc16) + ee15 + this.f6538X[7] + 1836072691, 8) + dd15;
        int bb16 = m3981RL(bb15, 10);
        int dd16 = m3981RL(m3984f3(ee16, aa16, bb16) + dd15 + this.f6538X[14] + 1836072691, 6) + cc16;
        int aa17 = m3981RL(aa16, 10);
        int cc17 = m3981RL(m3984f3(dd16, ee16, aa17) + cc16 + this.f6538X[6] + 1836072691, 6) + bb16;
        int ee17 = m3981RL(ee16, 10);
        int bb17 = m3981RL(m3984f3(cc17, dd16, ee17) + bb16 + this.f6538X[9] + 1836072691, 14) + aa17;
        int dd17 = m3981RL(dd16, 10);
        int aa18 = m3981RL(m3984f3(bb17, cc17, dd17) + aa17 + this.f6538X[11] + 1836072691, 12) + ee17;
        int cc18 = m3981RL(cc17, 10);
        int ee18 = m3981RL(m3984f3(aa18, bb17, cc18) + ee17 + this.f6538X[8] + 1836072691, 13) + dd17;
        int bb18 = m3981RL(bb17, 10);
        int dd18 = m3981RL(m3984f3(ee18, aa18, bb18) + dd17 + this.f6538X[12] + 1836072691, 5) + cc18;
        int aa19 = m3981RL(aa18, 10);
        int cc19 = m3981RL(m3984f3(dd18, ee18, aa19) + cc18 + this.f6538X[2] + 1836072691, 14) + bb18;
        int ee19 = m3981RL(ee18, 10);
        int bb19 = m3981RL(m3984f3(cc19, dd18, ee19) + bb18 + this.f6538X[10] + 1836072691, 13) + aa19;
        int dd19 = m3981RL(dd18, 10);
        int aa20 = m3981RL(m3984f3(bb19, cc19, dd19) + aa19 + this.f6538X[0] + 1836072691, 13) + ee19;
        int cc20 = m3981RL(cc19, 10);
        int ee20 = m3981RL(m3984f3(aa20, bb19, cc20) + ee19 + this.f6538X[4] + 1836072691, 7) + dd19;
        int bb20 = m3981RL(bb19, 10);
        int dd20 = m3981RL(m3984f3(ee20, aa20, bb20) + dd19 + this.f6538X[13] + 1836072691, 5) + cc20;
        int aa21 = m3981RL(aa20, 10);
        int c21 = m3981RL(((m3985f4(d20, e20, a21) + c20) + this.f6538X[1]) - 1894007588, 11) + b20;
        int e21 = m3981RL(e20, 10);
        int b21 = m3981RL(((m3985f4(c21, d20, e21) + b20) + this.f6538X[9]) - 1894007588, 12) + a21;
        int d21 = m3981RL(d20, 10);
        int a22 = m3981RL(((m3985f4(b21, c21, d21) + a21) + this.f6538X[11]) - 1894007588, 14) + e21;
        int c22 = m3981RL(c21, 10);
        int e22 = m3981RL(((m3985f4(a22, b21, c22) + e21) + this.f6538X[10]) - 1894007588, 15) + d21;
        int b22 = m3981RL(b21, 10);
        int d22 = m3981RL(((m3985f4(e22, a22, b22) + d21) + this.f6538X[0]) - 1894007588, 14) + c22;
        int a23 = m3981RL(a22, 10);
        int c23 = m3981RL(((m3985f4(d22, e22, a23) + c22) + this.f6538X[8]) - 1894007588, 15) + b22;
        int e23 = m3981RL(e22, 10);
        int b23 = m3981RL(((m3985f4(c23, d22, e23) + b22) + this.f6538X[12]) - 1894007588, 9) + a23;
        int d23 = m3981RL(d22, 10);
        int a24 = m3981RL(((m3985f4(b23, c23, d23) + a23) + this.f6538X[4]) - 1894007588, 8) + e23;
        int c24 = m3981RL(c23, 10);
        int e24 = m3981RL(((m3985f4(a24, b23, c24) + e23) + this.f6538X[13]) - 1894007588, 9) + d23;
        int b24 = m3981RL(b23, 10);
        int d24 = m3981RL(((m3985f4(e24, a24, b24) + d23) + this.f6538X[3]) - 1894007588, 14) + c24;
        int a25 = m3981RL(a24, 10);
        int c25 = m3981RL(((m3985f4(d24, e24, a25) + c24) + this.f6538X[7]) - 1894007588, 5) + b24;
        int e25 = m3981RL(e24, 10);
        int b25 = m3981RL(((m3985f4(c25, d24, e25) + b24) + this.f6538X[15]) - 1894007588, 6) + a25;
        int d25 = m3981RL(d24, 10);
        int a26 = m3981RL(((m3985f4(b25, c25, d25) + a25) + this.f6538X[14]) - 1894007588, 8) + e25;
        int c26 = m3981RL(c25, 10);
        int e26 = m3981RL(((m3985f4(a26, b25, c26) + e25) + this.f6538X[5]) - 1894007588, 6) + d25;
        int b26 = m3981RL(b25, 10);
        int d26 = m3981RL(((m3985f4(e26, a26, b26) + d25) + this.f6538X[6]) - 1894007588, 5) + c26;
        int a27 = m3981RL(a26, 10);
        int c27 = m3981RL(((m3985f4(d26, e26, a27) + c26) + this.f6538X[2]) - 1894007588, 12) + b26;
        int e27 = m3981RL(e26, 10);
        int cc21 = m3981RL(m3983f2(dd20, ee20, aa21) + cc20 + this.f6538X[8] + 2053994217, 15) + bb20;
        int ee21 = m3981RL(ee20, 10);
        int bb21 = m3981RL(m3983f2(cc21, dd20, ee21) + bb20 + this.f6538X[6] + 2053994217, 5) + aa21;
        int dd21 = m3981RL(dd20, 10);
        int aa22 = m3981RL(m3983f2(bb21, cc21, dd21) + aa21 + this.f6538X[4] + 2053994217, 8) + ee21;
        int cc22 = m3981RL(cc21, 10);
        int ee22 = m3981RL(m3983f2(aa22, bb21, cc22) + ee21 + this.f6538X[1] + 2053994217, 11) + dd21;
        int bb22 = m3981RL(bb21, 10);
        int dd22 = m3981RL(m3983f2(ee22, aa22, bb22) + dd21 + this.f6538X[3] + 2053994217, 14) + cc22;
        int aa23 = m3981RL(aa22, 10);
        int cc23 = m3981RL(m3983f2(dd22, ee22, aa23) + cc22 + this.f6538X[11] + 2053994217, 14) + bb22;
        int ee23 = m3981RL(ee22, 10);
        int bb23 = m3981RL(m3983f2(cc23, dd22, ee23) + bb22 + this.f6538X[15] + 2053994217, 6) + aa23;
        int dd23 = m3981RL(dd22, 10);
        int aa24 = m3981RL(m3983f2(bb23, cc23, dd23) + aa23 + this.f6538X[0] + 2053994217, 14) + ee23;
        int cc24 = m3981RL(cc23, 10);
        int ee24 = m3981RL(m3983f2(aa24, bb23, cc24) + ee23 + this.f6538X[5] + 2053994217, 6) + dd23;
        int bb24 = m3981RL(bb23, 10);
        int dd24 = m3981RL(m3983f2(ee24, aa24, bb24) + dd23 + this.f6538X[12] + 2053994217, 9) + cc24;
        int aa25 = m3981RL(aa24, 10);
        int cc25 = m3981RL(m3983f2(dd24, ee24, aa25) + cc24 + this.f6538X[2] + 2053994217, 12) + bb24;
        int ee25 = m3981RL(ee24, 10);
        int bb25 = m3981RL(m3983f2(cc25, dd24, ee25) + bb24 + this.f6538X[13] + 2053994217, 9) + aa25;
        int dd25 = m3981RL(dd24, 10);
        int aa26 = m3981RL(m3983f2(bb25, cc25, dd25) + aa25 + this.f6538X[9] + 2053994217, 12) + ee25;
        int cc26 = m3981RL(cc25, 10);
        int ee26 = m3981RL(m3983f2(aa26, bb25, cc26) + ee25 + this.f6538X[7] + 2053994217, 5) + dd25;
        int bb26 = m3981RL(bb25, 10);
        int dd26 = m3981RL(m3983f2(ee26, aa26, bb26) + dd25 + this.f6538X[10] + 2053994217, 15) + cc26;
        int aa27 = m3981RL(aa26, 10);
        int cc27 = m3981RL(m3983f2(dd26, ee26, aa27) + cc26 + this.f6538X[14] + 2053994217, 8) + bb26;
        int ee27 = m3981RL(ee26, 10);
        int b27 = m3981RL(((m3986f5(c27, d26, e27) + b26) + this.f6538X[4]) - 1454113458, 9) + a27;
        int d27 = m3981RL(d26, 10);
        int a28 = m3981RL(((m3986f5(b27, c27, d27) + a27) + this.f6538X[0]) - 1454113458, 15) + e27;
        int c28 = m3981RL(c27, 10);
        int e28 = m3981RL(((m3986f5(a28, b27, c28) + e27) + this.f6538X[5]) - 1454113458, 5) + d27;
        int b28 = m3981RL(b27, 10);
        int d28 = m3981RL(((m3986f5(e28, a28, b28) + d27) + this.f6538X[9]) - 1454113458, 11) + c28;
        int a29 = m3981RL(a28, 10);
        int c29 = m3981RL(((m3986f5(d28, e28, a29) + c28) + this.f6538X[7]) - 1454113458, 6) + b28;
        int e29 = m3981RL(e28, 10);
        int b29 = m3981RL(((m3986f5(c29, d28, e29) + b28) + this.f6538X[12]) - 1454113458, 8) + a29;
        int d29 = m3981RL(d28, 10);
        int a30 = m3981RL(((m3986f5(b29, c29, d29) + a29) + this.f6538X[2]) - 1454113458, 13) + e29;
        int c30 = m3981RL(c29, 10);
        int e30 = m3981RL(((m3986f5(a30, b29, c30) + e29) + this.f6538X[10]) - 1454113458, 12) + d29;
        int b30 = m3981RL(b29, 10);
        int d30 = m3981RL(((m3986f5(e30, a30, b30) + d29) + this.f6538X[14]) - 1454113458, 5) + c30;
        int a31 = m3981RL(a30, 10);
        int c31 = m3981RL(((m3986f5(d30, e30, a31) + c30) + this.f6538X[1]) - 1454113458, 12) + b30;
        int e31 = m3981RL(e30, 10);
        int b31 = m3981RL(((m3986f5(c31, d30, e31) + b30) + this.f6538X[3]) - 1454113458, 13) + a31;
        int d31 = m3981RL(d30, 10);
        int a32 = m3981RL(((m3986f5(b31, c31, d31) + a31) + this.f6538X[8]) - 1454113458, 14) + e31;
        int c32 = m3981RL(c31, 10);
        int e32 = m3981RL(((m3986f5(a32, b31, c32) + e31) + this.f6538X[11]) - 1454113458, 11) + d31;
        int b32 = m3981RL(b31, 10);
        int d32 = m3981RL(((m3986f5(e32, a32, b32) + d31) + this.f6538X[6]) - 1454113458, 8) + c32;
        int a33 = m3981RL(a32, 10);
        int c33 = m3981RL(((m3986f5(d32, e32, a33) + c32) + this.f6538X[15]) - 1454113458, 5) + b32;
        int e33 = m3981RL(e32, 10);
        int b33 = m3981RL(((m3986f5(c33, d32, e33) + b32) + this.f6538X[13]) - 1454113458, 6) + a33;
        int d33 = m3981RL(d32, 10);
        int bb27 = m3981RL(m3982f1(cc27, dd26, ee27) + bb26 + this.f6538X[12], 8) + aa27;
        int dd27 = m3981RL(dd26, 10);
        int aa28 = m3981RL(m3982f1(bb27, cc27, dd27) + aa27 + this.f6538X[15], 5) + ee27;
        int cc28 = m3981RL(cc27, 10);
        int ee28 = m3981RL(m3982f1(aa28, bb27, cc28) + ee27 + this.f6538X[10], 12) + dd27;
        int bb28 = m3981RL(bb27, 10);
        int dd28 = m3981RL(m3982f1(ee28, aa28, bb28) + dd27 + this.f6538X[4], 9) + cc28;
        int aa29 = m3981RL(aa28, 10);
        int cc29 = m3981RL(m3982f1(dd28, ee28, aa29) + cc28 + this.f6538X[1], 12) + bb28;
        int ee29 = m3981RL(ee28, 10);
        int bb29 = m3981RL(m3982f1(cc29, dd28, ee29) + bb28 + this.f6538X[5], 5) + aa29;
        int dd29 = m3981RL(dd28, 10);
        int aa30 = m3981RL(m3982f1(bb29, cc29, dd29) + aa29 + this.f6538X[8], 14) + ee29;
        int cc30 = m3981RL(cc29, 10);
        int ee30 = m3981RL(m3982f1(aa30, bb29, cc30) + ee29 + this.f6538X[7], 6) + dd29;
        int bb30 = m3981RL(bb29, 10);
        int dd30 = m3981RL(m3982f1(ee30, aa30, bb30) + dd29 + this.f6538X[6], 8) + cc30;
        int aa31 = m3981RL(aa30, 10);
        int cc31 = m3981RL(m3982f1(dd30, ee30, aa31) + cc30 + this.f6538X[2], 13) + bb30;
        int ee31 = m3981RL(ee30, 10);
        int bb31 = m3981RL(m3982f1(cc31, dd30, ee31) + bb30 + this.f6538X[13], 6) + aa31;
        int dd31 = m3981RL(dd30, 10);
        int aa32 = m3981RL(m3982f1(bb31, cc31, dd31) + aa31 + this.f6538X[14], 5) + ee31;
        int cc32 = m3981RL(cc31, 10);
        int ee32 = m3981RL(m3982f1(aa32, bb31, cc32) + ee31 + this.f6538X[0], 15) + dd31;
        int bb32 = m3981RL(bb31, 10);
        int dd32 = m3981RL(m3982f1(ee32, aa32, bb32) + dd31 + this.f6538X[3], 13) + cc32;
        int aa33 = m3981RL(aa32, 10);
        int cc33 = m3981RL(m3982f1(dd32, ee32, aa33) + cc32 + this.f6538X[9], 11) + bb32;
        int ee33 = m3981RL(ee32, 10);
        int bb33 = m3981RL(m3982f1(cc33, dd32, ee33) + bb32 + this.f6538X[11], 11) + aa33;
        int dd33 = m3981RL(dd32, 10) + this.f6534H1 + c33;
        this.f6534H1 = this.f6535H2 + d33 + ee33;
        this.f6535H2 = this.f6536H3 + e33 + aa33;
        this.f6536H3 = this.f6537H4 + a33 + bb33;
        this.f6537H4 = this.f6533H0 + b33 + cc33;
        this.f6533H0 = dd33;
        this.xOff = 0;
        for (int i = 0; i != this.f6538X.length; i++) {
            this.f6538X[i] = 0;
        }
    }

    public Memoable copy() {
        return new RIPEMD160Digest(this);
    }

    public void reset(Memoable other) {
        copyIn((RIPEMD160Digest) other);
    }
}
