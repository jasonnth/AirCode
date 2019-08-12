package org.spongycastle.jcajce.provider.asymmetric.util;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.anssi.ANSSINamedCurves;
import org.spongycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.spongycastle.asn1.nist.NISTNamedCurves;
import org.spongycastle.asn1.p325x9.ECNamedCurveTable;
import org.spongycastle.asn1.p325x9.X962NamedCurves;
import org.spongycastle.asn1.p325x9.X9ECParameters;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.sec.SECNamedCurves;
import org.spongycastle.asn1.teletrust.TeleTrusTNamedCurves;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.p326ec.CustomNamedCurves;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.p330ec.BCECPublicKey;
import org.spongycastle.jce.interfaces.ECPrivateKey;
import org.spongycastle.jce.interfaces.ECPublicKey;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECParameterSpec;

public class ECUtil {
    static int[] convertMidTerms(int[] k) {
        int[] res = new int[3];
        if (k.length == 1) {
            res[0] = k[0];
        } else if (k.length != 3) {
            throw new IllegalArgumentException("Only Trinomials and pentanomials supported");
        } else if (k[0] < k[1] && k[0] < k[2]) {
            res[0] = k[0];
            if (k[1] < k[2]) {
                res[1] = k[1];
                res[2] = k[2];
            } else {
                res[1] = k[2];
                res[2] = k[1];
            }
        } else if (k[1] < k[2]) {
            res[0] = k[1];
            if (k[0] < k[2]) {
                res[1] = k[0];
                res[2] = k[2];
            } else {
                res[1] = k[2];
                res[2] = k[0];
            }
        } else {
            res[0] = k[2];
            if (k[0] < k[1]) {
                res[1] = k[0];
                res[2] = k[1];
            } else {
                res[1] = k[1];
                res[2] = k[0];
            }
        }
        return res;
    }

