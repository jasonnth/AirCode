package org.spongycastle.crypto.digests;

import java.lang.reflect.Array;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.engines.GOST28147Engine;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

public class GOST3411Digest implements ExtendedDigest, Memoable {

    /* renamed from: C2 */
    private static final byte[] f6491C2 = {0, -1, 0, -1, 0, -1, 0, -1, -1, 0, -1, 0, -1, 0, -1, 0, 0, -1, -1, 0, -1, 0, 0, -1, -1, 0, 0, 0, -1, -1, 0, -1};
    private static final int DIGEST_LENGTH = 32;

    /* renamed from: C */
    private byte[][] f6492C;

    /* renamed from: H */
    private byte[] f6493H;

    /* renamed from: K */
    private byte[] f6494K;

    /* renamed from: L */
    private byte[] f6495L;

    /* renamed from: M */
    private byte[] f6496M;

    /* renamed from: S */
    byte[] f6497S;
    private byte[] Sum;

    /* renamed from: U */
    byte[] f6498U;

    /* renamed from: V */
    byte[] f6499V;

    /* renamed from: W */
    byte[] f6500W;

    /* renamed from: a */
    byte[] f6501a;
    private long byteCount;
    private BlockCipher cipher;
    private byte[] sBox;

    /* renamed from: wS */
    short[] f6502wS;
    short[] w_S;
    private byte[] xBuf;
    private int xBufOff;

