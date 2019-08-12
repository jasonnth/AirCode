package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class InfoTypeAndValue extends ASN1Object {
    private ASN1ObjectIdentifier infoType;
    private ASN1Encodable infoValue;

    private InfoTypeAndValue(ASN1Sequence seq) {
        this.infoType = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
        if (seq.size() > 1) {
            this.infoValue = seq.getObjectAt(1);
        }
    }

    public static InfoTypeAndValue getInstance(Object o) {
        if (o instanceof InfoTypeAndValue) {
            return (InfoTypeAndValue) o;
        }
        if (o != null) {
            return new InfoTypeAndValue(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public InfoTypeAndValue(ASN1ObjectIdentifier infoType2) {
        this.infoType = infoType2;
        this.infoValue = null;
    }

    public InfoTypeAndValue(ASN1ObjectIdentifier infoType2, ASN1Encodable optionalValue) {
        this.infoType = infoType2;
        this.infoValue = optionalValue;
    }

    public ASN1ObjectIdentifier getInfoType() {
        return this.infoType;
    }

    public ASN1Encodable getInfoValue() {
        return this.infoValue;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.infoType);
        if (this.infoValue != null) {
            v.add(this.infoValue);
        }
        return new DERSequence(v);
    }
}
