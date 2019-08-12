package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.security.SecureRandom;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

public class TlsBlockCipher implements TlsCipher {
    protected TlsContext context;
    protected BlockCipher decryptCipher;
    protected BlockCipher encryptCipher;
    protected boolean encryptThenMAC;
    protected byte[] randomData = new byte[256];
    protected TlsMac readMac;
    protected boolean useExplicitIV;
    protected TlsMac writeMac;

    public TlsMac getWriteMac() {
        return this.writeMac;
    }

    public TlsMac getReadMac() {
        return this.readMac;
    }

    public TlsBlockCipher(TlsContext context2, BlockCipher clientWriteCipher, BlockCipher serverWriteCipher, Digest clientWriteDigest, Digest serverWriteDigest, int cipherKeySize) throws IOException {
        byte[] client_write_IV;
        byte[] server_write_IV;
        ParametersWithIV parametersWithIV;
        ParametersWithIV parametersWithIV2;
        this.context = context2;
        context2.getNonceRandomGenerator().nextBytes(this.randomData);
        this.useExplicitIV = TlsUtils.isTLSv11(context2);
        this.encryptThenMAC = context2.getSecurityParameters().encryptThenMAC;
        int key_block_size = (cipherKeySize * 2) + clientWriteDigest.getDigestSize() + serverWriteDigest.getDigestSize();
        if (!this.useExplicitIV) {
            key_block_size += clientWriteCipher.getBlockSize() + serverWriteCipher.getBlockSize();
        }
        byte[] key_block = TlsUtils.calculateKeyBlock(context2, key_block_size);
        TlsMac clientWriteMac = new TlsMac(context2, clientWriteDigest, key_block, 0, clientWriteDigest.getDigestSize());
        int offset = 0 + clientWriteDigest.getDigestSize();
        TlsMac serverWriteMac = new TlsMac(context2, serverWriteDigest, key_block, offset, serverWriteDigest.getDigestSize());
        int offset2 = offset + serverWriteDigest.getDigestSize();
        KeyParameter client_write_key = new KeyParameter(key_block, offset2, cipherKeySize);
        int offset3 = offset2 + cipherKeySize;
        KeyParameter keyParameter = new KeyParameter(key_block, offset3, cipherKeySize);
        int offset4 = offset3 + cipherKeySize;
        if (this.useExplicitIV) {
            client_write_IV = new byte[clientWriteCipher.getBlockSize()];
            server_write_IV = new byte[serverWriteCipher.getBlockSize()];
        } else {
            client_write_IV = Arrays.copyOfRange(key_block, offset4, clientWriteCipher.getBlockSize() + offset4);
            int offset5 = offset4 + clientWriteCipher.getBlockSize();
            server_write_IV = Arrays.copyOfRange(key_block, offset5, serverWriteCipher.getBlockSize() + offset5);
            offset4 = offset5 + serverWriteCipher.getBlockSize();
        }
        if (offset4 != key_block_size) {
            throw new TlsFatalAlert(80);
        }
        if (context2.isServer()) {
            this.writeMac = serverWriteMac;
            this.readMac = clientWriteMac;
            this.encryptCipher = serverWriteCipher;
            this.decryptCipher = clientWriteCipher;
            parametersWithIV = new ParametersWithIV(keyParameter, server_write_IV);
            parametersWithIV2 = new ParametersWithIV(client_write_key, client_write_IV);
        } else {
            this.writeMac = clientWriteMac;
            this.readMac = serverWriteMac;
            this.encryptCipher = clientWriteCipher;
            this.decryptCipher = serverWriteCipher;
            parametersWithIV = new ParametersWithIV(client_write_key, client_write_IV);
            parametersWithIV2 = new ParametersWithIV(keyParameter, server_write_IV);
        }
        this.encryptCipher.init(true, parametersWithIV);
        this.decryptCipher.init(false, parametersWithIV2);
    }

    public int getPlaintextLimit(int ciphertextLimit) {
        int plaintextLimit;
        int blockSize = this.encryptCipher.getBlockSize();
        int macSize = this.writeMac.getSize();
        int plaintextLimit2 = ciphertextLimit;
        if (this.useExplicitIV) {
            plaintextLimit2 -= blockSize;
        }
        if (this.encryptThenMAC) {
            int plaintextLimit3 = plaintextLimit2 - macSize;
            plaintextLimit = plaintextLimit3 - (plaintextLimit3 % blockSize);
        } else {
            plaintextLimit = (plaintextLimit2 - (plaintextLimit2 % blockSize)) - macSize;
        }
        return plaintextLimit - 1;
    }