    public static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey key) throws InvalidKeyException {
        if (key instanceof ECPublicKey) {
            ECPublicKey k = (ECPublicKey) key;
            ECParameterSpec s = k.getParameters();
            if (s != null) {
                return new ECPublicKeyParameters(k.getQ(), new ECDomainParameters(s.getCurve(), s.getG(), s.getN(), s.getH(), s.getSeed()));
            }
            ECParameterSpec s2 = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
            return new ECPublicKeyParameters(((BCECPublicKey) k).engineGetQ(), new ECDomainParameters(s2.getCurve(), s2.getG(), s2.getN(), s2.getH(), s2.getSeed()));
        } else if (key instanceof java.security.interfaces.ECPublicKey) {
            java.security.interfaces.ECPublicKey pubKey = (java.security.interfaces.ECPublicKey) key;
            ECParameterSpec s3 = EC5Util.convertSpec(pubKey.getParams(), false);
            return new ECPublicKeyParameters(EC5Util.convertPoint(pubKey.getParams(), pubKey.getW(), false), new ECDomainParameters(s3.getCurve(), s3.getG(), s3.getN(), s3.getH(), s3.getSeed()));
        } else {
            try {
                byte[] bytes = key.getEncoded();
                if (bytes == null) {
                    throw new InvalidKeyException("no encoding for EC public key");
                }
                PublicKey publicKey = BouncyCastleProvider.getPublicKey(SubjectPublicKeyInfo.getInstance(bytes));
                if (publicKey instanceof java.security.interfaces.ECPublicKey) {
                    return generatePublicKeyParameter(publicKey);
                }
                throw new InvalidKeyException("cannot identify EC public key.");
            } catch (Exception e) {
                throw new InvalidKeyException("cannot identify EC public key: " + e.toString());
            }
        }
    }

    public static AsymmetricKeyParameter generatePrivateKeyParameter(PrivateKey key) throws InvalidKeyException {
        if (key instanceof ECPrivateKey) {
            ECPrivateKey k = (ECPrivateKey) key;
            ECParameterSpec s = k.getParameters();
            if (s == null) {
                s = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
            }
            return new ECPrivateKeyParameters(k.getD(), new ECDomainParameters(s.getCurve(), s.getG(), s.getN(), s.getH(), s.getSeed()));
        } else if (key instanceof java.security.interfaces.ECPrivateKey) {
            java.security.interfaces.ECPrivateKey privKey = (java.security.interfaces.ECPrivateKey) key;
            ECParameterSpec s2 = EC5Util.convertSpec(privKey.getParams(), false);
            return new ECPrivateKeyParameters(privKey.getS(), new ECDomainParameters(s2.getCurve(), s2.getG(), s2.getN(), s2.getH(), s2.getSeed()));
        } else {
            try {
                byte[] bytes = key.getEncoded();
                if (bytes == null) {
                    throw new InvalidKeyException("no encoding for EC private key");
                }
                PrivateKey privateKey = BouncyCastleProvider.getPrivateKey(PrivateKeyInfo.getInstance(bytes));
                if (privateKey instanceof java.security.interfaces.ECPrivateKey) {
                    return generatePrivateKeyParameter(privateKey);
                }
                throw new InvalidKeyException("can't identify EC private key.");
            } catch (Exception e) {
                throw new InvalidKeyException("cannot identify EC private key: " + e.toString());
            }
        }
    }

    public static int getOrderBitLength(BigInteger order, BigInteger privateValue) {
        if (order != null) {
            return order.bitLength();
        }
        ECParameterSpec implicitCA = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
        if (implicitCA == null) {
            return privateValue.bitLength();
        }
        return implicitCA.getN().bitLength();
    }

    public static ASN1ObjectIdentifier getNamedCurveOid(String curveName) {
        String name;
        if (curveName.indexOf(32) > 0) {
            name = curveName.substring(curveName.indexOf(32) + 1);
        } else {
            name = curveName;
        }
        try {
            if (name.charAt(0) < '0' || name.charAt(0) > '2') {
                return lookupOidByName(name);
            }
            return new ASN1ObjectIdentifier(name);
        } catch (IllegalArgumentException e) {
            return lookupOidByName(name);
        }
    }

    private static ASN1ObjectIdentifier lookupOidByName(String name) {
        ASN1ObjectIdentifier oid = X962NamedCurves.getOID(name);
        if (oid != null) {
            return oid;
        }
        ASN1ObjectIdentifier oid2 = SECNamedCurves.getOID(name);
        if (oid2 == null) {
            oid2 = NISTNamedCurves.getOID(name);
        }
        if (oid2 == null) {
            oid2 = TeleTrusTNamedCurves.getOID(name);
        }
        if (oid2 == null) {
            oid2 = ECGOST3410NamedCurves.getOID(name);
        }
        if (oid2 == null) {
            return ANSSINamedCurves.getOID(name);
        }
        return oid2;
    }

    public static ASN1ObjectIdentifier getNamedCurveOid(ECParameterSpec ecParameterSpec) {
        Enumeration names = ECNamedCurveTable.getNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            X9ECParameters params = ECNamedCurveTable.getByName(name);
            if (params.getN().equals(ecParameterSpec.getN()) && params.getH().equals(ecParameterSpec.getH()) && params.getCurve().equals(ecParameterSpec.getCurve()) && params.getG().equals(ecParameterSpec.getG())) {
                return ECNamedCurveTable.getOID(name);
            }
        }
        return null;
    }

    public static X9ECParameters getNamedCurveByOid(ASN1ObjectIdentifier oid) {
        X9ECParameters params = CustomNamedCurves.getByOID(oid);
        if (params != null) {
            return params;
        }
        X9ECParameters params2 = X962NamedCurves.getByOID(oid);
        if (params2 == null) {
            params2 = SECNamedCurves.getByOID(oid);
        }
        if (params2 == null) {
            params2 = NISTNamedCurves.getByOID(oid);
        }
        if (params2 == null) {
            return TeleTrusTNamedCurves.getByOID(oid);
        }
        return params2;
    }

    public static X9ECParameters getNamedCurveByName(String curveName) {
        X9ECParameters params = CustomNamedCurves.getByName(curveName);
        if (params != null) {
            return params;
        }
        X9ECParameters params2 = X962NamedCurves.getByName(curveName);
        if (params2 == null) {
            params2 = SECNamedCurves.getByName(curveName);
        }
        if (params2 == null) {
            params2 = NISTNamedCurves.getByName(curveName);
        }
        if (params2 == null) {
            return TeleTrusTNamedCurves.getByName(curveName);
        }
        return params2;
    }

    public static String getCurveName(ASN1ObjectIdentifier oid) {
        String name = X962NamedCurves.getName(oid);
        if (name != null) {
            return name;
        }
        String name2 = SECNamedCurves.getName(oid);
        if (name2 == null) {
            name2 = NISTNamedCurves.getName(oid);
        }
        if (name2 == null) {
            name2 = TeleTrusTNamedCurves.getName(oid);
        }
        if (name2 == null) {
            return ECGOST3410NamedCurves.getName(oid);
        }
        return name2;
    }
}
