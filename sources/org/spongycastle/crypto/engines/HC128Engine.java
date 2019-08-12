package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class HC128Engine implements StreamCipher {
    private byte[] buf = new byte[4];
    private int cnt = 0;
    private int idx = 0;
    private boolean initialised;

    /* renamed from: iv */
    private byte[] f6673iv;
    private byte[] key;

    /* renamed from: p */
    private int[] f6674p = new int[512];

    /* renamed from: q */
    private int[] f6675q = new int[512];

    /* renamed from: f1 */
    private static int m4013f1(int x) {
        return (rotateRight(x, 7) ^ rotateRight(x, 18)) ^ (x >>> 3);
    }

    /* renamed from: f2 */
    private static int m4014f2(int x) {
        return (rotateRight(x, 17) ^ rotateRight(x, 19)) ^ (x >>> 10);
    }

    /* renamed from: g1 */
    private int m4015g1(int x, int y, int z) {
        return (rotateRight(x, 10) ^ rotateRight(z, 23)) + rotateRight(y, 8);
    }

    /* renamed from: g2 */
    private int m4016g2(int x, int y, int z) {
        return (rotateLeft(x, 10) ^ rotateLeft(z, 23)) + rotateLeft(y, 8);
    }

    private static int rotateLeft(int x, int bits) {
        return (x << bits) | (x >>> (-bits));
    }

    private static int rotateRight(int x, int bits) {
        return (x >>> bits) | (x << (-bits));
    }

    /* renamed from: h1 */
    private int m4017h1(int x) {
        return this.f6675q[x & 255] + this.f6675q[((x >> 16) & 255) + 256];
    }

    /* renamed from: h2 */
    private int m4018h2(int x) {
        return this.f6674p[x & 255] + this.f6674p[((x >> 16) & 255) + 256];
    }

    private static int mod1024(int x) {
        return x & 1023;
    }

    private static int mod512(int x) {
        return x & 511;
    }

    private static int dim(int x, int y) {
        return mod512(x - y);
    }

    private int step() {
        int ret;
        int j = mod512(this.cnt);
        if (this.cnt < 512) {
            int[] iArr = this.f6674p;
            iArr[j] = iArr[j] + m4015g1(this.f6674p[dim(j, 3)], this.f6674p[dim(j, 10)], this.f6674p[dim(j, 511)]);
            ret = m4017h1(this.f6674p[dim(j, 12)]) ^ this.f6674p[j];
        } else {
            int[] iArr2 = this.f6675q;
            iArr2[j] = iArr2[j] + m4016g2(this.f6675q[dim(j, 3)], this.f6675q[dim(j, 10)], this.f6675q[dim(j, 511)]);
            ret = m4018h2(this.f6675q[dim(j, 12)]) ^ this.f6675q[j];
        }
        this.cnt = mod1024(this.cnt + 1);
        return ret;
    }

    private void init() {
        if (this.key.length != 16) {
            throw new IllegalArgumentException("The key must be 128 bits long");
        }
        this.idx = 0;
        this.cnt = 0;
        int[] w = new int[1280];
        for (int i = 0; i < 16; i++) {
            int i2 = i >> 2;
            w[i2] = w[i2] | ((this.key[i] & 255) << ((i & 3) * 8));
        }
        System.arraycopy(w, 0, w, 4, 4);
        int i3 = 0;
        while (i3 < this.f6673iv.length && i3 < 16) {
            int i4 = (i3 >> 2) + 8;
            w[i4] = w[i4] | ((this.f6673iv[i3] & 255) << ((i3 & 3) * 8));
            i3++;
        }
        System.arraycopy(w, 8, w, 12, 4);
        for (int i5 = 16; i5 < 1280; i5++) {
            w[i5] = m4014f2(w[i5 - 2]) + w[i5 - 7] + m4013f1(w[i5 - 15]) + w[i5 - 16] + i5;
        }
        System.arraycopy(w, 256, this.f6674p, 0, 512);
        System.arraycopy(w, 768, this.f6675q, 0, 512);
        for (int i6 = 0; i6 < 512; i6++) {
            this.f6674p[i6] = step();
        }
        for (int i7 = 0; i7 < 512; i7++) {
            this.f6675q[i7] = step();
        }
        this.cnt = 0;
    }

    public String getAlgorithmName() {
        return "HC-128";
    }

    public void init(boolean forEncryption, CipherParameters params) throws IllegalArgumentException {
        CipherParameters keyParam = params;
        if (params instanceof ParametersWithIV) {
            this.f6673iv = ((ParametersWithIV) params).getIV();
            keyParam = ((ParametersWithIV) params).getParameters();
        } else {
            this.f6673iv = new byte[0];
        }
        if (keyParam instanceof KeyParameter) {
            this.key = ((KeyParameter) keyParam).getKey();
            init();
            this.initialised = true;
            return;
        }
        throw new IllegalArgumentException("Invalid parameter passed to HC128 init - " + params.getClass().getName());
    }

    private byte getByte() {
        if (this.idx == 0) {
            int step = step();
            this.buf[0] = (byte) (step & 255);
            int step2 = step >> 8;
            this.buf[1] = (byte) (step2 & 255);
            int step3 = step2 >> 8;
            this.buf[2] = (byte) (step3 & 255);
            this.buf[3] = (byte) ((step3 >> 8) & 255);
        }
        byte ret = this.buf[this.idx];
        this.idx = (this.idx + 1) & 3;
        return ret;
    }

    public int processBytes(byte[] in, int inOff, int len, byte[] out, int outOff) throws DataLengthException {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (inOff + len > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (outOff + len > out.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            for (int i = 0; i < len; i++) {
                out[outOff + i] = (byte) (in[inOff + i] ^ getByte());
            }
            return len;
        }
    }

    public void reset() {
        init();
    }

    public byte returnByte(byte in) {
        return (byte) (getByte() ^ in);
    }
}
