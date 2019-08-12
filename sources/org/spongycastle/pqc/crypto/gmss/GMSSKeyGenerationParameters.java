package org.spongycastle.pqc.crypto.gmss;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class GMSSKeyGenerationParameters extends KeyGenerationParameters {
    private GMSSParameters params;

    public GMSSKeyGenerationParameters(SecureRandom random, GMSSParameters params2) {
        super(random, 1);
        this.params = params2;
    }

    public GMSSParameters getParameters() {
        return this.params;
    }
}
