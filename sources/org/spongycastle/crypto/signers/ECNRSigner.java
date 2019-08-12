package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.generators.ECKeyPairGenerator;
import org.spongycastle.crypto.params.ECKeyGenerationParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.math.p332ec.ECAlgorithms;
import org.spongycastle.math.p332ec.ECConstants;
import org.spongycastle.math.p332ec.ECPoint;

public class ECNRSigner implements DSA {
    private boolean forSigning;
    private ECKeyParameters key;
    private SecureRandom random;

    public void init(boolean forSigning2, CipherParameters param) {
        this.forSigning = forSigning2;
        if (!forSigning2) {
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

    public BigInteger[] generateSignature(byte[] digest) {
        AsymmetricCipherKeyPair tempPair;
        BigInteger r;
        if (!this.forSigning) {
            throw new IllegalStateException("not initialised for signing");
        }
        BigInteger n = ((ECPrivateKeyParameters) this.key).getParameters().getN();
        int nBitLength = n.bitLength();
        BigInteger e = new BigInteger(1, digest);
        ECPrivateKeyParameters privKey = (ECPrivateKeyParameters) this.key;
        if (e.bitLength() > nBitLength) {
            throw new DataLengthException("input too large for ECNR key.");
        }
        do {
            ECKeyPairGenerator keyGen = new ECKeyPairGenerator();
            keyGen.init(new ECKeyGenerationParameters(privKey.getParameters(), this.random));
            tempPair = keyGen.generateKeyPair();
            r = ((ECPublicKeyParameters) tempPair.getPublic()).getQ().getAffineXCoord().toBigInteger().add(e).mod(n);
        } while (r.equals(ECConstants.ZERO));
        return new BigInteger[]{r, ((ECPrivateKeyParameters) tempPair.getPrivate()).getD().subtract(r.multiply(privKey.getD())).mod(n)};
    }

    public boolean verifySignature(byte[] digest, BigInteger r, BigInteger s) {
        if (this.forSigning) {
            throw new IllegalStateException("not initialised for verifying");
        }
        ECPublicKeyParameters pubKey = (ECPublicKeyParameters) this.key;
        BigInteger n = pubKey.getParameters().getN();
        int nBitLength = n.bitLength();
        BigInteger e = new BigInteger(1, digest);
        if (e.bitLength() > nBitLength) {
            throw new DataLengthException("input too large for ECNR key.");
        } else if (r.compareTo(ECConstants.ONE) < 0 || r.compareTo(n) >= 0 || s.compareTo(ECConstants.ZERO) < 0 || s.compareTo(n) >= 0) {
            return false;
        } else {
            ECPoint P = ECAlgorithms.sumOfTwoMultiplies(pubKey.getParameters().getG(), s, pubKey.getQ(), r).normalize();
            if (!P.isInfinity()) {
                return r.subtract(P.getAffineXCoord().toBigInteger()).mod(n).equals(e);
            }
            return false;
        }
    }
}
