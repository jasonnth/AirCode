package org.spongycastle.crypto.engines;

import java.math.BigInteger;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSABlindingParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;

public class RSABlindingEngine implements AsymmetricBlockCipher {
    private BigInteger blindingFactor;
    private RSACoreEngine core = new RSACoreEngine();
    private boolean forEncryption;
    private RSAKeyParameters key;

    public void init(boolean forEncryption2, CipherParameters param) {
        RSABlindingParameters p;
        if (param instanceof ParametersWithRandom) {
            p = (RSABlindingParameters) ((ParametersWithRandom) param).getParameters();
        } else {
            p = (RSABlindingParameters) param;
        }
        this.core.init(forEncryption2, p.getPublicKey());
        this.forEncryption = forEncryption2;
        this.key = p.getPublicKey();
        this.blindingFactor = p.getBlindingFactor();
    }

    public int getInputBlockSize() {
        return this.core.getInputBlockSize();
    }

    public int getOutputBlockSize() {
        return this.core.getOutputBlockSize();
    }

    public byte[] processBlock(byte[] in, int inOff, int inLen) {
        BigInteger msg;
        BigInteger msg2 = this.core.convertInput(in, inOff, inLen);
        if (this.forEncryption) {
            msg = blindMessage(msg2);
        } else {
            msg = unblindMessage(msg2);
        }
        return this.core.convertOutput(msg);
    }

    private BigInteger blindMessage(BigInteger msg) {
        return msg.multiply(this.blindingFactor.modPow(this.key.getExponent(), this.key.getModulus())).mod(this.key.getModulus());
    }

    private BigInteger unblindMessage(BigInteger blindedMsg) {
        BigInteger m = this.key.getModulus();
        return blindedMsg.multiply(this.blindingFactor.modInverse(m)).mod(m);
    }
}
