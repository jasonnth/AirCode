package org.spongycastle.crypto.params;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class ECKeyGenerationParameters extends KeyGenerationParameters {
    private ECDomainParameters domainParams;

    public ECKeyGenerationParameters(ECDomainParameters domainParams2, SecureRandom random) {
        super(random, domainParams2.getN().bitLength());
        this.domainParams = domainParams2;
    }

    public ECDomainParameters getDomainParameters() {
        return this.domainParams;
    }
}
