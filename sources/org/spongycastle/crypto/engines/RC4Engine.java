package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;

public class RC4Engine implements StreamCipher {
    private static final int STATE_LENGTH = 256;
    private byte[] engineState = null;
    private byte[] workingKey = null;

    /* renamed from: x */
    private int f6686x = 0;

    /* renamed from: y */
    private int f6687y = 0;

    public void init(boolean forEncryption, CipherParameters params) {
        if (params instanceof KeyParameter) {
            this.workingKey = ((KeyParameter) params).getKey();
            setKey(this.workingKey);
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to RC4 init - " + params.getClass().getName());
    }

    public String getAlgorithmName() {
        return "RC4";
    }

    public byte returnByte(byte in) {
        this.f6686x = (this.f6686x + 1) & 255;
        this.f6687y = (this.engineState[this.f6686x] + this.f6687y) & 255;
        byte tmp = this.engineState[this.f6686x];
        this.engineState[this.f6686x] = this.engineState[this.f6687y];
        this.engineState[this.f6687y] = tmp;
        return (byte) (this.engineState[(this.engineState[this.f6686x] + this.engineState[this.f6687y]) & 255] ^ in);
    }

    public int processBytes(byte[] in, int inOff, int len, byte[] out, int outOff) {
        if (inOff + len > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (outOff + len > out.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            for (int i = 0; i < len; i++) {
                this.f6686x = (this.f6686x + 1) & 255;
                this.f6687y = (this.engineState[this.f6686x] + this.f6687y) & 255;
                byte tmp = this.engineState[this.f6686x];
                this.engineState[this.f6686x] = this.engineState[this.f6687y];
                this.engineState[this.f6687y] = tmp;
                out[i + outOff] = (byte) (in[i + inOff] ^ this.engineState[(this.engineState[this.f6686x] + this.engineState[this.f6687y]) & 255]);
            }
            return len;
        }
    }

    public void reset() {
        setKey(this.workingKey);
    }

    private void setKey(byte[] keyBytes) {
        this.workingKey = keyBytes;
        this.f6686x = 0;
        this.f6687y = 0;
        if (this.engineState == null) {
            this.engineState = new byte[256];
        }
        for (int i = 0; i < 256; i++) {
            this.engineState[i] = (byte) i;
        }
        int i1 = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            i2 = ((keyBytes[i1] & 255) + this.engineState[i3] + i2) & 255;
            byte tmp = this.engineState[i3];
            this.engineState[i3] = this.engineState[i2];
            this.engineState[i2] = tmp;
            i1 = (i1 + 1) % keyBytes.length;
        }
    }
}
