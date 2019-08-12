package org.spongycastle.asn1.esf;

import java.io.IOException;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class OtherRevRefs extends ASN1Object {
    private ASN1ObjectIdentifier otherRevRefType;
    private ASN1Encodable otherRevRefs;

    public static OtherRevRefs getInstance(Object obj) {
        if (obj instanceof OtherRevRefs) {
            return (OtherRevRefs) obj;
        }
        if (obj != null) {
            return new OtherRevRefs(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private OtherRevRefs(ASN1Sequence seq) {
        if (seq.size() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + seq.size());
        }
        this.otherRevRefType = new ASN1ObjectIdentifier(((ASN1ObjectIdentifier) seq.getObjectAt(0)).getId());
        try {
            this.otherRevRefs = ASN1Primitive.fromByteArray(seq.getObjectAt(1).toASN1Primitive().getEncoded(ASN1Encoding.DER));
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }

    public OtherRevRefs(ASN1ObjectIdentifier otherRevRefType2, ASN1Encodable otherRevRefs2) {
        this.otherRevRefType = otherRevRefType2;
        this.otherRevRefs = otherRevRefs2;
    }

    public ASN1ObjectIdentifier getOtherRevRefType() {
        return this.otherRevRefType;
    }

    public ASN1Encodable getOtherRevRefs() {
        return this.otherRevRefs;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.otherRevRefType);
        v.add(this.otherRevRefs);
        return new DERSequence(v);
    }
}
