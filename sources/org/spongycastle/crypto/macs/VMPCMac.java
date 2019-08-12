package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class VMPCMac implements Mac {

    /* renamed from: P */
    private byte[] f6770P = null;

    /* renamed from: T */
    private byte[] f6771T;

    /* renamed from: g */
    private byte f6772g;

    /* renamed from: n */
    private byte f6773n = 0;

    /* renamed from: s */
    private byte f6774s = 0;
    private byte[] workingIV;
    private byte[] workingKey;

    /* renamed from: x1 */
    private byte f6775x1;

    /* renamed from: x2 */
    private byte f6776x2;

    /* renamed from: x3 */
    private byte f6777x3;

    /* renamed from: x4 */
    private byte f6778x4;

    public int doFinal(byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        for (int r = 1; r < 25; r++) {
            this.f6774s = this.f6770P[(this.f6774s + this.f6770P[this.f6773n & 255]) & 255];
            this.f6778x4 = this.f6770P[(this.f6778x4 + this.f6777x3 + r) & 255];
            this.f6777x3 = this.f6770P[(this.f6777x3 + this.f6776x2 + r) & 255];
            this.f6776x2 = this.f6770P[(this.f6776x2 + this.f6775x1 + r) & 255];
            this.f6775x1 = this.f6770P[(this.f6775x1 + this.f6774s + r) & 255];
            this.f6771T[this.f6772g & 31] = (byte) (this.f6771T[this.f6772g & 31] ^ this.f6775x1);
            this.f6771T[(this.f6772g + 1) & 31] = (byte) (this.f6771T[(this.f6772g + 1) & 31] ^ this.f6776x2);
            this.f6771T[(this.f6772g + 2) & 31] = (byte) (this.f6771T[(this.f6772g + 2) & 31] ^ this.f6777x3);
            this.f6771T[(this.f6772g + 3) & 31] = (byte) (this.f6771T[(this.f6772g + 3) & 31] ^ this.f6778x4);
            this.f6772g = (byte) ((this.f6772g + 4) & 31);
            byte temp = this.f6770P[this.f6773n & 255];
            this.f6770P[this.f6773n & 255] = this.f6770P[this.f6774s & 255];
            this.f6770P[this.f6774s & 255] = temp;
            this.f6773n = (byte) ((this.f6773n + 1) & 255);
        }
        for (int m = 0; m < 768; m++) {
            this.f6774s = this.f6770P[(this.f6774s + this.f6770P[m & 255] + this.f6771T[m & 31]) & 255];
            byte temp2 = this.f6770P[m & 255];
            this.f6770P[m & 255] = this.f6770P[this.f6774s & 255];
            this.f6770P[this.f6774s & 255] = temp2;
        }
        byte[] M = new byte[20];
        for (int i = 0; i < 20; i++) {
            this.f6774s = this.f6770P[(this.f6774s + this.f6770P[i & 255]) & 255];
            M[i] = this.f6770P[(this.f6770P[this.f6770P[this.f6774s & 255] & 255] + 1) & 255];
            byte temp3 = this.f6770P[i & 255];
            this.f6770P[i & 255] = this.f6770P[this.f6774s & 255];
            this.f6770P[this.f6774s & 255] = temp3;
        }
        System.arraycopy(M, 0, out, outOff, M.length);
        reset();
        return M.length;
    }

    public String getAlgorithmName() {
        return "VMPC-MAC";
    }

    public int getMacSize() {
        return 20;
    }

    public void init(CipherParameters params) throws IllegalArgumentException {
        if (!(params instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include an IV");
        }
        ParametersWithIV ivParams = (ParametersWithIV) params;
        KeyParameter key = (KeyParameter) ivParams.getParameters();
        if (!(ivParams.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include a key");
        }
        this.workingIV = ivParams.getIV();
        if (this.workingIV == null || this.workingIV.length < 1 || this.workingIV.length > 768) {
            throw new IllegalArgumentException("VMPC-MAC requires 1 to 768 bytes of IV");
        }
        this.workingKey = key.getKey();
        reset();
    }

    private void initKey(byte[] keyBytes, byte[] ivBytes) {
        this.f6774s = 0;
        this.f6770P = new byte[256];
        for (int i = 0; i < 256; i++) {
            this.f6770P[i] = (byte) i;
        }
        for (int m = 0; m < 768; m++) {
            this.f6774s = this.f6770P[(this.f6774s + this.f6770P[m & 255] + keyBytes[m % keyBytes.length]) & 255];
            byte temp = this.f6770P[m & 255];
            this.f6770P[m & 255] = this.f6770P[this.f6774s & 255];
            this.f6770P[this.f6774s & 255] = temp;
        }
        for (int m2 = 0; m2 < 768; m2++) {
            this.f6774s = this.f6770P[(this.f6774s + this.f6770P[m2 & 255] + ivBytes[m2 % ivBytes.length]) & 255];
            byte temp2 = this.f6770P[m2 & 255];
            this.f6770P[m2 & 255] = this.f6770P[this.f6774s & 255];
            this.f6770P[this.f6774s & 255] = temp2;
        }
        this.f6773n = 0;
    }

    public void reset() {
        initKey(this.workingKey, this.workingIV);
        this.f6773n = 0;
        this.f6778x4 = 0;
        this.f6777x3 = 0;
        this.f6776x2 = 0;
        this.f6775x1 = 0;
        this.f6772g = 0;
        this.f6771T = new byte[32];
        for (int i = 0; i < 32; i++) {
            this.f6771T[i] = 0;
        }
    }

    public void update(byte in) throws IllegalStateException {
        this.f6774s = this.f6770P[(this.f6774s + this.f6770P[this.f6773n & 255]) & 255];
        byte c = (byte) (this.f6770P[(this.f6770P[this.f6770P[this.f6774s & 255] & 255] + 1) & 255] ^ in);
        this.f6778x4 = this.f6770P[(this.f6778x4 + this.f6777x3) & 255];
        this.f6777x3 = this.f6770P[(this.f6777x3 + this.f6776x2) & 255];
        this.f6776x2 = this.f6770P[(this.f6776x2 + this.f6775x1) & 255];
        this.f6775x1 = this.f6770P[(this.f6775x1 + this.f6774s + c) & 255];
        this.f6771T[this.f6772g & 31] = (byte) (this.f6771T[this.f6772g & 31] ^ this.f6775x1);
        this.f6771T[(this.f6772g + 1) & 31] = (byte) (this.f6771T[(this.f6772g + 1) & 31] ^ this.f6776x2);
        this.f6771T[(this.f6772g + 2) & 31] = (byte) (this.f6771T[(this.f6772g + 2) & 31] ^ this.f6777x3);
        this.f6771T[(this.f6772g + 3) & 31] = (byte) (this.f6771T[(this.f6772g + 3) & 31] ^ this.f6778x4);
        this.f6772g = (byte) ((this.f6772g + 4) & 31);
        byte temp = this.f6770P[this.f6773n & 255];
        this.f6770P[this.f6773n & 255] = this.f6770P[this.f6774s & 255];
        this.f6770P[this.f6774s & 255] = temp;
        this.f6773n = (byte) ((this.f6773n + 1) & 255);
    }

    public void update(byte[] in, int inOff, int len) throws DataLengthException, IllegalStateException {
        if (inOff + len > in.length) {
            throw new DataLengthException("input buffer too short");
        }
        for (int i = 0; i < len; i++) {
            update(in[inOff + i]);
        }
    }
}
