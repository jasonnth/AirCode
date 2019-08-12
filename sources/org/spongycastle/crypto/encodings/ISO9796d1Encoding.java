package org.spongycastle.crypto.encodings;

import java.math.BigInteger;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.PassportService;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSAKeyParameters;

public class ISO9796d1Encoding implements AsymmetricBlockCipher {
    private static final BigInteger SIX = BigInteger.valueOf(6);
    private static final BigInteger SIXTEEN = BigInteger.valueOf(16);
    private static byte[] inverse = {8, 15, 6, 1, 5, 2, PassportService.SF_DG11, PassportService.SF_DG12, 3, 4, 13, 10, 14, 9, 0, 7};
    private static byte[] shadows = {14, 3, 5, 8, 9, 4, 2, 15, 0, 13, PassportService.SF_DG11, 6, 7, 10, PassportService.SF_DG12, 1};
    private int bitSize;
    private AsymmetricBlockCipher engine;
    private boolean forEncryption;
    private BigInteger modulus;
    private int padBits = 0;

    public ISO9796d1Encoding(AsymmetricBlockCipher cipher) {
        this.engine = cipher;
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    public void init(boolean forEncryption2, CipherParameters param) {
        RSAKeyParameters kParam;
        if (param instanceof ParametersWithRandom) {
            kParam = (RSAKeyParameters) ((ParametersWithRandom) param).getParameters();
        } else {
            kParam = (RSAKeyParameters) param;
        }
        this.engine.init(forEncryption2, param);
        this.modulus = kParam.getModulus();
        this.bitSize = this.modulus.bitLength();
        this.forEncryption = forEncryption2;
    }

    public int getInputBlockSize() {
        int baseBlockSize = this.engine.getInputBlockSize();
        if (this.forEncryption) {
            return (baseBlockSize + 1) / 2;
        }
        return baseBlockSize;
    }

    public int getOutputBlockSize() {
        int baseBlockSize = this.engine.getOutputBlockSize();
        return this.forEncryption ? baseBlockSize : (baseBlockSize + 1) / 2;
    }

    public void setPadBits(int padBits2) {
        if (padBits2 > 7) {
            throw new IllegalArgumentException("padBits > 7");
        }
        this.padBits = padBits2;
    }

    public int getPadBits() {
        return this.padBits;
    }

    public byte[] processBlock(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        if (this.forEncryption) {
            return encodeBlock(in, inOff, inLen);
        }
        return decodeBlock(in, inOff, inLen);
    }

    private byte[] encodeBlock(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        byte[] block = new byte[((this.bitSize + 7) / 8)];
        int r = this.padBits + 1;
        int z = inLen;
        int t = (this.bitSize + 13) / 16;
        for (int i = 0; i < t; i += z) {
            if (i > t - z) {
                System.arraycopy(in, (inOff + inLen) - (t - i), block, block.length - t, t - i);
            } else {
                System.arraycopy(in, inOff, block, block.length - (i + z), z);
            }
        }
        for (int i2 = block.length - (t * 2); i2 != block.length; i2 += 2) {
            byte val = block[(block.length - t) + (i2 / 2)];
            block[i2] = (byte) ((shadows[(val & 255) >>> 4] << 4) | shadows[val & 15]);
            block[i2 + 1] = val;
        }
        int length = block.length - (z * 2);
        block[length] = (byte) (block[length] ^ r);
        block[block.length - 1] = (byte) ((block[block.length - 1] << 4) | 6);
        int maxBit = 8 - ((this.bitSize - 1) % 8);
        int offSet = 0;
        if (maxBit != 8) {
            block[0] = (byte) (block[0] & (255 >>> maxBit));
            block[0] = (byte) (block[0] | (128 >>> maxBit));
        } else {
            block[0] = 0;
            block[1] = (byte) (block[1] | ISOFileInfo.DATA_BYTES1);
            offSet = 1;
        }
        return this.engine.processBlock(block, offSet, block.length - offSet);
    }

    private byte[] decodeBlock(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        BigInteger iR;
        int r = 1;
        int t = (this.bitSize + 13) / 16;
        BigInteger iS = new BigInteger(1, this.engine.processBlock(in, inOff, inLen));
        if (iS.mod(SIXTEEN).equals(SIX)) {
            iR = iS;
        } else if (this.modulus.subtract(iS).mod(SIXTEEN).equals(SIX)) {
            iR = this.modulus.subtract(iS);
        } else {
            throw new InvalidCipherTextException("resulting integer iS or (modulus - iS) is not congruent to 6 mod 16");
        }
        byte[] block = convertOutputDecryptOnly(iR);
        if ((block[block.length - 1] & 15) != 6) {
            throw new InvalidCipherTextException("invalid forcing byte in block");
        }
        block[block.length - 1] = (byte) (((block[block.length - 1] & 255) >>> 4) | (inverse[(block[block.length - 2] & 255) >> 4] << 4));
        block[0] = (byte) ((shadows[(block[1] & 255) >>> 4] << 4) | shadows[block[1] & 15]);
        boolean boundaryFound = false;
        int boundary = 0;
        for (int i = block.length - 1; i >= block.length - (t * 2); i -= 2) {
            int val = (shadows[(block[i] & 255) >>> 4] << 4) | shadows[block[i] & 15];
            if (((block[i - 1] ^ val) & 255) != 0) {
                if (!boundaryFound) {
                    boundaryFound = true;
                    r = (block[i - 1] ^ val) & 255;
                    boundary = i - 1;
                } else {
                    throw new InvalidCipherTextException("invalid tsums in block");
                }
            }
        }
        block[boundary] = 0;
        byte[] nblock = new byte[((block.length - boundary) / 2)];
        for (int i2 = 0; i2 < nblock.length; i2++) {
            nblock[i2] = block[(i2 * 2) + boundary + 1];
        }
        this.padBits = r - 1;
        return nblock;
    }

    private static byte[] convertOutputDecryptOnly(BigInteger result) {
        byte[] output = result.toByteArray();
        if (output[0] != 0) {
            return output;
        }
        byte[] tmp = new byte[(output.length - 1)];
        System.arraycopy(output, 1, tmp, 0, tmp.length);
        return tmp;
    }
}
