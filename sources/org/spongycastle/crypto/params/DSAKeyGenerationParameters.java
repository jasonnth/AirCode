package org.spongycastle.crypto.params;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class DSAKeyGenerationParameters extends KeyGenerationParameters {
    private DSAParameters params;

    public DSAKeyGenerationParameters(SecureRandom random, DSAParameters params2) {
        super(random, params2.getP().bitLength() - 1);
        this.params = params2;
    }

    public DSAParameters getParameters() {
        return this.params;
    }
}
