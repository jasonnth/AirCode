package org.spongycastle.asn1.esf;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class SigPolicyQualifiers extends ASN1Object {
    ASN1Sequence qualifiers;

    public static SigPolicyQualifiers getInstance(Object obj) {
        if (obj instanceof SigPolicyQualifiers) {
            return (SigPolicyQualifiers) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new SigPolicyQualifiers(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private SigPolicyQualifiers(ASN1Sequence seq) {
        this.qualifiers = seq;
    }

    public SigPolicyQualifiers(SigPolicyQualifierInfo[] qualifierInfos) {
        ASN1EncodableVector v = new ASN1EncodableVector();
        for (SigPolicyQualifierInfo add : qualifierInfos) {
            v.add(add);
        }
        this.qualifiers = new DERSequence(v);
    }

    public int size() {
        return this.qualifiers.size();
    }

    public SigPolicyQualifierInfo getInfoAt(int i) {
        return SigPolicyQualifierInfo.getInstance(this.qualifiers.getObjectAt(i));
    }

    public ASN1Primitive toASN1Primitive() {
        return this.qualifiers;
    }
}
