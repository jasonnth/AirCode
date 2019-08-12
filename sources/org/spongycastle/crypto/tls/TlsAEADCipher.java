package org.spongycastle.crypto.tls;

import java.io.IOException;
import org.spongycastle.crypto.modes.AEADBlockCipher;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;

public class TlsAEADCipher implements TlsCipher {
    protected TlsContext context;
    protected AEADBlockCipher decryptCipher;
    protected byte[] decryptImplicitNonce;
    protected AEADBlockCipher encryptCipher;
    protected byte[] encryptImplicitNonce;
    protected int macSize;
    protected int nonce_explicit_length;

    public TlsAEADCipher(TlsContext context2, AEADBlockCipher clientWriteCipher, AEADBlockCipher serverWriteCipher, int cipherKeySize, int macSize2) throws IOException {
        KeyParameter encryptKey;
        KeyParameter decryptKey;
        if (!TlsUtils.isTLSv12(context2)) {
            throw new TlsFatalAlert(80);
        }
        this.context = context2;
        this.macSize = macSize2;
        this.nonce_explicit_length = 8;
        int key_block_size = (cipherKeySize * 2) + 8;
        byte[] key_block = TlsUtils.calculateKeyBlock(context2, key_block_size);
        KeyParameter client_write_key = new KeyParameter(key_block, 0, cipherKeySize);
        int offset = 0 + cipherKeySize;
        KeyParameter server_write_key = new KeyParameter(key_block, offset, cipherKeySize);
        int offset2 = offset + cipherKeySize;
        byte[] client_write_IV = Arrays.copyOfRange(key_block, offset2, offset2 + 4);
        int offset3 = offset2 + 4;
        byte[] server_write_IV = Arrays.copyOfRange(key_block, offset3, offset3 + 4);
        if (offset3 + 4 != key_block_size) {
            throw new TlsFatalAlert(80);
        }
        if (context2.isServer()) {
            this.encryptCipher = serverWriteCipher;
            this.decryptCipher = clientWriteCipher;
            this.encryptImplicitNonce = server_write_IV;
            this.decryptImplicitNonce = client_write_IV;
            encryptKey = server_write_key;
            decryptKey = client_write_key;
        } else {
            this.encryptCipher = clientWriteCipher;
            this.decryptCipher = serverWriteCipher;
            this.encryptImplicitNonce = client_write_IV;
            this.decryptImplicitNonce = server_write_IV;
            encryptKey = client_write_key;
            decryptKey = server_write_key;
        }
        byte[] dummyNonce = new byte[(this.nonce_explicit_length + 4)];
        this.encryptCipher.init(true, new AEADParameters(encryptKey, macSize2 * 8, dummyNonce));
        this.decryptCipher.init(false, new AEADParameters(decryptKey, macSize2 * 8, dummyNonce));
    }

    public int getPlaintextLimit(int ciphertextLimit) {
        return (ciphertextLimit - this.macSize) - this.nonce_explicit_length;
    }

    public byte[] encodePlaintext(long seqNo, short type, byte[] plaintext, int offset, int len) throws IOException {
        byte[] nonce = new byte[(this.encryptImplicitNonce.length + this.nonce_explicit_length)];
        System.arraycopy(this.encryptImplicitNonce, 0, nonce, 0, this.encryptImplicitNonce.length);
        TlsUtils.writeUint64(seqNo, nonce, this.encryptImplicitNonce.length);
        int plaintextOffset = offset;
        int plaintextLength = len;
        byte[] output = new byte[(this.nonce_explicit_length + this.encryptCipher.getOutputSize(plaintextLength))];
        System.arraycopy(nonce, this.encryptImplicitNonce.length, output, 0, this.nonce_explicit_length);
        int outputPos = this.nonce_explicit_length;
        try {
            this.encryptCipher.init(true, new AEADParameters(null, this.macSize * 8, nonce, getAdditionalData(seqNo, type, plaintextLength)));
            int outputPos2 = outputPos + this.encryptCipher.processBytes(plaintext, plaintextOffset, plaintextLength, output, outputPos);
            if (outputPos2 + this.encryptCipher.doFinal(output, outputPos2) == output.length) {
                return output;
            }
            throw new TlsFatalAlert(80);
        } catch (Exception e) {
            throw new TlsFatalAlert(80, e);
        }
    }

    public byte[] decodeCiphertext(long seqNo, short type, byte[] ciphertext, int offset, int len) throws IOException {
        if (getPlaintextLimit(len) < 0) {
            throw new TlsFatalAlert(50);
        }
        byte[] nonce = new byte[(this.decryptImplicitNonce.length + this.nonce_explicit_length)];
        System.arraycopy(this.decryptImplicitNonce, 0, nonce, 0, this.decryptImplicitNonce.length);
        System.arraycopy(ciphertext, offset, nonce, this.decryptImplicitNonce.length, this.nonce_explicit_length);
        int ciphertextOffset = offset + this.nonce_explicit_length;
        int ciphertextLength = len - this.nonce_explicit_length;
        int plaintextLength = this.decryptCipher.getOutputSize(ciphertextLength);
        byte[] output = new byte[plaintextLength];
        try {
            this.decryptCipher.init(false, new AEADParameters(null, this.macSize * 8, nonce, getAdditionalData(seqNo, type, plaintextLength)));
            int outputPos = 0 + this.decryptCipher.processBytes(ciphertext, ciphertextOffset, ciphertextLength, output, 0);
            if (outputPos + this.decryptCipher.doFinal(output, outputPos) == output.length) {
                return output;
            }
            throw new TlsFatalAlert(80);
        } catch (Exception e) {
            throw new TlsFatalAlert(20, e);
        }
    }

    /* access modifiers changed from: protected */
    public byte[] getAdditionalData(long seqNo, short type, int len) throws IOException {
        byte[] additional_data = new byte[13];
        TlsUtils.writeUint64(seqNo, additional_data, 0);
        TlsUtils.writeUint8(type, additional_data, 8);
        TlsUtils.writeVersion(this.context.getServerVersion(), additional_data, 9);
        TlsUtils.writeUint16(len, additional_data, 11);
        return additional_data;
    }
}
