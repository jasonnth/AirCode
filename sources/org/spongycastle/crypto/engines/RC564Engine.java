package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.RC5Parameters;

public class RC564Engine implements BlockCipher {
    private static final long P64 = -5196783011329398165L;
    private static final long Q64 = -7046029254386353131L;
    private static final int bytesPerWord = 8;
    private static final int wordSize = 64;

    /* renamed from: _S */
    private long[] f6689_S = null;
    private int _noRounds = 12;
    private boolean forEncryption;

    public String getAlgorithmName() {
        return "RC5-64";
    }

    public int getBlockSize() {
        return 16;
    }

    public void init(boolean forEncryption2, CipherParameters params) {
        if (!(params instanceof RC5Parameters)) {
            throw new IllegalArgumentException("invalid parameter passed to RC564 init - " + params.getClass().getName());
        }
        RC5Parameters p = (RC5Parameters) params;
        this.forEncryption = forEncryption2;
        this._noRounds = p.getRounds();
        setKey(p.getKey());
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) {
        if (this.forEncryption) {
            return encryptBlock(in, inOff, out, outOff);
        }
        return decryptBlock(in, inOff, out, outOff);
    }

    public void reset() {
    }

    private void setKey(byte[] key) {
        int iter;
        long[] L = new long[((key.length + 7) / 8)];
        for (int i = 0; i != key.length; i++) {
            int i2 = i / 8;
            L[i2] = L[i2] + (((long) (key[i] & 255)) << ((i % 8) * 8));
        }
        this.f6689_S = new long[((this._noRounds + 1) * 2)];
        this.f6689_S[0] = -5196783011329398165L;
        for (int i3 = 1; i3 < this.f6689_S.length; i3++) {
            this.f6689_S[i3] = this.f6689_S[i3 - 1] + Q64;
        }
        if (L.length > this.f6689_S.length) {
            iter = L.length * 3;
        } else {
            iter = this.f6689_S.length * 3;
        }
        long A = 0;
        long B = 0;
        int i4 = 0;
        int j = 0;
        for (int k = 0; k < iter; k++) {
            long[] jArr = this.f6689_S;
            A = rotateLeft(this.f6689_S[i4] + A + B, 3);
            jArr[i4] = A;
            B = rotateLeft(L[j] + A + B, A + B);
            L[j] = B;
            i4 = (i4 + 1) % this.f6689_S.length;
            j = (j + 1) % L.length;
        }
    }

    private int encryptBlock(byte[] in, int inOff, byte[] out, int outOff) {
        long A = bytesToWord(in, inOff) + this.f6689_S[0];
        long B = bytesToWord(in, inOff + 8) + this.f6689_S[1];
        for (int i = 1; i <= this._noRounds; i++) {
            A = rotateLeft(A ^ B, B) + this.f6689_S[i * 2];
            B = rotateLeft(B ^ A, A) + this.f6689_S[(i * 2) + 1];
        }
        wordToBytes(A, out, outOff);
        wordToBytes(B, out, outOff + 8);
        return 16;
    }

    private int decryptBlock(byte[] in, int inOff, byte[] out, int outOff) {
        long A = bytesToWord(in, inOff);
        long B = bytesToWord(in, inOff + 8);
        for (int i = this._noRounds; i >= 1; i--) {
            B = rotateRight(B - this.f6689_S[(i * 2) + 1], A) ^ A;
            A = rotateRight(A - this.f6689_S[i * 2], B) ^ B;
        }
        wordToBytes(A - this.f6689_S[0], out, outOff);
        wordToBytes(B - this.f6689_S[1], out, outOff + 8);
        return 16;
    }

    private long rotateLeft(long x, long y) {
        return (x << ((int) (y & 63))) | (x >>> ((int) (64 - (63 & y))));
    }

    private long rotateRight(long x, long y) {
        return (x >>> ((int) (y & 63))) | (x << ((int) (64 - (63 & y))));
    }

    private long bytesToWord(byte[] src, int srcOff) {
        long word = 0;
        for (int i = 7; i >= 0; i--) {
            word = (word << 8) + ((long) (src[i + srcOff] & 255));
        }
        return word;
    }

    private void wordToBytes(long word, byte[] dst, int dstOff) {
        for (int i = 0; i < 8; i++) {
            dst[i + dstOff] = (byte) ((int) word);
            word >>>= 8;
        }
    }
}
