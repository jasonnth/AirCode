package org.spongycastle.crypto.prng;

import java.security.SecureRandom;
import org.spongycastle.crypto.prng.drbg.SP80090DRBG;

public class SP800SecureRandom extends SecureRandom {
    private SP80090DRBG drbg;
    private final DRBGProvider drbgProvider;
    private final EntropySource entropySource;
    private final boolean predictionResistant;
    private final SecureRandom randomSource;

    SP800SecureRandom(SecureRandom randomSource2, EntropySource entropySource2, DRBGProvider drbgProvider2, boolean predictionResistant2) {
        this.randomSource = randomSource2;
        this.entropySource = entropySource2;
        this.drbgProvider = drbgProvider2;
        this.predictionResistant = predictionResistant2;
    }

    public void setSeed(byte[] seed) {
        synchronized (this) {
            if (this.randomSource != null) {
                this.randomSource.setSeed(seed);
            }
        }
    }

    public void setSeed(long seed) {
        synchronized (this) {
            if (this.randomSource != null) {
                this.randomSource.setSeed(seed);
            }
        }
    }

    public void nextBytes(byte[] bytes) {
        synchronized (this) {
            if (this.drbg == null) {
                this.drbg = this.drbgProvider.get(this.entropySource);
            }
            if (this.drbg.generate(bytes, null, this.predictionResistant) < 0) {
                this.drbg.reseed(null);
                this.drbg.generate(bytes, null, this.predictionResistant);
            }
        }
    }

    public byte[] generateSeed(int numBytes) {
        return EntropyUtil.generateSeed(this.entropySource, numBytes);
    }
}
