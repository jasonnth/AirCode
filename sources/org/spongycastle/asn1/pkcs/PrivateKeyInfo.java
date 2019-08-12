package org.spongycastle.asn1.pkcs;

import java.io.IOException;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class PrivateKeyInfo extends ASN1Object {
    private AlgorithmIdentifier algId;
    private ASN1Set attributes;
    private ASN1OctetString privKey;

    public static PrivateKeyInfo getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static PrivateKeyInfo getInstance(Object obj) {
        if (obj instanceof PrivateKeyInfo) {
            return (PrivateKeyInfo) obj;
        }
        if (obj != null) {
            return new PrivateKeyInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public PrivateKeyInfo(AlgorithmIdentifier algId2, ASN1Encodable privateKey) throws IOException {
        this(algId2, privateKey, null);
    }

    public PrivateKeyInfo(AlgorithmIdentifier algId2, ASN1Encodable privateKey, ASN1Set attributes2) throws IOException {
        this.privKey = new DEROctetString(privateKey.toASN1Primitive().getEncoded(ASN1Encoding.DER));
        this.algId = algId2;
        this.attributes = attributes2;
    }

    public PrivateKeyInfo(ASN1Sequence seq) {
        Enumeration e = seq.getObjects();
        if (((ASN1Integer) e.nextElement()).getValue().intValue() != 0) {
            throw new IllegalArgumentException("wrong version for private key info");
        }
        this.algId = AlgorithmIdentifier.getInstance(e.nextElement());
        this.privKey = ASN1OctetString.getInstance(e.nextElement());
        if (e.hasMoreElements()) {
            this.attributes = ASN1Set.getInstance((ASN1TaggedObject) e.nextElement(), false);
        }
    }

    public AlgorithmIdentifier getPrivateKeyAlgorithm() {
        return this.algId;
    }

    public AlgorithmIdentifier getAlgorithmId() {
        return this.algId;
    }

    public ASN1Encodable parsePrivateKey() throws IOException {
        return ASN1Primitive.fromByteArray(this.privKey.getOctets());
    }

    public ASN1Primitive getPrivateKey() {
        try {
            return parsePrivateKey().toASN1Primitive();
        } catch (IOException e) {
            throw new IllegalStateException("unable to parse private key");
        }
    }

    public ASN1Set getAttributes() {
        return this.attributes;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(0));
        v.add(this.algId);
        v.add(this.privKey);
        if (this.attributes != null) {
            v.add(new DERTaggedObject(false, 0, this.attributes));
        }
        return new DERSequence(v);
    }
}