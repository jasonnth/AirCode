package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.StreamBlockCipher;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

public class CFBBlockCipher extends StreamBlockCipher {

    /* renamed from: IV */
    private byte[] f6780IV;
    private int blockSize;
    private int byteCount;
    private byte[] cfbOutV;
    private byte[] cfbV;
    private BlockCipher cipher = null;
    private boolean encrypting;
    private byte[] inBuf;

    public CFBBlockCipher(BlockCipher cipher2, int bitBlockSize) {
        super(cipher2);
        this.cipher = cipher2;
        this.blockSize = bitBlockSize / 8;
        this.f6780IV = new byte[cipher2.getBlockSize()];
        this.cfbV = new byte[cipher2.getBlockSize()];
        this.cfbOutV = new byte[cipher2.getBlockSize()];
        this.inBuf = new byte[this.blockSize];
    }

    public void init(boolean encrypting2, CipherParameters params) throws IllegalArgumentException {
        this.encrypting = encrypting2;
        if (params instanceof ParametersWithIV) {
            ParametersWithIV ivParam = (ParametersWithIV) params;
            byte[] iv = ivParam.getIV();
            if (iv.length < this.f6780IV.length) {
                System.arraycopy(iv, 0, this.f6780IV, this.f6780IV.length - iv.length, iv.length);
                for (int i = 0; i < this.f6780IV.length - iv.length; i++) {
                    this.f6780IV[i] = 0;
                }
            } else {
                System.arraycopy(iv, 0, this.f6780IV, 0, this.f6780IV.length);
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
        return this.cipher.getAlgorithmName() + "/CFB" + (this.blockSize * 8);
    }

    /* access modifiers changed from: protected */
    public byte calculateByte(byte in) throws DataLengthException, IllegalStateException {
        return this.encrypting ? encryptByte(in) : decryptByte(in);
    }

    private byte encryptByte(byte in) {
        if (this.byteCount == 0) {
            this.cipher.processBlock(this.cfbV, 0, this.cfbOutV, 0);
        }
        byte rv = (byte) (this.cfbOutV[this.byteCount] ^ in);
        byte[] bArr = this.inBuf;
        int i = this.byteCount;
        this.byteCount = i + 1;
        bArr[i] = rv;
        if (this.byteCount == this.blockSize) {
            this.byteCount = 0;
            System.arraycopy(this.cfbV, this.blockSize, this.cfbV, 0, this.cfbV.length - this.blockSize);
            System.arraycopy(this.inBuf, 0, this.cfbV, this.cfbV.length - this.blockSize, this.blockSize);
        }
        return rv;
    }

    private byte decryptByte(byte in) {
        if (this.byteCount == 0) {
            this.cipher.processBlock(this.cfbV, 0, this.cfbOutV, 0);
        }
        this.inBuf[this.byteCount] = in;
        byte[] bArr = this.cfbOutV;
        int i = this.byteCount;
        this.byteCount = i + 1;
        byte rv = (byte) (bArr[i] ^ in);
        if (this.byteCount == this.blockSize) {
            this.byteCount = 0;
            System.arraycopy(this.cfbV, this.blockSize, this.cfbV, 0, this.cfbV.length - this.blockSize);
            System.arraycopy(this.inBuf, 0, this.cfbV, this.cfbV.length - this.blockSize, this.blockSize);
        }
        return rv;
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        processBytes(in, inOff, this.blockSize, out, outOff);
        return this.blockSize;
    }

    public int encryptBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        processBytes(in, inOff, this.blockSize, out, outOff);
        return this.blockSize;
    }

    public int decryptBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        processBytes(in, inOff, this.blockSize, out, outOff);
        return this.blockSize;
    }

    public byte[] getCurrentIV() {
        return Arrays.clone(this.cfbV);
    }

    public void reset() {
        System.arraycopy(this.f6780IV, 0, this.cfbV, 0, this.f6780IV.length);
        Arrays.fill(this.inBuf, 0);
        this.byteCount = 0;
        this.cipher.reset();
    }
}
