package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.DERSequence;

public class Attribute extends ASN1Object {
    private ASN1ObjectIdentifier attrType;
    private ASN1Set attrValues;

    public static Attribute getInstance(Object o) {
        if (o instanceof Attribute) {
            return (Attribute) o;
        }
        if (o != null) {
            return new Attribute(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    private Attribute(ASN1Sequence seq) {
        if (seq.size() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + seq.size());
        }
        this.attrType = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
        this.attrValues = ASN1Set.getInstance(seq.getObjectAt(1));
    }

    public Attribute(ASN1ObjectIdentifier attrType2, ASN1Set attrValues2) {
        this.attrType = attrType2;
        this.attrValues = attrValues2;
    }

    public ASN1ObjectIdentifier getAttrType() {
        return new ASN1ObjectIdentifier(this.attrType.getId());
    }

    public ASN1Encodable[] getAttributeValues() {
        return this.attrValues.toArray();
    }

    public ASN1Set getAttrValues() {
        return this.attrValues;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.attrType);
        v.add(this.attrValues);
        return new DERSequence(v);
    }
}
