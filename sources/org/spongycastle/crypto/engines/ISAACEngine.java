package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Pack;

public class ISAACEngine implements StreamCipher {

    /* renamed from: a */
    private int f6681a = 0;

    /* renamed from: b */
    private int f6682b = 0;

    /* renamed from: c */
    private int f6683c = 0;
    private int[] engineState = null;
    private int index = 0;
    private boolean initialised = false;
    private byte[] keyStream = new byte[1024];
    private int[] results = null;
    private final int sizeL = 8;
    private final int stateArraySize = 256;
    private byte[] workingKey = null;

    public void init(boolean forEncryption, CipherParameters params) {
        if (!(params instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to ISAAC init - " + params.getClass().getName());
        }
        setKey(((KeyParameter) params).getKey());
    }

    public byte returnByte(byte in) {
        if (this.index == 0) {
            isaac();
            this.keyStream = Pack.intToBigEndian(this.results);
        }
        byte out = (byte) (this.keyStream[this.index] ^ in);
        this.index = (this.index + 1) & 1023;
        return out;
    }

    public int processBytes(byte[] in, int inOff, int len, byte[] out, int outOff) {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (inOff + len > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (outOff + len > out.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            for (int i = 0; i < len; i++) {
                if (this.index == 0) {
                    isaac();
                    this.keyStream = Pack.intToBigEndian(this.results);
                }
                out[i + outOff] = (byte) (this.keyStream[this.index] ^ in[i + inOff]);
                this.index = (this.index + 1) & 1023;
            }
            return len;
        }
    }

    public String getAlgorithmName() {
        return "ISAAC";
    }

    public void reset() {
        setKey(this.workingKey);
    }

    private void setKey(byte[] keyBytes) {
        this.workingKey = keyBytes;
        if (this.engineState == null) {
            this.engineState = new int[256];
        }
        if (this.results == null) {
            this.results = new int[256];
        }
        for (int i = 0; i < 256; i++) {
            int[] iArr = this.engineState;
            this.results[i] = 0;
            iArr[i] = 0;
        }
        this.f6683c = 0;
        this.f6682b = 0;
        this.f6681a = 0;
        this.index = 0;
        byte[] t = new byte[(keyBytes.length + (keyBytes.length & 3))];
        System.arraycopy(keyBytes, 0, t, 0, keyBytes.length);
        for (int i2 = 0; i2 < t.length; i2 += 4) {
            this.results[i2 >>> 2] = Pack.littleEndianToInt(t, i2);
        }
        int[] abcdefgh = new int[8];
        for (int i3 = 0; i3 < 8; i3++) {
            abcdefgh[i3] = -1640531527;
        }
        for (int i4 = 0; i4 < 4; i4++) {
            mix(abcdefgh);
        }
        int i5 = 0;
        while (i5 < 2) {
            for (int j = 0; j < 256; j += 8) {
                for (int k = 0; k < 8; k++) {
                    abcdefgh[k] = (i5 < 1 ? this.results[j + k] : this.engineState[j + k]) + abcdefgh[k];
                }
                mix(abcdefgh);
                for (int k2 = 0; k2 < 8; k2++) {
                    this.engineState[j + k2] = abcdefgh[k2];
                }
            }
            i5++;
        }
        isaac();
        this.initialised = true;
    }

    private void isaac() {
        int i = this.f6682b;
        int i2 = this.f6683c + 1;
        this.f6683c = i2;
        this.f6682b = i + i2;
        for (int i3 = 0; i3 < 256; i3++) {
            int x = this.engineState[i3];
            switch (i3 & 3) {
                case 0:
                    this.f6681a ^= this.f6681a << 13;
                    break;
                case 1:
                    this.f6681a ^= this.f6681a >>> 6;
                    break;
                case 2:
                    this.f6681a ^= this.f6681a << 2;
                    break;
                case 3:
                    this.f6681a ^= this.f6681a >>> 16;
                    break;
            }
            this.f6681a += this.engineState[(i3 + 128) & 255];
            int y = this.engineState[(x >>> 2) & 255] + this.f6681a + this.f6682b;
            this.engineState[i3] = y;
            int[] iArr = this.results;
            int i4 = this.engineState[(y >>> 10) & 255] + x;
            this.f6682b = i4;
            iArr[i3] = i4;
        }
    }

    private void mix(int[] x) {
        x[0] = x[0] ^ (x[1] << 11);
        x[3] = x[3] + x[0];
        x[1] = x[1] + x[2];
        x[1] = x[1] ^ (x[2] >>> 2);
        x[4] = x[4] + x[1];
        x[2] = x[2] + x[3];
        x[2] = x[2] ^ (x[3] << 8);
        x[5] = x[5] + x[2];
        x[3] = x[3] + x[4];
        x[3] = x[3] ^ (x[4] >>> 16);
        x[6] = x[6] + x[3];
        x[4] = x[4] + x[5];
        x[4] = x[4] ^ (x[5] << 10);
        x[7] = x[7] + x[4];
        x[5] = x[5] + x[6];
        x[5] = x[5] ^ (x[6] >>> 4);
        x[0] = x[0] + x[5];
        x[6] = x[6] + x[7];
        x[6] = x[6] ^ (x[7] << 8);
        x[1] = x[1] + x[6];
        x[7] = x[7] + x[0];
        x[7] = x[7] ^ (x[0] >>> 9);
        x[2] = x[2] + x[7];
        x[0] = x[0] + x[1];
    }
}
