package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

public class SHA1Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 20;

    /* renamed from: Y1 */
    private static final int f6559Y1 = 1518500249;

    /* renamed from: Y2 */
    private static final int f6560Y2 = 1859775393;

    /* renamed from: Y3 */
    private static final int f6561Y3 = -1894007588;

    /* renamed from: Y4 */
    private static final int f6562Y4 = -899497514;

    /* renamed from: H1 */
    private int f6563H1;

    /* renamed from: H2 */
    private int f6564H2;

    /* renamed from: H3 */
    private int f6565H3;

    /* renamed from: H4 */
    private int f6566H4;

    /* renamed from: H5 */
    private int f6567H5;

    /* renamed from: X */
    private int[] f6568X = new int[80];
    private int xOff;

    public SHA1Digest() {
        reset();
    }

    public SHA1Digest(SHA1Digest t) {
        super((GeneralDigest) t);
        copyIn(t);
    }

    public SHA1Digest(byte[] encodedState) {
        super(encodedState);
        this.f6563H1 = Pack.bigEndianToInt(encodedState, 16);
        this.f6564H2 = Pack.bigEndianToInt(encodedState, 20);
        this.f6565H3 = Pack.bigEndianToInt(encodedState, 24);
        this.f6566H4 = Pack.bigEndianToInt(encodedState, 28);
        this.f6567H5 = Pack.bigEndianToInt(encodedState, 32);
        this.xOff = Pack.bigEndianToInt(encodedState, 36);
        for (int i = 0; i != this.xOff; i++) {
            this.f6568X[i] = Pack.bigEndianToInt(encodedState, (i * 4) + 40);
        }
    }

    private void copyIn(SHA1Digest t) {
        this.f6563H1 = t.f6563H1;
        this.f6564H2 = t.f6564H2;
        this.f6565H3 = t.f6565H3;
        this.f6566H4 = t.f6566H4;
        this.f6567H5 = t.f6567H5;
        System.arraycopy(t.f6568X, 0, this.f6568X, 0, t.f6568X.length);
        this.xOff = t.xOff;
    }

    public String getAlgorithmName() {
        return "SHA-1";
    }

    public int getDigestSize() {
        return 20;
    }

    /* access modifiers changed from: protected */
    public void processWord(byte[] in, int inOff) {
        int inOff2 = inOff + 1;
        int inOff3 = inOff2 + 1;
        this.f6568X[this.xOff] = (in[inOff] << 24) | ((in[inOff2] & 255) << 16) | ((in[inOff3] & 255) << 8) | (in[inOff3 + 1] & 255);
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
        this.f6568X[14] = (int) (bitLength >>> 32);
        this.f6568X[15] = (int) (-1 & bitLength);
    }

    public int doFinal(byte[] out, int outOff) {
        finish();
        Pack.intToBigEndian(this.f6563H1, out, outOff);
        Pack.intToBigEndian(this.f6564H2, out, outOff + 4);
        Pack.intToBigEndian(this.f6565H3, out, outOff + 8);
        Pack.intToBigEndian(this.f6566H4, out, outOff + 12);
        Pack.intToBigEndian(this.f6567H5, out, outOff + 16);
        reset();
        return 20;
    }

    public void reset() {
        super.reset();
        this.f6563H1 = 1732584193;
        this.f6564H2 = -271733879;
        this.f6565H3 = -1732584194;
        this.f6566H4 = 271733878;
        this.f6567H5 = -1009589776;
        this.xOff = 0;
        for (int i = 0; i != this.f6568X.length; i++) {
            this.f6568X[i] = 0;
        }
    }

    /* renamed from: f */
    private int m4002f(int u, int v, int w) {
        return (u & v) | ((u ^ -1) & w);
    }

    /* renamed from: h */
    private int m4004h(int u, int v, int w) {
        return (u ^ v) ^ w;
    }

    /* renamed from: g */
    private int m4003g(int u, int v, int w) {
        return (u & v) | (u & w) | (v & w);
    }

    /* access modifiers changed from: protected */
    public void processBlock() {
        int idx;
        for (int i = 16; i < 80; i++) {
            int t = ((this.f6568X[i - 3] ^ this.f6568X[i - 8]) ^ this.f6568X[i - 14]) ^ this.f6568X[i - 16];
            this.f6568X[i] = (t << 1) | (t >>> 31);
        }
        int A = this.f6563H1;
        int B = this.f6564H2;
        int C = this.f6565H3;
        int D = this.f6566H4;
        int E = this.f6567H5;
        int idx2 = 0;
        int j = 0;
        while (true) {
            idx = idx2;
            if (j >= 4) {
                break;
            }
            int idx3 = idx + 1;
            int E2 = E + ((A << 5) | (A >>> 27)) + m4002f(B, C, D) + this.f6568X[idx] + f6559Y1;
            int B2 = (B << 30) | (B >>> 2);
            int idx4 = idx3 + 1;
            int D2 = D + ((E2 << 5) | (E2 >>> 27)) + m4002f(A, B2, C) + this.f6568X[idx3] + f6559Y1;
            int A2 = (A << 30) | (A >>> 2);
            int idx5 = idx4 + 1;
            int C2 = C + ((D2 << 5) | (D2 >>> 27)) + m4002f(E2, A2, B2) + this.f6568X[idx4] + f6559Y1;
            E = (E2 << 30) | (E2 >>> 2);
            int idx6 = idx5 + 1;
            B = B2 + ((C2 << 5) | (C2 >>> 27)) + m4002f(D2, E, A2) + this.f6568X[idx5] + f6559Y1;
            D = (D2 << 30) | (D2 >>> 2);
            idx2 = idx6 + 1;
            A = A2 + ((B << 5) | (B >>> 27)) + m4002f(C2, D, E) + this.f6568X[idx6] + f6559Y1;
            C = (C2 << 30) | (C2 >>> 2);
            j++;
        }
        int j2 = 0;
        while (j2 < 4) {
            int idx7 = idx + 1;
            int E3 = E + ((A << 5) | (A >>> 27)) + m4004h(B, C, D) + this.f6568X[idx] + f6560Y2;
            int B3 = (B << 30) | (B >>> 2);
            int idx8 = idx7 + 1;
            int D3 = D + ((E3 << 5) | (E3 >>> 27)) + m4004h(A, B3, C) + this.f6568X[idx7] + f6560Y2;
            int A3 = (A << 30) | (A >>> 2);
            int idx9 = idx8 + 1;
            int C3 = C + ((D3 << 5) | (D3 >>> 27)) + m4004h(E3, A3, B3) + this.f6568X[idx8] + f6560Y2;
            E = (E3 << 30) | (E3 >>> 2);
            int idx10 = idx9 + 1;
            B = B3 + ((C3 << 5) | (C3 >>> 27)) + m4004h(D3, E, A3) + this.f6568X[idx9] + f6560Y2;
            D = (D3 << 30) | (D3 >>> 2);
            A = A3 + ((B << 5) | (B >>> 27)) + m4004h(C3, D, E) + this.f6568X[idx10] + f6560Y2;
            C = (C3 << 30) | (C3 >>> 2);
            j2++;
            idx = idx10 + 1;
        }
        int j3 = 0;
        while (j3 < 4) {
            int idx11 = idx + 1;
            int E4 = E + ((A << 5) | (A >>> 27)) + m4003g(B, C, D) + this.f6568X[idx] + f6561Y3;
            int B4 = (B << 30) | (B >>> 2);
            int idx12 = idx11 + 1;
            int D4 = D + ((E4 << 5) | (E4 >>> 27)) + m4003g(A, B4, C) + this.f6568X[idx11] + f6561Y3;
            int A4 = (A << 30) | (A >>> 2);
            int idx13 = idx12 + 1;
            int C4 = C + ((D4 << 5) | (D4 >>> 27)) + m4003g(E4, A4, B4) + this.f6568X[idx12] + f6561Y3;
            E = (E4 << 30) | (E4 >>> 2);
            int idx14 = idx13 + 1;
            B = B4 + ((C4 << 5) | (C4 >>> 27)) + m4003g(D4, E, A4) + this.f6568X[idx13] + f6561Y3;
            D = (D4 << 30) | (D4 >>> 2);
            A = A4 + ((B << 5) | (B >>> 27)) + m4003g(C4, D, E) + this.f6568X[idx14] + f6561Y3;
            C = (C4 << 30) | (C4 >>> 2);
            j3++;
            idx = idx14 + 1;
        }
        int j4 = 0;
        while (j4 <= 3) {
            int idx15 = idx + 1;
            int E5 = E + ((A << 5) | (A >>> 27)) + m4004h(B, C, D) + this.f6568X[idx] + f6562Y4;
            int B5 = (B << 30) | (B >>> 2);
            int idx16 = idx15 + 1;
            int D5 = D + ((E5 << 5) | (E5 >>> 27)) + m4004h(A, B5, C) + this.f6568X[idx15] + f6562Y4;
            int A5 = (A << 30) | (A >>> 2);
            int idx17 = idx16 + 1;
            int C5 = C + ((D5 << 5) | (D5 >>> 27)) + m4004h(E5, A5, B5) + this.f6568X[idx16] + f6562Y4;
            E = (E5 << 30) | (E5 >>> 2);
            int idx18 = idx17 + 1;
            B = B5 + ((C5 << 5) | (C5 >>> 27)) + m4004h(D5, E, A5) + this.f6568X[idx17] + f6562Y4;
            D = (D5 << 30) | (D5 >>> 2);
            A = A5 + ((B << 5) | (B >>> 27)) + m4004h(C5, D, E) + this.f6568X[idx18] + f6562Y4;
            C = (C5 << 30) | (C5 >>> 2);
            j4++;
            idx = idx18 + 1;
        }
        this.f6563H1 += A;
        this.f6564H2 += B;
        this.f6565H3 += C;
        this.f6566H4 += D;
        this.f6567H5 += E;
        this.xOff = 0;
        for (int i2 = 0; i2 < 16; i2++) {
            this.f6568X[i2] = 0;
        }
    }

    public Memoable copy() {
        return new SHA1Digest(this);
    }

    public void reset(Memoable other) {
        SHA1Digest d = (SHA1Digest) other;
        super.copyIn(d);
        copyIn(d);
    }

    public byte[] getEncodedState() {
        byte[] state = new byte[((this.xOff * 4) + 40)];
        super.populateState(state);
        Pack.intToBigEndian(this.f6563H1, state, 16);
        Pack.intToBigEndian(this.f6564H2, state, 20);
        Pack.intToBigEndian(this.f6565H3, state, 24);
        Pack.intToBigEndian(this.f6566H4, state, 28);
        Pack.intToBigEndian(this.f6567H5, state, 32);
        Pack.intToBigEndian(this.xOff, state, 36);
        for (int i = 0; i != this.xOff; i++) {
            Pack.intToBigEndian(this.f6568X[i], state, (i * 4) + 40);
        }
        return state;
    }
}
