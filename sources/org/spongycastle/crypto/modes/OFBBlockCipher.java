package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.StreamBlockCipher;
import org.spongycastle.crypto.params.ParametersWithIV;

public class OFBBlockCipher extends StreamBlockCipher {

    /* renamed from: IV */
    private byte[] f6791IV;
    private final int blockSize;
    private int byteCount;
    private final BlockCipher cipher;
    private byte[] ofbOutV;
    private byte[] ofbV;

    public OFBBlockCipher(BlockCipher cipher2, int blockSize2) {
        super(cipher2);
        this.cipher = cipher2;
        this.blockSize = blockSize2 / 8;
        this.f6791IV = new byte[cipher2.getBlockSize()];
        this.ofbV = new byte[cipher2.getBlockSize()];
        this.ofbOutV = new byte[cipher2.getBlockSize()];
    }

    public void init(boolean encrypting, CipherParameters params) throws IllegalArgumentException {
        if (params instanceof ParametersWithIV) {
            ParametersWithIV ivParam = (ParametersWithIV) params;
            byte[] iv = ivParam.getIV();
            if (iv.length < this.f6791IV.length) {
                System.arraycopy(iv, 0, this.f6791IV, this.f6791IV.length - iv.length, iv.length);
                for (int i = 0; i < this.f6791IV.length - iv.length; i++) {
                    this.f6791IV[i] = 0;
                }
            } else {
                System.arraycopy(iv, 0, this.f6791IV, 0, this.f6791IV.length);
            }
            reset();
            if (ivParam.getParameters() != null) {
                this.cipher.init(true, ivParam.getParameters());
                return;
            }
            return;
        }
        reset();
        if (params != null) {
            this.cipher.init(true, params);
        }
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/OFB" + (this.blockSize * 8);
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        processBytes(in, inOff, this.blockSize, out, outOff);
        return this.blockSize;
    }

    public void reset() {
        System.arraycopy(this.f6791IV, 0, this.ofbV, 0, this.f6791IV.length);
        this.byteCount = 0;
        this.cipher.reset();
    }

    /* access modifiers changed from: protected */
    public byte calculateByte(byte in) throws DataLengthException, IllegalStateException {
        if (this.byteCount == 0) {
            this.cipher.processBlock(this.ofbV, 0, this.ofbOutV, 0);
        }
        byte[] bArr = this.ofbOutV;
        int i = this.byteCount;
        this.byteCount = i + 1;
        byte rv = (byte) (bArr[i] ^ in);
        if (this.byteCount == this.blockSize) {
            this.byteCount = 0;
            System.arraycopy(this.ofbV, this.blockSize, this.ofbV, 0, this.ofbV.length - this.blockSize);
            System.arraycopy(this.ofbOutV, 0, this.ofbV, this.ofbV.length - this.blockSize, this.blockSize);
        }
        return rv;
    }
}
