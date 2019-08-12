package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class OtherKeyAttribute extends ASN1Object {
    private ASN1Encodable keyAttr;
    private ASN1ObjectIdentifier keyAttrId;

    public static OtherKeyAttribute getInstance(Object o) {
        if (o instanceof OtherKeyAttribute) {
            return (OtherKeyAttribute) o;
        }
        if (o != null) {
            return new OtherKeyAttribute(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public OtherKeyAttribute(ASN1Sequence seq) {
        this.keyAttrId = (ASN1ObjectIdentifier) seq.getObjectAt(0);
        this.keyAttr = seq.getObjectAt(1);
    }

    public OtherKeyAttribute(ASN1ObjectIdentifier keyAttrId2, ASN1Encodable keyAttr2) {
        this.keyAttrId = keyAttrId2;
        this.keyAttr = keyAttr2;
    }

    public ASN1ObjectIdentifier getKeyAttrId() {
        return this.keyAttrId;
    }

    public ASN1Encodable getKeyAttr() {
        return this.keyAttr;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.keyAttrId);
        v.add(this.keyAttr);
        return new DERSequence(v);
    }
}
