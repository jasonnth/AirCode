package org.spongycastle.crypto.tls;

import java.io.IOException;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.engines.AESEngine;
import org.spongycastle.crypto.engines.CamelliaEngine;
import org.spongycastle.crypto.engines.DESedeEngine;
import org.spongycastle.crypto.engines.RC4Engine;
import org.spongycastle.crypto.engines.SEEDEngine;
import org.spongycastle.crypto.engines.Salsa20Engine;
import org.spongycastle.crypto.modes.AEADBlockCipher;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.modes.CCMBlockCipher;
import org.spongycastle.crypto.modes.GCMBlockCipher;

public class DefaultTlsCipherFactory extends AbstractTlsCipherFactory {
    public TlsCipher createCipher(TlsContext context, int encryptionAlgorithm, int macAlgorithm) throws IOException {
        switch (encryptionAlgorithm) {
            case 0:
                return createNullCipher(context, macAlgorithm);
            case 2:
                return createRC4Cipher(context, 16, macAlgorithm);
            case 7:
                return createDESedeCipher(context, macAlgorithm);
            case 8:
                return createAESCipher(context, 16, macAlgorithm);
            case 9:
                return createAESCipher(context, 32, macAlgorithm);
            case 10:
                return createCipher_AES_GCM(context, 16, 16);
            case 11:
                return createCipher_AES_GCM(context, 32, 16);
            case 12:
                return createCamelliaCipher(context, 16, macAlgorithm);
            case 13:
                return createCamelliaCipher(context, 32, macAlgorithm);
            case 14:
                return createSEEDCipher(context, macAlgorithm);
            case 15:
                return createCipher_AES_CCM(context, 16, 16);
            case 16:
                return createCipher_AES_CCM(context, 16, 8);
            case 17:
                return createCipher_AES_CCM(context, 32, 16);
            case 18:
                return createCipher_AES_CCM(context, 32, 8);
            case 19:
                return createCipher_Camellia_GCM(context, 16, 16);
            case 20:
                return createCipher_Camellia_GCM(context, 32, 16);
            case 100:
                return createSalsa20Cipher(context, 12, 32, macAlgorithm);
            case 101:
                return createSalsa20Cipher(context, 20, 32, macAlgorithm);
            case 102:
                return createChaCha20Poly1305(context);
            default:
                throw new TlsFatalAlert(80);
        }
    }

    /* access modifiers changed from: protected */
    public TlsBlockCipher createAESCipher(TlsContext context, int cipherKeySize, int macAlgorithm) throws IOException {
        return new TlsBlockCipher(context, createAESBlockCipher(), createAESBlockCipher(), createHMACDigest(macAlgorithm), createHMACDigest(macAlgorithm), cipherKeySize);
    }

    /* access modifiers changed from: protected */
    public TlsBlockCipher createCamelliaCipher(TlsContext context, int cipherKeySize, int macAlgorithm) throws IOException {
        return new TlsBlockCipher(context, createCamelliaBlockCipher(), createCamelliaBlockCipher(), createHMACDigest(macAlgorithm), createHMACDigest(macAlgorithm), cipherKeySize);
    }

    /* access modifiers changed from: protected */
    public TlsCipher createChaCha20Poly1305(TlsContext context) throws IOException {
        return new Chacha20Poly1305(context);
    }

    /* access modifiers changed from: protected */
    public TlsAEADCipher createCipher_AES_CCM(TlsContext context, int cipherKeySize, int macSize) throws IOException {
        return new TlsAEADCipher(context, createAEADBlockCipher_AES_CCM(), createAEADBlockCipher_AES_CCM(), cipherKeySize, macSize);
    }

    /* access modifiers changed from: protected */
    public TlsAEADCipher createCipher_AES_GCM(TlsContext context, int cipherKeySize, int macSize) throws IOException {
        return new TlsAEADCipher(context, createAEADBlockCipher_AES_GCM(), createAEADBlockCipher_AES_GCM(), cipherKeySize, macSize);
    }

    /* access modifiers changed from: protected */
    public TlsAEADCipher createCipher_Camellia_GCM(TlsContext context, int cipherKeySize, int macSize) throws IOException {
        return new TlsAEADCipher(context, createAEADBlockCipher_Camellia_GCM(), createAEADBlockCipher_Camellia_GCM(), cipherKeySize, macSize);
    }

