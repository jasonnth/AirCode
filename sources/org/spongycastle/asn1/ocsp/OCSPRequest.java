package org.spongycastle.asn1.ocsp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class OCSPRequest extends ASN1Object {
    Signature optionalSignature;
    TBSRequest tbsRequest;

    public OCSPRequest(TBSRequest tbsRequest2, Signature optionalSignature2) {
        this.tbsRequest = tbsRequest2;
        this.optionalSignature = optionalSignature2;
    }

    private OCSPRequest(ASN1Sequence seq) {
        this.tbsRequest = TBSRequest.getInstance(seq.getObjectAt(0));
        if (seq.size() == 2) {
            this.optionalSignature = Signature.getInstance((ASN1TaggedObject) seq.getObjectAt(1), true);
        }
    }

    public static OCSPRequest getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static OCSPRequest getInstance(Object obj) {
        if (obj instanceof OCSPRequest) {
            return (OCSPRequest) obj;
        }
        if (obj != null) {
            return new OCSPRequest(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public TBSRequest getTbsRequest() {
        return this.tbsRequest;
    }

    public Signature getOptionalSignature() {
        return this.optionalSignature;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.tbsRequest);
        if (this.optionalSignature != null) {
            v.add(new DERTaggedObject(true, 0, this.optionalSignature));
        }
        return new DERSequence(v);
    }
}
