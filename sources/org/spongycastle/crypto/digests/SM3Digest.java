package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

public class SM3Digest extends GeneralDigest {
    private static final int BLOCK_SIZE = 16;
    private static final int DIGEST_LENGTH = 32;

    /* renamed from: T */
    private static final int[] f6589T = new int[64];

    /* renamed from: V */
    private int[] f6590V = new int[8];

    /* renamed from: W */
    private int[] f6591W = new int[68];

    /* renamed from: W1 */
    private int[] f6592W1 = new int[64];
    private int[] inwords = new int[16];
    private int xOff;

    static {
        for (int i = 0; i < 16; i++) {
            f6589T[i] = (2043430169 << i) | (2043430169 >>> (32 - i));
        }
        for (int i2 = 16; i2 < 64; i2++) {
            int n = i2 % 32;
            f6589T[i2] = (2055708042 << n) | (2055708042 >>> (32 - n));
        }
    }

    public SM3Digest() {
        reset();
    }

    public SM3Digest(SM3Digest t) {
        super((GeneralDigest) t);
        copyIn(t);
    }

    private void copyIn(SM3Digest t) {
        System.arraycopy(t.f6590V, 0, this.f6590V, 0, this.f6590V.length);
        System.arraycopy(t.inwords, 0, this.inwords, 0, this.inwords.length);
        this.xOff = t.xOff;
    }

    public String getAlgorithmName() {
        return "SM3";
    }

    public int getDigestSize() {
        return 32;
    }

    public Memoable copy() {
        return new SM3Digest(this);
    }

    public void reset(Memoable other) {
        SM3Digest d = (SM3Digest) other;
        super.copyIn(d);
        copyIn(d);
    }

    public void reset() {
        super.reset();
        this.f6590V[0] = 1937774191;
        this.f6590V[1] = 1226093241;
        this.f6590V[2] = 388252375;
        this.f6590V[3] = -628488704;
        this.f6590V[4] = -1452330820;
        this.f6590V[5] = 372324522;
        this.f6590V[6] = -477237683;
        this.f6590V[7] = -1325724082;
        this.xOff = 0;
    }

    public int doFinal(byte[] out, int outOff) {
        finish();
        Pack.intToBigEndian(this.f6590V[0], out, outOff + 0);
        Pack.intToBigEndian(this.f6590V[1], out, outOff + 4);
        Pack.intToBigEndian(this.f6590V[2], out, outOff + 8);
        Pack.intToBigEndian(this.f6590V[3], out, outOff + 12);
        Pack.intToBigEndian(this.f6590V[4], out, outOff + 16);
        Pack.intToBigEndian(this.f6590V[5], out, outOff + 20);
        Pack.intToBigEndian(this.f6590V[6], out, outOff + 24);
        Pack.intToBigEndian(this.f6590V[7], out, outOff + 28);
        reset();
        return 32;
    }

    /* access modifiers changed from: protected */
    public void processWord(byte[] in, int inOff) {
        int inOff2 = inOff + 1;
        int inOff3 = inOff2 + 1;
        this.inwords[this.xOff] = ((in[inOff] & 255) << 24) | ((in[inOff2] & 255) << 16) | ((in[inOff3] & 255) << 8) | (in[inOff3 + 1] & 255);
        this.xOff++;
        if (this.xOff >= 16) {
            processBlock();
        }
    }

    /* access modifiers changed from: protected */
    public void processLength(long bitLength) {
        if (this.xOff > 14) {
            this.inwords[this.xOff] = 0;
            this.xOff++;
            processBlock();
        }
        while (this.xOff < 14) {
            this.inwords[this.xOff] = 0;
            this.xOff++;
        }
        int[] iArr = this.inwords;
        int i = this.xOff;
        this.xOff = i + 1;
        iArr[i] = (int) (bitLength >>> 32);
        int[] iArr2 = this.inwords;
        int i2 = this.xOff;
        this.xOff = i2 + 1;
        iArr2[i2] = (int) bitLength;
    }

