package org.spongycastle.asn1.pkcs;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class CRLBag extends ASN1Object {
    private ASN1ObjectIdentifier crlId;
    private ASN1Encodable crlValue;

    private CRLBag(ASN1Sequence seq) {
        this.crlId = (ASN1ObjectIdentifier) seq.getObjectAt(0);
        this.crlValue = ((DERTaggedObject) seq.getObjectAt(1)).getObject();
    }

    public static CRLBag getInstance(Object o) {
        if (o instanceof CRLBag) {
            return (CRLBag) o;
        }
        if (o != null) {
            return new CRLBag(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public CRLBag(ASN1ObjectIdentifier crlId2, ASN1Encodable crlValue2) {
        this.crlId = crlId2;
        this.crlValue = crlValue2;
    }

    public ASN1ObjectIdentifier getCrlId() {
        return this.crlId;
    }

    public ASN1Encodable getCrlValue() {
        return this.crlValue;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.crlId);
        v.add(new DERTaggedObject(0, this.crlValue));
        return new DERSequence(v);
    }
}
