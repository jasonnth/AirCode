package org.spongycastle.crypto.prng;

import java.security.SecureRandom;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.prng.drbg.CTRSP800DRBG;
import org.spongycastle.crypto.prng.drbg.DualECPoints;
import org.spongycastle.crypto.prng.drbg.DualECSP800DRBG;
import org.spongycastle.crypto.prng.drbg.HMacSP800DRBG;
import org.spongycastle.crypto.prng.drbg.HashSP800DRBG;
import org.spongycastle.crypto.prng.drbg.SP80090DRBG;

public class SP800SecureRandomBuilder {
    private int entropyBitsRequired;
    private final EntropySourceProvider entropySourceProvider;
    private byte[] personalizationString;
    private final SecureRandom random;
    private int securityStrength;

    private static class CTRDRBGProvider implements DRBGProvider {
        private final BlockCipher blockCipher;
        private final int keySizeInBits;
        private final byte[] nonce;
        private final byte[] personalizationString;
        private final int securityStrength;

        public CTRDRBGProvider(BlockCipher blockCipher2, int keySizeInBits2, byte[] nonce2, byte[] personalizationString2, int securityStrength2) {
            this.blockCipher = blockCipher2;
            this.keySizeInBits = keySizeInBits2;
            this.nonce = nonce2;
            this.personalizationString = personalizationString2;
            this.securityStrength = securityStrength2;
        }

        public SP80090DRBG get(EntropySource entropySource) {
            return new CTRSP800DRBG(this.blockCipher, this.keySizeInBits, this.securityStrength, entropySource, this.personalizationString, this.nonce);
        }
    }

    private static class ConfigurableDualECDRBGProvider implements DRBGProvider {
        private final Digest digest;
        private final byte[] nonce;
        private final byte[] personalizationString;
        private final DualECPoints[] pointSet;
        private final int securityStrength;

        public ConfigurableDualECDRBGProvider(DualECPoints[] pointSet2, Digest digest2, byte[] nonce2, byte[] personalizationString2, int securityStrength2) {
            this.pointSet = new DualECPoints[pointSet2.length];
            System.arraycopy(pointSet2, 0, this.pointSet, 0, pointSet2.length);
            this.digest = digest2;
            this.nonce = nonce2;
            this.personalizationString = personalizationString2;
            this.securityStrength = securityStrength2;
        }

        public SP80090DRBG get(EntropySource entropySource) {
            return new DualECSP800DRBG(this.pointSet, this.digest, this.securityStrength, entropySource, this.personalizationString, this.nonce);
        }
    }

    private static class DualECDRBGProvider implements DRBGProvider {
        private final Digest digest;
        private final byte[] nonce;
        private final byte[] personalizationString;
        private final int securityStrength;

        public DualECDRBGProvider(Digest digest2, byte[] nonce2, byte[] personalizationString2, int securityStrength2) {
            this.digest = digest2;
            this.nonce = nonce2;
            this.personalizationString = personalizationString2;
            this.securityStrength = securityStrength2;
        }

        public SP80090DRBG get(EntropySource entropySource) {
            return new DualECSP800DRBG(this.digest, this.securityStrength, entropySource, this.personalizationString, this.nonce);
        }
    }

    private static class HMacDRBGProvider implements DRBGProvider {
        private final Mac hMac;
        private final byte[] nonce;
        private final byte[] personalizationString;
        private final int securityStrength;

        public HMacDRBGProvider(Mac hMac2, byte[] nonce2, byte[] personalizationString2, int securityStrength2) {
            this.hMac = hMac2;
            this.nonce = nonce2;
            this.personalizationString = personalizationString2;
            this.securityStrength = securityStrength2;
        }

        public SP80090DRBG get(EntropySource entropySource) {
            return new HMacSP800DRBG(this.hMac, this.securityStrength, entropySource, this.personalizationString, this.nonce);
        }
    }

    private static class HashDRBGProvider implements DRBGProvider {
        private final Digest digest;
        private final byte[] nonce;
        private final byte[] personalizationString;
        private final int securityStrength;

        public HashDRBGProvider(Digest digest2, byte[] nonce2, byte[] personalizationString2, int securityStrength2) {
            this.digest = digest2;
            this.nonce = nonce2;
            this.personalizationString = personalizationString2;
            this.securityStrength = securityStrength2;
        }

        public SP80090DRBG get(EntropySource entropySource) {
            return new HashSP800DRBG(this.digest, this.securityStrength, entropySource, this.personalizationString, this.nonce);
        }
    }

    public SP800SecureRandomBuilder() {
        this(new SecureRandom(), false);
    }

    public SP800SecureRandomBuilder(SecureRandom entropySource, boolean predictionResistant) {
        this.securityStrength = 256;
        this.entropyBitsRequired = 256;
        this.random = entropySource;
        this.entropySourceProvider = new BasicEntropySourceProvider(this.random, predictionResistant);
    }

    public SP800SecureRandomBuilder(EntropySourceProvider entropySourceProvider2) {
        this.securityStrength = 256;
        this.entropyBitsRequired = 256;
        this.random = null;
        this.entropySourceProvider = entropySourceProvider2;
    }

    public SP800SecureRandomBuilder setPersonalizationString(byte[] personalizationString2) {
        this.personalizationString = personalizationString2;
        return this;
    }

    public SP800SecureRandomBuilder setSecurityStrength(int securityStrength2) {
        this.securityStrength = securityStrength2;
        return this;
    }

    public SP800SecureRandomBuilder setEntropyBitsRequired(int entropyBitsRequired2) {
        this.entropyBitsRequired = entropyBitsRequired2;
        return this;
    }

    public SP800SecureRandom buildHash(Digest digest, byte[] nonce, boolean predictionResistant) {
        return new SP800SecureRandom(this.random, this.entropySourceProvider.get(this.entropyBitsRequired), new HashDRBGProvider(digest, nonce, this.personalizationString, this.securityStrength), predictionResistant);
    }

    public SP800SecureRandom buildCTR(BlockCipher cipher, int keySizeInBits, byte[] nonce, boolean predictionResistant) {
        return new SP800SecureRandom(this.random, this.entropySourceProvider.get(this.entropyBitsRequired), new CTRDRBGProvider(cipher, keySizeInBits, nonce, this.personalizationString, this.securityStrength), predictionResistant);
    }

    public SP800SecureRandom buildHMAC(Mac hMac, byte[] nonce, boolean predictionResistant) {
        return new SP800SecureRandom(this.random, this.entropySourceProvider.get(this.entropyBitsRequired), new HMacDRBGProvider(hMac, nonce, this.personalizationString, this.securityStrength), predictionResistant);
    }

    public SP800SecureRandom buildDualEC(Digest digest, byte[] nonce, boolean predictionResistant) {
        return new SP800SecureRandom(this.random, this.entropySourceProvider.get(this.entropyBitsRequired), new DualECDRBGProvider(digest, nonce, this.personalizationString, this.securityStrength), predictionResistant);
    }

    public SP800SecureRandom buildDualEC(DualECPoints[] pointSet, Digest digest, byte[] nonce, boolean predictionResistant) {
        return new SP800SecureRandom(this.random, this.entropySourceProvider.get(this.entropyBitsRequired), new ConfigurableDualECDRBGProvider(pointSet, digest, nonce, this.personalizationString, this.securityStrength), predictionResistant);
    }
}
