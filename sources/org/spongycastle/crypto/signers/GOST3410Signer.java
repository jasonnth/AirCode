package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.GOST3410KeyParameters;
import org.spongycastle.crypto.params.GOST3410Parameters;
import org.spongycastle.crypto.params.GOST3410PrivateKeyParameters;
import org.spongycastle.crypto.params.GOST3410PublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;

public class GOST3410Signer implements DSA {
    GOST3410KeyParameters key;
    SecureRandom random;

    public void init(boolean forSigning, CipherParameters param) {
        if (!forSigning) {
            this.key = (GOST3410PublicKeyParameters) param;
        } else if (param instanceof ParametersWithRandom) {
            ParametersWithRandom rParam = (ParametersWithRandom) param;
            this.random = rParam.getRandom();
            this.key = (GOST3410PrivateKeyParameters) rParam.getParameters();
        } else {
            this.random = new SecureRandom();
            this.key = (GOST3410PrivateKeyParameters) param;
        }
    }

    public BigInteger[] generateSignature(byte[] message) {
        BigInteger k;
        byte[] mRev = new byte[message.length];
        for (int i = 0; i != mRev.length; i++) {
            mRev[i] = message[(mRev.length - 1) - i];
        }
        BigInteger m = new BigInteger(1, mRev);
        GOST3410Parameters params = this.key.getParameters();
        do {
            k = new BigInteger(params.getQ().bitLength(), this.random);
        } while (k.compareTo(params.getQ()) >= 0);
        BigInteger r = params.getA().modPow(k, params.getP()).mod(params.getQ());
        return new BigInteger[]{r, k.multiply(m).add(((GOST3410PrivateKeyParameters) this.key).getX().multiply(r)).mod(params.getQ())};
    }

    public boolean verifySignature(byte[] message, BigInteger r, BigInteger s) {
        byte[] mRev = new byte[message.length];
        for (int i = 0; i != mRev.length; i++) {
            mRev[i] = message[(mRev.length - 1) - i];
        }
        BigInteger m = new BigInteger(1, mRev);
        GOST3410Parameters params = this.key.getParameters();
        BigInteger zero = BigInteger.valueOf(0);
        if (zero.compareTo(r) >= 0 || params.getQ().compareTo(r) <= 0 || zero.compareTo(s) >= 0 || params.getQ().compareTo(s) <= 0) {
            return false;
        }
        BigInteger v = m.modPow(params.getQ().subtract(new BigInteger("2")), params.getQ());
        return params.getA().modPow(s.multiply(v).mod(params.getQ()), params.getP()).multiply(((GOST3410PublicKeyParameters) this.key).getY().modPow(params.getQ().subtract(r).multiply(v).mod(params.getQ()), params.getP())).mod(params.getP()).mod(params.getQ()).equals(r);
    }
}
