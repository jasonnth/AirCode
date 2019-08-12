package org.spongycastle.crypto.agreement;

import java.math.BigInteger;
import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.MQVPrivateParameters;
import org.spongycastle.crypto.params.MQVPublicParameters;
import org.spongycastle.math.p332ec.ECAlgorithms;
import org.spongycastle.math.p332ec.ECConstants;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.util.Properties;

public class ECMQVBasicAgreement implements BasicAgreement {
    MQVPrivateParameters privParams;

    public void init(CipherParameters key) {
        this.privParams = (MQVPrivateParameters) key;
    }

    public int getFieldSize() {
        return (this.privParams.getStaticPrivateKey().getParameters().getCurve().getFieldSize() + 7) / 8;
    }

    public BigInteger calculateAgreement(CipherParameters pubKey) {
        if (Properties.isOverrideSet("org.spongycastle.ec.disable_mqv")) {
            throw new IllegalStateException("ECMQV explicitly disabled");
        }
        MQVPublicParameters pubParams = (MQVPublicParameters) pubKey;
        ECPrivateKeyParameters staticPrivateKey = this.privParams.getStaticPrivateKey();
        ECPoint agreement = calculateMqvAgreement(staticPrivateKey.getParameters(), staticPrivateKey, this.privParams.getEphemeralPrivateKey(), this.privParams.getEphemeralPublicKey(), pubParams.getStaticPublicKey(), pubParams.getEphemeralPublicKey()).normalize();
        if (!agreement.isInfinity()) {
            return agreement.getAffineXCoord().toBigInteger();
        }
        throw new IllegalStateException("Infinity is not a valid agreement value for MQV");
    }

    private ECPoint calculateMqvAgreement(ECDomainParameters parameters, ECPrivateKeyParameters d1U, ECPrivateKeyParameters d2U, ECPublicKeyParameters Q2U, ECPublicKeyParameters Q1V, ECPublicKeyParameters Q2V) {
        ECPoint q;
        BigInteger n = parameters.getN();
        int e = (n.bitLength() + 1) / 2;
        BigInteger powE = ECConstants.ONE.shiftLeft(e);
        ECCurve curve = parameters.getCurve();
        ECPoint[] points = new ECPoint[3];
        if (Q2U == null) {
            q = parameters.getG().multiply(d2U.getD());
        } else {
            q = Q2U.getQ();
        }
        points[0] = ECAlgorithms.importPoint(curve, q);
        points[1] = ECAlgorithms.importPoint(curve, Q1V.getQ());
        points[2] = ECAlgorithms.importPoint(curve, Q2V.getQ());
        curve.normalizeAll(points);
        ECPoint q2u = points[0];
        ECPoint q1v = points[1];
        ECPoint q2v = points[2];
        BigInteger s = d1U.getD().multiply(q2u.getAffineXCoord().toBigInteger().mod(powE).setBit(e)).add(d2U.getD()).mod(n);
        BigInteger Q2VBar = q2v.getAffineXCoord().toBigInteger().mod(powE).setBit(e);
        BigInteger hs = parameters.getH().multiply(s).mod(n);
        return ECAlgorithms.sumOfTwoMultiplies(q1v, Q2VBar.multiply(hs).mod(n), q2v, hs);
    }
}
