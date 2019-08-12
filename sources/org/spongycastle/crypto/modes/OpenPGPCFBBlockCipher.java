package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;

public class OpenPGPCFBBlockCipher implements BlockCipher {

    /* renamed from: FR */
    private byte[] f6792FR = new byte[this.blockSize];
    private byte[] FRE = new byte[this.blockSize];

    /* renamed from: IV */
    private byte[] f6793IV = new byte[this.blockSize];
    private int blockSize;
    private BlockCipher cipher;
    private int count;
    private boolean forEncryption;

    public OpenPGPCFBBlockCipher(BlockCipher cipher2) {
        this.cipher = cipher2;
        this.blockSize = cipher2.getBlockSize();
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/OpenPGPCFB";
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        return this.forEncryption ? encryptBlock(in, inOff, out, outOff) : decryptBlock(in, inOff, out, outOff);
    }

    public void reset() {
        this.count = 0;
        System.arraycopy(this.f6793IV, 0, this.f6792FR, 0, this.f6792FR.length);
        this.cipher.reset();
    }

    public void init(boolean forEncryption2, CipherParameters params) throws IllegalArgumentException {
        this.forEncryption = forEncryption2;
        reset();
        this.cipher.init(true, params);
    }

    private byte encryptByte(byte data, int blockOff) {
        return (byte) (this.FRE[blockOff] ^ data);
    }

    private int encryptBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        if (this.blockSize + inOff > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (this.blockSize + outOff > out.length) {
            throw new DataLengthException("output buffer too short");
        } else {
            if (this.count > this.blockSize) {
                byte[] bArr = this.f6792FR;
                int i = this.blockSize - 2;
                byte encryptByte = encryptByte(in[inOff], this.blockSize - 2);
                out[outOff] = encryptByte;
                bArr[i] = encryptByte;
                byte[] bArr2 = this.f6792FR;
                int i2 = this.blockSize - 1;
                int i3 = outOff + 1;
                byte encryptByte2 = encryptByte(in[inOff + 1], this.blockSize - 1);
                out[i3] = encryptByte2;
                bArr2[i2] = encryptByte2;
                this.cipher.processBlock(this.f6792FR, 0, this.FRE, 0);
                for (int n = 2; n < this.blockSize; n++) {
                    byte[] bArr3 = this.f6792FR;
                    int i4 = n - 2;
                    int i5 = outOff + n;
                    byte encryptByte3 = encryptByte(in[inOff + n], n - 2);
                    out[i5] = encryptByte3;
                    bArr3[i4] = encryptByte3;
                }
            } else if (this.count == 0) {
                this.cipher.processBlock(this.f6792FR, 0, this.FRE, 0);
                for (int n2 = 0; n2 < this.blockSize; n2++) {
                    byte[] bArr4 = this.f6792FR;
                    int i6 = outOff + n2;
                    byte encryptByte4 = encryptByte(in[inOff + n2], n2);
                    out[i6] = encryptByte4;
                    bArr4[n2] = encryptByte4;
                }
                this.count += this.blockSize;
            } else if (this.count == this.blockSize) {
                this.cipher.processBlock(this.f6792FR, 0, this.FRE, 0);
                out[outOff] = encryptByte(in[inOff], 0);
                out[outOff + 1] = encryptByte(in[inOff + 1], 1);
                System.arraycopy(this.f6792FR, 2, this.f6792FR, 0, this.blockSize - 2);
                System.arraycopy(out, outOff, this.f6792FR, this.blockSize - 2, 2);
                this.cipher.processBlock(this.f6792FR, 0, this.FRE, 0);
                for (int n3 = 2; n3 < this.blockSize; n3++) {
                    byte[] bArr5 = this.f6792FR;
                    int i7 = n3 - 2;
                    int i8 = outOff + n3;
                    byte encryptByte5 = encryptByte(in[inOff + n3], n3 - 2);
                    out[i8] = encryptByte5;
                    bArr5[i7] = encryptByte5;
                }
                this.count += this.blockSize;
            }
            return this.blockSize;
        }
    }

    private int decryptBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        if (this.blockSize + inOff > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (this.blockSize + outOff > out.length) {
            throw new DataLengthException("output buffer too short");
        } else {
            if (this.count > this.blockSize) {
                byte inVal = in[inOff];
                this.f6792FR[this.blockSize - 2] = inVal;
                out[outOff] = encryptByte(inVal, this.blockSize - 2);
                byte inVal2 = in[inOff + 1];
                this.f6792FR[this.blockSize - 1] = inVal2;
                out[outOff + 1] = encryptByte(inVal2, this.blockSize - 1);
                this.cipher.processBlock(this.f6792FR, 0, this.FRE, 0);
                for (int n = 2; n < this.blockSize; n++) {
                    byte inVal3 = in[inOff + n];
                    this.f6792FR[n - 2] = inVal3;
                    out[outOff + n] = encryptByte(inVal3, n - 2);
                }
            } else if (this.count == 0) {
                this.cipher.processBlock(this.f6792FR, 0, this.FRE, 0);
                for (int n2 = 0; n2 < this.blockSize; n2++) {
                    this.f6792FR[n2] = in[inOff + n2];
                    out[n2] = encryptByte(in[inOff + n2], n2);
                }
                this.count += this.blockSize;
            } else if (this.count == this.blockSize) {
                this.cipher.processBlock(this.f6792FR, 0, this.FRE, 0);
                byte inVal1 = in[inOff];
                byte inVal22 = in[inOff + 1];
                out[outOff] = encryptByte(inVal1, 0);
                out[outOff + 1] = encryptByte(inVal22, 1);
                System.arraycopy(this.f6792FR, 2, this.f6792FR, 0, this.blockSize - 2);
                this.f6792FR[this.blockSize - 2] = inVal1;
                this.f6792FR[this.blockSize - 1] = inVal22;
                this.cipher.processBlock(this.f6792FR, 0, this.FRE, 0);
                for (int n3 = 2; n3 < this.blockSize; n3++) {
                    byte inVal4 = in[inOff + n3];
                    this.f6792FR[n3 - 2] = inVal4;
                    out[outOff + n3] = encryptByte(inVal4, n3 - 2);
                }
                this.count += this.blockSize;
            }
            return this.blockSize;
        }
    }
}
