package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.GOST3410KeyGenerationParameters;
import org.spongycastle.crypto.params.GOST3410Parameters;
import org.spongycastle.crypto.params.GOST3410PrivateKeyParameters;
import org.spongycastle.crypto.params.GOST3410PublicKeyParameters;
import org.spongycastle.math.p332ec.WNafUtil;

public class GOST3410KeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private GOST3410KeyGenerationParameters param;

    public void init(KeyGenerationParameters param2) {
        this.param = (GOST3410KeyGenerationParameters) param2;
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        GOST3410Parameters GOST3410Params = this.param.getParameters();
        SecureRandom random = this.param.getRandom();
        BigInteger q = GOST3410Params.getQ();
        BigInteger p = GOST3410Params.getP();
        BigInteger a = GOST3410Params.getA();
        while (true) {
            BigInteger x = new BigInteger(256, random);
            if (x.signum() >= 1 && x.compareTo(q) < 0 && WNafUtil.getNafWeight(x) >= 64) {
                return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new GOST3410PublicKeyParameters(a.modPow(x, p), GOST3410Params), (AsymmetricKeyParameter) new GOST3410PrivateKeyParameters(x, GOST3410Params));
            }
        }
    }
}
