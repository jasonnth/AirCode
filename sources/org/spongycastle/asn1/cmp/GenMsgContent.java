package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class GenMsgContent extends ASN1Object {
    private ASN1Sequence content;

    private GenMsgContent(ASN1Sequence seq) {
        this.content = seq;
    }

    public static GenMsgContent getInstance(Object o) {
        if (o instanceof GenMsgContent) {
            return (GenMsgContent) o;
        }
        if (o != null) {
            return new GenMsgContent(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public GenMsgContent(InfoTypeAndValue itv) {
        this.content = new DERSequence((ASN1Encodable) itv);
    }

    public GenMsgContent(InfoTypeAndValue[] itv) {
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
