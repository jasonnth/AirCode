package org.spongycastle.crypto.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.oiw.ElGamalParameter;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.p325x9.DHPublicKey;
import org.spongycastle.asn1.p325x9.DomainParameters;
import org.spongycastle.asn1.p325x9.ECNamedCurveTable;
import org.spongycastle.asn1.p325x9.ValidationParams;
import org.spongycastle.asn1.p325x9.X962Parameters;
import org.spongycastle.asn1.p325x9.X9ECParameters;
import org.spongycastle.asn1.p325x9.X9ECPoint;
import org.spongycastle.asn1.p325x9.X9ObjectIdentifiers;
import org.spongycastle.asn1.pkcs.DHParameter;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.RSAPublicKey;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DSAParameter;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.p326ec.CustomNamedCurves;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.crypto.params.DHValidationParameters;
import org.spongycastle.crypto.params.DSAParameters;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECNamedDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ElGamalParameters;
import org.spongycastle.crypto.params.ElGamalPublicKeyParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;

public class PublicKeyFactory {
    public static AsymmetricKeyParameter createKey(byte[] keyInfoData) throws IOException {
        return createKey(SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(keyInfoData)));
    }

    public static AsymmetricKeyParameter createKey(InputStream inStr) throws IOException {
        return createKey(SubjectPublicKeyInfo.getInstance(new ASN1InputStream(inStr).readObject()));
    }

    public static AsymmetricKeyParameter createKey(SubjectPublicKeyInfo keyInfo) throws IOException {
        X9ECParameters x9;
        ECDomainParameters dParams;
        AlgorithmIdentifier algId = keyInfo.getAlgorithm();
        if (algId.getAlgorithm().equals(PKCSObjectIdentifiers.rsaEncryption) || algId.getAlgorithm().equals(X509ObjectIdentifiers.id_ea_rsa)) {
            RSAPublicKey pubKey = RSAPublicKey.getInstance(keyInfo.parsePublicKey());
            return new RSAKeyParameters(false, pubKey.getModulus(), pubKey.getPublicExponent());
        } else if (algId.getAlgorithm().equals(X9ObjectIdentifiers.dhpublicnumber)) {
            BigInteger y = DHPublicKey.getInstance(keyInfo.parsePublicKey()).getY();
            DomainParameters dhParams = DomainParameters.getInstance(algId.getParameters());
            BigInteger p = dhParams.getP();
            BigInteger g = dhParams.getG();
            BigInteger q = dhParams.getQ();
            BigInteger j = null;
            if (dhParams.getJ() != null) {
                j = dhParams.getJ();
            }
            DHValidationParameters validation = null;
            ValidationParams dhValidationParms = dhParams.getValidationParams();
            if (dhValidationParms != null) {
                validation = new DHValidationParameters(dhValidationParms.getSeed(), dhValidationParms.getPgenCounter().intValue());
            }
            return new DHPublicKeyParameters(y, new DHParameters(p, g, q, j, validation));
        } else if (algId.getAlgorithm().equals(PKCSObjectIdentifiers.dhKeyAgreement)) {
            DHParameter params = DHParameter.getInstance(algId.getParameters());
            ASN1Integer derY = (ASN1Integer) keyInfo.parsePublicKey();
            BigInteger lVal = params.getL();
            DHParameters dHParameters = new DHParameters(params.getP(), params.getG(), null, lVal == null ? 0 : lVal.intValue());
            return new DHPublicKeyParameters(derY.getValue(), dHParameters);
        } else if (algId.getAlgorithm().equals(OIWObjectIdentifiers.elGamalAlgorithm)) {
            ElGamalParameter params2 = ElGamalParameter.getInstance(algId.getParameters());
            return new ElGamalPublicKeyParameters(((ASN1Integer) keyInfo.parsePublicKey()).getValue(), new ElGamalParameters(params2.getP(), params2.getG()));
        } else if (algId.getAlgorithm().equals(X9ObjectIdentifiers.id_dsa) || algId.getAlgorithm().equals(OIWObjectIdentifiers.dsaWithSHA1)) {
            ASN1Integer derY2 = (ASN1Integer) keyInfo.parsePublicKey();
            ASN1Encodable de = algId.getParameters();
            DSAParameters parameters = null;
            if (de != null) {
                DSAParameter params3 = DSAParameter.getInstance(de.toASN1Primitive());
                parameters = new DSAParameters(params3.getP(), params3.getQ(), params3.getG());
            }
            return new DSAPublicKeyParameters(derY2.getValue(), parameters);
        } else if (algId.getAlgorithm().equals(X9ObjectIdentifiers.id_ecPublicKey)) {
            X962Parameters params4 = X962Parameters.getInstance(algId.getParameters());
            if (params4.isNamedCurve()) {
                ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier) params4.getParameters();
                x9 = CustomNamedCurves.getByOID(oid);
                if (x9 == null) {
                    x9 = ECNamedCurveTable.getByOID(oid);
                }
                dParams = new ECNamedDomainParameters(oid, x9.getCurve(), x9.getG(), x9.getN(), x9.getH(), x9.getSeed());
            } else {
                x9 = X9ECParameters.getInstance(params4.getParameters());
                dParams = new ECDomainParameters(x9.getCurve(), x9.getG(), x9.getN(), x9.getH(), x9.getSeed());
            }
            DEROctetString dEROctetString = new DEROctetString(keyInfo.getPublicKeyData().getBytes());
            X9ECPoint x9ECPoint = new X9ECPoint(x9.getCurve(), (ASN1OctetString) dEROctetString);
            return new ECPublicKeyParameters(x9ECPoint.getPoint(), dParams);
        } else {
            throw new RuntimeException("algorithm identifier in key not recognised");
        }
    }
}
