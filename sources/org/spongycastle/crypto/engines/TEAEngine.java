package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public class TEAEngine implements BlockCipher {
    private static final int block_size = 8;
    private static final int d_sum = -957401312;
    private static final int delta = -1640531527;
    private static final int rounds = 32;

    /* renamed from: _a */
    private int f6710_a;

    /* renamed from: _b */
    private int f6711_b;

    /* renamed from: _c */
    private int f6712_c;

    /* renamed from: _d */
    private int f6713_d;
    private boolean _forEncryption;
    private boolean _initialised = false;

    public String getAlgorithmName() {
        return "TEA";
    }

    public int getBlockSize() {
        return 8;
    }

    public void init(boolean forEncryption, CipherParameters params) {
        if (!(params instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to TEA init - " + params.getClass().getName());
        }
        this._forEncryption = forEncryption;
        this._initialised = true;
        setKey(((KeyParameter) params).getKey());
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) {
        if (!this._initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (inOff + 8 > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (outOff + 8 > out.length) {
            throw new OutputLengthException("output buffer too short");
        } else if (this._forEncryption) {
            return encryptBlock(in, inOff, out, outOff);
        } else {
            return decryptBlock(in, inOff, out, outOff);
        }
    }

    public void reset() {
    }

    private void setKey(byte[] key) {
        if (key.length != 16) {
            throw new IllegalArgumentException("Key size must be 128 bits.");
        }
        this.f6710_a = bytesToInt(key, 0);
        this.f6711_b = bytesToInt(key, 4);
        this.f6712_c = bytesToInt(key, 8);
        this.f6713_d = bytesToInt(key, 12);
    }

    private int encryptBlock(byte[] in, int inOff, byte[] out, int outOff) {
        int v0 = bytesToInt(in, inOff);
        int v1 = bytesToInt(in, inOff + 4);
        int sum = 0;
        for (int i = 0; i != 32; i++) {
            sum -= 1640531527;
            v0 += (((v1 << 4) + this.f6710_a) ^ (v1 + sum)) ^ ((v1 >>> 5) + this.f6711_b);
            v1 += (((v0 << 4) + this.f6712_c) ^ (v0 + sum)) ^ ((v0 >>> 5) + this.f6713_d);
        }
        unpackInt(v0, out, outOff);
        unpackInt(v1, out, outOff + 4);
        return 8;
    }

    private int decryptBlock(byte[] in, int inOff, byte[] out, int outOff) {
        int v0 = bytesToInt(in, inOff);
        int v1 = bytesToInt(in, inOff + 4);
        int sum = d_sum;
        for (int i = 0; i != 32; i++) {
            v1 -= (((v0 << 4) + this.f6712_c) ^ (v0 + sum)) ^ ((v0 >>> 5) + this.f6713_d);
            v0 -= (((v1 << 4) + this.f6710_a) ^ (v1 + sum)) ^ ((v1 >>> 5) + this.f6711_b);
            sum += 1640531527;
        }
        unpackInt(v0, out, outOff);
        unpackInt(v1, out, outOff + 4);
        return 8;
    }

    private int bytesToInt(byte[] in, int inOff) {
        int inOff2 = inOff + 1;
        int inOff3 = inOff2 + 1;
        return (in[inOff] << 24) | ((in[inOff2] & 255) << 16) | ((in[inOff3] & 255) << 8) | (in[inOff3 + 1] & 255);
    }

    private void unpackInt(int v, byte[] out, int outOff) {
        int outOff2 = outOff + 1;
        out[outOff] = (byte) (v >>> 24);
        int outOff3 = outOff2 + 1;
        out[outOff2] = (byte) (v >>> 16);
        int outOff4 = outOff3 + 1;
        out[outOff3] = (byte) (v >>> 8);
        out[outOff4] = (byte) v;
    }
}
