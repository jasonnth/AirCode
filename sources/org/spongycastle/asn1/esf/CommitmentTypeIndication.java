package org.spongycastle.asn1.esf;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class CommitmentTypeIndication extends ASN1Object {
    private ASN1ObjectIdentifier commitmentTypeId;
    private ASN1Sequence commitmentTypeQualifier;

    private CommitmentTypeIndication(ASN1Sequence seq) {
        this.commitmentTypeId = (ASN1ObjectIdentifier) seq.getObjectAt(0);
        if (seq.size() > 1) {
            this.commitmentTypeQualifier = (ASN1Sequence) seq.getObjectAt(1);
        }
    }

    public CommitmentTypeIndication(ASN1ObjectIdentifier commitmentTypeId2) {
        this.commitmentTypeId = commitmentTypeId2;
    }

    public CommitmentTypeIndication(ASN1ObjectIdentifier commitmentTypeId2, ASN1Sequence commitmentTypeQualifier2) {
        this.commitmentTypeId = commitmentTypeId2;
        this.commitmentTypeQualifier = commitmentTypeQualifier2;
    }

    public static CommitmentTypeIndication getInstance(Object obj) {
        if (obj == null || (obj instanceof CommitmentTypeIndication)) {
            return (CommitmentTypeIndication) obj;
        }
        return new CommitmentTypeIndication(ASN1Sequence.getInstance(obj));
    }

    public ASN1ObjectIdentifier getCommitmentTypeId() {
        return this.commitmentTypeId;
    }

    public ASN1Sequence getCommitmentTypeQualifier() {
        return this.commitmentTypeQualifier;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.commitmentTypeId);
        if (this.commitmentTypeQualifier != null) {
            v.add(this.commitmentTypeQualifier);
        }
        return new DERSequence(v);
    }
}
