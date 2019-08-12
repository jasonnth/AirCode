package org.spongycastle.crypto.util;

import java.io.IOException;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Null;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.p325x9.X962Parameters;
import org.spongycastle.asn1.p325x9.X9ECParameters;
import org.spongycastle.asn1.p325x9.X9ECPoint;
import org.spongycastle.asn1.p325x9.X9ObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.RSAPublicKey;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DSAParameter;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DSAParameters;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECNamedDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;

public class SubjectPublicKeyInfoFactory {
    public static SubjectPublicKeyInfo createSubjectPublicKeyInfo(AsymmetricKeyParameter publicKey) throws IOException {
        ASN1Encodable params;
        if (publicKey instanceof RSAKeyParameters) {
            RSAKeyParameters pub = (RSAKeyParameters) publicKey;
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE), (ASN1Encodable) new RSAPublicKey(pub.getModulus(), pub.getExponent()));
        } else if (publicKey instanceof DSAPublicKeyParameters) {
            DSAPublicKeyParameters pub2 = (DSAPublicKeyParameters) publicKey;
            DSAParameter params2 = null;
            DSAParameters dsaParams = pub2.getParameters();
            if (dsaParams != null) {
                params2 = new DSAParameter(dsaParams.getP(), dsaParams.getQ(), dsaParams.getG());
            }
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, params2), (ASN1Encodable) new ASN1Integer(pub2.getY()));
        } else if (publicKey instanceof ECPublicKeyParameters) {
            ECPublicKeyParameters pub3 = (ECPublicKeyParameters) publicKey;
            ECDomainParameters domainParams = pub3.getParameters();
            if (domainParams == null) {
                params = new X962Parameters((ASN1Null) DERNull.INSTANCE);
            } else if (domainParams instanceof ECNamedDomainParameters) {
                params = new X962Parameters(((ECNamedDomainParameters) domainParams).getName());
            } else {
                params = new X962Parameters(new X9ECParameters(domainParams.getCurve(), domainParams.getG(), domainParams.getN(), domainParams.getH(), domainParams.getSeed()));
            }
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, params), ((ASN1OctetString) new X9ECPoint(pub3.getQ()).toASN1Primitive()).getOctets());
        } else {
            throw new IOException("key parameters not recognised.");
        }
    }
}
