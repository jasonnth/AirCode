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
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECMultiplier;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.math.p332ec.FixedPointCombMultiplier;
import org.spongycastle.util.Arrays;

public class DSTU4145Signer implements DSA {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private ECKeyParameters key;
    private SecureRandom random;

    public void init(boolean forSigning, CipherParameters param) {
        if (forSigning) {
            if (param instanceof ParametersWithRandom) {
                ParametersWithRandom rParam = (ParametersWithRandom) param;
                this.random = rParam.getRandom();
                param = rParam.getParameters();
            } else {
                this.random = new SecureRandom();
            }
            this.key = (ECPrivateKeyParameters) param;
            return;
        }
        this.key = (ECPublicKeyParameters) param;
    }

    public BigInteger[] generateSignature(byte[] message) {
        ECDomainParameters ec = this.key.getParameters();
        ECCurve curve = ec.getCurve();
        ECFieldElement h = hash2FieldElement(curve, message);
        if (h.isZero()) {
            h = curve.fromBigInteger(ONE);
        }
        BigInteger n = ec.getN();
        BigInteger d = ((ECPrivateKeyParameters) this.key).getD();
        ECMultiplier basePointMultiplier = createBasePointMultiplier();
        while (true) {
            BigInteger e = generateRandomInteger(n, this.random);
            ECFieldElement Fe = basePointMultiplier.multiply(ec.getG(), e).normalize().getAffineXCoord();
            if (!Fe.isZero()) {
                BigInteger r = fieldElement2Integer(n, h.multiply(Fe));
                if (r.signum() != 0) {
                    BigInteger s = r.multiply(d).add(e).mod(n);
                    if (s.signum() != 0) {
                        return new BigInteger[]{r, s};
                    }
                } else {
                    continue;
                }
            }
        }
    }

    public boolean verifySignature(byte[] message, BigInteger r, BigInteger s) {
        if (r.signum() <= 0 || s.signum() <= 0) {
            return false;
        }
        ECDomainParameters parameters = this.key.getParameters();
        BigInteger n = parameters.getN();
        if (r.compareTo(n) >= 0 || s.compareTo(n) >= 0) {
            return false;
        }
        ECCurve curve = parameters.getCurve();
        ECFieldElement h = hash2FieldElement(curve, message);
        if (h.isZero()) {
            h = curve.fromBigInteger(ONE);
        }
        ECPoint R = ECAlgorithms.sumOfTwoMultiplies(parameters.getG(), s, ((ECPublicKeyParameters) this.key).getQ(), r).normalize();
        if (R.isInfinity()) {
            return false;
        }
        return fieldElement2Integer(n, h.multiply(R.getAffineXCoord())).compareTo(r) == 0;
    }

    /* access modifiers changed from: protected */
    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    private static BigInteger generateRandomInteger(BigInteger n, SecureRandom random2) {
        return new BigInteger(n.bitLength() - 1, random2);
    }

    private static ECFieldElement hash2FieldElement(ECCurve curve, byte[] hash) {
        return curve.fromBigInteger(truncate(new BigInteger(1, Arrays.reverse(hash)), curve.getFieldSize()));
    }

    private static BigInteger fieldElement2Integer(BigInteger n, ECFieldElement fe) {
        return truncate(fe.toBigInteger(), n.bitLength() - 1);
    }

    private static BigInteger truncate(BigInteger x, int bitLength) {
        if (x.bitLength() > bitLength) {
            return x.mod(ONE.shiftLeft(bitLength));
        }
        return x;
    }
}
