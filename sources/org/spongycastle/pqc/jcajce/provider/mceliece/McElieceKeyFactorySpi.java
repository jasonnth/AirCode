package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactorySpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.pqc.asn1.McEliecePrivateKey;
import org.spongycastle.pqc.asn1.McEliecePublicKey;
import org.spongycastle.pqc.jcajce.spec.McEliecePrivateKeySpec;
import org.spongycastle.pqc.jcajce.spec.McEliecePublicKeySpec;

public class McElieceKeyFactorySpi extends KeyFactorySpi {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.1";

    public PublicKey generatePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof McEliecePublicKeySpec) {
            return new BCMcEliecePublicKey((McEliecePublicKeySpec) keySpec);
        }
        if (keySpec instanceof X509EncodedKeySpec) {
            try {
                try {
                    ASN1Sequence publicKey = (ASN1Sequence) SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(((X509EncodedKeySpec) keySpec).getEncoded())).parsePublicKey();
                    String aSN1ObjectIdentifier = ((ASN1ObjectIdentifier) publicKey.getObjectAt(0)).toString();
                    return new BCMcEliecePublicKey(new McEliecePublicKeySpec("1.3.6.1.4.1.8301.3.1.3.4.1", ((ASN1Integer) publicKey.getObjectAt(2)).getValue().intValue(), ((ASN1Integer) publicKey.getObjectAt(1)).getValue().intValue(), ((ASN1OctetString) publicKey.getObjectAt(3)).getOctets()));
                } catch (IOException cce) {
                    throw new InvalidKeySpecException("Unable to decode X509EncodedKeySpec: " + cce.getMessage());
                }
            } catch (IOException e) {
                throw new InvalidKeySpecException(e.toString());
            }
        } else {
            throw new InvalidKeySpecException("Unsupported key specification: " + keySpec.getClass() + ".");
        }
    }

    public PrivateKey generatePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof McEliecePrivateKeySpec) {
            return new BCMcEliecePrivateKey((McEliecePrivateKeySpec) keySpec);
        }
        if (keySpec instanceof PKCS8EncodedKeySpec) {
            try {
                try {
                    ASN1Sequence privKey = (ASN1Sequence) PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(((PKCS8EncodedKeySpec) keySpec).getEncoded())).parsePrivateKey().toASN1Primitive();
                    String aSN1ObjectIdentifier = ((ASN1ObjectIdentifier) privKey.getObjectAt(0)).toString();
                    int n = ((ASN1Integer) privKey.getObjectAt(1)).getValue().intValue();
                    int k = ((ASN1Integer) privKey.getObjectAt(2)).getValue().intValue();
                    byte[] encFieldPoly = ((ASN1OctetString) privKey.getObjectAt(3)).getOctets();
                    byte[] encGoppaPoly = ((ASN1OctetString) privKey.getObjectAt(4)).getOctets();
                    byte[] encSInv = ((ASN1OctetString) privKey.getObjectAt(5)).getOctets();
                    byte[] encP1 = ((ASN1OctetString) privKey.getObjectAt(6)).getOctets();
                    byte[] encP2 = ((ASN1OctetString) privKey.getObjectAt(7)).getOctets();
                    byte[] encH = ((ASN1OctetString) privKey.getObjectAt(8)).getOctets();
                    ASN1Sequence qSeq = (ASN1Sequence) privKey.getObjectAt(9);
                    byte[][] encQInv = new byte[qSeq.size()][];
                    for (int i = 0; i < qSeq.size(); i++) {
                        encQInv[i] = ((ASN1OctetString) qSeq.getObjectAt(i)).getOctets();
                    }
                    BCMcEliecePrivateKey bCMcEliecePrivateKey = new BCMcEliecePrivateKey(new McEliecePrivateKeySpec("1.3.6.1.4.1.8301.3.1.3.4.1", n, k, encFieldPoly, encGoppaPoly, encSInv, encP1, encP2, encH, encQInv));
                    return bCMcEliecePrivateKey;
                } catch (IOException e) {
                    throw new InvalidKeySpecException("Unable to decode PKCS8EncodedKeySpec.");
                }
            } catch (IOException e2) {
                throw new InvalidKeySpecException("Unable to decode PKCS8EncodedKeySpec: " + e2);
            }
        } else {
            throw new InvalidKeySpecException("Unsupported key specification: " + keySpec.getClass() + ".");
        }
    }

    public KeySpec getKeySpec(Key key, Class keySpec) throws InvalidKeySpecException {
        if (key instanceof BCMcEliecePrivateKey) {
            if (PKCS8EncodedKeySpec.class.isAssignableFrom(keySpec)) {
                return new PKCS8EncodedKeySpec(key.getEncoded());
            }
            if (McEliecePrivateKeySpec.class.isAssignableFrom(keySpec)) {
                BCMcEliecePrivateKey privKey = (BCMcEliecePrivateKey) key;
                return new McEliecePrivateKeySpec("1.3.6.1.4.1.8301.3.1.3.4.1", privKey.getN(), privKey.getK(), privKey.getField(), privKey.getGoppaPoly(), privKey.getSInv(), privKey.getP1(), privKey.getP2(), privKey.getH(), privKey.getQInv());
            }
        } else if (!(key instanceof BCMcEliecePublicKey)) {
            throw new InvalidKeySpecException("Unsupported key type: " + key.getClass() + ".");
        } else if (X509EncodedKeySpec.class.isAssignableFrom(keySpec)) {
            return new X509EncodedKeySpec(key.getEncoded());
        } else {
            if (McEliecePublicKeySpec.class.isAssignableFrom(keySpec)) {
                BCMcEliecePublicKey pubKey = (BCMcEliecePublicKey) key;
                return new McEliecePublicKeySpec("1.3.6.1.4.1.8301.3.1.3.4.1", pubKey.getN(), pubKey.getT(), pubKey.getG());
            }
        }
        throw new InvalidKeySpecException("Unknown key specification: " + keySpec + ".");
    }

    public Key translateKey(Key key) throws InvalidKeyException {
        if ((key instanceof BCMcEliecePrivateKey) || (key instanceof BCMcEliecePublicKey)) {
            return key;
        }
        throw new InvalidKeyException("Unsupported key type.");
    }

    public PublicKey generatePublic(SubjectPublicKeyInfo pki) throws InvalidKeySpecException {
        try {
            McEliecePublicKey key = McEliecePublicKey.getInstance(pki.parsePublicKey());
            return new BCMcEliecePublicKey(key.getOID().getId(), key.getN(), key.getT(), key.getG());
        } catch (IOException e) {
            throw new InvalidKeySpecException("Unable to decode X509EncodedKeySpec");
        }
    }

    public PrivateKey generatePrivate(PrivateKeyInfo pki) throws InvalidKeySpecException {
        try {
            McEliecePrivateKey key = McEliecePrivateKey.getInstance(pki.parsePrivateKey().toASN1Primitive());
            return new BCMcEliecePrivateKey(key.getOID().getId(), key.getN(), key.getK(), key.getField(), key.getGoppaPoly(), key.getSInv(), key.getP1(), key.getP2(), key.getH(), key.getQInv());
        } catch (IOException e) {
            throw new InvalidKeySpecException("Unable to decode PKCS8EncodedKeySpec");
        }
    }

    /* access modifiers changed from: protected */
    public PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        return null;
    }

    /* access modifiers changed from: protected */
    public PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        return null;
    }

    /* access modifiers changed from: protected */
    public KeySpec engineGetKeySpec(Key key, Class tClass) throws InvalidKeySpecException {
        return null;
    }

    /* access modifiers changed from: protected */
    public Key engineTranslateKey(Key key) throws InvalidKeyException {
        return null;
    }
}
