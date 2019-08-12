package org.spongycastle.util.test;

import java.security.SecureRandom;
import org.spongycastle.crypto.prng.EntropySource;
import org.spongycastle.crypto.prng.EntropySourceProvider;

public class TestRandomEntropySourceProvider implements EntropySourceProvider {
    /* access modifiers changed from: private */
    public final boolean _predictionResistant;
    /* access modifiers changed from: private */
    public final SecureRandom _sr = new SecureRandom();

    public TestRandomEntropySourceProvider(boolean isPredictionResistant) {
        this._predictionResistant = isPredictionResistant;
    }

    public EntropySource get(final int bitsRequired) {
        return new EntropySource() {
            public boolean isPredictionResistant() {
                return TestRandomEntropySourceProvider.this._predictionResistant;
            }

            public byte[] getEntropy() {
                byte[] rv = new byte[((bitsRequired + 7) / 8)];
                TestRandomEntropySourceProvider.this._sr.nextBytes(rv);
                return rv;
            }

            public int entropySize() {
                return bitsRequired;
            }
        };
    }
}
