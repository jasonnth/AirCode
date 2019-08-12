package org.spongycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.p325x9.X9ObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.util.Arrays;

public class KTSParameterSpec implements AlgorithmParameterSpec {
    private final AlgorithmIdentifier kdfAlgorithm;
    private final int keySizeInBits;
    private byte[] otherInfo;
    private final AlgorithmParameterSpec parameterSpec;
    private final String wrappingKeyAlgorithm;

    public static final class Builder {
        private final String algorithmName;
        private AlgorithmIdentifier kdfAlgorithm;
        private final int keySizeInBits;
        private byte[] otherInfo;
        private AlgorithmParameterSpec parameterSpec;

        public Builder(String algorithmName2, int keySizeInBits2) {
            this(algorithmName2, keySizeInBits2, null);
        }

        public Builder(String algorithmName2, int keySizeInBits2, byte[] otherInfo2) {
            this.algorithmName = algorithmName2;
            this.keySizeInBits = keySizeInBits2;
            this.kdfAlgorithm = new AlgorithmIdentifier(X9ObjectIdentifiers.id_kdf_kdf3, new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256));
            this.otherInfo = otherInfo2 == null ? new byte[0] : Arrays.clone(otherInfo2);
        }

        public Builder withParameterSpec(AlgorithmParameterSpec parameterSpec2) {
            this.parameterSpec = parameterSpec2;
            return this;
        }

        public Builder withKdfAlgorithm(AlgorithmIdentifier kdfAlgorithm2) {
            this.kdfAlgorithm = kdfAlgorithm2;
            return this;
        }

        public KTSParameterSpec build() {
            return new KTSParameterSpec(this.algorithmName, this.keySizeInBits, this.parameterSpec, this.kdfAlgorithm, this.otherInfo);
        }
    }

    private KTSParameterSpec(String wrappingKeyAlgorithm2, int keySizeInBits2, AlgorithmParameterSpec parameterSpec2, AlgorithmIdentifier kdfAlgorithm2, byte[] otherInfo2) {
        this.wrappingKeyAlgorithm = wrappingKeyAlgorithm2;
        this.keySizeInBits = keySizeInBits2;
        this.parameterSpec = parameterSpec2;
        this.kdfAlgorithm = kdfAlgorithm2;
        this.otherInfo = otherInfo2;
    }

    public String getKeyAlgorithmName() {
        return this.wrappingKeyAlgorithm;
    }

    public int getKeySize() {
        return this.keySizeInBits;
    }

    public AlgorithmParameterSpec getParameterSpec() {
        return this.parameterSpec;
    }

    public AlgorithmIdentifier getKdfAlgorithm() {
        return this.kdfAlgorithm;
    }

    public byte[] getOtherInfo() {
        return Arrays.clone(this.otherInfo);
    }
}
