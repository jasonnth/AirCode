package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class AttributeTypeAndValue extends ASN1Object {
    private ASN1ObjectIdentifier type;
    private ASN1Encodable value;

    private AttributeTypeAndValue(ASN1Sequence seq) {
        this.type = (ASN1ObjectIdentifier) seq.getObjectAt(0);
        this.value = seq.getObjectAt(1);
    }

    public static AttributeTypeAndValue getInstance(Object o) {
        if (o instanceof AttributeTypeAndValue) {
            return (AttributeTypeAndValue) o;
        }
        if (o != null) {
            return new AttributeTypeAndValue(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public AttributeTypeAndValue(String oid, ASN1Encodable value2) {
        this(new ASN1ObjectIdentifier(oid), value2);
    }

    public AttributeTypeAndValue(ASN1ObjectIdentifier type2, ASN1Encodable value2) {
        this.type = type2;
        this.value = value2;
    }

    public ASN1ObjectIdentifier getType() {
        return this.type;
    }

    public ASN1Encodable getValue() {
        return this.value;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.type);
        v.add(this.value);
        return new DERSequence(v);
    }
}