    public byte[] encodePlaintext(long seqNo, short type, byte[] plaintext, int offset, int len) {
        int outOff;
        int blockSize = this.encryptCipher.getBlockSize();
        int macSize = this.writeMac.getSize();
        ProtocolVersion version = this.context.getServerVersion();
        int enc_input_length = len;
        if (!this.encryptThenMAC) {
            enc_input_length += macSize;
        }
        int padding_length = (blockSize - 1) - (enc_input_length % blockSize);
        if (!version.isDTLS() && !version.isSSL()) {
            padding_length += chooseExtraPadBlocks(this.context.getSecureRandom(), (255 - padding_length) / blockSize) * blockSize;
        }
        int totalSize = len + macSize + padding_length + 1;
        if (this.useExplicitIV) {
            totalSize += blockSize;
        }
        byte[] outBuf = new byte[totalSize];
        int outOff2 = 0;
        if (this.useExplicitIV) {
            byte[] explicitIV = new byte[blockSize];
            this.context.getNonceRandomGenerator().nextBytes(explicitIV);
            this.encryptCipher.init(true, new ParametersWithIV(null, explicitIV));
            System.arraycopy(explicitIV, 0, outBuf, 0, blockSize);
            outOff2 = 0 + blockSize;
        }
        int blocks_start = outOff2;
        System.arraycopy(plaintext, offset, outBuf, outOff2, len);
        int outOff3 = outOff2 + len;
        if (!this.encryptThenMAC) {
            byte[] mac = this.writeMac.calculateMac(seqNo, type, plaintext, offset, len);
            System.arraycopy(mac, 0, outBuf, outOff3, mac.length);
            outOff = outOff3 + mac.length;
        } else {
            outOff = outOff3;
        }
        int i = 0;
        while (i <= padding_length) {
            int outOff4 = outOff + 1;
            outBuf[outOff] = (byte) padding_length;
            i++;
            outOff = outOff4;
        }
        for (int i2 = blocks_start; i2 < outOff; i2 += blockSize) {
            this.encryptCipher.processBlock(outBuf, i2, outBuf, i2);
        }
        if (this.encryptThenMAC) {
            byte[] mac2 = this.writeMac.calculateMac(seqNo, type, outBuf, 0, outOff);
            System.arraycopy(mac2, 0, outBuf, outOff, mac2.length);
            int outOff5 = outOff + mac2.length;
        }
        return outBuf;
    }

    public byte[] decodeCiphertext(long seqNo, short type, byte[] ciphertext, int offset, int len) throws IOException {
        int minLen;
        int blocks_length;
        int blocks_length2;
        int i;
        int blockSize = this.decryptCipher.getBlockSize();
        int macSize = this.readMac.getSize();
        int minLen2 = blockSize;
        if (this.encryptThenMAC) {
            minLen = minLen2 + macSize;
        } else {
            minLen = Math.max(minLen2, macSize + 1);
        }
        if (this.useExplicitIV) {
            minLen += blockSize;
        }
        if (len < minLen) {
            throw new TlsFatalAlert(50);
        }
        int blocks_length3 = len;
        if (this.encryptThenMAC) {
            blocks_length = blocks_length3 - macSize;
        } else {
            blocks_length = blocks_length3;
        }
        if (blocks_length % blockSize != 0) {
            throw new TlsFatalAlert(21);
        }
        if (this.encryptThenMAC) {
            int end = offset + len;
            if (!Arrays.constantTimeAreEqual(this.readMac.calculateMac(seqNo, type, ciphertext, offset, len - macSize), Arrays.copyOfRange(ciphertext, end - macSize, end))) {
                throw new TlsFatalAlert(20);
            }
        }
        if (this.useExplicitIV) {
            this.decryptCipher.init(false, new ParametersWithIV(null, ciphertext, offset, blockSize));
            offset += blockSize;
            blocks_length2 = blocks_length - blockSize;
        } else {
            blocks_length2 = blocks_length;
        }
        for (int i2 = 0; i2 < blocks_length2; i2 += blockSize) {
            this.decryptCipher.processBlock(ciphertext, offset + i2, ciphertext, offset + i2);
        }
        if (this.encryptThenMAC) {
            i = 0;
        } else {
            i = macSize;
        }
        int totalPad = checkPaddingConstantTime(ciphertext, offset, blocks_length2, blockSize, i);
        boolean badMac = totalPad == 0;
        int dec_output_length = blocks_length2 - totalPad;
        if (!this.encryptThenMAC) {
            dec_output_length -= macSize;
            int macInputLen = dec_output_length;
            int macOff = offset + macInputLen;
            badMac |= !Arrays.constantTimeAreEqual(this.readMac.calculateMacConstantTime(seqNo, type, ciphertext, offset, macInputLen, blocks_length2 - macSize, this.randomData), Arrays.copyOfRange(ciphertext, macOff, macOff + macSize));
        }
        if (badMac) {
            throw new TlsFatalAlert(20);
        }
        return Arrays.copyOfRange(ciphertext, offset, offset + dec_output_length);
    }

    /* access modifiers changed from: protected */
    public int checkPaddingConstantTime(byte[] buf, int off, int len, int blockSize, int macSize) {
        int end = off + len;
        byte lastByte = buf[end - 1];
        int totalPad = (lastByte & 255) + 1;
        int dummyIndex = 0;
        byte padDiff = 0;
        if ((!TlsUtils.isSSL(this.context) || totalPad <= blockSize) && macSize + totalPad <= len) {
            int padPos = end - totalPad;
            while (true) {
                int padPos2 = padPos + 1;
                padDiff = (byte) ((buf[padPos] ^ lastByte) | padDiff);
                if (padPos2 >= end) {
                    break;
                }
                padPos = padPos2;
            }
            dummyIndex = totalPad;
            if (padDiff != 0) {
                totalPad = 0;
            }
        } else {
            totalPad = 0;
        }
        byte[] dummyPad = this.randomData;
        while (true) {
            int dummyIndex2 = dummyIndex;
            if (dummyIndex2 < 256) {
                dummyIndex = dummyIndex2 + 1;
                padDiff = (byte) ((dummyPad[dummyIndex2] ^ lastByte) | padDiff);
            } else {
                dummyPad[0] = (byte) (dummyPad[0] ^ padDiff);
                return totalPad;
            }
        }
    }

    /* access modifiers changed from: protected */
    public int chooseExtraPadBlocks(SecureRandom r, int max) {
        return Math.min(lowestBitSet(r.nextInt()), max);
    }

    /* access modifiers changed from: protected */
    public int lowestBitSet(int x) {
        if (x == 0) {
            return 32;
        }
        int n = 0;
        while ((x & 1) == 0) {
            n++;
            x >>= 1;
        }
        return n;
    }
}
