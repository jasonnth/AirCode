package org.spongycastle.asn1.esf;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class CommitmentTypeQualifier extends ASN1Object {
    private ASN1ObjectIdentifier commitmentTypeIdentifier;
    private ASN1Encodable qualifier;

    public CommitmentTypeQualifier(ASN1ObjectIdentifier commitmentTypeIdentifier2) {
        this(commitmentTypeIdentifier2, null);
    }

    public CommitmentTypeQualifier(ASN1ObjectIdentifier commitmentTypeIdentifier2, ASN1Encodable qualifier2) {
        this.commitmentTypeIdentifier = commitmentTypeIdentifier2;
        this.qualifier = qualifier2;
    }

    private CommitmentTypeQualifier(ASN1Sequence as) {
        this.commitmentTypeIdentifier = (ASN1ObjectIdentifier) as.getObjectAt(0);
        if (as.size() > 1) {
            this.qualifier = as.getObjectAt(1);
        }
    }

    public static CommitmentTypeQualifier getInstance(Object as) {
        if (as instanceof CommitmentTypeQualifier) {
            return (CommitmentTypeQualifier) as;
        }
        if (as != null) {
            return new CommitmentTypeQualifier(ASN1Sequence.getInstance(as));
        }
        return null;
    }

    public ASN1ObjectIdentifier getCommitmentTypeIdentifier() {
        return this.commitmentTypeIdentifier;
    }

    public ASN1Encodable getQualifier() {
        return this.qualifier;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector dev = new ASN1EncodableVector();
        dev.add(this.commitmentTypeIdentifier);
        if (this.qualifier != null) {
            dev.add(this.qualifier);
        }
        return new DERSequence(dev);
    }
}
