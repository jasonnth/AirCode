package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class VMPCEngine implements StreamCipher {

    /* renamed from: P */
    protected byte[] f6719P = null;

    /* renamed from: n */
    protected byte f6720n = 0;

    /* renamed from: s */
    protected byte f6721s = 0;
    protected byte[] workingIV;
    protected byte[] workingKey;

    public String getAlgorithmName() {
        return "VMPC";
    }

    public void init(boolean forEncryption, CipherParameters params) {
        if (!(params instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("VMPC init parameters must include an IV");
        }
        ParametersWithIV ivParams = (ParametersWithIV) params;
        if (!(ivParams.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("VMPC init parameters must include a key");
        }
        KeyParameter key = (KeyParameter) ivParams.getParameters();
        this.workingIV = ivParams.getIV();
        if (this.workingIV == null || this.workingIV.length < 1 || this.workingIV.length > 768) {
            throw new IllegalArgumentException("VMPC requires 1 to 768 bytes of IV");
        }
        this.workingKey = key.getKey();
        initKey(this.workingKey, this.workingIV);
    }

    /* access modifiers changed from: protected */
    public void initKey(byte[] keyBytes, byte[] ivBytes) {
        this.f6721s = 0;
        this.f6719P = new byte[256];
        for (int i = 0; i < 256; i++) {
            this.f6719P[i] = (byte) i;
        }
        for (int m = 0; m < 768; m++) {
            this.f6721s = this.f6719P[(this.f6721s + this.f6719P[m & 255] + keyBytes[m % keyBytes.length]) & 255];
            byte temp = this.f6719P[m & 255];
            this.f6719P[m & 255] = this.f6719P[this.f6721s & 255];
            this.f6719P[this.f6721s & 255] = temp;
        }
        for (int m2 = 0; m2 < 768; m2++) {
            this.f6721s = this.f6719P[(this.f6721s + this.f6719P[m2 & 255] + ivBytes[m2 % ivBytes.length]) & 255];
            byte temp2 = this.f6719P[m2 & 255];
            this.f6719P[m2 & 255] = this.f6719P[this.f6721s & 255];
            this.f6719P[this.f6721s & 255] = temp2;
        }
        this.f6720n = 0;
    }

    public int processBytes(byte[] in, int inOff, int len, byte[] out, int outOff) {
        if (inOff + len > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (outOff + len > out.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            for (int i = 0; i < len; i++) {
                this.f6721s = this.f6719P[(this.f6721s + this.f6719P[this.f6720n & 255]) & 255];
                byte z = this.f6719P[(this.f6719P[this.f6719P[this.f6721s & 255] & 255] + 1) & 255];
                byte temp = this.f6719P[this.f6720n & 255];
                this.f6719P[this.f6720n & 255] = this.f6719P[this.f6721s & 255];
                this.f6719P[this.f6721s & 255] = temp;
                this.f6720n = (byte) ((this.f6720n + 1) & 255);
                out[i + outOff] = (byte) (in[i + inOff] ^ z);
            }
            return len;
        }
    }

    public void reset() {
        initKey(this.workingKey, this.workingIV);
    }

    public byte returnByte(byte in) {
        this.f6721s = this.f6719P[(this.f6721s + this.f6719P[this.f6720n & 255]) & 255];
        byte z = this.f6719P[(this.f6719P[this.f6719P[this.f6721s & 255] & 255] + 1) & 255];
        byte temp = this.f6719P[this.f6720n & 255];
        this.f6719P[this.f6720n & 255] = this.f6719P[this.f6721s & 255];
        this.f6719P[this.f6721s & 255] = temp;
        this.f6720n = (byte) ((this.f6720n + 1) & 255);
        return (byte) (in ^ z);
    }
}