    /* access modifiers changed from: protected */
    public TlsBlockCipher createDESedeCipher(TlsContext context, int macAlgorithm) throws IOException {
        return new TlsBlockCipher(context, createDESedeBlockCipher(), createDESedeBlockCipher(), createHMACDigest(macAlgorithm), createHMACDigest(macAlgorithm), 24);
    }

    /* access modifiers changed from: protected */
    public TlsNullCipher createNullCipher(TlsContext context, int macAlgorithm) throws IOException {
        return new TlsNullCipher(context, createHMACDigest(macAlgorithm), createHMACDigest(macAlgorithm));
    }

    /* access modifiers changed from: protected */
    public TlsStreamCipher createRC4Cipher(TlsContext context, int cipherKeySize, int macAlgorithm) throws IOException {
        return new TlsStreamCipher(context, createRC4StreamCipher(), createRC4StreamCipher(), createHMACDigest(macAlgorithm), createHMACDigest(macAlgorithm), cipherKeySize, false);
    }

    /* access modifiers changed from: protected */
    public TlsStreamCipher createSalsa20Cipher(TlsContext context, int rounds, int cipherKeySize, int macAlgorithm) throws IOException {
        return new TlsStreamCipher(context, createSalsa20StreamCipher(rounds), createSalsa20StreamCipher(rounds), createHMACDigest(macAlgorithm), createHMACDigest(macAlgorithm), cipherKeySize, true);
    }

    /* access modifiers changed from: protected */
    public TlsBlockCipher createSEEDCipher(TlsContext context, int macAlgorithm) throws IOException {
        return new TlsBlockCipher(context, createSEEDBlockCipher(), createSEEDBlockCipher(), createHMACDigest(macAlgorithm), createHMACDigest(macAlgorithm), 16);
    }

    /* access modifiers changed from: protected */
    public BlockCipher createAESEngine() {
        return new AESEngine();
    }

    /* access modifiers changed from: protected */
    public BlockCipher createCamelliaEngine() {
        return new CamelliaEngine();
    }

    /* access modifiers changed from: protected */
    public BlockCipher createAESBlockCipher() {
        return new CBCBlockCipher(createAESEngine());
    }

    /* access modifiers changed from: protected */
    public AEADBlockCipher createAEADBlockCipher_AES_CCM() {
        return new CCMBlockCipher(createAESEngine());
    }

    /* access modifiers changed from: protected */
    public AEADBlockCipher createAEADBlockCipher_AES_GCM() {
        return new GCMBlockCipher(createAESEngine());
    }

    /* access modifiers changed from: protected */
    public AEADBlockCipher createAEADBlockCipher_Camellia_GCM() {
        return new GCMBlockCipher(createCamelliaEngine());
    }

    /* access modifiers changed from: protected */
    public BlockCipher createCamelliaBlockCipher() {
        return new CBCBlockCipher(createCamelliaEngine());
    }

    /* access modifiers changed from: protected */
    public BlockCipher createDESedeBlockCipher() {
        return new CBCBlockCipher(new DESedeEngine());
    }

    /* access modifiers changed from: protected */
    public StreamCipher createRC4StreamCipher() {
        return new RC4Engine();
    }

    /* access modifiers changed from: protected */
    public StreamCipher createSalsa20StreamCipher(int rounds) {
        return new Salsa20Engine(rounds);
    }

    /* access modifiers changed from: protected */
    public BlockCipher createSEEDBlockCipher() {
        return new CBCBlockCipher(new SEEDEngine());
    }

    /* access modifiers changed from: protected */
    public Digest createHMACDigest(int macAlgorithm) throws IOException {
        switch (macAlgorithm) {
            case 0:
                return null;
            case 1:
                return TlsUtils.createHash(1);
            case 2:
                return TlsUtils.createHash(2);
            case 3:
                return TlsUtils.createHash(4);
            case 4:
                return TlsUtils.createHash(5);
            case 5:
                return TlsUtils.createHash(6);
            default:
                throw new TlsFatalAlert(80);
        }
    }
}
