package org.spongycastle.crypto.prng;

import java.security.SecureRandom;

public class BasicEntropySourceProvider implements EntropySourceProvider {
    /* access modifiers changed from: private */
    public final boolean _predictionResistant;
    /* access modifiers changed from: private */
    public final SecureRandom _sr;

    public BasicEntropySourceProvider(SecureRandom random, boolean isPredictionResistant) {
        this._sr = random;
        this._predictionResistant = isPredictionResistant;
    }

    public EntropySource get(final int bitsRequired) {
        return new EntropySource() {
            public boolean isPredictionResistant() {
                return BasicEntropySourceProvider.this._predictionResistant;
            }

            public byte[] getEntropy() {
                if (!(BasicEntropySourceProvider.this._sr instanceof SP800SecureRandom) && !(BasicEntropySourceProvider.this._sr instanceof X931SecureRandom)) {
                    return BasicEntropySourceProvider.this._sr.generateSeed((bitsRequired + 7) / 8);
                }
                byte[] rv = new byte[((bitsRequired + 7) / 8)];
                BasicEntropySourceProvider.this._sr.nextBytes(rv);
                return rv;
            }

            public int entropySize() {
                return bitsRequired;
            }
        };
    }
}
