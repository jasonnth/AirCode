package org.spongycastle.pqc.crypto.rainbow;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class RainbowKeyGenerationParameters extends KeyGenerationParameters {
    private RainbowParameters params;

    public RainbowKeyGenerationParameters(SecureRandom random, RainbowParameters params2) {
        super(random, params2.getVi()[params2.getVi().length - 1] - params2.getVi()[0]);
        this.params = params2;
    }

    public RainbowParameters getParameters() {
        return this.params;
    }
}
