package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.math.p332ec.ECAlgorithms;
import org.spongycastle.math.p332ec.ECConstants;
import org.spongycastle.math.p332ec.ECMultiplier;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.p332ec.FixedPointCombMultiplier;

public class ECGOST3410Signer implements DSA {
    ECKeyParameters key;
    SecureRandom random;

    public void init(boolean forSigning, CipherParameters param) {
        if (!forSigning) {
            this.key = (ECPublicKeyParameters) param;
        } else if (param instanceof ParametersWithRandom) {
            ParametersWithRandom rParam = (ParametersWithRandom) param;
            this.random = rParam.getRandom();
            this.key = (ECPrivateKeyParameters) rParam.getParameters();
        } else {
            this.random = new SecureRandom();
            this.key = (ECPrivateKeyParameters) param;
        }
    }

    public BigInteger[] generateSignature(byte[] message) {
        byte[] mRev = new byte[message.length];
        for (int i = 0; i != mRev.length; i++) {
            mRev[i] = message[(mRev.length - 1) - i];
        }
        BigInteger e = new BigInteger(1, mRev);
        ECDomainParameters ec = this.key.getParameters();
        BigInteger n = ec.getN();
        BigInteger d = ((ECPrivateKeyParameters) this.key).getD();
        ECMultiplier basePointMultiplier = createBasePointMultiplier();
        while (true) {
            BigInteger k = new BigInteger(n.bitLength(), this.random);
            if (!k.equals(ECConstants.ZERO)) {
                BigInteger r = basePointMultiplier.multiply(ec.getG(), k).normalize().getAffineXCoord().toBigInteger().mod(n);
                if (!r.equals(ECConstants.ZERO)) {
                    BigInteger s = k.multiply(e).add(d.multiply(r)).mod(n);
                    if (!s.equals(ECConstants.ZERO)) {
                        return new BigInteger[]{r, s};
                    }
                } else {
                    continue;
                }
            }
        }
    }

    public boolean verifySignature(byte[] message, BigInteger r, BigInteger s) {
        byte[] mRev = new byte[message.length];
        for (int i = 0; i != mRev.length; i++) {
            mRev[i] = message[(mRev.length - 1) - i];
        }
        BigInteger e = new BigInteger(1, mRev);
        BigInteger n = this.key.getParameters().getN();
        if (r.compareTo(ECConstants.ONE) < 0 || r.compareTo(n) >= 0) {
            return false;
        }
        if (s.compareTo(ECConstants.ONE) < 0 || s.compareTo(n) >= 0) {
            return false;
        }
        BigInteger v = e.modInverse(n);
        ECPoint point = ECAlgorithms.sumOfTwoMultiplies(this.key.getParameters().getG(), s.multiply(v).mod(n), ((ECPublicKeyParameters) this.key).getQ(), n.subtract(r).multiply(v).mod(n)).normalize();
        if (point.isInfinity()) {
            return false;
        }
        return point.getAffineXCoord().toBigInteger().mod(n).equals(r);
    }

    /* access modifiers changed from: protected */
    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }
}
