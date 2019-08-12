package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class ProtectedPart extends ASN1Object {
    private PKIBody body;
    private PKIHeader header;

    private ProtectedPart(ASN1Sequence seq) {
        this.header = PKIHeader.getInstance(seq.getObjectAt(0));
        this.body = PKIBody.getInstance(seq.getObjectAt(1));
    }

    public static ProtectedPart getInstance(Object o) {
        if (o instanceof ProtectedPart) {
            return (ProtectedPart) o;
        }
        if (o != null) {
            return new ProtectedPart(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public ProtectedPart(PKIHeader header2, PKIBody body2) {
        this.header = header2;
        this.body = body2;
    }

    public PKIHeader getHeader() {
        return this.header;
    }

    public PKIBody getBody() {
        return this.body;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.header);
        v.add(this.body);
        return new DERSequence(v);
    }
}