    public GOST3411Digest() {
        this.f6493H = new byte[32];
        this.f6495L = new byte[32];
        this.f6496M = new byte[32];
        this.Sum = new byte[32];
        this.f6492C = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{4, 32});
        this.xBuf = new byte[32];
        this.cipher = new GOST28147Engine();
        this.f6494K = new byte[32];
        this.f6501a = new byte[8];
        this.f6502wS = new short[16];
        this.w_S = new short[16];
        this.f6497S = new byte[32];
        this.f6498U = new byte[32];
        this.f6499V = new byte[32];
        this.f6500W = new byte[32];
        this.sBox = GOST28147Engine.getSBox("D-A");
        this.cipher.init(true, new ParametersWithSBox(null, this.sBox));
        reset();
    }

    public GOST3411Digest(byte[] sBoxParam) {
        this.f6493H = new byte[32];
        this.f6495L = new byte[32];
        this.f6496M = new byte[32];
        this.Sum = new byte[32];
        this.f6492C = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{4, 32});
        this.xBuf = new byte[32];
        this.cipher = new GOST28147Engine();
        this.f6494K = new byte[32];
        this.f6501a = new byte[8];
        this.f6502wS = new short[16];
        this.w_S = new short[16];
        this.f6497S = new byte[32];
        this.f6498U = new byte[32];
        this.f6499V = new byte[32];
        this.f6500W = new byte[32];
        this.sBox = Arrays.clone(sBoxParam);
        this.cipher.init(true, new ParametersWithSBox(null, this.sBox));
        reset();
    }

    public GOST3411Digest(GOST3411Digest t) {
        this.f6493H = new byte[32];
        this.f6495L = new byte[32];
        this.f6496M = new byte[32];
        this.Sum = new byte[32];
        this.f6492C = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{4, 32});
        this.xBuf = new byte[32];
        this.cipher = new GOST28147Engine();
        this.f6494K = new byte[32];
        this.f6501a = new byte[8];
        this.f6502wS = new short[16];
        this.w_S = new short[16];
        this.f6497S = new byte[32];
        this.f6498U = new byte[32];
        this.f6499V = new byte[32];
        this.f6500W = new byte[32];
        reset(t);
    }

    public String getAlgorithmName() {
        return "GOST3411";
    }

    public int getDigestSize() {
        return 32;
    }

    public void update(byte in) {
        byte[] bArr = this.xBuf;
        int i = this.xBufOff;
        this.xBufOff = i + 1;
        bArr[i] = in;
        if (this.xBufOff == this.xBuf.length) {
            sumByteArray(this.xBuf);
            processBlock(this.xBuf, 0);
            this.xBufOff = 0;
        }
        this.byteCount++;
    }

    public void update(byte[] in, int inOff, int len) {
        while (this.xBufOff != 0 && len > 0) {
            update(in[inOff]);
            inOff++;
            len--;
        }
        while (len > this.xBuf.length) {
            System.arraycopy(in, inOff, this.xBuf, 0, this.xBuf.length);
            sumByteArray(this.xBuf);
            processBlock(this.xBuf, 0);
            inOff += this.xBuf.length;
            len -= this.xBuf.length;
            this.byteCount += (long) this.xBuf.length;
        }
        while (len > 0) {
            update(in[inOff]);
            inOff++;
            len--;
        }
    }

    /* renamed from: P */
    private byte[] m3961P(byte[] in) {
        for (int k = 0; k < 8; k++) {
            this.f6494K[k * 4] = in[k];
            this.f6494K[(k * 4) + 1] = in[k + 8];
            this.f6494K[(k * 4) + 2] = in[k + 16];
            this.f6494K[(k * 4) + 3] = in[k + 24];
        }
        return this.f6494K;
    }

    /* renamed from: A */
    private byte[] m3959A(byte[] in) {
        for (int j = 0; j < 8; j++) {
            this.f6501a[j] = (byte) (in[j] ^ in[j + 8]);
        }
        System.arraycopy(in, 8, in, 0, 24);
        System.arraycopy(this.f6501a, 0, in, 24, 8);
        return in;
    }

    /* renamed from: E */
    private void m3960E(byte[] key, byte[] s, int sOff, byte[] in, int inOff) {
        this.cipher.init(true, new KeyParameter(key));
        this.cipher.processBlock(in, inOff, s, sOff);
    }

    /* renamed from: fw */
    private void m3962fw(byte[] in) {
        cpyBytesToShort(in, this.f6502wS);
        this.w_S[15] = (short) (((((this.f6502wS[0] ^ this.f6502wS[1]) ^ this.f6502wS[2]) ^ this.f6502wS[3]) ^ this.f6502wS[12]) ^ this.f6502wS[15]);
        System.arraycopy(this.f6502wS, 1, this.w_S, 0, 15);
        cpyShortToBytes(this.w_S, in);
    }

    /* access modifiers changed from: protected */
    public void processBlock(byte[] in, int inOff) {
        System.arraycopy(in, inOff, this.f6496M, 0, 32);
        System.arraycopy(this.f6493H, 0, this.f6498U, 0, 32);
        System.arraycopy(this.f6496M, 0, this.f6499V, 0, 32);
        for (int j = 0; j < 32; j++) {
            this.f6500W[j] = (byte) (this.f6498U[j] ^ this.f6499V[j]);
        }
        m3960E(m3961P(this.f6500W), this.f6497S, 0, this.f6493H, 0);
        for (int i = 1; i < 4; i++) {
            byte[] tmpA = m3959A(this.f6498U);
            for (int j2 = 0; j2 < 32; j2++) {
                this.f6498U[j2] = (byte) (tmpA[j2] ^ this.f6492C[i][j2]);
            }
            this.f6499V = m3959A(m3959A(this.f6499V));
            for (int j3 = 0; j3 < 32; j3++) {
                this.f6500W[j3] = (byte) (this.f6498U[j3] ^ this.f6499V[j3]);
            }
            m3960E(m3961P(this.f6500W), this.f6497S, i * 8, this.f6493H, i * 8);
        }
        for (int n = 0; n < 12; n++) {
            m3962fw(this.f6497S);
        }
        for (int n2 = 0; n2 < 32; n2++) {
            this.f6497S[n2] = (byte) (this.f6497S[n2] ^ this.f6496M[n2]);
        }
        m3962fw(this.f6497S);
        for (int n3 = 0; n3 < 32; n3++) {
            this.f6497S[n3] = (byte) (this.f6493H[n3] ^ this.f6497S[n3]);
        }
        for (int n4 = 0; n4 < 61; n4++) {
            m3962fw(this.f6497S);
        }
        System.arraycopy(this.f6497S, 0, this.f6493H, 0, this.f6493H.length);
    }

    private void finish() {
        Pack.longToLittleEndian(this.byteCount * 8, this.f6495L, 0);
        while (this.xBufOff != 0) {
            update(0);
        }
        processBlock(this.f6495L, 0);
        processBlock(this.Sum, 0);
    }

    public int doFinal(byte[] out, int outOff) {
        finish();
        System.arraycopy(this.f6493H, 0, out, outOff, this.f6493H.length);
        reset();
        return 32;
    }

    public void reset() {
        this.byteCount = 0;
        this.xBufOff = 0;
        for (int i = 0; i < this.f6493H.length; i++) {
            this.f6493H[i] = 0;
        }
        for (int i2 = 0; i2 < this.f6495L.length; i2++) {
            this.f6495L[i2] = 0;
        }
        for (int i3 = 0; i3 < this.f6496M.length; i3++) {
            this.f6496M[i3] = 0;
        }
        for (int i4 = 0; i4 < this.f6492C[1].length; i4++) {
            this.f6492C[1][i4] = 0;
        }
        for (int i5 = 0; i5 < this.f6492C[3].length; i5++) {
            this.f6492C[3][i5] = 0;
        }
        for (int i6 = 0; i6 < this.Sum.length; i6++) {
            this.Sum[i6] = 0;
        }
        for (int i7 = 0; i7 < this.xBuf.length; i7++) {
            this.xBuf[i7] = 0;
        }
        System.arraycopy(f6491C2, 0, this.f6492C[2], 0, f6491C2.length);
    }

    private void sumByteArray(byte[] in) {
        int carry = 0;
        for (int i = 0; i != this.Sum.length; i++) {
            int sum = (this.Sum[i] & 255) + (in[i] & 255) + carry;
            this.Sum[i] = (byte) sum;
            carry = sum >>> 8;
        }
    }

    private void cpyBytesToShort(byte[] S, short[] wS) {
        for (int i = 0; i < S.length / 2; i++) {
            wS[i] = (short) (((S[(i * 2) + 1] << 8) & 65280) | (S[i * 2] & 255));
        }
    }

    private void cpyShortToBytes(short[] wS, byte[] S) {
        for (int i = 0; i < S.length / 2; i++) {
            S[(i * 2) + 1] = (byte) (wS[i] >> 8);
            S[i * 2] = (byte) wS[i];
        }
    }

    public int getByteLength() {
        return 32;
    }

    public Memoable copy() {
        return new GOST3411Digest(this);
    }

    public void reset(Memoable other) {
        GOST3411Digest t = (GOST3411Digest) other;
        this.sBox = t.sBox;
        this.cipher.init(true, new ParametersWithSBox(null, this.sBox));
        reset();
        System.arraycopy(t.f6493H, 0, this.f6493H, 0, t.f6493H.length);
        System.arraycopy(t.f6495L, 0, this.f6495L, 0, t.f6495L.length);
        System.arraycopy(t.f6496M, 0, this.f6496M, 0, t.f6496M.length);
        System.arraycopy(t.Sum, 0, this.Sum, 0, t.Sum.length);
        System.arraycopy(t.f6492C[1], 0, this.f6492C[1], 0, t.f6492C[1].length);
        System.arraycopy(t.f6492C[2], 0, this.f6492C[2], 0, t.f6492C[2].length);
        System.arraycopy(t.f6492C[3], 0, this.f6492C[3], 0, t.f6492C[3].length);
        System.arraycopy(t.xBuf, 0, this.xBuf, 0, t.xBuf.length);
        this.xBufOff = t.xBufOff;
        this.byteCount = t.byteCount;
    }
}
