package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class McElieceCCA2KeyGenerationParameters extends KeyGenerationParameters {
    private McElieceCCA2Parameters params;

    public McElieceCCA2KeyGenerationParameters(SecureRandom random, McElieceCCA2Parameters params2) {
        super(random, 128);
        this.params = params2;
    }

    public McElieceCCA2Parameters getParameters() {
        return this.params;
    }
}
