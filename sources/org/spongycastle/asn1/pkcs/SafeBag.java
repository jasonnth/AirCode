package org.spongycastle.asn1.pkcs;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DLSequence;
import org.spongycastle.asn1.DLTaggedObject;

public class SafeBag extends ASN1Object {
    private ASN1Set bagAttributes;
    private ASN1ObjectIdentifier bagId;
    private ASN1Encodable bagValue;

    public SafeBag(ASN1ObjectIdentifier oid, ASN1Encodable obj) {
        this.bagId = oid;
        this.bagValue = obj;
        this.bagAttributes = null;
    }

    public SafeBag(ASN1ObjectIdentifier oid, ASN1Encodable obj, ASN1Set bagAttributes2) {
        this.bagId = oid;
        this.bagValue = obj;
        this.bagAttributes = bagAttributes2;
    }

    public static SafeBag getInstance(Object obj) {
        if (obj instanceof SafeBag) {
            return (SafeBag) obj;
        }
        if (obj != null) {
            return new SafeBag(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private SafeBag(ASN1Sequence seq) {
        this.bagId = (ASN1ObjectIdentifier) seq.getObjectAt(0);
        this.bagValue = ((ASN1TaggedObject) seq.getObjectAt(1)).getObject();
        if (seq.size() == 3) {
            this.bagAttributes = (ASN1Set) seq.getObjectAt(2);
        }
    }

    public ASN1ObjectIdentifier getBagId() {
        return this.bagId;
    }

    public ASN1Encodable getBagValue() {
        return this.bagValue;
    }

    public ASN1Set getBagAttributes() {
        return this.bagAttributes;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.bagId);
        v.add(new DLTaggedObject(true, 0, this.bagValue));
        if (this.bagAttributes != null) {
            v.add(this.bagAttributes);
        }
        return new DLSequence(v);
    }
}
