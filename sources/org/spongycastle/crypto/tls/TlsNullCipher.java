package org.spongycastle.crypto.tls;

import java.io.IOException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Arrays;

public class TlsNullCipher implements TlsCipher {
    protected TlsContext context;
    protected TlsMac readMac;
    protected TlsMac writeMac;

    public TlsNullCipher(TlsContext context2) {
        this.context = context2;
        this.writeMac = null;
        this.readMac = null;
    }

    public TlsNullCipher(TlsContext context2, Digest clientWriteDigest, Digest serverWriteDigest) throws IOException {
        if ((clientWriteDigest == null) != (serverWriteDigest == null)) {
            throw new TlsFatalAlert(80);
        }
        this.context = context2;
        TlsMac clientWriteMac = null;
        TlsMac serverWriteMac = null;
        if (clientWriteDigest != null) {
            int key_block_size = clientWriteDigest.getDigestSize() + serverWriteDigest.getDigestSize();
            byte[] key_block = TlsUtils.calculateKeyBlock(context2, key_block_size);
            clientWriteMac = new TlsMac(context2, clientWriteDigest, key_block, 0, clientWriteDigest.getDigestSize());
            int offset = 0 + clientWriteDigest.getDigestSize();
            serverWriteMac = new TlsMac(context2, serverWriteDigest, key_block, offset, serverWriteDigest.getDigestSize());
            if (offset + serverWriteDigest.getDigestSize() != key_block_size) {
                throw new TlsFatalAlert(80);
            }
        }
        if (context2.isServer()) {
            this.writeMac = serverWriteMac;
            this.readMac = clientWriteMac;
            return;
        }
        this.writeMac = clientWriteMac;
        this.readMac = serverWriteMac;
    }

    public int getPlaintextLimit(int ciphertextLimit) {
        int result = ciphertextLimit;
        if (this.writeMac != null) {
            return result - this.writeMac.getSize();
        }
        return result;
    }

    public byte[] encodePlaintext(long seqNo, short type, byte[] plaintext, int offset, int len) throws IOException {
        if (this.writeMac == null) {
            return Arrays.copyOfRange(plaintext, offset, offset + len);
        }
        byte[] mac = this.writeMac.calculateMac(seqNo, type, plaintext, offset, len);
        byte[] ciphertext = new byte[(mac.length + len)];
        System.arraycopy(plaintext, offset, ciphertext, 0, len);
        System.arraycopy(mac, 0, ciphertext, len, mac.length);
        return ciphertext;
    }

    public byte[] decodeCiphertext(long seqNo, short type, byte[] ciphertext, int offset, int len) throws IOException {
        if (this.readMac == null) {
            return Arrays.copyOfRange(ciphertext, offset, offset + len);
        }
        int macSize = this.readMac.getSize();
        if (len < macSize) {
            throw new TlsFatalAlert(50);
        }
        int macInputLen = len - macSize;
        if (!Arrays.constantTimeAreEqual(Arrays.copyOfRange(ciphertext, offset + macInputLen, offset + len), this.readMac.calculateMac(seqNo, type, ciphertext, offset, macInputLen))) {
            throw new TlsFatalAlert(20);
        }
        return Arrays.copyOfRange(ciphertext, offset, offset + macInputLen);
    }
}
