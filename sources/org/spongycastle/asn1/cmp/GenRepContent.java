package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class GenRepContent extends ASN1Object {
    private ASN1Sequence content;

    private GenRepContent(ASN1Sequence seq) {
        this.content = seq;
    }

    public static GenRepContent getInstance(Object o) {
        if (o instanceof GenRepContent) {
            return (GenRepContent) o;
        }
        if (o != null) {
            return new GenRepContent(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public GenRepContent(InfoTypeAndValue itv) {
        this.content = new DERSequence((ASN1Encodable) itv);
    }

    public GenRepContent(InfoTypeAndValue[] itv) {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for (InfoTypeAndValue add : itv) {
            v.add(add);
        }
        this.content = new DERSequence(v);
    }

    public InfoTypeAndValue[] toInfoTypeAndValueArray() {
        InfoTypeAndValue[] result = new InfoTypeAndValue[this.content.size()];
        for (int i = 0; i != result.length; i++) {
            result[i] = InfoTypeAndValue.getInstance(this.content.getObjectAt(i));
        }
        return result;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }
}
