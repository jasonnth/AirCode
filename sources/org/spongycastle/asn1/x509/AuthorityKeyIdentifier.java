package org.spongycastle.asn1.x509;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA1Digest;

public class AuthorityKeyIdentifier extends ASN1Object {
    GeneralNames certissuer;
    ASN1Integer certserno;
    ASN1OctetString keyidentifier;

    public static AuthorityKeyIdentifier getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static AuthorityKeyIdentifier getInstance(Object obj) {
        if (obj instanceof AuthorityKeyIdentifier) {
            return (AuthorityKeyIdentifier) obj;
        }
        if (obj != null) {
            return new AuthorityKeyIdentifier(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static AuthorityKeyIdentifier fromExtensions(Extensions extensions) {
        return getInstance(extensions.getExtensionParsedValue(Extension.authorityKeyIdentifier));
    }

    protected AuthorityKeyIdentifier(ASN1Sequence seq) {
        this.keyidentifier = null;
        this.certissuer = null;
        this.certserno = null;
        Enumeration e = seq.getObjects();
        while (e.hasMoreElements()) {
            ASN1TaggedObject o = DERTaggedObject.getInstance(e.nextElement());
            switch (o.getTagNo()) {
                case 0:
                    this.keyidentifier = ASN1OctetString.getInstance(o, false);
                    break;
                case 1:
                    this.certissuer = GeneralNames.getInstance(o, false);
                    break;
                case 2:
                    this.certserno = ASN1Integer.getInstance(o, false);
                    break;
                default:
                    throw new IllegalArgumentException("illegal tag");
            }
        }
    }

    public AuthorityKeyIdentifier(SubjectPublicKeyInfo spki) {
        this.keyidentifier = null;
        this.certissuer = null;
        this.certserno = null;
        Digest digest = new SHA1Digest();
        byte[] resBuf = new byte[digest.getDigestSize()];
        byte[] bytes = spki.getPublicKeyData().getBytes();
        digest.update(bytes, 0, bytes.length);
        digest.doFinal(resBuf, 0);
        this.keyidentifier = new DEROctetString(resBuf);
    }

    public AuthorityKeyIdentifier(SubjectPublicKeyInfo spki, GeneralNames name, BigInteger serialNumber) {
        this.keyidentifier = null;
        this.certissuer = null;
        this.certserno = null;
        Digest digest = new SHA1Digest();
        byte[] resBuf = new byte[digest.getDigestSize()];
        byte[] bytes = spki.getPublicKeyData().getBytes();
        digest.update(bytes, 0, bytes.length);
        digest.doFinal(resBuf, 0);
        this.keyidentifier = new DEROctetString(resBuf);
        this.certissuer = GeneralNames.getInstance(name.toASN1Primitive());
        this.certserno = new ASN1Integer(serialNumber);
    }

    public AuthorityKeyIdentifier(GeneralNames name, BigInteger serialNumber) {
        this((byte[]) null, name, serialNumber);
    }

    public AuthorityKeyIdentifier(byte[] keyIdentifier) {
        this(keyIdentifier, (GeneralNames) null, (BigInteger) null);
    }

    public AuthorityKeyIdentifier(byte[] keyIdentifier, GeneralNames name, BigInteger serialNumber) {
        DEROctetString dEROctetString;
        ASN1Integer aSN1Integer = null;
        this.keyidentifier = null;
        this.certissuer = null;
        this.certserno = null;
        if (keyIdentifier != null) {
            dEROctetString = new DEROctetString(keyIdentifier);
        } else {
            dEROctetString = null;
        }
        this.keyidentifier = dEROctetString;
        this.certissuer = name;
        if (serialNumber != null) {
            aSN1Integer = new ASN1Integer(serialNumber);
        }
        this.certserno = aSN1Integer;
    }

    public byte[] getKeyIdentifier() {
        if (this.keyidentifier != null) {
            return this.keyidentifier.getOctets();
        }
        return null;
    }

    public GeneralNames getAuthorityCertIssuer() {
        return this.certissuer;
    }

    public BigInteger getAuthorityCertSerialNumber() {
        if (this.certserno != null) {
            return this.certserno.getValue();
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.keyidentifier != null) {
            v.add(new DERTaggedObject(false, 0, this.keyidentifier));
        }
        if (this.certissuer != null) {
            v.add(new DERTaggedObject(false, 1, this.certissuer));
        }
        if (this.certserno != null) {
            v.add(new DERTaggedObject(false, 2, this.certserno));
        }
        return new DERSequence(v);
    }

    public String toString() {
        return "AuthorityKeyIdentifier: KeyID(" + this.keyidentifier.getOctets() + ")";
    }
}