    /* renamed from: P0 */
    private int m4007P0(int x) {
        return (x ^ ((x << 9) | (x >>> 23))) ^ ((x << 17) | (x >>> 15));
    }

    /* renamed from: P1 */
    private int m4008P1(int x) {
        return (x ^ ((x << 15) | (x >>> 17))) ^ ((x << 23) | (x >>> 9));
    }

    private int FF0(int x, int y, int z) {
        return (x ^ y) ^ z;
    }

    private int FF1(int x, int y, int z) {
        return (x & y) | (x & z) | (y & z);
    }

    private int GG0(int x, int y, int z) {
        return (x ^ y) ^ z;
    }

    private int GG1(int x, int y, int z) {
        return (x & y) | ((x ^ -1) & z);
    }

    /* access modifiers changed from: protected */
    public void processBlock() {
        for (int j = 0; j < 16; j++) {
            this.f6591W[j] = this.inwords[j];
        }
        for (int j2 = 16; j2 < 68; j2++) {
            int wj3 = this.f6591W[j2 - 3];
            int r15 = (wj3 << 15) | (wj3 >>> 17);
            int wj13 = this.f6591W[j2 - 13];
            this.f6591W[j2] = (m4008P1((this.f6591W[j2 - 16] ^ this.f6591W[j2 - 9]) ^ r15) ^ ((wj13 << 7) | (wj13 >>> 25))) ^ this.f6591W[j2 - 6];
        }
        for (int j3 = 0; j3 < 64; j3++) {
            this.f6592W1[j3] = this.f6591W[j3] ^ this.f6591W[j3 + 4];
        }
        int A = this.f6590V[0];
        int B = this.f6590V[1];
        int C = this.f6590V[2];
        int D = this.f6590V[3];
        int E = this.f6590V[4];
        int F = this.f6590V[5];
        int G = this.f6590V[6];
        int H = this.f6590V[7];
        for (int j4 = 0; j4 < 16; j4++) {
            int a12 = (A << 12) | (A >>> 20);
            int s1_ = a12 + E + f6589T[j4];
            int SS1 = (s1_ << 7) | (s1_ >>> 25);
            int TT1 = FF0(A, B, C) + D + (SS1 ^ a12) + this.f6592W1[j4];
            int TT2 = GG0(E, F, G) + H + SS1 + this.f6591W[j4];
            D = C;
            C = (B << 9) | (B >>> 23);
            B = A;
            A = TT1;
            H = G;
            G = (F << 19) | (F >>> 13);
            F = E;
            E = m4007P0(TT2);
        }
        for (int j5 = 16; j5 < 64; j5++) {
            int a122 = (A << 12) | (A >>> 20);
            int s1_2 = a122 + E + f6589T[j5];
            int SS12 = (s1_2 << 7) | (s1_2 >>> 25);
            int TT12 = FF1(A, B, C) + D + (SS12 ^ a122) + this.f6592W1[j5];
            int TT22 = GG1(E, F, G) + H + SS12 + this.f6591W[j5];
            D = C;
            C = (B << 9) | (B >>> 23);
            B = A;
            A = TT12;
            H = G;
            G = (F << 19) | (F >>> 13);
            F = E;
            E = m4007P0(TT22);
        }
        int[] iArr = this.f6590V;
        iArr[0] = iArr[0] ^ A;
        int[] iArr2 = this.f6590V;
        iArr2[1] = iArr2[1] ^ B;
        int[] iArr3 = this.f6590V;
        iArr3[2] = iArr3[2] ^ C;
        int[] iArr4 = this.f6590V;
        iArr4[3] = iArr4[3] ^ D;
        int[] iArr5 = this.f6590V;
        iArr5[4] = iArr5[4] ^ E;
        int[] iArr6 = this.f6590V;
        iArr6[5] = iArr6[5] ^ F;
        int[] iArr7 = this.f6590V;
        iArr7[6] = iArr7[6] ^ G;
        int[] iArr8 = this.f6590V;
        iArr8[7] = iArr8[7] ^ H;
        this.xOff = 0;
    }
}
