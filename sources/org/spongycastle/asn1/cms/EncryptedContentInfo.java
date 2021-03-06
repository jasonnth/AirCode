package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.BERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class EncryptedContentInfo extends ASN1Object {
    private AlgorithmIdentifier contentEncryptionAlgorithm;
    private ASN1ObjectIdentifier contentType;
    private ASN1OctetString encryptedContent;

    public EncryptedContentInfo(ASN1ObjectIdentifier contentType2, AlgorithmIdentifier contentEncryptionAlgorithm2, ASN1OctetString encryptedContent2) {
        this.contentType = contentType2;
        this.contentEncryptionAlgorithm = contentEncryptionAlgorithm2;
        this.encryptedContent = encryptedContent2;
    }

    private EncryptedContentInfo(ASN1Sequence seq) {
        if (seq.size() < 2) {
            throw new IllegalArgumentException("Truncated Sequence Found");
        }
        this.contentType = (ASN1ObjectIdentifier) seq.getObjectAt(0);
        this.contentEncryptionAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(1));
        if (seq.size() > 2) {
            this.encryptedContent = ASN1OctetString.getInstance((ASN1TaggedObject) seq.getObjectAt(2), false);
        }
    }

    public static EncryptedContentInfo getInstance(Object obj) {
        if (obj instanceof EncryptedContentInfo) {
            return (EncryptedContentInfo) obj;
        }
        if (obj != null) {
            return new EncryptedContentInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.contentType;
    }

    public AlgorithmIdentifier getContentEncryptionAlgorithm() {
        return this.contentEncryptionAlgorithm;
    }

    public ASN1OctetString getEncryptedContent() {
        return this.encryptedContent;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.contentType);
        v.add(this.contentEncryptionAlgorithm);
        if (this.encryptedContent != null) {
            v.add(new BERTaggedObject(false, 0, this.encryptedContent));
        }
        return new BERSequence(v);
    }
}
