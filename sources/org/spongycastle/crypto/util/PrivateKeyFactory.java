package org.spongycastle.crypto.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.oiw.ElGamalParameter;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.p325x9.ECNamedCurveTable;
import org.spongycastle.asn1.p325x9.X962Parameters;
import org.spongycastle.asn1.p325x9.X9ECParameters;
import org.spongycastle.asn1.p325x9.X9ObjectIdentifiers;
import org.spongycastle.asn1.pkcs.DHParameter;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.pkcs.RSAPrivateKey;
import org.spongycastle.asn1.sec.ECPrivateKey;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DSAParameter;
import org.spongycastle.crypto.p326ec.CustomNamedCurves;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DSAParameters;
import org.spongycastle.crypto.params.DSAPrivateKeyParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECNamedDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ElGamalParameters;
import org.spongycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;

public class PrivateKeyFactory {
    public static AsymmetricKeyParameter createKey(byte[] privateKeyInfoData) throws IOException {
        return createKey(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(privateKeyInfoData)));
    }

    public static AsymmetricKeyParameter createKey(InputStream inStr) throws IOException {
        return createKey(PrivateKeyInfo.getInstance(new ASN1InputStream(inStr).readObject()));
    }

    public static AsymmetricKeyParameter createKey(PrivateKeyInfo keyInfo) throws IOException {
        ECDomainParameters dParams;
        AlgorithmIdentifier algId = keyInfo.getPrivateKeyAlgorithm();
        if (algId.getAlgorithm().equals(PKCSObjectIdentifiers.rsaEncryption)) {
            RSAPrivateKey keyStructure = RSAPrivateKey.getInstance(keyInfo.parsePrivateKey());
            return new RSAPrivateCrtKeyParameters(keyStructure.getModulus(), keyStructure.getPublicExponent(), keyStructure.getPrivateExponent(), keyStructure.getPrime1(), keyStructure.getPrime2(), keyStructure.getExponent1(), keyStructure.getExponent2(), keyStructure.getCoefficient());
        } else if (algId.getAlgorithm().equals(PKCSObjectIdentifiers.dhKeyAgreement)) {
            DHParameter params = DHParameter.getInstance(algId.getParameters());
            ASN1Integer derX = (ASN1Integer) keyInfo.parsePrivateKey();
            BigInteger lVal = params.getL();
            return new DHPrivateKeyParameters(derX.getValue(), new DHParameters(params.getP(), params.getG(), null, lVal == null ? 0 : lVal.intValue()));
        } else if (algId.getAlgorithm().equals(OIWObjectIdentifiers.elGamalAlgorithm)) {
            ElGamalParameter params2 = ElGamalParameter.getInstance(algId.getParameters());
            return new ElGamalPrivateKeyParameters(((ASN1Integer) keyInfo.parsePrivateKey()).getValue(), new ElGamalParameters(params2.getP(), params2.getG()));
        } else if (algId.getAlgorithm().equals(X9ObjectIdentifiers.id_dsa)) {
            ASN1Integer derX2 = (ASN1Integer) keyInfo.parsePrivateKey();
            ASN1Encodable de = algId.getParameters();
            DSAParameters parameters = null;
            if (de != null) {
                DSAParameter params3 = DSAParameter.getInstance(de.toASN1Primitive());
                parameters = new DSAParameters(params3.getP(), params3.getQ(), params3.getG());
            }
            return new DSAPrivateKeyParameters(derX2.getValue(), parameters);
        } else if (algId.getAlgorithm().equals(X9ObjectIdentifiers.id_ecPublicKey)) {
            X962Parameters x962Parameters = new X962Parameters((ASN1Primitive) algId.getParameters());
            if (x962Parameters.isNamedCurve()) {
                ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier) x962Parameters.getParameters();
                X9ECParameters x9 = CustomNamedCurves.getByOID(oid);
                if (x9 == null) {
                    x9 = ECNamedCurveTable.getByOID(oid);
                }
                dParams = new ECNamedDomainParameters(oid, x9.getCurve(), x9.getG(), x9.getN(), x9.getH(), x9.getSeed());
            } else {
                X9ECParameters x92 = X9ECParameters.getInstance(x962Parameters.getParameters());
                dParams = new ECDomainParameters(x92.getCurve(), x92.getG(), x92.getN(), x92.getH(), x92.getSeed());
            }
            return new ECPrivateKeyParameters(ECPrivateKey.getInstance(keyInfo.parsePrivateKey()).getKey(), dParams);
        } else {
            throw new RuntimeException("algorithm identifier in key not recognised");
        }
    }
}
