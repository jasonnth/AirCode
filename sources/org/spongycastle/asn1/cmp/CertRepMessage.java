package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class CertRepMessage extends ASN1Object {
    private ASN1Sequence caPubs;
    private ASN1Sequence response;

    private CertRepMessage(ASN1Sequence seq) {
        int index = 0;
        if (seq.size() > 1) {
            int index2 = 0 + 1;
            this.caPubs = ASN1Sequence.getInstance((ASN1TaggedObject) seq.getObjectAt(0), true);
            index = index2;
        }
        this.response = ASN1Sequence.getInstance(seq.getObjectAt(index));
    }

    public static CertRepMessage getInstance(Object o) {
        if (o instanceof CertRepMessage) {
            return (CertRepMessage) o;
        }
        if (o != null) {
            return new CertRepMessage(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public CertRepMessage(CMPCertificate[] caPubs2, CertResponse[] response2) {
        if (response2 == null) {
            throw new IllegalArgumentException("'response' cannot be null");
        }
        if (caPubs2 != null) {
            ASN1EncodableVector v = new ASN1EncodableVector();
            for (CMPCertificate add : caPubs2) {
                v.add(add);
            }
            this.caPubs = new DERSequence(v);
        }
        ASN1EncodableVector v2 = new ASN1EncodableVector();
        for (CertResponse add2 : response2) {
            v2.add(add2);
        }
        this.response = new DERSequence(v2);
    }

    public CMPCertificate[] getCaPubs() {
        if (this.caPubs == null) {
            return null;
        }
        CMPCertificate[] results = new CMPCertificate[this.caPubs.size()];
        for (int i = 0; i != results.length; i++) {
            results[i] = CMPCertificate.getInstance(this.caPubs.getObjectAt(i));
        }
        return results;
    }

    public CertResponse[] getResponse() {
        CertResponse[] results = new CertResponse[this.response.size()];
        for (int i = 0; i != results.length; i++) {
            results[i] = CertResponse.getInstance(this.response.getObjectAt(i));
        }
        return results;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.caPubs != null) {
            v.add(new DERTaggedObject(true, 1, this.caPubs));
        }
        v.add(this.response);
        return new DERSequence(v);
    }
}
