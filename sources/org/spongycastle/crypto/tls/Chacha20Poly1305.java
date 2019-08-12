package org.spongycastle.crypto.tls;

import java.io.IOException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.engines.ChaChaEngine;
import org.spongycastle.crypto.generators.Poly1305KeyGenerator;
import org.spongycastle.crypto.macs.Poly1305;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

public class Chacha20Poly1305 implements TlsCipher {
    protected TlsContext context;
    protected ChaChaEngine decryptCipher;
    protected ChaChaEngine encryptCipher;

    public Chacha20Poly1305(TlsContext context2) throws IOException {
        KeyParameter encryptKey;
        KeyParameter decryptKey;
        if (!TlsUtils.isTLSv12(context2)) {
            throw new TlsFatalAlert(80);
        }
        this.context = context2;
        byte[] key_block = TlsUtils.calculateKeyBlock(context2, 64);
        KeyParameter client_write_key = new KeyParameter(key_block, 0, 32);
        KeyParameter server_write_key = new KeyParameter(key_block, 32, 32);
        this.encryptCipher = new ChaChaEngine(20);
        this.decryptCipher = new ChaChaEngine(20);
        if (context2.isServer()) {
            encryptKey = server_write_key;
            decryptKey = client_write_key;
        } else {
            encryptKey = client_write_key;
            decryptKey = server_write_key;
        }
        byte[] dummyNonce = new byte[8];
        this.encryptCipher.init(true, new ParametersWithIV(encryptKey, dummyNonce));
        this.decryptCipher.init(false, new ParametersWithIV(decryptKey, dummyNonce));
    }

    public int getPlaintextLimit(int ciphertextLimit) {
        return ciphertextLimit - 16;
    }

    public byte[] encodePlaintext(long seqNo, short type, byte[] plaintext, int offset, int len) throws IOException {
        int ciphertextLength = len + 16;
        KeyParameter macKey = initRecordMAC(this.encryptCipher, true, seqNo);
        byte[] output = new byte[ciphertextLength];
        this.encryptCipher.processBytes(plaintext, offset, len, output, 0);
        byte[] mac = calculateRecordMAC(macKey, getAdditionalData(seqNo, type, len), output, 0, len);
        System.arraycopy(mac, 0, output, len, mac.length);
        return output;
    }

    public byte[] decodeCiphertext(long seqNo, short type, byte[] ciphertext, int offset, int len) throws IOException {
        if (getPlaintextLimit(len) < 0) {
            throw new TlsFatalAlert(50);
        }
        int plaintextLength = len - 16;
        if (!Arrays.constantTimeAreEqual(calculateRecordMAC(initRecordMAC(this.decryptCipher, false, seqNo), getAdditionalData(seqNo, type, plaintextLength), ciphertext, offset, plaintextLength), Arrays.copyOfRange(ciphertext, offset + plaintextLength, offset + len))) {
            throw new TlsFatalAlert(20);
        }
        byte[] output = new byte[plaintextLength];
        this.decryptCipher.processBytes(ciphertext, offset, plaintextLength, output, 0);
        return output;
    }

    /* access modifiers changed from: protected */
    public KeyParameter initRecordMAC(ChaChaEngine cipher, boolean forEncryption, long seqNo) {
        byte[] nonce = new byte[8];
        TlsUtils.writeUint64(seqNo, nonce, 0);
        cipher.init(forEncryption, new ParametersWithIV(null, nonce));
        byte[] firstBlock = new byte[64];
        cipher.processBytes(firstBlock, 0, firstBlock.length, firstBlock, 0);
        System.arraycopy(firstBlock, 0, firstBlock, 32, 16);
        KeyParameter macKey = new KeyParameter(firstBlock, 16, 32);
        Poly1305KeyGenerator.clamp(macKey.getKey());
        return macKey;
    }

    /* access modifiers changed from: protected */
    public byte[] calculateRecordMAC(KeyParameter macKey, byte[] additionalData, byte[] buf, int off, int len) {
        Mac mac = new Poly1305();
        mac.init(macKey);
        updateRecordMAC(mac, additionalData, 0, additionalData.length);
        updateRecordMAC(mac, buf, off, len);
        byte[] output = new byte[mac.getMacSize()];
        mac.doFinal(output, 0);
        return output;
    }

    /* access modifiers changed from: protected */
    public void updateRecordMAC(Mac mac, byte[] buf, int off, int len) {
        mac.update(buf, off, len);
        byte[] longLen = Pack.longToLittleEndian(((long) len) & 4294967295L);
        mac.update(longLen, 0, longLen.length);
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
