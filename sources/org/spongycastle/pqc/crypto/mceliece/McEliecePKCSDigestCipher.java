package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.crypto.MessageEncryptor;

public class McEliecePKCSDigestCipher {
    private boolean forEncrypting;
    private final MessageEncryptor mcElieceCipher;
    private final Digest messDigest;

    public McEliecePKCSDigestCipher(MessageEncryptor mcElieceCipher2, Digest messDigest2) {
        this.mcElieceCipher = mcElieceCipher2;
        this.messDigest = messDigest2;
    }

    public void init(boolean forEncrypting2, CipherParameters param) {
        AsymmetricKeyParameter k;
        this.forEncrypting = forEncrypting2;
        if (param instanceof ParametersWithRandom) {
            k = (AsymmetricKeyParameter) ((ParametersWithRandom) param).getParameters();
        } else {
            k = (AsymmetricKeyParameter) param;
        }
        if (forEncrypting2 && k.isPrivate()) {
            throw new IllegalArgumentException("Encrypting Requires Public Key.");
        } else if (forEncrypting2 || k.isPrivate()) {
            reset();
            this.mcElieceCipher.init(forEncrypting2, param);
        } else {
            throw new IllegalArgumentException("Decrypting Requires Private Key.");
        }
    }

    public byte[] messageEncrypt() {
        if (!this.forEncrypting) {
            throw new IllegalStateException("McEliecePKCSDigestCipher not initialised for encrypting.");
        }
        byte[] hash = new byte[this.messDigest.getDigestSize()];
        this.messDigest.doFinal(hash, 0);
        byte[] enc = null;
        try {
            return this.mcElieceCipher.messageEncrypt(hash);
        } catch (Exception e) {
            e.printStackTrace();
            return enc;
        }
    }

    public byte[] messageDecrypt(byte[] ciphertext) {
        byte[] output = null;
        if (this.forEncrypting) {
            throw new IllegalStateException("McEliecePKCSDigestCipher not initialised for decrypting.");
        }
        try {
            return this.mcElieceCipher.messageDecrypt(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
            return output;
        }
    }

    public void update(byte b) {
        this.messDigest.update(b);
    }

    public void update(byte[] in, int off, int len) {
        this.messDigest.update(in, off, len);
    }

    public void reset() {
        this.messDigest.reset();
    }
}
