package org.spongycastle.jcajce.provider.asymmetric.gost;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.generators.GOST3410KeyPairGenerator;
import org.spongycastle.crypto.params.GOST3410KeyGenerationParameters;
import org.spongycastle.crypto.params.GOST3410Parameters;
import org.spongycastle.crypto.params.GOST3410PrivateKeyParameters;
import org.spongycastle.crypto.params.GOST3410PublicKeyParameters;
import org.spongycastle.jce.spec.GOST3410ParameterSpec;
import org.spongycastle.jce.spec.GOST3410PublicKeyParameterSetSpec;

public class KeyPairGeneratorSpi extends KeyPairGenerator {
    GOST3410KeyPairGenerator engine = new GOST3410KeyPairGenerator();
    GOST3410ParameterSpec gost3410Params;
    boolean initialised = false;
    GOST3410KeyGenerationParameters param;
    SecureRandom random = null;
    int strength = 1024;

    public KeyPairGeneratorSpi() {
        super("GOST3410");
    }

    public void initialize(int strength2, SecureRandom random2) {
        this.strength = strength2;
        this.random = random2;
    }

    private void init(GOST3410ParameterSpec gParams, SecureRandom random2) {
        GOST3410PublicKeyParameterSetSpec spec = gParams.getPublicKeyParameters();
        this.param = new GOST3410KeyGenerationParameters(random2, new GOST3410Parameters(spec.getP(), spec.getQ(), spec.getA()));
        this.engine.init(this.param);
        this.initialised = true;
        this.gost3410Params = gParams;
    }

    public void initialize(AlgorithmParameterSpec params, SecureRandom random2) throws InvalidAlgorithmParameterException {
        if (!(params instanceof GOST3410ParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a GOST3410ParameterSpec");
        }
        init((GOST3410ParameterSpec) params, random2);
    }

    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            init(new GOST3410ParameterSpec(CryptoProObjectIdentifiers.gostR3410_94_CryptoPro_A.getId()), new SecureRandom());
        }
        AsymmetricCipherKeyPair pair = this.engine.generateKeyPair();
        return new KeyPair(new BCGOST3410PublicKey((GOST3410PublicKeyParameters) pair.getPublic(), this.gost3410Params), new BCGOST3410PrivateKey((GOST3410PrivateKeyParameters) pair.getPrivate(), this.gost3410Params));
    }
}
