package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class KeyAgreeRecipientInfo extends ASN1Object {
    private AlgorithmIdentifier keyEncryptionAlgorithm;
    private OriginatorIdentifierOrKey originator;
    private ASN1Sequence recipientEncryptedKeys;
    private ASN1OctetString ukm;
    private ASN1Integer version;

    public KeyAgreeRecipientInfo(OriginatorIdentifierOrKey originator2, ASN1OctetString ukm2, AlgorithmIdentifier keyEncryptionAlgorithm2, ASN1Sequence recipientEncryptedKeys2) {
        this.version = new ASN1Integer(3);
        this.originator = originator2;
        this.ukm = ukm2;
        this.keyEncryptionAlgorithm = keyEncryptionAlgorithm2;
        this.recipientEncryptedKeys = recipientEncryptedKeys2;
    }

    public KeyAgreeRecipientInfo(ASN1Sequence seq) {
        int index = 0 + 1;
        this.version = (ASN1Integer) seq.getObjectAt(0);
        int index2 = index + 1;
        this.originator = OriginatorIdentifierOrKey.getInstance((ASN1TaggedObject) seq.getObjectAt(index), true);
        if (seq.getObjectAt(index2) instanceof ASN1TaggedObject) {
            int index3 = index2 + 1;
            this.ukm = ASN1OctetString.getInstance((ASN1TaggedObject) seq.getObjectAt(index2), true);
            index2 = index3;
        }
        int index4 = index2 + 1;
        this.keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(index2));
        int i = index4 + 1;
        this.recipientEncryptedKeys = (ASN1Sequence) seq.getObjectAt(index4);
    }

    public static KeyAgreeRecipientInfo getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static KeyAgreeRecipientInfo getInstance(Object obj) {
        if (obj instanceof KeyAgreeRecipientInfo) {
            return (KeyAgreeRecipientInfo) obj;
        }
        if (obj != null) {
            return new KeyAgreeRecipientInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public OriginatorIdentifierOrKey getOriginator() {
        return this.originator;
    }

    public ASN1OctetString getUserKeyingMaterial() {
        return this.ukm;
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.keyEncryptionAlgorithm;
    }

    public ASN1Sequence getRecipientEncryptedKeys() {
        return this.recipientEncryptedKeys;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.version);
        v.add(new DERTaggedObject(true, 0, this.originator));
        if (this.ukm != null) {
            v.add(new DERTaggedObject(true, 1, this.ukm));
        }
        v.add(this.keyEncryptionAlgorithm);
        v.add(this.recipientEncryptedKeys);
        return new DERSequence(v);
    }
}
