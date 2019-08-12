package org.spongycastle.crypto.p326ec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.math.p332ec.ECMultiplier;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.p332ec.FixedPointCombMultiplier;

/* renamed from: org.spongycastle.crypto.ec.ECElGamalEncryptor */
public class ECElGamalEncryptor implements ECEncryptor {
    private ECPublicKeyParameters key;
    private SecureRandom random;

    public void init(CipherParameters param) {
        if (param instanceof ParametersWithRandom) {
            ParametersWithRandom p = (ParametersWithRandom) param;
            if (!(p.getParameters() instanceof ECPublicKeyParameters)) {
                throw new IllegalArgumentException("ECPublicKeyParameters are required for encryption.");
            }
            this.key = (ECPublicKeyParameters) p.getParameters();
            this.random = p.getRandom();
        } else if (!(param instanceof ECPublicKeyParameters)) {
            throw new IllegalArgumentException("ECPublicKeyParameters are required for encryption.");
        } else {
            this.key = (ECPublicKeyParameters) param;
            this.random = new SecureRandom();
        }
    }

    public ECPair encrypt(ECPoint point) {
        if (this.key == null) {
            throw new IllegalStateException("ECElGamalEncryptor not initialised");
        }
        ECDomainParameters ec = this.key.getParameters();
        BigInteger k = ECUtil.generateK(ec.getN(), this.random);
        ECPoint[] gamma_phi = {createBasePointMultiplier().multiply(ec.getG(), k), this.key.getQ().multiply(k).add(point)};
        ec.getCurve().normalizeAll(gamma_phi);
        return new ECPair(gamma_phi[0], gamma_phi[1]);
    }

    /* access modifiers changed from: protected */
    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }
}
