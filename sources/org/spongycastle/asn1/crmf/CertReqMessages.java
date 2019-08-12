package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class CertReqMessages extends ASN1Object {
    private ASN1Sequence content;

    private CertReqMessages(ASN1Sequence seq) {
        this.content = seq;
    }

    public static CertReqMessages getInstance(Object o) {
        if (o instanceof CertReqMessages) {
            return (CertReqMessages) o;
        }
        if (o != null) {
            return new CertReqMessages(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public CertReqMessages(CertReqMsg msg) {
        this.content = new DERSequence((ASN1Encodable) msg);
    }

    public CertReqMessages(CertReqMsg[] msgs) {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for (CertReqMsg add : msgs) {
            v.add(add);
        }
        this.content = new DERSequence(v);
    }

    public CertReqMsg[] toCertReqMsgArray() {
        CertReqMsg[] result = new CertReqMsg[this.content.size()];
        for (int i = 0; i != result.length; i++) {
            result[i] = CertReqMsg.getInstance(this.content.getObjectAt(i));
        }
        return result;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }
}
