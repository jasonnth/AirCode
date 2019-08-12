package org.spongycastle.jcajce.provider.asymmetric.elgamal;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.DHParameterSpec;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.generators.ElGamalKeyPairGenerator;
import org.spongycastle.crypto.generators.ElGamalParametersGenerator;
import org.spongycastle.crypto.params.ElGamalKeyGenerationParameters;
import org.spongycastle.crypto.params.ElGamalParameters;
import org.spongycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.spongycastle.crypto.params.ElGamalPublicKeyParameters;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ElGamalParameterSpec;

public class KeyPairGeneratorSpi extends KeyPairGenerator {
    int certainty = 20;
    ElGamalKeyPairGenerator engine = new ElGamalKeyPairGenerator();
    boolean initialised = false;
    ElGamalKeyGenerationParameters param;
    SecureRandom random = new SecureRandom();
    int strength = 1024;

    public KeyPairGeneratorSpi() {
        super("ElGamal");
    }

    public void initialize(int strength2, SecureRandom random2) {
        this.strength = strength2;
        this.random = random2;
    }

    public void initialize(AlgorithmParameterSpec params, SecureRandom random2) throws InvalidAlgorithmParameterException {
        if ((params instanceof ElGamalParameterSpec) || (params instanceof DHParameterSpec)) {
            if (params instanceof ElGamalParameterSpec) {
                ElGamalParameterSpec elParams = (ElGamalParameterSpec) params;
                this.param = new ElGamalKeyGenerationParameters(random2, new ElGamalParameters(elParams.getP(), elParams.getG()));
            } else {
                DHParameterSpec dhParams = (DHParameterSpec) params;
                this.param = new ElGamalKeyGenerationParameters(random2, new ElGamalParameters(dhParams.getP(), dhParams.getG(), dhParams.getL()));
            }
            this.engine.init(this.param);
            this.initialised = true;
            return;
        }
        throw new InvalidAlgorithmParameterException("parameter object not a DHParameterSpec or an ElGamalParameterSpec");
    }

    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            DHParameterSpec dhParams = BouncyCastleProvider.CONFIGURATION.getDHDefaultParameters(this.strength);
            if (dhParams != null) {
                this.param = new ElGamalKeyGenerationParameters(this.random, new ElGamalParameters(dhParams.getP(), dhParams.getG(), dhParams.getL()));
            } else {
                ElGamalParametersGenerator pGen = new ElGamalParametersGenerator();
                pGen.init(this.strength, this.certainty, this.random);
                this.param = new ElGamalKeyGenerationParameters(this.random, pGen.generateParameters());
            }
            this.engine.init(this.param);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair pair = this.engine.generateKeyPair();
        return new KeyPair(new BCElGamalPublicKey((ElGamalPublicKeyParameters) pair.getPublic()), new BCElGamalPrivateKey((ElGamalPrivateKeyParameters) pair.getPrivate()));
    }
}
