package org.spongycastle.crypto.engines;

import com.airbnb.android.core.enums.HelpCenterArticle;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class HC256Engine implements StreamCipher {
    private byte[] buf = new byte[4];
    private int cnt = 0;
    private int idx = 0;
    private boolean initialised;

    /* renamed from: iv */
    private byte[] f6676iv;
    private byte[] key;

    /* renamed from: p */
    private int[] f6677p = new int[1024];

    /* renamed from: q */
    private int[] f6678q = new int[1024];

    private int step() {
        int ret;
        int j = this.cnt & 1023;
        if (this.cnt < 1024) {
            int x = this.f6677p[(j - 3) & 1023];
            int y = this.f6677p[(j - 1023) & 1023];
            int[] iArr = this.f6677p;
            iArr[j] = iArr[j] + this.f6677p[(j - 10) & 1023] + (rotateRight(x, 10) ^ rotateRight(y, 23)) + this.f6678q[(x ^ y) & 1023];
            int x2 = this.f6677p[(j - 12) & 1023];
            ret = (((this.f6678q[x2 & 255] + this.f6678q[((x2 >> 8) & 255) + 256]) + this.f6678q[((x2 >> 16) & 255) + 512]) + this.f6678q[((x2 >> 24) & 255) + 768]) ^ this.f6677p[j];
        } else {
            int x3 = this.f6678q[(j - 3) & 1023];
            int y2 = this.f6678q[(j - 1023) & 1023];
            int[] iArr2 = this.f6678q;
            iArr2[j] = iArr2[j] + this.f6678q[(j - 10) & 1023] + (rotateRight(x3, 10) ^ rotateRight(y2, 23)) + this.f6677p[(x3 ^ y2) & 1023];
            int x4 = this.f6678q[(j - 12) & 1023];
            ret = (((this.f6677p[x4 & 255] + this.f6677p[((x4 >> 8) & 255) + 256]) + this.f6677p[((x4 >> 16) & 255) + 512]) + this.f6677p[((x4 >> 24) & 255) + 768]) ^ this.f6678q[j];
        }
        this.cnt = (this.cnt + 1) & 2047;
        return ret;
    }

    private void init() {
        if (this.key.length != 32 && this.key.length != 16) {
            throw new IllegalArgumentException("The key must be 128/256 bits long");
        } else if (this.f6676iv.length < 16) {
            throw new IllegalArgumentException("The IV must be at least 128 bits long");
        } else {
            if (this.key.length != 32) {
                byte[] k = new byte[32];
                System.arraycopy(this.key, 0, k, 0, this.key.length);
                System.arraycopy(this.key, 0, k, 16, this.key.length);
                this.key = k;
            }
            if (this.f6676iv.length < 32) {
                byte[] newIV = new byte[32];
                System.arraycopy(this.f6676iv, 0, newIV, 0, this.f6676iv.length);
                System.arraycopy(this.f6676iv, 0, newIV, this.f6676iv.length, newIV.length - this.f6676iv.length);
                this.f6676iv = newIV;
            }
            this.idx = 0;
            this.cnt = 0;
            int[] w = new int[2560];
            for (int i = 0; i < 32; i++) {
                int i2 = i >> 2;
                w[i2] = w[i2] | ((this.key[i] & 255) << ((i & 3) * 8));
            }
            for (int i3 = 0; i3 < 32; i3++) {
                int i4 = (i3 >> 2) + 8;
                w[i4] = w[i4] | ((this.f6676iv[i3] & 255) << ((i3 & 3) * 8));
            }
            for (int i5 = 16; i5 < 2560; i5++) {
                int x = w[i5 - 2];
                int y = w[i5 - 15];
                w[i5] = ((rotateRight(x, 17) ^ rotateRight(x, 19)) ^ (x >>> 10)) + w[i5 - 7] + ((rotateRight(y, 7) ^ rotateRight(y, 18)) ^ (y >>> 3)) + w[i5 - 16] + i5;
            }
            System.arraycopy(w, 512, this.f6677p, 0, 1024);
            System.arraycopy(w, HelpCenterArticle.COHOSTING_DIFFERENCE_BETWEEN_COHOST_AND_PRIMARY_HOST, this.f6678q, 0, 1024);
            for (int i6 = 0; i6 < 4096; i6++) {
                step();
            }
            this.cnt = 0;
        }
    }

    public String getAlgorithmName() {
        return "HC-256";
    }

    public void init(boolean forEncryption, CipherParameters params) throws IllegalArgumentException {
        CipherParameters keyParam = params;
        if (params instanceof ParametersWithIV) {
            this.f6676iv = ((ParametersWithIV) params).getIV();
            keyParam = ((ParametersWithIV) params).getParameters();
        } else {
            this.f6676iv = new byte[0];
        }
        if (keyParam instanceof KeyParameter) {
            this.key = ((KeyParameter) keyParam).getKey();
            init();
            this.initialised = true;
            return;
        }
        throw new IllegalArgumentException("Invalid parameter passed to HC256 init - " + params.getClass().getName());
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

    private static int rotateRight(int x, int bits) {
        return (x >>> bits) | (x << (-bits));
    }
}
