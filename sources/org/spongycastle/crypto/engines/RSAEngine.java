package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;

public class RSAEngine implements AsymmetricBlockCipher {
    private RSACoreEngine core;

    public void init(boolean forEncryption, CipherParameters param) {
        if (this.core == null) {
            this.core = new RSACoreEngine();
        }
        this.core.init(forEncryption, param);
    }

    public int getInputBlockSize() {
        return this.core.getInputBlockSize();
    }

    public int getOutputBlockSize() {
        return this.core.getOutputBlockSize();
    }

    public byte[] processBlock(byte[] in, int inOff, int inLen) {
        if (this.core != null) {
            return this.core.convertOutput(this.core.processBlock(this.core.convertInput(in, inOff, inLen)));
        }
        throw new IllegalStateException("RSA engine not initialised");
    }
}
