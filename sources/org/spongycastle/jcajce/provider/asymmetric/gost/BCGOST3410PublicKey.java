package org.spongycastle.jcajce.provider.asymmetric.gost;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.params.GOST3410PublicKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.spongycastle.jce.interfaces.GOST3410Params;
import org.spongycastle.jce.interfaces.GOST3410PublicKey;
import org.spongycastle.jce.spec.GOST3410ParameterSpec;
import org.spongycastle.jce.spec.GOST3410PublicKeyParameterSetSpec;
import org.spongycastle.jce.spec.GOST3410PublicKeySpec;
import org.spongycastle.util.Strings;

public class BCGOST3410PublicKey implements GOST3410PublicKey {
    static final long serialVersionUID = -6251023343619275990L;
    private transient GOST3410Params gost3410Spec;

    /* renamed from: y */
    private BigInteger f6912y;

    BCGOST3410PublicKey(GOST3410PublicKeySpec spec) {
        this.f6912y = spec.getY();
        this.gost3410Spec = new GOST3410ParameterSpec(new GOST3410PublicKeyParameterSetSpec(spec.getP(), spec.getQ(), spec.getA()));
    }

    BCGOST3410PublicKey(GOST3410PublicKey key) {
        this.f6912y = key.getY();
        this.gost3410Spec = key.getParameters();
    }

    BCGOST3410PublicKey(GOST3410PublicKeyParameters params, GOST3410ParameterSpec spec) {
        this.f6912y = params.getY();
        this.gost3410Spec = spec;
    }

    BCGOST3410PublicKey(BigInteger y, GOST3410ParameterSpec gost3410Spec2) {
        this.f6912y = y;
        this.gost3410Spec = gost3410Spec2;
    }

    BCGOST3410PublicKey(SubjectPublicKeyInfo info) {
        GOST3410PublicKeyAlgParameters params = new GOST3410PublicKeyAlgParameters((ASN1Sequence) info.getAlgorithmId().getParameters());
        try {
            byte[] keyEnc = ((DEROctetString) info.parsePublicKey()).getOctets();
            byte[] keyBytes = new byte[keyEnc.length];
            for (int i = 0; i != keyEnc.length; i++) {
                keyBytes[i] = keyEnc[(keyEnc.length - 1) - i];
            }
            this.f6912y = new BigInteger(1, keyBytes);
            this.gost3410Spec = GOST3410ParameterSpec.fromPublicKeyAlg(params);
        } catch (IOException e) {
            throw new IllegalArgumentException("invalid info structure in GOST3410 public key");
        }
    }

    public String getAlgorithm() {
        return "GOST3410";
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        byte[] keyBytes;
        SubjectPublicKeyInfo info;
        byte[] keyEnc = getY().toByteArray();
        if (keyEnc[0] == 0) {
            keyBytes = new byte[(keyEnc.length - 1)];
        } else {
            keyBytes = new byte[keyEnc.length];
        }
        for (int i = 0; i != keyBytes.length; i++) {
            keyBytes[i] = keyEnc[(keyEnc.length - 1) - i];
        }
        try {
            if (!(this.gost3410Spec instanceof GOST3410ParameterSpec)) {
                info = new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_94), (ASN1Encodable) new DEROctetString(keyBytes));
            } else if (this.gost3410Spec.getEncryptionParamSetOID() != null) {
                info = new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_94, new GOST3410PublicKeyAlgParameters(new ASN1ObjectIdentifier(this.gost3410Spec.getPublicKeyParamSetOID()), new ASN1ObjectIdentifier(this.gost3410Spec.getDigestParamSetOID()), new ASN1ObjectIdentifier(this.gost3410Spec.getEncryptionParamSetOID()))), (ASN1Encodable) new DEROctetString(keyBytes));
            } else {
                info = new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_94, new GOST3410PublicKeyAlgParameters(new ASN1ObjectIdentifier(this.gost3410Spec.getPublicKeyParamSetOID()), new ASN1ObjectIdentifier(this.gost3410Spec.getDigestParamSetOID()))), (ASN1Encodable) new DEROctetString(keyBytes));
            }
            return KeyUtil.getEncodedSubjectPublicKeyInfo(info);
        } catch (IOException e) {
            return null;
        }
    }

    public GOST3410Params getParameters() {
        return this.gost3410Spec;
    }

    public BigInteger getY() {
        return this.f6912y;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        String nl = Strings.lineSeparator();
        buf.append("GOST3410 Public Key").append(nl);
        buf.append("            y: ").append(getY().toString(16)).append(nl);
        return buf.toString();
    }

    public boolean equals(Object o) {
        if (!(o instanceof BCGOST3410PublicKey)) {
            return false;
        }
        BCGOST3410PublicKey other = (BCGOST3410PublicKey) o;
        if (!this.f6912y.equals(other.f6912y) || !this.gost3410Spec.equals(other.gost3410Spec)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.f6912y.hashCode() ^ this.gost3410Spec.hashCode();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        String publicKeyParamSetOID = (String) in.readObject();
        if (publicKeyParamSetOID != null) {
            this.gost3410Spec = new GOST3410ParameterSpec(publicKeyParamSetOID, (String) in.readObject(), (String) in.readObject());
            return;
        }
        this.gost3410Spec = new GOST3410ParameterSpec(new GOST3410PublicKeyParameterSetSpec((BigInteger) in.readObject(), (BigInteger) in.readObject(), (BigInteger) in.readObject()));
        in.readObject();
        in.readObject();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if (this.gost3410Spec.getPublicKeyParamSetOID() != null) {
            out.writeObject(this.gost3410Spec.getPublicKeyParamSetOID());
            out.writeObject(this.gost3410Spec.getDigestParamSetOID());
            out.writeObject(this.gost3410Spec.getEncryptionParamSetOID());
            return;
        }
        out.writeObject(null);
        out.writeObject(this.gost3410Spec.getPublicKeyParameters().getP());
        out.writeObject(this.gost3410Spec.getPublicKeyParameters().getQ());
        out.writeObject(this.gost3410Spec.getPublicKeyParameters().getA());
        out.writeObject(this.gost3410Spec.getDigestParamSetOID());
        out.writeObject(this.gost3410Spec.getEncryptionParamSetOID());
    }
}
