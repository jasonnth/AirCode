package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class CAKeyUpdAnnContent extends ASN1Object {
    private CMPCertificate newWithNew;
    private CMPCertificate newWithOld;
    private CMPCertificate oldWithNew;

    private CAKeyUpdAnnContent(ASN1Sequence seq) {
        this.oldWithNew = CMPCertificate.getInstance(seq.getObjectAt(0));
        this.newWithOld = CMPCertificate.getInstance(seq.getObjectAt(1));
        this.newWithNew = CMPCertificate.getInstance(seq.getObjectAt(2));
    }

    public static CAKeyUpdAnnContent getInstance(Object o) {
        if (o instanceof CAKeyUpdAnnContent) {
            return (CAKeyUpdAnnContent) o;
        }
        if (o != null) {
            return new CAKeyUpdAnnContent(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public CAKeyUpdAnnContent(CMPCertificate oldWithNew2, CMPCertificate newWithOld2, CMPCertificate newWithNew2) {
        this.oldWithNew = oldWithNew2;
        this.newWithOld = newWithOld2;
        this.newWithNew = newWithNew2;
    }

    public CMPCertificate getOldWithNew() {
        return this.oldWithNew;
    }

    public CMPCertificate getNewWithOld() {
        return this.newWithOld;
    }

    public CMPCertificate getNewWithNew() {
        return this.newWithNew;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.oldWithNew);
        v.add(this.newWithOld);
        v.add(this.newWithNew);
        return new DERSequence(v);
    }
}
